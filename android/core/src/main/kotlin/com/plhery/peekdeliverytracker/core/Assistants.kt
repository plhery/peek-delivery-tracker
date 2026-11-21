package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.AssistantConnection
import com.plhery.peekdeliverytracker.core.api.AssistantConnectionKind
import com.plhery.peekdeliverytracker.core.api.AssistantScope
import com.plhery.peekdeliverytracker.core.api.AssistantsResponse
import java.time.Instant
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

// Assistants.swift: Settings › Assistants, as on the website. The server opens it to some accounts only.

internal const val ASSISTANTS = "/api/account/assistants"

/** The assistants' requests of `DeliveryApiClient`, for the account signed in: the app's client, or a test's fake. */
interface AssistantsClient {
    /** Peek's address for assistants and the ones connected, or null when the server answers 404: none may connect to this account. */
    suspend fun read(): AssistantsResponse?

    /** Disconnects an assistant; one already gone counts as done. */
    suspend fun revoke(id: UUID)
}

/** The assistants' requests through the app's API client. */
fun DeliveryApiClient.assistantsClient(): AssistantsClient = object : AssistantsClient {
    override suspend fun read() = readAssistants()

    override suspend fun revoke(id: UUID) = revokeAssistant(id)
}

/**
 * [AssistantsResponse] read leniently (`AssistantsOffer`): a connection this version can't read, such as a kind or a
 * permission a newer server adds, drops out of the list instead of failing the whole page.
 */
object AssistantsResponseReader : JsonTransformingSerializer<AssistantsResponse>(AssistantsResponse.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        val response = element as? JsonObject ?: return element
        val connections = response["connections"] as? JsonArray ?: return element
        return JsonObject(response + ("connections" to JsonArray(connections.filter(::readable))))
    }

    private fun readable(element: JsonElement): Boolean =
        try {
            ApiJson.decodeFromJsonElement(AssistantConnection.serializer(), element)
            true
        } catch (_: Exception) {
            false
        }
}

/** The assistants connected with OAuth, which Settings lists: personal tokens have no screen. */
val AssistantsResponse.assistants: List<AssistantConnection>
    get() = connections.filter { it.kind == AssistantConnectionKind.Oauth }

/** What the connection may do, as Settings words it: whether it sees, adds, and edits and archives parcels. */
val AssistantConnection.scopeKey: String
    get() {
        val kinds = listOf(AssistantScope.ParcelsRead to "read", AssistantScope.ParcelsWrite to "write", AssistantScope.ParcelsEdit to "edit")
            .filter { it.first in scopes }
            .joinToString(" ") { it.second }
        return when (kinds) {
            "read" -> "assistants.scope.read"
            "write" -> "assistants.scope.write"
            "edit" -> "assistants.scope.edit"
            "read write" -> "assistants.scope.both"
            "read edit" -> "assistants.scope.readEdit"
            "write edit" -> "assistants.scope.writeEdit"
            "read write edit" -> "assistants.scope.all"
            else -> "assistants.scope.read"
        }
    }

/**
 * A connected assistant's line under its name (`AssistantRow.detail`): where it sends the reader back, what it may do
 * and when it was last used, " · " between them.
 */
fun AssistantConnection.detail(localizer: Localizer, now: Instant = AppClock.now()): String {
    val used = lastUsedAt?.let { localizer.text("assistants.lastUsed", mapOf("time" to localizer.relativeTime(from = it, now = now))) }
        ?: localizer.text("assistants.neverUsed")
    return listOfNotNull(redirectHost, localizer.text(scopeKey), used).joinToString(" · ")
}

/** The public page on connecting an assistant, in the reader's language (`AssistantsPage.guideURL`). English has no prefix. */
fun assistantsGuideUrl(apiBaseUrl: String, language: AppLanguage): String =
    apiBaseUrl.appendingPath(if (language == AppLanguage.En) "assistants" else "${language.rawValue}/assistants")

/** What a setup line's `{{…}}` stands for: the name of an assistant's menu or button, a page of its own, or a command to type. */
sealed interface SetupLabel {
    data class Name(val name: String) : SetupLabel

    data class Link(val text: String, val url: String) : SetupLabel

    data class Command(val command: String) : SetupLabel
}

