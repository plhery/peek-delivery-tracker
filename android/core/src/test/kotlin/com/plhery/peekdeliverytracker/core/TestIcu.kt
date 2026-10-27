package com.plhery.peekdeliverytracker.core

/**
 * A phone sorts and reads host names with ICU, as iOS does, where the JDK has its own versions. Test classes
 * that compare text or read links call this first, so they see what a phone does.
 */
internal object TestIcu {
    fun install() {
        Icu.hostNames = checkNotNull(IcuHostNames.from("com.ibm.icu.text")) { "ICU4J is a test dependency" }
        Icu.collators = checkNotNull(IcuCollators.from("com.ibm.icu.text")) { "ICU4J is a test dependency" }
    }
}
