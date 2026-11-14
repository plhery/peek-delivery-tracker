package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
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
import org.junit.Test

/**
 * Signed out, a parcel followed through someone else's link is forgotten while the phone holds a key whose saved copy
 * it could not read. DeviceParcelFiles.save writes the list as it is, empty too, as the iPhone does, so the forgotten
 * parcel's row leaves the file and stays forgotten at the next launch.
 */
class ForgottenFollowedParcelTest {
    private val key = "k".repeat(43)
    private val now = Instant.parse("2026-11-12T08:00:00Z")

    private fun pkg(id: UUID) = PublicPackage(
        id = id, trackingNumber = "TESTPARCEL000111", label = "", carrier = CarrierId.Dhl, createdAt = "2026-11-01T08:00:00Z",
        syncStatus = SyncStatus.Ok, carrierData = PublicPackageCarrierData(), notificationsMuted = false, trackingEvents = emptyList(),
    )

    private fun entry(linkId: String, id: UUID, viewer: Boolean? = null) = DeviceParcel(
        linkId = linkId, label = "", archivedAt = null, parcel = Parcel(shared = pkg(id)),
        checkedAt = now.timeIntervalSinceReferenceDate, viewer = viewer,
    )

    private class MemoryFile(var data: ByteArray?) : DeviceParcelFile {
        override fun read(): ByteArray? = data

        override fun write(data: ByteArray) {
            this.data = data
        }

        override fun delete() {
            data = null
        }
    }

    /** The phone is offline: every call to the service fails. */
    private val offline = DeviceParcelClient(
        lookup = { throw IOException("offline") },
        read = { _, _ -> throw IOException("offline") },
        forget = { _, _ -> throw IOException("offline") },
        update = { _, _, _ -> throw IOException("offline") },
        detect = { _, _, _ -> throw IOException("offline") },
    )

    private fun device(storage: DeviceParcelStorage, scope: CoroutineScope) = DeviceParcels(
        offline, storage, PostcodeMemory(MemoryDefaults()), scope, region = { Locale.forLanguageTag("fr-CH") },
        language = { AppLanguage.En }, now = { now },
    )

    private fun keychain(keys: Map<String, String>) = MemorySessionPersistence().apply {
        save(JsonEncoder.deliveryTracker.encode(MapSerializer(String.serializer(), String.serializer()), keys))
    }

    private fun rows(saved: List<DeviceParcel>): List<JsonObject> =
        Json.parseToJsonElement(DeviceParcelFiles.encoder.encode(ListSerializer(DeviceParcel.serializer()), saved).decodeToString())
            .jsonArray.map { it.jsonObject }

    @Test
    fun aParcelFollowedThroughSomeoneElsesLinkStaysForgottenAfterARelaunch() = runTest {
        val followed = UUID.randomUUID()
        val own = UUID.randomUUID()
        val saved = rows(listOf(entry("33333333333A", followed, viewer = true), entry("22222222222B", own)))
        // The second row is one this build can't read (an update changed the model): its key reads it again later.
        val file = MemoryFile(JsonArray(listOf(saved[0], JsonObject(saved[1] + ("checkedAt" to JsonPrimitive("yesterday"))))).toString().toByteArray())
        val keys = keychain(mapOf("22222222222B" to key))
        val forgets = MemorySessionPersistence()
        val storage = DeviceParcelFiles(keys, file, forgets)

        val first = device(storage, this)
        assertEquals(listOf(followed), first.list().map { it.id })
        first.forget(followed)
        assertEquals("Forgotten on screen", emptyList<UUID>(), first.list().map { it.id })

        // The app is closed and opened again, still offline.
        val relaunched = device(DeviceParcelFiles(keys, file, forgets), this)
        assertEquals(
            "A parcel followed through someone else's link, forgotten while another saved copy couldn't be read, is listed again after a relaunch",
            emptyList<UUID>(),
            relaunched.list().map { it.id },
        )
    }

    /** With every saved copy readable, the same forget lasts. */
    @Test
    fun withEverySavedCopyReadableTheForgetLasts() = runTest {
        val followed = UUID.randomUUID()
        val own = UUID.randomUUID()
        val file = MemoryFile(JsonArray(rows(listOf(entry("33333333333A", followed, viewer = true), entry("22222222222B", own)))).toString().toByteArray())
        val keys = keychain(mapOf("22222222222B" to key))
        val forgets = MemorySessionPersistence()
        val storage = DeviceParcelFiles(keys, file, forgets)

        val first = device(storage, this)
        assertEquals(listOf(followed, own), first.list().map { it.id })
        first.forget(followed)

        val relaunched = device(DeviceParcelFiles(keys, file, forgets), this)
        assertEquals(listOf(own), relaunched.list().map { it.id })
    }
}
