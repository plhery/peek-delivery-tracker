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
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Signed out, Forget all sets aside the keys whose parcels were lost on this phone before it calls the service, then
 * forgets the listed parcels one by one. A refresh that runs meanwhile (the 30-second poll) no longer reads such a
 * key's parcel again, so nothing is left, listed or with its key, after Forget all.
 */
class ForgetAllRaceTest {
    private val key = "k".repeat(43)
    private val now = Instant.parse("2026-11-12T08:00:00Z")

    private fun pkg(id: UUID) = PublicPackage(
        id = id, trackingNumber = "TESTPARCEL000222", label = "", carrier = CarrierId.Dhl, createdAt = "2026-11-01T08:00:00Z",
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

    /**
     * A listed parcel "22222222222A" with notifications on, and the key of "22222222222C", whose saved copy was lost.
     * Turning the notifications off waits for `slowAlert`; the lost parcel reads at once.
     */
    private class Setup(val storage: Memory, val client: DeviceParcelClient, val slowAlert: CompletableDeferred<Unit>, val lost: UUID)

    private fun setup(): Setup {
        val listed = UUID.randomUUID()
        val lost = UUID.randomUUID()
        val storage = Memory().apply {
            parcels = listOf(
                DeviceParcel(
                    linkId = "22222222222A", label = "Lamp", archivedAt = null, parcel = Parcel(shared = pkg(listed)),
                    checkedAt = now.timeIntervalSinceReferenceDate, alertToken = "token-1",
                ),
            )
            keys = mapOf("22222222222A" to key, "22222222222C" to key)
        }
        val slowAlert = CompletableDeferred<Unit>()
        val client = DeviceParcelClient(
            lookup = { throw IOException("unused") },
            read = { linkId, _ -> if (linkId == "22222222222C") PublicParcelResponse(link = owner(linkId), parcel = pkg(lost)) else null },
            forget = { _, _ -> },
            update = { _, _, _ -> throw IOException("unused") },
            detect = { _, _, _ -> throw IOException("unused") },
            removeAlert = { _, _ -> slowAlert.await() },
        )
        return Setup(storage, client, slowAlert, lost)
    }

    private fun device(setup: Setup, scope: CoroutineScope) = DeviceParcels(
        setup.client, setup.storage, PostcodeMemory(MemoryDefaults()), scope, region = { Locale.forLanguageTag("fr-CH") },
        language = { AppLanguage.En }, now = { now },
    )

    @Test
    fun forgetAllLeavesNothingWhenARefreshReadsALostParcelMeanwhile() = runTest {
        val setup = setup()
        val device = device(setup, this)

        val forgetting = launch { device.forgetAll() }
        runCurrent()
        // The poll comes round while the first parcel's notifications are being turned off.
        device.refresh()
        setup.slowAlert.complete(Unit)
        forgetting.join()

        assertEquals(
            "A parcel whose saved copy was lost, read again by a refresh while Forget all was under way, is still listed after Forget all",
            emptyList<UUID>(),
            device.list().map { it.id },
        )
        assertEquals("Its owner key stays on the phone after Forget all", emptySet<String>(), setup.storage.keys.keys)
    }

    /** The same Forget all with nothing running beside it leaves nothing. */
    @Test
    fun forgetAllAloneLeavesNothing() = runTest {
        val setup = setup()
        val device = device(setup, this)

        val forgetting = launch { device.forgetAll() }
        runCurrent()
        setup.slowAlert.complete(Unit)
        forgetting.join()

        assertTrue(device.list().isEmpty())
        assertTrue(setup.storage.keys.isEmpty())
    }
}
