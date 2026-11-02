package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendProfile
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendUpdate
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.FriendsActivity
import com.plhery.peekdeliverytracker.core.api.FriendsSnapshot
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.io.IOException
import java.time.Duration
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * FriendsStore.swift on made-up parcels and friends: the card the demo shows friends (its stamps, the average rounded
 * up, this ISO week's arrival in UTC, only what is shared), the copy keys of the service's refusals, the demo's actions,
 * answers overtaken or cleared changing nothing, and the activity's notices, reveals and acknowledgements.
 */
class FriendsStoreTests {
    /** A client a test answers: snapshots and actions as given, each held until `holding` lets it go when set. */
    private class FakeClient : FriendsClient {
        val snapshots = ArrayDeque<FriendsSnapshot>()
        var holding: CompletableDeferred<Unit>? = null
        var failure: Exception? = null
        var answer = FriendsActionResponse()
        var activity = FriendsActivity(emptyList())
        val actions = mutableListOf<FriendsActionRequest>()

        override suspend fun snapshot(): FriendsSnapshot {
            val held = holding
            val next = snapshots.removeFirst()
            held?.await()
            failure?.let { throw it }
            return next
        }

        override suspend fun action(request: FriendsActionRequest): FriendsActionResponse {
            actions += request
            holding?.await()
            failure?.let { throw it }
            return answer
        }

        override suspend fun activity(): FriendsActivity {
            holding?.await()
            failure?.let { throw it }
            return activity
        }
    }

    private val now = Instant.parse("2026-10-28T12:00:00Z")
    private val mila = FriendCard(UUID.fromString("11000000-0000-4000-8000-000000000001"), "Mila")
    private val noe = FriendCard(UUID.fromString("22000000-0000-4000-8000-000000000002"), "Noé")

    /** A parcel first scanned at [accepted] and delivered at [delivered] by [carrier]. */
    private fun delivered(accepted: String, delivered: String, carrier: CarrierId = CarrierId.Ups): Parcel {
        val id = UUID.randomUUID()
        return makeParcel(id, events = listOf(event(id, TrackingStage.Accepted, accepted), event(id, TrackingStage.Delivered, delivered)))
            .copy(carrier = carrier)
    }

    /** [count] parcels each delivered a day after its first scan, a week apart, before this week. */
    private fun deliveredDaily(count: Int): List<Parcel> = (0 until count).map { index ->
        val at = Instant.parse("2026-01-05T10:00:00Z").plus(Duration.ofDays(7L * index))
        delivered(at.minus(Duration.ofDays(1)).toString(), at.toString())
    }

    @Test
    fun theOwnCardEarnsTheStampsInTheirOrder() {
        val profile = FriendProfile("Alex", shareStats = true, shareArrival = false)
        assertEquals(emptyList<FriendStamp>(), FriendsStore.ownCard(emptyList(), profile, now).stats?.stamps)
        assertEquals(listOf(FriendStamp.First, FriendStamp.Express), FriendsStore.ownCard(deliveredDaily(1), profile, now).stats?.stamps)
        assertEquals(listOf(FriendStamp.First, FriendStamp.Ten, FriendStamp.Express), FriendsStore.ownCard(deliveredDaily(10), profile, now).stats?.stamps)
        val carriers = listOf(CarrierId.Ups, CarrierId.Dpd, CarrierId.SwissPost).mapIndexed { index, carrier ->
            delivered("2026-0${index + 3}-01T10:00:00Z", "2026-0${index + 3}-05T10:00:00Z", carrier)
        }
        assertEquals(listOf(FriendStamp.First, FriendStamp.Connected), FriendsStore.ownCard(carriers, profile, now).stats?.stamps)
        assertEquals(3, FriendsStore.ownCard(carriers, profile, now).stats?.deliveredCount)
    }

    @Test
    fun theAverageRoundsUpToWholeDaysAndOneAtLeast() {
        val profile = FriendProfile("Alex", shareStats = true, shareArrival = false)
        val quick = delivered("2026-03-01T10:00:00Z", "2026-03-01T16:00:00Z")
        assertEquals(1, FriendsStore.ownCard(listOf(quick), profile, now).stats?.averageDays)
        val dayAndAnHour = delivered("2026-03-01T10:00:00Z", "2026-03-02T11:00:00Z")
        assertEquals(2, FriendsStore.ownCard(listOf(dayAndAnHour), profile, now).stats?.averageDays)
        val threeDays = delivered("2026-03-01T10:00:00Z", "2026-03-04T10:00:00Z")
        assertEquals(3, FriendsStore.ownCard(listOf(threeDays), profile, now).stats?.averageDays)
        assertNull(FriendsStore.ownCard(emptyList(), profile, now).stats?.averageDays)
    }

    @Test
    fun anArrivalCountsFromMondayMidnightInUtcUntilNow() {
        val profile = FriendProfile("Alex", shareStats = false, shareArrival = true)
        // Monday 00:30 UTC, this week; Sunday 23:30 UTC, last week, though Monday already in Zurich.
        assertEquals(true, FriendsStore.ownCard(listOf(delivered("2026-10-23T10:00:00Z", "2026-10-26T00:30:00Z")), profile, now).arrivedThisWeek)
        assertEquals(false, FriendsStore.ownCard(listOf(delivered("2026-10-23T10:00:00Z", "2026-10-25T23:30:00Z")), profile, now).arrivedThisWeek)
        // Delivered after now, as a wrong clock may say, and not delivered yet.
        assertEquals(false, FriendsStore.ownCard(listOf(delivered("2026-10-23T10:00:00Z", "2026-10-28T13:00:00Z")), profile, now).arrivedThisWeek)
        val id = UUID.randomUUID()
        val onItsWay = makeParcel(id, events = listOf(event(id, TrackingStage.InTransit, "2026-10-27T10:00:00Z")))
        assertEquals(false, FriendsStore.ownCard(listOf(onItsWay), profile, now).arrivedThisWeek)
    }

    @Test
    fun theOwnCardSharesOnlyWhatTheProfileShares() {
        val parcels = listOf(delivered("2026-10-23T10:00:00Z", "2026-10-27T10:00:00Z"))
        val hidden = FriendsStore.ownCard(parcels, FriendProfile("Alex", shareStats = false, shareArrival = false), now)
        assertNull(hidden.stats)
        assertNull(hidden.arrivedThisWeek)
        assertEquals("Alex", hidden.nickname)
        assertEquals(FriendsStore.OWN_CARD_ID, hidden.id)
        val shown = FriendsStore.ownCard(parcels, FriendProfile("Alex", shareStats = true, shareArrival = true), now)
        assertEquals(1, shown.stats?.deliveredCount)
        assertEquals(true, shown.arrivedThisWeek)
    }

    @Test
    fun theServicesRefusalsHaveTheirOwnWords() {
        assertEquals("friends.inviteUnavailable", FriendsStore.errorKey(DeliveryApiError.Service("Invitation unavailable")))
        assertEquals("friends.selfInvitation", FriendsStore.errorKey(DeliveryApiError.Service("Cannot accept your own invitation")))
        assertEquals("friends.circleFull", FriendsStore.errorKey(DeliveryApiError.Service("Your circle is full")))
        assertEquals("friends.actionFailed", FriendsStore.errorKey(DeliveryApiError.Service("Something else")))
        assertEquals("friends.actionFailed", FriendsStore.errorKey(IOException("offline")))
        assertEquals("friends.actionFailed", FriendsStore.errorKey(DeliveryApiError.ServiceFailed(500)))
    }

    @Test
    fun theDemoLoadsItsCircleWithItsOwnCard() = runTest {
        val client = FakeClient()
        val store = FriendsStore(client)
        store.configure(demo = true)
        store.load(deliveredDaily(2))
        val snapshot = store.snapshot!!
        assertEquals(listOf("Mila", "Noé", "Sam"), snapshot.friends.map { it.nickname })
        assertEquals("Alex", snapshot.ownCard?.nickname)
        assertEquals(2, snapshot.ownCard?.stats?.deliveredCount)
        assertNull(store.errorKey)
        assertTrue(client.actions.isEmpty())
    }

    @Test
    fun theDemosActionsChangeItsCopyInMemory() = runTest {
        val client = FakeClient()
        val store = FriendsStore(client)
        store.configure(demo = true)
        store.load(emptyList())
        val saved = store.act(FriendsActionRequest(FriendsAction.SaveProfile, nickname = "  Robin \n", shareStats = false, shareArrival = true), emptyList())
        assertEquals(FriendProfile("Robin", shareStats = false, shareArrival = true), saved?.snapshot?.profile)
        assertEquals("Robin", store.snapshot?.ownCard?.nickname)
        assertNull(store.snapshot?.ownCard?.stats)
        // Without all three values it saves nothing, and says nothing.
        assertNull(store.act(FriendsActionRequest(FriendsAction.SaveProfile, nickname = "Kim"), emptyList()))
        assertNull(store.errorKey)
        assertEquals("Robin", store.snapshot?.profile?.nickname)

        val milaId = store.snapshot!!.friends.first().id
        store.act(FriendsActionRequest(FriendsAction.RemoveFriend, friendId = milaId), emptyList())
        assertEquals(listOf("Noé", "Sam"), store.snapshot?.friends?.map { it.nickname })

        assertNull(store.act(FriendsActionRequest(FriendsAction.CreateInvite), emptyList()))
        assertEquals("friends.demoInvites", store.errorKey)
        assertNull(store.act(FriendsActionRequest(FriendsAction.RevokeInvite, code = "abc"), emptyList()))
        assertEquals("friends.demoInvites", store.errorKey)

        store.act(FriendsActionRequest(FriendsAction.Disable), emptyList())
        assertNull(store.snapshot?.profile)
        assertTrue(store.snapshot!!.friends.isEmpty())
        assertNull(store.snapshot?.ownCard)
        assertFalse(store.working)
        // The demo keeps its changes until it is left; back in it, it starts again.
        store.load(emptyList())
        assertTrue(store.snapshot!!.friends.isEmpty())
        store.configure(demo = false)
        store.configure(demo = true)
        store.load(emptyList())
        assertEquals(3, store.snapshot?.friends?.size)
        assertTrue(client.actions.isEmpty())
    }

    @Test
    fun theAccountsCircleComesFromTheServiceAndAFailureShowsNone() = runTest {
        val client = FakeClient()
        val store = FriendsStore(client)
        store.configure(demo = false)
        client.snapshots += FriendsSnapshot(profile = FriendProfile("Kim", true, false), friends = listOf(mila))
        store.load(emptyList())
        assertEquals(listOf(mila), store.snapshot?.friends)
        client.failure = IOException("offline")
        client.snapshots += FriendsSnapshot(friends = emptyList())
        store.load(emptyList())
        assertNull(store.snapshot)
        assertEquals("friends.unavailable", store.errorKey)
        client.failure = null
        client.snapshots += FriendsSnapshot(friends = listOf(noe))
        store.load(emptyList())
        assertEquals(listOf(noe), store.snapshot?.friends)
        assertNull(store.errorKey)
    }

    @Test
    fun anActionTakesInItsSnapshotAndAFailureItsWords() = runTest {
        val client = FakeClient()
        val store = FriendsStore(client)
        store.configure(demo = false)
        client.answer = FriendsActionResponse(snapshot = FriendsSnapshot(friends = listOf(noe)), inviteCode = "code")
        var committed: FriendsActionResponse? = null
        val result = store.act(FriendsActionRequest(FriendsAction.CreateInvite), emptyList()) { committed = it }
        assertEquals("code", result?.inviteCode)
        assertEquals(result, committed)
        assertEquals(listOf(noe), store.snapshot?.friends)
        client.failure = DeliveryApiError.Service("Your circle is full")
        assertNull(store.act(FriendsActionRequest(FriendsAction.CreateInvite), emptyList()))
        assertEquals("friends.circleFull", store.errorKey)
        assertEquals(listOf(noe), store.snapshot?.friends)
        assertFalse(store.working)
    }

    @Test
    fun oneActionAtATimeAndNoLoadDuringIt() = runTest {
        val client = FakeClient()
        val store = FriendsStore(client)
        store.configure(demo = false)
        val held = CompletableDeferred<Unit>()
        client.holding = held
        val first = launch { store.act(FriendsActionRequest(FriendsAction.CreateInvite), emptyList()) }
        runCurrent()
        assertTrue(store.working)
        assertNull(store.act(FriendsActionRequest(FriendsAction.Disable), emptyList()))
        store.load(emptyList())
        assertEquals(1, client.actions.size)
        held.complete(Unit)
        first.join()
        assertFalse(store.working)
    }

    @Test
    fun aLateAnswerChangesNothing() = runTest {
        val client = FakeClient()
        val store = FriendsStore(client)
        store.configure(demo = false)
        val held = CompletableDeferred<Unit>()
        client.holding = held
        client.snapshots += FriendsSnapshot(friends = listOf(mila))
        val late = launch { store.load(emptyList()) }
        runCurrent()
        // A newer load answers first; the older one's answer is dropped.
        client.holding = null
        client.snapshots += FriendsSnapshot(friends = listOf(noe))
        store.load(emptyList())
        held.complete(Unit)
        late.join()
        assertEquals(listOf(noe), store.snapshot?.friends)

        // Cleared while a load waits: it shows nothing once it answers.
        val again = CompletableDeferred<Unit>()
        client.holding = again
        client.snapshots += FriendsSnapshot(friends = listOf(mila))
        val cleared = launch { store.load(emptyList()) }
        runCurrent()
        store.clear()
        again.complete(Unit)
        cleared.join()
        assertNull(store.snapshot)
        assertNull(store.errorKey)

        // A load called off with its tab changes nothing either.
        val off = CompletableDeferred<Unit>()
        client.holding = off
        client.snapshots += FriendsSnapshot(friends = listOf(mila))
        val cancelled = launch { store.load(emptyList()) }
        runCurrent()
        cancelled.cancel()
        off.complete(Unit)
        cancelled.join()
        assertNull(store.snapshot)
    }

    @Test
    fun aSeedShowsBeforeTheFirstLoad() {
        val store = FriendsStore(FakeClient())
        store.seed(FriendsSnapshot(friends = listOf(mila)))
        assertEquals(listOf(mila), store.snapshot?.friends)
    }

    @Test
    fun theActivityLeavesOutWhatWasAcknowledged() = runTest {
        val client = FakeClient()
        var signedIn = true
        val store = FriendsActivityStore(client, signedIn = { signedIn })
        client.activity = FriendsActivity(listOf(FriendUpdate(mila.id, "Mila"), FriendUpdate(noe.id, "Noé")))
        store.refresh()
        assertEquals(listOf(mila.id, noe.id), store.updates.map { it.friendId })
        store.acknowledge(mila.id)
        assertEquals(listOf(noe.id), store.updates.map { it.friendId })
        assertEquals(listOf(FriendsActionRequest(FriendsAction.AcknowledgeFriend, friendId = mila.id)), client.actions)
        // Once: a second acknowledgement asks nothing, and a refresh keeps the friend out.
        store.acknowledge(mila.id)
        assertEquals(1, client.actions.size)
        store.refresh()
        assertEquals(listOf(noe.id), store.updates.map { it.friendId })
        // A failure keeps what is shown.
        client.failure = IOException("offline")
        store.refresh()
        assertEquals(listOf(noe.id), store.updates.map { it.friendId })
        // An acknowledgement the server refuses still holds here.
        store.acknowledge(noe.id)
        assertTrue(store.updates.isEmpty())
        client.failure = null
        // Signed out, it is all forgotten, the acknowledgements too.
        signedIn = false
        store.refresh()
        signedIn = true
        store.refresh()
        assertEquals(listOf(mila.id, noe.id), store.updates.map { it.friendId })
    }

    @Test
    fun aRevealIsANewPresentationOfItsFriend() {
        val store = FriendsActivityStore(FakeClient(), signedIn = { true })
        val before = store.presentationId
        val seed = FriendsSnapshot(friends = listOf(mila))
        store.reveal(mila.id, seed)
        assertEquals(mila.id, store.focusId)
        assertEquals(seed, store.arrivalSnapshot)
        assertNotEquals(before, store.presentationId)
        val first = store.presentationId
        store.reveal(mila.id)
        assertNotEquals(first, store.presentationId)
        assertNull(store.arrivalSnapshot)
        store.reveal(noe.id, seed)
        store.consumeArrival()
        assertNull(store.arrivalSnapshot)
        // Only the friend focused is let go.
        store.consumeFocus(mila.id)
        assertEquals(noe.id, store.focusId)
        store.consumeFocus(noe.id)
        assertNull(store.focusId)
    }

    @Test
    fun hidingAndClearingForgetWhatIsPrivate() = runTest {
        val client = FakeClient()
        val removed = mutableListOf<UUID>()
        val store = FriendsActivityStore(client, signedIn = { true }, removeNotifications = { removed += it })
        client.activity = FriendsActivity(listOf(FriendUpdate(mila.id, "Mila")))
        store.refresh()
        store.reveal(mila.id, FriendsSnapshot(friends = listOf(mila)))
        // Hidden while a refresh waits: its answer isn't taken in, and the focus stays.
        val held = CompletableDeferred<Unit>()
        client.holding = held
        val waiting = launch { store.refresh() }
        runCurrent()
        store.hidePrivateContent()
        held.complete(Unit)
        waiting.join()
        assertTrue(store.updates.isEmpty())
        assertNull(store.arrivalSnapshot)
        assertEquals(mila.id, store.focusId)
        client.holding = null
        store.acknowledge(mila.id)
        assertEquals(listOf(mila.id), removed)
        val presentation = store.presentationId
        store.clear()
        assertNull(store.focusId)
        assertNotEquals(presentation, store.presentationId)
        store.refresh()
        assertEquals(listOf(mila.id), store.updates.map { it.friendId })
    }
}
