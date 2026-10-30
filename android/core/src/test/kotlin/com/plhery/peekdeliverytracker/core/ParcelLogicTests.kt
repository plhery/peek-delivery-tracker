package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.widgetParcel
import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeeded
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeededField
import com.plhery.peekdeliverytracker.core.api.CarrierInputSuggested
import com.plhery.peekdeliverytracker.core.api.CarrierInputSuggestedField
import com.plhery.peekdeliverytracker.core.api.CarrierPostcodeRefused
import com.plhery.peekdeliverytracker.core.api.ChangePackageCarrierRequest
import com.plhery.peekdeliverytracker.core.api.CreatePackageRequest
import com.plhery.peekdeliverytracker.core.api.ErrorResponse
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendsSnapshot
import com.plhery.peekdeliverytracker.core.api.LikelyCarrier
import com.plhery.peekdeliverytracker.core.api.LiveActivityUpdateTokenRequest
import com.plhery.peekdeliverytracker.core.api.NativePushDeviceRequest
import com.plhery.peekdeliverytracker.core.api.NativePushEnvironment
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.RecipientAction
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Locale
import java.util.UUID
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** ParcelLogicTests.swift: a parcel's state, history and attention, and the list's order. */
class ParcelLogicTests {
    @Test
    fun pickupPointSplitsNameFromAddressAndOpensAppleMaps() {
        val shop = PickupPoint(" Corner shop & café \n12 Main Street\n\n1000 Town ")
        assertEquals("Corner shop & café", shop?.name)
        assertEquals("12 Main Street, 1000 Town", shop?.address)
        assertEquals("Corner shop & café, 12 Main Street, 1000 Town", shop?.query)
        assertEquals(
            "https://maps.apple.com/?daddr=Corner%20shop%20%26%20caf%C3%A9,%2012%20Main%20Street,%201000%20Town",
            shop?.mapsUrl,
        )
        val office = PickupPoint("Post office 42")
        assertNull(office?.address)
        assertEquals("https://maps.apple.com/?q=Post%20office%2042", office?.mapsUrl)
        assertNull(PickupPoint(null))
        assertNull(PickupPoint(" \n "))
    }

    @Test
    fun automaticCarrierNoticeExpiresAndDoesNotSurviveManualSelection() {
        val at = DateParser.date("2026-09-10T12:00:00Z")!!
        var parcel = DemoRepository.seed(at)[0].copy(
            carrier = CarrierId.Ups,
            carrierData = CarrierData(autoChangedFrom = CarrierId.Dhl, autoChangedTo = CarrierId.Ups, autoChangedAt = DateParser.isoString(at)),
        )
        assertEquals(CarrierId.Dhl, parcel.automaticallyChangedFrom(at))
        assertEquals(CarrierId.Dhl, parcel.automaticallyChangedFrom(at.plusSeconds(43_199)))
        assertNull(parcel.automaticallyChangedFrom(at.plusSeconds(43_200)))
        assertNull(parcel.automaticallyChangedFrom(at.minusSeconds(1)))
        parcel = parcel.copy(carrier = CarrierId.Fedex)
        assertNull(parcel.automaticallyChangedFrom(at))
    }

    @Test
    fun automaticCarrierNoticeIgnoresParcelsFiledWithoutCarrier() {
        val at = DateParser.date("2026-09-10T12:00:00Z")!!
        var parcel = DemoRepository.seed(at)[0].copy(carrier = CarrierId.Ups)
        for (from in listOf(CarrierId.Unknown, CarrierId.InternationalPost)) {
            parcel = parcel.copy(carrierData = CarrierData(autoChangedFrom = from, autoChangedTo = CarrierId.Ups, autoChangedAt = DateParser.isoString(at)))
            assertNull(parcel.automaticallyChangedFrom(at))
        }
    }

    @Test
    fun sharedDemoCatalogHasVariedHistoriesAndRelativeDates() {
        val now = DateParser.date("2026-09-09T12:00:00Z")!!
        val parcels = DemoRepository.seed(now)
        assertEquals(17, parcels.size)
        assertEquals(17, parcels.map { it.trackingNumber }.toSet().size)
        assertEquals(7, parcels.count { it.isActive })
        assertEquals(10, parcels.count { it.isDelivered })
        assertEquals(5, parcels.count { it.isArchived })
        assertEquals("New trainers 👟", ParcelOrganizer.nextDelivery(parcels, now)?.label)
        assertTrue(parcels.any { it.expectedDelivery != null })
        assertTrue(parcels.any { it.currentStage == TrackingStage.InTransit && it.expectedDelivery == null })
        for (parcel in parcels) {
            assertTrue(CarrierId.entries.contains(parcel.carrier))
            val dates = parcel.trackingEvents.mapNotNull { DateParser.date(it.occurredAt) }
            assertEquals(parcel.trackingEvents.size, dates.size)
            assertEquals(dates.sorted(), dates)
            assertTrue(dates.all { it <= now })
            assertTrue(parcel.trackingEvents.all { it.packageId == parcel.id })
        }
    }

    @Test
    fun demoUpgradePreservesEditsAndOnlyAddsExamplesOnce() {
        val defaults = MemoryDefaults()
        val coffee = DemoRepository.seed().first { it.trackingNumber == "993412345678901234" }
            .copy(label = "My edited coffee", archivedAt = DateParser.isoString(java.time.Instant.now()))
        defaults.set(JsonEncoder.deliveryTracker.encode(ListSerializer(Parcel.serializer()), listOf(coffee)), forKey = "sdt.native.demo.parcels.v1")
        val repo = DemoRepository(defaults, language = { AppLanguage.En })
        val upgraded = repo.list()
        assertEquals(17, upgraded.size)
        assertEquals("My edited coffee", upgraded.first { it.id == coffee.id }.label)
        assertTrue(upgraded.first { it.id == coffee.id }.isArchived)
        val lamp = upgraded.first { it.label == "Moon lamp \uD83C\uDF19" }
        repo.permanentlyDelete(lamp.id)
        // Swift's `DemoRepository(defaults:)` speaks `AppLanguage.current`; the count is the same in every language.
        assertEquals(16, DemoRepository(defaults, language = { AppLanguage.En }).list().size)
        defaults.set(JsonEncoder.deliveryTracker.encode(ListSerializer(Parcel.serializer()), emptyList()), forKey = "sdt.native.demo.parcels.v1")
        defaults.removeObject(forKey = "sdt.native.demo.catalog.v2")
        assertTrue(DemoRepository(defaults, language = { AppLanguage.En }).list().isEmpty())
    }

    @Test
    fun demoIsWrittenInTheAppLanguageAndKeepsEditedNames() {
        val defaults = MemoryDefaults()
        var language = AppLanguage.En
        val repo = DemoRepository(defaults, language = { language })
        val coffee = repo.list().first { it.label == "Coffee beans \u2615" }
        repo.rename(coffee.id, "My own coffee")

        language = AppLanguage.Fr
        val french = repo.list()
        assertEquals("My own coffee", french.first { it.id == coffee.id }.label)
        val sneakers = french.first { it.trackingNumber == "1234567899" }
        assertEquals("Nouvelles baskets \uD83D\uDC5F", sneakers.label)
        assertEquals("Kiosk im Hauptbahnhof", sneakers.pickupPlace)
        assertTrue(sneakers.trackingEvents.any { it.description == "Personne à la maison. Sans doute parti courir." })
        assertEquals("T\u2019attend au point de retrait", sneakers.lastStatusText)
        // A refresh adds its scan in the same language.
        val collected = repo.refresh(sneakers.id)
        assertEquals("Livré dans ta boîte aux lettres", collected.currentEvent?.description)
        assertEquals("Domicile", collected.currentEvent?.location)
        assertNull(collected.pickupPlace)

        language = AppLanguage.De
        val german = repo.list()
        assertEquals("Neue Sneaker \uD83D\uDC5F", german.first { it.id == sneakers.id }.label)
        assertEquals("In deinen Briefkasten zugestellt", german.first { it.id == sneakers.id }.currentEvent?.description)
        assertEquals("My own coffee", german.first { it.id == coffee.id }.label)
        // Another launch, and a reset, keep the chosen language.
        val later = DemoRepository(defaults, language = { AppLanguage.De })
        assertEquals("Neue Sneaker \uD83D\uDC5F", later.list().first { it.id == sneakers.id }.label)
        later.reset()
        assertTrue(later.list().any { it.label == "Kaffeebohnen \u2615" })

        language = AppLanguage.En
        assertEquals(DemoRepository.seed().map { it.label }.toSet(), repo.list().map { it.label }.toSet())
    }

    @Test
    fun arrivalTiltFiltersJitterAndBoundsLargeMovements() {
        var tilt = ArrivalTilt().follow(roll = 0.44, pitch = -0.44)
        assertEquals(0.22, tilt.x, 0.0001)
        assertEquals(-0.22, tilt.y, 0.0001)
        repeat(100) { tilt = tilt.follow(roll = 50.0, pitch = -50.0) }
        assertTrue(tilt.x <= 1)
        assertTrue(tilt.y >= -1)
        assertEquals(tilt, tilt.follow(roll = Double.NaN, pitch = Double.POSITIVE_INFINITY))
    }

    @Test
    fun arrivalTiltFollowsScreenOrientationAndSettlesAtRest() {
        val portrait = ArrivalTilt().follow(roll = 0.44, pitch = 0.0)
        var landscape = ArrivalTilt().follow(roll = 0.44, pitch = 0.0, quarterTurns = 1)
        assertEquals(0.0, landscape.x, 0.0001)
        assertEquals(-portrait.x, landscape.y, 0.0001)
        repeat(40) { landscape = landscape.follow(roll = 0.0, pitch = 0.0) }
        assertEquals(0.0, landscape.y, 0.0001)
    }

    @Test
    fun arrivalTiltRespondsGentlyToAccelerationAndRejectsInvalidSamples() {
        var tilt = ArrivalTilt().follow(roll = 0.0, pitch = 0.0, accelerationX = 0.35, accelerationY = 0.35)
        assertTrue(tilt.x > 0)
        assertTrue(tilt.y < 0)
        assertTrue(Math.abs(tilt.x) + Math.abs(tilt.y) < 0.1)
        assertEquals(tilt, tilt.follow(roll = 0.0, pitch = 0.0, accelerationX = Double.NaN))
        repeat(60) { tilt = tilt.follow(roll = 0.0, pitch = 0.0) }
        assertEquals(0.0, tilt.x, 0.0001)
        assertEquals(0.0, tilt.y, 0.0001)
    }

    @Test
    fun archiveReleaseKeepsTheFingerPositionUntilTheSettleAnimation() {
        var swipe = ArchiveSwipeState()
        // The first 8 points only pick the direction, so the card starts without a jump.
        swipe = swipe.drag(Size(-60.0, 3.0), width = 360.0)
        assertEquals(52.0, swipe.reveal, 0.001)

        val released = swipe.release(velocity = -300.0, width = 360.0)
        assertEquals(ArchiveSwipeState.Destination.Revealed, released.destination)
        swipe = released.state
        // Releasing used to reset the displayed translation to zero for a frame.
        assertEquals(52.0, swipe.reveal, 0.001)
        assertNull(swipe.cancel().destination)
        swipe = swipe.settle(at = 88.0)

        // Past the action the card resists the finger.
        swipe = swipe.drag(Size(-12.0, 0.0), width = 360.0)
        assertEquals(91.2, swipe.reveal, 0.001)
    }

