package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PostcodeCountry
import com.plhery.peekdeliverytracker.core.api.ProviderInputNeeded
import com.plhery.peekdeliverytracker.core.api.ProviderInputNeededField
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** The change-carrier sheet's arithmetic (ParcelDetailView.swift's `ChangeCarrierView`), on synthetic numbers. */
class CarrierChangeTests {
    private val catalog = CarrierCatalog(cacheFile = null)
    private val localizer = Localizer(AppLanguage.En)

    private fun parcel(
        carrier: CarrierId,
        number: String = "TESTPARCEL123",
        trackingUrl: String? = null,
        postcode: String? = null,
        data: CarrierData? = null,
        createdAt: String = "2026-08-01T10:00:00Z",
    ): Parcel = makeParcel(trackingNumber = number).copy(
        carrier = carrier, trackingUrl = trackingUrl, dpdPostcode = postcode, carrierData = data, createdAt = createdAt,
    )

    private fun start(parcel: Parcel, initial: CarrierId? = null, region: String? = "FR") =
        CarrierChange.start(parcel, initial, catalog, region)

    @Test
    fun anUnfiledParcelOnItsOwnCarrierStartsOnAutomaticDetectionButForAnAmazonNumber() {
        for (carrier in listOf(CarrierId.Unknown, CarrierId.InternationalPost)) {
            val change = start(parcel(carrier))
            assertNull(change.chosenCarrier)
            assertTrue(change.automatic)
            // Filed under none already, automatic detection keeps it there.
            assertEquals(carrier, change.automaticCarrier)
            assertEquals(carrier, change.selectedCarrier)
            assertEquals("Detect automatically", change.carrierName(localizer))
            assertEquals("Peek finds the carrier for you.", change.hint(localizer))
        }
        // An Amazon number is tracked through Amazon Shipping alone: no automatic detection.
        val amazon = start(parcel(CarrierId.Unknown, number = "FR1234567890"))
        assertEquals(CarrierId.Unknown, amazon.chosenCarrier)
        assertNull(amazon.automaticCarrier)
        assertFalse(amazon.automatic)
        // A prompt's carrier is chosen, even for an unfiled parcel.
        assertEquals(CarrierId.Dpd, start(parcel(CarrierId.Unknown), CarrierId.Dpd).chosenCarrier)
    }

    @Test
    fun aFiledParcelStartsOnItsCarrierOrThePromptsAndItsFieldsOnlyOnItsOwn() {
        val filed = parcel(CarrierId.Dpd, postcode = "8000")
        val own = start(filed)
        assertEquals(CarrierId.Dpd, own.chosenCarrier)
        assertEquals("8000", own.deliveryPostcode)
        assertFalse(own.changed)
        assertFalse(own.canSave)
        val linked = start(parcel(CarrierId.Planzer, number = "9999012345678", trackingUrl = "https://example.com/shared/1"))
        assertEquals("https://example.com/shared/1", linked.trackingUrl)
        assertFalse(linked.changed)
        assertEquals("", start(linked.parcel, CarrierId.Dpd).trackingUrl)

        val other = start(filed, CarrierId.GlsCh)
        assertEquals(CarrierId.GlsCh, other.selectedCarrier)
        assertEquals("", other.trackingUrl)
        assertEquals("", other.deliveryPostcode)
        assertTrue(other.changed)
        // GLS Switzerland needs its postcode first.
        assertFalse(other.canSave)
        assertTrue(other.copy(deliveryPostcode = "8000").canSave)
    }

    @Test
    fun automaticDetectionFilesUnderTheCarrierTheNumberNamesElseUnderNone() {
        val ups = start(parcel(CarrierId.Dpd, number = "1Z999AA10123456784")).choosing(null)
        assertEquals(CarrierId.Ups, ups.automaticCarrier)
        assertEquals(CarrierId.Ups, ups.selectedCarrier)
        assertEquals("UPS, from the number", ups.automaticDescription(localizer))
        val unknown = start(parcel(CarrierId.Dpd)).choosing(null)
        assertEquals(CarrierId.Unknown, unknown.selectedCarrier)
        assertEquals("Peek finds the carrier for you.", unknown.automaticDescription(localizer))
        assertTrue(unknown.canSave)
    }

