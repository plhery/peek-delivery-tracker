package com.plhery.peekdeliverytracker

import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED

/**
 * The app as each Robolectric test makes it (`robolectric.properties`): its clock stopped at the captures' moment
 * ([CAPTURED]), read in Zurich's time as the build sets it, so that the screenshots are the same whatever the day and
 * the hour. A test moves the clock on with [AppClock.source].
 */
class TestPeekApplication : PeekApplication() {
    override fun onCreate() {
        AppClock.source = { CAPTURED }
        super.onCreate()
    }
}
