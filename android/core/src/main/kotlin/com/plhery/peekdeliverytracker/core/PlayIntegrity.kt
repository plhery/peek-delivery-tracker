package com.plhery.peekdeliverytracker.core

import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Google Play's Integrity API, its standard requests: the Play build's (GooglePlayIntegrity). The F-Droid build has none.
 * Each fails with PlayIntegrityError, which carries `StandardIntegrityException.errorCode`.
 */
interface StandardIntegrity {
    /** Readies a token provider for the Cloud project [projectNumber], replacing the last. */
    suspend fun prepare(projectNumber: Long)

    /** A token for [requestHash] from the provider [prepare] readied. */
    suspend fun request(requestHash: String): String
}

class PlayIntegrityError(val code: Int) : Exception("Play Integrity error $code")

/**
 * The Play Integrity token that goes with a key's registration, where the server gives a Cloud project. A token is
 * Google Play's word on the phone and the app, which can register a key the phone's keystore attests badly, and which a
 * registration never waits on: whatever fails, the key registers with its attestation alone.
 *
 * One for the process. The provider is readied at launch, ahead of the registration's challenge, and kept; at most 5 are
 * readied a minute. A transient failure is asked again after 5 s, then 10 s; an invalid provider is replaced once. A
 * phone without Play (`API_NOT_AVAILABLE` and the like: a de-Googled phone, a sideload) isn't asked again, and a
 * configuration or client bug (an invalid project, a request hash too long) is [report]ed.
 */
class PlayIntegrityTokens(
    private val api: StandardIntegrity,
    private val report: (Int) -> Unit = {},
    /** Milliseconds on a monotonic clock. */
    private val now: () -> Long = { System.nanoTime() / 1_000_000 },
) {
    private val lock = Mutex()
    private var prepared: Long? = null
    @Volatile private var gone = false
    val unavailable: Boolean get() = gone
    private val prepares = ArrayDeque<Long>()

    /** Readies a provider for [project] ahead of a registration; whether there is one. */
    suspend fun prepare(project: Long): Boolean = lock.withLock {
        try {
            ready(project, fresh = false)
        } catch (error: Exception) {
            failed(error)
            false
        }
    }

    /** A token for [requestHash] within [within], or null: no Play, a failure, or not in time. */
    suspend fun token(project: Long, requestHash: String, within: Duration = 30.seconds): String? =
        withTimeoutOrNull(within) { lock.withLock { attempt(project, requestHash) } }

    private suspend fun attempt(project: Long, requestHash: String): String? {
        val waits = ArrayDeque(listOf(5.seconds, 10.seconds))
        var fresh = false
        var replaced = false
        while (true) {
            try {
                if (!ready(project, fresh)) return null
                fresh = false
                return api.request(requestHash)
            } catch (error: Exception) {
                if (error is CancellationException) throw error
                val code = (error as? PlayIntegrityError)?.code
                when {
                    code in RETRIED && waits.isNotEmpty() -> delay(waits.removeFirst())
                    code == PROVIDER_INVALID && !replaced -> {
                        replaced = true
                        fresh = true
                    }
                    else -> {
                        failed(error)
                        return null
                    }
                }
            }
        }
    }

    /** Whether a provider for [project] is ready, readying one where it isn't (or where [fresh] asks for a new one). */
    private suspend fun ready(project: Long, fresh: Boolean): Boolean {
        if (gone) return false
        if (prepared == project && !fresh) return true
        val at = now()
        while (prepares.isNotEmpty() && at - prepares.first() >= 60_000) prepares.removeFirst()
        if (prepares.size >= 5) return false
        prepares.addLast(at)
        prepared = null
        api.prepare(project)
        prepared = project
        return true
    }

    private fun failed(error: Exception) {
        if (error is CancellationException) throw error
        val code = (error as? PlayIntegrityError)?.code ?: return
        if (code in NO_PLAY) gone = true
        if (code in BUGS) report(code)
    }

    companion object {
        /** Network, too many requests, Google's servers, a transient client error, an internal error. */
        val RETRIED = setOf(-3, -8, -12, -18, -100)

        const val PROVIDER_INVALID = -19

        /** No Play here: no API, no Play Store, not installed by it, no Play services, a UID mismatch, no binding, outdated. */
        val NO_PLAY = setOf(-1, -2, -5, -6, -7, -9, -14, -15)

        /** The Cloud project number is invalid; the request hash is too long. */
        val BUGS = setOf(-16, -17)
    }
}