    @Test
    fun anotherCarrierStartsItsFieldsAgainAndTheParcelsOwnGetsThemBack() {
        val filed = parcel(CarrierId.MondialRelay, number = "76434219", postcode = "75001")
        val change = start(filed).copy(chosenPostcodeCountry = "BE", deliveryPostcode = "1000")
        val dpd = change.choosing(CarrierId.Dpd)
        assertEquals("", dpd.deliveryPostcode)
        assertNull(dpd.chosenPostcodeCountry)
        val back = dpd.copy(deliveryPostcode = "8000").choosing(CarrierId.MondialRelay)
        assertEquals("75001", back.deliveryPostcode)
        // The same carrier again, chosen or detected, leaves what is typed.
        assertEquals("1000", change.choosing(CarrierId.MondialRelay).deliveryPostcode)
        assertEquals("BE", change.choosing(CarrierId.MondialRelay).chosenPostcodeCountry)
        val unfiled = start(parcel(CarrierId.Unknown)).copy(deliveryPostcode = "x")
        assertEquals("x", unfiled.choosing(CarrierId.Unknown).deliveryPostcode)
    }

    @Test
    fun thePostcodesCountryIsOfferedWhereTheFieldTakesSeveralAndShapesIt() {
        val change = start(parcel(CarrierId.Unknown, number = "76434219"), CarrierId.MondialRelay, region = "FR")
        assertNotNull(change.carrierPostcodeRequirement)
        assertEquals("FR", change.initialPostcodeCountry)
        assertEquals("FR", change.offeredPostcodeCountry)
        assertFalse(change.postcodeRequirement!!.isOptional)
        // The home country's postcode goes as it always has.
        assertNull(change.copy(deliveryPostcode = "75001").sentPostcodeCountry)
        assertEquals("75001", change.copy(deliveryPostcode = " 75001 ").sentPostcode)
        assertNull(change.sentPostcode)
        assertNull(change.sentTrackingUrl)
        assertFalse(change.canSave)
        assertTrue(change.copy(deliveryPostcode = "75001").canSave)

        val belgium = change.copy(chosenPostcodeCountry = "BE", deliveryPostcode = "1000")
        assertEquals("BE", belgium.postcodeCountry)
        assertEquals(CarrierRequirement.anyPostcode, belgium.postcodeRequirement!!.pattern)
        assertEquals("BE", belgium.sentPostcodeCountry)
        // Without a postcode, no country goes.
        assertNull(belgium.copy(deliveryPostcode = "").sentPostcodeCountry)
        // A country the field doesn't take is the field's start.
        assertEquals("FR", change.copy(chosenPostcodeCountry = "ZZ").postcodeCountry)

        // DPD reads the postcode alone: no country beside it, and an optional field.
        val dpd = start(parcel(CarrierId.Unknown), CarrierId.Dpd)
        assertNull(dpd.offeredPostcodeCountry)
        assertTrue(dpd.postcodeRequirement!!.isOptional)
        assertTrue(dpd.canSave)
    }

    @Test
    fun aPostcodeSavedWithItsCountryStartsThereAndAnotherCountryIsAChange() {
        val saved = parcel(
            CarrierId.MondialRelay, number = "76434219", postcode = "1000",
            data = CarrierData(postcodeCountry = PostcodeCountry("1000", "BE")),
        )
        val change = start(saved)
        assertEquals("BE", change.initialPostcodeCountry)
        assertFalse(change.changed)
        assertTrue(change.copy(chosenPostcodeCountry = "LU").changed)
    }

    @Test
    fun theKeyboardFollowsTheFieldAndTheCountrysExample() {
        val gls = start(parcel(CarrierId.Unknown), CarrierId.GlsCh)
        assertEquals(CarrierChange.PostcodeKeys.NumberPad, gls.postcodeKeys(gls.postcodeRequirement!!))
        val dpd = start(parcel(CarrierId.Unknown), CarrierId.Dpd, region = "FR")
        assertEquals(CarrierChange.PostcodeKeys.NumbersAndPunctuation, dpd.postcodeKeys(dpd.postcodeRequirement!!))
        val british = start(parcel(CarrierId.Unknown), CarrierId.Dpd, region = "GB")
        assertEquals(CarrierChange.PostcodeKeys.AsciiCapable, british.postcodeKeys(british.postcodeRequirement!!))
        // Mondial Relay reads the country beside the field rather than the phone's.
        val relay = start(parcel(CarrierId.Unknown, number = "76434219"), CarrierId.MondialRelay, region = "GB")
            .copy(chosenPostcodeCountry = "FR")
        assertEquals(CarrierChange.PostcodeKeys.NumbersAndPunctuation, relay.postcodeKeys(relay.postcodeRequirement!!))
    }

