package com.plhery.peekdeliverytracker.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.provider.Settings
import android.util.Log
import android.view.PixelCopy
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.ArrivalTilt
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.FriendInvitationStore
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.platform.SensorArrivalMotion
import com.plhery.peekdeliverytracker.ui.arrival.ArrivalMotionReadings
import com.plhery.peekdeliverytracker.ui.arrival.ArrivalMotionSource
import com.plhery.peekdeliverytracker.ui.arrival.LocalArrivalMotion
import com.plhery.peekdeliverytracker.ui.arrival.NoArrivalMotion
import com.plhery.peekdeliverytracker.ui.arrival.quarterTurns
import com.plhery.peekdeliverytracker.ui.arrival.relativeRollPitch
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.INVITATION_BASE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.appearanceSetting
import com.plhery.peekdeliverytracker.ui.theme.reduceMotionSetting
import java.io.File
import java.util.UUID
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith

/**
 * The welcome's parcel following this device's own motion (A-AN49, A-P02, C-AN06, X-AD43): a friend's invitation's
 * welcome on the window, on fake deliveries and a made-up invitation that reach no server, its tilt read from the
 * phone's sensors as the app's ([SensorArrivalMotion]) and Reduce Motion from the phone's setting as the app reads it.
 * The motion comes from outside the test: a hand on a device, or the emulator's device pose turned over its gRPC
 * bridge, as Extended controls › Virtual sensors turns it. So the walk runs only when asked, with `-e tiltWalk
 * <seconds>`, and is skipped otherwise.
 *
 * With motion on the welcome never stands idle, its stars asking for frames all along, so the test moves Compose's
 * clock itself, a frame at a time, and reads the window as the screen shows it. Once the welcome has settled it saves
 * the window, level, and logs "ready" (tag PeekTiltWalk) as it starts following. Every half second for the walk's
 * seconds it logs the roll and pitch the sensors give since the first reading, the display's quarter turns, the tilt
 * those make through the app's filter, whether animations are off, and how many of the window's pixels (every fourth
 * each way) differ from the level one (the first, then the first a second after a turn; -1 until then), and saves the
 * window as tiltwalk/<test>/<ms>.png in the app's files. It passes once the parcel has been drawn leaning: a window
 * differing from the level one by more than the welcome's stars move it, a hundredth of the pixels.
 *
 * A half turn keeps the window; a quarter turn makes it again, so the display is turned a quarter before the walk,
 * not during it.
 */
