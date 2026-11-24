package com.plhery.peekdeliverytracker.core.api

import com.plhery.peekdeliverytracker.core.SharedResources
import java.util.UUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Test

class ApiContractTest {
    @Serializable
    private data class Fixture(val packageList: PackageListResponse, val queue: QueueResponse, val job: SyncJobResponse)

    @Test
    fun decodesSharedApiContractFixture() {
        val fixture = ApiJson.decodeFromString<Fixture>(SharedResources.text("ContractFixtures.json"))

        assertEquals(CarrierId.SwissPost, fixture.packageList.packages.first().carrier)
        assertEquals(TrackingStage.InTransit, fixture.packageList.packages.first().trackingEvents.first().stage)
        assertEquals(1, fixture.queue.jobIds.size)
        assertEquals(SyncJobStatus.Succeeded, fixture.job.status)
        assertEquals(1, fixture.job.result?.checked)
    }

    @Test
    fun decodesProductionPackagePayload() {
        val packageId = UUID.randomUUID()
        val eventId = UUID.randomUUID()
        val json = """
            {
              "packages": [{
                "id": "${packageId.toString().uppercase()}",
                "tracking_number": "1Z999AA10123456784",
                "label": "Test parcel",
                "carrier": "ups",
                "created_at": "2026-08-01T10:00:00Z",
                "expected_delivery": null,
                "last_status_text": "In transit",
                "last_synced_at": "2026-08-09T10:00:00Z",
                "sync_status": "ok",
                "sync_error": null,
                "tracking_url": "https://www.ups.com/track?tracknum=1Z999AA10123456784",
                "dpd_postcode": null,
                "carrier_data": null,
                "archived_at": null,
                "notifications_muted": false,
                "tracking_events": [{
                  "id": "$eventId",
                  "package_id": "$packageId",
                  "stage": "in_transit",
                  "description": "In transit",
                  "location": null,
                  "occurred_at": "2026-08-09T10:00:00Z"
                }]
              }]
            }
        """

        val response = ApiJson.decodeFromString<PackageListResponse>(json)

        assertEquals("https://www.ups.com/track?tracknum=1Z999AA10123456784", response.packages.first().trackingUrl)
        assertEquals(packageId, response.packages.first().trackingEvents.first().packageId)
    }

    @Test
    fun decodesNullTrackingEventsAsEmptyHistory() {
        val json = """
            {
              "packages": [{
                "id": "${UUID.randomUUID()}",
                "tracking_number": "999999999999999999",
                "label": "New parcel",
                "carrier": "swiss-post",
                "created_at": "2026-08-09T10:00:00Z",
                "expected_delivery": null,
                "last_status_text": null,
                "last_synced_at": null,
                "sync_status": "waiting",
                "sync_error": null,
                "tracking_url": null,
                "dpd_postcode": null,
                "archived_at": null,
                "notifications_muted": false,
                "tracking_events": null
              }]
            }
        """

        val response = ApiJson.decodeFromString<PackageListResponse>(json)

        assertEquals(emptyList<TrackingEvent>(), response.packages.first().trackingEvents)
    }

    /**
     * The input a carrier may find a parcel with reads in each of the three places the service suggests it: an account's
     * parcel, a lookup's link and a detection's answer. One added after a release reads as none, and the rest still reads.
     */
    @Test
    fun readsASuggestedInputAndAnUnknownOneAsNone() {
        fun row(field: String) = """
            {
              "packages": [{
                "id": "${UUID.randomUUID()}",
                "tracking_number": "JT0805136742918",
                "label": "Synthetic parcel",
                "carrier": "unknown",
                "created_at": "2026-11-20T10:00:00Z",
                "sync_status": "ok",
                "carrier_data": { "routing": { "input_suggested": { "carrier": "j-and-t-cn", "field": "$field" }, "next_check_at": "2026-11-21T10:00:00Z" } },
                "notifications_muted": false,
                "tracking_events": []
              }]
            }
        """
        val row = ApiJson.decodeFromString<PackageListResponse>(row("phoneDigits")).packages.single()
        assertEquals(CarrierInputSuggested(CarrierId.JAndTCn, CarrierInputSuggestedField.PhoneDigits), row.carrierData?.routing?.inputSuggested)
        val newer = ApiJson.decodeFromString<PackageListResponse>(row("somethingNew")).packages.single()
        assertNull(newer.carrierData?.routing?.inputSuggested)
        assertEquals("2026-11-21T10:00:00Z", newer.carrierData?.routing?.nextCheckAt)
        assertEquals("Synthetic parcel", newer.label)

        fun link(field: String) = """
            {"id":"22222222222A","role":"owner","kind":"lookup","createdAt":"2026-11-20T10:00:00Z","numberShown":true,
             "canKeep":true,"showNumber":false,"gift":false,"shared":true,"inputSuggested":{"carrier":"j-and-t-cn","field":"$field"}}
        """
        assertEquals(
            ParcelLinkInputSuggested(CarrierId.JAndTCn, ParcelLinkInputSuggestedField.PhoneDigits),
            ApiJson.decodeFromString<ParcelLink>(link("phoneDigits")).inputSuggested,
        )
        val newerLink = ApiJson.decodeFromString<ParcelLink>(link("somethingNew"))
        assertNull(newerLink.inputSuggested)
        assertEquals(ParcelLinkRole.Owner, newerLink.role)

        fun answer(field: String) =
            """{"trackingNumber":"JT0805136742918","carrier":"unknown","inputSuggested":{"carrier":"j-and-t-cn","field":"$field"}}"""
        assertEquals(
            CarrierDetectionInputSuggested(CarrierId.JAndTCn, CarrierDetectionInputSuggestedField.PhoneDigits),
            ApiJson.decodeFromString<CarrierDetectionResponse>(answer("phoneDigits")).inputSuggested,
        )
        val newerAnswer = ApiJson.decodeFromString<CarrierDetectionResponse>(answer("somethingNew"))
        assertNull(newerAnswer.inputSuggested)
        assertEquals("JT0805136742918", newerAnswer.trackingNumber)
    }

    @Test
    fun keepsACarrierTheReleaseDoesNotKnowAndRefusesAnUnknownStage() {
        val request = ApiJson.decodeFromString<NumberRefusalRequest>(
            """{"trackingNumber": "SYNTHETIC1", "carrier": "a-carrier-from-tomorrow", "extra": 1}""",
        )
        assertEquals(CarrierId("a-carrier-from-tomorrow"), request.carrier)
        assertEquals(
            """{"trackingNumber":"SYNTHETIC1","carrier":"a-carrier-from-tomorrow"}""",
            ApiJson.encodeToString(NumberRefusalRequest.serializer(), request),
        )
        assertThrows(SerializationException::class.java) {
            ApiJson.decodeFromString<TrackingStage>("\"lost_in_space\"")
        }
    }
}