    @Test
    fun archiveSwipeThatStartsAtZeroFollowsTheFingerOneToOne() {
        // iOS 27 reports a recognized pan at zero before it moves.
        var swipe = ArchiveSwipeState()
        swipe = swipe.drag(Size.Zero, width = 360.0)
        swipe = swipe.drag(Size(-15.5, -2.0), width = 360.0)
        assertEquals(15.5, swipe.reveal, 0.001)
        swipe = swipe.drag(Size(-48.0, -5.0), width = 360.0)
        assertEquals(48.0, swipe.reveal, 0.001)
        val released = swipe.release(velocity = -600.0, width = 360.0)
        assertEquals(ArchiveSwipeState.Destination.Revealed, released.destination)
        swipe = released.state

        swipe = swipe.settle(at = 0.0)
        swipe = swipe.drag(Size.Zero, width = 360.0)
        swipe = swipe.drag(Size(-3.0, -18.0), width = 360.0)
        swipe = swipe.drag(Size(-120.0, -20.0), width = 360.0)
        assertEquals(0.0, swipe.reveal, 0.0)
        assertNull(swipe.release(velocity = -900.0, width = 360.0).destination)
    }

    @Test
    fun shortArchiveSwipeClosesFromTheReleasePosition() {
        val swipe = ArchiveSwipeState().drag(Size(-25.0, 0.0), width = 360.0)
        val released = swipe.release(velocity = 0.0, width = 360.0)
        assertEquals(ArchiveSwipeState.Destination.Closed, released.destination)
        assertEquals(17.0, released.state.reveal, 0.001)
    }

    @Test
    fun reversingAnOpenArchiveSwipeClosesIt() {
        var swipe = ArchiveSwipeState().settle(at = 88.0)
        swipe = swipe.drag(Size(65.0, 2.0), width = 360.0)
        assertEquals(31.0, swipe.reveal, 0.001)
        val released = swipe.release(velocity = 400.0, width = 360.0)
        assertEquals(ArchiveSwipeState.Destination.Closed, released.destination)
        assertEquals(31.0, released.state.reveal, 0.001)
    }

    @Test
    fun archiveNeedsTheCommitPointOrAThrowPastTheAction() {
        var swipe = ArchiveSwipeState().drag(Size(-30.0, 0.0), width = 360.0)
        var released = swipe.release(velocity = -2000.0, width = 360.0)
        assertEquals(ArchiveSwipeState.Destination.Revealed, released.destination)

        swipe = released.state.settle(at = 0.0).drag(Size(-120.0, 1.0), width = 360.0)
        released = swipe.release(velocity = -500.0, width = 360.0)
        assertEquals(ArchiveSwipeState.Destination.Revealed, released.destination)
        swipe = released.state.settle(at = 0.0).drag(Size(-120.0, 1.0), width = 360.0)
        released = swipe.release(velocity = -1500.0, width = 360.0)
        assertEquals(ArchiveSwipeState.Destination.Archive, released.destination)

        swipe = released.state.settle(at = 0.0).drag(Size(-230.0, 1.0), width = 360.0)
        assertTrue(swipe.reveal >= ArchiveSwipeState.commitPoint(width = 360.0))
        assertEquals(ArchiveSwipeState.Destination.Archive, swipe.release(velocity = 0.0, width = 360.0).destination)
    }

    @Test
    fun verticalScrollDoesNotBecomeAnArchiveSwipe() {
        var swipe = ArchiveSwipeState()
        swipe = swipe.drag(Size(-3.0, -18.0), width = 360.0)
        swipe = swipe.drag(Size(-200.0, -25.0), width = 360.0)
        assertEquals(0.0, swipe.reveal, 0.0)
        assertNull(swipe.release(velocity = -900.0, width = 360.0).destination)
    }

    @Test
    fun cancelledSwipeSettlesWithoutArchivingOrResettingItsPosition() {
        val swipe = ArchiveSwipeState().drag(Size(-220.0, 0.0), width = 360.0)
        val reveal = swipe.reveal
        val cancelled = swipe.cancel()
        assertEquals(ArchiveSwipeState.Destination.Revealed, cancelled.destination)
        assertEquals(reveal, cancelled.state.reveal, 0.0)
        assertNull(cancelled.state.cancel().destination)
    }

    @Test
    fun archiveSwipeStaysWithinCardBounds() {
        var swipe = ArchiveSwipeState()
        swipe = swipe.drag(Size(50.0, 0.0), width = 360.0)
        assertTrue(swipe.reveal < 0)
        assertTrue(swipe.reveal > -44)
        swipe = swipe.drag(Size(-500.0, 0.0), width = 360.0)
        assertTrue(swipe.reveal < 360)
    }

    @Test
    fun archiveSwipeTravelIsContinuousAndInvertible() {
        val knee = 88 + (ArchiveSwipeState.commitPoint(width = 360.0) - 88) / 0.8
        for (travel in listOf(-120.0, -1.0, 0.0, 30.0, 88.0, 120.0, knee - 1, knee + 1, 260.0, 400.0)) {
            val reveal = ArchiveSwipeState.reveal(forTravel = travel, width = 360.0)
            assertEquals(travel, ArchiveSwipeState.travel(forReveal = reveal, width = 360.0), 0.0001)
        }
        for (edge in listOf(0.0, 88.0, knee)) {
            val step = ArchiveSwipeState.reveal(forTravel = edge + 0.0001, width = 360.0) -
                ArchiveSwipeState.reveal(forTravel = edge - 0.0001, width = 360.0)
            assertTrue(step < 0.001)
        }
        assertEquals(180.0, ArchiveSwipeState.commitPoint(width = 360.0), 0.0)
        assertEquals(144.0, ArchiveSwipeState.commitPoint(width = 200.0), 0.0)
    }

    @Test
    fun swipeSpringStartsWithItsReleaseSpeedAndSettlesOnTarget() {
        val start = SwipeSpring.fling.state(from = 40.0, to = 88.0, velocity = 600.0, at = 0.0)
        assertEquals(40.0, start.value, 0.0001)
        assertEquals(600.0, start.velocity, 0.0001)
        val rest = SwipeSpring.snap.state(from = 40.0, to = 88.0, velocity = 600.0, at = 1.5)
        assertEquals(88.0, rest.value, 0.01)
        val samples = (0 until 120).map { SwipeSpring.snap.state(from = 0.0, to = 100.0, velocity = 0.0, at = it / 120.0).value }
        assertTrue((samples.maxOrNull() ?: 0.0) <= 100)
        val settle = SwipeSpring.snap.settleTime(from = 0.0, to = 100.0, velocity = 0.0)
        assertTrue(settle > 0.25)
        assertTrue(settle < 0.6)
    }

    @Test
    fun archiveSwipeMotionHandsOverItsPositionAndSpeed() {
        val began = 0.0
        val motion = ArchiveSwipeMotion(
            start = ArchiveSwipePose(reveal = 150.0), target = ArchiveSwipePose(reveal = 360.0, spread = 1.0, land = 1.0),
            velocity = ArchiveSwipePose(reveal = 900.0), spring = SwipeSpring.exit, reduceMotion = false, began = began,
        )
        assertEquals(150.0, motion.pose(at = began).reveal, 0.001)
        assertEquals(900.0, motion.velocity(at = began).reveal, 0.001)
        val midway = began + 0.1
        assertTrue(motion.pose(at = midway).reveal > 150)
        assertTrue(motion.pose(at = midway).land > 0)
        assertEquals(motion.target, motion.pose(at = began + motion.duration + 0.1))
        val leave = motion.time { it.reveal >= 358 && it.land >= 0.92 }
        assertTrue(leave > 0.1)
        assertTrue(leave < motion.duration)

        // A throw from beside the action: the gap to the card only closes on the way out.
        val exit = ArchiveSwipeMotion(
            start = ArchiveSwipePose(reveal = 106.0), target = ArchiveSwipePose(reveal = 370.0, spread = 1.0, land = 1.0),
            velocity = ArchiveSwipePose(reveal = 2000.0), spring = SwipeSpring.exit, reduceMotion = false, glued = true,
            began = began,
        )
        var gap = 18.001
        for (step in 0..60) {
            val pose = exit.pose(at = began + step / 120.0)
            val next = (1 - pose.spread) * maxOf(0.0, minOf(pose.reveal, 370.0) - 88)
            assertTrue(next <= gap + 0.001)
            gap = next
        }

        val still = ArchiveSwipeMotion(
            start = ArchiveSwipePose(reveal = 150.0), target = ArchiveSwipePose(),
            velocity = ArchiveSwipePose(reveal = 900.0), spring = SwipeSpring.fling, reduceMotion = true, began = began,
        )
        assertEquals(ArchiveSwipePose(), still.velocity)
        assertEquals(SwipeSpring.reduced, still.revealSpring)
    }

    @Test
    fun internationalPostWaitsForItsAutomaticLookup() {
        val id = UUID.randomUUID()
        val parcel = makeParcel(id, events = emptyList()).copy(carrier = CarrierId.InternationalPost, syncStatus = SyncStatus.Pending)
        assertEquals("status.syncing", parcel.displayStatus.key)
        assertTrue(parcel.displayStatus.syncing)
    }

    @Test
    fun retryAfterSupportsBothHeaderFormats() {
        val now = DateParser.date("2026-09-06T12:00:00Z")!!
        assertEquals(12.0, DeliveryApiClient.retryAfterSeconds("12", now = now), 0.0)
        assertEquals(12.0, DeliveryApiClient.retryAfterSeconds("Sun, 06 Sep 2026 12:00:12 GMT", now = now), 0.0)
        assertEquals(0.0, DeliveryApiClient.retryAfterSeconds("invalid", now = now), 0.0)
        assertEquals(0.0, DeliveryApiClient.retryAfterSeconds(null, now = now), 0.0)
    }

    @Test
    fun pickupAndDeliveryActionsSurviveSyncErrors() {
        val id = UUID.randomUUID()
        for ((stage, attention) in listOf(
            TrackingStage.ReadyForPickup to ParcelAttention.ReadyForPickup,
            TrackingStage.FailedAttempt to ParcelAttention.FailedAttempt,
            TrackingStage.Customs to ParcelAttention.Customs,
            TrackingStage.Exception to ParcelAttention.Exception,
        )) {
            val parcel = makeParcel(id, events = listOf(event(id, stage, "2026-09-06T10:00:00Z"))).copy(syncStatus = SyncStatus.Error)
            assertEquals(attention, parcel.attention())
            assertEquals(stage, parcel.currentStage)
        }
    }

    @Test
    fun customsRequestToTheRecipientNeedsActionWhileItIsTheNewestScan() {
        val now = DateParser.date("2026-08-09T12:00:00Z")!!
        val id = UUID.randomUUID()
        // As the server serves it (parcelPriority.test.ts).
        val held = ApiJson.decodeFromString(
            TrackingEvent.serializer(),
            """
                {"id":"${UUID.randomUUID()}","package_id":"$id","stage":"customs","description":"Duties or taxes are due on this package",
                 "occurred_at":"2026-08-09T08:00:00Z","recipient_action":"duties"}
            """.trimIndent(),
        )
        assertEquals(RecipientAction.Duties, held.recipientAction)
        var parcel = makeParcel(id = id, events = listOf(event(id, TrackingStage.InTransit, "2026-08-08T10:00:00Z"), held))
        assertEquals(ParcelAttention.CustomsDuties, parcel.attention(now))
        assertEquals("attention.customs_duties", parcel.notice(now)?.localizationKey)
        assertNull(ParcelOrganizer.nextDelivery(listOf(parcel), now))
        parcel = parcel.copy(syncStatus = SyncStatus.Error)
        assertEquals(ParcelAttention.CustomsDuties, parcel.attention(now))
        // Each request reads as the web's label.
        for ((action, attention) in listOf(
            RecipientAction.TaxId to ParcelAttention.CustomsTaxId, RecipientAction.Duties to ParcelAttention.CustomsDuties,
            RecipientAction.Documents to ParcelAttention.CustomsDocuments,
        )) {
            assertEquals(attention, ParcelAttention(action))
            assertEquals("attention.customs_${action.rawValue}", attention.localizationKey)
        }
        // Paid: the carrier moved on.
        val released = makeParcel(id = id, events = listOf(held, event(id, TrackingStage.InTransit, "2026-08-09T10:00:00Z")))
        assertNull(released.attention(now))
    }

