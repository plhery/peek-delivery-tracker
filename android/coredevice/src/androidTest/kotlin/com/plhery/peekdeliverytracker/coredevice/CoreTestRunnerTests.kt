package com.plhery.peekdeliverytracker.coredevice

import androidx.test.platform.app.InstrumentationRegistry
import java.time.ZoneId
import org.junit.Assert.assertEquals
import org.junit.Assume.assumeNotNull
import org.junit.Test

class CoreTestRunnerTests {
    /** The run's results say which zone the tests saw. */
    @Test
    fun theTestsRunInTheZoneAskedFor() {
        val asked = InstrumentationRegistry.getArguments().getString(CoreTestRunner.TIME_ZONE)
        assumeNotNull(asked)
        assertEquals(asked, ZoneId.systemDefault().id)
    }
}
