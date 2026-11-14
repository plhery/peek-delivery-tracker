package com.plhery.peekdeliverytracker

import android.provider.Settings
import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED

/**
 * The app as each Robolectric test makes it (`robolectric.properties`): its clock stopped at the captures' moment
 * ([CAPTURED]), read in Zurich's time as the build sets it, on a phone set to a 24-hour clock, so that the screenshots
 * are the same whatever the day and the hour. A test moves the clock on with [AppClock.source].
 */
class TestPeekApplication : PeekApplication() {
    override fun onCreate() {
        AppClock.source = { CAPTURED }
        Settings.System.putString(contentResolver, Settings.System.TIME_12_24, "24")
        super.onCreate()
    }
}
