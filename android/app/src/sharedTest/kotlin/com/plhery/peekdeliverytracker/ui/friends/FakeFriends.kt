package com.plhery.peekdeliverytracker.ui.friends

import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.FriendsClient
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendProfile
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.FriendsActivity
import com.plhery.peekdeliverytracker.core.api.FriendsSnapshot
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred

/**
 * The account's friends as a test sets them: the circle each load answers ([snapshot], or [failure]), held until
 * [loading] lets it go when a test holds it; each action answered by [answer] (by default a new invitation, or the
 * circle as it was), held by [acting], or failing with [actionFailure]; and what was asked, in order ([requests]).
 * Nothing leaves the test.
 */
class FakeFriendsClient : FriendsClient {
    var snapshot: FriendsSnapshot = FriendFixtures.circle()
    var failure: Exception? = null
    var loading: CompletableDeferred<Unit>? = null
    var loads = 0
        private set

    var answer: (FriendsActionRequest) -> FriendsActionResponse = { request ->
        when (request.action) {
            FriendsAction.CreateInvite -> FriendsActionResponse(
                inviteCode = "code-${invitations + 1}", previewId = FriendFixtures.previewId(invitations + 1), previousInviteCount = previous,
            ).also { invitations++ }
            else -> FriendsActionResponse(snapshot = snapshot)
        }
    }
    var acting: CompletableDeferred<Unit>? = null
    var actionFailure: Exception? = null
    val requests = mutableListOf<FriendsActionRequest>()

    /** How many links the account had out before each new one, which the invitation offers to cancel. */
    var previous = 0
    private var invitations = 0

    var activity = FriendsActivity(updates = emptyList())

    override suspend fun snapshot(): FriendsSnapshot {
        loads++
        loading?.await()
        failure?.let { throw it }
        return snapshot
    }

    override suspend fun action(request: FriendsActionRequest): FriendsActionResponse {
        requests += request
        acting?.await()
        actionFailure?.let { throw it }
        return answer(request)
    }

    override suspend fun activity(): FriendsActivity = activity
}

/** Made-up friends: names, ids and counts that belong to no one. */
object FriendFixtures {
    /** First bytes 0x10 to 0x13: one of each colour, transit, lilac, pickup and delivered. */
    val ada = FriendCard(UUID.fromString("10000000-0000-4000-8000-000000000010"), "Ada", FriendStats(8, 2, listOf(FriendStamp.First, FriendStamp.Express)), arrivedThisWeek = true)
    val bo = FriendCard(UUID.fromString("11000000-0000-4000-8000-000000000011"), "Bo", FriendStats(3, 1, listOf(FriendStamp.First)))
    val cy = FriendCard(UUID.fromString("12000000-0000-4000-8000-000000000012"), "Cy", stats = null)
    val di = FriendCard(UUID.fromString("13000000-0000-4000-8000-000000000013"), "Di", FriendStats(0, null, emptyList()), arrivedThisWeek = false)

    val profile = FriendProfile("Robin", shareStats = true, shareArrival = false)

    fun circle(friends: List<FriendCard> = listOf(ada, bo, cy, di), profile: FriendProfile? = this.profile) =
        FriendsSnapshot(profile = profile, friends = friends)

    /** A short id as the service gives one, 16 characters. */
    fun previewId(index: Int) = "preview${index.toString().padStart(9, '0')}"

    fun unavailable() = DeliveryApiError.InvalidResponse()
}
