package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.FriendsActivity
import com.plhery.peekdeliverytracker.core.api.FriendsSnapshot
import java.io.IOException
import java.time.Duration
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * FriendsStore.swift's `FriendInvitationStore` on made-up codes and names (AppRoutingTests' two invitation tests and
 * more): the links it opens, the code waiting a week through a sign-in or the process gone, the sender's name asked
 * publicly then as the account for a used link, its error keys, answers overtaken changing nothing, and the
 * acceptance's receipt, dismissal and finish.
 */
class FriendInvitationStoreTests {
    private val base = "https://peek.example"
    private val hosts = listOf("go.peek.example")
    private val previewId = "Ab7kP2mQ9xR4tY6n" // gitleaks:allow -- synthetic invitation ID used only in tests
    private val legacy = "b".repeat(32)
    private var clock = Instant.parse("2026-10-28T12:00:00Z")
    private val defaults = MemoryDefaults()

    /** The public preview: a name, a failure, or held until `holding` lets it go when set. */
    private var previews = mutableListOf<String>()
    private var previewName = "Robin"
    private var previewFailure: Exception? = null
    private var holding: CompletableDeferred<Unit>? = null
    private val client = Client()

    /** The account's friends: what a used link's preview asks. */
    private inner class Client : FriendsClient {
        var answer = FriendsActionResponse(previewNickname = "Robin")
        var failure: Exception? = null
        val actions = mutableListOf<FriendsActionRequest>()

        override suspend fun snapshot(): FriendsSnapshot = throw IOException()

        override suspend fun action(request: FriendsActionRequest): FriendsActionResponse {
            actions += request
            failure?.let { throw it }
            return answer
        }

        override suspend fun activity(): FriendsActivity = throw IOException()
    }

    private fun store() = FriendInvitationStore(
        defaults, base, hosts,
        preview = { code ->
            previews += code
            holding?.await()
            previewFailure?.let { throw it }
            previewName
        },
        friends = client,
        now = { clock },
    )

    private val unavailable get() = DeliveryApiError.Service("Invitation unavailable")

    @Test
    fun eachKindOfInvitationLinkOpensAtItsWelcome() {
        val store = store()
        assertFalse(store.isPresenting)
        for ((url, code) in listOf(
            "$base/i/$previewId" to previewId,
            "https://go.peek.example/i/$previewId" to previewId,
            "$base/invite#$legacy" to legacy,
            "peekdeliverytracker://invite#$previewId" to previewId,
        )) {
            val before = store.presentationId
            store.opened = true
            assertTrue(url, store.open(url))
            assertTrue(store.isPresenting)
            assertEquals(code, store.code)
            assertFalse(store.opened)
            assertNull(store.errorKey)
            assertNotEquals(before, store.presentationId)
        }
    }

    @Test
    fun otherAddressesAreNoInvitation() {
        val store = store()
        for (url in listOf(
            "$base/p/$previewId", "https://evil.example/i/$previewId", OAuthFlow.callbackUrl,
            "peekdeliverytracker://friend/${UUID.randomUUID()}", "not a link",
        )) {
            assertFalse(url, store.open(url))
        }
        assertFalse(store.isPresenting)
        assertFalse(defaults.hasObject(FriendInvitationStore.STORAGE_KEY))
    }

    @Test
    fun anUnreadableCodeShowsUnavailableAndWaitsNowhere() = runTest {
        val store = store()
        store.open("$base/i/$previewId")
        assertTrue(defaults.hasObject(FriendInvitationStore.STORAGE_KEY))
        assertTrue(store.open("peekdeliverytracker://invite#invalid"))
        assertTrue(store.isPresenting)
        assertNull(store.code)
        assertEquals("friends.inviteUnavailable", store.errorKey)
        assertFalse(defaults.hasObject(FriendInvitationStore.STORAGE_KEY))
        store.clearPreview()
        store.loadPreview()
        assertEquals("friends.inviteUnavailable", store.errorKey)
        assertTrue(previews.isEmpty())
        assertFalse(store().isPresenting)
    }

