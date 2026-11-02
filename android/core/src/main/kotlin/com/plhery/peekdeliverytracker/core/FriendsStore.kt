package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendProfile
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendUpdate
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.FriendsActivity
import com.plhery.peekdeliverytracker.core.api.FriendsSnapshot
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneOffset
import java.time.temporal.TemporalAdjusters
import java.util.UUID
import kotlin.math.ceil
import kotlin.math.max
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.Serializable

// FriendsStore.swift's `FriendInvitationStore`, `FriendsStore`, `FriendStamp`'s keys and symbols and
// `FriendsActivityStore`. A Swift `Task` cancelled is a coroutine cancelled here: what it was waiting for then changes
// nothing.

/** The friends' requests of `DeliveryAPIClient`, for the account signed in: the app's client, or a test's fake. */
interface FriendsClient {
    /** `friendsSnapshot()`. */
    suspend fun snapshot(): FriendsSnapshot

    /** `friendsAction(_:)`. */
    suspend fun action(request: FriendsActionRequest): FriendsActionResponse

    /** `friendsActivity()`. */
    suspend fun activity(): FriendsActivity
}

/** The account's friends' requests through the app's API client. */
fun DeliveryApiClient.friends(): FriendsClient = object : FriendsClient {
    override suspend fun snapshot() = friendsSnapshot()

    override suspend fun action(request: FriendsActionRequest) = friendsAction(request)

    override suspend fun activity() = friendsActivity()
}

/**
 * `FriendsStore`: the circle as the Friends tab shows it, the account's from [client], or in the demo a local copy of
 * FriendsDemo.json with the demo's own card, which its actions change in memory. A load or an action answered after a
 * newer one began, or after [clear], changes nothing (`generation`). Used from the main thread, as the iPhone's is
 * `@MainActor`.
 */
class FriendsStore(private val client: FriendsClient) {
    private val snapshotState = MutableStateFlow<FriendsSnapshot?>(null)
    private val workingState = MutableStateFlow(false)
    private val errorKeyState = MutableStateFlow<String?>(null)
    val snapshotFlow: StateFlow<FriendsSnapshot?> = snapshotState.asStateFlow()
    val workingFlow: StateFlow<Boolean> = workingState.asStateFlow()
    val errorKeyFlow: StateFlow<String?> = errorKeyState.asStateFlow()

    val snapshot: FriendsSnapshot? get() = snapshotState.value
    val working: Boolean get() = workingState.value

    /** The copy key of what went wrong last, cleared by the next load that works, an action, or the panel's Close. */
    var errorKey: String?
        get() = errorKeyState.value
        set(value) {
            errorKeyState.value = value
        }

    private var demo = false
    private var local: FriendsSnapshot? = null
    private var generation = 0

    /** `seed(_:)`: the circle an accepted invitation answered with, shown before the first load. */
    fun seed(value: FriendsSnapshot) {
        snapshotState.value = value
    }

    /**
     * `configure(session:)`: the demo's circle or the account's. The demo's is FriendsDemo.json, read as it first shows
     * and kept, with what its actions change, until the session leaves the demo ([leaveDemo]).
     */
    fun configure(demo: Boolean) {
        this.demo = demo
        if (demo && local == null) {
            local = runCatching { ApiJson.decodeFromString(FriendsSnapshot.serializer(), SharedResources.text(DEMO_RESOURCE)) }.getOrNull()
        }
    }

    /**
     * The session has left the demo: what the demo's actions changed is forgotten, and its circle is FriendsDemo.json
     * afresh once it comes back, as the iPhone's tab makes its store (`@StateObject`) anew on the way back through the
     * first screen. The app's root calls it, not the tab, which Android disposes as another tab is chosen.
     */
    fun leaveDemo() {
        local = null
    }

