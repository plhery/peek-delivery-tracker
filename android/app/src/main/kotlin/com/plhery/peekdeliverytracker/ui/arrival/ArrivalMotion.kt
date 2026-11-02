package com.plhery.peekdeliverytracker.ui.arrival

import android.view.Surface
import android.view.View
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.currentStateAsState
import com.plhery.peekdeliverytracker.core.ArrivalTilt
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.atan2
import kotlinx.coroutines.delay

/**
 * The device's motion, which the welcome's parcel follows (`ArrivalMotion`'s `CMMotionManager`): its attitude and the
 * acceleration the hand gives it, read while [start]ed. The app's reads the phone's sensors
 * ([com.plhery.peekdeliverytracker.platform.SensorArrivalMotion]).
 */
fun interface ArrivalMotionSource {
    /** `startDeviceMotionUpdates`: the readings, until stopped; null where the device can't tell its attitude. */
    fun start(): ArrivalMotionReadings?
}

/** The readings of a motion source started ([ArrivalMotionSource.start]). */
interface ArrivalMotionReadings {
    /** `manager.deviceMotion`: the latest reading, null before the first. */
    val latest: ArrivalMotionReading?

    /** `stopDeviceMotionUpdates()`. */
    fun stop()
}

/**
 * A reading of the device's motion: its attitude, the rotation from the device's axes to the world's (3 × 3, row by
 * row, as `SensorManager.getRotationMatrixFromVector` gives it), and the acceleration the hand gives it along its x
 * and y axes, in g (`userAcceleration`).
 */
class ArrivalMotionReading(val rotation: FloatArray, val accelerationX: Double, val accelerationY: Double)

/** No motion to follow: the parcel stays level, as on a Mac. */
object NoArrivalMotion : ArrivalMotionSource {
    override fun start(): ArrivalMotionReadings? = null
}

/** The motion the welcome's parcel follows: the container's sensors in the app; none, level, where nothing gives it. */
val LocalArrivalMotion = staticCompositionLocalOf<ArrivalMotionSource> { NoArrivalMotion }

/**
 * `currentTilt`: the welcome's parcel's pose, following the device's motion while [greetingActive] and the window is
 * resumed (`motion.setActive(_:)`), and level otherwise and under Reduce Motion, animated `.easeOut(duration: opening ?
 * 0.35 : 0.07)` as it changes. Started, the motion is sampled every 33 ms: the first sample, and the first after the
 * display turns, is the reference the next are seen from (the attitude on arrival), the tilt level again; each sample
 * after it moves the tilt toward the attitude relative to it ([relativeRollPitch]) and the hand's acceleration, turned
 * by the display's quarter turns ([quarterTurns]), and changes it only by more than 0.0005. Stopped, it is level, and
 * its readings stop in every way it can stop: the greeting over, the window gone from the front, the welcome gone.
 */
@Composable
internal fun rememberArrivalTilt(greetingActive: Boolean, opening: Boolean): ArrivalTilt {
    val source = LocalArrivalMotion.current
    val view = LocalView.current
    val resumed = LocalLifecycleOwner.current.lifecycle.currentStateAsState().value.isAtLeast(Lifecycle.State.RESUMED)
    val following = greetingActive && resumed
    var tilt by remember { mutableStateOf(ArrivalTilt()) }
    LaunchedEffect(following, source) {
        tilt = ArrivalTilt()
        if (!following) return@LaunchedEffect
        val readings = source.start() ?: return@LaunchedEffect
        try {
            var reference: FloatArray? = null
            var turns: Int? = null
            while (true) {
                val reading = readings.latest
                if (reading != null) {
                    val current = quarterTurns(view)
                    if (turns != current) {
                        turns = current
                        reference = null
                        tilt = ArrivalTilt()
                    }
                    val from = reference
                    if (from == null) {
                        reference = reading.rotation.copyOf()
                    } else {
                        val (roll, pitch) = relativeRollPitch(from, reading.rotation)
                        val next = tilt.follow(roll, pitch, reading.accelerationX, reading.accelerationY, current)
                        if (abs(next.x - tilt.x) + abs(next.y - tilt.y) > 0.0005) tilt = next
                    }
                }
                delay(SAMPLE_MILLIS)
            }
        } finally {
            readings.stop()
        }
    }
    val shown = if (PeekTheme.reduceMotion) ArrivalTilt() else tilt
    val spec = Motion.easeOut<Float>(if (opening) 0.35 else 0.07)
    val x by animateFloatAsState(shown.x.toFloat(), spec, label = "tiltX")
    val y by animateFloatAsState(shown.y.toFloat(), spec, label = "tiltY")
    return ArrivalTilt(x.toDouble(), y.toDouble())
}

/**
 * `relative.multiply(byInverseOf: reference)`'s roll and pitch, in radians: how far the device, at [rotation], has
 * turned about its y axis (its right side going down) and its x axis (its top coming up) since [reference], both device
 * to world, row by row. The angles are those of the turn from the reference's axes taken in yaw, pitch, roll order, as
 * Core Motion's.
 */
internal fun relativeRollPitch(reference: FloatArray, rotation: FloatArray): Pair<Double, Double> {
    // The turn from the reference, its transpose times the rotation, of which only the last row is needed: the
    // reference's z axis as the device's axes now see it.
    fun turn(row: Int, column: Int) = (0..2).sumOf { reference[it * 3 + row].toDouble() * rotation[it * 3 + column] }
    val pitch = asin(turn(2, 1).coerceIn(-1.0, 1.0))
    val roll = atan2(-turn(2, 0), turn(2, 2))
    return roll to pitch
}

/**
 * `turns`: the display's quarter turns as the iPhone counts its interface's, 1 where the device is turned clockwise
 * (`.landscapeLeft`, the display drawn a quarter anticlockwise, [Surface.ROTATION_270]), -1 anticlockwise
 * (`.landscapeRight`), 2 upside down, 0 upright and where the view has no display.
 */
internal fun quarterTurns(view: View): Int = when (view.display?.rotation) {
    Surface.ROTATION_270 -> 1
    Surface.ROTATION_90 -> -1
    Surface.ROTATION_180 -> 2
    else -> 0
}

/** `deviceMotionUpdateInterval` and the samples' `Task.sleep`: 30 a second. */
private const val SAMPLE_MILLIS = 33L
