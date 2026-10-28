package com.plhery.peekdeliverytracker.core

import java.io.IOException
import java.util.UUID
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * AnalyticsTests.swift, then what the iPhone's sender does that its tests leave out: the queue, the collector's cache,
 * switching off, and the screens its lifecycle observer counts. Those expectations are what Analytics.swift does with the
 * same calls and answers, built with swiftc on macOS for this port.
 */
class AnalyticsTests {
    private val app = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://delivery.example", supabaseUrl = null, supabasePublishableKey = "",
        googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = false, appGroupIdentifier = "analytics.test",
    )
    private val website = "6F0D6C1E-2B7A-4C55-9E21-3A1B5C7D9E0F"
    private val web = "1802C52E-466B-47E6-AC7F-F5A497655B1B"
    private val ios = "2802C52E-466B-47E6-AC7F-F5A497655B1B"
    private val configurationAnswer =
        """{"endpoint":"https://analytics.example/api/send","hostname":"delivery.example","webWebsite":"$web","iosWebsite":"$ios","androidWebsite":"$website"}"""

    @Test
    fun sharedCatalogExcludesPollingAndPrivateInputs() {
        val catalog = AnalyticsCatalog.bundled!!
        assertEquals("parcel-rename", catalog.event("/api/packages/private-id?token=secret", "PATCH", null))
        assertNull(catalog.event("/api/sync/jobs?ids=private", "GET", null))
        assertNull(catalog.event("/api/push/devices", "POST", "{\"token\":\"secret\"}".toByteArray()))
        assertEquals("friend-invite-accept", catalog.event("/api/friends", "POST", "{\"action\":\"accept_invite\",\"code\":\"secret\"}".toByteArray()))
        assertNull(catalog.event("/api/friends", "POST", "{\"action\":\"private nickname\"}".toByteArray()))
        for (operation in catalog.operations) assertTrue(operation.event in catalog.actions)
    }

    @Test
    fun deliveryEmailNamesAreInTheSharedCatalog() {
        val catalog = AnalyticsCatalog.bundled!!
        // A name the catalog does not list is never sent.
        for (name in listOf("email-delivery-change", "email-offer-accept", "email-offer-decline", "email-example-open")) {
            assertTrue(name, name in catalog.actions)
        }
        assertEquals(
            "parcel-notifications-change",
            catalog.event("/api/packages/private-id/notifications", "PATCH", "{\"emailMuted\":true}".toByteArray()),
        )
        assertEquals("notifications-preferences", catalog.event("/api/push/preferences", "PATCH", "{\"emailOnDelivery\":true}".toByteArray()))
        assertNull(catalog.event("/api/push/preferences", "GET", null))
    }

    /** The device is the app's to read, so each platform is given; Android's take the place of the iPhone, iPad and Mac. */
    @Test
    fun payloadContainsOnlyFixedNamesAndDeviceMetadata() {
        val configuration = DeliveryAnalytics.Configuration("https://analytics.example/api/send", "delivery.example", UUID.randomUUID().uuidString)
        val event = DeliveryAnalytics.Event("parcel", "parcel-add", DeliveryAnalytics.Outcome.Success, DeliveryAnalytics.Mode.Account)
        val payload = DeliveryAnalytics.payload(event, configuration, DeliveryAnalytics.Platform.Android, "en")
        assertEquals("/parcel", payload.string("url"))
        assertEquals("parcel-add", payload.string("name"))
        assertEquals(DeliveryAnalytics.Platform.Android.rawValue, payload["data"]!!.jsonObject.string("platform"))
        for ((platform, device) in listOf(
            DeliveryAnalytics.Platform.Android to "mobile",
            DeliveryAnalytics.Platform.AndroidTablet to "tablet",
            DeliveryAnalytics.Platform.Chromebook to "desktop",
        )) {
            val payload = DeliveryAnalytics.payload(DeliveryAnalytics.Event("parcel", null, null, DeliveryAnalytics.Mode.Demo), configuration, platform, "en")
            assertEquals(platform.rawValue, payload["data"]!!.jsonObject.string("platform"))
            assertEquals(device, payload.string("device"))
        }
        for (key in listOf("id", "referrer", "userID", "email", "trackingNumber", "token")) assertNull(payload[key])
        assertTrue(configuration.valid("https://delivery.example"))
        assertFalse(configuration.valid("https://another.example"))
    }

    @Test
    fun configurationRejectsCredentialsAndUnencryptedCollection() {
        for (endpoint in listOf("http://analytics.example/api/send", "https://secret@analytics.example/api/send", "https://analytics.example/api/send?token=secret")) {
            val configuration = DeliveryAnalytics.Configuration(endpoint, "delivery.example", UUID.randomUUID().uuidString)
            assertFalse(configuration.valid("https://delivery.example"))
        }
    }

    /** Swift Regex reads a path by characters, and JSONSerialization a body in UTF-16 and with a first key kept. */
    @Test
    fun requestsAreReadAsTheIphoneReadsThem() {
        val catalog = AnalyticsCatalog.bundled!!
        assertNull(catalog.event("/api/packages/\u0301", "PATCH", null))
        assertEquals("parcel-rename", catalog.event("/api/packages/%2F/\u0301", "PATCH", null))
        assertNull(catalog.event("/api/sync\n", "POST", null))
        // A question mark with a mark on it is no query.
        assertNull(catalog.event("/api/sync?\u0301x", "POST", null))
        assertEquals("parcels-refresh", catalog.event("/api/sync?\u00E9", "POST", null))
        val friends = { body: ByteArray -> catalog.event("/api/friends", "POST", body) }
        assertEquals("friend-remove", friends(byteArrayOf(0xFF.toByte(), 0xFE.toByte()) + "{\"action\":\"remove_friend\"}".toByteArray(Charsets.UTF_16LE)))
        assertEquals("friend-remove", friends("{\"action\":\"remove_friend\",\"action\":\"disable\"}".toByteArray()))
        assertEquals("friend-remove", friends("{\"action\":\"remove_friend\",}".toByteArray()))
        assertNull(friends("{\"action\":\"remove_friend\",\"code\":secret}".toByteArray()))
    }

    @Test
    fun theConfigurationIsReadAsJsonDecoderReadsIt() {
        val decode = { body: String -> DeliveryAnalytics.Configuration.decode(body.toByteArray()) }
        assertNull(decode(" null "))
        assertEquals("delivery.example", decode(configurationAnswer.dropLast(1) + ",\"hostname\":\"another.example\"}")!!.hostname)
        assertEquals("delivery.example", decode(configurationAnswer.dropLast(1) + ",}")!!.hostname)
        for (body in listOf("", "[]", "{}", configurationAnswer.dropLast(1) + ",\"x\":abc}", configurationAnswer.replace("\"androidWebsite\"", "\"android_website\""),
            configurationAnswer.replace(",\"androidWebsite\":\"$website\"", ""))) {
            assertTrue(body, runCatching { decode(body) }.exceptionOrNull() is DecodingError)
        }
    }

    @Test
    fun startingReadsTheConfigurationThenCountsTheAppOpen() = runTest {
        val transport = collector()
        val analytics = analytics(transport)
        analytics.view("deliveries", DeliveryAnalytics.Mode.Account)
        analytics.start()
        runCurrent()
        val (configuration, view, open) = transport.requests
        assertEquals("https://delivery.example/api/analytics/config", configuration.url)
        assertEquals("GET", configuration.method)
        assertEquals(5.0, configuration.timeout, 0.0)
        for (request in listOf(view, open)) {
            assertEquals("https://analytics.example/api/send", request.url)
            assertEquals("POST", request.method)
            assertEquals(5.0, request.timeout, 0.0)
            assertEquals("application/json", request.header("Content-Type"))
            assertEquals(DeliveryAnalytics.userAgent(DeliveryAnalytics.Platform.Android, "1.2"), request.header("User-Agent"))
            assertNull(request.header("x-umami-cache"))
            assertEquals("event", request.json.string("type"))
        }
        assertEquals(
            """{"website":"$website","hostname":"delivery.example","url":"/deliveries","title":"deliveries","language":"fr-CH",""" +
                """"browser":"Delivery Android","os":"Android OS","device":"mobile","data":{"platform":"android","mode":"account"}}""",
            view.json["payload"].toString(),
        )
        assertEquals("app-open", open.payload.string("name"))
        assertEquals("/deliveries", open.payload.string("url"))
        assertEquals(3, transport.requests.size)
    }

    /**
     * The iPhone sends `Locale.preferredLanguages.first`, its language, script and region alone: Android 14's regional
     * preferences ride on the system's first language as extensions, which stay on the device.
     */
    @Test
    fun theLanguageIsSentAsTheIphonesLanguagesWriteIt() = runTest {
        for ((tag, sent) in listOf(
            "en-GB-u-fw-mon-mu-celsius" to "en-GB",
            "fr-CH-u-mu-fahrenhe-nu-latn" to "fr-CH",
            "zh-Hant-TW" to "zh-Hant-TW",
            "sr-Latn-RS-u-fw-sun" to "sr-Latn-RS",
            "es-419" to "es-419",
            "de-DE-1996" to "de-DE",
            "pt-BR-x-private" to "pt-BR",
            "he-IL" to "he-IL",
        )) {
            assertEquals(tag, sent, DeliveryAnalytics.languageTag(tag))
        }
        val transport = collector()
        val analytics = analytics(transport, languages = listOf("en-GB-u-fw-mon-mu-celsius", "fr-CH"))
        analytics.start()
        runCurrent()
        assertEquals("en-GB", transport.requests.last().payload.string("language"))
        val none = collector()
        analytics(none, languages = emptyList()).start()
        runCurrent()
        assertEquals("en", none.requests.last().payload.string("language"))
    }

    /** The iPhone reads `Platform.current` for each payload: a foldable opened into a tablet counts as one from then on. */
    @Test
    fun eachEventReportsTheFormTheDeviceHasWhenItGoes() = runTest {
        var platform = DeliveryAnalytics.Platform.Android
        val transport = collector()
        val analytics = analytics(transport, platform = { platform })
        analytics.start()
        runCurrent()
        platform = DeliveryAnalytics.Platform.AndroidTablet
        analytics.action("search")
        runCurrent()
        platform = DeliveryAnalytics.Platform.Chromebook
        analytics.action("search")
        runCurrent()
        val (open, tablet, desktop) = transport.requests.drop(1)
        for ((request, form) in listOf(
            open to DeliveryAnalytics.Platform.Android,
            tablet to DeliveryAnalytics.Platform.AndroidTablet,
            desktop to DeliveryAnalytics.Platform.Chromebook,
        )) {
            assertEquals(form.rawValue, request.payload["data"]!!.jsonObject.string("platform"))
            assertEquals(form.device, request.payload.string("device"))
            assertEquals(DeliveryAnalytics.userAgent(form, "1.2"), request.header("User-Agent"))
        }
    }

    @Test
    fun eventsWaitForTheConfigurationAndTheQueueHoldsThirty() = runTest {
        val transport = collector()
        val analytics = analytics(transport)
        repeat(35) { analytics.action("search") }
        runCurrent()
        assertEquals(0, transport.requests.size)
        analytics.start()
        runCurrent()
        // The app open finds the queue full.
        val sent = transport.requests.drop(1).map { it.payload.string("name") }
        assertEquals(List(30) { "search" }, sent)
    }

    @Test
    fun theCollectorsCacheGoesWithTheNextEvent() = runTest {
        val answers = ArrayDeque(
            listOf(
                jsonResponse(200, """{"cache":"first"}"""),
                jsonResponse(500, """{"cache":"refused"}"""),
                jsonResponse(200, "[]"),
                jsonResponse(200, """{"cache":7}"""),
                jsonResponse(200, """{"cache":"second"}"""),
            ),
        )
        val transport = collector { answers.removeFirstOrNull() ?: HttpResponse(200) }
        val analytics = analytics(transport)
        analytics.start()
        runCurrent()
        repeat(5) {
            analytics.action("search")
            runCurrent()
        }
        // The open, then each action: the cache an answer of 200 gave, until one gives none.
        assertEquals(listOf(null, "first", "first", "first", null, "second"), transport.requests.drop(1).map { it.header("x-umami-cache") })
    }

    @Test
    fun aConfigurationRefusedSwitchesCollectionOff() = runTest {
        val refusals = listOf(
            HttpResponse(404, body = configurationAnswer.toByteArray()),
            jsonResponse(200, "null"),
            jsonResponse(200, configurationAnswer.replace("https://analytics", "http://analytics")),
            jsonResponse(200, configurationAnswer.replace("\"hostname\":\"delivery.example\"", "\"hostname\":\"another.example\"")),
            jsonResponse(200, configurationAnswer.replace("\"androidWebsite\":\"$website\"", "\"androidWebsite\":\"web\"")),
        )
        for (refusal in refusals) {
            var refused = true
            val transport = collector()
            val analytics = analytics(RecordingTransport { request -> if (refused) refusal else transport.data(request) })
            analytics.view("deliveries")
            analytics.start()
            analytics.action("search")
            runCurrent()
            // Until a configuration is taken, nothing waits to be sent.
            refused = false
            analytics.start()
            runCurrent()
            assertEquals(listOf("app-open"), transport.requests.filter { it.method == "POST" }.map { it.payload.string("name") })
        }
    }

    /** A configuration that could not be fetched or read is no refusal: the queue goes, and what follows waits for the next try. */
    @Test
    fun aConfigurationThatCouldNotBeReadIsTriedAgain() = runTest {
        val failures: List<() -> HttpResponse> = listOf(
            { throw IOException("offline") },
            { jsonResponse(200, "{\"endpoint\":") },
            { jsonResponse(200, """{"endpoint":"https://analytics.example/api/send","hostname":"delivery.example"}""") },
            { jsonResponse(200, configurationAnswer.replace("https://analytics.example/api/send", "")) },
        )
        for (failure in failures) {
            var failing = true
            val transport = collector()
            val analytics = analytics(RecordingTransport { request -> if (failing) failure() else transport.data(request) })
            analytics.view("deliveries")
            analytics.start()
            analytics.action("search")
            runCurrent()
            failing = false
            analytics.start()
            runCurrent()
            assertEquals(listOf("search", "app-open"), transport.requests.filter { it.method == "POST" }.map { it.payload.string("name") })
        }
    }

    @Test
    fun theDemoAndAnEmulatorNeverCollect() = runTest {
        val transport = collector()
        analytics(transport, app = app.copy(mode = AppConfiguration.Mode.Demo)).apply {
            view("deliveries")
            start()
            action("search")
        }
        analytics(transport, collects = false).apply {
            view("deliveries")
            start()
            action("search")
        }
        runCurrent()
        assertEquals(0, transport.requests.size)
    }

    @Test
    fun switchingOffDropsTheQueueAndSwitchingOnCountsTheScreenAgain() = runTest {
        val defaults = MemoryDefaults()
        val transport = collector { jsonResponse(200, """{"cache":"kept"}""") }
        val analytics = analytics(transport, defaults)
        analytics.view("passport", DeliveryAnalytics.Mode.Account)
        analytics.start()
        runCurrent()
        analytics.setEnabled(false)
        analytics.action("search")
        analytics.view("friends")
        runCurrent()
        assertEquals(false, defaults.values[DeliveryAnalytics.PREFERENCE_KEY])
        assertEquals(3, transport.requests.size)
        // Started already: the screen is counted again, and the cache went with the switch.
        analytics.setEnabled(true)
        runCurrent()
        val again = transport.requests.drop(3)
        assertEquals(listOf("/friends"), again.map { it.payload.string("url") })
        assertNull(again.single().header("x-umami-cache"))
        assertEquals(null, again.single().payload["name"])
    }

    @Test
    fun aScreenIsCountedOnceUntilItOrTheModeChanges() = runTest {
        val transport = collector()
        val analytics = analytics(transport)
        analytics.start()
        runCurrent()
        analytics.view("deliveries", DeliveryAnalytics.Mode.Anonymous)
        analytics.view("deliveries")
        analytics.view("deliveries", DeliveryAnalytics.Mode.Account)
        analytics.view("not-a-screen", DeliveryAnalytics.Mode.Demo)
        analytics.view("deliveries")
        analytics.action("not-an-action")
        analytics.action("parcel-add", DeliveryAnalytics.Outcome.Accepted)
        runCurrent()
        val sent = transport.requests.drop(2).map { it.payload }
        assertEquals(listOf("anonymous", "account", "account"), sent.map { it["data"]!!.jsonObject.string("mode") })
        assertEquals(listOf(null, null, "parcel-add"), sent.map { it["name"]?.jsonPrimitive?.content })
        assertEquals("accepted", sent.last()["data"]!!.jsonObject.string("outcome"))
        assertEquals("deliveries", analytics.screen)
    }

    @Test
    fun aParcelLinkIsOneScreenOverTheOneItOpenedOn() = runTest {
        val transport = collector()
        val analytics = analytics(transport)
        val observer = AnalyticsLifecycleObserver(analytics, backgroundScope)
        val anonymous = DeliveryAnalytics.Mode.Anonymous
        observer.update("front-door", anonymous, linkOpen = false)
        observer.appeared()
        runCurrent()
        observer.update("front-door", anonymous, linkOpen = true)
        // Signing in under the link counts the link again, and closing it the screen beneath now.
        observer.update("deliveries", DeliveryAnalytics.Mode.Account, linkOpen = true)
        observer.update("deliveries", DeliveryAnalytics.Mode.Account, linkOpen = false)
        observer.foreground()
        observer.languageChanged()
        observer.urlOpened()
        observer.friendNotificationOpened()
        runCurrent()
        val sent = transport.requests.drop(1).map { request -> request.payload.let { it["name"]?.jsonPrimitive?.content ?: it.string("url") } }
        assertEquals(
            listOf(
                "/front-door", "app-open", "/parcel-link", "/parcel-link", "/deliveries",
                "app-foreground", "language-change", "deep-link-open", "notification-open",
            ),
            sent,
        )
    }

    @Test
    fun theObserversScreenFollowsTheSession() {
        val screen = AnalyticsLifecycleObserver::screen
        assertEquals("invitation", screen(true, AuthenticationState.Loading, false))
        assertEquals("welcome", screen(false, AuthenticationState.Loading, true))
        assertEquals("front-door", screen(false, AuthenticationState.Welcome, false))
        assertEquals("deliveries", screen(false, AuthenticationState.Welcome, true))
        assertEquals("sign-in", screen(false, AuthenticationState.SignedOut, true))
        assertEquals("sign-in", screen(false, AuthenticationState.Unconfigured, false))
        assertEquals("deliveries", screen(false, AuthenticationState.Demo, false))
        assertEquals(DeliveryAnalytics.Mode.Demo, AnalyticsLifecycleObserver.mode(isDemo = true, signedIn = false))
        assertEquals(DeliveryAnalytics.Mode.Account, AnalyticsLifecycleObserver.mode(isDemo = false, signedIn = true))
        assertEquals(DeliveryAnalytics.Mode.Anonymous, AnalyticsLifecycleObserver.mode(isDemo = false, signedIn = false))
    }

    @Test
    fun theUserAgentIsChromesWithTheAppsName() {
        assertEquals(
            "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Mobile Safari/537.36 DeliveryTracker/1.2",
            DeliveryAnalytics.userAgent(DeliveryAnalytics.Platform.Android, "1.2"),
        )
        assertFalse(DeliveryAnalytics.userAgent(DeliveryAnalytics.Platform.AndroidTablet, "1.2").contains("Mobile"))
        assertTrue(DeliveryAnalytics.userAgent(DeliveryAnalytics.Platform.Chromebook, "1.2").contains("CrOS"))
    }

    private fun TestScope.analytics(
        transport: HttpTransport,
        defaults: UserDefaults = MemoryDefaults(),
        app: AppConfiguration = this@AnalyticsTests.app,
        collects: Boolean = true,
        platform: () -> DeliveryAnalytics.Platform = { DeliveryAnalytics.Platform.Android },
        languages: List<String> = listOf("fr-CH", "en"),
    ): DeliveryAnalytics = DeliveryAnalytics(app, transport, defaults, backgroundScope, platform, "1.2", { languages }, collects)

    /** The configuration, then `events` for each event sent. */
    private fun collector(events: (HttpRequest) -> HttpResponse = { HttpResponse(200) }) = RecordingTransport { request ->
        if (request.method == "GET") jsonResponse(200, configurationAnswer) else events(request)
    }

    private val HttpRequest.json: JsonObject
        get() = Json.parseToJsonElement(body!!.decodeToString()).jsonObject

    private val HttpRequest.payload: JsonObject
        get() = json["payload"]!!.jsonObject

    private fun JsonObject.string(key: String): String? = this[key]?.jsonPrimitive?.content
}