    /** `load(parcels:)`: the circle afresh, the demo's with its own card from [parcels]; none and an error if it fails. */
    suspend fun load(parcels: List<Parcel>) {
        if (working) return
        generation += 1
        val current = generation
        try {
            val next = if (demo) {
                demoSnapshot(local ?: throw DeliveryApiError.InvalidResponse(), parcels)
            } else {
                client.snapshot()
            }
            if (current != generation || !currentCoroutineContext().isActive) return
            snapshotState.value = next
            errorKey = null
        } catch (error: Exception) {
            // A load called off (the tab hidden) throws on; one overtaken is forgotten.
            currentCoroutineContext().ensureActive()
            if (current != generation) return
            snapshotState.value = null
            errorKey = "friends.unavailable"
        }
    }

    /**
     * `act(_:parcels:onCommitted:)`: one action at a time. The demo saves its profile trimmed, turns friends off and
     * removes a friend in memory, and answers anything else with `friends.demoInvites`. [onCommitted] hears the answer
     * even when a newer request has made it stale. Null when it failed, was refused or was overtaken.
     */
    suspend fun act(
        request: FriendsActionRequest,
        parcels: List<Parcel>,
        onCommitted: ((FriendsActionResponse) -> Unit)? = null,
    ): FriendsActionResponse? {
        if (working) return null
        workingState.value = true
        errorKey = null
        generation += 1
        val current = generation
        try {
            val result: FriendsActionResponse
            if (demo) {
                var local = local ?: throw DeliveryApiError.InvalidResponse()
                when (request.action) {
                    FriendsAction.SaveProfile -> {
                        val name = request.nickname ?: return null
                        val stats = request.shareStats ?: return null
                        val arrival = request.shareArrival ?: return null
                        local = local.copy(profile = FriendProfile(name.trimmingWhitespacesAndNewlines(), stats, arrival))
                    }
                    FriendsAction.Disable -> local = FriendsSnapshot(friends = emptyList())
                    FriendsAction.RemoveFriend -> local = local.copy(friends = local.friends.filter { it.id != request.friendId })
                    else -> {
                        errorKey = "friends.demoInvites"
                        return null
                    }
                }
                this.local = local
                result = FriendsActionResponse(snapshot = demoSnapshot(local, parcels))
            } else {
                result = client.action(request)
            }
            onCommitted?.invoke(result)
            if (current != generation || !currentCoroutineContext().isActive) return null
            result.snapshot?.let { snapshotState.value = it }
            return result
        } catch (error: Exception) {
            currentCoroutineContext().ensureActive()
            if (current != generation) return null
            errorKey = errorKey(error)
            return null
        } finally {
            workingState.value = false
        }
    }

    /** `clear()`: nothing shown, and what was asked before forgotten. */
    fun clear() {
        generation += 1
        snapshotState.value = null
        errorKey = null
    }

    private fun demoSnapshot(value: FriendsSnapshot, parcels: List<Parcel>): FriendsSnapshot =
        value.copy(ownCard = value.profile?.let { ownCard(parcels, it) })

