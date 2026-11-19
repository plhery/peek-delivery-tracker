package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.provider.Settings
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.DistanceUnit
import com.plhery.peekdeliverytracker.core.WeightUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.util.Locale

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

    /** Android 16's Measurement system setting, which reaches the app as the locale's `-u-ms-` keyword, over the region's. */
    @Test
    fun theMeasurementSystemChosenOnThePhoneOutweighsItsRegion() {
        fun units(tag: String) = Locale.forLanguageTag(tag).let { Device.distanceUnit(it) to Device.weightUnit(it) }
        assertEquals(DistanceUnit.Kilometres to WeightUnit.Kilograms, units("en-GB-u-ms-metric"))
        assertEquals(DistanceUnit.Kilometres to WeightUnit.Kilograms, units("en-US-u-ms-metric"))
        assertEquals(DistanceUnit.Miles to WeightUnit.Pounds, units("fr-FR-u-ms-ussystem"))
        assertEquals(DistanceUnit.Miles to WeightUnit.Kilograms, units("de-CH-u-ms-uksystem"))
        // Without the keyword, or with one it doesn't know, the region decides.
        assertEquals(DistanceUnit.Miles to WeightUnit.Kilograms, units("en-GB"))
        assertEquals(DistanceUnit.Miles to WeightUnit.Pounds, units("en-US-u-ms-other"))
        assertEquals(DistanceUnit.Kilometres to WeightUnit.Kilograms, units("de-CH-u-mu-fahrenhe"))
    }
}