    @Test
    fun aTrackingLinkMustBeASecureWebAddress() {
        val planzer = start(parcel(CarrierId.Unknown, number = "9999012345678"), CarrierId.Planzer)
        assertNotNull(planzer.trackingUrlRequirement)
        assertFalse(planzer.canSave)
        assertFalse(planzer.copy(trackingUrl = "http://example.com/shared/1").canSave)
        assertFalse(planzer.copy(trackingUrl = "https://").canSave)
        assertTrue(planzer.copy(trackingUrl = "  https://example.com/shared/1 ").canSave)
        assertEquals("https://example.com/shared/1", planzer.copy(trackingUrl = "  https://example.com/shared/1 ").normalizedTrackingUrl)
    }

    @Test
    fun aProvidersPostcodeIsAskedWhileTheParcelStaysWithItsCarrier() {
        val asked = CarrierData(
            routing = CarrierDataRouting(providerInputNeeded = ProviderInputNeeded("Example Provider", ProviderInputNeededField.DpdPostcode)),
        )
        val change = start(parcel(CarrierId.Dpd, data = asked))
        assertTrue(change.needsProviderPostcode)
        assertEquals("Example Provider", change.inputProvider)
        assertFalse(change.canSave)
        assertNull(change.copy(providerPostcode = "ab").normalizedProviderPostcode)
        assertNull(change.copy(providerPostcode = "ABCDE").normalizedProviderPostcode)
        val typed = change.copy(providerPostcode = " sw1a 1aa ")
        assertEquals("SW1A 1AA", typed.normalizedProviderPostcode)
        assertTrue(typed.changed)
        assertTrue(typed.canSave)
        // Another carrier isn't the provider's to ask for.
        val other = typed.choosing(CarrierId.GlsCh)
        assertFalse(other.needsProviderPostcode)
        assertNull(other.normalizedProviderPostcode)
    }

    @Test
    fun theRefusalQuestionIsAboutACarrierChosenByHandOutsideTheDemo() {
        val change = start(parcel(CarrierId.Dpd, number = "test parcel 123"))
        assertEquals(NumberRefusalRequest("TESTPARCEL123", CarrierId.Dpd), change.refusalQuestion(demo = false))
        assertNull(change.refusalQuestion(demo = true))
        assertNull(change.choosing(null).refusalQuestion(demo = false))
    }

    @Test
    fun thePickerOpensOnTheParcelsCarrierTheCarriersTheNumberFitsAndThoseUsedBefore() {
        val ups = parcel(CarrierId.Dpd, number = "1Z999AA10123456784")
        val used = listOf(
            parcel(CarrierId.GlsCh, createdAt = "2026-08-03T10:00:00Z"),
            parcel(CarrierId.Ups, createdAt = "2026-08-02T10:00:00Z"),
            parcel(CarrierId.Dpd, createdAt = "2026-08-01T10:00:00Z"),
        )
        val sections = start(ups).pickerSections(used)
        assertEquals(listOf("current", "fits", "used"), sections.map { it.id })
        assertEquals(listOf(CarrierId.Dpd), sections[0].carriers)
        assertEquals("picker.section.detected", sections[1].titleKey)
        assertEquals(listOf(CarrierId.Ups), sections[1].carriers)
        assertEquals(listOf(CarrierId.GlsCh), sections[2].carriers)
        // Filed automatically already, an unfiled parcel's own carrier isn't offered.
        assertEquals(emptyList<CarrierId>(), start(parcel(CarrierId.Unknown)).pickerSections(emptyList())[0].carriers)
        // A number of several shapes lists the carriers it fits.
        assertEquals("picker.section.fits", start(parcel(CarrierId.Unknown, number = "76434219")).pickerSections(emptyList())[1].titleKey)
    }
}
