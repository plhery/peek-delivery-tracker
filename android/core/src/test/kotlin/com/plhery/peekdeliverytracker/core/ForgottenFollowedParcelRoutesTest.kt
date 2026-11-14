package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CreatePackageRequest
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.PublicLookupResponse
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import java.io.IOException
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The other ways to ForgottenFollowedParcelTest's state: online while the own parcel's read fails, with the followed
 * parcel's notifications on, and a key saved without its file (a full disk) rather than a row this build can't read.
 * Each forget lasts through a relaunch and once the own parcel is read again.
 */
class ForgottenFollowedParcelRoutesTest {
    private val key = "k".repeat(43)
    private val now = Instant.parse("2026-11-12T08:00:00Z")
    private val viewerLink = "33333333333A"
    private val ownLink = "22222222222B"

    private fun pkg(id: UUID, number: String = "TESTPARCEL000111") = PublicPackage(
        id = id, trackingNumber = number, label = "", carrier = CarrierId.Dhl, createdAt = "2026-11-01T08:00:00Z",
        syncStatus = SyncStatus.Ok, carrierData = PublicPackageCarrierData(), notificationsMuted = false, trackingEvents = emptyList(),
    )

    private fun entry(linkId: String, id: UUID, viewer: Boolean? = null, alertToken: String? = null) = DeviceParcel(
        linkId = linkId, label = "", archivedAt = null, parcel = Parcel(shared = pkg(id)),
        checkedAt = now.timeIntervalSinceReferenceDate, viewer = viewer, alertToken = alertToken,
    )

    private fun ownerLink(id: String) = ParcelLink(
        id = id, role = ParcelLinkRole.Owner, kind = ParcelLinkKind.Lookup, createdAt = "2026-11-01T08:00:00Z", numberShown = true,
        canKeep = true, showNumber = false, gift = false, shared = true,
    )

    private fun readerLink(id: String) = ParcelLink(
        id = id, role = ParcelLinkRole.Viewer, kind = ParcelLinkKind.Shared, createdAt = "2026-11-01T08:00:00Z", numberShown = false,
        canKeep = true,
    )

    private class MemoryFile(var data: ByteArray?) : DeviceParcelFile {
        var full = false

        override fun read(): ByteArray? = data

        override fun write(data: ByteArray) {
            if (full) throw IOException("No space left on device")
            this.data = data
        }

        override fun delete() {
            data = null
        }
    }

    private val offline = DeviceParcelClient(
        lookup = { throw IOException("offline") },
        read = { _, _ -> throw IOException("offline") },
        forget = { _, _ -> throw IOException("offline") },
        update = { _, _, _ -> throw IOException("offline") },
        detect = { _, _, _ -> throw IOException("offline") },
        removeAlert = { _, _ -> throw IOException("offline") },
    )

    /** The service answers both links; `ownFails` makes the owner read fail (a 503, a rate limit). */
    private fun online(followed: UUID, own: UUID, ownFails: Boolean = false) = DeviceParcelClient(
        lookup = { request -> PublicLookupResponse(link = ownerLink(ownLink), key = key, parcel = pkg(own, request.trackingNumber)) },
        read = { linkId, linkKey ->
            when {
                linkId == viewerLink && linkKey == null -> PublicParcelResponse(link = readerLink(viewerLink), parcel = pkg(followed))
                linkId == ownLink && linkKey == key -> if (ownFails) throw IOException("503") else PublicParcelResponse(link = ownerLink(ownLink), parcel = pkg(own))
                else -> null
            }
        },
        forget = { _, _ -> },
        update = { _, _, _ -> throw IOException("unused") },
        detect = { _, _, _ -> throw IOException("unused") },
        removeAlert = { _, _ -> },
    )

    private fun device(client: DeviceParcelClient, storage: DeviceParcelStorage, scope: CoroutineScope) = DeviceParcels(
        client, storage, PostcodeMemory(MemoryDefaults()), scope, region = { Locale.forLanguageTag("fr-CH") },
        language = { AppLanguage.En }, now = { now },
    )

