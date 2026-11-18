package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.AddByEmail
import com.plhery.peekdeliverytracker.core.api.AddByEmailEntry
import com.plhery.peekdeliverytracker.core.api.AddByEmailResponse
import com.plhery.peekdeliverytracker.core.api.AddByEmailResult
import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.ForwardingConfirmation
import java.time.Duration
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

// The web's src/lib/addByEmail.ts and AddByEmail.tsx's `useAddByEmail`: Settings' "Add parcels by email", which the
// server opens to some accounts only.

internal const val ADD_BY_EMAIL = "/api/account/inbox"

/** The forwarding address's requests of `DeliveryApiClient`, for the account signed in: the app's client, or a test's fake. */
interface AddByEmailClient {
    /** The account's address and what its last emails did, or null when the server offers this account none (404). */
    suspend fun read(): AddByEmailResponse?

    /** Makes the address, or with [renew] a new one in its place. */
    suspend fun open(renew: Boolean): AddByEmailResponse

    /** Turns adding by email off. */
    suspend fun close()

    /** Switches the notification of each parcel an email adds on or off. */
    suspend fun setNotify(notify: Boolean): AddByEmailResponse
}

/** The forwarding address's requests through the app's API client. */
fun DeliveryApiClient.addByEmailClient(): AddByEmailClient = object : AddByEmailClient {
    override suspend fun read() = readAddByEmail()

    override suspend fun open(renew: Boolean) = openAddByEmail(renew)

    override suspend fun close() = closeAddByEmail()

    override suspend fun setNotify(notify: Boolean) = setAddByEmailNotify(notify)
}

/**
 * [AddByEmailResponse] read so that what the server adds later never breaks the page: an email whose result this
 * version doesn't know, or can't read at all, is left out of `recent`; a `lastResult` it doesn't know reads as none,
 * and so does a confirmation it can't read.
 */
object AddByEmailResponseReader : JsonTransformingSerializer<AddByEmailResponse>(AddByEmailResponse.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        val response = element as? JsonObject ?: return element
        val inbox = response["inbox"] as? JsonObject ?: return element
        val read = inbox.mapValues { (key, value) ->
            when (key) {
                "recent" -> if (value is JsonArray) JsonArray(value.filter { readable(AddByEmailEntry.serializer(), it) }) else value
                "lastResult" -> if (value is JsonPrimitive && value.isString && AddByEmailResult.fromRawValue(value.content) == null) JsonNull else value
                "confirmation" -> if (value is JsonObject && !readable(ForwardingConfirmation.serializer(), value)) JsonNull else value
                else -> value
            }
        }
        return JsonObject(response + ("inbox" to JsonObject(read)))
    }

    private fun <T> readable(deserializer: DeserializationStrategy<T>, element: JsonElement): Boolean =
        try {
            ApiJson.decodeFromJsonElement(deserializer, element)
            true
        } catch (_: Exception) {
            false
        }
}

/** How many of the last emails Settings lists. */
const val EMAILS_SHOWN = 5

/**
 * The last emails, newest first, as Settings lists them. A mailbox from before they were kept shows its one last
 * email, without its sender.
 */
fun AddByEmail.recentEmails(): List<AddByEmailEntry> {
    if (recent.isNotEmpty()) return recent.take(EMAILS_SHOWN)
    val at = lastMailAt
    val result = lastResult
    return if (at != null && result != null) listOf(AddByEmailEntry(at = at, sender = "", result = result, added = lastAdded ?: 0)) else emptyList()
}

/**
 * What an email did, in a few words. One that held no number from a shop that shows it only in the reader's account
 * with it ([AddByEmailEntry.account]) says where it is.
 */
