package com.plhery.peekdeliverytracker.core

import java.time.Instant
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * AppRoutingTests.swift. Where the Swift leaves out the base address and the earlier hosts, it reads them from
 * `AppConfiguration.current`, which the shared build settings make `https://peektracker.com` and
 * `delivery.plhery.com`: those are passed here.
 */
class AppRoutingTests {
    companion object {
        /** `AppConfiguration.current.apiBaseURL` and `.linkHosts` in the iPhone app's tests. */
        private const val currentBase = "https://peektracker.com"
        private val currentLinkHosts = listOf("delivery.plhery.com")
    }

    private fun route(url: String, baseUrl: String = currentBase, linkHosts: List<String> = currentLinkHosts) =
        ParcelLinkRoute(url, baseUrl, linkHosts)

    private fun inviteCode(from: String, baseUrl: String = currentBase, linkHosts: List<String> = currentLinkHosts) =
        FriendInvitationLink.code(from, baseUrl, linkHosts)

    private fun isInvitation(url: String, baseUrl: String = currentBase, linkHosts: List<String> = currentLinkHosts) =
        FriendInvitationLink.isInvitation(url, baseUrl, linkHosts)

    /** `XCTUnwrap`. */
    private fun <T : Any> unwrap(value: T?): T {
        assertNotNull(value)
        return value!!
    }

    /**
     * `URL(string:)!`. The routes take text and give nothing for text that is no URL, where the Swift test would trap,
     * so a link a test expects nothing from must first be one.
     */
    private fun parsed(text: String): String {
        assertNotNull(text, Url.parse(text))
        return text
    }

    /** `Dictionary(uniqueKeysWithValues:)` over the URL's query items, which traps on a repeated name. */
    private fun queryValues(components: UrlComponents): Map<String, String?> {
        val items = components.queryItems ?: emptyList()
        return items.associate { it.name to it.value }.also { check(it.size == items.size) { "repeated query item" } }
    }

    @Test
    fun theFootOpensTheSitesLandingAtItsOwnAddressBesideThePrivacyNoticeAndTheCode() {
        val configuration = AppConfiguration(
            mode = AppConfiguration.Mode.Demo, apiBaseUrl = "https://peektracker.com", supabaseUrl = null,
            supabasePublishableKey = "", googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true,
            appGroupIdentifier = "routing.test",
        )
        // `/` shows someone signed in on the web their deliveries; the landing has an address of its own.
        assertEquals("https://peektracker.com/home", configuration.homePageUrl)
        assertEquals("https://peektracker.com/privacy.html", configuration.privacyUrl)
        assertEquals("https", Url.parse(AppConfiguration.sourceUrl)?.scheme)
        assertEquals("github.com", Url.parse(AppConfiguration.sourceUrl)?.host)
    }

    @Test
    fun invitationLinksKeepTheTokenOutOfHttpPathsAndQueries() {
        val code = "a".repeat(32)
        val base = "https://peektracker.com"
        val url = FriendInvitationLink.url(code = code, baseUrl = base)
        val parsed = Url.parse(url)!!
        assertEquals("/invite", parsed.path)
        assertEquals("preview=3ba3f5f43b92602683c19aee62a20342b084dd5971ddd33808d81a328879a547", parsed.query)
        assertFalse(parsed.query!!.contains(code))
        assertEquals(code, inviteCode(from = "$base/invite#$code", baseUrl = base))
        assertEquals(code, parsed.fragment)
        assertEquals(code, inviteCode(from = url, baseUrl = base))
        assertEquals(code, inviteCode(from = "peekdeliverytracker://invite#$code"))
        for (text in listOf(
            "https://evil.example/invite#$code", "https://peektracker.com/invite?name=Paul#$code",
            "https://user@peektracker.com/invite#$code", "https://peektracker.com/invite#short",
            "http://peektracker.com/invite#$code", "peekdeliverytracker://auth-callback#$code",
        )) {
            assertNull(text, inviteCode(from = parsed(text), baseUrl = base))
        }
    }

