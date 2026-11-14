package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.provider.Settings
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.DistanceUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** The phone's clock and distance unit, which the app's Automatic choices follow. */
@RunWith(AndroidJUnit4::class)
class DeviceTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun theClockIsThePhonesSwitch() {
        Settings.System.putString(context.contentResolver, Settings.System.TIME_12_24, "12")
        assertFalse(Device.uses24HourTime(context))
        Settings.System.putString(context.contentResolver, Settings.System.TIME_12_24, "24")
        assertTrue(Device.uses24HourTime(context))
    }

    @Test
    @Config(qualifiers = "fr-rFR")
    fun aMetricRegionMeasuresInKilometres() = assertEquals(DistanceUnit.Kilometres, Device.distanceUnit())

    @Test
    @Config(qualifiers = "en-rUS")
    fun theUsMeasuresInMiles() = assertEquals(DistanceUnit.Miles, Device.distanceUnit())

    @Test
    @Config(qualifiers = "en-rGB")
    fun theUkMeasuresInMiles() = assertEquals(DistanceUnit.Miles, Device.distanceUnit())
}
