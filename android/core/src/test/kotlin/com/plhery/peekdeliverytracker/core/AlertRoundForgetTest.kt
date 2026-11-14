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
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The launch's round of notifications (DeviceParcels.registerAlerts) works from the parcels listed when it began.
 * Someone else's parcel forgotten while the round goes on, before it reaches the parcel or while it signs the parcel up,
 * hears nothing more on this phone, as one forgotten after the round does.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AlertRoundForgetTest {
    private val token = "fcm-token"

    private class Memory(var parcels: List<DeviceParcel> = emptyList()) : DeviceParcelStorage {
        override fun load() = DeviceParcelStorage.Contents(parcels, emptyMap(), emptyMap())

        override fun save(parcels: List<DeviceParcel>, keys: Map<String, String>, pendingForgets: Map<String, String>) {
            this.parcels = parcels
        }
    }

    /** The service's side: which links send their pushes to this phone. The first sign-up of `held` waits for `gate`. */
    private class Service(val held: String? = null) {
        val alerts = mutableSetOf<String>()
        val gate = CompletableDeferred<Unit>()
        val client = DeviceParcelClient(
            lookup = { throw IOException("offline") },
            read = { _, _ -> throw IOException("offline") },
            forget = { _, _ -> },
            update = { _, _, _ -> throw IOException("offline") },
            detect = { _, _, _ -> throw IOException("offline") },
            setAlert = { linkId, _, _ ->
                if (linkId == held) gate.await()
                alerts += linkId
                DeviceAlertAnswer.On
            },
            removeAlert = { linkId, _ -> alerts -= linkId },
        )
    }

    private fun pkg(id: UUID) = PublicPackage(
        id = id, trackingNumber = "TESTPARCEL000222", label = "", carrier = CarrierId.Dhl, createdAt = "2026-11-01T08:00:00Z",
        syncStatus = SyncStatus.Ok, carrierData = PublicPackageCarrierData(), notificationsMuted = false,
        trackingEvents = listOf(
            TrackingEvent(id = UUID.randomUUID(), packageId = id, stage = TrackingStage.InTransit, description = "", occurredAt = "2026-11-02T08:00:00Z"),
        ),
    )

    private fun reader(id: String) = ParcelLink(
        id = id, role = ParcelLinkRole.Viewer, kind = ParcelLinkKind.Shared, createdAt = "2026-11-01T08:00:00Z", numberShown = false, canKeep = true,
    )

    /** Someone else's parcel whose notifications an earlier launch turned on. */
    private fun followed(linkId: String, id: UUID) = DeviceParcel(
        linkId = linkId, label = "", archivedAt = null, parcel = Parcel(shared = pkg(id)),
        checkedAt = Instant.parse("2026-11-12T08:00:00Z").timeIntervalSinceReferenceDate, viewer = true, alertToken = token,
    )

    private fun device(client: DeviceParcelClient, storage: DeviceParcelStorage, scope: CoroutineScope) = DeviceParcels(
        client, storage, PostcodeMemory(MemoryDefaults()), scope, region = { Locale.forLanguageTag("fr-CH") }, language = { AppLanguage.En },
    )

    @Test
    fun aParcelForgottenWhileTheLaunchRoundSignsUpAnotherHearsNothingMore() = runTest {
        val first = UUID.randomUUID()
        val second = UUID.randomUUID()
        val service = Service(held = "33333333333A").apply { alerts += setOf("33333333333A", "33333333333B") }
        val device = device(service.client, Memory(listOf(followed("33333333333A", first), followed("33333333333B", second))), this)

        // The launch's round signs every parcel on its way up again; the first one's answer is slow to come.
        val round = launch { device.registerAlerts(token, NativePushLocale.En) }
        runCurrent()
        device.forget(second)
        assertTrue("forget() turned the second parcel's notifications off", "33333333333B" !in service.alerts)
        service.gate.complete(Unit)
        round.join()

        assertEquals(listOf(first), device.list().map { it.id })
        assertTrue(
            "Someone else's parcel forgotten while the launch's notification round was under way is signed up for " +
                "notifications again by that round, and nothing on the phone turns them off (the service still sends: ${service.alerts})",
            "33333333333B" !in service.alerts,
        )
    }

    @Test
    fun aParcelForgottenWhileItsFirstSignUpIsInFlightHearsNothingMore() = runTest {
        val id = UUID.randomUUID()
        val service = Service(held = "33333333333C")
        val device = device(service.client, Memory(), this)
        device.follow(PublicParcelResponse(link = reader("33333333333C"), parcel = pkg(id)), key = null, label = null)

        // Following asks for its notifications; the user forgets it before the service answers.
        val round = launch { device.registerAlerts(token, NativePushLocale.En) }
        runCurrent()
        device.forget(id)
        service.gate.complete(Unit)
        round.join()

        assertTrue(device.list().isEmpty())
        assertTrue(
            "Someone else's parcel forgotten while its notifications were being turned on keeps sending them, and " +
                "nothing on the phone turns them off (the service still sends: ${service.alerts})",
            "33333333333C" !in service.alerts,
        )
    }

    /** Control: forgotten once the round is over, its notifications stop. */
    @Test
    fun controlAParcelForgottenAfterTheRoundHearsNothingMore() = runTest {
        val first = UUID.randomUUID()
        val second = UUID.randomUUID()
        val service = Service().apply { alerts += setOf("33333333333A", "33333333333B") }
        val device = device(service.client, Memory(listOf(followed("33333333333A", first), followed("33333333333B", second))), this)

        device.registerAlerts(token, NativePushLocale.En)
        device.forget(second)

        assertEquals(setOf("33333333333A"), service.alerts)
    }
}