    companion object {
        const val DEMO_RESOURCE = "FriendsDemo.json"

        /** The demo's own card's id. */
        val OWN_CARD_ID: UUID = UUID.fromString("44000000-0000-4000-8000-000000000004")

        /** FriendsProfileForm's `value`: the nickname as it is saved, trimmed of whitespace and newlines. */
        fun nickname(typed: String): String = typed.trimmingWhitespacesAndNewlines()

        /** The copy key an action's failure shows: the service's three known refusals, else `friends.actionFailed`. */
        fun errorKey(error: Throwable): String = when ((error as? DeliveryApiError.Service)?.serviceMessage) {
            "Invitation unavailable" -> "friends.inviteUnavailable"
            "Cannot accept your own invitation" -> "friends.selfInvitation"
            "Your circle is full" -> "friends.circleFull"
            else -> "friends.actionFailed"
        }

        /**
         * `ownCard(parcels:profile:now:)`: the card [profile] would show friends, from [parcels] counted in UTC: the
         * stamps in their order, the average rounded up to whole days (one at least), and whether a parcel was delivered
         * since this ISO week began, each only where the profile shares it.
         */
        fun ownCard(parcels: List<Parcel>, profile: FriendProfile, now: Instant = Instant.now()): FriendCard {
            val stats = PassportStatistics(parcels, ZoneOffset.UTC)
            val stamps = buildList {
                if (stats.deliveredCount >= 1) add(FriendStamp.First)
                if (stats.deliveredCount >= 10) add(FriendStamp.Ten)
                if (parcels.map { it.carrier }.toSet().size >= 3) add(FriendStamp.Connected)
                stats.fastestDelivery?.let { if (it.duration <= 172_800) add(FriendStamp.Express) }
                if (stats.crossBorderCount >= 1) add(FriendStamp.AcrossBorders)
                if (stats.originCountries.size >= 5) add(FriendStamp.AroundWorld)
                if (stats.deliveredCount >= 25) add(FriendStamp.TheRegular)
                if (stats.domesticDeliveryCount >= 1) add(FriendStamp.RightNextDoor)
                if (stats.longWaitDeliveryCount >= 1) add(FriendStamp.WorthTheWait)
                if (stats.maxDeliveriesInOneDay >= 3) add(FriendStamp.BusyDoorstep)
                if (stats.pickupDeliveryCount >= 1) add(FriendStamp.PickedUp)
                if (stats.decemberDeliveryCount >= 1) add(FriendStamp.HomeForHolidays)
            }
            // `Calendar(identifier: .iso8601)` in UTC: the week starts on Monday at midnight.
            val week = now.atZone(ZoneOffset.UTC).toLocalDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .atStartOfDay(ZoneOffset.UTC).toInstant()
            val arrived = parcels.any { parcel ->
                if (parcel.currentStage != TrackingStage.Delivered) return@any false
                val date = parcel.trackingEvents.filter { it.stage == TrackingStage.Delivered }.mapNotNull { DateParser.date(it.occurredAt) }.minOrNull()
                    ?: return@any false
                date >= week && date <= now
            }
            return FriendCard(
                id = OWN_CARD_ID,
                nickname = profile.nickname,
                stats = if (profile.shareStats) {
                    FriendStats(stats.deliveredCount, stats.averageDeliveryDuration?.let { max(1, ceil(it / 86_400).toInt()) }, stamps)
                } else {
                    null
                },
                arrivedThisWeek = if (profile.shareArrival) arrived else null,
            )
        }
    }
}

/** `FriendStamp.titleKey`: the stamp's name, the passport's. */
val FriendStamp.titleKey: String
    get() = when (this) {
        FriendStamp.First -> "passport.firstArrival"
        FriendStamp.Ten -> "passport.doubleDigits"
        FriendStamp.Connected -> "passport.wellConnected"
        FriendStamp.Express -> "passport.expressArrival"
        FriendStamp.AcrossBorders -> "passport.acrossBorders"
        FriendStamp.AroundWorld -> "passport.aroundWorld"
        FriendStamp.TheRegular -> "passport.theRegular"
        FriendStamp.RightNextDoor -> "passport.rightNextDoor"
        FriendStamp.WorthTheWait -> "passport.worthTheWait"
        FriendStamp.BusyDoorstep -> "passport.busyDoorstep"
        FriendStamp.PickedUp -> "passport.pickedUp"
        FriendStamp.HomeForHolidays -> "passport.homeForHolidays"
    }

/** `FriendStamp.explanationKey`: how the stamp is earned, the passport's words. */
val FriendStamp.explanationKey: String
    get() = when (this) {
        FriendStamp.First -> "passport.firstExplanation"
        FriendStamp.Ten -> "passport.tenExplanation"
        FriendStamp.Connected -> "passport.carrierExplanation"
        FriendStamp.Express -> "passport.expressExplanation"
        FriendStamp.AcrossBorders -> "passport.acrossExplanation"
        FriendStamp.AroundWorld -> "passport.aroundExplanation"
        FriendStamp.TheRegular -> "passport.regularExplanation"
        FriendStamp.RightNextDoor -> "passport.domesticExplanation"
        FriendStamp.WorthTheWait -> "passport.waitExplanation"
        FriendStamp.BusyDoorstep -> "passport.busyExplanation"
        FriendStamp.PickedUp -> "passport.pickupExplanation"
        FriendStamp.HomeForHolidays -> "passport.holidayExplanation"
    }