@RunWith(AndroidJUnit4::class)
class ArrivalTiltDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val name = TestName()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(name).around(MadePreferences()).around(ZurichTime()).around(compose)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext
    private val store = AppearanceStore.of(context)
    private val appearanceBefore = store.appearance.value
    private val preferences = PreferencesDefaults.standard(context)
    private val pendingBefore = preferences.data(FriendInvitationStore.STORAGE_KEY)
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** The phone's sensors, as the app reads them, and the readings the welcome started. */
    private val sensors = Watched(SensorArrivalMotion(context))

    /** The motion the welcome follows: none until it has settled, then the phone's. */
    private val motion = mutableStateOf<ArrivalMotionSource>(NoArrivalMotion)

    private lateinit var inbox: DeliveriesInbox
    private lateinit var invitation: FriendInvitationStore

    @After
    fun putBack() {
        store.set(appearanceBefore)
        val pending = pendingBefore
        if (pending != null) {
            preferences.set(pending, forKey = FriendInvitationStore.STORAGE_KEY)
        } else {
            preferences.removeObject(forKey = FriendInvitationStore.STORAGE_KEY)
        }
    }

    @Test
    fun theWelcomesParcelFollowsTheDevice() {
        val seconds = InstrumentationRegistry.getArguments().getString("tiltWalk")?.toIntOrNull()
        assumeTrue("A walk the device's motion drives from outside: -e tiltWalk <seconds>", seconds != null)
        show()
        val activity = compose.activity
        val view = activity.window.decorView
        // With motion on, the welcome never stands idle (its stars keep asking for frames): from here the test's
        // clock goes on only as the walk moves it, and the window is read as the screen shows it, not through
        // Compose's idling.
        compose.mainClock.autoAdvance = false
        instrumentation.runOnMainSync { inbox.follow(Intent(Intent.ACTION_VIEW, Uri.parse(LINK))) }
        frames(SETTLE_MILLIS)
        assertTrue("The welcome, with its sender's name", invitation.isPresenting && invitation.nicknameFlow.value != null)
        val level = window(activity)
        save(level, "level")
        instrumentation.runOnMainSync { motion.value = sensors }
        frames(FRAME_MILLIS)
        Log.i(TAG, "ready")

        val start = SystemClock.uptimeMillis()
        var started: ArrivalMotionReadings? = null
        var reference: FloatArray? = null
        var turns: Int? = null
        var tilt = ArrivalTilt()
        var nextShot = 0L
        var mostDiffering = 0
        // The level window the shots are measured against: the first one, then the first a second after each turn.
        var baseline: Bitmap? = level
        var rebaseAt = Long.MAX_VALUE
        while (true) {
            val elapsed = SystemClock.uptimeMillis() - start
            if (elapsed > seconds!! * 1_000L) break
            compose.mainClock.advanceTimeBy(FRAME_MILLIS)
            // The app's filter, sample by sample, beside the welcome's own: what its parcel should be drawn at.
            var roll = 0.0
            var pitch = 0.0
            instrumentation.runOnMainSync {
                // Readings started again (animations back on) start from their own first, as the welcome's do.
                if (sensors.readings !== started) {
                    started = sensors.readings
                    reference = null
                    tilt = ArrivalTilt()
                }
                val reading = started?.latest ?: return@runOnMainSync
                val current = quarterTurns(view)
                if (turns != current) {
                    if (turns != null) {
                        baseline = null
                        rebaseAt = elapsed + REBASE_MILLIS
                    }
                    turns = current
                    reference = null
                    tilt = ArrivalTilt()
                }
                val from = reference
                if (from == null) {
                    reference = reading.rotation.copyOf()
                } else {
                    relativeRollPitch(from, reading.rotation).let { (r, p) -> roll = r; pitch = p }
                    tilt = tilt.follow(roll, pitch, reading.accelerationX, reading.accelerationY, current)
                }
            }
            if (elapsed >= nextShot) {
                nextShot += SHOT_MILLIS
                val shot = window(activity)
                if (baseline == null && elapsed >= rebaseAt) baseline = shot
                val differing = baseline?.let { differing(it, shot) } ?: -1
                mostDiffering = maxOf(mostDiffering, differing)
                save(shot, "%05d".format(elapsed))
                Log.i(
                    TAG,
                    "t=%5d roll=%6.1f pitch=%6.1f turns=%d tilt=(%5.2f, %5.2f) animationsOff=%b differing=%d".format(
                        elapsed, Math.toDegrees(roll), Math.toDegrees(pitch), turns ?: 0, tilt.x, tilt.y, animationsOff(), differing,
                    ),
                )
            }
            SystemClock.sleep(FRAME_MILLIS)
        }
        Log.i(TAG, "done, most differing $mostDiffering of ${sampled(level)}")
        assertTrue("The parcel drawn leaning somewhere in the walk ($mostDiffering pixels differing)", mostDiffering > sampled(level) / 100)
    }

    /** The tabs of a signed-in reader as the app's window, the welcome's motion [motion], Reduce Motion the phone's. */
    private fun show() {
        store.set(AppAppearance.Light)
        val source = FakeDeliveries(DemoCards.parcels(), reader)
        invitation = source.invitation
        inbox = DeliveriesInbox(invitationLink = source.invitation::open)
        compose.waitsForMaps()
        compose.setContent {
            val dark = appearanceSetting().isDark(isSystemInDarkTheme())
            DisposableEffect(dark) {
                compose.activity.edgeToEdge(dark)
                onDispose {}
            }
            CompositionLocalProvider(LocalArrivalMotion provides motion.value) {
                RootHost(state = reader, dark = dark, reduceMotion = reduceMotionSetting(), source = source, inbox = inbox)
            }
        }
        compose.waitUntil(5_000) { exists(TAB_BAR) }
        compose.waitForIdle()
    }

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** The test's clock moved on by [millis], a frame at a time, in about as long a real time for the window to draw. */
    private fun frames(millis: Long) {
        var gone = 0L
        while (gone < millis) {
            compose.mainClock.advanceTimeBy(FRAME_MILLIS)
            SystemClock.sleep(FRAME_MILLIS)
            gone += FRAME_MILLIS
        }
    }

    /** [activity]'s window as the screen shows it now. */
    private fun window(activity: ComponentActivity): Bitmap {
        val view = activity.window.decorView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val copied = CountDownLatch(1)
        var result = PixelCopy.ERROR_UNKNOWN
        PixelCopy.request(activity.window, bitmap, { result = it; copied.countDown() }, Handler(Looper.getMainLooper()))
        check(copied.await(5, TimeUnit.SECONDS) && result == PixelCopy.SUCCESS) { "The window couldn't be read ($result)" }
        return bitmap
    }

    private fun save(bitmap: Bitmap, shot: String) {
        val file = File(context.filesDir, "tiltwalk/${name.methodName}/$shot.png")
        file.parentFile?.mkdirs()
        file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
    }

    private fun sampled(bitmap: Bitmap) = ((bitmap.width + 3) / 4) * ((bitmap.height + 3) / 4)

    /** How many of every fourth pixel each way differ between [a] and [b], by more than a rounding in any channel. */
    private fun differing(a: Bitmap, b: Bitmap): Int {
        if (a.width != b.width || a.height != b.height) return sampled(a)
        var count = 0
        for (y in 0 until a.height step 4) {
            for (x in 0 until a.width step 4) {
                val p = a.getPixel(x, y)
                val q = b.getPixel(x, y)
                val near = (0..3).all { abs((p shr (it * 8) and 0xFF) - (q shr (it * 8) and 0xFF)) <= 2 }
                if (!near) count++
            }
        }
        return count
    }

    private fun animationsOff() = Settings.Global.getFloat(context.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f) == 0f

    /** [source], keeping the readings it last started. */
    private class Watched(private val source: ArrivalMotionSource) : ArrivalMotionSource {
        @Volatile
        var readings: ArrivalMotionReadings? = null

        override fun start(): ArrivalMotionReadings? = source.start().also { readings = it }
    }

    private companion object {
        const val TAG = "PeekTiltWalk"

        /** A made-up invitation code, in the 16-character form the service gives. */
        const val LINK = "$INVITATION_BASE/invite#Qa3vT8nL5wZ1pR7k"

        /** The welcome's samples, 30 a second. */
        const val FRAME_MILLIS = 33L

        const val SHOT_MILLIS = 500L

        /** How long after a turn the window is taken as the level one again. */
        const val REBASE_MILLIS = 1_000L

        /** How long the welcome is given to come in over the tabs, its parcel and name settled. */
        const val SETTLE_MILLIS = 3_000L
    }
}