    @Test
    fun shortInvitationLinksAndLegacyCompatibility() {
        val code = "a".repeat(32)
        val preview = "Ab7kP2mQ9xR4tY6n"
        val base = "https://peektracker.com"
        val url = FriendInvitationLink.url(code = code, previewId = preview, baseUrl = base)
        assertEquals("$base/i/$preview", url)
        assertNull(Url.parse(url)!!.query)
        assertNull(Url.parse(url)!!.fragment)
        assertEquals(preview, inviteCode(from = url, baseUrl = base))
        assertEquals(preview, inviteCode(from = "$url#$code", baseUrl = base))
        assertEquals(preview, inviteCode(from = "$url?fbclid=tracking#ignored", baseUrl = base))
        assertEquals(preview, inviteCode(from = "peekdeliverytracker://invite#$preview"))
        assertEquals(code, inviteCode(from = "peekdeliverytracker://invite#$code"))
        for (invalid in listOf("$base/i/short#$code", "$base/i/$preview/extra#$code", "https://evil.example/i/$preview#$code")) {
            assertNull(invalid, inviteCode(from = parsed(invalid), baseUrl = base))
        }
    }

    /** The store is FriendsStore.swift's; what it asks of the links it is handed is checked here. */
    @Test
    fun pendingInvitationSurvivesAuthenticationButNotDismissalOrInvalidReplacement() {
        val shortKey = "Ab7kP2mQ9xR4tY6n" // gitleaks:allow -- synthetic invitation ID used only in tests
        val opened = "$currentBase/i/$shortKey"
        assertTrue(isInvitation(opened))
        assertEquals(shortKey, inviteCode(from = opened))
        // A restored invitation keeps its code only while it is one.
        assertTrue(FriendInvitationLink.validCode(shortKey))
        // The sign-in callback is no invitation, so it leaves the pending one alone.
        assertFalse(isInvitation(parsed(OAuthFlow.callbackUrl)))
        // A new invitation replaces it, and one with no valid code leaves nothing to restore.
        val replacement = "peekdeliverytracker://invite#" + "b".repeat(32)
        assertTrue(isInvitation(replacement))
        assertEquals("b".repeat(32), inviteCode(from = replacement))
        assertTrue(isInvitation("peekdeliverytracker://invite#invalid"))
        assertNull(inviteCode(from = "peekdeliverytracker://invite#invalid"))
        assertFalse(FriendInvitationLink.validCode("invalid"))
    }

    /** The store is FriendsStore.swift's; what it asks of the link it is handed is checked here. */
    @Test
    fun receivedFriendshipCannotRestoreAConsumedInvitation() {
        val link = "peekdeliverytracker://invite#" + "a".repeat(32)
        assertTrue(isInvitation(link))
        assertEquals("a".repeat(32), inviteCode(from = link))
    }

    @Test
    fun friendNotificationsAndLinksOpenOnlyValidProfileIdentifiers() {
        val friendId = UUID.randomUUID()
        assertEquals(
            NativeRoute.Friend(friendId),
            NativeRoute(remoteNotification = mapOf("kind" to "friend_accepted", "friend_id" to friendId.uuidString)),
        )
        assertEquals(NativeRoute.Friend(friendId), NativeRoute("peekdeliverytracker://friend/${friendId.uuidString}"))
        assertNull(NativeRoute(remoteNotification = mapOf("kind" to "friend_accepted", "friend_id" to "invalid")))
        assertNull(NativeRoute(remoteNotification = mapOf("friend_id" to friendId.uuidString)))
    }