/** A piece of a setup line as Settings draws it: words, an assistant's menu or button in bold, a link, or code. */
sealed interface SetupPiece {
    val text: String

    data class Plain(override val text: String) : SetupPiece

    data class Name(override val text: String) : SetupPiece

    data class Link(override val text: String, val url: String) : SetupPiece

    data class Command(override val text: String) : SetupPiece
}

/** A line's copy key, and what each of its `{{…}}` stands for (`AssistantSetup.Line`). */
data class SetupLine(val key: String, val labels: Map<String, SetupLabel> = emptyMap()) {
    /**
     * The line in the reader's language, with the assistant's menus and buttons as names, its own pages as links and
     * commands as code, `{{address}}` in them being Peek's address for assistants. A `{{…}}` the line has no label for
     * stays as written.
     */
    fun pieces(localizer: Localizer, address: String): List<SetupPiece> {
        val source = localizer.text(key)
        val pieces = mutableListOf<SetupPiece>()
        val plain = StringBuilder()
        fun flush() {
            if (plain.isNotEmpty()) pieces += SetupPiece.Plain(plain.toString())
            plain.clear()
        }
        var index = 0
        while (true) {
            val open = source.indexOf("{{", index)
            if (open < 0) break
            val close = source.indexOf("}}", open + 2)
            if (close < 0) break
            plain.append(source, index, open)
            val label = labels[source.substring(open + 2, close)]
            if (label == null) {
                plain.append(source, open, close + 2)
            } else {
                flush()
                pieces += when (label) {
                    is SetupLabel.Name -> SetupPiece.Name(label.name)
                    is SetupLabel.Link -> SetupPiece.Link(label.text, label.url)
                    is SetupLabel.Command -> SetupPiece.Command(label.command.replace("{{address}}", address))
                }
            }
            index = close + 2
        }
        plain.append(source, index, source.length)
        flush()
        return pieces
    }

    /** The line's words alone, as TalkBack and a search read them. */
    fun text(localizer: Localizer, address: String): String = pieces(localizer, address).joinToString("") { it.text }
}

/**
 * How to add Peek to one assistant (`AssistantSetup`), from shared/assistant-setups.json, which the website and the
 * iPhone read too: each line is a copy key and the assistant's own names its `{{…}}` stand for, in English as its
 * help pages give them.
 */
data class AssistantSetup(
    val name: String,
    /** Which plans and places can. */
    val who: SetupLine,
    val steps: List<SetupLine>,
    /** What to do where the steps don't apply. */
    val fallback: SetupLine? = null,
    /** The one thing to do, shown under the name. */
    val lead: SetupLine? = null,
) {
    /**
     * The steps with signing in to Peek as the one before the last: the assistant asks for it when the connector is
     * added or first used.
     */
    fun stepsWithSignIn(localizer: Localizer): List<SetupLine> {
        val signIn = SetupLine("assistants.setup.signIn", mapOf("allow" to SetupLabel.Name(localizer.text("assistants.consent.allow"))))
        return steps.dropLast(1) + signIn + steps.takeLast(1)
    }
}

/**
 * The setups the app bundles (AssistantSetups.json), read leniently: a line or a label of a kind this version doesn't
 * know drops out, a label's `{{…}}` then staying as written, and a setup without a name or a `who` line drops out
 * whole. None if the file can't be read.
 */
object AssistantSetups {
    val bundled: List<AssistantSetup> by lazy {
        try {
            read(SharedResources.text("AssistantSetups.json"))
        } catch (_: Exception) {
            emptyList()
        }
    }

    fun read(json: String): List<AssistantSetup> {
        val document = ApiJson.parseToJsonElement(json) as? JsonObject ?: return emptyList()
        val setups = document["setups"] as? JsonArray ?: return emptyList()
        return setups.mapNotNull(::setup)
    }

    private fun setup(element: JsonElement): AssistantSetup? {
        val setup = element as? JsonObject ?: return null
        val name = setup["name"]?.string() ?: return null
        val who = setup["who"]?.let(::line) ?: return null
        val steps = (setup["steps"] as? JsonArray)?.mapNotNull(::line).orEmpty()
        return AssistantSetup(name, who, steps, setup["fallback"]?.let(::line), setup["lead"]?.let(::line))
    }

