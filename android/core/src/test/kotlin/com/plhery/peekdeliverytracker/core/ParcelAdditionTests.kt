package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponseAmazonShippingStatus
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.TrackingProviderCheck
import com.plhery.peekdeliverytracker.core.api.TrackingProviderCheckOutcome
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** The Add sheet's arithmetic (AddParcelView.swift's derived state), on synthetic numbers. */
class ParcelAdditionTests {
    private val catalog = CarrierCatalog(cacheFile = null)
    private val localizer = Localizer(AppLanguage.En)

    private fun addition(input: String, region: String? = "CH") =
        ParcelAddition(trackingInput = input, catalog = catalog, region = region)

    @Test
    fun aNumberANamedCarrierClaimsSaysSoAndCanBeAdded() {
        val ups = addition("1Z999AA10123456784")
        assertEquals("1Z999AA10123456784", ups.normalizedNumber)
        assertEquals(CarrierId.Ups, ups.resolvedCarrier)
        assertEquals(CarrierId.Ups, ups.numberCarrier)
        assertFalse(ups.recognizable)
        assertEquals("UPS", ups.lineName(localizer))
        assertEquals(localizer.text("add.detectedCarrier"), ups.lineDetail(localizer))
        assertEquals(localizer.text("picker.auto.detected", mapOf("carrier" to "UPS")), ups.automaticDescription(localizer))
        // UPS tracks by itself: no hint under the line.
        assertNull(ups.hint(localizer))
        assertTrue(ups.canSave)
        assertEquals(ParcelAddition.Field.Label, ups.nextField)
        assertNull(ups.sentTrackingUrl)
        assertNull(ups.sentPostcode)
    }

    @Test
    fun anEmptyOrUnreadableFieldAddsNothing() {
        val empty = addition("")
        assertFalse(empty.notFound)
        assertFalse(empty.canSave)
        assertEquals(localizer.text("add.detect"), empty.lineName(localizer))
        assertNull(empty.lineDetail(localizer))
        val words = addition("hello there")
        assertTrue(words.notFound)
        assertFalse(words.canSave)
    }

    @Test
    fun anAmbiguousNumberIsCheckedOnceSettledAndTheLineSaysHowItWent() {
        val number = "12345478901"
        var form = addition(number)
        assertTrue(form.recognizable)
        assertEquals(CarrierRecognition.Status.Idle, form.recognitionStatus)
        assertNull(form.recognitionRequest)
        assertEquals(localizer.text("picker.auto.later"), form.automaticDescription(localizer))
        // The check never holds the button.
        assertTrue(form.canSave)

        form = form.settling(number)
        assertEquals(number, form.recognitionRequest)
        assertTrue(form.checking)
        assertEquals(localizer.text("add.checkingServices"), form.lineDetail(localizer))

        val asked = listOf(CarrierId.GlsCh, CarrierId.GlsDe)
        val found = form.copy(recognition = form.recognition.copy(answer = CarrierDetectionResponse(number, carrier = CarrierId.GlsCh, asked = asked)))
        assertEquals(CarrierId.GlsCh, found.resolvedCarrier)
        assertEquals(localizer.text("add.line.found"), found.lineDetail(localizer))
        assertEquals(mapOf(CarrierId.GlsCh to "picker.tag.found"), found.pickerTags)
        // GLS Switzerland asks for the postcode, which it needs.
        assertTrue(found.needsRequiredDetails)
        assertFalse(found.canSave)
        assertEquals(ParcelAddition.Field.DeliveryPostcode, found.nextField)
        assertTrue(found.copy(deliveryPostcode = "8000").canSave)

        val several = form.copy(
            recognition = form.recognition.copy(
                answer = CarrierDetectionResponse(number, carrier = CarrierId.Unknown, recognized = asked, asked = asked),
            ),
        )
        assertTrue(several.choosingCarrier)
        val names = several.carrierNames(asked, localizer)
        assertTrue(names, names.contains(" and "))
        assertEquals(localizer.text("add.line.several", mapOf("carriers" to names)), several.lineDetail(localizer))
        assertEquals(localizer.text("picker.auto.several", mapOf("carriers" to names)), several.automaticDescription(localizer))
        assertEquals(asked, several.pickerSections(emptyList()).first { it.id == "known" }.carriers)
        assertEquals(asked.associateWith { "picker.tag.knows" }, several.pickerTags)

        val none = form.copy(recognition = form.recognition.copy(answer = CarrierDetectionResponse(number, carrier = CarrierId.Unknown, asked = asked)))
        assertEquals(localizer.text("add.line.none"), none.lineDetail(localizer))
        val failed = form.copy(recognition = form.recognition.copy(failedNumber = number))
        assertEquals(localizer.text("add.line.failed"), failed.lineDetail(localizer))
        assertEquals(localizer.text("add.line.failed"), failed.automaticDescription(localizer))
        val unasked = form.copy(recognition = form.recognition.copy(answer = CarrierDetectionResponse(number, carrier = CarrierId.Unknown)))
        assertEquals(localizer.text("add.line.later"), unasked.lineDetail(localizer))

        // What tracking services found, while no carrier knows it.
        val history = form.copy(
            recognition = form.recognition.copy(
                answer = CarrierDetectionResponse(
                    number, carrier = CarrierId.Unknown, trackingFound = true,
                    providers = listOf(TrackingProviderCheck(provider = "service", outcome = TrackingProviderCheckOutcome.NoHistory)),
                ),
            ),
        )
        assertEquals(localizer.text("add.historyFound"), history.lineDetail(localizer))
        assertEquals(localizer.text("add.historyFound"), history.automaticDescription(localizer))
    }