    private fun keychain(keys: Map<String, String>) = MemorySessionPersistence().apply {
        if (keys.isNotEmpty()) save(JsonEncoder.deliveryTracker.encode(MapSerializer(String.serializer(), String.serializer()), keys))
    }

    private fun rows(saved: List<DeviceParcel>): List<JsonObject> =
        Json.parseToJsonElement(DeviceParcelFiles.encoder.encode(ListSerializer(DeviceParcel.serializer()), saved).decodeToString())
            .jsonArray.map { it.jsonObject }

    /** The followed row readable, the own row not (one this build can't read). */
    private fun mixedFile(followed: UUID, own: UUID, alertToken: String? = null): MemoryFile {
        val saved = rows(listOf(entry(viewerLink, followed, viewer = true, alertToken = alertToken), entry(ownLink, own)))
        return MemoryFile(JsonArray(listOf(saved[0], JsonObject(saved[1] + ("checkedAt" to JsonPrimitive("yesterday"))))).toString().toByteArray())
    }

    /** With its notifications on, the offline forget keeps the row as forgotten, and it lasts. */
    @Test
    fun offlineWithTheFollowedParcelsNotificationsOnTheForgetLasts() = runTest {
        val followed = UUID.randomUUID()
        val own = UUID.randomUUID()
        val file = mixedFile(followed, own, alertToken = "fcm-token")
        val keys = keychain(mapOf(ownLink to key))
        val forgets = MemorySessionPersistence()

        val first = device(offline, DeviceParcelFiles(keys, file, forgets), this)
        first.forget(followed)
        assertEquals(emptyList<UUID>(), first.list().map { it.id })

        val relaunched = device(offline, DeviceParcelFiles(keys, file, forgets), this)
        assertEquals(emptyList<UUID>(), relaunched.list().map { it.id })
    }

    /**
     * Online, its notifications on and turned off at the forget, while the own parcel's read fails: the parcel stays
     * forgotten at the relaunch, and once the service answers the own parcel is read again alone.
     */
    @Test
    fun onlineWithTheOwnParcelsReadFailingTheForgetLasts() = runTest {
        val followed = UUID.randomUUID()
        val own = UUID.randomUUID()
        val file = mixedFile(followed, own, alertToken = "fcm-token")
        val keys = keychain(mapOf(ownLink to key))
        val forgets = MemorySessionPersistence()

        val first = device(online(followed, own, ownFails = true), DeviceParcelFiles(keys, file, forgets), this)
        runCatching { first.refresh() }
        first.forget(followed)
        assertEquals(emptyList<UUID>(), first.list().map { it.id })

        val relaunched = device(online(followed, own), DeviceParcelFiles(keys, file, forgets), this)
        relaunched.refresh()
        assertEquals("The forgotten parcel is listed again after a relaunch", setOf(own), relaunched.list().map { it.id }.toSet())
        val again = device(online(followed, own), DeviceParcelFiles(keys, file, forgets), this)
        assertTrue(
            "The forgotten parcel is still listed after the own parcel was read again and the app relaunched",
            followed !in again.list().map { it.id },
        )
    }

    /**
     * A lookup whose file write failed (a full disk) leaves its key alone at the next launch, and forgetting the
     * followed parcel offline then lasts the same way.
     */
    @Test
    fun aKeySavedWithoutItsFileKeepsTheForgetToo() = runTest {
        val followed = UUID.randomUUID()
        val own = UUID.randomUUID()
        val file = MemoryFile(JsonArray(rows(listOf(entry(viewerLink, followed, viewer = true)))).toString().toByteArray())
        val keys = keychain(emptyMap())
        val forgets = MemorySessionPersistence()

        val first = device(online(followed, own), DeviceParcelFiles(keys, file, forgets), this)
        file.full = true
        first.add(CreatePackageRequest(trackingNumber = "TESTPARCEL000222"))
        file.full = false

        val second = device(offline, DeviceParcelFiles(keys, file, forgets), this)
        assertEquals("The lookup's parcel is kept by its key alone", 1, second.unlistedCount)
        second.forget(followed)

        val third = device(offline, DeviceParcelFiles(keys, file, forgets), this)
        assertEquals("The forgotten parcel is listed again after a relaunch", emptyList<UUID>(), third.list().map { it.id })
    }
}