    /** Written as `[key]` or `[key, {labels}]`. */
    private fun line(element: JsonElement): SetupLine? {
        val line = element as? JsonArray ?: return null
        val key = line.getOrNull(0)?.string() ?: return null
        val labels = line.getOrNull(1) as? JsonObject
        return SetupLine(key, labels?.mapNotNull { (name, value) -> label(value)?.let { name to it } }?.toMap().orEmpty())
    }

    /** A string (a menu's or a button's name), `{"text", "href"}` (a link of the web's) or `{"command"}`. */
    private fun label(element: JsonElement): SetupLabel? {
        element.string()?.let { return SetupLabel.Name(it) }
        val label = element as? JsonObject ?: return null
        label["command"]?.string()?.let { return SetupLabel.Command(it) }
        val text = label["text"]?.string() ?: return null
        val href = label["href"]?.string()?.takeIf { it.startsWith("https://") || it.startsWith("http://") } ?: return null
        return SetupLabel.Link(text, href)
    }

    private fun JsonElement.string(): String? = (this as? JsonPrimitive)?.takeIf { it.isString }?.content
}

/** What the server offers the account [account]: Peek's address for assistants and the ones connected. */
data class AssistantsOffer(val account: UUID, val value: AssistantsResponse)

/**
 * Settings' Assistants for the account signed in (`AssistantsModel`): whether assistants may connect to it, read as
 * Settings opens, and Disconnect.
 *
 * [offer] is null until a read found the server offering it: it answers 404 until the feature is set up and open to
 * the account, and Settings then shows no row. A read that fails leaves what was read before, which before any answer
 * is no row, and what was read for one account never shows for another. A read that answers after a Disconnect began
 * is set aside, as it would bring back what that took away. Used from the main thread.
 */
class AssistantsStore(private val client: AssistantsClient) {
    private val offerState = MutableStateFlow<AssistantsOffer?>(null)
    private val workingState = MutableStateFlow(false)
    private val failureState = MutableStateFlow<Throwable?>(null)

    /** What was last read or changed, for the account it was read for. */
    val offerFlow: StateFlow<AssistantsOffer?> = offerState.asStateFlow()

    /** Whether a Disconnect waits for the server: no other can begin meanwhile. */
    val workingFlow: StateFlow<Boolean> = workingState.asStateFlow()

    /** Why the last Disconnect failed, until the next one begins or Settings closes ([dismiss]). */
    val failureFlow: StateFlow<Throwable?> = failureState.asStateFlow()

    /** What the server offers [account], or null: Settings' row and page show only for it. */
    fun offer(account: UUID?): AssistantsResponse? = offerState.value?.takeIf { it.account == account }?.value

    /** Bumped as each Disconnect begins and ends: a read that saw it move is set aside. */
    private var changes = 0

    /** Bumped as each read begins: a read that answers after a newer one began is set aside. */
    private var reads = 0

    /** Reads what the server offers [account]. A failure leaves what is shown: a moment offline doesn't take the row away. */
    suspend fun read(account: UUID) {
        val started = changes
        val read = ++reads
        val value = try {
            client.read()
        } catch (error: CancellationException) {
            throw error
        } catch (_: Exception) {
            return
        }
        if (started != changes || read != reads) return
        offerState.value = value?.let { AssistantsOffer(account, it) }
    }

    /**
     * Disconnects the assistant [id] from [account]: it loses access to the account at once, and leaves the list. A
     * failure keeps it there and says why ([failureFlow]). False when it failed, or another Disconnect still waits.
     */
    suspend fun revoke(account: UUID, id: UUID): Boolean {
        if (workingState.value) return false
        workingState.value = true
        failureState.value = null
        changes += 1
        try {
            client.revoke(id)
            val current = offerState.value
            if (current != null && current.account == account) {
                offerState.value = current.copy(value = current.value.copy(connections = current.value.connections.filter { it.id != id }))
            }
            return true
        } catch (error: CancellationException) {
            throw error
        } catch (error: Exception) {
            failureState.value = error
            return false
        } finally {
            changes += 1
            workingState.value = false
        }
    }

    /** Settings closed: why the last Disconnect failed goes with it, as a new `AssistantsModel` starts without. */
    fun dismiss() {
        failureState.value = null
    }
}
