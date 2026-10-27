package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.BeforeClass
import org.junit.Test

/**
 * Inputs where Foundation and the JVM part ways, with what the iPhone app answers: each expected value is the
 * output of CarrierCatalog.swift run on iOS 27's Foundation.
 */
class CarrierCatalogParityTests {
    companion object {
        @JvmStatic
        @BeforeClass
        fun installIcu() = TestIcu.install()
    }

    private val catalog = CarrierCatalog(cacheFile = null)

    private fun assertLink(expected: TrackingInputMatch, input: String) {
        assertEquals(expected, catalog.parse(input))
        assertEquals(expected, catalog.read(input))
    }

    private fun linkMatch(number: String, carrier: CarrierId) = TrackingInputMatch(
        trackingNumber = number,
        carrier = carrier,
        confidence = CarrierMatch.Confidence.High,
        candidates = listOf(carrier),
        trackingUrl = null,
        source = TrackingInputMatch.Source.Link,
    )

    @Test
    fun readsLinksFoundationEncodes() {
        // "|" is percent-encoded, not refused, so GLS's link is read.
        assertLink(
            linkMatch("0.10.2.40193547", CarrierId.GlsDe),
            "https://www.gls-pakete.de/sendungsverfolgung?trackingNumber=0.10.2.40193547|0.10.2.40193547",
        )
        // A host that is not ASCII is a link too, whose number is read from it.
        assertLink(linkMatch("0000000000000000000000", CarrierId.CttExpress), "https://münchen.de/t/0000000000000000000000")
        // A query value that is not UTF-8 is nil, so the link's number is found in the rest.
        assertLink(
            linkMatch("0000000000000000000000", CarrierId.CttExpress),
            "https://www.ups.com/track?tracknum=0000000000000000000000%FF",
        )
        val numbers = catalog.numbers("https://münchen.de/t/1ZDEMO202600000001 or 1ZDEMO202600000029")
        assertEquals(listOf(TrackingInputMatch.Source.Link, TrackingInputMatch.Source.Text), numbers.map { it.match.source })
    }

    @Test
    fun aLinkWithAShopsNumberIsNoOrder() {
        assertNull(catalog.order("https://www.gls-pakete.de/sendungsverfolgung?trackingNumber=251015WE1ANOP9|251015WE1ANOP9"))
        assertNull(catalog.order("https://www.gls-pakete.de/sendungsverfolgung?trackingNumber=302-4571983-2294617|302-4571983-2294617"))
        assertNull(catalog.order("https://münchen.de/t/251015WE1ANOP9"))
    }

    @Test
    fun aLineEndAfterTheNumberIsNoNumberToAskAbout() {
        // `range(of:options: .regularExpression)` reads `$` as the end of the input, not before a final line end.
        for (number in listOf("000000000\n", "000000000\r\n", "ABCD\r", "ABCD\u0085", "ABCD\u2028")) {
            assertNull(number, NumberRefusals.question(CarrierId.Dhl, number, demo = false))
        }
        assertEquals(
            NumberRefusalRequest(trackingNumber = "000000000", carrier = CarrierId.Dhl),
            NumberRefusals.question(CarrierId.Dhl, "000000000", demo = false),
        )
    }

    private val nothing = TrackingInputMatch(
        trackingNumber = "",
        carrier = CarrierId.Unknown,
        confidence = CarrierMatch.Confidence.None,
        candidates = emptyList(),
        trackingUrl = null,
        source = TrackingInputMatch.Source.None,
    )

    @Test
    fun ignoringCaseTheTurkishIsAreNoIs() {
        // ICU folds İ to "i̇" and keeps ı, so "trackİng" is no tracking label.
        assertEquals(nothing, catalog.parse("Trackİng NO123456789"))
        assertEquals(nothing, catalog.parse("Your trackİng number İs 12345678901."))
        assertEquals(nothing, catalog.read("trackİng notable1234"))
        val entry = "Tracking number ıs 12345678901"
        val asTyped = TrackingInputMatch(
            trackingNumber = entry,
            carrier = CarrierId.Unknown,
            confidence = CarrierMatch.Confidence.None,
            candidates = emptyList(),
            trackingUrl = null,
            source = TrackingInputMatch.Source.Number,
        )
        assertEquals(asTyped, catalog.parse(entry))
        assertEquals(asTyped, catalog.read(entry))
    }

    @Test
    fun ignoringCaseTheKelvinSignIsK() {
        assertEquals(nothing, catalog.parse("Tracking: JJD000\u212A0000000000"))
        assertEquals(nothing, catalog.read("Tracking: JJD000\u212A0000000000"))
        assertEquals(OrderShop.Shein, catalog.order("GSH\u212A1B2C3D4E5F6"))
        assertEquals(OrderShop.Shein, catalog.order("Order GSH0A1B2C3D4E5\u212A"))
    }

    @Test
    fun linkParametersAreNamedAsCaseInsensitiveCompareReadsThem() {
        // ı, İ and "i̇" are no i.
        assertLink(nothing, "https://www.dhl.com/track?tracking-\u0131d=1234567899")
        assertLink(nothing, "https://www.dhl.com/track?tracking-\u0130d=1234567899")
        assertLink(nothing, "https://www.dhl.com/track?tracking-i\u0307d=1234567899")
        assertLink(linkMatch("1234567899", CarrierId.Dhl), "https://www.dhl.com/track?TRACKING-ID=1234567899")
        assertLink(linkMatch("1234567899", CarrierId.Dhl), "https://www.dhl.com/track?trac\u212Aing-id=1234567899")
        // A ligature is the letters it joins.
        assertLink(linkMatch("XT123456785FR", CarrierId.Chronopost), "https://www.chronopost.fr/suivi?li\uFB06eNumeros=XT123456785FR")
    }
}
