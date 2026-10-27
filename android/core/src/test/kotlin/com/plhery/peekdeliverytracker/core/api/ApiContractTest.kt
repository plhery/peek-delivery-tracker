package com.plhery.peekdeliverytracker.core.api

import com.plhery.peekdeliverytracker.core.SharedResources
import java.util.UUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import org.junit.Assert.assertEquals
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
