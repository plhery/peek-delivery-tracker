package com.plhery.peekdeliverytracker.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Collects the app's baseline profile from its unshrunk benchmark build: starting on the welcome and entering the
 * demo, starting again on the list, scrolling it and opening a parcel's page. Its rules go in
 * app/src/main/baselineProfiles once the benchmarks show they help (android/README.md).
 */
@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {
    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() = rule.collect(packageName = PACKAGE) {
        pressHome()
        val list = openList()
        scrollThrough(list)
        openFirstParcel()
    }
}