fun AddByEmailEntry.resultText(localizer: Localizer): String = when (result) {
    AddByEmailResult.Added -> localizer.text("inbox.result.added.many", mapOf("count" to added))
    AddByEmailResult.Known -> localizer.text("inbox.result.known")
    AddByEmailResult.Named -> localizer.text("inbox.result.named")
    AddByEmailResult.Delivered -> localizer.text("inbox.result.delivered")
    AddByEmailResult.None -> account?.let { localizer.text("inbox.result.account", mapOf("shop" to it)) } ?: localizer.text("inbox.result.none")
    AddByEmailResult.Limited -> localizer.text("inbox.result.limited")
    AddByEmailResult.Full -> localizer.text("inbox.result.full")
    AddByEmailResult.Confirmation -> localizer.text("inbox.result.confirmation")
}

/** Whether the email held no number and its row doesn't say where it is, which Settings' note then explains. */
val AddByEmailEntry.heldNoNumberUnexplained: Boolean
    get() = result == AddByEmailResult.None && account == null

/** Whether each parcel an email adds is notified: on unless switched off, as an address from before the switch is. */
val AddByEmail.notifies: Boolean
    get() = notify ?: true

/** Whether Settings says how many emails today took: once half the day's allowance is used. */
val AddByEmailResponse.showsUsage: Boolean
    get() = inbox?.let { it.usedToday * 2 >= dailyLimit } ?: false

/** What the server offers the account [account]: its address, if it has one, and the day's allowance. */
data class AddByEmailOffer(val account: UUID, val value: AddByEmailResponse)

/**
 * Settings' "Add parcels by email" for the account signed in (the web's `useAddByEmail` and AddByEmailPage's runs):
 * whether the server offers it a forwarding address, read as Settings opens and again as the reader comes back to the
 * app, where Gmail's confirmation may have come in meanwhile; and the address turned on, renewed and turned off, and
 * its notification switched.
 *
 * [offer] is null until a read found the server offering one: it answers 404 until the feature is set up and open to
 * the account, and Settings then shows no row. A read that fails leaves what was read before, and what was read for
 * one account never shows for another. A read that answers after a change began is set aside, as the change's answer
 * is newer. Used from the main thread.
 */
class AddByEmailStore(private val client: AddByEmailClient, private val now: () -> Instant = AppClock::now) {
    private val offerState = MutableStateFlow<AddByEmailOffer?>(null)

    /** What was last read or changed, for the account it was read for. */
    val offerFlow: StateFlow<AddByEmailOffer?> = offerState.asStateFlow()

    /** What the server offers [account], or null: Settings' row and page show only for it. */
    fun offer(account: UUID?): AddByEmailResponse? = offerState.value?.takeIf { it.account == account }?.value

    /** Bumped as each change begins and ends: a read that saw it move is set aside. */
    private var changes = 0
    private var readAt: Instant? = null

    /** Reads what the server offers [account]. */
    suspend fun read(account: UUID) {
        val started = changes
        readAt = now()
        val value = try {
            client.read()
        } catch (error: CancellationException) {
            throw error
        } catch (_: Exception) {
            return
        }
        if (started != changes) return
        offerState.value = value?.let { AddByEmailOffer(account, it) }
    }

    /**
     * The reader is back in the app, where Gmail's confirmation, sent while they were in Gmail, then shows: reads again,
     * unless no read began yet or one began within [REREAD_AFTER], as the same return does.
     */
    suspend fun readAgain(account: UUID) {
        val last = readAt ?: return
        if (Duration.between(last, now()) < REREAD_AFTER) return
        read(account)
    }

    /** Turns adding by email on for [account], or with [renew] gives it a new address. Throws what the request threw. */
    suspend fun open(account: UUID, renew: Boolean = false) {
        val value = changing { client.open(renew) }
        offerState.value = AddByEmailOffer(account, value)
    }

    /** Turns adding by email off for [account]: the offer stays, without an address. */
    suspend fun close(account: UUID) {
        changing { client.close() }
        val current = offerState.value?.takeIf { it.account == account } ?: return
        offerState.value = current.copy(value = current.value.copy(inbox = null))
    }

