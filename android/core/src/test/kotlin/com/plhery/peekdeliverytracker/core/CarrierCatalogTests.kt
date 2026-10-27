package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PostcodeCountry
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingProviderCheck
import com.plhery.peekdeliverytracker.core.api.TrackingProviderCheckOutcome
import com.plhery.peekdeliverytracker.core.CarrierCatalogTestFixtures.bundledCatalogData
import com.plhery.peekdeliverytracker.core.CarrierCatalogTestFixtures.futureCatalogData
import com.plhery.peekdeliverytracker.core.CarrierCatalogTestFixtures.linkRulesCatalogData
import com.plhery.peekdeliverytracker.core.CarrierCatalogTestFixtures.recognitionPatternCatalogData
import com.plhery.peekdeliverytracker.core.CarrierCatalogTestFixtures.unknownChecksumCatalogData
import com.plhery.peekdeliverytracker.core.CarrierCatalogTestFixtures.unreadableCarrierCatalogData
import java.io.File
import java.io.IOException
import java.text.Collator
import java.util.UUID
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CarrierCatalogTests {
    // Exercise the bundled rules without inheriting a previously cached live catalog.
    private val catalog = CarrierCatalog(cacheFile = null)

    private fun parcel(
        trackingNumber: String,
        label: String,
        carrier: CarrierId,
        createdAt: String,
        syncStatus: SyncStatus = SyncStatus.Ok,
        trackingUrl: String? = null,
        dpdPostcode: String? = null,
        carrierData: CarrierData? = null,
    ) = Parcel(
        id = UUID.randomUUID(), trackingNumber = trackingNumber, label = label, carrier = carrier, createdAt = createdAt,
        syncStatus = syncStatus, trackingUrl = trackingUrl, dpdPostcode = dpdPostcode, carrierData = carrierData,
        notificationsMuted = false,
    )

    /** `URL.host`. */
    private fun host(url: String?): String? = url?.let { UrlComponents.parse(it)?.host }

    private fun query(url: String, name: String): String? =
        UrlComponents.parse(url)?.queryItems?.firstOrNull { it.name == name }?.value

    @Test
    fun discoveryIncludesBrowserCandidatesWithoutChangingHTTPRecognition() {
        val http = catalog.recognitionCandidates("000000000103")
        assertFalse(http.isEmpty())
        assertFalse(http.contains(CarrierId.Fedex))
        val discovery = catalog.discoveryCandidates("000000000103")
        assertEquals(http, discovery.take(http.size))
        assertTrue(discovery.drop(http.size).contains(CarrierId.Fedex))
        assertTrue(catalog.recognitionCandidates("33870000000000001", browser = true).contains(CarrierId.DhlEcommerce))
        assertEquals(emptyList<CarrierId>(), catalog.discoveryCandidates("1Z999AA10123456784"))
    }

    @Test
    fun claimingCarrierNamesOnlyOneOtherCarrierByShapeAndCheckDigit() {
        assertEquals(CarrierId.Dhl, catalog.claimingCarrier("JJD000390012345678", besides = CarrierId.Heppner))
        assertNull(catalog.claimingCarrier("JJD000390012345678", besides = CarrierId.Dhl))
        // Several carriers issue eight digits.
        assertNull(catalog.claimingCarrier("12345678", besides = CarrierId.Dhl))
        // A failed check digit leaves the number to no carrier.
        assertNull(catalog.claimingCarrier("1Z999AA10123456785", besides = CarrierId.Dhl))
        assertEquals(CarrierId.Ups, catalog.claimingCarrier("1Z999AA10123456784", besides = CarrierId.Dhl))
    }

    @Test
    fun mondialRelayLabelBarcodeDetectionAndPublicLink() {
        // Published example in Mondial Relay's label specification, not customer data.
        val barcode = "12123456780101006623123454"
        assertEquals(CarrierId.MondialRelay, catalog.detect(barcode).carrier)
        assertEquals(CarrierMatch.Confidence.High, catalog.detect(barcode).confidence)
        assertEquals(barcode, catalog.parse("Mon colis: $barcode").trackingNumber)
        assertTrue(catalog.requirements(CarrierId.MondialRelay, trackingNumber = barcode).isEmpty())
        for (invalid in listOf("00000000000000000000000000", "12123456780101106623123454", "12123456780101006623123455")) {
            assertNotEquals(CarrierId.MondialRelay, catalog.detect(invalid).carrier)
        }
        val parcel = parcel(barcode, "Example", CarrierId.MondialRelay, "2026-09-10T12:00:00Z")
        assertEquals(
            "https://www.mondialrelay.fr/suivi-de-colis/?numeroExpedition=121234567801",
            catalog.trackingLinks(parcel, AppLanguage.Fr).first().url,
        )
    }

    @Test
    fun inPostUKNumbersOpenInPostUK() {
        fun link(number: String): String {
            val parcel = parcel(number, "Example", CarrierId.Inpost, "2026-09-10T12:00:00Z")
            return catalog.trackingLinks(parcel, AppLanguage.En).first().url
        }
        for (number in listOf("JJD0002233564270287", "JD0002123456789012", "8YDR098765432", "00ABC0000001A001")) {
            assertEquals("https://inpost.co.uk/tracking/result?parcel_code=$number", link(number))
        }
        assertTrue(link("640000000000000000000001").startsWith("https://inpost.pl/sledzenie-przesylek"))
    }

    @Test
    fun courierGuyProductTrackingLinks() {
        for ((number, native) in listOf("LD000001" to "LD-000001", "DD000001" to "DD-000001")) {
            val parcel = parcel(number, "Example", CarrierId.TheCourierGuy, "2026-09-10T12:00:00Z")
            val link = catalog.trackingLinks(parcel, AppLanguage.En).first()
            assertEquals(native, query(link.url, "ref"))
        }
    }

    @Test
    fun linksUseTheNumberEachCarriersPageFinds() {
        fun link(carrier: CarrierId, number: String): String {
            val parcel = parcel(number, "Example", carrier, "2026-09-10T12:00:00Z")
            return catalog.trackingLinks(parcel, AppLanguage.En).first().url
        }
        assertEquals("https://apps.cdldelivers.com/Tracking-Page/track?id=123456789-0", link(CarrierId.Cdl, "1234567890"))
        assertEquals(
            "https://www.gofo.com/fr/tracking-results/?id=PK-1234567890123456789-0",
            link(CarrierId.GofoFr, "PK12345678901234567890"),
        )
        assertEquals(
            "https://ecoscooting.com/tracking/CNESP28001123400000000001",
            link(CarrierId.Ecoscooting, "ESP28001123400000000001"),
        )
        assertEquals("https://www.ninjavan.co/en-sg/tracking?id=NVSGABCD12345678", link(CarrierId.NinjaVan, "NVSGABCD12345678"))
        assertEquals("https://www.ninjaxpress.co/en-id/tracking?id=NJVTT12345678901", link(CarrierId.NinjaVan, "NJVTT12345678901"))
        assertEquals("https://www.ninjavan.co/en-my/tracking?id=NVMYSTAMP123456789", link(CarrierId.NinjaVan, "NVMYSTAMP123456789"))
    }

    @Test
    fun findsANumberOnlyTheCatalogKnowsInAShippingMessage() {
        val parsed = catalog.parse("Your order has shipped. Follow CDL12345678901 to see where it is.")
        assertEquals("CDL12345678901", parsed.trackingNumber)
        assertEquals(CarrierId.Cdl, parsed.carrier)
        assertEquals(TrackingInputMatch.Source.Text, parsed.source)
    }

    @Test
    fun linksFollowWorkingUniversalAndReturnToDirectOnRecovery() {
        var parcel = parcel("TEST1234", "Test", CarrierId.Dhl, "2026-09-10T12:00:00Z")
        val providers = listOf(
            "17TRACK" to "https://t.17track.net/fr#nums=TEST1234",
            "ParcelsApp" to "https://parcelsapp.com/fr/tracking/TEST1234",
            "Ship24" to "https://www.ship24.com/tracking?p=TEST1234",
            "UPU" to "https://globaltracktrace.ptc.post/gtt.web/Search.aspx",
            "Postal Ninja" to "https://postal.ninja/en/track",
        )
        for ((provider, expected) in providers) {
            parcel = parcel.copy(carrierData = CarrierData(trackingProvider = provider))
            val link = catalog.trackingLinks(parcel, AppLanguage.Fr).first()
            assertEquals(provider, link.name)
            assertEquals(expected, link.url)
        }
        parcel = parcel.copy(
            carrierData = CarrierData(activeTrackingCarrier = CarrierId.Ups),
            trackingUrl = "https://www.dhl.de/old-capability",
        )
        val recovered = catalog.trackingLinks(parcel, AppLanguage.Fr).first()
        assertEquals(CarrierId.Ups, recovered.carrier)
        assertTrue(recovered.url.contains("ups.com"))
        // The carrier answered the same check: its link stays, whoever supplied the result.
        parcel = parcel.copy(trackingUrl = null, carrierData = CarrierData(trackingProvider = "Ship24", carrierAnswered = true))
        val answered = catalog.trackingLinks(parcel, AppLanguage.Fr)
        assertEquals(listOf(CarrierId.Dhl), answered.map { it.carrier })
        assertFalse(answered[0].url.contains("ship24"))
    }

    @Test
    fun universalUsesLocalNumberAndKeepsOriginLink() {
        val parcel = parcel(
            "ORIGIN1234", "Test", CarrierId.Dhl, "2026-09-10T12:00:00Z",
            carrierData = CarrierData(
                activeTrackingCarrier = CarrierId.SwissPost, activeTrackingNumber = "LOCAL1234",
                originalCarrier = CarrierId.Dhl, originalTrackingNumber = "ORIGIN1234", trackingProvider = "Ship24",
            ),
        )
        val links = catalog.trackingLinks(parcel, AppLanguage.Fr)
        assertEquals(listOf("Ship24", "DHL"), links.map { it.name })
        assertEquals(listOf(ParcelTrackingLink.Role.Active, ParcelTrackingLink.Role.History), links.map { it.role })
        assertTrue(links[0].url.contains("LOCAL1234"))
        assertTrue(links[1].url.contains("ORIGIN1234"))
    }

    @Test
    fun linkedGLSParcelKeepsOriginalIdentityAndSwissPostPrimaryLink() {
        val parcel = parcel(
            "993412345612345678", "Perfume / Surprise", CarrierId.SwissPost, "2026-09-10T07:00:00Z",
            carrierData = CarrierData(originalCarrier = CarrierId.GlsDe, originalTrackingNumber = "12345678901"),
        )
        assertEquals(CarrierId.GlsDe, parcel.displayedCarrier)
        assertEquals(CarrierId.SwissPost, parcel.activeTrackingCarrier)
        val links = catalog.trackingLinks(parcel, AppLanguage.Fr)
        assertEquals(listOf(CarrierId.SwissPost, CarrierId.GlsDe), links.map { it.carrier })
        assertEquals(listOf(ParcelTrackingLink.Role.Active, ParcelTrackingLink.Role.History), links.map { it.role })
        assertTrue(links[0].url.contains("993412345612345678"))
        assertTrue(links[1].url.contains("12345678901"))
    }

    @Test
    fun dHLHandoffUsesSwissPostURLWhileKeepingDHLHistory() {
        val parcel = parcel(
            "LF123456785DE", "Garden cable", CarrierId.Dhl, "2026-09-10T07:00:00Z",
            trackingUrl = "https://www.dhl.de/en/privatkunden/dhl-sendungsverfolgung.html?piececode=LF123456785DE",
            carrierData = CarrierData(
                activeTrackingCarrier = CarrierId.SwissPost, originalCarrier = CarrierId.Dhl,
                originalTrackingNumber = "LF123456785DE",
            ),
        )
        val links = catalog.trackingLinks(parcel, AppLanguage.En)
        assertEquals(1, parcel.trackingNumbers.size)
        assertEquals(CarrierId.Dhl, parcel.displayedCarrier)
        assertEquals(listOf(CarrierId.SwissPost, CarrierId.Dhl), links.map { it.carrier })
        assertEquals("service.post.ch", host(links[0].url))
        assertEquals("www.dhl.de", host(links[1].url))
    }

    @Test
    fun handoffUsesDifferentLocalTrackingNumber() {
        val parcel = parcel(
            "123456789011", "Perfume", CarrierId.GlsDe, "2026-09-10T07:00:00Z",
            carrierData = CarrierData(
                activeTrackingCarrier = CarrierId.SwissPost, activeTrackingNumber = "990000000000000001",
                originalCarrier = CarrierId.GlsDe, originalTrackingNumber = "123456789011",
            ),
        )
        assertEquals(listOf("990000000000000001", "123456789011"), parcel.trackingNumbers.map { it.number })
        assertEquals(listOf(CarrierId.SwissPost, CarrierId.GlsDe), parcel.trackingNumbers.map { it.carrier })
        val links = catalog.trackingLinks(parcel, AppLanguage.En)
        assertEquals(listOf(CarrierId.SwissPost, CarrierId.GlsDe), links.map { it.carrier })
        assertTrue(links[0].url.contains("990000000000000001"))
        assertFalse(links[0].url.contains("123456789011"))
        assertTrue(links[1].url.contains("123456789011"))
    }

    @Test
    fun eachCarrierPageStandsUnderItsOwnNumber() {
        // Two numbers: the delivering carrier's page under the first, the first carrier's under its own.
        val handed = parcel(
            "123456789011", "Perfume", CarrierId.GlsDe, "2026-09-10T07:00:00Z",
            carrierData = CarrierData(
                activeTrackingCarrier = CarrierId.SwissPost, activeTrackingNumber = "990000000000000001",
                originalCarrier = CarrierId.GlsDe, originalTrackingNumber = "123456789011",
            ),
        )
        val pages = ParcelTrackingLink.byNumber(catalog.trackingLinks(handed, AppLanguage.En), numbers = handed.trackingNumbers)
        assertEquals(listOf(listOf(CarrierId.SwissPost), listOf(CarrierId.GlsDe)), pages.own.map { row -> row.map { it.carrier } })
        assertTrue(pages.loose.isEmpty())

        // One number followed on two carriers' pages: the earlier carrier's has no number of its own.
        val sameNumber = parcel(
            "LF123456785DE", "Garden cable", CarrierId.Dhl, "2026-09-10T07:00:00Z",
            carrierData = CarrierData(
                activeTrackingCarrier = CarrierId.SwissPost, originalCarrier = CarrierId.Dhl,
                originalTrackingNumber = "LF123456785DE",
            ),
        )
        val shared = ParcelTrackingLink.byNumber(catalog.trackingLinks(sameNumber, AppLanguage.En), numbers = sameNumber.trackingNumbers)
        assertEquals(listOf(listOf(CarrierId.SwissPost)), shared.own.map { row -> row.map { it.carrier } })
        assertEquals(listOf(CarrierId.Dhl), shared.loose.map { it.carrier })
        assertEquals(listOf(ParcelTrackingLink.Role.History), shared.loose.map { it.role })
    }

    @Test
    fun theNamedDeliveringCarrierFollowsTheFollowedNumber() {
        // The followed carrier names who delivers before the parcel is followed there: its number comes second.
        var named = parcel(
            "XY123456789FR", "Lamp", CarrierId.Chronopost, "2026-09-10T07:00:00Z",
            carrierData = CarrierData(deliveryCarrier = CarrierId.DpdDe, deliveryTrackingNumber = "01234567890123"),
        )
        assertEquals(CarrierId.Chronopost, named.displayedCarrier)
        assertEquals(CarrierId.DpdDe, named.deliveringCarrier)
        assertEquals(listOf("XY123456789FR", "01234567890123"), named.trackingNumbers.map { it.number })
        val links = catalog.trackingLinks(named, AppLanguage.En)
        assertEquals(listOf(CarrierId.Chronopost, CarrierId.DpdDe), links.map { it.carrier })
        assertEquals(listOf(ParcelTrackingLink.Role.Active, ParcelTrackingLink.Role.Waiting), links.map { it.role })
        assertTrue(links[1].url.contains("01234567890123"))
        val pages = ParcelTrackingLink.byNumber(links, numbers = named.trackingNumbers)
        assertEquals(listOf(listOf(CarrierId.Chronopost), listOf(CarrierId.DpdDe)), pages.own.map { row -> row.map { it.carrier } })
        assertTrue(pages.loose.isEmpty())

        // A parcel link brings both.
        val shared = Parcel(
            shared = PublicPackage(
                id = named.id, trackingNumber = named.trackingNumber, label = "", carrier = CarrierId.Chronopost,
                createdAt = named.createdAt, syncStatus = SyncStatus.Ok,
                carrierData = PublicPackageCarrierData(deliveryCarrier = CarrierId.DpdDe, deliveryTrackingNumber = "01234567890123"),
                notificationsMuted = false, trackingEvents = emptyList(),
            ),
        )
        assertEquals(CarrierId.DpdDe, shared.deliveringCarrier)
        assertEquals(listOf("XY123456789FR", "01234567890123"), shared.trackingNumbers.map { it.number })

        // Named without a number of its own: who delivers, and no second number.
        named = named.copy(carrierData = named.carrierData?.copy(deliveryTrackingNumber = null))
        assertEquals(CarrierId.DpdDe, named.deliveringCarrier)
        assertEquals(1, named.trackingNumbers.size)
        assertEquals(1, catalog.trackingLinks(named, AppLanguage.En).size)

        // The carrier shown, or one the catalog doesn't know, delivers nothing new.
        named = named.copy(carrierData = named.carrierData?.copy(deliveryCarrier = CarrierId.Chronopost))
        assertNull(named.deliveringCarrier)
        named = named.copy(carrierData = named.carrierData?.copy(deliveryCarrier = CarrierId("nobody")))
        assertNull(named.deliveringCarrier)

        // Once handed over, the carrier followed there delivers.
        named = named.copy(
            carrierData = CarrierData(
                activeTrackingCarrier = CarrierId.DpdDe, activeTrackingNumber = "01234567890123",
                deliveryCarrier = CarrierId.GlsDe, originalCarrier = CarrierId.Chronopost, originalTrackingNumber = "XY123456789FR",
            ),
        )
        assertEquals(CarrierId.DpdDe, named.deliveringCarrier)
        assertEquals(listOf("01234567890123", "XY123456789FR"), named.trackingNumbers.map { it.number })
    }

    @Test
    fun theNumberTheCarrierTracksTheParcelByComesLast() {
        // A reference the carrier's search resolved to a parcel: its own number follows the one typed, its page doesn't.
        var reference = parcel(
            "00000000000001", "Shoes", CarrierId.HermesDe, "2026-09-10T07:00:00Z",
            carrierData = CarrierData(canonicalTrackingNumber = "H0000000000001"),
        )
        assertEquals(listOf("00000000000001", "H0000000000001"), reference.trackingNumbers.map { it.number })
        assertEquals(listOf(false, true), reference.trackingNumbers.map { it.tracksAs })
        val localizer = Localizer()
        val originalLanguage = localizer.language
        try {
            localizer.language = AppLanguage.En
            assertEquals(
                listOf("Tracking number", "Hermes Germany tracks this as"),
                reference.trackingNumbers.map { reference.trackingNumberLabel(it, localizer = localizer) },
            )
        } finally {
            localizer.language = originalLanguage
        }
        val pages = ParcelTrackingLink.byNumber(catalog.trackingLinks(reference, AppLanguage.En), numbers = reference.trackingNumbers)
        assertEquals(listOf(1, 0), pages.own.map { it.size })

        // The same number written differently is not repeated, and a parcel link brings it too.
        reference = reference.copy(carrierData = reference.carrierData?.copy(canonicalTrackingNumber = "0000 0000 0000 01"))
        assertEquals(1, reference.trackingNumbers.size)
        val shared = Parcel(
            shared = PublicPackage(
                id = reference.id, trackingNumber = reference.trackingNumber, label = "", carrier = CarrierId.HermesDe,
                createdAt = reference.createdAt, syncStatus = SyncStatus.Ok,
                carrierData = PublicPackageCarrierData(canonicalTrackingNumber = "H0000000000001"), notificationsMuted = false,
                trackingEvents = emptyList(),
            ),
        )
        assertEquals(listOf("00000000000001", "H0000000000001"), shared.trackingNumbers.map { it.number })
    }

    @Test
    fun dHLEcommerceDetection() {
        assertEquals(CarrierMatch.Confidence.Low, catalog.detect("33870000000000001").confidence)
        assertEquals("dhl-ecommerce", catalog.detect("GM1234567890123456").carrier.rawValue)
        assertEquals(
            "dhl-ecommerce",
            catalog.parse("https://www.dhl.com/ch-en/home/tracking.html?tracking-id=33870000000000001").carrier.rawValue,
        )
        assertEquals("dhl-ecommerce", catalog.parse("https://ecommerceportal.dhl.com/track/?tracking-id=ABC123456").carrier.rawValue)
        assertEquals(CarrierId.Dhl, catalog.parse("https://www.dhl.com/ch-en/home/tracking.html?tracking-id=LF123456785DE").carrier)
        assertTrue(catalog.tracksAutomatically(CarrierId("dhl-ecommerce")))
    }

    @Test
    fun expandedCarriersAndHiddenUniversalLookup() {
        for (raw in listOf("hermes-de", "gls-de", "delivengo")) {
            val carrier = CarrierId(raw)
            assertTrue(catalog.tracksAutomatically(carrier))
        }
        assertTrue(catalog.tracksAutomatically(CarrierId.Unknown))
        assertEquals(
            "hermes-de",
            catalog.parse("https://www.myhermes.de/empfangen/sendungsverfolgung/sendungsinformation#H1234567890123456789").carrier.rawValue,
        )
        for ((country, carrier) in listOf("DE" to "gls-de", "FR" to "gls-fr", "CH" to "gls-ch")) {
            assertEquals(carrier, catalog.parse("https://gls-group.eu/$country/en/parcel-tracking?match=12345678901").carrier.rawValue)
        }
        assertEquals(CarrierId.Ups, catalog.parse("https://t.17track.net/en#nums=1Z999AA10123456784").carrier)
        assertEquals(CarrierId.Unknown, catalog.parse("https://parcelsapp.com/fr/tracking/ZZ12345678900").carrier)
        assertEquals("ZZ12345678900", catalog.parse("https://t.17track.net/en#nums=ZZ12345678900").trackingNumber)
    }

    @Test
    fun offersCChezVousForItsSharedOrderShape() {
        assertEquals(listOf(CarrierId.CChezVous), catalog.detect("FGRC45BKLM").candidates)
    }

    @Test
    fun detectsHighConfidenceCarriers() {
        assertEquals(CarrierId.Ups, catalog.detect("1Z999AA10123456784").carrier)
        assertEquals(CarrierId.Quickpac, catalog.detect("443412345678901234").carrier)
        assertEquals(CarrierId.SwissPost, catalog.detect("RA123456785CH").carrier)
        assertEquals(CarrierId.DpdFr, catalog.detect("250123456789012").carrier)
        assertEquals(CarrierId.RelaisColis, catalog.detect("CC200000000401").carrier)
        assertEquals(CarrierId.Asendia, catalog.detect("ASE12345678").carrier)
        assertEquals(CarrierId.AmazonLogistics, catalog.detect("FR1234567890").carrier)
        assertEquals("nacex", catalog.detect("2103/11207088").carrier.rawValue)
        assertEquals("nacex", catalog.parse("2103/11207088").carrier.rawValue)
        assertEquals("2103/11207088", catalog.parse("Tracking: 2103/11207088").trackingNumber)
    }

    @Test
    fun keepsAmbiguousCarrierForConfirmation() {
        val result = catalog.detect("12345678901234")
        assertEquals(CarrierId.Unknown, result.carrier)
        assertEquals(CarrierMatch.Confidence.Low, result.confidence)
        assertTrue(result.candidates.contains(CarrierId.Dpd))

        for (value in listOf("AB12CD34", "36631000001", "99112233445575012")) {
            val frenchResult = catalog.detect(value)
            assertEquals(CarrierId.Unknown, frenchResult.carrier)
            assertEquals(CarrierMatch.Confidence.Low, frenchResult.confidence)
        }
        assertEquals(CarrierMatch.Confidence.Low, catalog.detect("99112233445500000").confidence)
    }

    @Test
    fun recognitionAsksOnlyAboutASettledAmbiguousNumber() {
        val number = "12345678901"
        val ambiguous = catalog.parse(number)
        val asked = catalog.recognitionCandidates(number)
        assertEquals(CarrierMatch.Confidence.Low, ambiguous.confidence)
        assertEquals(CarrierId.Unknown, ambiguous.carrier)
        // The scraper may add a network to the shape; the known ones still asked keep their order.
        val known = listOf(CarrierId.GlsDe, CarrierId.GlsCh, CarrierId.Postlogistics)
        assertTrue(asked.contains(CarrierId.GlsDe))
        assertEquals(known.filter { it in asked }, asked.filter { it in known })
        assertTrue(CarrierRecognition.applies(ambiguous, amazon = false, demo = false))
        assertFalse(CarrierRecognition.applies(ambiguous, amazon = false, demo = true))
        assertFalse(CarrierRecognition.applies(ambiguous, amazon = true, demo = false))
        assertFalse(CarrierRecognition.applies(catalog.parse("1Z999AA10123456784"), amazon = false, demo = false))
        assertEquals(emptyList<CarrierId>(), catalog.recognitionCandidates("1Z999AA10123456784"))

        var recognition = CarrierRecognition()
        // Typing alone never asks the carriers.
        assertNull(recognition.request(number, applies = true))
        assertEquals(CarrierRecognition.Status.Idle, recognition.status(number, applies = true))

        recognition = recognition.copy(settledNumber = number)
        assertEquals(number, recognition.request(number, applies = true))
        // The server decides which carriers to ask; the pending state stays generic.
        assertEquals(CarrierRecognition.Status.Asking(emptyList()), recognition.status(number, applies = true))
        assertNull(recognition.request(number, applies = false))
        assertEquals(CarrierRecognition.Status.Idle, recognition.status(number, applies = false))
        // Editing the number drops the request until it settles again.
        assertNull(recognition.request("1234567890", applies = true))
        assertEquals(CarrierRecognition.Status.Idle, recognition.status("1234567890", applies = true))
        assertNull(CarrierRecognition(settledNumber = "").request("", applies = true))
    }

    @Test
    fun sixDigitsAreAPickupCodeNobodyIsAskedAbout() {
        assertTrue(CarrierCatalog.isPickupCode(CarrierCatalog.normalize("204 617")))
        assertFalse(CarrierCatalog.isPickupCode("20461"))
        assertFalse(CarrierCatalog.isPickupCode("2046170"))
        assertFalse(CarrierCatalog.isPickupCode("ZZ4617"))
        assertFalse(CarrierRecognition.applies(catalog.parse("204617"), amazon = false, demo = false))
    }

    @Test
    fun aRefusalIsAskedOnceForACarrierAndANumberTheServerTakes() = runTest {
        assertNull(NumberRefusals.question(carrier = null, trackingNumber = "SYNTH12345", demo = false))
        assertNull(NumberRefusals.question(carrier = CarrierId.Unknown, trackingNumber = "SYNTH12345", demo = false))
        assertNull(NumberRefusals.question(carrier = CarrierId.NzCouriers, trackingNumber = "SYNTH12345", demo = true))
        assertNull(NumberRefusals.question(carrier = CarrierId.NzCouriers, trackingNumber = "SYN", demo = false))
        assertNull(NumberRefusals.question(carrier = CarrierId.NzCouriers, trackingNumber = "SYNTH-12345", demo = false))
        val question = NumberRefusals.question(carrier = CarrierId.NzCouriers, trackingNumber = "SYNTHREFUSAL01", demo = false)
        assertNotNull(question)
        assertEquals(NumberRefusalRequest(trackingNumber = "SYNTHREFUSAL01", carrier = CarrierId.NzCouriers), question)

        var asked = 0
        val refuses = NumberRefusals.refuses(question!!) { asked += 1; true }
        assertTrue(refuses)
        val again = NumberRefusals.refuses(question) { asked += 1; false }
        assertTrue(again)
        assertEquals(1, asked)
        // Nothing is refused when the server cannot answer, and the next sight asks again.
        val other = NumberRefusalRequest(trackingNumber = "SYNTHREFUSAL02", carrier = CarrierId.NzCouriers)
        val failed = NumberRefusals.refuses(other) { throw IOException("Service failed: 503") }
        assertFalse(failed)
        val answered = NumberRefusals.refuses(other) { true }
        assertTrue(answered)
    }

    @Test
    fun recognitionAsksDirectCandidatesForGenericPostalNumbers() {
        val number = "RR123456785FI"
        val input = catalog.parse(number)
        assertEquals(CarrierId.InternationalPost, input.carrier)
        assertEquals(CarrierMatch.Confidence.High, input.confidence)
        val finnish = catalog.recognitionCandidates(number)
        assertEquals(CarrierId.Posti, finnish.firstOrNull())
        assertTrue(finnish.contains(CarrierId.Chronopost))
        val generic = catalog.recognitionCandidates("RA123456785IR")
        assertTrue(generic.contains(CarrierId.Chronopost))
        assertFalse(generic.contains(CarrierId.Posti))
        assertFalse(catalog.recognitionCandidates("RR123456789FI").contains(CarrierId.Posti))
        assertTrue(CarrierRecognition.applies(input, amazon = false, demo = false))
        assertFalse(CarrierRecognition.applies(input, amazon = false, demo = true))
        assertFalse(CarrierRecognition.applies(input, amazon = true, demo = false))

        var recognition = CarrierRecognition(settledNumber = number)
        val asked = listOf(CarrierId.Posti, CarrierId.Chronopost)
        assertEquals(number, recognition.request(number, applies = true))
        assertEquals(CarrierRecognition.Status.Asking(emptyList()), recognition.status(number, applies = true))
        recognition = recognition.copy(
            answer = CarrierDetectionResponse(trackingNumber = number, carrier = CarrierId.InternationalPost, asked = asked),
        )
        assertEquals(CarrierRecognition.Status.NotFound(asked), recognition.status(number, applies = true))
        recognition = recognition.copy(answer = CarrierDetectionResponse(trackingNumber = number, carrier = CarrierId.Posti, asked = asked))
        assertEquals(CarrierRecognition.Status.Recognized(CarrierId.Posti), recognition.status(number, applies = true))
    }

    @Test
    fun recognitionAnswersResolveAChoiceOrSayWhatHappened() {
        val number = "12345678901"
        val asked = listOf(CarrierId.GlsCh, CarrierId.GlsDe)
        var recognition = CarrierRecognition(settledNumber = number)

        recognition = recognition.copy(answer = CarrierDetectionResponse(trackingNumber = number, carrier = CarrierId.GlsCh, asked = asked))
        assertEquals(CarrierRecognition.Status.Recognized(CarrierId.GlsCh), recognition.status(number, applies = true))
        assertEquals(CarrierRecognition.Status.Idle, recognition.status(number, applies = false))
        // One answer per settled number: returning to it shows the answer without asking again.
        assertNull(recognition.request(number, applies = true))
        // An answer for another number never leaks into the current one.
        assertEquals(CarrierRecognition.Status.Idle, recognition.status("12345678902", applies = true))
        // The recognized carrier brings its required postcode, which then gates Add.
        val postcode = catalog.requirements(CarrierId.GlsCh, trackingNumber = number).first()
        assertEquals(CarrierRequirement.Field.DpdPostcode, postcode.field)
        assertFalse(postcode.isOptional)

        recognition = recognition.copy(
            answer = CarrierDetectionResponse(
                trackingNumber = "06080000000002", carrier = CarrierId.Unknown, recognized = listOf(CarrierId.Dpd, CarrierId.HermesDe),
            ),
        )
        assertEquals(
            CarrierRecognition.Status.Several(listOf(CarrierId.Dpd, CarrierId.HermesDe)),
            recognition.status("06080000000002", applies = true),
        )
        assertEquals(CarrierRecognition.Status.Asking(emptyList()), recognition.status(number, applies = true))

        // Every carrier said no, and every carrier failing, are told apart; one failing is still a no.
        recognition = recognition.copy(answer = CarrierDetectionResponse(trackingNumber = number, carrier = CarrierId.Unknown, asked = asked))
        assertEquals(CarrierRecognition.Status.NotFound(asked), recognition.status(number, applies = true))
        recognition = recognition.copy(
            answer = CarrierDetectionResponse(
                trackingNumber = number, carrier = CarrierId.Unknown, asked = asked, unanswered = listOf(CarrierId.GlsDe),
            ),
        )
        assertEquals(CarrierRecognition.Status.NotFound(asked), recognition.status(number, applies = true))
        recognition = recognition.copy(
            answer = CarrierDetectionResponse(trackingNumber = number, carrier = CarrierId.Unknown, asked = asked, unanswered = asked),
        )
        assertEquals(CarrierRecognition.Status.Failed(asked), recognition.status(number, applies = true))
        // Tracking services that answered give the status, whatever the carriers said.
        recognition = recognition.copy(
            answer = CarrierDetectionResponse(
                trackingNumber = number, carrier = CarrierId.Unknown, asked = asked,
                providers = listOf(
                    TrackingProviderCheck(provider = "Ship24", outcome = TrackingProviderCheckOutcome.NoHistory),
                    TrackingProviderCheck(provider = "ParcelsApp", outcome = TrackingProviderCheckOutcome.Unavailable),
                ),
            ),
        )
        assertEquals(CarrierRecognition.Status.NotFound(asked, services = true), recognition.status(number, applies = true))
        recognition = recognition.copy(
            answer = CarrierDetectionResponse(
                trackingNumber = number, carrier = CarrierId.Unknown, asked = asked, unanswered = listOf(CarrierId.GlsDe),
                providers = listOf(TrackingProviderCheck(provider = "Ship24", outcome = TrackingProviderCheckOutcome.Unavailable)),
            ),
        )
        assertEquals(CarrierRecognition.Status.Failed(asked, services = true), recognition.status(number, applies = true))
        // A server that asked nobody leaves the number to routing.
        recognition = recognition.copy(
            answer = CarrierDetectionResponse(trackingNumber = number, carrier = CarrierId.Unknown, recognized = listOf(CarrierId.GlsDe)),
        )
        assertEquals(CarrierRecognition.Status.Unasked, recognition.status(number, applies = true))
    }

    @Test
    fun aRecognitionRequestFailureHasNoCarrierAttemptsAndDoesNotLoop() {
        val number = "12345678901"
        var recognition = CarrierRecognition(settledNumber = number)
        assertEquals(number, recognition.request(number, applies = true))

        recognition = recognition.copy(failedNumber = number)

        assertNull(recognition.answer)
        assertNull(recognition.request(number, applies = true))
        assertEquals(CarrierRecognition.Status.Failed(emptyList()), recognition.status(number, applies = true))
        assertEquals(CarrierRecognition.Status.Idle, recognition.status(number, applies = false))
        // Settling another number still starts a new check.
        val nextNumber = "12345678902"
        recognition = recognition.copy(settledNumber = nextNumber)
        assertEquals(nextNumber, recognition.request(nextNumber, applies = true))
        assertEquals(CarrierRecognition.Status.Asking(emptyList()), recognition.status(nextNumber, applies = true))
    }

    @Test
    fun recognitionReportsOnlyTheCarriersInTheServerAnswer() {
        val number = "12345678901"
        val serverAsked = listOf(CarrierId.GlsDe, CarrierId.Postlogistics)
        var recognition = CarrierRecognition(settledNumber = number)
        assertNotEquals(catalog.discoveryCandidates(number), serverAsked)

        recognition = recognition.copy(answer = CarrierDetectionResponse(trackingNumber = number, carrier = CarrierId.Unknown, asked = serverAsked))
        assertEquals(CarrierRecognition.Status.NotFound(serverAsked), recognition.status(number, applies = true))

        recognition = recognition.copy(
            answer = CarrierDetectionResponse(
                trackingNumber = number, carrier = CarrierId.Unknown, asked = serverAsked, unanswered = serverAsked,
            ),
        )
        assertEquals(CarrierRecognition.Status.Failed(serverAsked), recognition.status(number, applies = true))
    }

    @Test
    fun carrierPickerSearchesNamesOtherNamesAndCountries() {
        // Results are found by carrier, not position: new carriers keep sorting among them.
        fun search(query: String, language: AppLanguage = AppLanguage.En, preferred: Set<CarrierId> = emptySet()) =
            CarrierPickerSearch.search(query, catalog = catalog, language = language, preferred = preferred)
        fun result(query: String, carrier: CarrierId): CarrierPickerSearch.Result? = search(query).firstOrNull { it.carrier == carrier }
        fun found(query: String, language: AppLanguage = AppLanguage.En): Set<CarrierId> =
            search(query, language = language).map { it.carrier }.toSet()
        // Every name that starts with the query comes before the rest.
        fun starting(query: String): Set<CarrierId> {
            val results = search(query)
            val count = results.takeWhile { it.highlight?.first == 0 }.size
            assertFalse(query, results.drop(count).any { it.highlight?.first == 0 })
            return results.take(count).map { it.carrier }.toSet()
        }
        assertTrue(starting("dpd").containsAll(listOf(CarrierId.DpdFr, CarrierId.DpdDe)))
        // Case, accents and punctuation are ignored; a name equal to the query sorts before longer ones.
        assertEquals(CarrierId.ColisPrive, search("colis prive").firstOrNull()?.carrier)
        assertEquals(CarrierId.FourPx, search("4px").firstOrNull()?.carrier)
        for (carrier in listOf(CarrierId.JAndTCargo, CarrierId.JAndT)) {
            assertNotNull(carrier.rawValue, result("jt", carrier))
            assertNull(carrier.rawValue, result("jt", carrier)?.alias)
        }
        val hugger = result("hugger", CarrierId.SwissPostCargo)
        assertEquals("Hugger", hugger?.alias)
        assertNull(hugger?.highlight)
        assertEquals("Die Post", result("die post", CarrierId.SwissPost)?.alias)
        assertEquals("Hermes UK", result("hermes uk", CarrierId.EvriUk)?.alias)
        // The match is marked at the start of the name or of a later word, which ranks after.
        assertTrue(starting("colis").containsAll(listOf(CarrierId.ColisPrive, CarrierId.Colisweb)))
        assertEquals(0 until 5, result("colis", CarrierId.Colisweb)?.highlight)
        assertEquals(11 until 16, result("colis", CarrierId.LaPoste)?.highlight)
        assertEquals(7 until 12, result("colis", CarrierId.RelaisColis)?.highlight)
        assertTrue(found("italy").containsAll(listOf(CarrierId.Brt, CarrierId.PosteItaliane, CarrierId.Inpost)))
        // Countries are searched in the reader's language and in English.
        assertTrue(found("italien").intersect(setOf(CarrierId.Brt, CarrierId.PosteItaliane)).isEmpty())
        assertTrue(found("italien", language = AppLanguage.De).containsAll(listOf(CarrierId.Brt, CarrierId.PosteItaliane)))
        assertNotEquals(CarrierId.GlsCh, search("gls").firstOrNull()?.carrier)
        assertEquals(CarrierId.GlsCh, search("gls", preferred = setOf(CarrierId.GlsCh)).firstOrNull()?.carrier)
        assertEquals(emptyList<CarrierPickerSearch.Result>(), search("zzqx"))
    }

    @Test
    fun carrierPickerSectionsAndLines() {
        val sections = CarrierPickerSearch.letterSections(catalog = catalog, language = AppLanguage.En)
        assertEquals(catalog.selectableCarriers.size, sections.flatMap { it.carriers }.size)
        assertEquals("A", sections.firstOrNull()?.letter)
        assertEquals("#", sections.lastOrNull()?.letter)
        val numbered = listOf(CarrierId.FourPx, CarrierId.FivePost, CarrierId.SeventeenFeia, CarrierId.NinetyNineMinutos)
        assertEquals(numbered, sections.lastOrNull()?.carriers?.filter { it in numbered })
        val name = { code: String -> TrackingLocation.countryName(code, language = AppLanguage.En) }
        assertEquals("Switzerland · Liechtenstein", CarrierPickerSearch.countryLine(listOf("CH", "LI"), name = name))
        assertEquals("France · Belgium +3", CarrierPickerSearch.countryLine(listOf("FR", "BE", "ES", "LU", "PT"), name = name))
        assertEquals("", CarrierPickerSearch.countryLine(catalog.info(CarrierId.AmazonLogistics).countries ?: emptyList(), name = name))
        assertEquals("", CarrierPickerSearch.countryLine(emptyList(), name = name))
        val parcels = listOf(
            "dpd" to "2026-09-01", "swiss-post" to "2026-09-03", "unknown" to "2026-09-04",
            "dpd" to "2026-09-02", "planzer" to "2026-08-01", "ups" to "2026-07-01",
        ).map { (carrier, day) -> parcel("TEST", "", CarrierId(carrier), "${day}T00:00:00Z") }
        assertEquals(
            listOf(CarrierId.SwissPost, CarrierId.Dpd, CarrierId.Planzer),
            CarrierPickerSearch.usedCarriers(parcels, catalog = catalog),
        )
    }

    @Test
    fun recognisesDutchPostAndExplainsGenericPostalTracking() {
        val detected = catalog.detect("LX123456785NL")
        assertEquals(CarrierId.SpringGDS, detected.carrier)
        assertEquals(CarrierMatch.Confidence.High, detected.confidence)
        assertEquals(listOf(CarrierId.SpringGDS), detected.candidates)
        assertEquals(CarrierId.SpringGDS, catalog.detect("lx 123.456-785 nl").carrier)
        assertEquals(CarrierId.Unknown, catalog.detect("LX123456789NL").carrier)
        assertEquals(CarrierId.InternationalPost, catalog.detect("RA123456785IR").carrier)
        assertTrue(catalog.tracksAutomatically(CarrierId.SpringGDS))
        assertEquals("PostNL", catalog.info(CarrierId.SpringGDS).displayName)
        assertEquals("add.autoSync", catalog.trackingHintKey(CarrierId.InternationalPost))
        assertEquals("add.autoSync", catalog.trackingHintKey(CarrierId.Unknown))
        assertEquals("add.autoSync", catalog.trackingHintKey(CarrierId.Dhl))
        for (input in listOf(
            "https://postnl.post/track?barcodes=LX123456785NL",
            "https://mailingtechnology.com/tracking/?tn=LX123456785NL",
            "https://postnl.post/details/LX123456785NL",
            "https://postnl.post/tracktrace?B=LX123456785NL",
            "Your Myprotein shipment: LX123456785NL",
        )) {
            assertEquals(CarrierId.SpringGDS, catalog.parse(input).carrier)
            assertEquals("LX123456785NL", catalog.parse(input).trackingNumber)
        }
    }

    @Test
    fun postNLTrackingLinkRepairsObsoleteSavedRoute() {
        var parcel = parcel("LX123456785NL", "Postal shipment", CarrierId.SpringGDS, "2026-09-07T13:00:00Z")
        for (savedUrl in listOf(null, "https://postnl.post/details/LX123456785NL")) {
            parcel = parcel.copy(trackingUrl = savedUrl)
            val link = catalog.trackingLinks(parcel, AppLanguage.En).first()
            assertEquals("PostNL", link.name)
            assertEquals("https://postnl.post/track?barcodes=LX123456785NL", link.url)
        }
        parcel = parcel.copy(trackingUrl = "https://mailingtechnology.com/tracking/?tn=LX123456785NL")
        assertEquals(parcel.trackingUrl, catalog.trackingLinks(parcel, AppLanguage.En).first().url)
    }

    @Test
    fun recognisesDHLGermanPostalNumbersAndLinks() {
        for (number in listOf("LF123456785DE", "LX123456785DE", "CY123456785DE")) {
            val result = catalog.detect(number)
            assertEquals(CarrierId.Dhl, result.carrier)
            assertEquals(CarrierMatch.Confidence.High, result.confidence)
            assertEquals(listOf(CarrierId.Dhl), result.candidates)
        }
        assertEquals(CarrierId.Dhl, catalog.detect("lf 123.456-785 de").carrier)
        assertEquals(CarrierId.Unknown, catalog.detect("LF123456789DE").carrier)
        assertNotEquals(CarrierId.Dhl, catalog.detect("LF123456785US").carrier)
        assertTrue(catalog.tracksAutomatically(CarrierId.Dhl))
        for (input in listOf(
            "https://www.dhl.de/en/privatkunden/dhl-sendungsverfolgung.html?piececode=LF123456785DE",
            "https://nolp.dhl.de/nextt-online-public/en/search?piececode=LF123456785DE",
            "https://www.deutschepost.de/de/s/sendungsverfolgung.html?piececode=LF123456785DE",
            "Your shipment: LF123456785DE",
        )) {
            assertEquals(CarrierId.Dhl, catalog.parse(input).carrier)
            assertEquals("LF123456785DE", catalog.parse(input).trackingNumber)
        }
        val numeric = catalog.parse("https://www.dhl.de/int-verfolgen/?piececode=1234567890")
        assertEquals(CarrierId.Dhl, numeric.carrier)
        assertEquals(TrackingInputMatch.Source.Link, numeric.source)
    }

    @Test
    fun amazonLogisticsRequiresAnAccountEvenWithACarrierOverride() {
        assertEquals(CarrierId.AmazonLogistics, catalog.parse("Your parcel: FR3000000001").carrier)
        assertTrue(catalog.requiresAmazonAccount(CarrierId.Unknown, trackingNumber = "fr 3000-000001"))
        assertTrue(catalog.requiresAmazonAccount(CarrierId.Ups, trackingNumber = "FR3000000001"))
        assertTrue(catalog.requiresAmazonAccount(CarrierId.AmazonLogistics))
        assertFalse(catalog.requiresAmazonAccount(CarrierId.Ups, trackingNumber = "1Z999AA10123456784"))
        assertFalse(catalog.tracksAutomatically(CarrierId.AmazonLogistics))
        assertEquals("add.amazonAccount", catalog.trackingHintKey(CarrierId.AmazonLogistics))
        assertEquals("https://www.amazon.com/gp/your-account/order-history", catalog.info(CarrierId.AmazonLogistics).trackingUrlTemplate)
    }

    @Test
    fun amazonLogisticsReplacesSavedFallbackLinks() {
        var parcel = parcel("FR3000000001", "Example", CarrierId.Unknown, "2026-09-10T12:00:00Z", syncStatus = SyncStatus.Error)
        parcel = parcel.copy(carrierData = CarrierData(trackingProvider = "ParcelsApp"))
        parcel = parcel.copy(trackingUrl = "https://track.amazon.fr/tracking/FR3000000001")
        assertEquals(CarrierId.AmazonLogistics, parcel.activeTrackingCarrier)
        val link = catalog.trackingLinks(parcel, AppLanguage.Fr).first()
        assertEquals("Amazon Logistics", link.name)
        assertEquals("https://www.amazon.fr/gp/your-account/order-history", link.url)
    }

    @Test
    fun amazonOrdersOpenTheBuyersStore() {
        assertEquals("www.amazon.de", host(CarrierCatalog.amazonOrdersUrl("CH0000000001")))
        assertEquals("www.amazon.de", host(CarrierCatalog.amazonOrdersUrl("ch 000-0000001", country = "CA")))
        assertEquals("www.amazon.co.uk", host(CarrierCatalog.amazonOrdersUrl("IS0000000001")))
        assertEquals("www.amazon.com", host(CarrierCatalog.amazonOrdersUrl("SK0000000001")))
        assertEquals("www.amazon.ca", host(CarrierCatalog.amazonOrdersUrl("TBA000000000001", country = " ca ")))
        assertEquals("www.amazon.com", host(CarrierCatalog.amazonOrdersUrl("TBA000000000001", country = "FR")))
        assertEquals("www.amazon.com.mx", host(CarrierCatalog.amazonOrdersUrl("TBM000000000001", country = "CA")))
        assertEquals("www.amazon.de", host(CarrierCatalog.amazonOrdersUrl("", country = "CH")))
        assertEquals("www.amazon.ca", host(CarrierCatalog.amazonOrdersUrl("", country = "CA")))
        var parcel = parcel("TBA000000000001", "Example", CarrierId.AmazonLogistics, "2026-09-10T12:00:00Z")
        parcel = parcel.copy(carrierData = CarrierData(destinationCountry = "CA"))
        val link = catalog.trackingLinks(parcel, AppLanguage.En).first()
        assertEquals("www.amazon.ca", host(link.url))
    }

    @Test
    fun detectsInternationalAmazonAndKeepsConfirmedShippingDistinct() {
        for (prefix in listOf("FR", "DE", "BE", "UK", "GB", "IT", "ES", "NL", "AT", "IE", "PL", "SE", "PT", "CH")) {
            val number = "${prefix}0000000001"
            assertEquals(CarrierId.AmazonLogistics, catalog.parse(number).carrier)
            assertEquals(CarrierId.AmazonLogistics, catalog.parse("Your parcel: $number").carrier)
            assertTrue(catalog.requiresAmazonAccount(CarrierId.Unknown, trackingNumber = number))
            assertFalse(catalog.requiresAmazonAccount(CarrierId.AmazonShipping, trackingNumber = number))
        }
        assertEquals(CarrierId.AmazonLogistics, catalog.parse("TBA000000000001").carrier)
        assertFalse(catalog.isAmazonTrackingNumber("ZZ0000000001"))
        assertFalse(catalog.isAmazonTrackingNumber("TBA00000000001"))
        assertTrue(catalog.tracksAutomatically(CarrierId.AmazonShipping))
        assertEquals("www.amazon.com.be", host(CarrierCatalog.amazonOrdersUrl("BE0000000001")))
        assertEquals("www.amazon.co.uk", host(CarrierCatalog.amazonOrdersUrl("UK0000000001")))
        assertEquals("track.amazon.it", host(CarrierCatalog.amazonShippingUrl("IT0000000001")))
        assertEquals("track.amazon.com", host(CarrierCatalog.amazonShippingUrl("TBA000000000001")))
        assertEquals("www.amazon.com", host(CarrierCatalog.amazonOrdersUrl("TBA000000000001")))
        assertEquals("www.amazon.ca", host(CarrierCatalog.amazonOrdersUrl("tbc 000000000001")))
        assertEquals("www.amazon.com.mx", host(CarrierCatalog.amazonOrdersUrl("TBM000000000001")))
        assertEquals("track.amazon.com", host(CarrierCatalog.amazonShippingUrl("TBC000000000001")))
        val parcel = parcel("UK0000000001", "Example", CarrierId.AmazonShipping, "2026-09-10T12:00:00Z")
        assertEquals(CarrierId.AmazonShipping, parcel.activeTrackingCarrier)
        assertEquals("track.amazon.co.uk", host(catalog.trackingLinks(parcel, AppLanguage.En).firstOrNull()?.url))
    }

    @Test
    fun parsesKnownCarrierLink() {
        val parsed = catalog.parse("Track it: https://www.ups.com/track?tracknum=1Z999AA10123456784")
        assertEquals("1Z999AA10123456784", parsed.trackingNumber)
        assertEquals(CarrierId.Ups, parsed.carrier)
        assertEquals(TrackingInputMatch.Source.Link, parsed.source)
    }

    @Test
    fun takesALettersOnlyNumberOnlyWhenACarrierClaimsItsShape() {
        // Letters alone are a number in a shape a carrier's rule claims, as six are for a GLS Track ID.
        for (input in listOf("ABCDEF", " abcdef ")) {
            assertEquals(input, TrackingInputMatch.Source.Number, catalog.parse(input).source)
        }
        // A word no carrier uses stays a word, whatever a general rule makes of its length.
        for (input in listOf("ABCDE", "BONJOUR", "DELIVERED", "CONFIRMATION", "ABC DEF", "ABC-DEF", "AB.CD.EF")) {
            assertEquals(input, TrackingInputMatch.Source.None, catalog.parse(input).source)
        }
        // A word that follows a label in a message stays a word.
        assertEquals(TrackingInputMatch.Source.None, catalog.parse("Tracking number: pending").source)
        assertEquals(TrackingInputMatch.Source.None, catalog.parse("Your shipment arrives Monday").source)
    }

    @Test
    fun readsTheNumberALabelIntroduces() {
        for (text in listOf(
            "Tracking number: 12345678901",
            "Tracking number 12345678901",
            "Your tracking number is 12345678901.",
            "tracking no. 12345678901",
            "Tracking ID: 12345678901",
            "Tracking: 12345678901",
            "track 12345678901",
            "Parcel number: 12345678901",
            "Shipment tracking number: 12345678901",
            "Tracking update. Your parcel number 12345678901 leaves today",
        )) {
            val parsed = catalog.parse(text)
            assertEquals(text, "12345678901", parsed.trackingNumber)
            assertEquals(text, TrackingInputMatch.Source.Text, parsed.source)
        }
        // A label word attached to the number stays with it, and no word is taken for a number.
        assertEquals("NO123456789", catalog.parse("Tracking NO123456789").trackingNumber)
        assertEquals("notable1234", catalog.parse("tracking notable1234").trackingNumber)
        assertEquals(TrackingInputMatch.Source.None, catalog.parse("Shipment tracking: delayed").source)
    }

    @Test
    fun leavesALabelWordOutOfAShortEntry() {
        for ((text, number) in listOf(
            "Abholcode 123456" to "123456",
            "Abholcode: 123456" to "123456",
            "Pickup code 123456" to "123456",
            "Sendungsnummer 0034 0434 1234 5678" to "0034 0434 1234 5678",
            "Nr. 12345678" to "12345678",
            "Numéro 12345678" to "12345678",
            "N° de suivi 12345678" to "12345678",
            "N°12345678" to "12345678",
            "Numéro de suivi : 12345678" to "12345678",
            "numéro de colis 12345678" to "12345678",
            "Suivi: 12345678" to "12345678",
            "Trackingnummer: 12345678" to "12345678",
            "Nº de seguimiento: 12345678" to "12345678",
            "número de envío 12345678" to "12345678",
            "numero di tracciamento 12345678" to "12345678",
            "n.º de rastreio 12345678" to "12345678",
            "Numer przesyłki 12345678" to "12345678",
        )) {
            val parsed = catalog.parse(text)
            assertEquals(text, number, parsed.trackingNumber)
            assertEquals(text, TrackingInputMatch.Source.Text, parsed.source)
        }
        // No other number a label names is taken for the tracking number.
        for (text in listOf("numéro de commande 12345678", "número de pedido 12345678", "suivi 5555666677 ou 7777888899")) {
            assertEquals(text, "", catalog.parse(text).trackingNumber)
            assertEquals(text, TrackingInputMatch.Source.None, catalog.parse(text).source)
        }
        // Letters a carrier prints apart from its digits stay on the number.
        for (text in listOf("QZX 123456", "ABCD 1234567", "LP 00123456789012", "1111 2222 3333 4444", "1Z 999 AA1 01 2345 6784", "abholcode123456")) {
            val parsed = catalog.parse(text)
            assertEquals(text, text, parsed.trackingNumber)
            assertEquals(text, TrackingInputMatch.Source.Number, parsed.source)
        }
    }

    @Test
    fun readsNoSingleNumberFromTwoJoinedByAWord() {
        for (word in listOf("or", "oder", "ou", "o", "of", "lub", "OR")) {
            val text = "5555666677 $word 7777888899"
            assertEquals(text, "", catalog.parse(text).trackingNumber)
            assertEquals(text, TrackingInputMatch.Source.None, catalog.parse(text).source)
        }
    }

    @Test
    fun parsesGeodisHashLinkAndExposesFrenchCarriersInThePicker() {
        val parsed = catalog.parse("https://espace-client.geodis.com/services/destinataires/#/fr/suivi/1G123GEODIS0")
        assertEquals("1G123GEODIS0", parsed.trackingNumber)
        assertEquals(CarrierId.Geodis, parsed.carrier)
        assertEquals(TrackingInputMatch.Source.Link, parsed.source)
        for (carrier in listOf(
            CarrierId.DpdFr, CarrierId.MondialRelay, CarrierId.RelaisColis,
            CarrierId.LaPoste, CarrierId.Chronopost, CarrierId.GlsFr, CarrierId.ColisPrive, CarrierId.Geodis,
            CarrierId.SwissPostCargo, CarrierId.GlsCh, CarrierId.Colisweb, CarrierId.CChezVous,
            CarrierId.Heppner, CarrierId.Ciblex, CarrierId.Paack,
        )) {
            assertTrue(carrier.rawValue, catalog.selectableCarriers.contains(carrier))
            assertTrue(carrier.rawValue, catalog.tracksAutomatically(carrier))
        }
        assertTrue(catalog.selectableCarriers.contains(CarrierId.Asendia))
        for (carrier in listOf(CarrierId.Asendia, CarrierId.Fedex, CarrierId.Shipup)) {
            assertTrue(carrier.rawValue, catalog.tracksAutomatically(carrier))
        }
    }

    private fun postcodeRequirement(carrier: CarrierId, number: String): CarrierRequirement {
        val requirement = catalog.requirements(carrier, trackingNumber = number).firstOrNull { it.field == CarrierRequirement.Field.DpdPostcode }
        assertNotNull(requirement)
        return requirement!!
    }

    @Test
    fun countrySpecificPostcodeRequirementsNormalizeAndValidate() {
        val glsGermany = postcodeRequirement(CarrierId.GlsDe, "123456789018")
        // GLS Germany delivers abroad too: any country's postcode.
        assertTrue(glsGermany.takesAnyPostcode)
        assertTrue(glsGermany.accepts("8000"))
        assertTrue(glsGermany.accepts("01067"))
        assertTrue(glsGermany.accepts("SW1A 1AA"))
        assertFalse(glsGermany.accepts("12"))
        assertFalse(glsGermany.isOptional)
        assertFalse(glsGermany.isSatisfied(""))
        assertEquals("Unknown carrier", catalog.info(CarrierId.Unknown, language = AppLanguage.En).displayName)
        assertEquals("Transporteur inconnu", catalog.info(CarrierId.Unknown, language = AppLanguage.Fr).displayName)
        val dpd = postcodeRequirement(CarrierId.Dpd, "12345678901234")
        // DPD delivers abroad too: any country's postcode, as that country writes it.
        assertNull(dpd.placeholder)
        assertEquals(12, dpd.maxLength)
        assertEquals("SW1A 1AA", dpd.normalizedValue(" sw1a  1aa "))
        assertEquals("SW1A ", dpd.typedValue("sw1a "))
        assertEquals("SW1A ", dpd.typedValue("sw1a  "))
        assertEquals("", dpd.typedValue(" "))
        assertEquals("123456789012", dpd.typedValue("123456789012 "))
        for (postcode in listOf("8000", "75001", "SW1A 1AA", "1012 AB", "00-001", "sw1a 1aa ")) {
            assertTrue(postcode, dpd.accepts(postcode))
        }
        for (postcode in listOf("12", "ABCDE", "75001/2", "75 - 001", "1234567890123")) {
            assertFalse(postcode, dpd.accepts(postcode))
        }
        // DPD tracks without the postcode; one that is typed must still be valid.
        assertTrue(dpd.isOptional)
        assertTrue(dpd.isSatisfied(""))
        assertTrue(dpd.isSatisfied("  "))
        assertTrue(dpd.isSatisfied("8000"))
        assertFalse(dpd.isSatisfied("80"))

        // The example is the device's country, then the carrier's own.
        assertEquals("75001", catalog.postcodeExample(CarrierId.Dpd, requirement = dpd, region = "FR"))
        assertEquals("SW1A 1AA", catalog.postcodeExample(CarrierId.Dpd, requirement = dpd, region = "gb"))
        assertEquals("8000", catalog.postcodeExample(CarrierId.Dpd, requirement = dpd, region = "BR"))
        assertEquals("8000", catalog.postcodeExample(CarrierId.Dpd, requirement = dpd, region = null))
        assertTrue(dpd.startsWithNumberKeys(example = "75001"))
        assertFalse(dpd.startsWithNumberKeys(example = "SW1A 1AA"))
        for (example in CarrierCatalog.postcodeExamples.values) {
            assertTrue(example, dpd.accepts(example))
        }

        // A national network keeps its own shape and example wherever the device is.
        val dpdGermany = postcodeRequirement(CarrierId.DpdDe, "12345678901234")
        assertEquals("10115", catalog.postcodeExample(CarrierId.DpdDe, requirement = dpdGermany, region = "FR"))
        assertEquals("10115", dpdGermany.typedValue("10 115 "))
        assertFalse(dpdGermany.accepts("8000"))
        assertFalse(dpdGermany.startsWithNumberKeys(example = "10115"))

        val mondialRelay = postcodeRequirement(CarrierId.MondialRelay, "76434219")
        // Mondial Relay asks for it on eight-digit shipment numbers only, in any country's form.
        assertTrue(mondialRelay.takesAnyPostcode)
        assertNull(mondialRelay.placeholder)
        assertEquals(12, mondialRelay.maxLength)
        assertEquals("1012 AB", mondialRelay.normalizedValue(" 1012  ab "))
        assertTrue(mondialRelay.accepts("75001"))
        assertTrue(mondialRelay.accepts("8000"))
        assertFalse(mondialRelay.isOptional)
        assertFalse(mondialRelay.isSatisfied(""))
        assertTrue(catalog.requirements(CarrierId.MondialRelay, trackingNumber = "1234567890").isEmpty())

        val gls = postcodeRequirement(CarrierId.GlsCh, "993990103198")
        assertTrue(gls.accepts("8000"))
        assertFalse(gls.accepts("75001"))
        assertFalse(gls.isOptional)
        assertFalse(gls.isSatisfied(""))

        val heppner = postcodeRequirement(CarrierId.Heppner, "23456789")
        assertTrue(heppner.accepts("1201"))
        assertTrue(heppner.accepts("75001"))
        assertFalse(heppner.isOptional)
        assertFalse(heppner.isSatisfied(""))

        val paack = postcodeRequirement(CarrierId.Paack, "PAACK12345")
        assertTrue(paack.accepts("1234-567"))
        assertFalse(paack.accepts("12--345"))
        assertFalse(paack.accepts("ABC"))
        assertEquals("SW1A1AA", paack.normalizedValue("sw1a 1aa"))
    }

    @Test
    fun thePostcodeSuggestedIsTheNewestThatFitsTheCarriersOwnFirst() {
        fun parcel(carrier: CarrierId, postcode: String?, day: Int) =
            this@CarrierCatalogTests.parcel("TESTPARCEL123", "", carrier, "2026-10-0${day}T08:00:00Z", dpdPostcode = postcode)
        fun given(parcels: List<Parcel>, remembered: List<GivenPostcode> = emptyList()) =
            GivenPostcode.candidates(parcels = parcels, remembered = remembered)
        // Mondial Relay and DPD take any country's postcode; GLS Switzerland, Heppner and DPD Germany their countries'.
        val dpd = postcodeRequirement(CarrierId.Dpd, "12345678901234")
        val mondialRelay = postcodeRequirement(CarrierId.MondialRelay, "76434219")
        val gls = postcodeRequirement(CarrierId.GlsCh, "993990103198")
        val heppner = postcodeRequirement(CarrierId.Heppner, "23456789")
        val dpdGermany = postcodeRequirement(CarrierId.DpdDe, "12345678901234")

        // The carrier's own postcode wins over a newer one given to another carrier.
        val parcels = given(listOf(parcel(CarrierId.MondialRelay, "75002", 1), parcel(CarrierId.GlsCh, "8000", 2), parcel(CarrierId.Dpd, "75001", 3)))
        assertEquals("75002", mondialRelay.suggestedPostcode(CarrierId.MondialRelay, parcels))
        assertEquals("8000", gls.suggestedPostcode(CarrierId.GlsCh, parcels))
        assertEquals("75002", dpd.suggestedPostcode(CarrierId.Dpd, given(listOf(parcel(CarrierId.Dpd, "75001", 1), parcel(CarrierId.Dpd, "75002", 2)))))
        // Otherwise the newest one given to any carrier, as the field takes it.
        assertEquals("75001", heppner.suggestedPostcode(CarrierId.Heppner, parcels))
        assertEquals("75001", mondialRelay.suggestedPostcode(CarrierId.MondialRelay, given(listOf(parcel(CarrierId.Dpd, "75001", 1)))))
        assertEquals("SW1A 1AA", dpd.suggestedPostcode(CarrierId.Dpd, given(listOf(parcel(CarrierId.GlsDe, " sw1a  1aa ", 1)))))

        // One that does not fit is passed over, even the carrier's own.
        assertNull(dpdGermany.suggestedPostcode(CarrierId.DpdDe, given(listOf(parcel(CarrierId.GlsCh, "8000", 1)))))
        assertNull(gls.suggestedPostcode(CarrierId.GlsCh, given(listOf(parcel(CarrierId.MondialRelay, "75001", 1)))))
        assertEquals("8000", gls.suggestedPostcode(CarrierId.GlsCh, given(listOf(parcel(CarrierId.GlsCh, "8000", 1), parcel(CarrierId.GlsCh, "75001", 2)))))
        val abroad = given(listOf(parcel(CarrierId.Dpd, "75001", 1), parcel(CarrierId.Dpd, "8000", 2), parcel(CarrierId.Dpd, "1012 AB", 3)))
        assertEquals("1012 AB", dpd.suggestedPostcode(CarrierId.Dpd, abroad))
        assertEquals("1012 AB", mondialRelay.suggestedPostcode(CarrierId.MondialRelay, abroad))
        assertEquals("8000", gls.suggestedPostcode(CarrierId.GlsCh, abroad))
        assertEquals("75001", dpdGermany.suggestedPostcode(CarrierId.DpdDe, abroad))
        // It must fit as written: "1012 AB" is not "1012", nor "00-001" "00001", nor "75 001" "75001".
        assertNull(gls.suggestedPostcode(CarrierId.GlsCh, given(listOf(parcel(CarrierId.Dpd, "1012 AB", 1)))))
        assertNull(dpdGermany.suggestedPostcode(CarrierId.DpdDe, given(listOf(parcel(CarrierId.MondialRelay, "00-001", 1)))))
        assertNull(heppner.suggestedPostcode(CarrierId.Heppner, given(listOf(parcel(CarrierId.Dpd, "75 001", 1)))))

        assertNull(dpd.suggestedPostcode(CarrierId.Dpd, given(listOf(parcel(CarrierId.Dpd, null, 1)))))
        assertNull(dpd.suggestedPostcode(CarrierId.Dpd, emptyList()))

        // The account's parcels come first, newest first, then what this iPhone remembers, in its order.
        val remembered = listOf(GivenPostcode(carrier = CarrierId.MondialRelay, postcode = "1012 AB"), GivenPostcode(carrier = CarrierId.GlsCh, postcode = "3000"))
        val both = given(listOf(parcel(CarrierId.Dpd, "75001", 1), parcel(CarrierId.GlsCh, "8000", 2), parcel(CarrierId.Dpd, null, 3)), remembered = remembered)
        assertEquals(listOf(GivenPostcode(carrier = CarrierId.GlsCh, postcode = "8000"), GivenPostcode(carrier = CarrierId.Dpd, postcode = "75001")) + remembered, both)
        assertEquals("8000", gls.suggestedPostcode(CarrierId.GlsCh, both))
        assertEquals("8000", heppner.suggestedPostcode(CarrierId.Heppner, both))
        assertEquals("75001", dpdGermany.suggestedPostcode(CarrierId.DpdDe, both))
        assertEquals("The carrier's own, though only remembered", "1012 AB", mondialRelay.suggestedPostcode(CarrierId.MondialRelay, both))
        assertEquals("3000", gls.suggestedPostcode(CarrierId.GlsCh, given(emptyList(), remembered = remembered)))
    }

    @Test
    fun aPostcodeCanBeFromAnotherCountryThanTheCarriersOwn() {
        // PostNL's 3S labels take any country's postcode, Heppner its four countries' shapes, DPD Germany Germany's.
        val postNL = postcodeRequirement(CarrierId.SpringGDS, "3SABCD1234567")
        val heppner = postcodeRequirement(CarrierId.Heppner, "23456789")
        val dpdGermany = postcodeRequirement(CarrierId.DpdDe, "12345678901234")
        assertEquals("NL", catalog.homeCountry(CarrierId.SpringGDS))

        // Any real country, or the carrier's own. A field that takes one alone offers no choice.
        val any = catalog.postcodeCountries(CarrierId.SpringGDS, requirement = postNL)
        for (country in listOf("NL", "BE", "LU", "GB", "XK")) assertTrue(country, any.contains(country))
        for (code in listOf("EU", "EZ", "UN", "ZZ", "IC", "AC", "419")) assertFalse(code, any.contains(code))
        assertEquals(listOf("FR", "CH", "BE", "LU"), catalog.postcodeCountries(CarrierId.Heppner, requirement = heppner))
        assertEquals(listOf("DE"), catalog.postcodeCountries(CarrierId.DpdDe, requirement = dpdGermany))

        // The menu lists the carrier's own first, then the others by name in the app's language.
        val choices = catalog.postcodeCountryChoices(CarrierId.SpringGDS, requirement = postNL, language = AppLanguage.Fr)
        assertEquals(listOf("NL"), choices.own)
        assertEquals(any.toSet(), (choices.own + choices.others).toSet())
        val names = choices.others.map { TrackingLocation.countryName(it, language = AppLanguage.Fr) }
        assertEquals(names.sortedWith(Collator.getInstance(AppLanguage.Fr.locale)), names)
        assertEquals(emptyList<String>(), catalog.postcodeCountryChoices(CarrierId.Heppner, requirement = heppner, language = AppLanguage.Fr).others)

        // A postcode from the carrier's home keeps its field; one from abroad takes any country's shape and that
        // country's example, or none rather than the carrier's.
        assertEquals(postNL, catalog.postcodeRequirement(postNL, CarrierId.SpringGDS, country = "NL"))
        assertFalse(postNL.accepts("1000"))
        assertEquals("1234 AB", catalog.postcodeExample(CarrierId.SpringGDS, requirement = postNL, region = "NL"))
        val belgian = catalog.postcodeRequirement(postNL, CarrierId.SpringGDS, country = "BE")
        for (postcode in listOf("1000", "1012 AB", "L-1009")) assertTrue(postcode, belgian.accepts(postcode))
        for (postcode in listOf("12", "ABCDE", "1234567890123")) assertFalse(postcode, belgian.accepts(postcode))
        assertEquals("L-1009", belgian.normalizedValue(" l-1009 "))
        assertEquals("text", belgian.inputMode)
        assertTrue(belgian.isOptional)
        assertEquals("1000", catalog.postcodeExample(CarrierId.SpringGDS, requirement = belgian, region = "BE"))
        val emirati = catalog.postcodeRequirement(postNL, CarrierId.SpringGDS, country = "AE")
        assertNull(catalog.postcodeExample(CarrierId.SpringGDS, requirement = emirati, region = "AE"))
        assertEquals(heppner, catalog.postcodeRequirement(heppner, CarrierId.Heppner, country = "BE"))

        // Only a postcode from abroad goes with its country: one from home goes as it always has.
        assertNull(catalog.sentPostcodeCountry("NL", postcode = "1012 AB", carrier = CarrierId.SpringGDS))
        assertEquals("BE", catalog.sentPostcodeCountry("BE", postcode = "1000", carrier = CarrierId.SpringGDS))
        assertNull(catalog.sentPostcodeCountry("BE", postcode = " ", carrier = CarrierId.SpringGDS))
        assertNull(catalog.sentPostcodeCountry("BE", postcode = null, carrier = CarrierId.SpringGDS))

        // A new postcode starts on the parcel's destination, then the device's region, among those the field takes,
        // else on the carrier's home.
        assertEquals("BE", catalog.initialPostcodeCountry(CarrierId.SpringGDS, requirement = postNL, region = "BE"))
        assertEquals("NL", catalog.initialPostcodeCountry(CarrierId.SpringGDS, requirement = postNL, region = null))
        assertEquals("BE", catalog.initialPostcodeCountry(CarrierId.Heppner, requirement = heppner, region = "be"))
        assertEquals("FR", catalog.initialPostcodeCountry(CarrierId.Heppner, requirement = heppner, region = "DE"))
        assertEquals("DE", catalog.initialPostcodeCountry(CarrierId.DpdDe, requirement = dpdGermany, region = "FR"))
        fun parcel(carrier: CarrierId, postcode: String?, data: CarrierData?) =
            this@CarrierCatalogTests.parcel("3SABCD1234567", "", carrier, "2026-10-01T08:00:00Z", dpdPostcode = postcode, carrierData = data)
        val abroad = CarrierData(postcodeCountry = PostcodeCountry(postcode = "1000", country = "BE"), destinationCountry = "LU")
        fun initial(parcel: Parcel): String? =
            catalog.initialPostcodeCountry(CarrierId.SpringGDS, requirement = postNL, parcel = parcel, region = "FR")
        assertEquals("LU", initial(parcel(CarrierId.Unknown, postcode = null, data = abroad)))
        assertEquals("Another carrier's postcode", "LU", initial(parcel(CarrierId.Dpd, postcode = "1000", data = abroad)))
        // The parcel's own postcode keeps the country saved with it, or the carrier's home when none was.
        assertEquals("BE", initial(parcel(CarrierId.SpringGDS, postcode = "1000", data = abroad)))
        assertEquals("A postcode changed since", "NL", initial(parcel(CarrierId.SpringGDS, postcode = "1012 AB", data = abroad)))
        assertEquals("NL", initial(parcel(CarrierId.SpringGDS, postcode = "1012 AB", data = null)))
    }

    @Test
    fun buildsUsableCarrierLinks() {
        var parcel = parcel("4TZKO15679059600", "Furniture", CarrierId.CChezVous, "2026-08-30T13:00:00Z")
        assertEquals(
            "https://www.cchezvous.fr/suivi-colis/4TZKO156790--59600",
            catalog.trackingLinks(parcel, AppLanguage.Fr).first().url,
        )

        parcel = parcel.copy(trackingNumber = "PAACK12345", carrier = CarrierId.Paack, dpdPostcode = "75001")
        assertEquals(
            "https://mydeliveries.paack.app/tracking?tracking_number=PAACK12345",
            catalog.trackingLinks(parcel, AppLanguage.Fr).first().url,
        )
    }

    @Test
    fun externalTrackingSitesUseSupportedLanguages() {
        var parcel = parcel("993412345612345678", "Parcel", CarrierId.SwissPost, "2026-09-07T13:00:00Z", syncStatus = SyncStatus.Unsupported)
        for (language in listOf(AppLanguage.Es, AppLanguage.Pt, AppLanguage.Pl)) {
            val url = catalog.trackingLinks(parcel, language).first().url
            assertEquals("en", query(url, "lang"))
        }
        parcel = parcel.copy(
            carrier = CarrierId.Unknown,
            trackingNumber = "ZZ12345678900",
            trackingUrl = "https://parcelsapp.com/fr/tracking/ZZ12345678900",
        )
        for ((language, name) in listOf(
            AppLanguage.Es to "Transportista desconocido",
            AppLanguage.Pt to "Transportadora desconhecida",
            AppLanguage.Pl to "Nieznany przewoźnik",
        )) {
            assertEquals(name, catalog.info(CarrierId.Unknown, language = language).displayName)
            val url = catalog.trackingLinks(parcel, language).first().url
            assertEquals("https://parcelsapp.com/${if (language == AppLanguage.Pl) "en" else language.rawValue}/tracking/ZZ12345678900", url)
        }
    }

    @Test
    fun unknownPostalCarrierUsesLocalized17TrackInsteadOfLegacySwissPostLink() {
        var parcel = parcel("RA123456785IR", "Postal shipment", CarrierId.InternationalPost, "2026-09-07T13:00:00Z", syncStatus = SyncStatus.Unsupported)
        val names = mapOf(
            AppLanguage.En to "Unknown postal carrier", AppLanguage.De to "Postanbieter unbekannt",
            AppLanguage.Fr to "Transporteur postal inconnu", AppLanguage.It to "Corriere postale sconosciuto",
            AppLanguage.Es to "Operador postal desconocido", AppLanguage.Pt to "Operador postal desconhecido",
            AppLanguage.Pl to "Nieznany operator pocztowy",
        )
        for (language in AppLanguage.entries) {
            for (savedUrl in listOf(null, "https://service.post.ch/ekp-web/ui/entry/search/RA123456785IR")) {
                parcel = parcel.copy(trackingUrl = savedUrl)
                val link = catalog.trackingLinks(parcel, language).first()
                assertEquals(names[language], catalog.info(CarrierId.InternationalPost, language = language).displayName)
                assertEquals("17TRACK", link.name)
                assertEquals("https://t.17track.net/${language.rawValue}#nums=RA123456785IR", link.url)
                assertEquals(CarrierId.InternationalPost, link.carrier)
            }
        }
        assertEquals("Unknown postal carrier", catalog.info(CarrierId.InternationalPost).displayName)
        assertTrue(catalog.tracksAutomatically(CarrierId.InternationalPost))

        parcel = parcel.copy(carrier = CarrierId.SwissPost, trackingNumber = "RA123456785CH", trackingUrl = null)
        val swissPost = catalog.trackingLinks(parcel, AppLanguage.Fr).first()
        assertEquals("Swiss Post", swissPost.name)
        assertEquals("https://service.post.ch/ekp-web/ui/entry/search/RA123456785CH?lang=fr", swissPost.url)

        parcel = parcel.copy(
            carrier = CarrierId.Dhl,
            trackingNumber = "LF123456785DE",
            trackingUrl = "https://www.dhl.de/int-verfolgen/?piececode=LF123456785DE",
        )
        val dhl = catalog.trackingLinks(parcel, AppLanguage.Fr).first()
        assertEquals("DHL", dhl.name)
        assertEquals(parcel.trackingUrl, dhl.url)
    }

    @Test
    fun planzerLinkKeepsQuickpacIdentityFor44Barcode() {
        val parsed = catalog.parse("https://tracking.app.planzer.ch/delivery/info?deliveryNumber=443412345678901234")
        assertEquals(CarrierId.Quickpac, parsed.carrier)
        assertEquals(TrackingInputMatch.Source.Link, parsed.source)
    }

    @Test
    fun formattingAndS10Checksum() {
        assertEquals("99.34.123456.78901234", CarrierCatalog.format("993412345678901234"))
        assertTrue(CarrierCatalog.isValidS10("RA123456785CH"))
        assertFalse(CarrierCatalog.isValidS10("RA123456789CH"))
    }

    @Test
    fun numericChecksumSchemes() {
        for (number in listOf("1234567891", "0000000070")) {
            assertTrue(CarrierCatalog.isValidDhlExpressWaybill(number))
        }
        for (number in listOf("1234567890", "1234567897", "123456789", "12345678910", "1234567891\n", "123 4567891")) {
            assertFalse(CarrierCatalog.isValidDhlExpressWaybill(number))
        }
        for (number in listOf("123456782", "123456785", "000000005", "000000080")) {
            assertTrue(CarrierCatalog.isValidTntConsignmentNumber(number))
        }
        for (number in listOf("123456789", "12345678", "1234567850", "123456785\n", "1234-56785")) {
            assertFalse(CarrierCatalog.isValidTntConsignmentNumber(number))
        }
        for (number in listOf("12345678901234567890", "00000000000000000017")) {
            assertTrue(CarrierCatalog.isValidPocztaPolskaBarcode(number))
        }
        for (number in listOf("12345678901234567891", "1234567890123456789", "123456789012345678900", "12345678901234567890\n")) {
            assertFalse(CarrierCatalog.isValidPocztaPolskaBarcode(number))
        }
        for (number in listOf("PL00ZZ000000001Z", "PL00ZZ0000000010100000Y")) {
            assertTrue(CarrierCatalog.isValidCorreosSpainCheckLetter(number))
        }
        for (number in listOf("PL00ZZ000000001A", "PL00ZZ0000000010100000Z", "PL00ZZ000000002Z", "Z", "")) {
            assertFalse(CarrierCatalog.isValidCorreosSpainCheckLetter(number))
        }
        for (number in listOf("12345678901234E", "00000000000000U", "99999999999999Z", "012345678901233")) {
            assertTrue(CarrierCatalog.isValidDpdParcelNumber(number))
        }
        for (number in listOf(
            "12345678901234F", "123456789012343", "12345678901234", "1234567890123E", "12345678901234EE", "12345678901234e",
            "12345678901234E\n",
        )) {
            assertFalse(CarrierCatalog.isValidDpdParcelNumber(number))
        }
        val express = catalog.detect("123 456-7891")
        assertEquals(CarrierId.Unknown, express.carrier)
        assertEquals(CarrierMatch.Confidence.Low, express.confidence)
        assertEquals(listOf(CarrierId.DhlExpress), express.preferred)
        assertEquals(CarrierId.DhlExpress, catalog.recognitionCandidates("1234567891").firstOrNull())
        assertFalse(catalog.detect("1234567890").candidates.contains(CarrierId.DhlExpress))
        assertEquals(listOf(CarrierId.Tnt), catalog.detect("123456785").preferred)
        assertFalse(catalog.detect("123456789").candidates.contains(CarrierId.Tnt))
        assertTrue(catalog.detect("1234567890123456").candidates.contains(CarrierId.Tnt))
        assertEquals(listOf(CarrierId.PocztaPolska), catalog.detect("00159007731234567899").preferred)
        assertFalse(catalog.detect("12345678901234567891").candidates.contains(CarrierId.PocztaPolska))
        assertTrue(catalog.detect("1234567890123456789").candidates.contains(CarrierId.PocztaPolska))
    }

    /** Constructed eleven-digit numbers: DHL eCommerce Poland weighs ten digits 4 and 9 from the left. */
    @Test
    fun dhlEcommercePolandNumbersCheckTheirLastDigit() {
        for (number in listOf("12345678900", "99999999995", "00000000000")) {
            assertTrue(number, CarrierCatalog.isValidDhlEcommercePolandNumber(number))
            assertEquals(number, true, CarrierCatalog.checksumPasses("dhl-ecommerce-pl", number))
        }
        for (number in listOf("12345678901", "99999999996", "123456789016", "1234567890", "12345678900\n", "12345 678900")) {
            assertFalse(number, CarrierCatalog.isValidDhlEcommercePolandNumber(number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("dhl-ecommerce-pl", number))
        }
    }

    /**
     * Constructed twelve-digit numbers: the Identcode weighs 4 and 9 from the left. Passing it suggests DHL, which
     * is never selected or listed first, and recognition asks it first.
     */
    @Test
    fun theIdentcodeCheckSuggestsDHLWithoutSelectingIt() {
        for (number in listOf("123456789016", "000000000000")) {
            assertTrue(number, CarrierCatalog.isValidDhlIdentcode(number))
            assertEquals(number, true, CarrierCatalog.checksumPasses("identcode", number))
        }
        for (number in listOf("123456789015", "123456789010", "12345678901", "1234567890166", "123456789016\n", "12345 6789016")) {
            assertFalse(number, CarrierCatalog.isValidDhlIdentcode(number))
        }
        val passing = catalog.detect("12.345 678.901 6")
        assertEquals(CarrierId.Unknown, passing.carrier)
        assertEquals(CarrierMatch.Confidence.Low, passing.confidence)
        assertTrue(passing.candidates.contains(CarrierId.Dhl))
        assertFalse(passing.preferred.contains(CarrierId.Dhl))
        assertEquals(CarrierId.Dhl, catalog.recognitionCandidates("123456789016").firstOrNull())
        val failing = catalog.detect("123456789015")
        assertFalse(failing.candidates.contains(CarrierId.Dhl))
        assertFalse(failing.candidates.isEmpty())
    }

    /** A check digit that passes ranks its carrier ahead of the catalog's popularity rank alone. */
    @Test
    fun recognitionAsksACarrierWhoseCheckDigitPassesFirst() {
        // Synthetic twelve digits; only the first passes Purolator's Luhn check.
        assertEquals(CarrierId.Purolator, catalog.recognitionCandidates("300000000004").firstOrNull())
        assertFalse(catalog.recognitionCandidates("300000000005").contains(CarrierId.Purolator))
        // Hermes's check digit passes; DPD's networks are only more popular.
        val fourteen = catalog.recognitionCandidates("12345678901231")
        assertEquals(CarrierId.HermesDe, fourteen.firstOrNull())
        assertTrue(fourteen.contains(CarrierId.Dpd))
    }

    @Test
    fun uspsPackageChecksumsIgnoreRoutingAndRejectAmbiguousSplits() {
        // Synthetic PICs, with independently calculated MOD10 check digits.
        val pic = "9210090000000012345679"
        val longPic = "92000000000123456789012344"
        // Behind the 420 routing code and a ZIP or ZIP+4, a PIC the bare rule selects selects USPS too.
        val routed = listOf("42000000$pic", "420000000000$pic")
        // A 26-digit PIC stays a suggestion, bare or routed, and so does a routed 22-digit PIC whose
        // Mailer ID does not fit its channel or whose family DHL eCommerce also tracks.
        val suggested = listOf(longPic, "42000000$longPic", "42000000" + "9205510000000012345670", "42000000" + "9261290000000012345677")
        for (number in suggested) {
            assertTrue(number, CarrierCatalog.isValidUspsPackageBarcode(number))
            val match = catalog.detect(number)
            assertEquals(number, CarrierId.Unknown, match.carrier)
            assertEquals(number, CarrierMatch.Confidence.Low, match.confidence)
            assertTrue(number, match.preferred.contains(CarrierId.Usps))
        }
        // 22-digit PICs that follow the IMpb layout are selected outright, bare or routed.
        for (number in listOf(pic, "9300000000000000000000", "9400000000000000000009") + routed) {
            assertTrue(number, CarrierCatalog.isValidUspsPackageBarcode(number))
            val match = catalog.detect(number)
            assertEquals(number, CarrierId.Usps, match.carrier)
            assertEquals(number, CarrierMatch.Confidence.High, match.confidence)
        }
        assertTrue(CarrierCatalog.isValidUspsPackageBarcode("9210 0900-0000.0012 3456 79"))
        // Retail labels use channel 95; 91 is the legacy construct.
        for (number in listOf("9500000000000000000008", "9100000000000000000002", "420123459102$pic")) {
            assertTrue(number, CarrierCatalog.isValidUspsPackageBarcode(number))
        }
        // A ZIP+4 before a 26-digit PIC is the only split of 38 digits.
        assertTrue(CarrierCatalog.isValidUspsPackageBarcode("420000000000$longPic"))
        assertFalse(CarrierCatalog.isValidUspsPackageBarcode("420000000000" + longPic.dropLast(1) + "5"))
        // When both readings of 34 digits pass the check digit, the one whose Mailer ID fits its
        // channel settles the split. ZIP+4 9201 makes a channel 92 PIC whose Mailer ID does not
        // start with 9; so does the 22-digit reading of this channel 93 PIC behind a ZIP5.
        val wide = "93009200000000123456789013"
        for (number in listOf("420000009201$pic", wide.drop(4), "42000000$wide")) {
            assertTrue(number, CarrierCatalog.isValidUspsPackageBarcode(number))
        }
        // ZIP+4 9300 reads as a channel 93 PIC with a six-digit Mailer ID, as valid as the PIC after it.
        assertTrue(CarrierCatalog.isValidUspsPackageBarcode("9300$pic"))
        for (number in listOf(
            pic.dropLast(1) + "1", "42000000" + pic.dropLast(1) + "1",
            "420ABCDE$pic", "4200000$pic", "420000000$pic", "420000009300$pic",
            "9600000000000000000007", pic.dropLast(1), pic + "0",
        )) {
            assertFalse(number, CarrierCatalog.isValidUspsPackageBarcode(number))
            assertFalse(number, catalog.detect(number).preferred.contains(CarrierId.Usps))
            if (number.startsWith("420")) {
                assertFalse(number, catalog.detect(number).candidates.contains(CarrierId.Usps))
            }
        }
        // A selected barcode needs no carrier asked; a suggested one is asked of USPS through the browser.
        for (number in routed) {
            assertEquals(number, emptyList<CarrierId>(), catalog.recognitionCandidates(number, browser = true))
        }
        for (number in suggested.drop(1)) {
            assertFalse(number, catalog.recognitionCandidates(number).contains(CarrierId.Usps))
            assertTrue(number, catalog.recognitionCandidates(number, browser = true).contains(CarrierId.Usps))
        }
    }

    /**
     * A FedEx label barcode passes when the tracking number it ends in passes: the 22-digit `96` barcode's last
     * fifteen digits, or the 34-digit barcode's last twelve behind two zeros.
     */
    @Test
    fun fedExLabelBarcodesCheckTheTrackingNumberTheyCarry() {
        // FedEx's published label examples.
        for (number in listOf("9611020987654312345672", "9612804045318533301017")) {
            assertTrue(number, CarrierCatalog.isValidFedExGround96Barcode(number))
            assertEquals(number, true, CarrierCatalog.checksumPasses("fedex-ground-96", number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("fedex-1d", number))
        }
        for (number in listOf("9622001560001234567100794808390594", "9632001960123456789400152152152158")) {
            assertTrue(number, CarrierCatalog.isValidFedEx1DBarcode(number))
            assertEquals(number, true, CarrierCatalog.checksumPasses("fedex-1d", number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("fedex-ground-96", number))
        }
        for (number in listOf(
            // GS1 over all 22 digits passes; over the last fifteen it does not.
            "9611020987654312345673",
            "9511020987654312345672", "987654312345672", "961102098765431234567", "96110209876543123456720",
            "9611020987654312345672\n", "٩611020987654312345672", "9611 020987654312345672",
        )) {
            assertFalse(number, CarrierCatalog.isValidFedExGround96Barcode(number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("fedex-ground-96", number))
        }
        for (number in listOf(
            "9622001560001234567100794808390595",
            // 4 + 1 × 7 keeps the spec's 13-digit sum, but the layout keeps two zeros there.
            "9622001560001234567141794808390594",
            "96220015600012345671794808390594", "794808390594",
            "9622001560001234567100794808390594\n", "٩622001560001234567100794808390594",
        )) {
            assertFalse(number, CarrierCatalog.isValidFedEx1DBarcode(number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("fedex-1d", number))
        }
    }

    /**
     * Made-up Ground Economy numbers: a USPS package identifier without its `92` channel, whose check digit counts
     * the `92` it left out.
     */
    @Test
    fun fedExGroundEconomyNumbersCheckWithTheUspsChannelInFront() {
        for (number in listOf("61299999999900000010", "74899999999900000023", "61299999999900000034")) {
            assertTrue(number, CarrierCatalog.isValidFedExGroundEconomyNumber(number))
            assertEquals(number, true, CarrierCatalog.checksumPasses("fedex-ground-economy", number))
            // The check over the twenty digits alone proves nothing.
            assertEquals(number, false, CarrierCatalog.checksumPasses("gs1", number))
        }
        for (number in listOf(
            "61299999999900000011", "74899999999900000020",
            // The 22-digit identifier is not the twenty-digit number.
            "9261299999999900000010", "6129999999990000001", "612999999999000000100",
            "61299999999900000010\n", "٩1299999999900000010", "6129 9999999900000010",
        )) {
            assertFalse(number, CarrierCatalog.isValidFedExGroundEconomyNumber(number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("fedex-ground-economy", number))
        }
    }

    /** Made-up Pos Laju consignments: the S10 check digit on the nine digits after three letters. */
    @Test
    fun posLajuConsignmentsCarryTheS10CheckDigit() {
        // 0 + … = 0 closes with 5; 8 × 7 = 56 leaves 1, so 10 closes with 0.
        for (number in listOf("EHE000000005MY", "ERA123456785MY", "XYZ000000080MY")) {
            assertTrue(number, CarrierCatalog.isValidPosLajuConsignment(number))
            assertEquals(number, true, CarrierCatalog.checksumPasses("pos-laju", number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("s10", number))
        }
        for (number in listOf(
            "EHE000000006MY", "ERA123456780MY", "XYZ000000085MY", "XYZ000000081MY",
            "HE000000005MY", "EEHE000000005MY", "EH1000000005MY", "EHE000000005MX", "EHE000000005M",
            "ehe000000005MY", "EHE 000000005 MY", "EHE000000005MY\n", "EHE٩00000005MY",
        )) {
            assertFalse(number, CarrierCatalog.isValidPosLajuConsignment(number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("pos-laju", number))
        }
        // The two-letter S10 check is unchanged.
        assertTrue(CarrierCatalog.isValidS10("EH000000005MY"))
        assertFalse(CarrierCatalog.isValidS10("EH000000006MY"))
    }

    /** Made-up Saia PROs: ten digits, a 0, then the remainder of the ten divided by seven. */
    @Test
    fun saiaProsCheckTheirTwelfthDigitAgainstTheFirstTen() {
        // 9 000 000 000 leaves 5 and 9 999 999 999 leaves 3.
        for (number in listOf("900000000005", "999999999903", "000000000000")) {
            assertTrue(number, CarrierCatalog.isValidSaiaPro(number))
            assertEquals(number, true, CarrierCatalog.checksumPasses("saia", number))
        }
        for (number in listOf(
            "900000000006", "900000000007", "999999999900",
            // Another eleventh digit, the eleven-digit PRO and a thirteenth digit are not checked.
            "900000000015", "90000000000", "9000000000050",
            "900000000005\n", "9000 00000005", "٩00000000005",
        )) {
            assertFalse(number, CarrierCatalog.isValidSaiaPro(number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("saia", number))
        }
    }

    /**
     * Made-up Magyar Posta domestic numbers: `P`, a product letter, a three-character code, then seven digits
     * weighing 3, 1, 3, … from the left whose sum mod 10 is the last digit.
     */
    @Test
    fun magyarPostaDomesticNumbersCheckTheirSevenDigits() {
        // 1 × 3 = 3; 9 × 3 + 9 + 9 × 3 + 9 + 9 × 3 + 9 + 9 × 3 = 135 leaves 5; the code is not counted.
        for (number in listOf("PNZZZ00000013", "PB00000000013", "PKA1B99999995", "PNZZZ00000000")) {
            assertTrue(number, CarrierCatalog.isValidMagyarPostaNumber(number))
            assertEquals(number, true, CarrierCatalog.checksumPasses("magyar-posta", number))
        }
        for (number in listOf(
            "PNZZZ00000014", "PKA1B99999996", "PB00000000010",
            // Another first letter, a lowercase or digit product letter, and a digit short or over.
            "QNZZZ00000013", "pNZZZ00000013", "PnZZZ00000013", "P1ZZZ00000013", "PNZZZ0000013", "PNZZZ000000013",
            "PNZZZ00000013\n", "PNZZZ 0000 0013", "PNZZZ٩0000013", "PNZZZ0000001٣",
        )) {
            assertFalse(number, CarrierCatalog.isValidMagyarPostaNumber(number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("magyar-posta", number))
        }
    }

    /**
     * Made-up Poste Italiane registered-mail numbers: eleven times the digits in even places plus those in odd
     * places gives a total whose digit sum mod 10 is the last digit.
     */
    @Test
    fun posteItalianeRegisteredMailNumbersCheckTheirDigitSum() {
        // 2 + 1 = 3; 11 × 9 + 9 + 1 = 109, whose digits sum to 10, so the check is 0 and not 9.
        for (number in listOf("200000000013", "990000000010", "990000001000", "000000000000")) {
            assertTrue(number, CarrierCatalog.isValidPosteItalianeRegisteredNumber(number))
            assertEquals(number, true, CarrierCatalog.checksumPasses("poste-italiane", number))
        }
        for (number in listOf(
            "200000000014", "990000000011", "990000001009",
            // A digit short or over, a separator, a trailing newline and a non-ASCII digit.
            "20000000001", "2000000000130", "20000000001-3", "200000000013\n", "٢00000000013",
        )) {
            assertFalse(number, CarrierCatalog.isValidPosteItalianeRegisteredNumber(number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("poste-italiane", number))
        }
    }

    /**
     * Made-up Deutsche Post Frankier-IDs and the specification's own example: the last character is the CRC-4 of
     * the nineteen before it as ASCII bytes.
     */
    @Test
    fun frankierIdsCheckTheirCrc4() {
        for (number in listOf("A0123456789ABCDEF01F", "A000000000000000000A", "RA51251496FC301A6903")) {
            assertTrue(number, CarrierCatalog.isValidFrankierId(number))
            assertEquals(number, true, CarrierCatalog.checksumPasses("frankier-id", number))
        }
        for (number in listOf(
            "A0123456789ABCDEF010", "A000000000000000000F", "RA51251496FC301A6902",
            // A check past F, lower case, a character short or over, a separator and a trailing newline.
            "A0123456789ABCDEF01G", "a0123456789abcdef01f", "A0123456789ABCDEF01", "A0123456789ABCDEF01F0",
            "A012-3456789ABCDEF01F", "A0123456789ABCDEF01F\n",
        )) {
            assertFalse(number, CarrierCatalog.isValidFrankierId(number))
            assertEquals(number, false, CarrierCatalog.checksumPasses("frankier-id", number))
        }
    }

    @Test
    fun postlogisticsPrintedReferenceAndTrackingLink() {
        val printed = "12345678-001"
        val compact = "12345678001"
        assertEquals(CarrierId.Postlogistics, catalog.parse(printed).carrier)
        assertEquals(CarrierId.Unknown, catalog.detect(compact).carrier)
        // A link from the former PostLogistics site still names the carrier.
        assertEquals(CarrierId.Postlogistics, catalog.parse("https://tracking.postlogistics.ch/public/trackandtrace/$printed").carrier)
        assertEquals(printed, CarrierCatalog.format(compact, carrier = CarrierId.Postlogistics))
        val parcel = parcel(compact, "Example", CarrierId.Postlogistics, "2026-09-10T12:00:00Z")
        // The published link, so a catalog update does not block app validation; it carries the printed reference.
        val template = catalog.info(CarrierId.Postlogistics).trackingUrlTemplate
        assertNotNull(template)
        assertTrue(template, template!!.contains("{trackingNumber}"))
        assertEquals(template.replace("{trackingNumber}", printed), catalog.trackingLinks(parcel, AppLanguage.En).first().url)
    }

    @Test
    fun hermesCheckDigitAndDepotPreferenceMatchTheWebEngine() {
        assertTrue(CarrierCatalog.isValidHermesParcelNumber("12345678901231"))
        assertFalse(CarrierCatalog.isValidHermesParcelNumber("12345678901234"))
        assertTrue(catalog.detect("12345678901231").candidates.contains(CarrierId.HermesDe))
        assertFalse(catalog.detect("12345678901234").candidates.contains(CarrierId.HermesDe))
        assertFalse(catalog.detect("12345678901234").candidates.contains(CarrierId.GlsCh))

        assertTrue(CarrierCatalog.isValidGlsParcelNumber("123456789011"))
        assertFalse(CarrierCatalog.isValidGlsParcelNumber("123456789012"))
        assertTrue(catalog.detect("123456789011").candidates.contains(CarrierId.GlsDe))
        assertFalse(catalog.detect("123456789012").candidates.contains(CarrierId.GlsDe))
        assertTrue(catalog.detect("12345678901").candidates.contains(CarrierId.GlsDe))

        // A DPD Switzerland depot prefix lists DPD first but still asks the user.
        val swissDepot = catalog.detect("06080000000002")
        assertEquals(CarrierId.Unknown, swissDepot.carrier)
        assertEquals(CarrierMatch.Confidence.Low, swissDepot.confidence)
        assertEquals(listOf(CarrierId.Dpd), swissDepot.preferred)
        assertEquals(CarrierId.Dpd, swissDepot.candidates.firstOrNull())
        assertEquals(CarrierId.DpdFr, catalog.detect("10000000000001").candidates.firstOrNull())
        assertFalse(catalog.detect("06460000000002").preferred.contains(CarrierId.Dpd))
    }

    @Test
    fun acceptsASelectableCarrierThatWasNotCompiledIntoTheApp() {
        val dynamic = CarrierCatalog(futureCatalogData)
        val futureCarrier = CarrierId("future-express")

        assertFalse(CarrierId.entries.contains(futureCarrier))
        assertTrue(dynamic.selectableCarriers.contains(futureCarrier))
        assertEquals("Future Express", dynamic.info(futureCarrier).displayName)
        assertEquals(futureCarrier, dynamic.detect("FX12345678").carrier)
        assertEquals(futureCarrier, dynamic.parse("https://tracking.future.example/FX12345678").carrier)
    }

    @Test
    fun anUnknownChecksumOnAHighRuleNeverSelectsTheCarrier() {
        val dynamic = CarrierCatalog(unknownChecksumCatalogData)
        val futureExpress = CarrierId("future-express")
        assertNull(CarrierCatalog.checksumPasses("future-check", "FX12345678"))

        // The shape still suggests the carrier; the user or the carriers confirm it.
        assertEquals(
            CarrierMatch(carrier = CarrierId.Unknown, confidence = CarrierMatch.Confidence.Low, candidates = listOf(futureExpress)),
            dynamic.detect("FX12345678"),
        )
        val input = dynamic.parse("FX12345678")
        assertEquals(CarrierId.Unknown, input.carrier)
        assertEquals(CarrierMatch.Confidence.Low, input.confidence)
        assertEquals(listOf(futureExpress), input.candidates)
        assertTrue(CarrierRecognition.applies(input, amazon = false, demo = false))
        assertEquals(listOf(futureExpress), dynamic.recognitionCandidates("FX12345678"))

        // A check this build knows still selects a carrier and rejects a mistyped number.
        assertEquals(true, CarrierCatalog.checksumPasses("s10", "RA123456785CH"))
        assertEquals(false, CarrierCatalog.checksumPasses("s10", "RA123456789CH"))
        assertEquals(CarrierId.InternationalPost, dynamic.detect("RA123456785CH").carrier)
        assertEquals(CarrierMatch.Confidence.High, dynamic.detect("RA123456785CH").confidence)
        assertFalse(dynamic.detect("RA123456789CH").candidates.contains(CarrierId.InternationalPost))
    }

    @Test
    fun anUnknownChecksumOnAPreferredRuleNeverListsTheCarrierFirst() {
        val dynamic = CarrierCatalog(unknownChecksumCatalogData)
        val checked = CarrierId("check-post")
        val future = CarrierId("future-post")
        val plain = CarrierId("plain-post")

        // Only the check this build runs puts its carrier first; the other is asked by rank.
        val valid = dynamic.detect("1234567891")
        assertEquals(CarrierMatch.Confidence.Low, valid.confidence)
        assertEquals(listOf(checked), valid.preferred)
        assertEquals(listOf(checked, future, plain), valid.candidates)
        assertEquals(listOf(checked, plain, future), dynamic.recognitionCandidates("1234567891"))

        // A mistyped number fails the known check; the unknown one cannot tell, so its carrier stays suggested.
        val mistyped = dynamic.detect("1234567890")
        assertEquals(emptyList<CarrierId>(), mistyped.preferred)
        assertEquals(listOf(future, plain), mistyped.candidates)
        assertEquals(listOf(plain, future), dynamic.recognitionCandidates("1234567890"))

        // A postal number recovers the rules S10 vouches for, not one behind another check.
        assertEquals(CarrierId.InternationalPost, dynamic.detect("RA123456785CH").carrier)
        assertEquals(listOf(plain), dynamic.recognitionCandidates("RA123456785CH"))
    }

    @Test
    fun aCarrierIsOnlySuggestedForANumberItsRecognitionCannotAnswer() {
        val dynamic = CarrierCatalog(recognitionPatternCatalogData)
        val freight = CarrierId("pro-freight")
        val plain = CarrierId("plain-post")

        // Its recognition answers eleven digits; a checked twelfth digit only suggests it.
        val twelve = dynamic.detect("900000000005")
        assertEquals(CarrierMatch.Confidence.Low, twelve.confidence)
        assertEquals(listOf(plain, freight), twelve.candidates)
        assertEquals(listOf(plain), dynamic.recognitionCandidates("900000000005"))
        assertEquals(listOf(freight, plain), dynamic.recognitionCandidates("90000000000"))
    }

    @Test
    fun refreshesCachesAndConditionallyRevalidatesTheRemoteCatalog() = runTest {
        val cacheFile = File(System.getProperty("java.io.tmpdir"), "carrier-catalog-${UUID.randomUUID()}.json")
        try {
            val firstRequests = mutableListOf<CarrierCatalog.Request>()
            val first = CarrierCatalog(
                bundledCatalogData,
                cacheFile = cacheFile,
                loader = { request ->
                    firstRequests += request
                    CarrierCatalog.Response(200, headers = mapOf("ETag" to "\"future-v1\""), body = futureCatalogData)
                },
            )

            val firstResult = first.refresh("https://delivery.example", force = true)
            assertEquals(CarrierCatalogRefreshResult.Updated, firstResult)
            val futureCarrier = CarrierId("future-express")
            assertEquals("Future Express", first.info(futureCarrier).displayName)
            assertTrue(cacheFile.exists())
            val firstRequest = firstRequests.lastOrNull()
            assertEquals("https://delivery.example/api/carriers", firstRequest?.url)
            assertNull(firstRequest?.headers?.get("If-None-Match"))

            val revalidationRequests = mutableListOf<CarrierCatalog.Request>()
            val cached = CarrierCatalog(
                cacheFile = cacheFile,
                loader = { request ->
                    revalidationRequests += request
                    CarrierCatalog.Response(304, body = ByteArray(0))
                },
            )
            assertEquals("Future Express", cached.info(futureCarrier).displayName)
            // The cache is re-encoded from the decoded classes, so it must keep `optional`.
            assertEquals(listOf(true), cached.requirements(futureCarrier, trackingNumber = "FX12345678").map { it.isOptional })
            // And the fields a newer build reads.
            assertEquals("https://tracking.future.example/", cached.info(futureCarrier).trackingSearchUrl)
            assertEquals("FX12345678", cached.parse("https://tracking.future.example/?FX12345678").trackingNumber)
            val cachedResult = cached.refresh("https://delivery.example", force = true)
            assertEquals(CarrierCatalogRefreshResult.NotModified, cachedResult)
            val revalidationRequest = revalidationRequests.lastOrNull()
            assertEquals("\"future-v1\"", revalidationRequest?.headers?.get("If-None-Match"))
        } finally {
            cacheFile.delete()
        }
    }

    @Test
    fun refreshedDefinitionsReplaceRememberedDetections() = runTest {
        val catalog = CarrierCatalog(
            bundledCatalogData,
            loader = { CarrierCatalog.Response(200, body = futureCatalogData) },
        )
        val futureCarrier = CarrierId("future-express")
        val link = "https://tracking.future.example/FX12345678"
        assertNotEquals(futureCarrier, catalog.detect("FX12345678").carrier)
        assertNotEquals(futureCarrier, catalog.parse(link).carrier)

        val result = catalog.refresh("https://delivery.example", force = true)
        assertEquals(CarrierCatalogRefreshResult.Updated, result)
        assertEquals(futureCarrier, catalog.detect("FX12345678").carrier)
        assertEquals(futureCarrier, catalog.parse(link).carrier)
    }

    @Test
    fun rejectsAnInvalidRemoteCatalogAndKeepsTheBundledDefinitions() = runTest {
        val original = CarrierCatalog(
            bundledCatalogData,
            loader = {
                val invalid = "{\"x-carriers\":{}}".toByteArray()
                CarrierCatalog.Response(200, body = invalid)
            },
        )

        val result = original.refresh("https://delivery.example", force = true)
        assertEquals(CarrierCatalogRefreshResult.Failed, result)
        assertEquals("Amazon Logistics", original.info(CarrierId.AmazonLogistics).displayName)
    }

    @Test
    fun aCarrierThisBuildCannotReadKeepsItsDefinitionWhileTheOthersRefresh() = runTest {
        val catalog = CarrierCatalog(
            bundledCatalogData,
            loader = { CarrierCatalog.Response(200, body = unreadableCarrierCatalogData) },
        )
        val bundledName = catalog.info(CarrierId.Dpd).displayName

        val result = catalog.refresh("https://delivery.example", force = true)
        assertEquals(CarrierCatalogRefreshResult.Updated, result)
        assertEquals("Future Express", catalog.info(CarrierId("future-express")).displayName)
        // DPD now asks for an input this build does not know.
        assertEquals(bundledName, catalog.info(CarrierId.Dpd).displayName)
    }

    /**
     * The server sends the bundled catalog without indentation. It must stay far below the limit, or every refresh
     * fails and the app keeps the catalog it has.
     */
    @Test
    fun theCatalogStaysFarBelowTheRefreshLimit() {
        val compact = Json.parseToJsonElement(bundledCatalogData.decodeToString()).toString().toByteArray()
        assertTrue(compact.size < CarrierCatalog.maximumResponseBytes / 4)
    }

    /** Link rules read as in the shared engine's `parseTrackingInput`. */
    @Test
    fun linkRulesReadAsInTheSharedEngine() {
        val links = CarrierCatalog(linkRulesCatalogData)
        val bare = CarrierId("bare-post")
        val market = CarrierId("market-one")
        val page = CarrierId("page-post")
        val portal = CarrierId("portal-post")

        // A portal that puts the number alone in the query.
        val query = links.parse("https://bare.example/track?BP12345678")
        assertEquals("BP12345678", query.trackingNumber)
        assertEquals(bare, query.carrier)

        // A marketplace link names its own carrier only for that carrier's numbers.
        assertEquals(market, links.parse("https://market.example/track?numer=MO12345678").carrier)
        assertEquals(bare, links.parse("https://market.example/track?numer=BP12345678").carrier)

        // A host that is also the number's carrier's own page names that carrier.
        assertEquals(page, links.parse("https://portal.example/t/PP123456789").carrier)
        assertEquals(portal, links.parse("https://portal.example/T/QQ123456789").carrier)

        // A link that defers to the number keeps two carriers the number fits for the user.
        val shared = links.parse("https://net.example/?id=N1234567890")
        assertEquals(CarrierId.Unknown, shared.carrier)
        assertEquals(CarrierMatch.Confidence.Low, shared.confidence)
        assertEquals(setOf(CarrierId("net-a"), CarrierId("net-b")), shared.candidates.toSet())
    }

    @Test
    fun aHiddenNumberLinksToTheCarriersSearchPage() {
        var parcel = parcel("", "Parcel", CarrierId.SwissPost, "2026-09-07T13:00:00Z")
        val link = catalog.searchLink(parcel, AppLanguage.Fr)
        assertNotNull(link)
        assertEquals("https://www.post.ch/de/empfangen/sendung-verfolgen?lang=fr", link!!.url)
        parcel = parcel.copy(carrier = CarrierId.AmazonLogistics)
        assertNull(catalog.searchLink(parcel, AppLanguage.Fr))
    }

    // The scraper's goldens and the front door's examples.

    @Serializable
    private data class GoldenEntry(
        val input: String,
        val carrier: String,
        val confidence: String,
        val candidates: List<String>,
        val preferred: List<String>? = null,
        val asked: List<String>? = null,
    )

    /**
     * The scraper's published detection corpus records what the TypeScript engine answers for every sample number;
     * the port must agree.
     */
    @Test
    fun detectionMatchesTheSharedGoldenFile() {
        val entries = ApiJson.decodeFromString(ListSerializer(GoldenEntry.serializer()), SharedResources.text("DetectionGolden.json"))
        assertTrue(entries.size > 100)
        val mismatches = mutableListOf<String>()
        for (entry in entries) {
            val match = catalog.detect(entry.input)
            val confidence = when (match.confidence) {
                CarrierMatch.Confidence.High -> "high"
                CarrierMatch.Confidence.Low -> "low"
                CarrierMatch.Confidence.None -> "none"
            }
            val candidates = match.candidates.map { it.rawValue }.sorted()
            val preferred = match.preferred.map { it.rawValue }.sorted()
            if (match.carrier.rawValue != entry.carrier || confidence != entry.confidence ||
                candidates != entry.candidates.sorted() || preferred != (entry.preferred ?: emptyList()).sorted() ||
                match.candidates.take(preferred.size).map { it.rawValue }.sorted() != preferred
            ) {
                mismatches += "${entry.input}: expected ${entry.carrier}/${entry.confidence} ${entry.candidates}, " +
                    "got ${match.carrier.rawValue}/$confidence $candidates"
            }
            // The catalog's static candidate order follows the shared engine.
            val asked = catalog.recognitionCandidates(entry.input).map { it.rawValue }
            if (asked != (entry.asked ?: emptyList<String>())) {
                mismatches += "${entry.input}: expected to ask ${entry.asked ?: emptyList<String>()}, got $asked"
            }
        }
        assertEquals(mismatches.take(10).joinToString("\n"), emptyList<String>(), mismatches)
    }

    /**
     * The scraper's published checksum vectors record what each TypeScript validator answers for synthetic inputs;
     * the checksum of the same id must agree.
     */
    @Test
    fun checksumsMatchTheSharedVectors() {
        val file = Json.parseToJsonElement(SharedResources.text("ChecksumVectors.json")).jsonObject
        assertEquals(1, file.getValue("version").jsonPrimitive.int)
        val vectors = file.getValue("vectors").jsonObject.mapValues { (_, list) ->
            list.jsonArray.map { pair ->
                val values = pair.jsonArray
                // A vector is an input and its answer.
                assertEquals(2, values.size)
                values[0].jsonPrimitive.content to values[1].jsonPrimitive.boolean
            }
        }
        assertFalse(vectors.isEmpty())
        val mismatches = mutableListOf<String>()
        for ((id, pairs) in vectors.entries.sortedBy { it.key }) {
            for ((input, expected) in pairs) {
                val shown = Json.encodeToString(String.serializer(), input)
                val passes = CarrierCatalog.checksumPasses(id, input)
                if (passes == null) {
                    mismatches += "$id $shown: the app does not know this checksum"
                    break
                }
                if (passes != expected) mismatches += "$id $shown: expected $expected, got $passes"
            }
        }
        assertEquals(mismatches.take(10).joinToString("\n"), emptyList<String>(), mismatches)
    }

    @Serializable
    private data class ReadingExample(
        val text: String,
        val shop: OrderShop? = null,
        /** The tracking number read, or none. */
        val number: String? = null,
        /** The carrier that claims the number read instead, with certainty. */
        val carrier: String? = null,
        /** Whether the number read is a pickup code, which Add holds back. */
        val pickup: Boolean? = null,
    )

    @Serializable
    private data class ReadingExamples(val examples: List<ReadingExample>)

    /**
     * The front door's examples (shared/reading.json): Add reads the same number out of a message and holds back
     * the same order numbers.
     */
    @Test
    fun textReadsAsTheFrontDoorReadsIt() {
        val file = ApiJson.decodeFromString(ReadingExamples.serializer(), SharedResources.text("Reading.json"))
        assertEquals(listOf(OrderShop.Amazon, OrderShop.Shein, OrderShop.Shopee, OrderShop.Temu), CarrierCatalog.orderNumbers.map { it.shop })
        assertFalse(CarrierCatalog.candidatePatterns.isEmpty())
        assertTrue(file.examples.size > 40)
        val mismatches = mutableListOf<String>()
        fun quoted(value: String) = Json.encodeToString(String.serializer(), value)
        for (example in file.examples) {
            val text = quoted(example.text)
            val order = catalog.order(example.text)
            if (order != example.shop) {
                mismatches += "$text: expected ${example.shop?.rawValue ?: "no shop"}, got ${order?.rawValue ?: "no shop"}"
            }
            val match = catalog.read(example.text)
            val number = example.number
            if (number != null && match.trackingNumber != number) {
                mismatches += "$text: expected to read ${quoted(number)}, got ${quoted(match.trackingNumber)}"
            }
            val carrier = example.carrier
            if (carrier != null && (match.carrier.rawValue != carrier || match.confidence != CarrierMatch.Confidence.High)) {
                mismatches += "$text: expected $carrier to claim it, got ${match.carrier.rawValue}/${match.confidence}"
            }
            val pickup = example.pickup
            if (pickup != null && CarrierCatalog.isPickupCode(CarrierCatalog.normalize(match.trackingNumber)) != pickup) {
                mismatches += "$text: expected ${if (pickup) "a pickup code" else "no pickup code"}"
            }
        }
        assertEquals(mismatches.take(10).joinToString("\n"), emptyList<String>(), mismatches)
        // Each shop's notice is the front door's, in every language.
        val languages = Json.decodeFromString(
            MapSerializer(String.serializer(), MapSerializer(String.serializer(), String.serializer())),
            SharedResources.text("Localization.json"),
        )
        for ((language, messages) in languages) {
            for ((shop, _) in CarrierCatalog.orderNumbers) {
                assertNotNull("$language $shop", messages["door.order.${shop.rawValue}.title"])
                assertNotNull("$language $shop", messages["door.order.${shop.rawValue}.body"])
            }
        }
    }

    @Serializable
    private data class CheckDigitExample(val number: String, val trouble: Boolean, val suggestion: String? = null)

    @Serializable
    private data class CheckDigitExamples(val checkDigits: List<CheckDigitExample>)

    /** The front door's check-digit examples (shared/reading.json): Add asks about the same numbers. */
    @Test
    fun checkDigitsAreQuestionedAsTheFrontDoorQuestionsThem() {
        val file = ApiJson.decodeFromString(CheckDigitExamples.serializer(), SharedResources.text("Reading.json"))
        assertTrue(file.checkDigits.size > 5)
        for (example in file.checkDigits) {
            val expected = if (example.trouble) CheckDigitTrouble(suggestion = example.suggestion) else null
            assertEquals(example.number, expected, catalog.checkDigitTrouble(example.number))
        }
    }

    /**
     * "Did you mean …?" marks the characters the suggestion changes, and only those, as the front door does. The
     * label's ranges stand for SwiftUI's background colour and monospaced font runs.
     */
    @Test
    fun suggestionMarksTheCharactersItChanges() {
        data class Case(val typed: String, val suggestion: String, val marked: List<String>)
        val cases = listOf(
            Case(typed = "LX1234567B5DE", suggestion = "LX123456785DE", marked = listOf("8")),
            Case(typed = "1X123456785DE", suggestion = "IX123456785DE", marked = listOf("I")),
            Case(typed = "RR12345678SCH", suggestion = "RR123456785CH", marked = listOf("5")),
            Case(typed = "LXZ234567B5DE", suggestion = "LX223456785DE", marked = listOf("2", "8")),
        )
        for (example in cases) {
            val label = AddParcelView.suggestionLabel(example.suggestion, typed = example.typed) { "Meinten Sie $it wirklich?" }
            assertEquals("Meinten Sie ${example.suggestion} wirklich?", label.text)
            val marked = label.marked.map { label.text.substring(it) }
            assertEquals(example.typed, example.marked, marked)
            // The number is set in monospace; the words around it are not.
            val set = listOf(label.text.substring(label.number))
            assertEquals(example.typed, listOf(example.suggestion), set)
        }
    }

    @Serializable
    private data class ListedNumber(val number: String, val carrier: String)

    @Serializable
    private data class SeveralExample(val text: String, val numbers: List<ListedNumber>)

    @Serializable
    private data class SeveralExamples(val several: List<SeveralExample>)

    /**
     * The front door's lists of several numbers in one text (shared/reading.json): Add lists the same, in the same
     * order, and the first is the one it adds unless another is chosen.
     */
    @Test
    fun severalNumbersAreListedAsTheFrontDoorListsThem() {
        val file = ApiJson.decodeFromString(SeveralExamples.serializer(), SharedResources.text("Reading.json"))
        assertTrue(file.several.size > 5)
        for (example in file.several) {
            val listed = catalog.numbers(example.text)
            val shown = Json.encodeToString(String.serializer(), example.text)
            assertEquals(shown, example.numbers, listed.map { ListedNumber(number = it.normalized, carrier = it.match.carrier.rawValue) })
            assertTrue(shown, listed.all { it.match.confidence == CarrierMatch.Confidence.High })
        }
    }

    /** The shared engine's patterns and separators are JavaScript's. */
    @Test
    fun patternsAndSeparatorsReadAsInTheSharedEngine() {
        assertEquals("AB12345", CarrierCatalog.normalize("﻿ab 12.3-4　5\u000B"))
        assertEquals("AB\u008512", CarrierCatalog.normalize("AB\u008512"))
        assertTrue(CarrierCatalog.hasMod7CheckDigit("000000000011"))
        assertFalse(CarrierCatalog.hasMod7CheckDigit("000000000011\n"))
        assertTrue(CarrierCatalog.isValidSscc("00000000000000000017"))
        assertFalse(CarrierCatalog.isValidSscc("00000000000000000017\n"))
        // S10 normalizes its own input, separators and the final line break included.
        assertTrue(CarrierCatalog.isValidS10("﻿RR 473 124 829 CH\n"))
    }

    /** A label prints its SSCC behind the bracketed GS1 identifier; carriers track the twenty digits. */
    @Test
    fun anSSCCTypedWithItsBracketedIdentifierReadsAsItsDigits() {
        val sscc = "00370123456789012347"
        assertEquals(sscc, CarrierCatalog.normalize("(00) 3 7012345 678901234 7"))
        assertEquals(catalog.detect(sscc), catalog.detect("(00) 3 7012345 678901234 7"))
        val input = catalog.parse("(00) 370123456789012347")
        assertEquals(TrackingInputMatch.Source.Number, input.source)
        assertEquals(catalog.detect(sscc).candidates, input.candidates)
        // (420) carries the destination ZIP, which is no parcel number.
        for (raw in listOf("(420) 12345", "(420) 12345 (92) 612 90 100 13043 50825 07", "(00) 37012345678901234", "(01) 09501101530003")) {
            assertTrue(raw, CarrierCatalog.normalize(raw).contains("("))
            assertEquals(raw, CarrierMatch.Confidence.None, catalog.detect(raw).confidence)
        }
    }
}
