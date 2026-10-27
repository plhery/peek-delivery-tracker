package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Inputs where Foundation and the JVM part ways in Core.swift's logic, with what the iPhone app answers: each
 * expected value is the output of the Swift code run on iOS 27's Foundation.
 */
class CoreParityTests {
    @Test
    fun aLinkHostEndingInALineEndIsLeftOut() {
        assertEquals(emptyList<String>(), AppConfiguration.linkHosts("peektracker.com\nwww.peektracker.com"))
        assertEquals(emptyList<String>(), AppConfiguration.linkHosts("peektracker.com\n"))
        assertEquals(listOf("peektracker.com"), AppConfiguration.linkHosts("PeekTracker.com, www.peektracker.com\r\n"))
    }
}
