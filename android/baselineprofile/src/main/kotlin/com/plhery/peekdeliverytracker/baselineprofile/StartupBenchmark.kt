package com.plhery.peekdeliverytracker.baselineprofile

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * A cold start on the list of parcels, as a returning person opens the app, in each of [compilations].
 */
@RunWith(Parameterized::class)
class StartupBenchmark(private val compilation: CompilationMode) {
    @get:Rule
    val rule = MacrobenchmarkRule()

    private var entered = false

    @Test
    fun coldStartOnTheList() = rule.measureRepeated(
        packageName = PACKAGE,
        metrics = listOf(StartupTimingMetric()),
        compilationMode = compilation,
        startupMode = StartupMode.COLD,
        iterations = 15,
        // The demo entered once, so each start opens on the list; the app is stopped after this, before each start.
        setupBlock = {
            if (!entered) {
                openList()
                entered = true
            }
        },
    ) {
        openList()
    }

    companion object {
        @Parameterized.Parameters(name = "{0}")
        @JvmStatic
        fun parameters(): List<CompilationMode> = compilations()
    }
}