    @Test
    fun senderMetadataDecodesWithoutRequiringItOnOlderResponses() {
        val sender = ApiJson.decodeFromString(CarrierData.serializer(), """{"sender_name":"Example sender"}""")
        assertEquals("Example sender", sender.senderName)
        val older = ApiJson.decodeFromString(CarrierData.serializer(), "{}")
        assertNull(older.senderName)
    }

    @Test
    fun decodesRoutingInputNeededAndPromptsOnlyWhileAnotherCarrierWaits() {
        val packageId = UUID.randomUUID()
        val json = """
            {
              "packages": [{
                "id": "${packageId.uuidString}",
                "tracking_number": "12345678901",
                "label": "Test parcel",
                "carrier": "gls-de",
                "created_at": "2026-08-01T10:00:00Z",
                "sync_status": "ok",
                "carrier_data": { "routing": { "input_needed": { "carrier": "gls-ch", "field": "dpdPostcode" } } },
                "notifications_muted": false,
                "tracking_events": [{
                  "id": "${UUID.randomUUID().uuidString}",
                  "package_id": "${packageId.uuidString}",
                  "stage": "in_transit",
                  "description": "In transit",
                  "location": null,
                  "occurred_at": "2026-08-09T10:00:00Z"
                }]
              }]
            }
        """.trimIndent()
        val parcel = ApiJson.decodeFromString(PackageListResponse.serializer(), json).packages.first()
        assertEquals(CarrierInputNeeded(CarrierId.GlsCh, CarrierInputNeededField.DpdPostcode), parcel.inputNeeded)
        assertEquals(CarrierId.GlsCh, parcel.inputNeededPrompt?.carrier)

        // Its own carrier asks only while the parcel has no postcode and the sync error isn't already asking.
        val sameCarrier = parcel.copy(carrier = CarrierId.GlsCh)
        assertEquals(CarrierId.GlsCh, sameCarrier.inputNeededPrompt?.carrier)
        val withPostcode = sameCarrier.copy(dpdPostcode = "8000")
        assertNull(withPostcode.inputNeededPrompt)
        val errorAsks = sameCarrier.copy(syncError = "carrier:input_required")
        assertNull(errorAsks.inputNeededPrompt)
        // The postcode its own carrier refused is the owner's to check; another carrier's refusal is not.
        assertNull(withPostcode.refusedPostcode)
        var refused = withPostcode.copy(
            carrierData = withPostcode.carrierData?.copy(
                routing = withPostcode.carrierData.routing?.copy(postcodeRefused = CarrierPostcodeRefused(CarrierId.GlsCh)),
            ),
        )
        assertEquals("8000", refused.refusedPostcode)
        refused = refused.copy(carrier = CarrierId.Dpd)
        assertNull(refused.refusedPostcode)
        val archived = parcel.copy(archivedAt = "2026-08-10T10:00:00Z")
        assertNull(archived.inputNeededPrompt)
        for (stage in listOf(TrackingStage.Delivered, TrackingStage.Returned)) {
            val finished = parcel.copy(trackingEvents = parcel.trackingEvents + event(packageId, stage, "2026-08-10T10:00:00Z"))
            assertNull(stage.rawValue, finished.inputNeededPrompt)
        }
        val trackingUrlNeeded = ApiJson.decodeFromString(CarrierInputNeeded.serializer(), """{"carrier":"gls-ch","field":"trackingUrl"}""")
        assertEquals(CarrierInputNeededField.TrackingUrl, trackingUrlNeeded.field)
        val link = parcel.copy(
            carrierData = parcel.carrierData?.copy(routing = parcel.carrierData.routing?.copy(inputNeeded = trackingUrlNeeded)),
        )
        assertNull(link.inputNeededPrompt)

        val older = ApiJson.decodeFromString(CarrierData.serializer(), """{"sender_name":"Example sender"}""")
        assertNull(older.routing)
        val untouched = parcel.copy(carrierData = older)
        assertNull(untouched.inputNeeded)
        assertNull(untouched.inputNeededPrompt)
    }

    @Test
    fun promptsForASuggestedCarrierOnlyWhileNobodyAnsweredForTheNumber() {
        val packageId = UUID.randomUUID()
        val json = """
            {
              "packages": [{
                "id": "${packageId.uuidString}",
                "tracking_number": "12345678",
                "label": "Test parcel",
                "carrier": "unknown",
                "created_at": "2026-08-01T10:00:00Z",
                "sync_status": "waiting",
                "carrier_data": { "routing": { "input_suggested": { "carrier": "mondial-relay", "field": "dpdPostcode" } } },
                "notifications_muted": false,
                "tracking_events": []
              }]
            }
        """.trimIndent()
        val parcel = ApiJson.decodeFromString(PackageListResponse.serializer(), json).packages.first()
        assertEquals(CarrierInputSuggested(CarrierId.MondialRelay, CarrierInputSuggestedField.DpdPostcode), parcel.inputSuggestedPrompt)
        assertNull(parcel.inputNeededPrompt)

        // Filed under a carrier, archived, with a scan, or once a carrier asks for the postcode itself: nothing suggested.
        val filed = parcel.copy(carrier = CarrierId.MondialRelay)
        assertNull(filed.inputSuggestedPrompt)
        val archived = parcel.copy(archivedAt = "2026-08-10T10:00:00Z")
        assertNull(archived.inputSuggestedPrompt)
        val scanned = parcel.copy(trackingEvents = parcel.trackingEvents + event(packageId, TrackingStage.InTransit, "2026-08-10T10:00:00Z"))
        assertNull(scanned.inputSuggestedPrompt)
        val needed = parcel.copy(
            carrierData = parcel.carrierData?.copy(
                routing = parcel.carrierData.routing?.copy(inputNeeded = CarrierInputNeeded(CarrierId.GlsCh, CarrierInputNeededField.DpdPostcode)),
            ),
        )
        assertNull(needed.inputSuggestedPrompt)
        assertEquals(CarrierId.GlsCh, needed.inputNeededPrompt?.carrier)
    }

    @Test
    fun asksForThePostcodeACheckedParcelsCarrierShowsMoreWith() {
        val catalog = CarrierCatalog(cacheFile = null)
        val json = """
            {
              "packages": [{
                "id": "${UUID.randomUUID().uuidString}",
                "tracking_number": "06080000000002",
                "label": "Test parcel",
                "carrier": "dpd",
                "created_at": "2026-08-01T10:00:00Z",
                "last_synced_at": "2026-08-02T10:00:00Z",
                "sync_status": "ok",
                "notifications_muted": false,
                "tracking_events": []
              }]
            }
        """.trimIndent()
        val parcel = ApiJson.decodeFromString(PackageListResponse.serializer(), json).packages.first()
        assertTrue(parcel.showsMoreWithPostcode(catalog))

        // Not before its first check, which gives it the account's own postcode, nor with one, nor once archived.
        assertFalse(parcel.copy(lastSyncedAt = null).showsMoreWithPostcode(catalog))
        assertFalse(parcel.copy(dpdPostcode = "8000").showsMoreWithPostcode(catalog))
        assertFalse(parcel.copy(archivedAt = "2026-08-10T10:00:00Z").showsMoreWithPostcode(catalog))
        // DHL needs it for some twelve-digit numbers only, and asks for those.
        assertFalse(parcel.copy(carrier = CarrierId.Dhl, trackingNumber = "000000000012").showsMoreWithPostcode(catalog))
    }

    @Test
    fun namesTheLikelyCarrierWhileNobodyAnsweredAndAsksToChooseAfterAWeek() {
        val packageId = UUID.randomUUID()
        val json = """
            {
              "packages": [{
                "id": "${packageId.uuidString}",
                "tracking_number": "EOB10293847561029384",
                "label": "Test parcel",
                "carrier": "unknown",
                "created_at": "2026-08-01T10:00:00Z",
                "sync_status": "waiting",
                "carrier_data": { "routing": { "likely_carrier": { "carrier": "brt", "since": "2026-08-01T10:00:00Z" } } },
                "notifications_muted": false,
                "tracking_events": []
              }]
            }
        """.trimIndent()
        val parcel = ApiJson.decodeFromString(PackageListResponse.serializer(), json).packages.first()
        val since = DateParser.date("2026-08-01T10:00:00Z")!!
        val early = parcel.likelyCarrierPrompt(since.plusSeconds(24 * 60 * 60))
        assertNotNull(early)
        assertEquals(CarrierId.Brt, early!!.carrier)
        assertFalse(early.waited)
        assertEquals(true, parcel.likelyCarrierPrompt(since.plusSeconds(Parcel.likelyCarrierWait.toLong()))?.waited)

        // Filed under a carrier, archived, with a scan, or once a postcode is asked for: no likely carrier.
        val filed = parcel.copy(carrier = CarrierId.Brt)
        assertNull(filed.likelyCarrierPrompt(since))
        val archived = parcel.copy(archivedAt = "2026-08-10T10:00:00Z")
        assertNull(archived.likelyCarrierPrompt(since))
        val scanned = parcel.copy(trackingEvents = parcel.trackingEvents + event(packageId, TrackingStage.InTransit, "2026-08-10T10:00:00Z"))
        assertNull(scanned.likelyCarrierPrompt(since))
        val suggested = parcel.copy(
            carrierData = parcel.carrierData?.copy(
                routing = parcel.carrierData.routing?.copy(
                    inputSuggested = CarrierInputSuggested(CarrierId.MondialRelay, CarrierInputSuggestedField.DpdPostcode),
                ),
            ),
        )
        assertNull(suggested.likelyCarrierPrompt(since))
    }

    @Test
    fun namesTheProbableCarrierWhereTheCarrierIsNamedUntilACarrierAnswers() {
        val packageId = UUID.randomUUID()
        // A synthetic number in DPD Switzerland's depot range, which no carrier has answered for yet.
        val json = """
            {
              "packages": [{
                "id": "${packageId.uuidString}",
                "tracking_number": "06080000000002",
                "label": "Test parcel",
                "carrier": "unknown",
                "created_at": "2026-10-28T09:00:00Z",
                "sync_status": "waiting",
                "carrier_data": { "routing": { "likely_carrier": { "carrier": "dpd", "since": "2026-10-28T10:00:00Z" } } },
                "notifications_muted": false,
                "tracking_events": []
              }]
            }
        """.trimIndent()
        val parcel = ApiJson.decodeFromString(PackageListResponse.serializer(), json).packages.first()
        val localizer = Localizer(AppLanguage.En)
        assertEquals(CarrierId.Dpd, parcel.likelyCarrier)
        assertEquals("status.likelyCarrier", parcel.displayStatus.key)
        assertEquals("Waiting for DPD Switzerland’s first scan", localizer.parcelStatus(parcel))
        val identity = CarrierVisualIdentity.shown(parcel, localizer = localizer)
        assertEquals("Probably DPD Switzerland", identity.name)
        assertEquals(CarrierVisualIdentity.of(CarrierId.Unknown, language = AppLanguage.En).decal, identity.decal)
        // Every language names the carrier in both lines.
        for (language in AppLanguage.entries) {
            localizer.language = language
            assertTrue("$language", localizer.parcelStatus(parcel).contains("DPD Switzerland"))
            assertTrue("$language", CarrierVisualIdentity.shown(parcel, localizer = localizer).name.contains("DPD Switzerland"))
        }
        localizer.language = AppLanguage.En

        // A shape nobody leads keeps the words of a number no carrier knows yet.
        val shared = parcel.copy(carrierData = null)
        assertNull(shared.likelyCarrier)
        assertEquals("status.noHistory", shared.displayStatus.key)
        assertEquals(
            CarrierVisualIdentity.of(CarrierId.Unknown, language = AppLanguage.En).name,
            CarrierVisualIdentity.shown(shared, localizer = localizer).name,
        )
        // Once another carrier answers for the number, the parcel is filed under it and the guess goes.
        val filed = parcel.copy(carrier = CarrierId.Seur)
        assertNull(filed.likelyCarrier)
        assertEquals("status.unannounced", filed.displayStatus.key)
        assertEquals("SEUR", CarrierVisualIdentity.shown(filed, localizer = localizer).name)
    }