    /** Switches the notification of each parcel an email adds on or off for [account]: the address as the server answers it. */
    suspend fun setNotify(account: UUID, notify: Boolean) {
        val value = changing { client.setNotify(notify) }
        offerState.value = AddByEmailOffer(account, value)
    }

    private suspend fun <T> changing(change: suspend () -> T): T {
        changes += 1
        try {
            return change()
        } finally {
            changes += 1
        }
    }

    companion object {
        /** How soon a return to the app reads again: the activity resumed and its window focused tell of the same return. */
        val REREAD_AFTER: Duration = Duration.ofSeconds(2)
    }
}

/** The mail services Settings has steps for. `Other` is any service whose rules can forward. */
enum class MailService(val rawValue: String) {
    Gmail("gmail"), Outlook("outlook"), Icloud("icloud"), Yahoo("yahoo"), Other("other");

    /** The service's own name, which no language translates; none for `Other`. */
    val serviceName: String?
        get() = when (this) {
            Gmail -> "Gmail"
            Outlook -> "Outlook"
            Icloud -> "iCloud"
            Yahoo -> "Yahoo"
            Other -> null
        }
}

/**
 * src/lib/emailFilter.ts: the words a shipping email's subject uses, by the language it is written in, from
 * shared/email-filter.json (EmailFilter.json), which the web and the iPhone read too; and the mail filter and the
 * rules' words Settings builds from them.
 */
object EmailFilter {
    private class Rules(val words: Map<String, List<String>>, val mainWords: Map<String, List<String>>)

    private val rules: Rules by lazy {
        val document = ApiJson.parseToJsonElement(SharedResources.text("EmailFilter.json")) as JsonObject
        fun lists(key: String): Map<String, List<String>> = (document.getValue(key) as JsonObject).mapValues { (_, words) ->
            (words as JsonArray).map { (it as JsonPrimitive).content }
        }
        Rules(lists("words"), lists("mainWords"))
    }

    /** The language [code]'s name in [locale], the language Peek shows, capitalised as a choice is: "Allemand". */
    fun languageName(code: String, locale: Locale): String {
        val name = Locale.forLanguageTag(code).getDisplayLanguage(locale).ifEmpty { code.uppercase(Locale.ROOT) }
        return name.take(1).uppercase(locale) + name.drop(1)
    }

    /** Each language's words, as the file lists them. */
    val shippingSubjectWords: Map<String, List<String>> get() = rules.words

    /** The few words of each language that most shipping subjects hold, the most telling first. */
    val mainSubjectWords: Map<String, List<String>> get() = rules.mainWords

    /** The languages a filter can take, in the order they are offered. */
    val languages: List<String> get() = rules.words.keys.toList()

    /**
     * The languages a reader's filter starts with: the one Peek shows, the phone's, and English, which shops and
     * carriers abroad write in. A language tag counts by its language alone, `pt-BR` as `pt`.
     */
    fun languagesFor(tags: List<String>): List<String> {
        val chosen = (tags + "en").map { it.lowercase().split('-')[0] }.toSet()
        return languages.filter { it in chosen }
    }

    /**
     * The search Gmail's filter takes for emails written in these languages: one of their words in the subject. A word
     * two languages share is named once. Empty when no language is chosen.
     */
    fun gmailFilter(chosen: Collection<String>): String {
        val words = LinkedHashMap<String, String>()
        for (language in languages) {
            if (language !in chosen) continue
            for (word in rules.words.getValue(language)) {
                words.getOrPut(word.lowercase()) { if (' ' in word) "\"$word\"" else word }
            }
        }
        return if (words.isEmpty()) "" else "subject:(${words.values.joinToString(" OR ")})"
    }

