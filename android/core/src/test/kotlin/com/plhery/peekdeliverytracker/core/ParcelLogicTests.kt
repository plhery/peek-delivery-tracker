package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.widgetParcel
import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierData
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
import com.plhery.peekdeliverytracker.core.api.LiveActivityUpdateTokenRequest
import com.plhery.peekdeliverytracker.core.api.NativePushDeviceRequest
import com.plhery.peekdeliverytracker.core.api.NativePushEnvironment
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.util.Locale
import java.util.UUID
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
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

        assertEquals(listOf(outForDelivery, nextUp), DeliveryWidgetSelection.displayParcels(listOf(nextUp, outForDelivery, later)))
    }

    @Test
    fun widgetShowsTwoOutForDeliveryParcelsBeforeOtherCandidates() {
        val nextUp = widgetParcel(label = "Next up", outForDelivery = false)
        val first = widgetParcel(label = "Courier one", outForDelivery = true)
        val second = widgetParcel(label = "Courier two", outForDelivery = true)

        assertEquals(listOf(first, second), DeliveryWidgetSelection.displayParcels(listOf(nextUp, first, second)))
        assertEquals(listOf(nextUp), DeliveryWidgetSelection.displayParcels(listOf(nextUp)))
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
