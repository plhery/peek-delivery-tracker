package com.plhery.peekdeliverytracker.ui.settings

import com.plhery.peekdeliverytracker.core.AddByEmailClient
import com.plhery.peekdeliverytracker.core.api.AddByEmail
import com.plhery.peekdeliverytracker.core.api.AddByEmailEntry
import com.plhery.peekdeliverytracker.core.api.AddByEmailResponse
import com.plhery.peekdeliverytracker.core.api.AddByEmailResult
import com.plhery.peekdeliverytracker.core.api.ForwardingConfirmation
import kotlinx.coroutines.CompletableDeferred

/**
 * The forwarding address as a test sets it: each read answers [offer] (null for a server that offers the account none,
 * as its 404 reads), or fails with [failure]; turning on gives [opened], renewing [renewed], turning off leaves the
 * offer without an address, and switching the notification gives the address with it switched. A change waits for
 * [changing] when a test holds it, then fails with [changeFailure] if a test sets one. [requests] notes what was asked,
 * in order. Nothing leaves the test.
 */
class FakeAddByEmailClient : AddByEmailClient {
    var offer: AddByEmailResponse? = null
    var failure: Exception? = null
    var changeFailure: Exception? = null
    var changing: CompletableDeferred<Unit>? = null
    var opened: AddByEmail = AddByEmailFixtures.mailbox()
    var renewed: AddByEmail = AddByEmailFixtures.mailbox(address = AddByEmailFixtures.RENEWED)
    val requests = mutableListOf<String>()

    override suspend fun read(): AddByEmailResponse? {
        requests += "read"
        failure?.let { throw it }
        return offer
    }

    override suspend fun open(renew: Boolean): AddByEmailResponse {
        requests += if (renew) "renew" else "open"
        changing?.await()
        changeFailure?.let { throw it }
        return AddByEmailResponse(inbox = if (renew) renewed else opened, dailyLimit = offer?.dailyLimit ?: AddByEmailFixtures.DAILY_LIMIT)
            .also { offer = it }
    }

    override suspend fun close() {
        requests += "close"
        changing?.await()
        changeFailure?.let { throw it }
        offer = offer?.copy(inbox = null)
    }

    override suspend fun setNotify(notify: Boolean): AddByEmailResponse {
        requests += "notify $notify"
        changing?.await()
        changeFailure?.let { throw it }
        val current = checkNotNull(offer?.inbox) { "No address to switch" }
        return AddByEmailResponse(inbox = current.copy(notify = notify), dailyLimit = offer?.dailyLimit ?: AddByEmailFixtures.DAILY_LIMIT)
            .also { offer = it }
    }
}

/** Synthetic forwarding addresses and emails, at the tests' fixed moment (2026-10-29 10:11 UTC). */
object AddByEmailFixtures {
    const val ADDRESS = "add+abcdefghijklmnop@in.example.com"
    const val RENEWED = "add+qrstuvwxyz234567@in.example.com"
    const val DAILY_LIMIT = 50

    fun mailbox(
        address: String = ADDRESS,
        recent: List<AddByEmailEntry> = emptyList(),
        usedToday: Int = recent.size,
        confirmation: ForwardingConfirmation? = null,
        notify: Boolean? = null,
    ) = AddByEmail(
        address = address, createdAt = "2026-10-20T08:00:00.000Z", usedToday = usedToday,
        lastMailAt = recent.firstOrNull()?.at, lastResult = recent.firstOrNull()?.result, lastAdded = recent.firstOrNull()?.added,
        recent = recent, confirmation = confirmation, notify = notify,
    )

    /** A week of forwarded emails, newest first, one of each kind the page words differently. */
    val recent = listOf(
        AddByEmailEntry(at = "2026-10-29T09:58:00.000Z", sender = "Atelier Lune", result = AddByEmailResult.Added, added = 1),
        AddByEmailEntry(at = "2026-10-28T21:20:00.000Z", sender = "Example Books", result = AddByEmailResult.Named, added = 0),
        AddByEmailEntry(at = "2026-10-27T14:05:00.000Z", sender = "Synthetic Market", result = AddByEmailResult.None, added = 0),
        AddByEmailEntry(at = "2026-10-25T16:40:00.000Z", sender = "Atelier Lune", result = AddByEmailResult.Delivered, added = 0),
    )

    /** Gmail's confirmation, come in while the reader was in Gmail. */
    val confirmation = ForwardingConfirmation(code = "123456789", link = "https://mail.google.com/mail/vf-synthetic", receivedAt = "2026-10-29T10:09:00.000Z")

    fun offer(inbox: AddByEmail? = mailbox(), dailyLimit: Int = DAILY_LIMIT) = AddByEmailResponse(inbox = inbox, dailyLimit = dailyLimit)
}
