package com.plhery.peekdeliverytracker.core

import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PlayIntegrityTokensTests {
    /** Play Integrity's stand-in: each call takes the next answer, a token or an error code. */
    private class Api(vararg answers: Any) : StandardIntegrity {
        val answers = ArrayDeque(answers.toList())
        val calls = mutableListOf<String>()
        var hang: CompletableDeferred<Unit>? = null

        override suspend fun prepare(projectNumber: Long) {
            calls += "prepare $projectNumber"
            answer()
        }

        override suspend fun request(requestHash: String): String {
            calls += "request $requestHash"
            hang?.await()
            return answer() as String
        }

        private fun answer(): Any? = when (val next = answers.removeFirstOrNull()) {
            is Int -> throw PlayIntegrityError(next)
            else -> next
        }
    }

    private fun TestScope.tokens(api: Api, reported: MutableList<Int> = mutableListOf()) =
        PlayIntegrityTokens(api, report = { reported += it }, now = { currentTime })

    @Test
    fun aProviderReadiedAtLaunchGivesTheRegistrationsToken() = runTest {
        val api = Api(Unit, "synthetic-token")
        val tokens = tokens(api)
        assertTrue(tokens.prepare(123))
        assertEquals("synthetic-token", tokens.token(123, "hash"))
        assertEquals(listOf("prepare 123", "request hash"), api.calls)
    }

    @Test
    fun aTokenAskedWithoutAProviderReadiesOneFirst() = runTest {
        val api = Api(Unit, "synthetic-token")
        assertEquals("synthetic-token", tokens(api).token(123, "hash"))
        assertEquals(listOf("prepare 123", "request hash"), api.calls)
    }

    @Test
    fun transientFailuresAreAskedAgainAfter5ThenAfter10Seconds() = runTest {
        val api = Api(Unit, -3, -100, "synthetic-token")
        val tokens = tokens(api)
        assertEquals("synthetic-token", tokens.token(123, "hash"))
        assertEquals(15_000L, currentTime)
        assertEquals(3, api.calls.count { it == "request hash" })
    }

    @Test
    fun aThirdTransientFailureRegistersWithoutAToken() = runTest {
        val api = Api(Unit, -12, -9, -18)
        val tokens = tokens(api)
        assertNull(tokens.token(123, "hash"))
        // Not a phone without Play: the next registration asks again.
        api.answers += "synthetic-token"
        assertEquals("synthetic-token", tokens.token(123, "hash"))
    }

    @Test
    fun anInvalidProviderIsReplacedOnceThenAskedOnce() = runTest {
        val api = Api(Unit, -19, Unit, "synthetic-token")
        assertEquals("synthetic-token", tokens(api).token(123, "hash"))
        assertEquals(listOf("prepare 123", "request hash", "prepare 123", "request hash"), api.calls)

        val again = Api(Unit, -19, Unit, -19)
        assertNull(tokens(again).token(123, "hash"))
        assertEquals(4, again.calls.size)
    }

    @Test
    fun aPhoneWithoutPlayIsNotAskedAgainInThisProcess() = runTest {
        for (code in listOf(-1, -2, -5, -6, -7, -14, -15)) {
            val api = Api(Unit, code)
            val tokens = tokens(api)
            assertNull(tokens.token(123, "hash"))
            assertNull(tokens.token(123, "hash"))
            assertFalse(tokens.prepare(123))
            assertEquals("code $code", 2, api.calls.size)
            // An outdated Play Store or Play services is updated by Play: only a phone without Play stays so.
            assertEquals("code $code", code !in listOf(-14, -15), tokens.unavailable)
        }
        // A prepare that finds no Play counts the same.
        val api = Api(-6)
        val tokens = tokens(api)
        assertFalse(tokens.prepare(123))
        assertNull(tokens.token(123, "hash"))
        assertEquals(listOf("prepare 123"), api.calls)
    }

    @Test
    fun configurationBugsAreReportedAndRegisterWithoutAToken() = runTest {
        val reported = mutableListOf<Int>()
        val api = Api(Unit, -17, -16)
        val tokens = tokens(api, reported)
        assertNull(tokens.token(123, "hash"))
        assertFalse(tokens.prepare(456))
        assertEquals(listOf(-17, -16), reported)
    }

    @Test
    fun otherFailuresRegisterWithoutAToken() = runTest {
        val api = Api(Unit, -4)
        assertNull(tokens(api).token(123, "hash"))
        val broken = object : StandardIntegrity {
            override suspend fun prepare(projectNumber: Long) = throw IllegalStateException("synthetic")
            override suspend fun request(requestHash: String) = "unused"
        }
        assertNull(PlayIntegrityTokens(broken).token(123, "hash"))
    }

    @Test
    fun noRegistrationWaitsMoreThan30Seconds() = runTest {
        val api = Api(Unit, "late")
        api.hang = CompletableDeferred()
        assertNull(tokens(api).token(123, "hash"))
        assertEquals(30_000L, currentTime)
    }

    @Test
    fun atMost5ProvidersAreReadiedAMinute() = runTest {
        val api = Api(Unit, Unit, Unit, Unit, Unit, Unit)
        val tokens = tokens(api)
        for (project in 1L..5L) assertTrue(tokens.prepare(project))
        assertFalse(tokens.prepare(6))
        // The one ready still serves.
        api.answers.addFirst("synthetic-token")
        assertEquals("synthetic-token", tokens.token(5, "hash"))
        testScheduler.advanceTimeBy(60.seconds)
        api.answers.addLast(Unit)
        assertTrue(tokens.prepare(6))
    }
}