/** `FriendStamp.symbol`: the SF Symbol on its seal. */
val FriendStamp.symbol: String
    get() = when (this) {
        FriendStamp.First -> "shippingbox"
        FriendStamp.Ten -> "seal"
        FriendStamp.Connected -> "globe"
        FriendStamp.Express -> "bolt"
        FriendStamp.AcrossBorders -> "globe.europe.africa"
        FriendStamp.AroundWorld -> "map"
        FriendStamp.TheRegular -> "25.circle"
        FriendStamp.RightNextDoor -> "house"
        FriendStamp.WorthTheWait -> "hourglass"
        FriendStamp.BusyDoorstep -> "shippingbox"
        FriendStamp.PickedUp -> "storefront"
        FriendStamp.HomeForHolidays -> "gift"
    }

/**
 * `FriendsActivityStore`: memory-only news of friendships, the notices of invitations accepted ([updates]) and the friend
 * to reveal ([focusId]), each reveal a new [presentationId]. [client] asks for the account's; [signedIn] says whether
 * there is an account (`session.user != nil`); [removeNotifications] takes a friend's notifications off the device as
 * one is acknowledged, which comes with notifications (M6).
 */
class FriendsActivityStore(
    private val client: FriendsClient,
    private val signedIn: () -> Boolean,
    private val removeNotifications: suspend (UUID) -> Unit = {},
) {
    private val updatesState = MutableStateFlow<List<FriendUpdate>>(emptyList())
    private val focusIdState = MutableStateFlow<UUID?>(null)
    private val presentationIdState = MutableStateFlow(UUID.randomUUID())
    val updatesFlow: StateFlow<List<FriendUpdate>> = updatesState.asStateFlow()
    val focusIdFlow: StateFlow<UUID?> = focusIdState.asStateFlow()
    val presentationIdFlow: StateFlow<UUID> = presentationIdState.asStateFlow()

    val updates: List<FriendUpdate> get() = updatesState.value
    val focusId: UUID? get() = focusIdState.value
    val presentationId: UUID get() = presentationIdState.value

    /** The circle an invitation accepted in the foreground answered with, for the tab to show at once. */
    var arrivalSnapshot: FriendsSnapshot? = null
        private set

    private val dismissed = mutableSetOf<UUID>()
    private var generation = 0

    /** `refresh(session:)`: the notices afresh, but those already acknowledged; a failure keeps those shown. */
    suspend fun refresh() {
        if (!signedIn()) {
            clear()
            return
        }
        val current = generation
        try {
            val next = client.activity()
            if (current != generation || !currentCoroutineContext().isActive) return
            updatesState.value = next.updates.filter { it.friendId !in dismissed }
        } catch (error: Exception) {
            // Keep an existing notice on transient network failures.
            currentCoroutineContext().ensureActive()
        }
    }

    /** `reveal(_:snapshot:)`: the friend to show, a new presentation. */
    fun reveal(friendId: UUID, snapshot: FriendsSnapshot? = null) {
        focusIdState.value = friendId
        arrivalSnapshot = snapshot
        presentationIdState.value = UUID.randomUUID()
    }

    /**
     * `acknowledge(_:session:)`: once a friend: their notice goes, then their notifications, and the server hears it,
     * whatever it answers.
     */
    suspend fun acknowledge(friendId: UUID) {
        if (friendId in dismissed) return
        dismissed += friendId
        updatesState.value = updates.filter { it.friendId != friendId }
        removeNotifications(friendId)
        try {
            client.action(FriendsActionRequest(FriendsAction.AcknowledgeFriend, friendId = friendId))
        } catch (error: Exception) {
            currentCoroutineContext().ensureActive()
        }
    }

    fun consumeArrival() {
        arrivalSnapshot = null
    }

    fun consumeFocus(friendId: UUID) {
        if (focusId == friendId) focusIdState.value = null
    }

    /** `hidePrivateContent()`: nothing private shown while the app is away, and no refresh under way taken in. */
    fun hidePrivateContent() {
        generation += 1
        updatesState.value = emptyList()
        arrivalSnapshot = null
    }

    /** `clear()`: as for a new session. */
    fun clear() {
        hidePrivateContent()
        dismissed.clear()
        focusIdState.value = null
        presentationIdState.value = UUID.randomUUID()
    }
}

