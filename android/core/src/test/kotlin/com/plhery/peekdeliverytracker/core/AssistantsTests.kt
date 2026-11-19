package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.AssistantConnection
import com.plhery.peekdeliverytracker.core.api.AssistantConnectionKind
import com.plhery.peekdeliverytracker.core.api.AssistantScope
import com.plhery.peekdeliverytracker.core.api.AssistantsResponse
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
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Settings › Assistants (iOS's AssistantsTests): the setups the app bundles from shared/assistant-setups.json, the
 * client's two requests to `/api/account/assistants`, the answer read leniently, and the store behind the row.
 */
class AssistantsTests {
    private val address = "https://peek.example.test/mcp"

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

    private fun setup(name: String) = AssistantSetups.bundled.first { it.name == name }

    // The setups

    @Test
    fun everySetupReadsInEveryLanguageWithItsNamesFilledIn() {
        val setups = AssistantSetups.bundled
        assertEquals(listOf("Claude", "ChatGPT", "Gemini", "OpenClaw", "Home Assistant"), setups.map { it.name })
        for (language in AppLanguage.entries) {
            val localizer = Localizer(language)
            for (setup in setups) {
                val lines = listOf(setup.who) + setup.stepsWithSignIn(localizer) + listOfNotNull(setup.fallback)
                for (line in lines) {
                    val text = line.text(localizer, address)
                    assertFalse("${language.rawValue}: ${line.key}", text.contains("{{"))
                    assertNotEquals("${language.rawValue}: ${line.key}", line.key, text)
                }
            }
        }
    }

    @Test
    fun signingInIsTheStepBeforeTheLast() {
        val localizer = Localizer(AppLanguage.En)
        val steps = setup("Claude").stepsWithSignIn(localizer)
        assertEquals(
            listOf("assistants.setup.claude.open", "assistants.setup.claude.add", "assistants.setup.signIn", "assistants.setup.claude.use"),
            steps.map { it.key },
        )
        assertEquals("Sign in to Peek when it asks, then select Allow.", steps[2].text(localizer, address))
        assertEquals(listOf(SetupPiece.Name("Allow")), steps[2].pieces(localizer, address).filterIsInstance<SetupPiece.Name>())
        // Home Assistant has two steps: signing in comes between them.
        assertEquals(
            listOf("assistants.setup.homeAssistant.open", "assistants.setup.signIn", "assistants.setup.homeAssistant.use"),
            setup("Home Assistant").stepsWithSignIn(localizer).map { it.key },
        )
    }

    @Test
    fun aCommandHoldsPeeksAddressAsCodeAndAMenuIsBold() {
        val localizer = Localizer(AppLanguage.En)
        val code = setup("OpenClaw").steps[0].pieces(localizer, address).filterIsInstance<SetupPiece.Command>().map { it.text }
        assertEquals(
            listOf(
                "openclaw gateway stop",
                "openclaw mcp set peek '{\"url\":\"$address\",\"transport\":\"streamable-http\",\"auth\":\"oauth\"}'",
            ),
            code,
        )
        val line = setup("Claude").steps[0].pieces(localizer, address)
        assertTrue(line.contains(SetupPiece.Link("Peek Parcel Tracker", "https://claude.ai/directory/peek-parcel-tracker")))
        assertTrue(line.contains(SetupPiece.Name("Customize › Connectors")))
        assertEquals(
            "Open Peek Parcel Tracker in Claude’s connector directory, or in Claude open Customize › Connectors, select + and search for Peek.",
            setup("Claude").steps[0].text(localizer, address),
        )
    }

    @Test
    fun aNameTheLineHasNoLabelForStaysAsWritten() {
        val localizer = Localizer(AppLanguage.En)
        assertEquals(listOf(SetupPiece.Plain("Select {{connect}}.")), SetupLine("assistants.setup.claude.add").pieces(localizer, address))
        val other = SetupLine("assistants.setup.claude.add", mapOf("elsewhere" to SetupLabel.Name("Connect")))
        assertEquals("Select {{connect}}.", other.text(localizer, address))
        val named = SetupLine("assistants.setup.claude.add", mapOf("connect" to SetupLabel.Name("Connect")))
        assertEquals(listOf(SetupPiece.Plain("Select "), SetupPiece.Name("Connect"), SetupPiece.Plain(".")), named.pieces(localizer, address))
    }

    @Test
    fun aLineOrALabelOfAKindThisVersionDoesntKnowDropsOut() {
        val setups = AssistantSetups.read(
            """
            {"setups":[
              {"name":"Synthetic","who":["assistants.setup.gemini.who"],"steps":[
                ["assistants.setup.claude.open",{"listing":{"text":"Listing","href":"javascript:alert(1)"},"connectors":{"image":"menu.png"},"plus":"+"}],
                {"key":"assistants.setup.claude.add"},
                ["assistants.setup.claude.use",{"plus":"+","connectors":"Connectors"},"extra"],
                [42]
              ],"fallback":{"key":"assistants.setup.claude.custom"},"future":true},
              {"name":"Without who","steps":[["assistants.setup.gemini.use"]]},
              {"who":["assistants.setup.gemini.who"],"steps":[]},
              "future"
            ],"future":{}}
            """.trimIndent(),
        )
        val setup = setups.single()
        assertEquals("Synthetic", setup.name)
        assertNull(setup.fallback)
        assertEquals(listOf("assistants.setup.claude.open", "assistants.setup.claude.use"), setup.steps.map { it.key })
        // The link without a web address and the label of another kind drop out, their `{{…}}` staying.
        assertEquals(mapOf("plus" to SetupLabel.Name("+")), setup.steps[0].labels)
        val text = setup.steps[0].text(Localizer(AppLanguage.En), address)
        assertTrue(text, text.contains("{{listing}}") && text.contains("{{connectors}}") && !text.contains("{{plus}}"))
        assertEquals(emptyList<AssistantSetup>(), AssistantSetups.read("[]"))
        assertEquals(emptyList<AssistantSetup>(), AssistantSetups.read("""{"setups":{}}"""))
    }

    // The client

    private fun connection(id: String, kind: String = "oauth", scopes: String = """["parcels:read","parcels:write"]""", lastUsed: String = "null") =
        """{"id":"$id","kind":"$kind","name":"Claude","redirectHost":"claude.ai","scopes":$scopes,""" +
            """"createdAt":"2026-11-01T09:00:00Z","lastUsedAt":$lastUsed}"""

    private val claudeId = "0f6c1d2e-3a4b-4c5d-8e9f-0a1b2c3d4e5f"

    @Test
    fun theAssistantsAreReadWithTheAccountsToken() = runTest {
        val transport = serving(jsonResponse(200, """{"mcpUrl":"$address","connections":[${connection(claudeId, lastUsed = "\"2026-11-18T09:00:00Z\"")}]}"""))
        val offer = client(transport).readAssistants()

        assertEquals(address, offer?.mcpUrl)
        assertEquals(listOf("Claude"), offer?.connections?.map { it.name })
        assertEquals("2026-11-18T09:00:00Z", offer?.connections?.single()?.lastUsedAt)
        val request = transport.requests.single()
        assertEquals("GET https://peek.example/api/account/assistants", "${request.method} ${request.url}")
        assertEquals("Bearer synthetic-access", request.header("Authorization"))
        assertNull(request.body)
    }

    @Test
    fun anAccountAssistantsMayNotConnectToReadsAsNone() = runTest {
        assertNull(client(serving(jsonResponse(404, """{"error":"Not found"}"""))).readAssistants())
        assertNull(client(serving(jsonResponse(404))).readAssistants())
        // Other failures throw, as the account's other requests do.
        val failed = runCatching { client(serving(jsonResponse(500))).readAssistants() }.exceptionOrNull()
        assertTrue(failed is DeliveryApiError.ServiceFailed)
        val invalid = runCatching { client(serving(jsonResponse(200, "<html></html>"))).readAssistants() }.exceptionOrNull()
        assertTrue(invalid is DeliveryApiError.InvalidResponse)
    }

    @Test
    fun aConnectionThisAppCantReadDropsOut() = runTest {
        val future = connection("1f6c1d2e-3a4b-4c5d-8e9f-0a1b2c3d4e5f", kind = "robot", scopes = "[]")
        val unknownScope = connection("2f6c1d2e-3a4b-4c5d-8e9f-0a1b2c3d4e5f", scopes = """["parcels:delete"]""")
        val body = """{"mcpUrl":"$address","connections":[${connection(claudeId)},$future,$unknownScope,{"id":"not a uuid"}],"future":true}"""
        val offer = client(serving(jsonResponse(200, body))).readAssistants()

        assertEquals(address, offer?.mcpUrl)
        assertEquals(listOf(UUID.fromString(claudeId)), offer?.connections?.map { it.id })
        assertEquals("assistants.scope.both", offer?.connections?.single()?.scopeKey)
        // An answer without its address is no answer.
        val missing = runCatching { client(serving(jsonResponse(200, """{"connections":[]}"""))).readAssistants() }.exceptionOrNull()
        assertNotNull(missing)
    }

    @Test
    fun disconnectingSendsTheIdInLowercaseAndAnAssistantAlreadyGoneIsDone() = runTest {
        val transport = serving(jsonResponse(204))
        client(transport).revokeAssistant(UUID.fromString(claudeId.uppercase()))
        val request = transport.requests.single()
        assertEquals("DELETE https://peek.example/api/account/assistants/$claudeId", "${request.method} ${request.url}")
        assertEquals("Bearer synthetic-access", request.header("Authorization"))

        client(serving(jsonResponse(404, """{"error":"Not found"}"""))).revokeAssistant(UUID.fromString(claudeId))
        val failed = runCatching { client(serving(jsonResponse(500))).revokeAssistant(UUID.fromString(claudeId)) }.exceptionOrNull()
        assertTrue(failed is DeliveryApiError.ServiceFailed)
    }

    // What Settings shows of it

    private fun assistant(
        scopes: List<AssistantScope> = listOf(AssistantScope.ParcelsRead),
        redirectHost: String? = "claude.ai",
        lastUsedAt: String? = null,
        kind: AssistantConnectionKind = AssistantConnectionKind.Oauth,
        id: UUID = UUID.randomUUID(),
    ) = AssistantConnection(
        id = id, kind = kind, name = "Claude", redirectHost = redirectHost, scopes = scopes, createdAt = "2026-11-01T09:00:00Z",
        lastUsedAt = lastUsedAt,
    )

    @Test
    fun eachSetOfPermissionsHasItsWords() {
        val read = AssistantScope.ParcelsRead
        val write = AssistantScope.ParcelsWrite
        val edit = AssistantScope.ParcelsEdit
        val cases = listOf(
            listOf(read) to "assistants.scope.read",
            listOf(write) to "assistants.scope.write",
            listOf(edit) to "assistants.scope.edit",
            listOf(write, read) to "assistants.scope.both",
            listOf(read, edit) to "assistants.scope.readEdit",
            listOf(edit, write) to "assistants.scope.writeEdit",
            listOf(edit, read, write) to "assistants.scope.all",
            listOf(read, read) to "assistants.scope.read",
            emptyList<AssistantScope>() to "assistants.scope.read",
        )
        for ((scopes, key) in cases) assertEquals(scopes.toString(), key, assistant(scopes).scopeKey)
        for (language in AppLanguage.entries) {
            val localizer = Localizer(language)
            for ((scopes, key) in cases) assertNotEquals("$language $key", key, localizer.text(assistant(scopes).scopeKey))
        }
    }

    @Test
    fun aConnectedAssistantsLineSaysWhereWhatItMayDoAndWhenItWasUsed() {
        val localizer = Localizer(AppLanguage.En)
        val now = Instant.parse("2026-11-18T10:00:00Z")
        val used = assistant(listOf(AssistantScope.ParcelsRead, AssistantScope.ParcelsWrite), lastUsedAt = "2026-11-18T09:00:00Z")
        assertEquals("claude.ai · Sees, adds and archives parcels · used 1 h ago", used.detail(localizer, now))
        assertEquals("claude.ai · Sees your parcels · not used yet", assistant().detail(localizer, now))
        assertEquals("Sees your parcels · not used yet", assistant(redirectHost = null).detail(localizer, now))
        // Only the assistants connected with OAuth show: a personal token has no screen.
        val token = assistant(kind = AssistantConnectionKind.Personal)
        assertEquals(listOf(used), AssistantsResponse(connections = listOf(used, token), mcpUrl = address).assistants)
    }

    @Test
    fun theGuideIsInTheReadersLanguage() {
        assertEquals("https://peek.example/assistants", assistantsGuideUrl("https://peek.example", AppLanguage.En))
        assertEquals("https://peek.example/assistants", assistantsGuideUrl("https://peek.example/", AppLanguage.En))
        assertEquals("https://peek.example/fr/assistants", assistantsGuideUrl("https://peek.example", AppLanguage.Fr))
        assertEquals("https://peek.example/pt-br/assistants", assistantsGuideUrl("https://peek.example", AppLanguage.PtBr))
    }

    // The store

    private class FakeClient : AssistantsClient {
        var answer: AssistantsResponse? = null
        var failure: Exception? = null
        var revokeFailure: Exception? = null
        var holding: CompletableDeferred<Unit>? = null
        var revoking: CompletableDeferred<Unit>? = null
        val calls = mutableListOf<String>()

        override suspend fun read(): AssistantsResponse? {
            calls += "read"
            holding?.await()
            failure?.let { throw it }
            return answer
        }

        override suspend fun revoke(id: UUID) {
            calls += "revoke $id"
            revoking?.await()
            revokeFailure?.let { throw it }
        }
    }

    private val reader = UUID(0, 7)
    private val other = UUID(0, 8)
    private val claude = assistant(id = UUID(1, 1))
    private val chatGpt = assistant(id = UUID(1, 2), redirectHost = "chatgpt.com")
    private val offered = AssistantsResponse(connections = listOf(claude, chatGpt), mcpUrl = "https://peek.example.test/mcp")

    @Test
    fun theRowShowsOnlyOnceOfferedAndForTheAccountItWasReadFor() = runTest {
        val client = FakeClient()
        val store = AssistantsStore(client)
        assertNull(store.offer(reader))
        // The server's 404: none may connect to the account.
        store.read(reader)
        assertNull(store.offer(reader))

        client.answer = offered
        store.read(reader)
        assertEquals(offered, store.offer(reader))
        assertEquals(reader, store.offerFlow.value?.account)
        assertNull(store.offer(other))
        assertNull(store.offer(null))
    }

    @Test
    fun aFirstFailedReadHidesItALaterFailureKeepsWhatWasReadAndA404TakesItAway() = runTest {
        val client = FakeClient()
        val store = AssistantsStore(client)
        client.failure = IOException("offline")
        store.read(reader)
        assertNull(store.offer(reader))

        client.failure = null
        client.answer = offered
        store.read(reader)
        client.failure = DeliveryApiError.ServiceFailed(500)
        store.read(reader)
        assertEquals(offered, store.offer(reader))

        client.failure = null
        client.answer = null
        store.read(reader)
        assertNull(store.offer(reader))
    }

    @Test
    fun disconnectTakesTheAssistantAway() = runTest {
        val client = FakeClient().apply { answer = offered }
        val store = AssistantsStore(client)
        store.read(reader)

        assertTrue(store.revoke(reader, claude.id))
        assertEquals(listOf(chatGpt), store.offer(reader)?.connections)
        assertEquals(listOf("read", "revoke ${claude.id}"), client.calls)
        assertFalse(store.workingFlow.value)
        assertNull(store.failureFlow.value)
    }

    @Test
    fun aFailedDisconnectKeepsTheAssistantAndSaysWhy() = runTest {
        val client = FakeClient().apply { answer = offered }
        val store = AssistantsStore(client)
        store.read(reader)
        client.revokeFailure = DeliveryApiError.ServiceFailed(502)

        assertFalse(store.revoke(reader, claude.id))
        assertEquals(offered, store.offer(reader))
        assertTrue(store.failureFlow.value is DeliveryApiError.ServiceFailed)
        assertFalse(store.workingFlow.value)

        // The next Disconnect starts without it, and no other begins while one waits.
        client.revokeFailure = null
        client.revoking = CompletableDeferred()
        val revoking = async { store.revoke(reader, claude.id) }
        runCurrent()
        assertTrue(store.workingFlow.value)
        assertNull(store.failureFlow.value)
        assertFalse(store.revoke(reader, chatGpt.id))
        client.revoking?.complete(Unit)
        assertTrue(revoking.await())
        assertEquals(listOf("read", "revoke ${claude.id}", "revoke ${claude.id}"), client.calls)

        // Settings closing forgets why one failed.
        client.revokeFailure = IOException("offline")
        assertFalse(store.revoke(reader, chatGpt.id))
        assertNotNull(store.failureFlow.value)
        store.dismiss()
        assertNull(store.failureFlow.value)
    }

    @Test
    fun aReadThatAnswersAfterADisconnectBeganIsSetAside() = runTest {
        val client = FakeClient().apply { answer = offered }
        val store = AssistantsStore(client)
        store.read(reader)
        client.holding = CompletableDeferred()
        val reading = async { store.read(reader) }
        runCurrent()
        assertTrue(store.revoke(reader, claude.id))
        client.holding?.complete(Unit)
        reading.await()
        assertEquals(listOf(chatGpt), store.offer(reader)?.connections)
    }
}
