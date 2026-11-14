package com.plhery.peekdeliverytracker.baselineprofile

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/** The frames of a parcel's page as it opens from the list and draws its map and history, in each of [compilations]. */
@RunWith(Parameterized::class)
class PageBenchmark(private val compilation: CompilationMode) {
    @get:Rule
    val rule = MacrobenchmarkRule()

    @Test
    fun openAParcel() = rule.measureRepeated(
        packageName = PACKAGE,
        metrics = listOf(FrameTimingMetric()),
        compilationMode = compilation,
        iterations = 10,
        // Back on the list the page left open, the app still running.
        setupBlock = {
            pressHome()
            startActivityAndWait()
            backToTheList()
        },
    ) {
        openFirstParcelAndWait()
    }

    companion object {
        @Parameterized.Parameters(name = "{0}")
        @JvmStatic
        fun parameters(): List<CompilationMode> = compilations()
    }
}