/**
 * `FriendInvitationStore`: the invitation a link brought ([open]), shown over the app until it is accepted ([finish])
 * or dismissed ([dismiss]). Its code and whether the parcel was opened wait in [defaults] for a week, so a sign-in
 * that leaves the app, or the process gone meanwhile, finds it again; the sender's name is asked afresh each time
 * ([loadPreview]), publicly or, for a link already used, as the account signed in through [friends]. [preview] is
 * `DeliveryAPIClient.invitationPreview(code:)`; [baseUrl] and [linkHosts] say which addresses are the site's. Each
 * [open] is a new [presentationId]; each [finish] counts one more [completed]. Used from the main thread, as the
 * iPhone's is (`@MainActor`).
 */
class FriendInvitationStore(
    private val defaults: UserDefaults,
    private val baseUrl: String,
    private val linkHosts: List<String>,
    private val preview: suspend (String) -> String,
    private val friends: FriendsClient,
    private val now: () -> Instant = Instant::now,
) {
    private val presentingState = MutableStateFlow(false)
    private val presentationIdState = MutableStateFlow(UUID.randomUUID())
    private val codeState = MutableStateFlow<String?>(null)
    private val nicknameState = MutableStateFlow<String?>(null)
    private val errorKeyState = MutableStateFlow<String?>(null)
    private val loadingState = MutableStateFlow(false)
    private val completedState = MutableStateFlow(0)
    private val receiptState = MutableStateFlow<FriendsActionResponse?>(null)
    private val openedState = MutableStateFlow(false)
    val presentingFlow: StateFlow<Boolean> = presentingState.asStateFlow()
    val presentationIdFlow: StateFlow<UUID> = presentationIdState.asStateFlow()
    val codeFlow: StateFlow<String?> = codeState.asStateFlow()
    val nicknameFlow: StateFlow<String?> = nicknameState.asStateFlow()
    val errorKeyFlow: StateFlow<String?> = errorKeyState.asStateFlow()
    val loadingFlow: StateFlow<Boolean> = loadingState.asStateFlow()
    val completedFlow: StateFlow<Int> = completedState.asStateFlow()
    val receiptFlow: StateFlow<FriendsActionResponse?> = receiptState.asStateFlow()
    val openedFlow: StateFlow<Boolean> = openedState.asStateFlow()

    val isPresenting: Boolean get() = presentingState.value
    val presentationId: UUID get() = presentationIdState.value
    val code: String? get() = codeState.value
    val nickname: String? get() = nicknameState.value
    val errorKey: String? get() = errorKeyState.value
    val loading: Boolean get() = loadingState.value
    val completed: Int get() = completedState.value
    val receipt: FriendsActionResponse? get() = receiptState.value

    /** Whether the parcel was opened, past the welcome to the sign-in or the acceptance; kept with the code (`didSet`). */
    var opened: Boolean
        get() = openedState.value
        set(value) {
            openedState.value = value
            persist()
        }

    private var receivedAt = now()
    private var generation = 0

    /** `Pending`, as the iPhone's JSONEncoder writes it: the date as seconds since 2001. */
    @Serializable
    private class Pending(
        val code: String,
        val opened: Boolean,
        @Serializable(with = ReferenceDateSerializer::class) val receivedAt: Instant,
    )

    init {
        val pending = defaults.data(forKey = STORAGE_KEY)?.let { data ->
            try {
                decodeJson(Pending.serializer(), data)
            } catch (_: Exception) {
                null
            }
        }
        val age = pending?.let { now().timeIntervalSinceReferenceDate - it.receivedAt.timeIntervalSinceReferenceDate }
        // A clock gone back before the link came, as one older than a week, forgets it.
        if (pending != null && age != null && FriendInvitationLink.validCode(pending.code) && age >= 0 && age < LIFETIME) {
            codeState.value = pending.code
            openedState.value = pending.opened
            receivedAt = pending.receivedAt
            presentingState.value = true
        } else {
            defaults.removeObject(forKey = STORAGE_KEY)
        }
    }

    /**
     * `open(_:)`: an invitation's link, on the site or in the app's scheme, shown afresh at its welcome; one whose code
     * can't be read shows `friends.inviteUnavailable` and waits nowhere. False for any other address.
     */
    fun open(url: String): Boolean {
        if (!FriendInvitationLink.isInvitation(url, baseUrl, linkHosts)) return false
        clearPreview()
        receiptState.value = null
        codeState.value = FriendInvitationLink.code(url, baseUrl, linkHosts)
        presentationIdState.value = UUID.randomUUID()
        receivedAt = now()
        presentingState.value = true
        opened = false
        if (code == null) {
            errorKeyState.value = "friends.inviteUnavailable"
            defaults.removeObject(forKey = STORAGE_KEY)
        }
        persist()
        return true
    }

    /**
     * `loadPreview(session:)`: the sender's name for the code shown. A link already used has no public preview; only
     * the account signed in ([signedIn]) asks for its name then, without learning yet whether it was used. A failure
     * shows no name and `friends.inviteUnavailable` for a link that leads nowhere, `friends.unavailable` otherwise. An
     * answer overtaken by a newer call or [clearPreview], or called off, changes nothing.
     */
    suspend fun loadPreview(signedIn: Boolean = false) {
        if (!isPresenting || receipt != null) return
        val code = code ?: run {
            errorKeyState.value = "friends.inviteUnavailable"
            return
        }
        generation += 1
        val current = generation
        loadingState.value = true
        errorKeyState.value = null
        try {
            val name = try {
                preview(code)
            } catch (error: DeliveryApiError.Service) {
                if (error.serviceMessage != UNAVAILABLE || !signedIn) throw error
                val answer = friends.action(FriendsActionRequest(FriendsAction.PreviewInvite, code = code))
                val nickname = answer.previewNickname
                if (nickname.isNullOrEmpty()) throw DeliveryApiError.InvalidResponse()
                nickname
            }
            if (current != generation || !currentCoroutineContext().isActive) return
            nicknameState.value = name
        } catch (error: Exception) {
            currentCoroutineContext().ensureActive()
            if (current != generation) return
            nicknameState.value = null
            errorKeyState.value = if ((error as? DeliveryApiError.Service)?.serviceMessage == UNAVAILABLE) {
                "friends.inviteUnavailable"
            } else {
                "friends.unavailable"
            }
        } finally {
            if (current == generation) loadingState.value = false
        }
    }

    /** `clearPreview()`: no name, error or spinner, and what was asked forgotten, as the app leaves the front. */
    fun clearPreview() {
        generation += 1
        nicknameState.value = null
        errorKeyState.value = null
        loadingState.value = false
    }

    /** `receive(_:)`: the acceptance's answer. The invitation was used on the server, so it no longer waits. */
    fun receive(result: FriendsActionResponse) {
        receiptState.value = result
        defaults.removeObject(forKey = STORAGE_KEY)
    }

    /** `dismiss()`: nothing shown, and nothing waiting. */
    fun dismiss() {
        presentingState.value = false
        codeState.value = null
        openedState.value = false
        receiptState.value = null
        clearPreview()
        defaults.removeObject(forKey = STORAGE_KEY)
    }

    /** `finish()`: the acceptance is over; the app goes to its friends ([completed]). */
    fun finish() {
        completedState.value += 1
        dismiss()
    }

    /** Only the waiting code survives a sign-in; who sent it is asked afresh. */
    private fun persist() {
        val code = code
        if (!isPresenting || receipt != null || code == null) return
        val data = try {
            JsonEncoder().encode(Pending.serializer(), Pending(code, opened, receivedAt))
        } catch (_: Exception) {
            return
        }
        defaults.set(data, forKey = STORAGE_KEY)
    }

    companion object {
        const val STORAGE_KEY = "sdt.pendingFriendInvitation.v1"

        /** A week, in seconds. */
        private const val LIFETIME = 604_800.0

        private const val UNAVAILABLE = "Invitation unavailable"
    }
}