    @Test
    fun parcelLinksOpenFromTheAppsOwnHostAndScheme() {
        val base = "https://peektracker.com"
        val id = "k7Qm2xHd9RtW"
        for (text in listOf(
            "https://peektracker.com/p/$id",
            "https://PEEKTRACKER.com/p/$id",
            "https://peektracker.com/p/$id?utm_source=chat",
            "https://peektracker.com/p/$id/",
            "peekdeliverytracker://p/$id",
            "PeekDeliveryTracker://P/$id",
        )) {
            assertEquals(text, ParcelLinkRoute(id), route(text, baseUrl = base))
        }
        // Another deployment answers on its own origin only, port included.
        val local = "http://localhost:3000"
        assertEquals(id, route("http://localhost:3000/p/$id", baseUrl = local)?.id)
        assertNull(route(parsed("http://localhost:4000/p/$id"), baseUrl = local))
        assertNull(route(parsed("https://peektracker.com/p/$id"), baseUrl = local, linkHosts = emptyList()))

        for (text in listOf(
            "https://evil.example/p/$id",
            "https://peektracker.com.evil.example/p/$id",
            "https://user@peektracker.com/p/$id",
            "http://peektracker.com/p/$id",
            "https://peektracker.com:8443/p/$id",
            "https://peektracker.com/p/$id/extra",
            "https://peektracker.com/P/$id",
            "https://peektracker.com/x/p/$id",
            "https://peektracker.com/p/$id%0A",
            "https://peektracker.com/p/" + id.dropLast(1),
            "https://peektracker.com/p/${id}2",
            "https://peektracker.com/p/",
            "https://peektracker.com/i/$id",
            "peekdeliverytracker://parcel/$id",
            "peekdeliverytracker://p/$id/extra",
            "peekdeliverytracker://p",
            "peekdeliverytracker://user@p/$id",
            "otherapp://p/$id",
        )) {
            assertNull(text, route(parsed(text), baseUrl = base))
        }
        // Lookalike characters are not in the alphabet, so a mistyped link is never sent.
        for (lookalike in listOf("0", "1", "I", "O", "l", "-", "_", "é")) {
            assertFalse(lookalike, ParcelLinkRoute.validId(id.dropLast(1) + lookalike))
        }
        assertTrue(ParcelLinkRoute.validId("23456789ABCD"))
        assertTrue(ParcelLinkRoute.validId("HJKLMNPQRSTU"))
        assertTrue(ParcelLinkRoute.validId("VWXYZabcdefg"))
        assertTrue(ParcelLinkRoute.validId("hijkmnopqrst"))
        assertTrue(ParcelLinkRoute.validId("uvwxyz222222"))

        // A parcel link is not an invitation, and the other routes leave it alone.
        val link = parsed("peekdeliverytracker://p/$id")
        assertNull(NativeRoute(link))
        assertFalse(isInvitation(link, baseUrl = base))
        assertFalse(isInvitation(parsed("https://peektracker.com/p/$id"), baseUrl = base))
    }

    @Test
    fun linksFromAHostTheSiteLeftStillOpenAndNothingIsBuiltOnIt() {
        val base = "https://peektracker.com"
        val earlier = listOf("delivery.plhery.com")
        val id = "k7Qm2xHd9RtW"
        val preview = "Ab7kP2mQ9xR4tY6n"
        val code = "a".repeat(32)

        assertEquals(
            ParcelLinkRoute(id, name = "Moon lamp"),
            route("https://delivery.plhery.com/p/$id#n=Moon%20lamp", baseUrl = base, linkHosts = earlier),
        )
        assertEquals(id, route("https://DELIVERY.plhery.com/p/$id?utm_source=chat", baseUrl = base, linkHosts = earlier)?.id)
        assertEquals(preview, inviteCode(from = "https://delivery.plhery.com/i/$preview", baseUrl = base, linkHosts = earlier))
        assertEquals(code, inviteCode(from = "https://delivery.plhery.com/invite#$code", baseUrl = base, linkHosts = earlier))
        assertTrue(isInvitation("https://delivery.plhery.com/invite", baseUrl = base, linkHosts = earlier))
        // The site's own host opens as before.
        assertEquals(id, route("https://peektracker.com/p/$id", baseUrl = base, linkHosts = earlier)?.id)
        assertEquals(preview, inviteCode(from = "https://peektracker.com/i/$preview", baseUrl = base, linkHosts = earlier))

        // Only the hosts named in the configuration, over HTTPS on its own port.
        for (text in listOf(
            "http://delivery.plhery.com", "https://delivery.plhery.com:8443", "https://user@delivery.plhery.com",
            "https://delivery.plhery.com.evil.example", "https://old.delivery.plhery.com", "https://evil.example",
        )) {
            assertNull(text, route(parsed("$text/p/$id"), baseUrl = base, linkHosts = earlier))
            assertNull(text, inviteCode(from = parsed("$text/i/$preview"), baseUrl = base, linkHosts = earlier))
            assertFalse(text, SiteLink.belongs(parsed("$text/"), baseUrl = base, linkHosts = earlier))
        }
        assertNull(route(parsed("https://delivery.plhery.com/p/$id"), baseUrl = base, linkHosts = emptyList()))
        assertNull(inviteCode(from = parsed("https://delivery.plhery.com/i/$preview"), baseUrl = base, linkHosts = emptyList()))
        // The same paths and ids as on the site's own host, nothing more.
        assertNull(route(parsed("https://delivery.plhery.com/p/$id/extra"), baseUrl = base, linkHosts = earlier))
        assertNull(inviteCode(from = parsed("https://delivery.plhery.com/i/short"), baseUrl = base, linkHosts = earlier))
        assertFalse(isInvitation(parsed("https://delivery.plhery.com/"), baseUrl = base, linkHosts = earlier))

        // A link the app shares is always on the site's own host.
        assertEquals("https://peektracker.com/i/$preview", FriendInvitationLink.url(code = code, previewId = preview, baseUrl = base))
        assertEquals("peektracker.com", Url.parse(FriendInvitationLink.url(code = code, baseUrl = base))?.host)

        assertEquals(
            listOf("delivery.plhery.com", "old.example.com", "third.example.com"),
            AppConfiguration.linkHosts(" Delivery.plhery.com,old.example.com  third.example.com "),
        )
        assertEquals(emptyList<String>(), AppConfiguration.linkHosts(""))
        assertEquals(
            emptyList<String>(),
            AppConfiguration.linkHosts(
                "https://delivery.plhery.com delivery.plhery.com:443 localhost user@old.example.com *.example.com -a.example.com",
            ),
        )
    }