    @Test
    fun tellsAnOrderNumberOrAShortNumberNoCarrierAnsweredFor() {
        // Synthetic numbers: Amazon's without hyphens, AliExpress's, Temu's as a lookup stores it, a short one.
        assertEquals(NonTrackingShape.Order(shop = "amazon"), CarrierCatalog.nonTrackingShape("30245719832294617"))
        assertEquals(NonTrackingShape.Order(shop = "aliexpress"), CarrierCatalog.nonTrackingShape("3041234567890123"))
        assertEquals(NonTrackingShape.Order(shop = "temu"), CarrierCatalog.nonTrackingShape("PO00012345678901234567"))
        assertEquals(NonTrackingShape.Short, CarrierCatalog.nonTrackingShape("10293"))
        assertNull(CarrierCatalog.nonTrackingShape("99945719832294617"))
        assertNull(CarrierCatalog.nonTrackingShape("12345678"))

        val packageId = UUID.randomUUID()
        val json = """
            {
              "packages": [{
                "id": "${packageId.uuidString}",
                "tracking_number": "30245719832294617",
                "label": "Test parcel",
                "carrier": "unknown",
                "created_at": "2026-08-01T10:00:00Z",
                "sync_status": "waiting",
                "notifications_muted": false,
                "tracking_events": []
              }]
            }
        """.trimIndent()
        val parcel = ApiJson.decodeFromString(PackageListResponse.serializer(), json).packages.first()
        assertEquals(NonTrackingShape.Order(shop = "amazon"), parcel.nonTrackingShape)
        // Filed under a carrier, or with a carrier its own format points to: nothing said.
        val filed = parcel.copy(carrier = CarrierId.Ups)
        assertNull(filed.nonTrackingShape)
        val likely = parcel.copy(
            carrierData = CarrierData(routing = CarrierDataRouting(likelyCarrier = LikelyCarrier(CarrierId.Brt, "2026-08-01T10:00:00Z"))),
        )
        assertNull(likely.nonTrackingShape)
    }

    @Test
    fun journalShowsAnEarlierCarriersCopyUnderTheScanItRepeats() {
        val packageId = UUID.randomUUID()
        val (scan, copy, other, orphan) = List(4) { UUID.randomUUID() }
        fun event(id: UUID, description: String, at: String, relayOf: UUID?): String = """
            {"id": "${id.uuidString}", "package_id": "${packageId.uuidString}", "stage": "in_transit",
             "description": "$description", "location": null, "occurred_at": "$at"${relayOf?.let { ", \"relay_of\": \"${it.uuidString}\"" } ?: ""}}
        """.trimIndent()
        val json = """
            {"packages": [{
              "id": "${packageId.uuidString}", "tracking_number": "RELAYTEST0001", "label": "Relayed", "carrier": "dhl",
              "created_at": "2026-09-01T10:00:00Z", "expected_delivery": null, "last_status_text": null, "last_synced_at": null,
              "sync_status": "ok", "sync_error": null, "tracking_url": null, "dpd_postcode": null, "archived_at": null,
              "notifications_muted": false,
              "tracking_events": [
                ${event(scan, "Arrival in destination country", "2026-09-09T14:16:51Z", null)},
                ${event(copy, "The shipment has arrived in the destination country", "2026-09-09T14:16:00Z", scan)},
                ${event(other, "Sorted for delivery", "2026-09-09T15:42:29Z", null)},
                ${event(orphan, "The shipment is being prepared for delivery", "2026-09-10T04:44:00Z", UUID.randomUUID())}
              ]
            }]}
        """.trimIndent()
        val parcel = ApiJson.decodeFromString(PackageListResponse.serializer(), json).packages.first()
        assertEquals(scan, parcel.trackingEvents.first { it.id == copy }.relayOf)
        // The copy is a line under its scan; one whose scan is not there keeps its row.
        assertEquals(listOf(orphan, other, scan), parcel.journalEntries.map { it.id })
        assertEquals(listOf(copy), parcel.journalEntries.last().relays.map { it.id })
        assertTrue(parcel.journalEntries.dropLast(1).all { it.relays.isEmpty() })
    }

    @Test
    fun currentEventIsTheScanTheJournalShowsNotTheLineUnderIt() {
        val packageId = UUID.randomUUID()
        val scan = event(packageId, TrackingStage.InTransit, "2026-09-09T14:16:00Z")
            .copy(id = UUID.fromString("00000000-0000-4000-8000-000000000001"))
        val line = event(packageId, TrackingStage.InTransit, "2026-09-09T14:16:00Z")
            .copy(id = UUID.fromString("ffffffff-0000-4000-8000-000000000001"), relayOf = scan.id)
        // The line would win the tie on its id, or is a few seconds newer: the scan it sits under is the parcel's step.
        assertEquals(scan.id, makeParcel(id = packageId, events = listOf(scan, line)).currentEvent?.id)
        val newer = line.copy(occurredAt = "2026-09-09T14:16:20Z")
        assertEquals(scan.id, makeParcel(id = packageId, events = listOf(scan, newer)).currentEvent?.id)
    }

    @Test
    fun generatedRequestModelsEncodeApiFieldNamesAndEnumValues() {
        val packageRequest = CreatePackageRequest(
            trackingNumber = "99.34.123456.12345678",
            carrier = CarrierId.SwissPost,
            trackingUrl = "https://service.post.ch/parcel/123",
        )
        val packageJson = encode(ApiJson.encodeToString(CreatePackageRequest.serializer(), packageRequest))

        assertEquals(packageRequest.trackingNumber, packageJson["trackingNumber"]?.jsonPrimitive?.content)
        assertEquals("swiss-post", packageJson["carrier"]?.jsonPrimitive?.content)
        assertEquals(packageRequest.trackingUrl, packageJson["trackingUrl"]?.jsonPrimitive?.content)
        assertNull(packageJson["trackingURL"])

        val carrierRequest = ChangePackageCarrierRequest(carrier = CarrierId.MondialRelay, dpdPostcode = "59650")
        val carrierJson = encode(ApiJson.encodeToString(ChangePackageCarrierRequest.serializer(), carrierRequest))
        assertEquals("mondial-relay", carrierJson["carrier"]?.jsonPrimitive?.content)
        assertEquals("59650", carrierJson["dpdPostcode"]?.jsonPrimitive?.content)

        val pushRequest = NativePushDeviceRequest(
            token = "device-token",
            environment = NativePushEnvironment.Production,
            locale = NativePushLocale.Fr,
            deviceName = "iPhone",
            sendTest = true,
        )
        val pushJson = encode(ApiJson.encodeToString(NativePushDeviceRequest.serializer(), pushRequest))

        assertEquals("production", pushJson["environment"]?.jsonPrimitive?.content)
        assertEquals("fr", pushJson["locale"]?.jsonPrimitive?.content)
        assertEquals(true, pushJson["sendTest"]?.jsonPrimitive?.boolean)

        val installationId = UUID.randomUUID()
        val parcelId = UUID.randomUUID()
        val liveRequest = LiveActivityUpdateTokenRequest(
            installationId = installationId,
            activityId = "activity-1",
            parcelId = parcelId,
            token = "ab".repeat(32),
            environment = NativePushEnvironment.Development,
            locale = NativePushLocale.De,
        )
        val liveJson = encode(ApiJson.encodeToString(LiveActivityUpdateTokenRequest.serializer(), liveRequest))
        // The contract writes UUIDs in lower case, where Foundation writes them in upper case.
        assertEquals(installationId.uuidString, liveJson["installationId"]?.jsonPrimitive?.content?.uppercase())
        assertEquals(parcelId.uuidString, liveJson["parcelId"]?.jsonPrimitive?.content?.uppercase())
        assertEquals("activity-1", liveJson["activityId"]?.jsonPrimitive?.content)
    }

    private fun encode(json: String): JsonObject = ApiJson.parseToJsonElement(json).jsonObject

    @Test
    fun duplicateErrorDecodesItsExistingParcelLink() {
        val packageId = UUID.randomUUID()
        val error = ApiJson.decodeFromString(
            ErrorResponse.serializer(),
            """
            {
              "error": "This tracking number is already in your delivery box",
              "packageId": "${packageId.uuidString}"
            }
            """.trimIndent(),
        )

        assertEquals(packageId, error.packageId)
    }

    @Test
    fun futureCarrierIdentifierDecodesAndEncodesWithoutAnAppRelease() {
        val packageId = UUID.randomUUID()
        val response = ApiJson.decodeFromString(
            PackageListResponse.serializer(),
            """
            {
              "packages": [{
                "id": "${packageId.uuidString}",
                "tracking_number": "FX12345678",
                "label": "Future parcel",
                "carrier": "future-express",
                "created_at": "2026-09-01T09:00:00Z",
                "sync_status": "waiting",
                "notifications_muted": false,
                "tracking_events": []
              }]
            }
            """.trimIndent(),
        )
        val futureCarrier = response.packages.first().carrier
        assertEquals("future-express", futureCarrier.rawValue)
        assertFalse(CarrierId.entries.contains(futureCarrier))

        val request = CreatePackageRequest(trackingNumber = "FX12345678", carrier = futureCarrier)
        val json = encode(ApiJson.encodeToString(CreatePackageRequest.serializer(), request))
        assertEquals("future-express", json["carrier"]?.jsonPrimitive?.content)
    }

    @Test
    fun widgetPrioritizesOutForDeliveryThenKeepsNextUp() {
        val nextUp = widgetParcel(label = "Next up", outForDelivery = false)
        val outForDelivery = widgetParcel(label = "Courier", outForDelivery = true)
        val later = widgetParcel(label = "Later", outForDelivery = false)
        val parcels = DeliveryWidgetSelection.parcels(onTheWay = listOf(nextUp, outForDelivery, later), delivered = emptyList())

        assertEquals(listOf(outForDelivery, nextUp, later), parcels)
        assertEquals(listOf(listOf(outForDelivery, nextUp), listOf(later)), DeliveryWidgetSelection.pages(parcels, medium = true))
        assertEquals(listOf(listOf(outForDelivery), listOf(nextUp), listOf(later)), DeliveryWidgetSelection.pages(parcels, medium = false))
    }

    @Test
    fun widgetShowsTwoOutForDeliveryParcelsBeforeOtherCandidates() {
        val nextUp = widgetParcel(label = "Next up", outForDelivery = false)
        val first = widgetParcel(label = "Courier one", outForDelivery = true)
        val second = widgetParcel(label = "Courier two", outForDelivery = true)
        val third = widgetParcel(label = "Courier three", outForDelivery = true)
        val parcels = DeliveryWidgetSelection.parcels(onTheWay = listOf(nextUp, first, second, third), delivered = emptyList())

        assertEquals(listOf(listOf(first, second), listOf(third, nextUp)), DeliveryWidgetSelection.pages(parcels, medium = true))
        assertEquals(listOf(listOf(nextUp)), DeliveryWidgetSelection.pages(listOf(nextUp), medium = true))
    }

