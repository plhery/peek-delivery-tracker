package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.plhery.peekdeliverytracker.ui.arrival.ArrivalMotionReading
import com.plhery.peekdeliverytracker.ui.arrival.ArrivalMotionReadings
import com.plhery.peekdeliverytracker.ui.arrival.ArrivalMotionSource

/**
 * `CMMotionManager`'s device motion (A-AN49, A-D25, A-P02) from the phone's sensors, 30 a second: the attitude from the
 * game rotation vector (gyroscope and accelerometer without the compass, as `.xArbitraryZVertical` leaves north
 * arbitrary) and the hand's acceleration from the linear acceleration, gravity taken out, in g as `userAcceleration`
 * gives it. Neither needs a permission. Without the rotation vector there is nothing to follow and the parcel stays
 * level; without the linear acceleration it follows the attitude alone. The readings come on the main thread, which
 * reads them, and stop with their listener unregistered.
 */
class SensorArrivalMotion(context: Context) : ArrivalMotionSource {
    private val sensors = context.getSystemService(SensorManager::class.java)

    override fun start(): ArrivalMotionReadings? {
        val manager = sensors ?: return null
        val attitude = manager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR) ?: return null
        val readings = Readings(manager)
        manager.registerListener(readings, attitude, SAMPLING_MICROS)
        manager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)?.let { manager.registerListener(readings, it, SAMPLING_MICROS) }
        return readings
    }

    private class Readings(private val manager: SensorManager) : ArrivalMotionReadings, SensorEventListener {
        private var rotation: FloatArray? = null
        private var accelerationX = 0.0
        private var accelerationY = 0.0

        override val latest: ArrivalMotionReading?
            get() = rotation?.let { ArrivalMotionReading(it, accelerationX, accelerationY) }

        override fun onSensorChanged(event: SensorEvent) {
            when (event.sensor.type) {
                Sensor.TYPE_GAME_ROTATION_VECTOR -> rotation = FloatArray(9).also { SensorManager.getRotationMatrixFromVector(it, event.values) }
                Sensor.TYPE_LINEAR_ACCELERATION -> {
                    accelerationX = event.values[0] / SensorManager.GRAVITY_EARTH.toDouble()
                    accelerationY = event.values[1] / SensorManager.GRAVITY_EARTH.toDouble()
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) = Unit

        override fun stop() = manager.unregisterListener(this)
    }

    private companion object {
        /** `deviceMotionUpdateInterval = 1.0 / 30`. */
        const val SAMPLING_MICROS = 33_333
    }
}