    /** The version the app was built as is the app's to read (`AppConfiguration.version`). */
    @Test
    fun aMailToPeekNamesTheVersionAndNothingElse() {
        assertEquals("mailto:hello@peektracker.com?subject=Peek%201.0.2%20feedback", AppConfiguration.feedbackUrl("1.0.2"))
    }

    @Test
    fun aTappedWebLinkDeliveredTwiceOpensOnce() {
        val link = "https://peektracker.com/p/k7Qm2xHd9RtW"
        val other = "https://peektracker.com/i/Ab7kP2mQ9xR4tY6n"
        val start = Instant.ofEpochSecond(1_800_000_000)
        val deliveries = LinkDeliveries()
        assertFalse(deliveries.isRepeat(link, now = start))
        assertTrue(deliveries.isRepeat(link, now = start.plusMillis(50)))
        // Another link, or the same one tapped again later, opens.
        assertFalse(deliveries.isRepeat(other, now = start.plusMillis(100)))
        assertFalse(deliveries.isRepeat(link, now = start.plusMillis(200)))
        assertFalse(deliveries.isRepeat(link, now = start.plusSeconds(2)))
    }

    @Test
    fun aParcelLinkCarriesItsNameOnlyAfterTheHash() {
        val base = "https://peektracker.com"
        val id = "k7Qm2xHd9RtW"
        fun name(fragment: String): String? = route(parsed("https://peektracker.com/p/$id$fragment"), baseUrl = base)?.name
        assertEquals("New sneakers", name("#n=New%20sneakers"))
        assertEquals("Café ☕", name("#n=Caf%C3%A9%20%E2%98%95"))
        assertEquals("Moon lamp", name("#x=1&n=Moon%20lamp"))
        assertEquals("a+b&c", name("#n=a+b%26c"))
        assertEquals("Fragment", name("?n=Query#n=Fragment"))
        assertEquals(ParcelLinkRoute(id, name = "Kind of Blue"), route("peekdeliverytracker://p/$id#n=Kind%20of%20Blue", baseUrl = base))
        for (fragment in listOf("", "#", "#n=", "#n=%20%20", "#name=Lamp", "#Lamp", "?n=Query")) {
            assertNull(fragment, name(fragment))
        }
        // The link opens whatever follows the hash.
        assertEquals(ParcelLinkRoute(id), route("https://peektracker.com/p/$id#anything", baseUrl = base))

        // The name is cleaned to what a parcel's name may hold: one line, 80 characters.
        assertEquals("Line one two", ParcelLinkRoute.name(inFragment = "n=%20Line%0Aone%09two%20"))
        assertEquals("a".repeat(80), ParcelLinkRoute.name(inFragment = "n=" + "a".repeat(100)))
        assertEquals(80, ParcelLinkRoute.name(inFragment = "n=" + "%F0%9F%93%A6".repeat(50))?.length)
        assertNull(ParcelLinkRoute.name(inFragment = "n=%ZZ"))
        assertNull(ParcelLinkRoute.name(inFragment = null))
    }