    @Test
    fun aCarrierPickedByHandIsSaidSoAndAskedAboutOnceSettled() {
        val number = "12345478901"
        val picked = addition(number).settling(number).copy(carrierOverride = CarrierId.Dpd)
        assertEquals(CarrierId.Dpd, picked.resolvedCarrier)
        assertFalse(picked.checking)
        assertEquals(localizer.text("add.line.chosen"), picked.lineDetail(localizer))
        val question = NumberRefusalRequest(trackingNumber = number, carrier = CarrierId.Dpd)
        assertEquals(question, picked.refusalQuestion)
        assertFalse(picked.refused)
        val refused = picked.copy(refusedQuestion = question)
        assertTrue(refused.refused)
        assertEquals(localizer.text("add.line.refused"), refused.lineDetail(localizer))
        // Not settled yet, nothing is asked; nor in the demo.
        assertNull(addition(number).copy(carrierOverride = CarrierId.Dpd).refusalQuestion)
        assertNull(picked.copy(demo = true).refusalQuestion)

        // The carrier the check found stays a hint beside the one picked.
        val found = picked.copy(recognition = picked.recognition.copy(answer = CarrierDetectionResponse(number, carrier = CarrierId.GlsCh)))
        assertEquals(CarrierId.GlsCh, found.knownElsewhere)
        assertNull(found.copy(carrierOverride = CarrierId.GlsCh).knownElsewhere)

        // Typing again drops the carrier picked.
        assertNull(picked.typing("$number ").carrierOverride)
    }

    @Test
    fun severalNumbersInOneTextListTheFirstUnlessAnotherIsChosen() {
        val text = "UPS 1ZDEMO202600000001\nUPS 1ZDEMO202600000029"
        val form = addition(text)
        assertEquals(listOf("1ZDEMO202600000001", "1ZDEMO202600000029"), form.several.map { it.normalized })
        assertEquals("1ZDEMO202600000001", form.normalizedNumber)
        assertNull(form.order)
        val chosen = form.copy(pick = "1ZDEMO202600000029")
        assertEquals("1ZDEMO202600000029", chosen.normalizedNumber)
        // A number chosen stays chosen while the text still names it.
        assertEquals("1ZDEMO202600000029", chosen.typing("$text\nthanks").pick)
        assertNull(chosen.typing("UPS 1ZDEMO202600000001").pick)
        assertEquals("1ZDEMO202600000001", ParcelAddition.trackingNumber(text, catalog))
        // A number chosen among several is certain of its carrier: no check-digit question.
        assertNull(form.typo)
    }

