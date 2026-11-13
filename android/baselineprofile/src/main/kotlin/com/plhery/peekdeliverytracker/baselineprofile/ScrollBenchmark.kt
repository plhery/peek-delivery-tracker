package com.plhery.peekdeliverytracker.baselineprofile

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/** The list's frames as it is flung to its end and back, in each of [compilations]. */
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
        },
    ) {
        scrollThrough(awaitList())
    }

    companion object {
        @Parameterized.Parameters(name = "{0}")
        @JvmStatic
        fun parameters(): List<CompilationMode> = compilations()
    }
}