    /** Swift's `description` and `debugDescription` are both `toString()` here. */
    @Test
    fun aHandedOverLinkCarriesItsOwnerKeyBesideItsWords() {
        val base = "https://peektracker.com"
        val id = "k7Qm2xHd9RtW"
        val key = "Ab3_-" + "x".repeat(38)
        fun route(address: String): ParcelLinkRoute? = route(address, baseUrl = base, linkHosts = listOf("delivery.plhery.com"))

        // The site opens the app on the host it answered on before, or on the app's own scheme.
        val handed = unwrap(route("https://delivery.plhery.com/p/$id#k=$key"))
        assertEquals(id, handed.id)
        assertEquals(key, handed.key)
        assertEquals(key, route("peekdeliverytracker://p/$id#k=$key")?.key)

        // The other words stay readable around it, in any order.
        val worded = unwrap(route("https://delivery.plhery.com/p/$id#n=Moon%20lamp&k=$key&g=Enjoy&f=Sam"))
        assertEquals(ParcelLinkRoute(id, name = "Moon lamp", note = "Enjoy", from = "Sam", key = key), worded)
        assertEquals("Moon lamp", route("https://peektracker.com/p/$id#k=$key&n=Moon%20lamp")?.name)

        // Anything that is not a key is no key, and the link still opens.
        for (fragment in listOf(
            "", "#k=", "#k=${key.dropLast(1)}", "#k=${key}x", "#k=${key.dropLast(1)}%2B", "#k=${key.dropLast(1)}=",
            "#K=$key", "#key=$key", "#n=k%3D$key", "?k=$key",
        )) {
            val opened = route("https://delivery.plhery.com/p/$id$fragment")
            assertEquals(fragment, id, opened?.id)
            assertNull(fragment, opened?.key)
        }
        assertNull(ParcelLinkRoute(id, key = "short").key)
        assertTrue(ParcelLinkRoute.validKey(key))
        assertFalse(ParcelLinkRoute.validKey("é".repeat(43)))

        // The key never goes into an address the app builds, nor into what the route says of itself.
        val address = ParcelLinkRoute.address(id = id, words = ParcelLinkWords(name = "Moon lamp"), baseUrl = base)
        assertEquals("https://peektracker.com/p/$id#n=Moon%20lamp", address)
        assertFalse("$worded".contains(key))
        assertFalse(worded.toString().contains(key))
    }

    @Test
    fun parsesParcelDeepLink() {
        val parcelId = UUID.randomUUID()
        val url = "peekdeliverytracker://parcel/${parcelId.uuidString}"

        assertEquals(NativeRoute.Parcel(parcelId), NativeRoute(url))
    }

    @Test
    fun parsesAddDeepLinkAndDecodesTrackingInput() {
        val url = "peekdeliverytracker://add?tracking=1Z999%20AA"

        assertEquals(NativeRoute.Add(trackingInput = "1Z999 AA"), NativeRoute(url))
    }

    @Test
    fun rejectsMalformedOrUnrelatedDeepLinks() {
        assertNull(NativeRoute(parsed("https://parcel/not-ours")))
        assertNull(NativeRoute(parsed("peekdeliverytracker://parcel/not-a-uuid")))
        assertNull(NativeRoute(parsed(OAuthFlow.callbackUrl)))
    }

    @Test
    fun parsesParcelNotificationPayload() {
        val parcelId = UUID.randomUUID()

        assertEquals(NativeRoute.Parcel(parcelId), NativeRoute(remoteNotification = mapOf("parcel_id" to parcelId.uuidString)))
        assertNull(NativeRoute(remoteNotification = mapOf("parcel_id" to "not-a-uuid")))
        assertNull(NativeRoute(remoteNotification = mapOf("other" to parcelId.uuidString)))
    }

