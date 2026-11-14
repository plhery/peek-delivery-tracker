package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import java.io.IOException
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * ForgetAllRaceTest more widely: the window is any of Forget all's calls to the service for the listed parcels,
 * notifications or not, and a poll whose read of the lost parcel was already under way when Forget all began lands
 * for a key already set aside, so it is dropped. A key moved aside before Forget all's first call to the service is
 * never brought back by a read that lands afterwards.
 */
class ForgetAllReadUnderWayTest {
    private val key = "k".repeat(43)
    private val now = Instant.parse("2026-11-12T08:00:00Z")

    private fun pkg(id: UUID) = PublicPackage(
        id = id, trackingNumber = "TESTPARCEL000333", label = "", carrier = CarrierId.Dhl, createdAt = "2026-11-01T08:00:00Z",
        syncStatus = SyncStatus.Ok, carrierData = PublicPackageCarrierData(), notificationsMuted = false, trackingEvents = emptyList(),
    )

    private fun owner(linkId: String) = ParcelLink(
        id = linkId, role = ParcelLinkRole.Owner, kind = ParcelLinkKind.Lookup, createdAt = "2026-11-01T08:00:00Z", numberShown = true,
        canKeep = true, showNumber = false, gift = false, shared = true,
    )

    private class Memory : DeviceParcelStorage {
        var parcels: List<DeviceParcel> = emptyList()
        var keys: Map<String, String> = emptyMap()
        var pendingForgets: Map<String, String> = emptyMap()

        override fun load() = DeviceParcelStorage.Contents(parcels, keys, pendingForgets)

        override fun save(parcels: List<DeviceParcel>, keys: Map<String, String>, pendingForgets: Map<String, String>) {
            this.parcels = parcels
            this.keys = keys
            this.pendingForgets = pendingForgets
        }
    }

    private val lostLink = "33333333333C"
    private val listedLink = "33333333333A"

    /** `listed`: whether a parcel without notifications is listed beside the lost one. */
    private fun storage(listed: Boolean) = Memory().apply {
        if (listed) {
            parcels = listOf(
                DeviceParcel(
                    linkId = listedLink, label = "Lamp", archivedAt = null, parcel = Parcel(shared = pkg(UUID.randomUUID())),
                    checkedAt = now.timeIntervalSinceReferenceDate,
                ),
            )
        }
        keys = if (listed) mapOf(listedLink to key, lostLink to key) else mapOf(lostLink to key)
    }

    private fun client(slowRead: CompletableDeferred<Unit>, slowForget: CompletableDeferred<Unit>) = DeviceParcelClient(
        lookup = { throw IOException("unused") },
        read = { linkId, _ ->
            if (linkId == lostLink) {
                slowRead.await()
                PublicParcelResponse(link = owner(linkId), parcel = pkg(UUID.randomUUID()))
            } else {
                null
            }
        },
        forget = { linkId, _ -> if (linkId == listedLink) slowForget.await() },
        update = { _, _, _ -> throw IOException("unused") },
        detect = { _, _, _ -> throw IOException("unused") },
        removeAlert = { _, _ -> },
    )

    private fun device(storage: Memory, client: DeviceParcelClient, scope: CoroutineScope) = DeviceParcels(
        client, storage, PostcodeMemory(MemoryDefaults()), scope, region = { Locale.forLanguageTag("fr-CH") },
        language = { AppLanguage.En }, now = { now },
    )

    /** The poll was reading the lost parcel when Forget all began; telling the service of the listed one takes a while. */
    @Test
    fun forgetAllLeavesNothingWhenAReadWasUnderWayAsItBegan() = runTest {
        val storage = storage(listed = true)
        val slowRead = CompletableDeferred<Unit>()
        val slowForget = CompletableDeferred<Unit>()
        val device = device(storage, client(slowRead, slowForget), this)

        val polling = launch { device.refresh() }
        runCurrent()
        val forgetting = launch { device.forgetAll() }
        runCurrent()
        slowRead.complete(Unit)
        polling.join()
        slowForget.complete(Unit)
        forgetting.join()

        assertEquals("The lost parcel, read by a poll begun before Forget all, is listed after it", emptyList<Parcel>(), device.list())
        assertEquals("Its key stays after Forget all", emptySet<String>(), storage.keys.keys)
    }

    /** A key set aside before the first call: the read landing after is dropped. */
    @Test
    fun aKeySetAsideFirstIsNotBroughtBack() = runTest {
        val storage = storage(listed = false)
        val slowRead = CompletableDeferred<Unit>()
        val slowForget = CompletableDeferred<Unit>()
        val device = device(storage, client(slowRead, slowForget), this)

        val polling = launch { device.refresh() }
        runCurrent()
        val forgetting = launch { device.forgetAll() }
        runCurrent()
        slowRead.complete(Unit)
        polling.join()
        forgetting.join()

        assertEquals(emptyList<Parcel>(), device.list())
        assertEquals(emptySet<String>(), storage.keys.keys)
        assertEquals(emptyMap<String, String>(), storage.pendingForgets)
    }
}
