package com.plhery.peekdeliverytracker.coredevice

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import java.time.ZoneId
import java.util.TimeZone

/**
 * Runs :core's tests in the zone given as the `timeZone` argument, as TEST_TIME_ZONE does on the JVM, else in the
 * device's. An unknown zone stops the run rather than leaving it in GMT.
 */
class CoreTestRunner : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle) {
        arguments.getString(TIME_ZONE)?.let { TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of(it))) }
        super.onCreate(arguments)
    }

    companion object {
        const val TIME_ZONE = "timeZone"
    }
}