    @Test
    fun aPickupCodeAPlaceholderAndAnOrderNumberAreHeldBack() {
        val pickup = addition("204 617")
        assertTrue(pickup.pickupCode)
        assertFalse(pickup.canSave)
        // A carrier picked by hand takes the six digits as its number.
        assertFalse(pickup.copy(carrierOverride = CarrierId.Dpd).pickupCode)

        val placeholder = addition("RR123456785CH")
        assertTrue(placeholder.placeholderNumber)
        assertFalse(placeholder.canSave)
        assertFalse(placeholder.recognizable)
        // The demo looks nothing up, and takes it.
        assertFalse(placeholder.copy(demo = true).placeholderNumber)

        val order = addition("402-9182736-4554637")
        assertEquals(OrderShop.Amazon, order.order)
        assertEquals("", order.parsed.trackingNumber)
        assertFalse(order.notFound)
        assertFalse(order.canSave)
        assertEquals("", ParcelAddition.trackingNumber("402-9182736-4554637", catalog))
    }

    @Test
    fun aCheckDigitThatDoesNotAddUpIsAskedAboutUntilAddedAsTyped() {
        val form = addition("LX1234567B5DE")
        assertEquals(CheckDigitTrouble(suggestion = "LX123456785DE"), form.typo)
        assertNull(form.copy(asTyped = "LX1234567B5DE").typo)
    }

    @Test
    fun anAmazonNumberWaitsForAmazonsAnswer() {
        val number = "FR4829173650"
        val form = addition(number)
        assertTrue(form.amazonNumber)
        assertFalse(form.recognizable)
        assertEquals(number, form.lookupTrackingNumber)
        assertEquals(CarrierId.AmazonLogistics, form.resolvedCarrier)
        assertTrue(form.accountRequired)
        assertFalse(form.canSave)
        assertEquals(localizer.text("add.amazonAccount", mapOf("carrier" to catalog.info(CarrierId.AmazonLogistics, AppLanguage.En).displayName)), form.hint(localizer))

        val confirmed = form.copy(
            verifiedCarrier = CarrierDetectionResponse(number, CarrierDetectionResponseAmazonShippingStatus.Available, CarrierId.AmazonShipping),
        )
        assertTrue(confirmed.shippingConfirmed)
        assertFalse(confirmed.accountRequired)
        assertEquals(CarrierId.AmazonShipping, confirmed.resolvedCarrier)
        assertTrue(confirmed.canSave)
        assertEquals(localizer.text("add.detectedCarrier"), confirmed.lineDetail(localizer))
        val name = catalog.info(CarrierId.AmazonShipping, AppLanguage.En).displayName
        assertEquals(localizer.text("add.amazonShippingConfirmed", mapOf("carrier" to name)), confirmed.hint(localizer))
        val expired = form.copy(
            verifiedCarrier = CarrierDetectionResponse(number, CarrierDetectionResponseAmazonShippingStatus.Expired, CarrierId.AmazonShipping),
        )
        assertEquals(localizer.text("add.amazonHistoryExpired", mapOf("carrier" to name)), expired.hint(localizer))

        val unavailable = form.copy(
            verifiedCarrier = CarrierDetectionResponse(number, CarrierDetectionResponseAmazonShippingStatus.Unavailable, CarrierId.AmazonLogistics),
        )
        assertTrue(unavailable.amazonCheckUnavailable)
        assertTrue(unavailable.accountRequired)
        assertFalse(unavailable.canSave)
        // Another number's answer is not this one's.
        assertNull(form.copy(verifiedCarrier = confirmed.verifiedCarrier!!.copy(trackingNumber = "DE4829173650")).currentVerification)
        // The demo asks Amazon nothing.
        assertNull(form.copy(demo = true).lookupTrackingNumber)
    }