    @Test
    fun aNotificationAboutAFollowedLinkOpensThatLink() {
        val payload: Map<String, Any?> = mapOf(
            "aps" to mapOf("alert" to mapOf("title" to "Out for delivery"), "thread-id" to "link-k7Qm2xHd9RtW"),
            "link_id" to "k7Qm2xHd9RtW",
        )
        assertEquals(NativeRoute.Link("k7Qm2xHd9RtW"), NativeRoute(remoteNotification = payload))
        assertNull(NativeRoute(remoteNotification = mapOf("link_id" to "not-a-link")))
        assertNull(NativeRoute(remoteNotification = mapOf("link_id" to "k7Qm2xHd9RtW0")))
        assertNull(NativeRoute(remoteNotification = mapOf("link_id" to 12)))
    }

    @Test
    fun googleAuthorizationUrlReturnsToNativeAppWithPkce() {
        val url = unwrap(
            OAuthFlow.authorizationUrl(baseUrl = "https://example.supabase.co", provider = "google", codeChallenge = "challenge-value"),
        )
        val components = unwrap(UrlComponents.parse(url))
        val query = queryValues(components)

        assertEquals("/auth/v1/authorize", components.path)
        assertEquals("google", query.getValue("provider"))
        assertEquals(OAuthFlow.callbackUrl, query.getValue("redirect_to"))
        assertEquals("challenge-value", query.getValue("code_challenge"))
        assertEquals("s256", query.getValue("code_challenge_method"))
    }

    @Test
    fun oAuthCodeOnlyAcceptsTheExpectedNativeCallback() {
        assertEquals("valid-code", OAuthFlow.authorizationCode(from = "peekdeliverytracker://auth-callback?code=valid-code"))
        assertNull(OAuthFlow.authorizationCode(from = parsed("peekdeliverytracker://other?code=valid-code")))
        assertNull(OAuthFlow.authorizationCode(from = parsed("https://auth-callback?code=valid-code")))
        assertNull(OAuthFlow.authorizationCode(from = parsed("peekdeliverytracker://auth-callback?code=")))
    }

    @Test
    fun customOidcAuthorizationKeepsTheProviderAndPkceInSeparateQueryItems() {
        val url = unwrap(
            OAuthFlow.authorizationUrl(
                baseUrl = "https://auth.example.com",
                provider = "custom:authentik",
                codeChallenge = "challenge+with&reserved=characters",
            ),
        )
        val components = unwrap(UrlComponents.parse(url))
        val query = queryValues(components)
        assertEquals("/auth/v1/authorize", components.path)
        assertEquals(4, query.size)
        assertEquals("custom:authentik", query.getValue("provider"))
        assertEquals("peekdeliverytracker://auth-callback", query.getValue("redirect_to"))
        assertEquals("challenge+with&reserved=characters", query.getValue("code_challenge"))
        assertEquals("s256", query.getValue("code_challenge_method"))
    }

    @Test
    fun customOidcConfigurationIsOptionalAndCanBeTheOnlySignInMethod() {
        var configuration = AppConfiguration(
            mode = AppConfiguration.Mode.Api,
            apiBaseUrl = "https://peek.example.com",
            supabaseUrl = "https://auth.example.com", supabasePublishableKey = "public-key",
            googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = false,
            appGroupIdentifier = "oidc.test",
        )
        assertTrue(configuration.authenticationConfigured)
        assertFalse(configuration.oidcAuthEnabled)

        configuration = configuration.copy(oidcProvider = "custom:authentik")
        assertFalse(configuration.authenticationConfigured)
        configuration = configuration.copy(oidcName = "Authentik")
        assertTrue(configuration.authenticationConfigured)
        assertTrue(configuration.oidcAuthEnabled)

        configuration = configuration.copy(oidcProvider = "")
        assertFalse(configuration.authenticationConfigured)
        configuration = configuration.copy(oidcProvider = "custom:authentik")
        configuration = configuration.copy(oidcName = " \n ")
        assertFalse(configuration.authenticationConfigured)
    }