    /** The selection half of ParcelRouteTests.testTheWidgetGalleryShowsTheDemosNextDeliveries; its drawings wait for :app. */
    @Test
    fun theWidgetGalleryShowsTwoOfTheDemosNextDeliveriesInEveryLanguage() {
        for (language in AppLanguage.entries) {
            val parcels = DemoRepository(MemoryDefaults(), language = { language }).list()
            val shown = DeliveryWidgetSelection.pages(ParcelStore.widgetDeliveries(parcels, Localizer(language)), medium = true).firstOrNull()
            assertEquals("$language", 2, shown?.size)
        }
    }

    /** A delivered parcel comes after those on their way, for a day after it arrived, even when the app has not published since. */
    @Test
    fun widgetKeepsADeliveredParcelForADay() {
        val now = Instant.ofEpochSecond(1_800_000_000)
        val nextUp = widgetParcel(label = "Next up", outForDelivery = false)
        val arrived = widgetParcel(label = "Arrived", outForDelivery = false).copy(deliveredAt = now.minusSeconds(3_600))
        val older = widgetParcel(label = "Older", outForDelivery = false)
            .copy(deliveredAt = now.minusSeconds(DeliveryWidgetSelection.deliveredFor.toLong() + 1))
        val parcels = DeliveryWidgetSelection.parcels(onTheWay = listOf(nextUp), delivered = listOf(arrived, older), now = now)

        assertEquals(listOf(nextUp, arrived), parcels)
        assertEquals(listOf(listOf(nextUp), listOf(arrived)), DeliveryWidgetSelection.pages(parcels, medium = true, now = now))
        assertEquals(
            listOf(listOf(nextUp)),
            DeliveryWidgetSelection.pages(parcels, medium = false, now = now.plusSeconds(DeliveryWidgetSelection.deliveredFor.toLong())),
        )
        val many = (0 until 12).map { widgetParcel(label = "Parcel $it", outForDelivery = false) }
        assertEquals(DeliveryWidgetSelection.limit, DeliveryWidgetSelection.parcels(onTheWay = many, delivered = listOf(arrived), now = now).size)
    }

    /** The next button turns the widgets of one size, and for a few minutes only. */
    @Test
    fun widgetTurnLastsItsSpellForOneSize() {
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val at = Instant.ofEpochSecond(1_800_000_000)
        val turn = DeliveryWidgetTurn(parcel = UUID.randomUUID(), at = at)

        assertNull(store.turn(size = "small", now = at))
        store.setTurn(turn, size = "small")
        assertEquals(turn, store.turn(size = "small", now = at.plusSeconds(60)))
        assertNull(store.turn(size = "medium", now = at.plusSeconds(60)))
        assertNull(store.turn(size = "small", now = turn.ends))
    }

    /**
     * Android's own: the widget's line for a parcel with no estimate is its status as the list says it, which names the
     * probable carrier rather than leave its placeholder unfilled.
     */
    @Test
    fun widgetNamesTheProbableCarrierOfAParcelWithoutAnEstimate() {
        val packageId = UUID.randomUUID()
        // A synthetic number in DPD Switzerland's depot range, which no carrier has answered for yet.
        val json = """
            {
              "packages": [{
                "id": "${packageId.uuidString}",
                "tracking_number": "06080000000002",
                "label": "Test parcel",
                "carrier": "unknown",
                "created_at": "2026-10-28T09:00:00Z",
                "sync_status": "waiting",
                "carrier_data": { "routing": { "likely_carrier": { "carrier": "dpd", "since": "2026-10-28T10:00:00Z" } } },
                "notifications_muted": false,
                "tracking_events": []
              }]
            }
        """.trimIndent()
        val parcels = ApiJson.decodeFromString(PackageListResponse.serializer(), json).packages
        val localizer = Localizer(AppLanguage.En)
        val now = Instant.parse("2026-10-28T12:00:00Z")
        assertNull(localizer.parcelDeliveryEstimate(parcels.first(), now))

        val next = ParcelStore.nextDeliveries(parcels, localizer, now).single()
        assertEquals("Waiting for DPD Switzerland’s first scan", next.detail)
        assertEquals(listOf(next), ParcelStore.widgetDeliveries(parcels, localizer, now))
    }

    /**
     * Android's own: the store hands the widget the parcels on their way, then those delivered within the last day, the
     * latest first, each with its arrival and its completion date for a line.
     */
    @Test
    fun widgetDeliveriesFollowTheParcelsOnTheirWayWithThoseDeliveredWithinADay() {
        val now = Instant.parse("2026-10-28T12:00:00Z")
        val localizer = Localizer(AppLanguage.En)
        fun delivered(at: String) = UUID.randomUUID().let { makeParcel(it, events = listOf(event(it, TrackingStage.Delivered, at))) }
        val onTheWay = UUID.randomUUID().let { makeParcel(it, events = listOf(event(it, TrackingStage.InTransit, "2026-10-28T08:00:00Z"))) }
        val earlier = delivered("2026-10-28T09:00:00Z")
        val latest = delivered("2026-10-28T11:00:00Z")
        val yesterday = delivered("2026-10-27T11:00:00Z")

        val shown = ParcelStore.widgetDeliveries(listOf(earlier, yesterday, onTheWay, latest), localizer, now)
        assertEquals(listOf(onTheWay.id, latest.id, earlier.id), shown.map { it.id })
        assertNull(shown[0].deliveredAt)
        assertEquals(Instant.parse("2026-10-28T11:00:00Z"), shown[1].deliveredAt)
        assertEquals(localizer.parcelCompletionDate(latest, now), shown[1].detail)
    }

    @Test
    fun disablingWidgetRemovesSharedParcelSnapshot() {
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val snapshot = DeliveryWidgetSnapshot(
            generatedAt = java.time.Instant.ofEpochSecond(1_700_000_000),
            languageCode = "fr",
            parcels = listOf(widgetParcel(label = "Private parcel", outForDelivery = true)),
        )

        assertTrue(store.isEnabled)
        store.setLanguageCode("fr")
        store.setLiveActivitiesEnabled(true)
        assertTrue(store.save(snapshot))
        assertEquals(snapshot, store.snapshot)

        store.setEnabled(false)

        assertFalse(store.isEnabled)
        assertTrue(store.liveActivitiesEnabled)
        assertNull(store.snapshot)
        assertEquals("fr", store.languageCode)
    }

    @Test
    fun deliveryLiveActivityOnlyStartsForDeliveryDay() {
        assertNull(TrackingStage.Pending.deliveryActivityPhase)
        assertNull(TrackingStage.Registered.deliveryActivityPhase)
        assertNull(TrackingStage.Accepted.deliveryActivityPhase)
        assertNull(TrackingStage.InTransit.deliveryActivityPhase)
        assertNull(TrackingStage.Customs.deliveryActivityPhase)
        assertEquals(DeliveryActivityPhase.OutForDelivery, TrackingStage.OutForDelivery.deliveryActivityPhase)
        assertEquals(DeliveryActivityPhase.Delivered, TrackingStage.Delivered.deliveryActivityPhase)
        assertEquals(DeliveryActivityPhase.FailedAttempt, TrackingStage.FailedAttempt.deliveryActivityPhase)
        assertEquals(DeliveryActivityPhase.ReadyForPickup, TrackingStage.ReadyForPickup.deliveryActivityPhase)
        assertEquals(DeliveryActivityPhase.Returned, TrackingStage.Returned.deliveryActivityPhase)
        // A reported problem ends a running activity, like a failed attempt.
        assertEquals(DeliveryActivityPhase.Exception, TrackingStage.Exception.deliveryActivityPhase)
    }

    @Test
    fun pendingEventDoesNotOverrideCarrierProgress() {
        val id = UUID.randomUUID()
        val parcel = makeParcel(
            id,
            events = listOf(
                event(id, TrackingStage.InTransit, "2026-08-08T10:00:00Z"),
                event(id, TrackingStage.Pending, "2026-08-09T10:00:00Z"),
            ),
        )
        assertEquals(TrackingStage.InTransit, parcel.currentStage)
        assertEquals(TrackingStage.InTransit, parcel.currentEvent?.stage)
    }

    @Test
    fun announcementDoesNotStepAMovingParcelBack() {
        val id = UUID.randomUUID()
        val transit = event(id, TrackingStage.InTransit, "2026-08-08T10:00:00Z")
        val accepted = event(id, TrackingStage.Accepted, "2026-08-09T10:00:00Z")
        val notice = event(id, TrackingStage.Registered, "2026-08-10T10:00:00Z")
        assertEquals(transit.id, makeParcel(id, events = listOf(notice, accepted, transit)).currentEvent?.id)
        assertEquals(accepted.id, makeParcel(id, events = listOf(notice, accepted)).currentEvent?.id)
        assertEquals(notice.id, makeParcel(id, events = listOf(notice)).currentEvent?.id)
        for (stage in listOf(TrackingStage.Exception, TrackingStage.Returned, TrackingStage.Delivered)) {
            val before = event(id, stage, "2026-08-09T12:00:00Z")
            assertEquals(notice.id, makeParcel(id, events = listOf(transit, before, notice)).currentEvent?.id)
        }
    }

    @Test
    fun eventOrderingUsesInstantsAndStableTieBreakers() {
        val id = UUID.randomUUID()
        val accepted = event(id, TrackingStage.Accepted, "2026-08-08T10:00:00Z")
        val transit = event(id, TrackingStage.InTransit, "2026-08-08T12:00:00.000+02:00")
            .copy(id = UUID.fromString("00000000-0000-0000-0000-000000000001"))
        val tied = transit.copy(id = UUID.fromString("00000000-0000-0000-0000-000000000002"))
        val older = event(id, TrackingStage.Registered, "2026-08-08T09:59:59.999Z")
        val invalid = event(id, TrackingStage.Delivered, "invalid")
        val pending = event(id, TrackingStage.Pending, "2026-08-09T10:00:00Z")
        val events = listOf(older, tied, invalid, pending, accepted, transit)
        val expected = listOf(pending, tied, transit, accepted, older, invalid).map { it.id }
        for (offset in events.indices) {
            val reordered = events.drop(offset) + events.take(offset)
            val parcel = makeParcel(id, events = reordered)
            assertEquals(expected, parcel.sortedEvents.map { it.id })
            assertEquals(tied.id, parcel.currentEvent?.id)
        }
    }

    @Test
    fun currentEventHandlesEmptyPendingAndUpdatedHistories() {
        val id = UUID.randomUUID()
        var parcel = makeParcel(id)
        assertNull(parcel.currentEvent)
        val pending = event(id, TrackingStage.Pending, "2026-08-09T10:00:00Z")
        parcel = parcel.copy(trackingEvents = listOf(pending, event(id, TrackingStage.Pending, "2026-08-08T10:00:00Z")))
        assertEquals(pending.id, parcel.currentEvent?.id)
        val accepted = event(id, TrackingStage.Accepted, "2026-08-07T10:00:00Z")
        parcel = parcel.copy(trackingEvents = parcel.trackingEvents + accepted)
        assertEquals(accepted.id, parcel.currentEvent?.id)
        parcel = parcel.copy(trackingEvents = listOf(event(id, TrackingStage.Delivered, "2026-08-10T10:00:00Z")))
        assertEquals(TrackingStage.Delivered, parcel.currentStage)
    }

