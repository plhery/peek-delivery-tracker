package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.io.IOException
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The other ways to AlertRoundForgetTest's state. The service keeps an app alert until the journey ends, and forgetting
 * someone else's link tells it nothing else, so the phone turns the alert off itself: on the next launch, when the
 * parcel is followed again and forgotten in the same run, when the owner's link takes a reader link's place during the
 * round, and on the next refresh after a removal that failed. A parcel of this phone's own goes with its forget.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AlertRoundForgetRoutesTest {
    private val token = "fcm-token"

    private class Memory(var parcels: List<DeviceParcel> = emptyList(), var keys: Map<String, String> = emptyMap()) : DeviceParcelStorage {
        override fun load() = DeviceParcelStorage.Contents(parcels, keys, emptyMap())

        override fun save(parcels: List<DeviceParcel>, keys: Map<String, String>, pendingForgets: Map<String, String>) {
            this.parcels = parcels
            this.keys = keys
        }
    }

    /** The service: alerts by link; a link forgotten with its key goes, its alerts with it, as the database's cascade does. */
    private class Service(val held: String? = null) {
        val alerts = mutableSetOf<String>()
        val deleted = mutableSetOf<String>()
        val gate = CompletableDeferred<Unit>()
        var removalsFailing = false
        val client = DeviceParcelClient(
            lookup = { throw IOException("offline") },
            read = { _, _ -> throw IOException("offline") },
            forget = { linkId, _ ->
                deleted += linkId
                alerts -= linkId
            },
            update = { _, _, _ -> throw IOException("offline") },
            detect = { _, _, _ -> throw IOException("offline") },
            setAlert = { linkId, _, _ ->
                if (linkId == held && !gate.isCompleted) gate.await()
                if (linkId in deleted) {
                    DeviceAlertAnswer.Gone
                } else {
                    alerts += linkId
                    DeviceAlertAnswer.On
                }
            },
            removeAlert = { linkId, _ ->
                if (removalsFailing) throw IOException("offline")
                alerts -= linkId
            },
        )
    }

    private fun pkg(id: UUID) = PublicPackage(
        id = id, trackingNumber = "TESTPARCEL000333", label = "", carrier = CarrierId.Dhl, createdAt = "2026-11-01T08:00:00Z",
        syncStatus = SyncStatus.Ok, carrierData = PublicPackageCarrierData(), notificationsMuted = false,
        trackingEvents = listOf(
            TrackingEvent(id = UUID.randomUUID(), packageId = id, stage = TrackingStage.InTransit, description = "", occurredAt = "2026-11-02T08:00:00Z"),
        ),
    )

    private fun link(id: String, role: ParcelLinkRole) = ParcelLink(
        id = id, role = role, kind = if (role == ParcelLinkRole.Owner) ParcelLinkKind.Lookup else ParcelLinkKind.Shared,
        createdAt = "2026-11-01T08:00:00Z", numberShown = false, canKeep = true,
    )

    private fun entry(linkId: String, id: UUID, viewer: Boolean) = DeviceParcel(
        linkId = linkId, label = "", archivedAt = null, parcel = Parcel(shared = pkg(id)),
        checkedAt = Instant.parse("2026-11-12T08:00:00Z").timeIntervalSinceReferenceDate, viewer = if (viewer) true else null, alertToken = token,
    )

    private fun device(client: DeviceParcelClient, storage: DeviceParcelStorage, scope: CoroutineScope) = DeviceParcels(
        client, storage, PostcodeMemory(MemoryDefaults()), scope, region = { Locale.forLanguageTag("fr-CH") }, language = { AppLanguage.En },
    )

    /** Someone else's parcel forgotten during the launch's round, as in the proof; returns the storage the app keeps. */
    private suspend fun kotlinx.coroutines.test.TestScope.forgottenDuringRound(service: Service, second: UUID): Memory {
        val storage = Memory(listOf(entry("44444444444A", UUID.randomUUID(), viewer = true), entry("44444444444B", second, viewer = true)))
        service.alerts += setOf("44444444444A", "44444444444B")
        val device = device(service.client, storage, this)
        val round = launch { device.registerAlerts(token, NativePushLocale.En) }
        runCurrent()
        device.forget(second)
        service.gate.complete(Unit)
        round.join()
        return storage
    }

    /** No self-heal: the next launch's refresh (stopForgottenAlerts) and round leave the forgotten link's alert on. */
    @Test
    fun theNextLaunchTurnsTheForgottenParcelsNotificationsOff() = runTest {
        val service = Service(held = "44444444444A")
        val storage = forgottenDuringRound(service, UUID.randomUUID())

        val relaunched = device(service.client, storage, this)
        runCatching { relaunched.refresh(all = true) }
        relaunched.registerAlerts(token, NativePushLocale.En)

        assertTrue("The next launch does not turn it off either (the service still sends: ${service.alerts})", "44444444444B" !in service.alerts)
    }

    /**
     * No remedy in the same run: tapping its push opens the link and follows it again, but the round already counts it
     * as asked (alertsAsked), so it gets no token, and forgetting it again removes nothing.
     */
    @Test
    fun followingAgainAndForgettingInTheSameRunTurnsThemOff() = runTest {
        val service = Service(held = "44444444444C")
        val second = UUID.randomUUID()
        service.alerts += setOf("44444444444C", "44444444444D")
        val device = device(service.client, Memory(listOf(entry("44444444444C", UUID.randomUUID(), viewer = true), entry("44444444444D", second, viewer = true))), this)
        val round = launch { device.registerAlerts(token, NativePushLocale.En) }
        runCurrent()
        device.forget(second)
        service.gate.complete(Unit)
        round.join()

        device.follow(PublicParcelResponse(link = link("44444444444D", ParcelLinkRole.Viewer), parcel = pkg(second)), key = null, label = null)
        device.registerAlerts(token, NativePushLocale.En)
        device.forget(second)

        assertTrue(
            "Following it again and forgetting it in the same run leaves it on (the service still sends: ${service.alerts})",
            "44444444444D" !in service.alerts,
        )
    }

    /**
     * With the fix: when turning the left-over alert off fails during the round, the forgotten parcel is kept as one whose
     * notifications are still to be turned off, and the next refresh (stopForgottenAlerts) does it.
     */
    @Test
    fun aRemovalThatFailsDuringTheRoundIsTriedAgainOnTheNextRefresh() = runTest {
        val service = Service(held = "44444444444K")
        val second = UUID.randomUUID()
        val storage = Memory(listOf(entry("44444444444K", UUID.randomUUID(), viewer = true), entry("44444444444L", second, viewer = true)))
        service.alerts += setOf("44444444444K", "44444444444L")
        val device = device(service.client, storage, this)
        val round = launch { device.registerAlerts(token, NativePushLocale.En) }
        runCurrent()
        device.forget(second)
        service.removalsFailing = true
        service.gate.complete(Unit)
        round.join()
        service.removalsFailing = false

        runCatching { device(service.client, storage, this).refresh(all = true) }

        assertTrue("A removal that failed during the round is never tried again (the service still sends: ${service.alerts})", "44444444444L" !in service.alerts)
    }

    /** Control: a parcel of this phone's own forgotten during the round heals, its link and alerts going on the service. */
    @Test
    fun controlOwnParcelForgottenDuringTheRoundHearsNothingMore() = runTest {
        val service = Service(held = "44444444444E")
        val own = UUID.randomUUID()
        val storage = Memory(
            listOf(entry("44444444444E", UUID.randomUUID(), viewer = true), entry("44444444444F", own, viewer = false)),
            keys = mapOf("44444444444F" to "k".repeat(43)),
        )
        service.alerts += setOf("44444444444E", "44444444444F")
        val device = device(service.client, storage, this)
        val round = launch { device.registerAlerts(token, NativePushLocale.En) }
        runCurrent()
        device.forget(own)
        service.gate.complete(Unit)
        round.join()

        assertFalse("44444444444F" in service.alerts)
        assertEquals(setOf("44444444444E"), service.alerts)
    }

    /**
     * Case C: the owner's link replaces someone else's link to the same parcel while the round is under way. follow()
     * turns the old link's notifications off, the round signs it up again, and the next round signs the owner's link up
     * too: two alerts, so two pushes a scan, one of them never turned off.
     */
    @Test
    fun aReaderLinkReplacedByTheOwnersLinkDuringTheRoundHearsNothingMore() = runTest {
        val service = Service(held = "44444444444G")
        val parcel = UUID.randomUUID()
        val storage = Memory(listOf(entry("44444444444G", UUID.randomUUID(), viewer = true), entry("44444444444H", parcel, viewer = true)))
        service.alerts += setOf("44444444444G", "44444444444H")
        val device = device(service.client, storage, this)
        val round = launch { device.registerAlerts(token, NativePushLocale.En) }
        runCurrent()
        device.follow(PublicParcelResponse(link = link("44444444444J", ParcelLinkRole.Owner), parcel = pkg(parcel)), key = "k".repeat(43), label = null)
        runCurrent()
        assertTrue("precondition: follow() turned the reader link's notifications off", "44444444444H" !in service.alerts)
        service.gate.complete(Unit)
        round.join()

        assertTrue(
            "The reader link replaced during the round is signed up again and never turned off (the service still sends: ${service.alerts})",
            "44444444444H" !in service.alerts,
        )
    }
}
