package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ShopLinkFailure
import com.plhery.peekdeliverytracker.core.api.ShopLinkParcel
import com.plhery.peekdeliverytracker.core.api.ShopLinkParcelConfidence
import com.plhery.peekdeliverytracker.core.api.ShopLinkResponse
import com.plhery.peekdeliverytracker.core.api.ShopPlatform
import com.plhery.peekdeliverytracker.core.api.UnresolvedShopLink
import java.util.Locale
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * A shop's tracking-page link in Add (the web's `useShopLinkReading` and `ShopLinkNotice`, the scraper's
 * `shopTrackingLinks`): which links are a shop's, what Add sends of a text, what an answer says, the notices and the
 * button's words, and the requests that read a link or wait for its shop. Every link is made up.
 */
class ShopLinksTests {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://peek.example", supabaseUrl = "https://peek.example", supabasePublishableKey = "public",
        googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "harness.test",
    )

    private fun client(transport: HttpTransport) = DeliveryApiClient(
        configuration, TestSession(), transport,
        NativeVerification(
            NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) }),
            TurnstilePresenter { throw NativeVerificationFailed() }, emptyMap(), {}, language = { AppLanguage.En },
        ),
        deviceName = "Synthetic phone", region = { Locale.GERMANY },
    )

    private val shopPage = "https://hm.delivery-status.com/de/de/?s=synthetic-key"

    private fun bodyOf(request: HttpRequest): JsonObject = Json.parseToJsonElement(request.body!!.decodeToString()).jsonObject

    private fun unresolved(shop: String? = "H&M", waitable: Boolean = true, reason: ShopLinkFailure = ShopLinkFailure.NoTrackingYet) =
        UnresolvedShopLink(platform = ShopPlatform.Parcellab, platformName = "parcelLab", shopName = shop, reason = reason, waitable = waitable)

    private fun parcel(number: String, carrier: String) = ShopLinkParcel(trackingNumber = number, carrier = carrier, confidence = ShopLinkParcelConfidence.High)

    // Which links are a shop's (each checked against the scraper's `shopTrackingLinks` and the WHATWG parser)

    @Test
    fun aShopsOwnPageAndParcelLabsPortalAreShopLinks() {
        val accepted = listOf(
            shopPage,
            "https://delivery-status.com/x",
            "http://www.example.delivery-status.com/",
            "HTTPS://EXAMPLE.DELIVERY-STATUS.COM/a",
            "https://prtl.parcellab.com/trackings/details?trackingNo=synthetic",
            "https://prtl.parcellab.com/TRACKINGS/x",
            "https://someone@hm.delivery-status.com/",
            "https://user:pa@ss@hm.delivery-status.com/",
            "https://@hm.delivery-status.com/",
            "https://hm.delivery-status.com:443/x",
            "https://hm.delivery-status.com:/x",
            "https://hm.delivery-status.com:0443/x",
            "https://\\hm.delivery-status.com/",
            "https:///hm.delivery-status.com/",
            "https://hm.delivery%2Dstatus.com/",
            "https://hm.delivery-status.com",
            "https://hm.delivery-status.com#frag",
            "https://hm.delivery-status.com?q",
            "https://.delivery-status.com/",
            "https://a_b.delivery-status.com/",
            "https://prtl.parcellab.com/a/../trackings/x",
            "https://prtl.parcellab.com/trackings/./x",
            "https://prtl.parcellab.com/trackings/%2e",
            "https://prtl.parcellab.com/%2e%2E/trackings/x",
            "https://prtl.parcellab.com\\trackings\\x",
        )
        for (link in accepted) assertEquals(link, listOf(link), ShopTrackingLinks.links(link))
    }

    @Test
    fun lookalikesAndOtherHostsAreNone() {
        val refused = listOf(
            "https://delivery-status.com.example.test/",
            "https://hm.delivery-status.com.example.test/de/de/?s=synthetic-key",
            "https://evil.example/delivery-status.com",
            "https://evil.example/?next=https%3A%2F%2Fhm.delivery-status.com%2F",
            "https://evil-delivery-status.com/",
            "https://delivery-status.com@evil.example/",
            "https://evil.example\\@hm.delivery-status.com/",
            "https://evil.example#@hm.delivery-status.com/",
            "https://prtl.parcellab.com/track/x",
            "https://prtl.parcellab.com/trackings",
            "https://prtl.parcellab.com/x/trackings/",
            "https://prtl.parcellab.com/trackings/../x",
            "https://prtl.parcellab.com/%74rackings/x",
            "https://prtl.parcellab.com/tracking\u017F/x",
            "https://parcellab.com/trackings/x",
            "https://www.prtl.parcellab.com/trackings/x",
            "https://prtl.parcellab.com.example.test/trackings/x",
            "ftp://hm.delivery-status.com/",
            "hm.delivery-status.com/de/de/",
            "https://hm.delivery-status.com:99999/",
            "https://hm.delivery-status.com:8x/",
            "https://hm.delivery-status.com./",
            "https://[::1]/",
            "https://hm.delivery-status.com%/",
            "https://hm.delivery-status.com%25/",
            "https://hm.d\u00E9livery-status.com/",
            "https://hm.delivery-status.com\u0085/x",
            "https://hm.delivery-status.c\u03BFm/",
        )
        for (link in refused) assertEquals(link, emptyList<String>(), ShopTrackingLinks.links(link))
    }

    @Test
    fun linksAreFoundInProseAndInAnEmailsHtml() {
        assertEquals(
            listOf("https://hm.delivery-status.com/x"),
            ShopTrackingLinks.links("Your order is on its way. Follow package (https://hm.delivery-status.com/x)."),
        )
        assertEquals(listOf("https://hm.delivery-status.com/?a=1&b=2"), ShopTrackingLinks.links("""<a href="https://hm.delivery-status.com/?a=1&amp;b=2">Follow package</a>"""))
        assertEquals(listOf("https://hm.delivery-status.com/x"), ShopTrackingLinks.links("https://hm.delivery-status.com/x\u3000more"))
        // The full stop that ends a sentence is no part of the link.
        assertEquals(listOf("https://prtl.parcellab.com/trackings/"), ShopTrackingLinks.links("See https://prtl.parcellab.com/trackings/."))
        assertEquals(
            listOf("https://hm.delivery-status.com/x", "https://hm.delivery-status.com/y"),
            ShopTrackingLinks.links("https://hm.delivery-status.com/x https://hm.delivery-status.com/y"),
        )
        assertEquals(emptyList<String>(), ShopTrackingLinks.links("Your parcel ships soon. Order 1234-5678."))
    }

    @Test
    fun addSendsTheFirstThreeLinksAloneOneToALine() {
        val email = """
            Hello Synthetic Reader, your order from Example Street 1 is on its way.
            https://hm.delivery-status.com/a https://hm.delivery-status.com/a
            https://prtl.parcellab.com/trackings/b https://x.delivery-status.com/c https://y.delivery-status.com/d
        """.trimIndent()
        assertEquals(
            "https://hm.delivery-status.com/a\nhttps://prtl.parcellab.com/trackings/b\nhttps://x.delivery-status.com/c",
            ShopTrackingLinks.query(email),
        )
        assertNull(ShopTrackingLinks.query("https://example.com/track/123"))
        assertNull(ShopTrackingLinks.query(""))
    }

    @Test
    fun addReadsALinkOnlyWhereTheFieldHoldsNoNumber() {
        val catalog = CarrierCatalog.shared
        assertEquals(shopPage, ParcelAddition(trackingInput = shopPage, catalog = catalog).shopLinkQuery)
        assertEquals(shopPage, ParcelAddition(trackingInput = "Follow package: $shopPage", catalog = catalog).shopLinkQuery)
        // A number of the field's own comes first, as the web's `applies` says.
        assertNull(ParcelAddition(trackingInput = "1Z999AA10123456784 $shopPage", catalog = catalog).shopLinkQuery)
        assertNull(ParcelAddition(trackingInput = "https://delivery-status.com.example.test/", catalog = catalog).shopLinkQuery)
        assertNull(ParcelAddition(trackingInput = "", catalog = catalog).shopLinkQuery)
    }

    // What an answer says

    @Test
    fun aParcelFillsTheNumberInWithItsCarrierWhereTheCatalogKnowsIt() {
        val catalog = CarrierCatalog.shared
        assertTrue(CarrierId("dhl") in catalog.definitions)
        assertEquals(
            ShopLinkReading.Found("JJD000390007000000001", CarrierId("dhl")),
            ShopLinkReading.answer(ShopLinkResponse(links = 1, parcels = listOf(parcel("JJD000390007000000001", "dhl")), unresolved = emptyList()), catalog),
        )
        // "unknown", or a carrier this catalog doesn't hold, leaves the carrier to Peek's own check.
        assertEquals(
            ShopLinkReading.Found("JJD000390007000000001", null),
            ShopLinkReading.answer(ShopLinkResponse(links = 1, parcels = listOf(parcel("JJD000390007000000001", "unknown")), unresolved = emptyList()), catalog),
        )
        assertEquals(
            ShopLinkReading.Found("JJD000390007000000001", null),
            ShopLinkReading.answer(ShopLinkResponse(links = 1, parcels = listOf(parcel("JJD000390007000000001", "not-a-carrier-yet")), unresolved = emptyList()), catalog),
        )
        // A parcel beats a link left unresolved, and a blank number is no parcel.
        assertEquals(
            ShopLinkReading.Found("JJD000390007000000001", CarrierId("dhl")),
            ShopLinkReading.answer(
                ShopLinkResponse(links = 2, parcels = listOf(parcel(" ", "dhl"), parcel("JJD000390007000000001", "dhl")), unresolved = listOf(unresolved())),
                catalog,
            ),
        )
    }

    @Test
    fun noParcelIsTheLinkLeftUnresolvedOrNoAnswer() {
        assertEquals(
            ShopLinkReading.Unresolved(unresolved()),
            ShopLinkReading.answer(ShopLinkResponse(links = 1, parcels = emptyList(), unresolved = listOf(unresolved(), unresolved(shop = null)))),
        )
        assertEquals(
            ShopLinkReading.Unresolved(unresolved()),
            ShopLinkReading.answer(ShopLinkResponse(links = 1, parcels = listOf(parcel("", "dhl")), unresolved = listOf(unresolved()))),
        )
        assertEquals(ShopLinkReading.Failed, ShopLinkReading.answer(ShopLinkResponse(links = 0, parcels = emptyList(), unresolved = emptyList())))
    }

    @Test
    fun aReadingIsKeptWithoutItsLink() {
        for (reading in listOf(ShopLinkReading.Failed, ShopLinkReading.Unresolved(unresolved()), ShopLinkReading.Unresolved(unresolved(shop = null, waitable = false)))) {
            val kept = ShopLinkReading.kept(reading)!!
            assertFalse(kept, kept.contains("delivery-status"))
            assertEquals(reading, ShopLinkReading.restored(kept))
        }
        assertNull(ShopLinkReading.kept(ShopLinkReading.Reading))
        assertNull(ShopLinkReading.kept(ShopLinkReading.Found("JJD000390007000000001", null)))
        assertNull(ShopLinkReading.restored("not json"))
    }

    // What Add says

    @Test
    fun theNoticesSayWhatAddReadsInTheSheetsInkWhereNothingIsAtFault() {
        val en = Localizer(AppLanguage.En)
        assertEquals(ShopLinkNotice(null, "Reading the shop’s tracking page…", calm = true), ShopLinkReading.Reading.notice(en, signedIn = true))
        assertNull(ShopLinkReading.Found("JJD000390007000000001", null).notice(en, signedIn = true))
        assertEquals(
            ShopLinkNotice(
                "H&M hasn’t named a parcel on this page yet: it hasn’t shipped.",
                "Peek checks every few hours for 14 days and adds the parcel as soon as a carrier and tracking number appear.",
                calm = true,
            ),
            ShopLinkReading.Unresolved(unresolved()).notice(en, signedIn = true),
        )
        assertEquals(
            ShopLinkNotice(
                "The shop hasn’t named a parcel on this page yet: it hasn’t shipped.",
                "Sign in, and Peek waits for it: it checks every few hours for 14 days.",
                calm = true,
            ),
            ShopLinkReading.Unresolved(unresolved(shop = null)).notice(en, signedIn = false),
        )
        assertEquals("The shop hasn’t named a parcel on this page yet: it hasn’t shipped.", ShopLinkReading.Unresolved(unresolved(shop = "")).notice(en, signedIn = true)?.title)
        assertEquals(
            ShopLinkNotice(null, "Peek can’t read this shop’s tracking page. Paste the tracking number from the shipping email.", calm = false),
            ShopLinkReading.Unresolved(unresolved(waitable = false, reason = ShopLinkFailure.Unsupported)).notice(en, signedIn = true),
        )
        assertEquals(
            ShopLinkNotice(null, "The shop’s tracking page isn’t answering. Try again in a little while.", calm = false),
            ShopLinkReading.Failed.notice(en, signedIn = false),
        )
        assertEquals("Peek checks\nevery few hours", ShopLinkNotice("Peek checks", "every few hours", calm = true).spoken)
        assertEquals("Reading", ShopLinkNotice(null, "Reading", calm = true).spoken)
    }

    @Test
    fun addOffersToWaitSignedInForALinkThatCanBeWaitedFor() {
        val en = Localizer(AppLanguage.En)
        assertEquals("Wait for H&M", ShopLinkReading.Unresolved(unresolved()).waitWords(en, signedIn = true))
        assertEquals("Wait for the shop", ShopLinkReading.Unresolved(unresolved(shop = null)).waitWords(en, signedIn = true))
        assertNull(ShopLinkReading.Unresolved(unresolved()).waitWords(en, signedIn = false))
        assertNull(ShopLinkReading.Unresolved(unresolved(waitable = false)).waitWords(en, signedIn = true))
        assertNull(ShopLinkReading.Reading.waitWords(en, signedIn = true))
        assertNull(ShopLinkReading.Failed.waitWords(en, signedIn = true))
        assertNull((null as ShopLinkReading?).waitWords(en, signedIn = true))
    }

    @Test
    fun theWordsReadInEveryLanguage() {
        val readings = listOf(
            ShopLinkReading.Reading, ShopLinkReading.Failed, ShopLinkReading.Unresolved(unresolved()), ShopLinkReading.Unresolved(unresolved(shop = null)),
            ShopLinkReading.Unresolved(unresolved(waitable = false)),
        )
        for (language in AppLanguage.entries) {
            val localizer = Localizer(language)
            for (reading in readings) {
                for (signedIn in listOf(true, false)) {
                    val notice = reading.notice(localizer, signedIn)!!
                    val words = listOfNotNull(notice.title, notice.body, reading.waitWords(localizer, signedIn))
                    for (text in words) assertFalse("${language.rawValue}: $text", text.contains("{{") || text.startsWith("shopLink.") || text.isBlank())
                }
            }
            assertTrue(language.rawValue, ShopLinkReading.Unresolved(unresolved()).waitWords(localizer, signedIn = true)!!.contains("H&M"))
        }
    }

    // The requests

    @Test
    fun signedInALinkIsReadWithTheAccountsToken() = runTest {
        val transport = RecordingTransport {
            jsonResponse(200, """{"links":1,"parcels":[],"unresolved":[{"platform":"parcellab","platform_name":"parcelLab","shop_name":"H&M","reason":"no_tracking_yet","waitable":true}]}""")
        }
        val answer = client(transport).readShopLink(shopPage)

        assertEquals(ShopLinkResponse(links = 1, parcels = emptyList(), unresolved = listOf(unresolved())), answer)
        val request = transport.requests.single()
        assertEquals("POST https://peek.example/api/shop-links", "${request.method} ${request.url}")
        assertEquals("Bearer synthetic-access", request.header("Authorization"))
        assertEquals("application/json", request.header("Content-Type"))
        assertEquals(JsonObject(mapOf("text" to JsonPrimitive(shopPage))), bodyOf(request))
    }

    @Test
    fun aGuestsLinkIsReadByThePublicRequestAsTheAppsOtherPublicCallsSendIt() = runTest {
        val transport = RecordingTransport {
            jsonResponse(200, """{"links":1,"parcels":[{"tracking_number":"JJD000390007000000001","carrier":"dhl","confidence":"high"}],"unresolved":[]}""")
        }
        val answer = DeliveryApiClient.publicShopLink(shopPage, configuration, transport)

        assertEquals(listOf(parcel("JJD000390007000000001", "dhl")), answer.parcels)
        val request = transport.requests.single()
        assertEquals("POST https://peek.example/api/public/shop-links", "${request.method} ${request.url}")
        assertEquals("application/json", request.header("Accept"))
        assertEquals("application/json", request.header("Content-Type"))
        assertEquals("XMLHttpRequest", request.header("X-Requested-With"))
        assertEquals(CLIENT_INPUTS, request.header("X-Peek-Inputs"))
        // No account, no cookie, and neither Origin nor Sec-Fetch-Site, which the server's cross-site rule reads.
        assertNull(request.header("Authorization"))
        assertNull(request.header("Origin"))
        assertNull(request.header("Sec-Fetch-Site"))
        assertFalse(request.handlesCookies)
        assertEquals(JsonObject(mapOf("text" to JsonPrimitive(shopPage))), bodyOf(request))
    }

    @Test
    fun thePublicRequestsFailuresAreTheClientsOwn() = runTest {
        val limited = runCatching { DeliveryApiClient.publicShopLink(shopPage, configuration, RecordingTransport { jsonResponse(429, "{}", mapOf("Retry-After" to "60")) }) }.exceptionOrNull()
        assertEquals(60.0, (limited as DeliveryApiError.RateLimited).retryAfter, 0.0)
        val failed = runCatching { DeliveryApiClient.publicShopLink(shopPage, configuration, RecordingTransport { jsonResponse(500) }) }.exceptionOrNull()
        assertTrue(failed is DeliveryApiError.ServiceFailed)
        val garbled = runCatching { DeliveryApiClient.publicShopLink(shopPage, configuration, RecordingTransport { jsonResponse(200, "<html>") }) }.exceptionOrNull()
        assertTrue(garbled is DeliveryApiError.InvalidResponse)
    }

    @Test
    fun anAnswerIsReadLeniently() = runTest {
        // A link on a platform a newer server adds, or a parcel this version can't read, drops out of the answer.
        val body = """{"links":3,"parcels":[{"tracking_number":"JJD000390007000000001"},{"tracking_number":"JJD000390007000000002","carrier":"dhl","confidence":"high"}],""" +
            """"unresolved":[{"platform":"narvar","platform_name":"Narvar","reason":"unsupported","waitable":false},{"platform":"parcellab","platform_name":"parcelLab","reason":"no_tracking_yet","waitable":true}]}"""
        val answer = client(RecordingTransport { jsonResponse(200, body) }).readShopLink(shopPage)
        assertEquals(listOf(parcel("JJD000390007000000002", "dhl")), answer.parcels)
        assertEquals(listOf(unresolved(shop = null)), answer.unresolved)
    }

    @Test
    fun waitingSendsTheLinksAndTheNameAndAnswersTheKeptWaits() = runTest {
        val transport = RecordingTransport {
            jsonResponse(
                200,
                """{"shop_waits":[{"id":"3a1e0c3a-7b2d-4c8e-9a61-0d4b8e2f7c15","platform":"parcellab","platform_name":"parcelLab","shop_name":"H&M","label":"Wool coat",""" +
                    """"created_at":"2026-10-29T10:11:00.000Z","expires_at":"2026-11-12T10:11:00.000Z","expired_at":null}]}""",
            )
        }
        val kept = client(transport).keepShopWait(shopPage, "Wool coat")

        assertEquals(listOf("Wool coat"), kept.map { it.label })
        val request = transport.requests.single()
        assertEquals("POST https://peek.example/api/shop-waits", "${request.method} ${request.url}")
        assertEquals("Bearer synthetic-access", request.header("Authorization"))
        assertEquals(JsonObject(mapOf("text" to JsonPrimitive(shopPage), "label" to JsonPrimitive("Wool coat"))), bodyOf(request))

        val failed = runCatching { client(RecordingTransport { jsonResponse(500) }).keepShopWait(shopPage, "") }.exceptionOrNull()
        assertTrue(failed is DeliveryApiError.ServiceFailed)
    }

    @Test
    fun theLinksRequestsCountAsNoEvent() {
        val catalog = AnalyticsCatalog.bundled!!
        val body = """{"text":"$shopPage"}""".toByteArray()
        assertNull(catalog.event("/api/shop-links", "POST", body))
        assertNull(catalog.event("/api/public/shop-links", "POST", body))
        assertNull(catalog.event("/api/shop-waits", "POST", body))
    }
}