    /**
     * The main words of these languages, for a rule that asks "subject includes". Each language's first word comes
     * before any second one, so that a reader who stops early has covered every language.
     */
    fun subjectWords(chosen: Collection<String>): List<String> {
        val lists = languages.filter { it in chosen }.map { rules.mainWords[it].orEmpty() }
        val words = LinkedHashMap<String, String>()
        var rank = 0
        while (lists.any { rank < it.size }) {
            for (list in lists) {
                val word = list.getOrNull(rank) ?: continue
                words.getOrPut(word.lowercase()) { word }
            }
            rank += 1
        }
        return words.values.toList()
    }

    /**
     * Gmail's settings (Settings › See all settings), from which the reader opens Forwarding and POP/IMAP. A link straight
     * to that tab, or a reload of it, can show the tab without its "Add a forwarding address" button.
     */
    const val GMAIL_SETTINGS_LINK = "https://mail.google.com/mail/#settings/general"

    /** Where Outlook.com lists its rules, and iCloud's mail on the web. */
    const val OUTLOOK_RULES_LINK = "https://outlook.live.com/mail/0/options/mail/rules"
    const val ICLOUD_MAIL_LINK = "https://www.icloud.com/mail"

    /** The services' domains by their name, whatever the country ending: "hotmail.fr", "yahoo.co.uk". */
    private val serviceDomainNames = mapOf(
        "gmail" to MailService.Gmail, "googlemail" to MailService.Gmail,
        "outlook" to MailService.Outlook, "hotmail" to MailService.Outlook, "live" to MailService.Outlook, "msn" to MailService.Outlook,
        "icloud" to MailService.Icloud,
        "yahoo" to MailService.Yahoo, "ymail" to MailService.Yahoo, "rocketmail" to MailService.Yahoo,
    )
    private val icloudDomains = listOf("me.com", "mac.com")

    /** The mail service an address is most likely read in, by its domain. An address of another domain starts on Gmail. */
    fun mailServiceOf(email: String?): MailService {
        val domain = email?.trim()?.lowercase()?.split('@')?.getOrNull(1) ?: ""
        if (domain in icloudDomains) return MailService.Icloud
        return serviceDomainNames[domain.split('.')[0]] ?: MailService.Gmail
    }
}

/** A piece of a forwarding step's words: plain, a mail service's own name for a button or a field, or Gmail's options button. */
sealed interface StepPiece {
    data class Plain(val text: String) : StepPiece

    data class Bold(val text: String) : StepPiece

    data object Options : StepPiece
}

/**
 * A forwarding step's words as the translation marks them: the mail service's own button and field names `**…**`, in
 * bold, and Gmail's options button where the translation puts `{{options}}`, which is given [OPTIONS_MARK]: it has no
 * written name on screen, only its glyph.
 */
object StepText {
    /** What `{{options}}` is given, for the glyph to stand in. */
    const val OPTIONS_MARK = "[[options]]"

    fun pieces(text: String): List<StepPiece> {
        val pieces = mutableListOf<StepPiece>()
        val plain = StringBuilder()
        fun flush() {
            if (plain.isNotEmpty()) pieces += StepPiece.Plain(plain.toString())
            plain.clear()
        }
        var index = 0
        while (index < text.length) {
            if (text.startsWith(OPTIONS_MARK, index)) {
                flush()
                pieces += StepPiece.Options
                index += OPTIONS_MARK.length
                continue
            }
            if (text.startsWith("**", index)) {
                val end = text.indexOf('*', index + 2).takeIf { it >= 0 } ?: text.length
                if (end > index + 2 && text.startsWith("**", end)) {
                    flush()
                    pieces += StepPiece.Bold(text.substring(index + 2, end))
                    index = end + 2
                    continue
                }
            }
            plain.append(text[index])
            index += 1
        }
        flush()
        return pieces
    }

    /** What TalkBack reads: the words without their marks. The options button is named in words beside its glyph. */
    fun spoken(text: String): String = pieces(text).joinToString("") {
        when (it) {
            is StepPiece.Plain -> it.text
            is StepPiece.Bold -> it.text
            StepPiece.Options -> ""
        }
    }.replace("  ", " ")
}
