package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ShopPlatform
import com.plhery.peekdeliverytracker.core.api.ShopWait
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The list's waits for shops that haven't shipped yet (the web's `useShopWaits` and `ShopWaitCards`): the client's two
 * requests to `/api/shop-waits`, the answer read leniently, the card's words, the demo's wait and the store.
 */
class ShopWaitsTests {
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

    private fun serving(response: HttpResponse) = RecordingTransport { response }

    /** The tests' fixed moment, a Thursday (2026-10-29 10:11 UTC). */
    private val now = Instant.parse("2026-10-29T10:11:00Z")

    private val coatId = "3a1e0c3a-7b2d-4c8e-9a61-0d4b8e2f7c15"

    private fun wait(
        id: String = coatId,
        shop: String? = "Example Shop",
        label: String = "Wool coat",
        expiresAt: String = "2026-11-11T10:11:00.000Z",
        expiredAt: String? = null,
    ) = ShopWait(
        id = UUID.fromString(id), platform = ShopPlatform.Parcellab, platformName = "parcelLab", shopName = shop, label = label,
        createdAt = "2026-10-28T10:11:00.000Z", expiresAt = expiresAt, expiredAt = expiredAt,
    )

    private fun json(id: String = coatId, platform: String = "parcellab", shop: String = "\"Example Shop\"", expiredAt: String = "null") =
        """{"id":"$id","platform":"$platform","platform_name":"parcelLab","shop_name":$shop,"label":"Wool coat",""" +
            """"created_at":"2026-10-28T10:11:00.000Z","expires_at":"2026-11-11T10:11:00.000Z","expired_at":$expiredAt}"""

    // The requests

    @Test
    fun theWaitsAreReadWithTheAccountsToken() = runTest {
        val transport = serving(jsonResponse(200, """{"shop_waits":[${json()},${json(id = "4b1e0c3a-7b2d-4c8e-9a61-0d4b8e2f7c15", shop = "null", expiredAt = "\"2026-10-28T09:00:00.000Z\"")}]}"""))
        val waits = client(transport).shopWaits()

        assertEquals(listOf(wait(), wait(id = "4b1e0c3a-7b2d-4c8e-9a61-0d4b8e2f7c15", shop = null).copy(expiredAt = "2026-10-28T09:00:00.000Z")), waits)
        val request = transport.requests.single()
        assertEquals("GET https://peek.example/api/shop-waits", "${request.method} ${request.url}")
        assertEquals("Bearer synthetic-access", request.header("Authorization"))
        assertNull(request.body)
    }

    @Test
    fun aWaitThisVersionCantReadDropsOutOfTheList() = runTest {
        val body = """{"shop_waits":[${json(platform = "narvar", id = "5b1e0c3a-7b2d-4c8e-9a61-0d4b8e2f7c15")},{"id":"not-an-id"},${json()}]}"""
        assertEquals(listOf(wait()), client(serving(jsonResponse(200, body))).shopWaits())
        assertEquals(emptyList<ShopWait>(), client(serving(jsonResponse(200, """{"shop_waits":[]}"""))).shopWaits())

        val missing = runCatching { client(serving(jsonResponse(200, "{}"))).shopWaits() }.exceptionOrNull()
        assertTrue(missing is DeliveryApiError.InvalidResponse)
        val failed = runCatching { client(serving(jsonResponse(500))).shopWaits() }.exceptionOrNull()
        assertTrue(failed is DeliveryApiError.ServiceFailed)
    }

    @Test
    fun removingAWaitDeletesItAndOneAlreadyGoneCountsAsRemoved() = runTest {
        val transport = serving(jsonResponse(200, """{"ok":true}"""))
        client(transport).removeShopWait(UUID.fromString(coatId.uppercase()))

        val request = transport.requests.single()
        assertEquals("DELETE https://peek.example/api/shop-waits/$coatId", "${request.method} ${request.url}")
        assertEquals("Bearer synthetic-access", request.header("Authorization"))

        client(serving(jsonResponse(404, """{"error":"Not found"}"""))).removeShopWait(UUID.fromString(coatId))
        val failed = runCatching { client(serving(jsonResponse(500))).removeShopWait(UUID.fromString(coatId)) }.exceptionOrNull()
        assertTrue(failed is DeliveryApiError.ServiceFailed)
    }

    // The card's words

    @Test
    fun anOpenWaitSaysWhomItWaitsForAndUntilWhen() {
        val words = wait().words(Localizer(AppLanguage.En), now)
        assertEquals(
            ShopWaitWords(
                via = "Example Shop via parcelLab",
                title = "Wool coat",
                state = "Waiting for Example Shop to ship",
                detail = "Peek checks every few hours until Wed 11 Nov",
                action = "Stop waiting",
            ),
            words,
        )
        assertFalse(wait().isExpired)
    }

    @Test
    fun anExpiredWaitSaysSoAndOffersToRemoveIt() {
        val expired = wait(expiredAt = "2026-10-28T09:00:00.000Z")
        assertTrue(expired.isExpired)
        assertEquals(
            ShopWaitWords(
                via = "Example Shop via parcelLab",
                title = "Wool coat",
                state = "Example Shop named no parcel in 14 days",
                detail = "Peek stopped checking. Add the tracking number when the shop sends it.",
                action = "Remove",
            ),
            expired.words(Localizer(AppLanguage.En), now),
        )
    }

    @Test
    fun aWaitWithoutTheShopsNameNamesThePlatform() {
        val localizer = Localizer(AppLanguage.En)
        val open = wait(shop = null, label = "").words(localizer, now)
        assertEquals("Via parcelLab", open.via)
        assertEquals("parcelLab", open.title)
        assertEquals("Waiting for the shop to ship", open.state)
        // An empty name counts as none; the label still names the card, else the shop.
        assertEquals("Via parcelLab", wait(shop = "").words(localizer, now).via)
        assertEquals("Example Shop", wait(label = "").words(localizer, now).title)
        val expired = wait(shop = null, expiredAt = "2026-10-28T09:00:00.000Z").words(localizer, now)
        assertEquals("The shop named no parcel in 14 days", expired.state)
        assertEquals("Wool coat", expired.title)
    }

    @Test
    fun theWordsReadInEveryLanguage() {
        for (language in AppLanguage.entries) {
            val localizer = Localizer(language)
            for (shown in listOf(wait(), wait(shop = null), wait(expiredAt = "2026-10-28T09:00:00.000Z"), wait(shop = null, expiredAt = "2026-10-28T09:00:00.000Z"))) {
                val words = shown.words(localizer, now)
                for (text in listOf(words.via, words.state, words.detail, words.action)) {
                    assertFalse("${language.rawValue}: $text", text.contains("{{") || text.startsWith("shopWait."))
                }
            }
        }
    }

    // The demo

    @Test
    fun theDemosWaitIsACoatHAndMHasntShipped() {
        val demo = demoShopWait(now)
        assertEquals(DEMO_SHOP_WAIT_ID, demo.id)
        assertEquals(ShopPlatform.Parcellab, demo.platform)
        assertEquals("parcelLab", demo.platformName)
        assertEquals("H&M", demo.shopName)
        assertEquals("Wool coat", demo.label)
        assertEquals(Instant.parse("2026-10-28T10:11:00Z"), DateParser.date(demo.createdAt))
        assertEquals(Instant.parse("2026-11-11T10:11:00Z"), DateParser.date(demo.expiresAt))
        assertNull(demo.expiredAt)
        assertEquals("Peek checks every few hours until Wed 11 Nov", demo.words(Localizer(AppLanguage.En), now).detail)
        // The same wait at every launch, kept a day before now and asked about for 13 more days.
        assertEquals(demo.id, demoShopWait(now.plusSeconds(3600)).id)
    }

    // The store

    private class FakeClient : ShopWaitsClient {
        var waits: List<ShopWait> = emptyList()
        var failure: Exception? = null
        var removeFailure: Exception? = null
        var removing: CompletableDeferred<Unit>? = null
        var listing: CompletableDeferred<Unit>? = null
        val calls = mutableListOf<String>()

        override suspend fun list(): List<ShopWait> {
            calls += "list"
            listing?.await()
            failure?.let { throw it }
            return waits
        }

        override suspend fun remove(id: UUID) {
            calls += "remove $id"
            removing?.await()
            removeFailure?.let { throw it }
            waits = waits.filterNot { it.id == id }
        }

        /** What a keep answers: the wait it adds to [waits], kept for its links. */
        var keeping: ShopWait? = null
        var keepFailure: Exception? = null
        var keepHolding: CompletableDeferred<Unit>? = null

        override suspend fun keep(text: String, label: String): List<ShopWait> {
            calls += "keep $label"
            keepHolding?.await()
            keepFailure?.let { throw it }
            val kept = keeping ?: return emptyList()
            if (waits.none { it.id == kept.id }) waits = waits + kept
            return listOf(kept)
        }
    }

    private val reader = ShopWaitsOwner.Account(UUID(0, 11))
    private val coat = wait()
    private val boots = wait(id = "4b1e0c3a-7b2d-4c8e-9a61-0d4b8e2f7c15", label = "Boots")

    @Test
    fun theOwnerFollowsTheSession() {
        assertEquals(ShopWaitsOwner.Demo, ShopWaitsOwner.of(AuthenticationState.Demo))
        assertEquals(reader, ShopWaitsOwner.of(AuthenticationState.SignedIn(AuthUser(UUID(0, 11)))))
        assertNull(ShopWaitsOwner.of(AuthenticationState.Welcome))
        assertNull(ShopWaitsOwner.of(AuthenticationState.SignedOut))
    }

    @Test
    fun theDemoShowsItsWaitAndAGuestNone() = runTest {
        val client = FakeClient().apply { waits = listOf(coat) }
        val store = ShopWaitsStore(client, demo = { demoShopWait(now) })

        store.show(ShopWaitsOwner.Demo)
        assertEquals(listOf(demoShopWait(now)), store.waits(ShopWaitsOwner.Demo))
        assertFalse(store.read(ShopWaitsOwner.Demo))

        // Removing it in the demo asks no server, and it stays gone while the demo shows.
        assertFalse(store.remove(ShopWaitsOwner.Demo, DEMO_SHOP_WAIT_ID))
        assertEquals(emptyList<ShopWait>(), store.waits(ShopWaitsOwner.Demo))
        store.show(ShopWaitsOwner.Demo)
        assertEquals(emptyList<ShopWait>(), store.waits(ShopWaitsOwner.Demo))

        // A guest has none, and nothing is asked for them.
        store.show(null)
        assertEquals(emptyList<ShopWait>(), store.waits(null))
        assertFalse(store.read(null))
        assertEquals(emptyList<String>(), client.calls)

        // Entering the demo again shows its wait again.
        store.show(ShopWaitsOwner.Demo)
        assertEquals(listOf(demoShopWait(now)), store.waits(ShopWaitsOwner.Demo))
    }

    @Test
    fun anAccountsWaitsAreReadAndAnotherAccountStartsWithNone() = runTest {
        val client = FakeClient().apply { waits = listOf(coat, boots) }
        val store = ShopWaitsStore(client)

        // Nothing is read before the list shows the account's waits.
        assertFalse(store.read(reader))
        assertEquals(emptyList<String>(), client.calls)

        store.show(reader)
        assertEquals(emptyList<ShopWait>(), store.waits(reader))
        assertFalse(store.read(reader))
        assertEquals(listOf(coat, boots), store.waits(reader))
        // Another owner never sees them.
        assertEquals(emptyList<ShopWait>(), store.waits(ShopWaitsOwner.Demo))

        val other = ShopWaitsOwner.Account(UUID(0, 12))
        store.show(other)
        assertEquals(emptyList<ShopWait>(), store.waits(other))
        assertEquals(emptyList<ShopWait>(), store.waits(reader))
    }

    @Test
    fun aFailedReadKeepsTheWaitsShown() = runTest {
        val client = FakeClient().apply { waits = listOf(coat) }
        val store = ShopWaitsStore(client)
        store.show(reader)
        store.read(reader)

        client.failure = DeliveryApiError.ServiceFailed(502)
        assertFalse(store.read(reader))
        assertEquals(listOf(coat), store.waits(reader))
    }

    @Test
    fun aWaitThatGoesWithoutTheReaderSaysToReadTheParcelsAgain() = runTest {
        val client = FakeClient().apply { waits = listOf(coat, boots) }
        val store = ShopWaitsStore(client)
        store.show(reader)
        assertFalse(store.read(reader))

        // The shop shipped the coat: the server added its parcels and forgot the wait.
        client.waits = listOf(boots)
        assertTrue(store.read(reader))
        assertEquals(listOf(boots), store.waits(reader))
        assertFalse(store.read(reader))

        // A new wait is no news.
        client.waits = listOf(boots, coat)
        assertFalse(store.read(reader))
    }

    @Test
    fun removingAWaitTakesItAwayAtOnceAndIsNoNews() = runTest {
        val client = FakeClient().apply { waits = listOf(coat, boots) }
        val store = ShopWaitsStore(client)
        store.show(reader)
        store.read(reader)

        client.removing = CompletableDeferred()
        val removing = async { store.remove(reader, coat.id) }
        runCurrent()
        // Gone before the server answers.
        assertEquals(listOf(boots), store.waits(reader))
        // A read that answers meanwhile, the server not yet having forgotten it, doesn't bring it back.
        assertFalse(store.read(reader))
        assertEquals(listOf(boots), store.waits(reader))

        client.removing?.complete(Unit)
        assertFalse(removing.await())
        assertEquals(listOf(boots), store.waits(reader))
        // The wait the reader removed isn't counted as gone by itself.
        assertFalse(store.read(reader))
        assertEquals(listOf("list", "remove ${coat.id}", "list", "list"), client.calls)
    }

    @Test
    fun aFailedRemovalBringsTheWaitBackWhereItStoodAndReadsAgain() = runTest {
        val client = FakeClient().apply { waits = listOf(coat, boots) }
        val store = ShopWaitsStore(client)
        store.show(reader)
        store.read(reader)

        client.removeFailure = DeliveryApiError.ServiceFailed(502)
        client.removing = CompletableDeferred()
        val removing = async { store.remove(reader, coat.id) }
        runCurrent()
        assertEquals(listOf(boots), store.waits(reader))

        client.removing?.complete(Unit)
        assertFalse(removing.await())
        assertEquals(listOf(coat, boots), store.waits(reader))
        assertEquals(listOf("list", "remove ${coat.id}", "list"), client.calls)
    }

    @Test
    fun aFailedRemovalsReadSaysWhenAnotherWaitWent() = runTest {
        val client = FakeClient().apply { waits = listOf(coat, boots) }
        val store = ShopWaitsStore(client)
        store.show(reader)
        store.read(reader)

        // The removal fails, and meanwhile the shop shipped the boots.
        client.removeFailure = DeliveryApiError.ServiceFailed(502)
        client.waits = listOf(coat)
        assertTrue(store.remove(reader, coat.id))
        assertEquals(listOf(coat), store.waits(reader))
    }

    @Test
    fun workBegunForOneOwnerNeverFinishesForAnother() = runTest {
        val client = FakeClient().apply { waits = listOf(coat) }
        val store = ShopWaitsStore(client)
        store.show(reader)

        client.listing = CompletableDeferred()
        val reading = async { store.read(reader) }
        runCurrent()
        val other = ShopWaitsOwner.Account(UUID(0, 12))
        store.show(other)
        client.listing?.complete(Unit)
        assertFalse(reading.await())
        assertEquals(emptyList<ShopWait>(), store.waits(other))

        // An answer overtaken by a newer read is set aside.
        val held = CompletableDeferred<Unit>()
        client.listing = held
        val first = async { store.read(other) }
        runCurrent()
        client.listing = null
        client.waits = listOf(boots)
        assertFalse(store.read(other))
        client.waits = listOf(coat)
        held.complete(Unit)
        assertFalse(first.await())
        assertEquals(listOf(boots), store.waits(other))
    }

    // Add's "Wait for H&M"

    private val link = "https://hm.delivery-status.com/de/de/?s=synthetic-key"

    @Test
    fun aWaitKeptFromAddShowsAtOnceThenTheWaitsAreRead() = runTest {
        val client = FakeClient().apply { waits = listOf(boots); keeping = coat }
        val store = ShopWaitsStore(client)
        // The list showed the demo: waiting signed in shows the account's waits.
        store.show(ShopWaitsOwner.Demo)

        client.listing = CompletableDeferred()
        val keeping = async { store.keep(reader, link, "Wool coat") }
        runCurrent()
        assertEquals(listOf(coat), store.waits(reader))
        client.listing!!.complete(Unit)
        assertFalse(keeping.await())
        assertEquals(listOf("keep Wool coat", "list"), client.calls)
        assertEquals(listOf(boots, coat), store.waits(reader))

        // Kept again, the same wait shows once.
        assertFalse(store.keep(reader, link, "Wool coat"))
        assertEquals(listOf(boots, coat), store.waits(reader))
    }

    @Test
    fun aWaitThatFailsToBeKeptThrowsAndLeavesTheList() = runTest {
        val client = FakeClient().apply { waits = listOf(boots); keeping = coat; keepFailure = DeliveryApiError.ServiceFailed(502) }
        val store = ShopWaitsStore(client)
        store.show(reader)
        store.read(reader)

        val failed = runCatching { store.keep(reader, link, "Wool coat") }.exceptionOrNull()
        assertTrue(failed is DeliveryApiError.ServiceFailed)
        assertEquals(listOf(boots), store.waits(reader))
        assertEquals(listOf("list", "keep Wool coat"), client.calls)
    }

    @Test
    fun aWaitKeptForAnAccountSignedOutMeanwhileShowsForNoOne() = runTest {
        val client = FakeClient().apply { keeping = coat; keepHolding = CompletableDeferred() }
        val store = ShopWaitsStore(client)
        store.show(reader)

        val keeping = async { store.keep(reader, link, "Wool coat") }
        runCurrent()
        val other = ShopWaitsOwner.Account(UUID(0, 12))
        store.show(other)
        client.keepHolding!!.complete(Unit)
        assertFalse(keeping.await())
        assertEquals(emptyList<ShopWait>(), store.waits(other))
        assertEquals(emptyList<ShopWait>(), store.waits(reader))
        assertEquals(listOf("keep Wool coat"), client.calls)
    }
}
