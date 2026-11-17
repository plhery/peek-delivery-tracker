package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.AddByEmail
import com.plhery.peekdeliverytracker.core.api.AddByEmailEntry
import com.plhery.peekdeliverytracker.core.api.AddByEmailResponse
import com.plhery.peekdeliverytracker.core.api.AddByEmailResult
import java.io.IOException
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
 * Adding parcels by email: the client's four requests to `/api/account/inbox`, the answer read leniently, and the
 * store Settings reads its row and page from.
 */
class AddByEmailTests {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://peek.example", supabaseUrl = "https://peek.example", supabasePublishableKey = "public",
        googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "harness.test",
    )

    private fun client(transport: HttpTransport, session: TestSession = TestSession()) = DeliveryApiClient(
        configuration, session, transport,
        NativeVerification(
            NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) }),
            TurnstilePresenter { throw NativeVerificationFailed() }, emptyMap(), {}, language = { AppLanguage.En },
        ),
        deviceName = "Synthetic phone", region = { Locale.GERMANY },
    )

    private fun serving(response: HttpResponse) = RecordingTransport { response }

    private fun inbox(recent: String = "[]", extra: String = """"lastMailAt":null,"lastResult":null,"lastAdded":null,"confirmation":null""") =
        """{"inbox":{"address":"parcels-synthetic@inbox.peek.example","createdAt":"2026-10-01T08:00:00.000Z","usedToday":3,""" +
            """"recent":$recent,$extra},"dailyLimit":50}"""

    private fun entry(result: String, sender: String = "Synthetic Shop", added: Int = 0) =
        """{"at":"2026-10-28T09:00:00.000Z","sender":"$sender","result":"$result","added":$added}"""

    @Test
    fun theAddressIsReadWithTheAccountsToken() = runTest {
        val transport = serving(jsonResponse(200, inbox(recent = "[${entry("added", added = 2)}]")))
        val offer = client(transport).readAddByEmail()

        assertEquals("parcels-synthetic@inbox.peek.example", offer?.inbox?.address)
        assertEquals(50, offer?.dailyLimit)
        assertEquals(AddByEmailResult.Added, offer?.inbox?.recent?.single()?.result)
        val request = transport.requests.single()
        assertEquals("GET https://peek.example/api/account/inbox", "${request.method} ${request.url}")
        assertEquals("Bearer synthetic-access", request.header("Authorization"))
        assertNull(request.body)
    }

    @Test
    fun anAccountWithoutTheOfferReadsAsNone() = runTest {
        assertNull(client(serving(jsonResponse(404, """{"error":"Not found"}"""))).readAddByEmail())
        assertNull(client(serving(jsonResponse(404))).readAddByEmail())
        // An account with the offer and no address yet.
        assertNull(client(serving(jsonResponse(200, """{"inbox":null,"dailyLimit":50}"""))).readAddByEmail()?.inbox)
    }

    @Test
    fun otherFailuresThrowAsTheAccountsOtherRequestsDo() = runTest {
        val cases = listOf(
            jsonResponse(500) to DeliveryApiError.ServiceFailed::class,
            jsonResponse(502, """{"error":"Upstream failed"}""") to DeliveryApiError.Service::class,
            jsonResponse(200, "<html></html>") to DeliveryApiError.InvalidResponse::class,
        )
        for ((answer, expected) in cases) {
            val error = runCatching { client(serving(answer)).readAddByEmail() }.exceptionOrNull()
            assertEquals(expected, error?.let { it::class })
        }
        val offline = runCatching { client(RecordingTransport { throw IOException("offline") }).readAddByEmail() }.exceptionOrNull()
        assertTrue(offline is IOException)
        // A refused sign-in signs out, as anywhere else, rather than reading as no offer.
        val session = TestSession()
        val expired = runCatching { client(serving(jsonResponse(401)), session).readAddByEmail() }.exceptionOrNull()
        assertTrue(expired is DeliveryApiError.AuthenticationExpired)
        assertTrue("signOut" in session.log)
    }

    @Test
    fun turningOnRenewingAndTurningOffSendWhatTheWebSends() = runTest {
        val transport = serving(jsonResponse(201, inbox()))
        val api = client(transport)
        api.openAddByEmail()
        api.openAddByEmail(renew = true)
        val closing = serving(jsonResponse(204))
        client(closing).closeAddByEmail()

        val (first, second) = transport.requests
        assertEquals("POST https://peek.example/api/account/inbox", "${first.method} ${first.url}")
        assertEquals("{}", first.body?.decodeToString())
        assertEquals("application/json", first.header("Content-Type"))
        assertEquals("""{"renew":true}""", second.body?.decodeToString())
        val close = closing.requests.single()
        assertEquals("DELETE https://peek.example/api/account/inbox", "${close.method} ${close.url}")
        // Turning on where the server offers none is a failure, not an answer of none.
        val refused = runCatching { client(serving(jsonResponse(404, """{"error":"Not found"}"""))).openAddByEmail() }.exceptionOrNull()
        assertTrue(refused is DeliveryApiError.Service)
    }

    @Test
    fun switchingTheNotificationSendsWhatTheWebSends() = runTest {
        val transport = serving(jsonResponse(200, inbox(extra = """"confirmation":null,"notify":false""")))
        val answer = client(transport).setAddByEmailNotify(false)

        assertEquals(false, answer.inbox?.notify)
        assertFalse(answer.inbox!!.notifies)
        val request = transport.requests.single()
        assertEquals("PATCH https://peek.example/api/account/inbox", "${request.method} ${request.url}")
        assertEquals("""{"notify":false}""", request.body?.decodeToString())
        assertEquals("application/json", request.header("Content-Type"))
        assertEquals("Bearer synthetic-access", request.header("Authorization"))
        val on = serving(jsonResponse(200, inbox(extra = """"notify":true""")))
        assertTrue(client(on).setAddByEmailNotify(true).inbox!!.notifies)
        assertEquals("""{"notify":true}""", on.requests.single().body?.decodeToString())
        // An account without an address has nothing to switch.
        val refused = runCatching { client(serving(jsonResponse(404, """{"error":"Adding by email is off"}"""))).setAddByEmailNotify(true) }.exceptionOrNull()
        assertTrue(refused is DeliveryApiError.Service)
    }

    /** An address the server sends without `notify`, as one from before the switch, is notified. */
    @Test
    fun anAddressWithoutTheSwitchIsNotified() = runTest {
        val read = client(serving(jsonResponse(200, inbox()))).readAddByEmail()?.inbox
        assertNull(read?.notify)
        assertTrue(read!!.notifies)
        val off = client(serving(jsonResponse(200, inbox(extra = """"notify":false""")))).readAddByEmail()?.inbox
        assertFalse(off!!.notifies)
    }

    @Test
    fun theNewerResultsAreRead() = runTest {
        val recent = "[${entry("named")},${entry("delivered", sender = "")}]"
        val read = client(serving(jsonResponse(200, inbox(recent = recent)))).readAddByEmail()
        assertEquals(listOf(AddByEmailResult.Named, AddByEmailResult.Delivered), read?.inbox?.recent?.map { it.result })
    }

    @Test
    fun aValueThisVersionDoesntKnowLeavesOutItsEmailAlone() = runTest {
        val recent = "[${entry("forwarded")},${entry("known")},{\"at\":\"2026-10-28T08:00:00.000Z\"},${entry("none")}]"
        val extra = """"lastMailAt":"2026-10-28T09:00:00.000Z","lastResult":"forwarded","lastAdded":0,""" +
            """"confirmation":{"code":"12","link":42,"receivedAt":"2026-10-28T09:00:00.000Z"},"futureField":true"""
        val read = client(serving(jsonResponse(200, inbox(recent = recent, extra = extra)))).readAddByEmail()?.inbox

        assertEquals(listOf(AddByEmailResult.Known, AddByEmailResult.None), read?.recent?.map { it.result })
        assertNull(read?.lastResult)
        assertNull(read?.confirmation)
        assertEquals("2026-10-28T09:00:00.000Z", read?.lastMailAt)
    }

    @Test
    fun gmailsConfirmationIsRead() = runTest {
        val extra = """"lastMailAt":null,"lastResult":null,"lastAdded":null,""" +
            """"confirmation":{"code":"123456789","link":"https://mail.google.com/synthetic","receivedAt":"2026-10-28T09:00:00.000Z"}"""
        val read = client(serving(jsonResponse(200, inbox(extra = extra)))).readAddByEmail()?.inbox?.confirmation
        assertEquals("123456789", read?.code)
        assertEquals("https://mail.google.com/synthetic", read?.link)
    }

    // What Settings shows of it

    private fun mailbox(recent: List<AddByEmailEntry> = emptyList(), lastResult: AddByEmailResult? = null, usedToday: Int = 0) = AddByEmail(
        address = "parcels-synthetic@inbox.peek.example", createdAt = "2026-10-01T08:00:00.000Z", usedToday = usedToday,
        lastMailAt = lastResult?.let { "2026-10-28T09:00:00.000Z" }, lastResult = lastResult, lastAdded = lastResult?.let { 1 }, recent = recent,
    )

    @Test
    fun theLastFiveEmailsShowAndAnOlderMailboxShowsItsLastOne() {
        val many = (1..7).map { AddByEmailEntry(at = "2026-10-2${it}T09:00:00.000Z", sender = "Shop $it", result = AddByEmailResult.Known, added = 0) }
        assertEquals(many.take(5), mailbox(many).recentEmails())
        assertEquals(
            listOf(AddByEmailEntry(at = "2026-10-28T09:00:00.000Z", sender = "", result = AddByEmailResult.Added, added = 1)),
            mailbox(lastResult = AddByEmailResult.Added).recentEmails(),
        )
        assertEquals(emptyList<AddByEmailEntry>(), mailbox().recentEmails())
    }

    @Test
    fun eachResultHasItsWordsAndAnAdditionItsCount() {
        val localizer = Localizer(AppLanguage.En)
        fun text(result: AddByEmailResult, added: Int = 0) =
            AddByEmailEntry(at = "2026-10-28T09:00:00.000Z", sender = "", result = result, added = added).resultText(localizer)
        assertEquals("1 parcel added", text(AddByEmailResult.Added, 1))
        assertEquals("3 parcels added", text(AddByEmailResult.Added, 3))
        assertEquals("Already in your deliveries: shop name added", text(AddByEmailResult.Named))
        assertEquals("Already delivered: not added", text(AddByEmailResult.Delivered))
        for (result in AddByEmailResult.entries) assertFalse(text(result, 2).startsWith("inbox."))
        assertEquals("Dodano 2 przesyłki", AddByEmailEntry("2026-10-28T09:00:00.000Z", "", AddByEmailResult.Added, 2).resultText(Localizer(AppLanguage.Pl)))
    }

    @Test
    fun aFailedChangeSaysSoWhereTheAppHasNoBetterWords() {
        val localizer = Localizer(AppLanguage.En)
        val failed = "Couldn’t change your address. Try again."
        assertEquals(failed, localizer.errorMessage(DeliveryApiError.ServiceFailed(500), fallback = "inbox.failed"))
        assertEquals(failed, localizer.errorMessage(DeliveryApiError.InvalidResponse(), fallback = "inbox.failed"))
        assertEquals(failed, localizer.errorMessage(DeliveryApiError.Service("Something unexpected"), fallback = "inbox.failed"))
        assertEquals(failed, localizer.errorMessage(IllegalStateException(), fallback = "inbox.failed"))
        // What the app has words for keeps them.
        assertEquals(localizer.text("error.connection"), localizer.errorMessage(IOException("offline"), fallback = "inbox.failed"))
        assertEquals(localizer.text("native.error.rateLimited"), localizer.errorMessage(DeliveryApiError.RateLimited(5.0), fallback = "inbox.failed"))
        // And without one, as before.
        assertEquals(localizer.text("native.error.serviceFailed", mapOf("status" to 500)), localizer.errorMessage(DeliveryApiError.ServiceFailed(500)))
    }

    @Test
    fun aStepsWordsMarkTheServicesOwnNamesAndGmailsOptionsButton() {
        val localizer = Localizer(AppLanguage.En)
        val text = localizer.text("inbox.gmail.filter", mapOf("options" to StepText.OPTIONS_MARK))
        assertEquals(
            listOf(
                StepPiece.Plain("In Gmail’s search box at the top, click the "), StepPiece.Options, StepPiece.Plain(" button ("),
                StepPiece.Bold("Show search options"), StepPiece.Plain("). Paste the search into "), StepPiece.Bold("Has the words"),
                StepPiece.Plain(", then click "), StepPiece.Bold("Create filter"), StepPiece.Plain(" at the bottom."),
            ),
            StepText.pieces(text),
        )
        assertEquals(
            "In Gmail’s search box at the top, click the button (Show search options). Paste the search into Has the words, then click Create filter at the bottom.",
            StepText.spoken(text),
        )
        // Marks that don't close stay as written.
        assertEquals(listOf(StepPiece.Plain("a **b")), StepText.pieces("a **b"))
        assertEquals(listOf(StepPiece.Plain("****")), StepText.pieces("****"))
        assertEquals(listOf(StepPiece.Plain("one "), StepPiece.Bold("two")), StepText.pieces("one **two**"))
        assertEquals(emptyList<StepPiece>(), StepText.pieces(""))
        // Every language's steps close their marks.
        for (language in AppLanguage.entries) {
            val words = Localizer(language)
            for (key in listOf("inbox.gmail.settings", "inbox.gmail.add", "inbox.gmail.noButton", "inbox.gmail.keepOff", "inbox.gmail.filter", "inbox.gmail.forward", "inbox.outlook.rule", "inbox.outlook.condition", "inbox.outlook.action", "inbox.icloud.rule", "inbox.icloud.words")) {
                val spoken = StepText.spoken(words.text(key, mapOf("options" to StepText.OPTIONS_MARK)))
                assertFalse("$language $key", "**" in spoken || "[[" in spoken)
            }
        }
    }

    @Test
    fun theUsageShowsOnceHalfTheDaysAllowanceIsUsed() {
        assertFalse(AddByEmailResponse(inbox = mailbox(usedToday = 24), dailyLimit = 50).showsUsage)
        assertTrue(AddByEmailResponse(inbox = mailbox(usedToday = 25), dailyLimit = 50).showsUsage)
        assertFalse(AddByEmailResponse(inbox = null, dailyLimit = 0).showsUsage)
    }

    // The store

    private class FakeClient : AddByEmailClient {
        var answer: AddByEmailResponse? = null
        var failure: Exception? = null
        var holding: CompletableDeferred<Unit>? = null
        val calls = mutableListOf<String>()

        override suspend fun read(): AddByEmailResponse? {
            calls += "read"
            holding?.await()
            failure?.let { throw it }
            return answer
        }

        override suspend fun open(renew: Boolean): AddByEmailResponse {
            calls += if (renew) "renew" else "open"
            failure?.let { throw it }
            return AddByEmailResponse(inbox = AddByEmail("parcels-${calls.size}@inbox.peek.example", "2026-10-28T09:00:00.000Z", 0, recent = emptyList()), dailyLimit = 50)
        }

        override suspend fun close() {
            calls += "close"
            failure?.let { throw it }
        }

        override suspend fun setNotify(notify: Boolean): AddByEmailResponse {
            calls += "notify $notify"
            failure?.let { throw it }
            val inbox = AddByEmail("parcels-synthetic@inbox.peek.example", "2026-10-28T09:00:00.000Z", 0, recent = emptyList(), notify = notify)
            return AddByEmailResponse(inbox = inbox, dailyLimit = 50)
        }
    }

    private val reader = UUID(0, 7)
    private val other = UUID(0, 8)
    private val offered = AddByEmailResponse(inbox = null, dailyLimit = 50)

    @Test
    fun theOfferShowsForTheAccountItWasReadForAlone() = runTest {
        val client = FakeClient().apply { answer = offered }
        val store = AddByEmailStore(client)
        assertNull(store.offer(reader))
        store.read(reader)
        assertEquals(offered, store.offer(reader))
        assertNull(store.offer(other))
        assertNull(store.offer(null))
    }

    @Test
    fun noOfferOrAFirstFailedReadHidesItAndALaterFailureKeepsWhatWasRead() = runTest {
        val client = FakeClient()
        val store = AddByEmailStore(client)
        store.read(reader)
        assertNull(store.offer(reader))
        client.failure = IOException("offline")
        store.read(reader)
        assertNull(store.offer(reader))

        client.failure = null
        client.answer = offered
        store.read(reader)
        client.failure = IOException("offline")
        store.read(reader)
        assertEquals(offered, store.offer(reader))

        // The server stops offering it: the row goes.
        client.failure = null
        client.answer = null
        store.read(reader)
        assertNull(store.offer(reader))
    }

    @Test
    fun turningOnRenewingAndOffChangeTheOffer() = runTest {
        val client = FakeClient().apply { answer = offered }
        val store = AddByEmailStore(client)
        store.read(reader)
        store.open(reader)
        assertEquals("parcels-2@inbox.peek.example", store.offer(reader)?.inbox?.address)
        store.open(reader, renew = true)
        assertEquals("parcels-3@inbox.peek.example", store.offer(reader)?.inbox?.address)
        store.close(reader)
        assertEquals(offered, store.offer(reader))
        assertEquals(listOf("read", "open", "renew", "close"), client.calls)

        client.failure = IOException("offline")
        assertTrue(runCatching { store.open(reader) }.exceptionOrNull() is IOException)
        assertEquals(offered, store.offer(reader))
    }

    @Test
    fun switchingTheNotificationShowsTheAnswerAndAFailureKeepsWhatWasShown() = runTest {
        val client = FakeClient()
        val store = AddByEmailStore(client)
        store.setNotify(reader, false)
        assertEquals(false, store.offer(reader)?.inbox?.notify)
        assertNull(store.offer(other))

        client.failure = IOException("offline")
        assertTrue(runCatching { store.setNotify(reader, true) }.exceptionOrNull() is IOException)
        assertEquals(false, store.offer(reader)?.inbox?.notify)
        assertEquals(listOf("notify false", "notify true"), client.calls)
    }

    @Test
    fun aReadThatAnswersAfterAChangeBeganIsSetAside() = runTest {
        val client = FakeClient().apply { answer = offered }
        val store = AddByEmailStore(client)
        client.holding = CompletableDeferred()
        val reading = async { store.read(reader) }
        runCurrent()
        store.open(reader)
        client.holding?.complete(Unit)
        reading.await()
        assertEquals("parcels-2@inbox.peek.example", store.offer(reader)?.inbox?.address)
    }

    @Test
    fun aReturnToTheAppReadsAgainOnceTwoSecondsHavePassed() = runTest {
        var now = Instant.parse("2026-10-28T09:00:00Z")
        val client = FakeClient().apply { answer = offered }
        val store = AddByEmailStore(client) { now }
        // Settings' first read is its own.
        store.readAgain(reader)
        assertEquals(emptyList<String>(), client.calls)
        store.read(reader)
        store.readAgain(reader)
        now = now.plusMillis(1_999)
        store.readAgain(reader)
        assertEquals(listOf("read"), client.calls)
        now = now.plusMillis(1)
        store.readAgain(reader)
        assertEquals(listOf("read", "read"), client.calls)
    }
}
