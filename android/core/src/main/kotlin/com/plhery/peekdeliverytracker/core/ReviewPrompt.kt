package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import java.time.Instant
import java.time.ZoneId
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

// ReviewPrompt.swift. The system's prompt is the app's: Play In-App Review through `ReviewRequester`, where iOS asks
// StoreKit's `requestReview`. `settled(session:store:links:invitation:)` takes what it reads of the stores. The iPhone makes
// a `ReviewPrompt()` at each use, in the zone the device is in then; this one reads `timeZone` at each use instead.

/**
 * When Peek asks for a rating with the system's prompt, which the store shows only now and then: right after something
 * went well, once its reader has used the app on three days, once per version of the app, a month after the last ask at
 * the soonest, and never on a day something went wrong in front of them. The days are counted on this phone, and nothing
 * of it leaves it.
 */
class ReviewPrompt(
    private val defaults: UserDefaults,
    /** The app's version name (`CFBundleShortVersionString` on iOS): each version asks once. */
    private val version: String,
    /** The reader's zone, which their days are counted in: the device's at each use, as it moves with them. */
    private val timeZone: () -> ZoneId = ZoneId::systemDefault,
    /**
     * Debug builds' `-sdt.debug.reviewDue YES` on iOS: every calm moment is the one to ask at, so that a run shows the
     * prompt. Never in a release. Read at each use, as the iPhone reads its defaults.
     */
    private val forced: () -> Boolean = { false },
) {
    @Serializable
    private data class Memory(
        /** The days the app was used on, the latest of them included. */
        @Required val days: Int = 0,
        val lastDay: Long? = null,
        /** The latest day something went wrong. */
        val badDay: Long? = null,
        val askedVersion: String? = null,
        val askedDay: Long? = null,
    )

    /** The reader's parcels were in front of them `at`: its calendar day counts as one with use. */
    fun used(at: Instant) {
        val day = day(at)
        update { memory ->
            if (memory.lastDay == day) memory else memory.copy(lastDay = day, days = memory.days + 1)
        }
    }

    /**
     * Counts the day, and says whether a moment that went well `at` is the one to ask at: `calm` (nothing about it is
     * wrong), on the third day with use or a later one, for a version that has not asked, a month after the last ask,
     * on a day nothing went wrong. Nothing is asked until `asked`.
     */
    fun isDue(at: Instant, calm: Boolean): Boolean {
        used(at)
        if (forced()) return calm
        val memory = read()
        val day = day(at)
        if (!calm || memory.days < days || memory.askedVersion?.equalsCanonically(version) == true || memory.badDay == day) return false
        val askedDay = memory.askedDay
        if (askedDay != null && day - askedDay < gap) return false
        return true
    }

    /** Something went wrong in front of the reader `at`: nothing is asked for the rest of that day. */
    fun noteBadMoment(at: Instant) {
        val day = day(at)
        update { it.copy(badDay = day) }
    }

    /** The app asked `at`: this version asks no more, and the next one waits for the gap. */
    fun asked(at: Instant) {
        val day = day(at)
        update { it.copy(askedVersion = version, askedDay = day) }
    }

    /**
     * A screen is about to ask `at`, a moment after the moment that made it due: true, and remembered as an ask, while
     * the rule still holds then.
     */
    fun take(at: Instant, calm: Boolean): Boolean {
        if (!isDue(at, calm)) return false
        asked(at)
        return true
    }

    /**
     * The calendar day of `date` where the reader is, as days since 1970: the day's date read in the reader's zone,
     * counted in UTC, where every day is as long as the next.
     */
    private fun day(date: Instant): Long = date.atZone(timeZone()).toLocalDate().toEpochDay()

    private fun update(change: (Memory) -> Memory) {
        defaults.set(JsonEncoder().encode(Memory.serializer(), change(read())), forKey = key)
    }

    private fun read(): Memory {
        val data = defaults.data(forKey = key) ?: return Memory()
        return runCatching { ApiJson.decodeFromString(Memory.serializer(), data.decodeToString()) }.getOrNull() ?: Memory()
    }

    companion object {
        /** Days with use before the first ask. */
        const val days = 3

        /** Days from one ask to the next, whatever the version. */
        const val gap = 30

        /** How long a moment that went well is left to settle before the ask. */
        val settle: Duration = 2.seconds

        private const val key = "sdt.reviewPrompt.v1"

        /**
         * `settled(session:store:links:invitation:)`: nothing in the app stands in the way of an ask, whatever the
         * screen. Someone's own parcels are loaded and current, and no notice, notification permission, friend's
         * invitation or parcel link is over them. Never over the demo's made-up parcels, but for a look at the prompt
         * in a debug build (`asksInDemo`, the forced ask), nor over the first screen.
         */
        fun settled(
            session: AuthenticationState,
            hasParcels: Boolean,
            loading: Boolean,
            errorMessage: String?,
            authenticationRequired: Boolean,
            usingCachedData: Boolean,
            notificationEnableInProgress: Boolean,
            linkOpen: Boolean,
            invitationShown: Boolean,
            asksInDemo: Boolean,
        ): Boolean {
            val following = when (session) {
                is AuthenticationState.SignedIn -> true
                // Nobody is signed in: the parcels this phone follows, once there are some.
                AuthenticationState.Welcome -> hasParcels
                AuthenticationState.Demo -> asksInDemo
                AuthenticationState.Loading, AuthenticationState.SignedOut, AuthenticationState.Unconfigured -> false
            }
            return following && !loading && errorMessage == null && !authenticationRequired && !usingCachedData &&
                !notificationEnableInProgress && !linkOpen && !invitationShown
        }

        /**
         * Swift's `askingForReview(due:ready:take:)` modifier without its view: what its task does each time `due` or
         * `ready` changes. The screen runs it keyed on both (a `LaunchedEffect(due, ready)`), so that one that stops
         * being ready waits to be again. `ready` says the screen is in front of the reader with nothing over it, and
         * that includes the screen being resumed, as the iPhone's includes `scenePhase == .active`. `take` has the last
         * word, and an ask is counted (`report`, the analytics action). Never from a tap: the store may show nothing,
         * and a button that does nothing would be a broken one.
         *
         * StoreKit's prompt needs nothing first; Play's needs its review info, which may not come (no Play Store or an
         * outdated one, no connection, an app not installed from Play). It is fetched while the moment settles, and
         * without it nothing is taken: the version keeps its ask and no month starts, as no ask reached the system.
         */
        suspend fun ask(due: Boolean, ready: Boolean, take: () -> Boolean, requester: ReviewRequester, report: (String) -> Unit) {
            if (!due || !ready) return
            val prepared = coroutineScope {
                val prepared = async {
                    try {
                        requester.prepare()
                    } catch (error: Exception) {
                        currentCoroutineContext().ensureActive()
                        false
                    }
                }
                delay(settle)
                prepared.await()
            }
            if (!currentCoroutineContext().isActive || !prepared || !take()) return
            requester.launch()
            report("review-ask")
        }
    }
}

/**
 * Shows the system's prompt for a rating, which may show nothing: Play In-App Review (StoreKit's `requestReview` on
 * iOS), in two steps.
 */
interface ReviewRequester {
    /**
     * Play's `requestReviewFlow()`: fetches the review info the prompt needs. False, or a throw, when there is none (no
     * Play Store or an outdated one, no connection, an app not installed from Play); nothing is asked then.
     */
    suspend fun prepare(): Boolean

    /** Play's `launchReviewFlow` with the info `prepare` fetched, once the ask is counted. Play may show nothing. */
    fun launch()
}

/**
 * Nothing about the parcel is wrong or waits on its reader: its carrier is known and answers, and nothing on it needs
 * attention. Only then is an answer that its page is right the moment to ask for a rating.
 */
fun Parcel.untroubled(now: Instant = Instant.now()): Boolean =
    displayedCarrier != CarrierId.Unknown && syncStatus !in listOf(SyncStatus.Error, SyncStatus.Unsupported, SyncStatus.Waiting) &&
        attention(now) == null