    @Test
    fun carrierNoticeInNoStageLeavesTheNewestScanInAStageCurrent() {
        // The server stores a scan no rule gives a stage as pending where in transit would step back.
        val id = UUID.randomUUID()
        val notice = event(id, TrackingStage.Pending, "2026-08-10T15:08:00Z")
        for (stage in listOf(TrackingStage.Delivered, TrackingStage.Exception, TrackingStage.OutForDelivery, TrackingStage.ReadyForPickup)) {
            val scan = event(id, stage, "2026-08-10T14:59:00Z")
            val parcel = makeParcel(id, events = listOf(scan, notice))
            // The notice stays in the timeline, newest first.
            assertEquals(notice.id, parcel.sortedEvents.first().id)
            assertEquals(scan.id, parcel.currentEvent?.id)
            assertEquals(stage, parcel.currentStage)
        }
    }

    @Test
    fun repeatedTimestampParsingPreservesFormatsAndInvalidValues() {
        val expected = DateParser.date("2026-08-08T10:00:00Z")!!
        repeat(3) {
            assertEquals(expected, DateParser.date("2026-08-08T12:00:00.000+02:00"))
            assertEquals(0.123456, DateParser.date("2026-08-08T10:00:00.123456Z")!!.timeIntervalSince(expected), 0.001)
            assertEquals(expected, DateParser.date("2026-08-08T10:00:00Z"))
            assertNull(DateParser.date("invalid"))
            assertNull(DateParser.date(""))
        }
    }

    @Test
    fun parcelStatusLookupPerformance() {
        val parcels = (0 until 30).map { index ->
            val id = UUID.randomUUID()
            makeParcel(
                id,
                events = (0 until 20).map { day ->
                    event(id, TrackingStage.InTransit, String.format(Locale.ROOT, "2026-08-%02dT%02d:00:00.000Z", day + 1, index % 24))
                },
            )
        }
        // XCTest's measure block times this; JUnit runs it once.
        var count = 0
        // List grouping and card rendering repeatedly inspect each parcel's status.
        for (parcel in parcels) {
            repeat(20) {
                if (parcel.currentStage == TrackingStage.InTransit) count += 1
            }
        }
        assertEquals(600, count)
    }

    @Test
    fun attentionRulesMatchWebApp() {
        val now = DateParser.date("2026-08-09T12:00:00Z")!!
        val id = UUID.randomUUID()
        val stalled = makeParcel(id, events = listOf(event(id, TrackingStage.InTransit, "2026-08-04T10:00:00Z")))
        assertEquals(ParcelAttention.Stalled, stalled.attention(now))

        val failed = stalled.copy(syncStatus = SyncStatus.Error)
        assertEquals(ParcelAttention.SyncError, failed.attention(now))
        assertEquals(ParcelAttention.SyncError, failed.notice(now))
        assertTrue(failed.updateFailed)
        // A number only the owner can correct still sorts first, but its card does not say the update failed.
        for (code in listOf("carrier:refused_number", "carrier:placeholder_number")) {
            val refused = failed.copy(syncError = code)
            assertEquals(ParcelAttention.SyncError, refused.attention(now))
            assertNull(refused.notice(now))
            assertFalse(refused.updateFailed)
        }

        // A generated track, however old and whatever the latest check (parcelPriority.test.ts).
        val trackId = UUID.randomUUID()
        val moves = (0 until 4).map { step ->
            TrackingEvent(
                id = UUID.randomUUID(), packageId = trackId, stage = TrackingStage.InTransit, description = "In transit",
                location = "Place $step",
                occurredAt = DateParser.isoString(DateParser.date("2026-07-20T09:00:00Z")!!.plusSeconds(step * 86_111L)),
            )
        }
        var generated = makeParcel(trackId, events = moves)
        assertEquals(ParcelAttention.Generated, generated.attention(now))
        generated = generated.copy(syncStatus = SyncStatus.Error)
        assertEquals(ParcelAttention.Generated, generated.attention(now))
        // Two moves at the interval are not yet a track.
        assertEquals(ParcelAttention.Stalled, makeParcel(trackId, events = moves.take(3)).attention(now))

        val announcedId = UUID.randomUUID()
        val announced = makeParcel(announcedId, events = listOf(event(announcedId, TrackingStage.Pending, "2026-08-01T08:00:00Z")))
        assertNull(announced.attention(now))

        val oldUnannounced = announced.copy(syncStatus = SyncStatus.Waiting)
        assertEquals("status.unannounced", oldUnannounced.displayStatus.key)
        assertEquals(ParcelAttention.NotAnnounced, oldUnannounced.attention(now))

        val recentUnannounced = oldUnannounced.copy(createdAt = "2026-08-08T12:01:00Z")
        assertNull(recentUnannounced.attention(now))
    }

    @Test
    fun awaitingHandoverMatchesWebApp() {
        val now = DateParser.date("2026-08-09T12:00:00Z")!!
        val id = UUID.randomUUID()
        val labelled = makeParcel(
            id,
            events = listOf(event(id, TrackingStage.Pending, "2026-08-07T08:00:00Z"), event(id, TrackingStage.Registered, "2026-08-08T12:00:00Z")),
        )
        assertTrue(labelled.awaitingHandover(now))
        // A label reprinted since: the first still counts.
        val reprinted = makeParcel(
            id,
            events = listOf(event(id, TrackingStage.Registered, "2026-08-07T12:00:00Z"), event(id, TrackingStage.Registered, "2026-08-09T11:00:00Z")),
        )
        assertTrue(reprinted.awaitingHandover(now))

        assertFalse(makeParcel(id, events = listOf(event(id, TrackingStage.Registered, "2026-08-08T12:00:01Z"))).awaitingHandover(now))
        assertFalse(
            makeParcel(
                id,
                events = listOf(event(id, TrackingStage.Registered, "2026-08-05T12:00:00Z"), event(id, TrackingStage.Accepted, "2026-08-07T12:00:00Z")),
            ).awaitingHandover(now),
        )
        assertFalse(makeParcel(id, events = listOf(event(id, TrackingStage.Pending, "2026-08-05T12:00:00Z"))).awaitingHandover(now))
        assertFalse(makeParcel(id).awaitingHandover(now))
    }

    @Test
    fun filtersSearchCompactTrackingNumbers() {
        val parcel = makeParcel(trackingNumber = "99.34.123456.12345678")
        val result = ParcelOrganizer.visible(
            listOf(parcel),
            query = "9934 123456",
            status = ParcelStatusFilter.All,
            carrier = null,
            sort = ParcelSort.Priority,
        )
        assertEquals(listOf(parcel.id), result.map { it.id })
    }

    @Test
    fun archivedParcelsAreNotActive() {
        val parcel = makeParcel().copy(archivedAt = "2026-08-09T12:00:00Z")
        assertFalse(parcel.isActive)
        assertTrue(ParcelOrganizer.sections(listOf(parcel)).any { it.kind == ParcelSectionKind.Archived })
    }

    @Test
    fun pastDeliveriesSortByCompletionInsteadOfEtaOrCreationOrder() {
        val olderId = UUID.randomUUID()
        val newerId = UUID.randomUUID()
        val older = makeParcel(olderId, events = listOf(event(olderId, TrackingStage.Delivered, "2026-09-06T12:00:00Z")))
            .copy(createdAt = "2026-09-07T12:00:00Z", expectedDelivery = "2026-09-12")
        val newer = makeParcel(newerId, events = listOf(event(newerId, TrackingStage.Delivered, "2026-09-07T12:00:00Z")))
            .copy(createdAt = "2026-09-01T12:00:00Z", expectedDelivery = "2026-09-14")
        val past = ParcelOrganizer.sections(listOf(older, newer)).firstOrNull { it.kind == ParcelSectionKind.Delivered }
        assertEquals(listOf(newerId, olderId), past?.parcels?.map { it.id })
    }

    @Test
    fun archivedParcelsAreOrderedByNewestDisplayedDate() {
        val olderId = UUID.randomUUID()
        val older = makeParcel(olderId, events = listOf(event(olderId, TrackingStage.Delivered, "2026-08-05T12:00:00Z")))
            .copy(archivedAt = "2026-08-10T12:00:00Z")

        val newerId = UUID.randomUUID()
        val newer = makeParcel(newerId, events = listOf(event(newerId, TrackingStage.Delivered, "2026-08-07T12:00:00Z")))
            .copy(archivedAt = "2026-08-09T12:00:00Z")

        val archived = ParcelOrganizer.sections(listOf(older, newer)).firstOrNull { it.kind == ParcelSectionKind.Archived }

        assertEquals(listOf(newerId, olderId), archived?.parcels?.map { it.id })
    }

    @Test
    fun passportNewStampsUseExplicitScansAndLocalCompletionDates() {
        fun shipment(vararg scans: Triple<TrackingStage, String, String?>): Parcel {
            val id = UUID.randomUUID()
            return makeParcel(id, events = scans.map { (stage, date, location) -> event(id, stage, date).copy(location = location) })
        }
        val international = shipment(
            Triple(TrackingStage.Accepted, "2026-10-31T12:00:00Z", "Berlin, Germany"),
            Triple(TrackingStage.ReadyForPickup, "2026-11-30T12:00:00Z", "Zurich, Switzerland"),
            Triple(TrackingStage.Delivered, "2026-12-01T08:00:00Z", "Zurich, Switzerland"),
            Triple(TrackingStage.Delivered, "2026-12-01T08:10:00Z", "Zurich, Switzerland"),
        ).copy(archivedAt = "2026-12-02T12:00:00Z")
        val parcels = listOf(
            international,
            shipment(Triple(TrackingStage.Accepted, "2026-11-29T12:00:00Z", "CH"), Triple(TrackingStage.Delivered, "2026-12-01T09:00:00Z", "CH")),
            shipment(Triple(TrackingStage.Delivered, "2026-11-30T23:30:00Z", null)),
        )
        val stats = PassportStatistics(parcels, timeZone = ZoneId.of("Europe/Zurich"))
        assertEquals(1, stats.crossBorderCount)
        assertEquals(1, stats.domesticDeliveryCount)
        assertEquals(1, stats.longWaitDeliveryCount)
        assertEquals(1, stats.pickupDeliveryCount)
        assertEquals(3, stats.decemberDeliveryCount)
        assertEquals(3, stats.maxDeliveriesInOneDay)
        val utc = PassportStatistics(parcels, timeZone = ZoneOffset.UTC)
        assertEquals(2, utc.decemberDeliveryCount)
        assertEquals(2, utc.maxDeliveriesInOneDay)
    }

    @Test
    fun passportNewStampsRejectUnprovenJourneys() {
        fun shipment(vararg scans: Triple<TrackingStage, String, String?>): Parcel {
            val id = UUID.randomUUID()
            return makeParcel(id, events = scans.map { (stage, date, location) -> event(id, stage, date).copy(location = location) })
        }
        val stats = PassportStatistics(
            listOf(
                shipment(
                    Triple(TrackingStage.Registered, "2026-11-01T00:00:00Z", "DE"), Triple(TrackingStage.Accepted, "2026-11-02T00:00:00Z", null),
                    Triple(TrackingStage.Delivered, "2026-11-03T00:00:00Z", "CH"),
                ),
                shipment(Triple(TrackingStage.Accepted, "2026-11-01T00:00:00Z", "Wilmington, DE"), Triple(TrackingStage.Delivered, "2026-11-03T00:00:00Z", "Geneva, GE")),
                shipment(
                    Triple(TrackingStage.Accepted, "2026-11-01T00:00:00Z", "DE"), Triple(TrackingStage.ReadyForPickup, "2026-11-01T00:00:00Z", "CH"),
                    Triple(TrackingStage.Delivered, "2026-11-01T00:00:00Z", "CH"),
                ),
                shipment(Triple(TrackingStage.Accepted, "2026-11-01T00:00:00Z", "DE"), Triple(TrackingStage.ReadyForPickup, "2026-12-02T00:00:00Z", "CH")),
                shipment(
                    Triple(TrackingStage.Accepted, "2026-11-01T00:00:00Z", "DE"), Triple(TrackingStage.Delivered, "2026-12-02T00:00:00Z", "CH"),
                    Triple(TrackingStage.Returned, "2026-12-03T00:00:00Z", null),
                ),
                shipment(Triple(TrackingStage.Accepted, "invalid", "DE"), Triple(TrackingStage.Delivered, "2026-12-02T00:00:00Z", "CH")),
            ),
            timeZone = ZoneOffset.UTC,
        )
        assertEquals(0, stats.crossBorderCount)
        assertEquals(0, stats.domesticDeliveryCount)
        assertEquals(0, stats.longWaitDeliveryCount)
        assertEquals(0, stats.pickupDeliveryCount)
        assertEquals(0, stats.decemberDeliveryCount)
    }