    @Test
    fun theCodeWaitsThroughASignInButNotADismissal() {
        val store = store()
        store.open("$base/i/$previewId")
        store.opened = true
        val restored = store()
        assertTrue(restored.isPresenting)
        assertTrue(restored.opened)
        assertEquals(previewId, restored.code)
        assertNull(restored.nickname)
        // The sign-in's callback is no invitation, so it leaves the waiting one alone.
        assertFalse(restored.open(OAuthFlow.callbackUrl))
        assertEquals(previewId, restored.code)
        restored.dismiss()
        assertFalse(restored.isPresenting)
        assertNull(restored.code)
        assertFalse(restored.opened)
        assertFalse(store().isPresenting)
    }

    @Test
    fun theCodeIsWrittenAsTheIphoneWritesIt() {
        store().open("peekdeliverytracker://invite#$legacy")
        val written = Json.parseToJsonElement(String(defaults.data(FriendInvitationStore.STORAGE_KEY)!!)).jsonObject
        assertEquals(listOf("code", "opened", "receivedAt"), written.keys.toList())
        assertEquals(legacy, written.getValue("code").jsonPrimitive.content)
        assertEquals(false, written.getValue("opened").jsonPrimitive.boolean)
        assertEquals(clock.timeIntervalSinceReferenceDate, written.getValue("receivedAt").jsonPrimitive.double, 0.0)
        // One the iPhone wrote reads back.
        defaults.set("""{"code":"$previewId","opened":true,"receivedAt":${clock.timeIntervalSinceReferenceDate - 60}}""".toByteArray(), forKey = FriendInvitationStore.STORAGE_KEY)
        val restored = store()
        assertTrue(restored.isPresenting)
        assertTrue(restored.opened)
        assertEquals(previewId, restored.code)
    }

    @Test
    fun theCodeWaitsAWeekAndNotFromTheFuture() {
        val written = { store().open("$base/i/$previewId") }
        written()
        val receivedAt = clock
        clock = receivedAt.plus(Duration.ofSeconds(604_799))
        assertTrue(store().isPresenting)
        clock = receivedAt.plus(Duration.ofDays(7))
        assertFalse(store().isPresenting)
        assertFalse(defaults.hasObject(FriendInvitationStore.STORAGE_KEY))

        clock = receivedAt
        written()
        // The clock gone back before the link came.
        clock = receivedAt.minusSeconds(1)
        assertFalse(store().isPresenting)
        assertFalse(defaults.hasObject(FriendInvitationStore.STORAGE_KEY))
    }

    @Test
    fun aMalformedOrInvalidWaitingCodeIsForgotten() {
        defaults.set("not json".toByteArray(), forKey = FriendInvitationStore.STORAGE_KEY)
        assertFalse(store().isPresenting)
        assertFalse(defaults.hasObject(FriendInvitationStore.STORAGE_KEY))
        defaults.set("""{"code":"short","opened":false,"receivedAt":${clock.timeIntervalSinceReferenceDate}}""".toByteArray(), forKey = FriendInvitationStore.STORAGE_KEY)
        assertFalse(store().isPresenting)
        assertFalse(defaults.hasObject(FriendInvitationStore.STORAGE_KEY))
    }

    @Test
    fun aNewLinkReplacesTheWaitingOneAndStartsItsOwnWeek() {
        val store = store()
        store.open("$base/i/$previewId")
        store.opened = true
        clock = clock.plus(Duration.ofDays(6))
        store.open("peekdeliverytracker://invite#$legacy")
        clock = clock.plus(Duration.ofDays(6))
        val restored = store()
        assertTrue(restored.isPresenting)
        assertEquals(legacy, restored.code)
        assertFalse(restored.opened)
    }

    @Test
    fun thePreviewShowsTheSendersName() = runTest {
        val store = store()
        store.loadPreview()
        assertTrue("Nothing is presented", previews.isEmpty())
        store.open("$base/i/$previewId")
        val gate = CompletableDeferred<Unit>()
        holding = gate
        launch { store.loadPreview() }
        runCurrent()
        assertTrue(store.loading)
        assertNull(store.errorKey)
        gate.complete(Unit)
        runCurrent()
        assertFalse(store.loading)
        assertEquals("Robin", store.nickname)
        assertEquals(listOf(previewId), previews)
        assertTrue(client.actions.isEmpty())
    }

