package com.plhery.peekdeliverytracker.ui.settings

import com.plhery.peekdeliverytracker.core.AssistantsClient
import com.plhery.peekdeliverytracker.core.api.AssistantConnection
import com.plhery.peekdeliverytracker.core.api.AssistantConnectionKind
import com.plhery.peekdeliverytracker.core.api.AssistantScope
import com.plhery.peekdeliverytracker.core.api.AssistantsResponse
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred

/**
 * Assistants as a test sets them: each read answers [offer] (null for a server that lets assistants connect to no
 * account, as its 404 reads), or fails with [failure]; a Disconnect waits for [revoking] when a test holds it, then
 * fails with [revokeFailure] if a test sets one, or takes the assistant out of [offer]. [requests] notes what was
 * asked, in order. Nothing leaves the test.
 */
class FakeAssistantsClient : AssistantsClient {
    var offer: AssistantsResponse? = null
    var failure: Exception? = null
    var revokeFailure: Exception? = null
    var revoking: CompletableDeferred<Unit>? = null
    val requests = mutableListOf<String>()

    override suspend fun read(): AssistantsResponse? {
        requests += "read"
        failure?.let { throw it }
        return offer
    }

    override suspend fun revoke(id: UUID) {
        requests += "revoke $id"
        revoking?.await()
        revokeFailure?.let { throw it }
        offer = offer?.let { it.copy(connections = it.connections.filter { connection -> connection.id != id }) }
    }
}

/** Synthetic assistants and Peek's address for them, at the tests' fixed moment (2026-10-29 05:11 UTC). */
object AssistantsFixtures {
    const val ADDRESS = "https://example.com/mcp"

    /** Connected with OAuth, used an hour ago: it sees and adds parcels. */
    val claude = AssistantConnection(
        id = UUID.fromString("0f6c1d2e-3a4b-4c5d-8e9f-0a1b2c3d4e5f"), kind = AssistantConnectionKind.Oauth, name = "Claude",
        redirectHost = "claude.ai", scopes = listOf(AssistantScope.ParcelsRead, AssistantScope.ParcelsWrite),
        createdAt = "2026-10-20T08:00:00.000Z", lastUsedAt = "2026-10-29T04:11:00.000Z",
    )

    /** Connected with OAuth, not used yet: it only sees parcels. */
    val chatGpt = AssistantConnection(
        id = UUID.fromString("1f6c1d2e-3a4b-4c5d-8e9f-0a1b2c3d4e5f"), kind = AssistantConnectionKind.Oauth, name = "ChatGPT",
        redirectHost = "chatgpt.com", scopes = listOf(AssistantScope.ParcelsRead), createdAt = "2026-10-28T08:00:00.000Z",
    )

    /** A personal token, which Settings doesn't list. */
    val token = AssistantConnection(
        id = UUID.fromString("2f6c1d2e-3a4b-4c5d-8e9f-0a1b2c3d4e5f"), kind = AssistantConnectionKind.Personal, name = "Script",
        scopes = listOf(AssistantScope.ParcelsRead), createdAt = "2026-10-01T08:00:00.000Z",
    )

    fun offer(connections: List<AssistantConnection> = listOf(claude, chatGpt, token)) = AssistantsResponse(connections = connections, mcpUrl = ADDRESS)
}
