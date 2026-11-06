package com.plhery.peekdeliverytracker.core

import java.time.Instant

/**
 * The wall clock behind everything the app says or decides about now (`Date()` on the iPhone): the phone's, which the
 * app's tests set to one moment, so that what they draw is the same whatever the day and the hour.
 */
object AppClock {
    @Volatile
    var source: () -> Instant = Instant::now

    fun now(): Instant = source()
}
