package com.plhery.peekdeliverytracker.core

/**
 * A phone sorts, writes amounts and distances and reads host names with ICU, as iOS does, where the JDK has its own
 * versions. Test classes that compare text, write amounts or distances or read links call this first, so they see what
 * a phone does.
 */
internal object TestIcu {
    fun install() {
        Icu.hostNames = checkNotNull(IcuHostNames.from("com.ibm.icu.text")) { "ICU4J is a test dependency" }
        Icu.collators = checkNotNull(IcuCollators.from("com.ibm.icu.text")) { "ICU4J is a test dependency" }
        Icu.currencies = checkNotNull(IcuCurrencies.from("com.ibm.icu")) { "ICU4J is a test dependency" }
        Icu.lengths = checkNotNull(IcuLengths.from("com.ibm.icu")) { "ICU4J is a test dependency" }
    }
}