    @Test
    fun passportCountsAStateCodeOnlyInTheCountryTheScanWasPlacedIn() {
        class Scan(val stage: TrackingStage, val date: String, val location: String?, val placed: String?)
        fun shipment(vararg scans: Scan): Parcel {
            val id = UUID.randomUUID()
            return makeParcel(
                id,
                events = scans.map { scan ->
                    event(id, scan.stage, scan.date).copy(
                        location = scan.location,
                        place = scan.placed?.let { EventPlace(latitude = 0.0, longitude = 0.0, precision = EventPlacePrecision.Country, country = it, name = it) },
                    )
                },
            )
        }
        val stats = PassportStatistics(
            listOf(
                shipment(Scan(TrackingStage.Accepted, "2026-08-04T10:00:00Z", "Haiger, DE", "DE"), Scan(TrackingStage.Delivered, "2026-08-05T10:00:00Z", "Koblenz, DE", "DE")),
                shipment(Scan(TrackingStage.Accepted, "2026-08-04T10:00:00Z", "Toronto, CA", "CA"), Scan(TrackingStage.Delivered, "2026-08-06T10:00:00Z", null, null)),
                // Delaware and California, placed in the United States, and a code nobody placed: none of them a country.
                shipment(Scan(TrackingStage.Accepted, "2026-08-04T10:00:00Z", "Wilmington, DE", "US"), Scan(TrackingStage.Delivered, "2026-08-06T10:00:00Z", "Los Angeles, CA", "US")),
                shipment(Scan(TrackingStage.Accepted, "2026-08-04T10:00:00Z", "Hannover, DE", null), Scan(TrackingStage.Delivered, "2026-08-06T10:00:00Z", null, null)),
            ),
        )
        assertEquals(setOf("CA", "DE"), stats.originCountries.map { it.code }.toSet())
        assertEquals(listOf(1, 1), stats.originCountries.map { it.count })
        assertEquals(1, stats.domesticDeliveryCount)
        assertEquals(0, stats.crossBorderCount)
    }

    @Test
    fun passportWaitingStampRequiresMoreThanThirtyDays() {
        val id = UUID.randomUUID()
        val start = event(id, TrackingStage.Accepted, "2026-11-01T00:00:00Z")
        val exact = makeParcel(id, events = listOf(start, event(id, TrackingStage.Delivered, "2026-12-01T00:00:00Z")))
        val longer = makeParcel(id, events = listOf(start, event(id, TrackingStage.Delivered, "2026-12-01T00:00:00.001Z")))
        assertEquals(0, PassportStatistics(listOf(exact)).longWaitDeliveryCount)
        assertEquals(1, PassportStatistics(listOf(longer)).longWaitDeliveryCount)
    }

    @Test
    fun emptyPassportHasNoInventedRecordsOrCountries() {
        val statistics = PassportStatistics(emptyList())
        assertEquals(0, statistics.trackedCount)
        assertEquals(0, statistics.deliveredCount)
        assertEquals(0, statistics.activeCount)
        assertEquals(0, statistics.durationSampleCount)
        assertNull(statistics.averageDeliveryDuration)
        assertNull(statistics.fastestDelivery)
        assertTrue(statistics.originCountries.isEmpty())
        assertEquals(0, statistics.unknownOriginCount)
        assertEquals(1, statistics.nextMilestoneCount)
        assertEquals(0.0, statistics.milestoneProgress, 0.0)
    }

    @Test
    fun passportUsesCarrierJourneyTimesAndIncludesArchivedDeliveries() {
        val firstId = UUID.randomUUID()
        val first = makeParcel(
            firstId,
            events = listOf(
                event(firstId, TrackingStage.Registered, "2026-08-01T10:00:00Z"),
                event(firstId, TrackingStage.Accepted, "2026-08-06T10:00:00Z"),
                event(firstId, TrackingStage.Delivered, "2026-08-08T10:00:00Z"),
            ),
        ).copy(archivedAt = "2026-08-09T10:00:00Z")
        val secondId = UUID.randomUUID()
        val second = makeParcel(
            secondId,
            events = listOf(
                event(secondId, TrackingStage.InTransit, "2026-08-07T10:00:00Z"),
                event(secondId, TrackingStage.Delivered, "2026-08-08T10:00:00Z"),
                event(secondId, TrackingStage.Pending, "2026-08-09T10:00:00Z"),
            ),
        )
            // A parcel added after delivery still has a valid carrier journey.
            .copy(createdAt = "2026-08-10T10:00:00Z")
        val returnedId = UUID.randomUUID()
        val returned = makeParcel(
            returnedId,
            events = listOf(
                event(returnedId, TrackingStage.Accepted, "2026-08-05T10:00:00Z"),
                event(returnedId, TrackingStage.Delivered, "2026-08-06T10:00:00Z"),
                event(returnedId, TrackingStage.Returned, "2026-08-08T10:00:00Z"),
            ),
        )
        val statistics = PassportStatistics(listOf(first, second, returned, makeParcel()))
        assertEquals(4, statistics.trackedCount)
        assertEquals(2, statistics.deliveredCount)
        assertEquals(1, statistics.activeCount)
        assertEquals(2, statistics.durationSampleCount)
        assertEquals(36.0 * 3_600, statistics.averageDeliveryDuration!!, 0.001)
        assertEquals(secondId, statistics.fastestDelivery?.parcelId)
        assertEquals(24.0 * 3_600, statistics.fastestDelivery!!.duration, 0.0)
        assertEquals(DateParser.date("2026-08-08T10:00:00Z"), statistics.fastestDelivery?.deliveredAt)
    }

    @Test
    fun passportOrdersActualInstantsAcrossTimeZones() {
        val id = UUID.randomUUID()
        val parcel = makeParcel(
            id,
            events = listOf(
                // Lexical order incorrectly puts the accepted scan last.
                event(id, TrackingStage.Accepted, "2026-08-08T12:00:00+03:00"),
                event(id, TrackingStage.Delivered, "2026-08-08T11:00:00Z"),
            ),
        )
        val statistics = PassportStatistics(listOf(parcel))
        assertEquals(1, statistics.deliveredCount)
        assertEquals(0, statistics.activeCount)
        assertEquals(2.0 * 3_600, statistics.averageDeliveryDuration!!, 0.001)
    }

    @Test
    fun passportDoesNotInventDurationsFromPartialOrInvalidHistories() {
        val histories = listOf(
            listOf(TrackingStage.Delivered to "2026-08-08T10:00:00Z"),
            listOf(TrackingStage.Registered to "2026-08-07T10:00:00Z", TrackingStage.Delivered to "2026-08-08T10:00:00Z"),
            listOf(TrackingStage.OutForDelivery to "2026-08-08T09:00:00Z", TrackingStage.Delivered to "2026-08-08T10:00:00Z"),
            listOf(TrackingStage.Accepted to "invalid", TrackingStage.Delivered to "2026-08-08T10:00:00Z"),
            listOf(TrackingStage.Accepted to "2026-08-08T10:00:00Z", TrackingStage.Delivered to "invalid"),
            listOf(TrackingStage.Accepted to "2026-08-09T10:00:00Z", TrackingStage.Delivered to "2026-08-08T10:00:00Z"),
            listOf(TrackingStage.Accepted to "2026-08-08T10:00:00Z", TrackingStage.Delivered to "2026-08-08T10:00:00Z"),
        )
        for (history in histories) {
            val id = UUID.randomUUID()
            val parcel = makeParcel(id, events = history.map { event(id, it.first, it.second) })
            val statistics = PassportStatistics(listOf(parcel))
            assertEquals("Unexpected duration for $history", 0, statistics.durationSampleCount)
            assertNull(statistics.averageDeliveryDuration)
            assertNull(statistics.fastestDelivery)
        }
    }

    @Test
    fun passportCountryRanksRequireExplicitFirstPhysicalScanCountry() {
        fun originParcel(location: String?): Parcel {
            val id = UUID.randomUUID()
            val scan = event(id, TrackingStage.Accepted, "2026-08-06T10:00:00Z").copy(location = location)
            // Neither the international identifier nor UPS's headquarters is evidence.
            return makeParcel(id, trackingNumber = "RR123456785US", events = listOf(scan))
        }
        val statistics = PassportStatistics(
            listOf(
                originParcel("Milano, IT"), originParcel("Roma, Italy"),
                originParcel("Berlin (Germany)"), originParcel("Paris, France"),
                originParcel("Buchs AG"), originParcel(null), originParcel("Europe"),
            ),
        )
        assertEquals(listOf("IT", "DE", "FR"), statistics.originCountries.map { it.code })
        assertEquals(listOf(2, 1, 1), statistics.originCountries.map { it.count })
        assertEquals("🇮🇹", statistics.originCountries.firstOrNull()?.flag)
        assertEquals(4, statistics.knownOriginCount)
        assertEquals(3, statistics.unknownOriginCount)
    }

    @Test
    fun passportNeverPromotesLaterDestinationScanToOrigin() {
        val id = UUID.randomUUID()
        val registered = event(id, TrackingStage.Registered, "2026-08-05T10:00:00Z").copy(location = "London, GB")
        val accepted = event(id, TrackingStage.Accepted, "2026-08-06T10:00:00Z")
        val transit = event(id, TrackingStage.InTransit, "2026-08-07T10:00:00Z").copy(location = "Zürich, CH")
        val unknown = PassportStatistics(listOf(makeParcel(id, events = listOf(registered, accepted, transit))))
        assertTrue(unknown.originCountries.isEmpty())
        assertEquals(1, unknown.unknownOriginCount)

        // Registration's location is ignored; the first physical scan supplies evidence.
        val locatedAccepted = accepted.copy(location = "Paris, Frankreich")
        val known = PassportStatistics(listOf(makeParcel(id, events = listOf(registered, locatedAccepted, transit))))
        assertEquals(listOf("FR"), known.originCountries.map { it.code })

        val customs = event(id, TrackingStage.Customs, "2026-08-06T10:00:00Z").copy(location = "Basel, CH")
        assertTrue(PassportStatistics(listOf(makeParcel(id, events = listOf(customs, transit)))).originCountries.isEmpty())
    }

    @Test
    fun passportDoesNotMistakeStateOrCantonCodesForCountries() {
        for (location in listOf("Los Angeles, CA", "Bern, BE", "Dover, DE", "Fribourg (FR)", "St. John's, NL")) {
            val id = UUID.randomUUID()
            val scan = event(id, TrackingStage.Accepted, "2026-08-06T10:00:00Z").copy(location = location)
            val statistics = PassportStatistics(listOf(makeParcel(id, events = listOf(scan))))
            assertTrue("Ambiguous address: $location", statistics.originCountries.isEmpty())
        }
        for ((location, code) in listOf("Toronto, Canada" to "CA", "Brussels, Belgium" to "BE", "DE" to "DE")) {
            val id = UUID.randomUUID()
            val scan = event(id, TrackingStage.Accepted, "2026-08-06T10:00:00Z").copy(location = location)
            val statistics = PassportStatistics(listOf(makeParcel(id, events = listOf(scan))))
            assertEquals(code, statistics.originCountries.firstOrNull()?.code)
        }
    }