    @Test
    fun aRequiredLinkIsAskedForUnlessTheTextHadIt() {
        val planzer = addition("9999012345678")
        assertEquals(CarrierId.Planzer, planzer.resolvedCarrier)
        assertTrue(planzer.asksTrackingUrl)
        assertFalse(planzer.canSave)
        assertEquals(ParcelAddition.Field.TrackingUrl, planzer.nextField)
        assertFalse(planzer.copy(trackingUrl = "http://trackandtrace.planzergroup.com/shared/1").canSave)
        val link = "https://trackandtrace.planzergroup.com/shared/abc?accessKey=xyz"
        assertTrue(planzer.trackingUrlRequirement!!.accepts(link))
        val filled = planzer.copy(trackingUrl = link)
        assertTrue(filled.canSave)
        assertEquals(link, filled.sentTrackingUrl)
        assertEquals(ParcelAddition.Field.Label, filled.nextField)
    }

    @Test
    fun aPostcodeIsAskedForWithItsCountryAndItsLimits() {
        val number = "12345478901"
        val gls = addition(number).copy(carrierOverride = CarrierId.GlsCh)
        val requirement = gls.postcodeRequirement
        assertNotNull(requirement)
        assertFalse(requirement!!.isOptional)
        assertEquals("8000", gls.postcodeExample(requirement))
        assertEquals(CarrierChange.PostcodeKeys.NumberPad, gls.postcodeKeys(requirement))
        assertFalse(gls.copy(deliveryPostcode = "800").canSave)
        assertTrue(gls.copy(deliveryPostcode = "8000").canSave)
        assertFalse(gls.copy(deliveryPostcode = "80000").canSave)
        assertEquals("8000", gls.copy(deliveryPostcode = "8000").sentPostcode)

        // DPD's is optional, and its lookup reads the postcode alone: no country beside it.
        val dpd = addition(number, region = "DE").copy(carrierOverride = CarrierId.Dpd)
        val optional = dpd.postcodeRequirement!!
        assertTrue(optional.isOptional)
        assertTrue(dpd.canSave)
        // Next still goes to it while it is empty, as `submitTracking()` asks the field to accept what it holds.
        assertEquals(ParcelAddition.Field.DeliveryPostcode, dpd.nextField)
        assertEquals(ParcelAddition.Field.Label, dpd.copy(deliveryPostcode = "10115").nextField)
        assertNull(dpd.offeredPostcodeCountry)
        assertEquals("CH", dpd.postcodeCountry)
        // A country the field doesn't take falls back to where it starts.
        assertEquals("CH", dpd.copy(chosenPostcodeCountry = "FR").postcodeCountry)
        assertNull(dpd.copy(deliveryPostcode = "8000").sentPostcodeCountry)

        // Mondial Relay's lookup reads the country too: it stands beside the field, the reader's first.
        val relay = addition("76434219", region = "BE").copy(carrierOverride = CarrierId.MondialRelay)
        assertEquals("BE", relay.offeredPostcodeCountry)
        val dutch = relay.copy(chosenPostcodeCountry = "NL", deliveryPostcode = "1011 AB")
        assertEquals("NL", dutch.postcodeCountry)
        assertTrue(dutch.canSave)
        assertEquals("NL", dutch.sentPostcodeCountry)
        // The carrier's home country goes without saying.
        assertNull(relay.copy(chosenPostcodeCountry = "FR", deliveryPostcode = "75001").sentPostcodeCountry)

        // The last postcode given that fits fills a required field.
        val given = listOf(GivenPostcode(carrier = CarrierId.GlsCh, postcode = "3011"))
        assertEquals("3011", gls.previousPostcode(requirement, given))
    }