    @Test
    fun customOidcRejectsInvalidIdentifiersBeforeOpeningAuthorization() {
        for (provider in listOf(
            "custom:", "authentik", "custom:AuthEntik", "custom:a_b", "custom:a.b",
            "custom:a&redirect_to=https://evil.example", "custom:a\n", " custom:a",
            "custom:" + "a".repeat(44),
        )) {
            assertFalse(provider, OAuthFlow.validCustomProvider(provider))
            assertNull(provider, OAuthFlow.authorizationUrl(baseUrl = "https://auth.example.com", provider = provider, codeChallenge = "challenge"))
        }
        assertTrue(OAuthFlow.validCustomProvider("custom:" + "a".repeat(43)))
        assertTrue(OAuthFlow.validCustomProvider("custom:my-provider:v2"))
    }

    // Unicode as Swift has it. The expected answers are Swift's on macOS 27.

    /** Format characters as Swift's Unicode has them: Android 12's ICU does not know U+0890 or U+13439 to U+1343F. */
    @Test
    fun aLinksWordsDropFormatCharactersAsSwiftsUnicodeHasThem() {
        assertEquals("a b", ParcelLinkWords.clean("a\u0890b", limit = 60))
        assertEquals("x y", ParcelLinkWords.clean("x\u0891\u0891y", limit = 60))
        assertNull(ParcelLinkWords.clean("\uD80D\uDC39", limit = 60))
        assertNull(ParcelLinkWords.clean("\uD80D\uDC3F", limit = 60))
        assertEquals("a b", ParcelLinkWords.clean("a\uD80D\uDC3Fb", limit = 60))
        assertEquals("Hieroglyphs after U+1343F stay", "\uD80D\uDC40a", ParcelLinkWords.clean("\uD80D\uDC40a", limit = 60))
        assertEquals("a", ParcelLinkWords.clean("\uD80D\uDC3Aa", limit = 60))
        assertEquals("Moon lamp", ParcelLinkWords(fragment = "n=Moon%E0%A2%90lamp").name)
    }

    /** A Swift String cannot hold a lone surrogate: bridged, it is U+FFFD, the replacement character. */
    @Test
    fun aLoneSurrogateIsTheReplacementCharacter() {
        assertEquals("a\uFFFDb", ParcelLinkWords.clean("a\uD800b", limit = 60))
        assertEquals("a\uFFFDb", ParcelLinkWords.clean("a\uDC00b", limit = 60))
        assertEquals("\uFFFD", ParcelLinkWords.clean("\uDBFF", limit = 60))
        assertEquals("Each counts as one scalar", "\uFFFD\uFFFD", ParcelLinkWords.clean("\uDC00\uD800\uD800", limit = 2))
        assertEquals("a\uD83C\uDF81b", ParcelLinkWords.clean("a\uD83C\uDF81b", limit = 60))
    }

    /** Swift compares Characters canonically: U+212A, the Kelvin sign, is K, which the alphabet has. */
    @Test
    fun aLinkIdMayBeWrittenWithTheKelvinSign() {
        assertTrue(ParcelLinkRoute.validId("k7Qm2xHd9RtK"))
        assertTrue(ParcelLinkRoute.validId("k7Qm2xHd9Rt\u212A"))
        assertTrue(ParcelLinkRoute.validId("\u212A".repeat(12)))
        assertEquals("k7Qm2xHd9Rt\u212A", route("peekdeliverytracker://p/k7Qm2xHd9Rt%E2%84%AA", baseUrl = "https://peektracker.com")?.id)
        assertEquals("k7Qm2xHd9Rt\u212A", route("https://peektracker.com/p/k7Qm2xHd9Rt%E2%84%AA", baseUrl = "https://peektracker.com")?.id)
        for (id in listOf("k7Qm2xHd9RtK\u0301", "k7Qm2xHd9Rt\u212A\u0301", "k7Qm2xHd9Rt", "k7Qm2xHd9RtI", "k7Qm2xHd9Rt\uFF2B")) {
            assertFalse(id, ParcelLinkRoute.validId(id))
        }
    }
}
