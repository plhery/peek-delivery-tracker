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

// FriendsStore.swift's `FriendsStore`, `FriendStamp`'s keys and symbols and `FriendsActivityStore`. The invitation
// received (`FriendInvitationStore`) comes with its step. A Swift `Task` cancelled is a coroutine cancelled here: what
// it was waiting for then changes nothing.

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
     * `configure(session:)`: the demo's circle is FriendsDemo.json, read once. Leaving the demo forgets what its actions
     * changed, as the iPhone's tab makes its store anew on the way back through the first screen.
     */
    fun configure(demo: Boolean) {
        this.demo = demo
        if (!demo) {
            local = null
        } else if (local == null) {
            local = runCatching { ApiJson.decodeFromString(FriendsSnapshot.serializer(), SharedResources.text(DEMO_RESOURCE)) }.getOrNull()
        }
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