    @Test
    fun passportTiesAndMilestonesAreStableWhenParcelOrderChanges() {
        val parcels = (1..5).map { index ->
            val id = UUID.fromString("00000000-0000-0000-0000-%012d".format(Locale.ROOT, index))
            makeParcel(
                id,
                events = listOf(
                    event(id, TrackingStage.Accepted, "2026-08-06T10:00:00Z"),
                    event(id, TrackingStage.Delivered, "2026-08-07T10:00:00Z"),
                ),
            )
        }
        val forward = PassportStatistics(parcels)
        val reverse = PassportStatistics(parcels.reversed())
        assertEquals(forward.fastestDelivery, reverse.fastestDelivery)
        assertEquals(parcels.firstOrNull()?.id, forward.fastestDelivery?.parcelId)
        assertEquals(10, forward.nextMilestoneCount)
        assertEquals(0.5, forward.milestoneProgress, 0.0)
    }

    @Test
    fun sharedFriendsFixtureDecodesIncludingHiddenStatistics() {
        val snapshot = ApiJson.decodeFromString(FriendsSnapshot.serializer(), SharedResources.text("FriendsDemo.json"))
        assertEquals(3, snapshot.friends.size)
        assertEquals(
            listOf(FriendStamp.First, FriendStamp.Ten, FriendStamp.Connected, FriendStamp.Express),
            snapshot.friends.first().stats?.stamps,
        )
        assertNull(snapshot.friends.last().stats)
        assertNull(snapshot.friends.last().arrivedThisWeek)
        assertEquals(false, snapshot.profile?.shareArrival)
    }

    @Test
    fun nextDeliveryKeepsIssuesSeparateAndPrioritizesPickup() {
        val now = DateParser.date("2026-09-09T12:00:00Z")!!
        fun shipment(stage: TrackingStage): Parcel {
            val id = UUID.randomUUID()
            return makeParcel(id, events = listOf(event(id, stage, "2026-09-09T08:00:00Z")))
        }
        val customs = shipment(TrackingStage.Customs)
        val failed = shipment(TrackingStage.FailedAttempt)
        val delivery = shipment(TrackingStage.OutForDelivery).copy(syncStatus = SyncStatus.Error)
        val pickup = shipment(TrackingStage.ReadyForPickup)
        assertEquals(delivery.id, ParcelOrganizer.nextDelivery(listOf(customs, failed, delivery), now)?.id)
        assertEquals(pickup.id, ParcelOrganizer.nextDelivery(listOf(customs, delivery, pickup), now)?.id)
        assertNull(ParcelOrganizer.nextDelivery(listOf(customs, failed), now))
        assertNull(ParcelOrganizer.nextDelivery(listOf(shipment(TrackingStage.Delivered), shipment(TrackingStage.Returned)), now))
        val archived = delivery.copy(archivedAt = "2026-09-09T10:00:00Z")
        assertNull(ParcelOrganizer.nextDelivery(listOf(archived), now))
    }

    @Test
    fun nextDeliveryPrefersAParcelWhoseRouteCanBeDrawnOnTheSameDay() {
        val now = DateParser.date("2026-09-09T12:00:00Z")!!
        val zurich = EventPlace(latitude = 47.37, longitude = 8.54, precision = EventPlacePrecision.City, country = "CH", name = "Zürich")
        fun shipment(stage: TrackingStage, at: String, place: EventPlace? = null, expected: String? = null): Parcel {
            val id = UUID.randomUUID()
            return makeParcel(
                id,
                events = listOf(
                    TrackingEvent(
                        id = UUID.randomUUID(), packageId = id, stage = stage, description = "Update", location = null,
                        occurredAt = at, place = place,
                    ),
                ),
            ).copy(expectedDelivery = expected)
        }
        // Without a date on either, the newer update used to win; the map now decides first.
        val newer = shipment(TrackingStage.InTransit, at = "2026-09-09T09:00:00Z")
        val placed = shipment(TrackingStage.InTransit, at = "2026-09-09T07:00:00Z", place = zurich)
        assertEquals(placed.id, ParcelOrganizer.nextDelivery(listOf(newer, placed), now)?.id)
        val datedNewer = shipment(TrackingStage.InTransit, at = "2026-09-09T09:00:00Z", expected = "2026-09-10")
        val datedPlaced = shipment(TrackingStage.InTransit, at = "2026-09-09T07:00:00Z", place = zurich, expected = "2026-09-10")
        assertEquals(datedPlaced.id, ParcelOrganizer.nextDelivery(listOf(datedNewer, datedPlaced), now)?.id)
        // An earlier day or a more urgent stage still comes first.
        val sooner = shipment(TrackingStage.InTransit, at = "2026-09-09T06:00:00Z", expected = "2026-09-09")
        assertEquals(sooner.id, ParcelOrganizer.nextDelivery(listOf(sooner, datedPlaced), now)?.id)
        val pickup = shipment(TrackingStage.ReadyForPickup, at = "2026-09-09T06:00:00Z")
        assertEquals(pickup.id, ParcelOrganizer.nextDelivery(listOf(pickup, placed), now)?.id)
    }

    @Test
    fun stampNamesTheFirstPlacedCountryAndIsDatedOnlyOnceDelivered() {
        fun scan(day: Int, stage: TrackingStage, place: EventPlace?): TrackingEvent = TrackingEvent(
            id = UUID.randomUUID(), packageId = UUID.randomUUID(), stage = stage, description = "Update", location = null,
            occurredAt = "2026-09-${day}T12:00:00Z", place = place,
        )
        val kyoto = EventPlace(latitude = 35.01, longitude = 135.77, precision = EventPlacePrecision.City, country = "JP", name = "Kyoto")
        val zurich = EventPlace(latitude = 47.37, longitude = 8.54, precision = EventPlacePrecision.City, country = "CH", name = "Zürich")
        // Scans arrive in no particular order; the origin is the earliest one with a place.
        val scans = listOf(scan(20, TrackingStage.OutForDelivery, zurich), scan(10, TrackingStage.Registered, null), scan(11, TrackingStage.Accepted, kyoto))
        val travelling = makeParcel(events = scans)
        assertEquals("JP", travelling.stampOrigin)
        assertNull(travelling.stampDeliveryDate)

        val arrived = makeParcel(events = scans + scan(21, TrackingStage.Delivered, zurich))
        assertEquals("JP", arrived.stampOrigin)
        assertEquals("21.09", arrived.stampDeliveryDate)

        val unplaced = makeParcel(events = listOf(scan(10, TrackingStage.Registered, null)))
        assertNull(unplaced.stampOrigin)
    }

    @Test
    fun justDeliveredAreTheParcelsShownOnTheirWayThatHaveArrived() {
        val (tea, lamp, book) = List(3) { UUID.randomUUID() }
        val shown = listOf(
            makeParcel(tea, events = listOf(event(tea, TrackingStage.OutForDelivery, "2026-09-02T08:00:00Z"))),
            makeParcel(lamp, events = listOf(event(lamp, TrackingStage.InTransit, "2026-09-02T08:00:00Z"))),
            makeParcel(book, events = listOf(event(book, TrackingStage.Delivered, "2026-09-01T08:00:00Z"))),
        )
        val now = shown.toMutableList()
        now[0] = now[0].copy(trackingEvents = now[0].trackingEvents + event(tea, TrackingStage.Delivered, "2026-09-03T08:00:00Z"))
        now[1] = now[1].copy(trackingEvents = now[1].trackingEvents + event(lamp, TrackingStage.OutForDelivery, "2026-09-03T08:00:00Z"))
        // The parcel comes back as the list showed it: that is the place it keeps.
        assertEquals(listOf(shown[0]), ParcelOrganizer.justDelivered(shown, now))
        // One archived in the meantime, or gone, has no card to move.
        now[0] = now[0].copy(archivedAt = "2026-09-03T09:00:00Z")
        assertTrue(ParcelOrganizer.justDelivered(shown, now).isEmpty())
        assertTrue(ParcelOrganizer.justDelivered(shown, emptyList()).isEmpty())
    }

    @Test
    fun latestEventFollowsAChangedHistory() {
        val now = DateParser.date("2026-09-09T12:00:00Z")!!
        var parcel = DemoRepository.seed(now).first { it.label == "New trainers 👟" }
        val before = parcel.currentStage
        assertEquals(TrackingStage.ReadyForPickup, before)
        parcel = parcel.copy(
            trackingEvents = parcel.trackingEvents + TrackingEvent(
                id = UUID.randomUUID(), packageId = parcel.id, stage = TrackingStage.Delivered,
                description = "Delivered", occurredAt = DateParser.isoString(now),
            ),
        )
        assertEquals(TrackingStage.Delivered, parcel.currentStage)
        parcel = parcel.copy(trackingEvents = parcel.trackingEvents.dropLast(1))
        assertEquals(before, parcel.currentStage)
    }

    /** SessionIsolationTests' parcel, for the two of its tests that need only the parcel logic. */
    private fun sessionParcel(): Parcel = Parcel(
        id = UUID.randomUUID(), trackingNumber = "12345678", label = "Private account A parcel", carrier = CarrierId.Unknown,
        createdAt = "2026-09-08T00:00:00Z", syncStatus = SyncStatus.Ok, notificationsMuted = false,
    )

    @Test
    fun equalTimeEventsPreferDeliveryProgressRegardlessOfUUIDOrTimeZone() {
        var value = sessionParcel()
        value = value.copy(
            trackingEvents = listOf(
                TrackingEvent(
                    id = UUID.fromString("ffffffff-ffff-4fff-8fff-ffffffffffff"), packageId = value.id, stage = TrackingStage.InTransit,
                    description = "Transit", occurredAt = "2026-09-08T12:00:00+02:00",
                ),
                TrackingEvent(
                    id = UUID.fromString("00000000-0000-4000-8000-000000000001"), packageId = value.id, stage = TrackingStage.Delivered,
                    description = "Delivered", occurredAt = "2026-09-08T10:00:00Z",
                ),
            ),
        )
        assertEquals(TrackingStage.Delivered, value.currentStage)
    }

    @Test
    fun refreshReportsNewTrackingOnlyWhenTheTimelineStatusOrDeliveryDateChanged() {
        val checked = sessionParcel()
        val rechecked = checked.copy(lastSyncedAt = "2026-09-25T10:05:00Z", syncStatus = SyncStatus.Ok)
        assertFalse(Parcel.trackingChanged(from = listOf(checked), to = listOf(rechecked)))

        val status = checked.copy(lastStatusText = "Sorted at the depot")
        assertTrue(Parcel.trackingChanged(from = listOf(checked), to = listOf(status)))
        val delivery = checked.copy(expectedDelivery = "2026-09-27")
        assertTrue(Parcel.trackingChanged(from = listOf(checked), to = listOf(delivery)))
        val scanned = checked.copy(
            trackingEvents = checked.trackingEvents + TrackingEvent(
                id = UUID.randomUUID(), packageId = checked.id, stage = TrackingStage.InTransit,
                description = "Sorted at the depot", occurredAt = "2026-09-25T10:00:00Z",
            ),
        )
        assertTrue(Parcel.trackingChanged(from = listOf(checked), to = listOf(scanned)))

        // A delivery partner's parcel continues the one it replaced.
        val handoff = checked.copy(id = UUID.randomUUID(), carrierData = CarrierData(originalPackageId = checked.id))
        assertFalse(Parcel.trackingChanged(from = listOf(checked), to = listOf(handoff)))
    }
}
