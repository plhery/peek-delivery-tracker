package com.plhery.peekdeliverytracker.baselineprofile

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/** The frames of a list of [LONG_LIST] parcels as it is flung to its end and back, in each of [compilations]. */
@RunWith(Parameterized::class)
class ScrollBenchmark(private val compilation: CompilationMode) {
    @get:Rule
    val rule = MacrobenchmarkRule()

    @Test
    fun scrollTheList() = rule.measureRepeated(
        packageName = PACKAGE,
        metrics = listOf(FrameTimingMetric()),
        compilationMode = compilation,
        iterations = 10,
        setupBlock = {
            pressHome()
            openList()
            if (!lengthened) {
                lengthenTheList()
                lengthened = true
            }
        },
    ) {
        scrollThrough(awaitList())
    }

    companion object {
        /** Whether the list holds its [LONG_LIST] parcels: they stay from one compilation to the next. */
        private var lengthened = false

        @Parameterized.Parameters(name = "{0}")
        @JvmStatic
        fun parameters(): List<CompilationMode> = compilations()
    }
}