    @Test
    fun theFieldSettlesOnTheNumberAPasteOrADraftNames() {
        val form = addition("").typing("1Z999AA10123456784").settling("1Z999AA10123456784")
        assertTrue(form.settled)
        assertEquals("1Z999AA10123456784", form.recognition.settledNumber)
        assertFalse(form.typing("1Z999AA1012345678").settled)
    }

    @Test
    fun carrierNamesAreAListInTheAppsLanguage() {
        val carriers = listOf(CarrierId.GlsCh, CarrierId.GlsDe, CarrierId.Dpd)
        val names = carriers.map { catalog.info(it, AppLanguage.En).displayName }
        val form = addition("")
        assertEquals("${names[0]}, ${names[1]}, and ${names[2]}", form.carrierNames(carriers, localizer))
        val german = Localizer(AppLanguage.De)
        val germanNames = carriers.map { catalog.info(it, AppLanguage.De).displayName }
        assertEquals("${germanNames[0]}, ${germanNames[1]} und ${germanNames[2]}", form.carrierNames(carriers, german))
    }

    @Test
    fun theNameTakesEightyCharactersCountedAsTheReaderSeesThem() {
        assertEquals("a".repeat(80), ParcelAddition.label("a".repeat(81)))
        // A flag is one character of two code points: the eightieth whole, nothing after it.
        val flags = "\uD83C\uDDE8\uD83C\uDDED".repeat(81)
        assertEquals("\uD83C\uDDE8\uD83C\uDDED".repeat(80), ParcelAddition.label(flags))
        assertEquals("Shoes", ParcelAddition.label("Shoes"))
    }

    @Test
    fun aPasteOpensAddWithItsTextTrimmedAndCutToWhatAddTakes() {
        assertEquals("1Z999AA10123456784", FirstParcelRequest.Paste(" 1Z999AA10123456784\n").draft)
        assertNull(FirstParcelRequest.Paste(" \n ").draft)
        assertNull(FirstParcelRequest.Paste("").draft)
        assertEquals(FirstParcelRequest.pasteLimit, FirstParcelRequest.Paste("x".repeat(FirstParcelRequest.pasteLimit + 5)).draft?.length)
    }

    @Test
    fun theChecksAnswersAreKeptAsTextAndReadBack() {
        val answer = CarrierDetectionResponse(
            "12345478901", CarrierDetectionResponseAmazonShippingStatus.Unavailable, carrier = CarrierId.GlsCh,
            recognized = listOf(CarrierId.GlsCh, CarrierId.GlsDe), asked = listOf(CarrierId.GlsCh), unanswered = listOf(CarrierId.GlsDe),
        )
        assertEquals(answer, ParcelAddition.answer(ParcelAddition.keptAnswer(answer)))
        val question = NumberRefusalRequest("12345478901", CarrierId.Dpd)
        assertEquals(question, ParcelAddition.question(ParcelAddition.keptQuestion(question)))
        assertNull(ParcelAddition.answer("not an answer"))
        assertNull(ParcelAddition.question("{}"))
    }

    @Test
    fun whatTheFieldTakesIsCutToThePasteLimitCountedAsTheReaderSeesCharacters() {
        assertEquals(" 1Z999AA10123456784\n", FirstParcelRequest.taken(" 1Z999AA10123456784\n"))
        assertEquals("x".repeat(FirstParcelRequest.pasteLimit), FirstParcelRequest.taken("x".repeat(FirstParcelRequest.pasteLimit * 100)))
        // A flag is one character of two code points: the last one whole, nothing after it.
        val flag = "\uD83C\uDDE8\uD83C\uDDED"
        assertEquals(flag.repeat(FirstParcelRequest.pasteLimit), FirstParcelRequest.taken(flag.repeat(FirstParcelRequest.pasteLimit + 1)))
    }
}