    @Test
    fun aUsedLinksNameComesFromTheAccountOnlyWhenSignedIn() = runTest {
        val store = store()
        store.open("$base/i/$previewId")
        previewFailure = unavailable
        store.loadPreview(signedIn = false)
        assertNull(store.nickname)
        assertEquals("friends.inviteUnavailable", store.errorKey)
        assertTrue(client.actions.isEmpty())

        client.answer = FriendsActionResponse(previewNickname = "Sam")
        store.loadPreview(signedIn = true)
        assertEquals("Sam", store.nickname)
        assertNull(store.errorKey)
        assertEquals(listOf(FriendsActionRequest(FriendsAction.PreviewInvite, code = previewId)), client.actions)

        // No name in the answer reads as a broken one.
        client.answer = FriendsActionResponse(previewNickname = "")
        store.loadPreview(signedIn = true)
        assertNull(store.nickname)
        assertEquals("friends.unavailable", store.errorKey)

        client.failure = unavailable
        store.loadPreview(signedIn = true)
        assertEquals("friends.inviteUnavailable", store.errorKey)
    }

    @Test
    fun anyOtherFailureIsUnavailable() = runTest {
        val store = store()
        store.open("$base/i/$previewId")
        for (failure in listOf(IOException(), DeliveryApiError.ServiceFailed(500), DeliveryApiError.InvalidResponse(), DeliveryApiError.Service("Other"))) {
            previewFailure = failure
            store.loadPreview(signedIn = true)
            assertNull(store.nickname)
            assertEquals("friends.unavailable", store.errorKey)
            assertFalse(store.loading)
        }
        assertTrue("Only an unavailable link asks the account", client.actions.isEmpty())
    }

    @Test
    fun anAnswerOvertakenOrClearedChangesNothing() = runTest {
        val store = store()
        store.open("$base/i/$previewId")
        val first = CompletableDeferred<Unit>()
        holding = first
        launch { store.loadPreview() }
        runCurrent()
        store.clearPreview()
        assertFalse(store.loading)
        first.complete(Unit)
        runCurrent()
        assertNull(store.nickname)
        assertFalse(store.loading)

        val late = CompletableDeferred<Unit>()
        holding = late
        val slow = launch { store.loadPreview() }
        runCurrent()
        holding = null
        previewName = "Sam"
        store.loadPreview()
        assertEquals("Sam", store.nickname)
        previewName = "Robin"
        late.complete(Unit)
        slow.join()
        assertEquals("Sam", store.nickname)
        assertFalse(store.loading)

        // One called off changes nothing either, but its spinner goes.
        val held = CompletableDeferred<Unit>()
        holding = held
        val called = launch { store.loadPreview() }
        runCurrent()
        called.cancel()
        runCurrent()
        assertFalse(store.loading)
        assertEquals("Sam", store.nickname)
    }

    @Test
    fun aReceivedFriendshipNeverBringsTheUsedLinkBack() = runTest {
        val store = store()
        store.open("peekdeliverytracker://invite#$legacy")
        store.opened = true
        val friend = FriendCard(UUID.fromString("11000000-0000-4000-8000-000000000001"), "Robin")
        store.receive(FriendsActionResponse(acceptedFriend = friend))
        assertTrue(store.isPresenting)
        assertEquals(friend.id, store.receipt?.acceptedFriend?.id)
        assertFalse(store().isPresenting)
        // Nor does going back to the welcome, and no preview is asked.
        store.opened = false
        assertFalse(defaults.hasObject(FriendInvitationStore.STORAGE_KEY))
        store.loadPreview()
        assertTrue(previews.isEmpty())
        store.finish()
        assertEquals(1, store.completed)
        assertNull(store.receipt)
        assertFalse(store.isPresenting)
        assertNull(store.code)
    }

    @Test
    fun aDismissalForgetsThePreviewAndCountsNoCompletion() = runTest {
        val store = store()
        store.open("$base/i/$previewId")
        store.loadPreview()
        assertEquals("Robin", store.nickname)
        store.dismiss()
        assertNull(store.nickname)
        assertNull(store.errorKey)
        assertEquals(0, store.completed)
        // A new link is presented afresh.
        store.open("$base/i/$previewId")
        assertNull(store.nickname)
        assertTrue(store().isPresenting)
    }
}
