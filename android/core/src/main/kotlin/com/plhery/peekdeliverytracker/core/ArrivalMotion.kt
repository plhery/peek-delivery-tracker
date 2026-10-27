package com.plhery.peekdeliverytracker.core

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

// From ArrivalMotion.swift, the filter. Reading the device's motion is the app's.

/** A small, bounded pose relative to how the phone was held on arrival. */
data class ArrivalTilt(val x: Double = 0.0, val y: Double = 0.0) {
    /** The pose after a motion sample; this one, unchanged, for a sample that isn't finite. */
    fun follow(roll: Double, pitch: Double, accelerationX: Double = 0.0, accelerationY: Double = 0.0, quarterTurns: Int = 0): ArrivalTilt {
        if (!roll.isFinite() || !pitch.isFinite() || !accelerationX.isFinite() || !accelerationY.isFinite()) return this
        // Device motion separates gravity from translation: a small inertial
        // response makes the paper feel weighted even without rotating the phone.
        val horizontalInput = roll / 0.36 + min(1.0, max(-1.0, accelerationX * 2)) * 0.16
        val verticalInput = pitch / 0.36 - min(1.0, max(-1.0, accelerationY * 2)) * 0.16
        val angle = quarterTurns.toDouble() * PI / 2
        val horizontal = horizontalInput * cos(angle) + verticalInput * sin(angle)
        val vertical = verticalInput * cos(angle) - horizontalInput * sin(angle)
        // A low-pass filter removes hand tremor; output can never exceed ±1.
        return ArrivalTilt(
            x = x + (min(1.0, max(-1.0, horizontal)) - x) * 0.22,
            y = y + (min(1.0, max(-1.0, vertical)) - y) * 0.22,
        )
    }
}
