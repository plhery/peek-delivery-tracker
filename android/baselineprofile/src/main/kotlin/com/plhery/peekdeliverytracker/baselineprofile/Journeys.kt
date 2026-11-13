package com.plhery.peekdeliverytracker.baselineprofile

import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until

/** The app the benchmarks start: its benchmark build types keep the release's application ID. */
internal const val PACKAGE = "com.plhery.peekdeliverytracker"

// The app's test tags, which it gives UiAutomator as resource names (PeekRoot's testTagsAsResourceId).
private const val PARCEL_LIST = "parcelList"
private const val FIRST_OPEN_DEMO = "welcome.demo"
private const val PAGE_CONTENT = "parcelPage.content"

/**
 * The compilations each benchmark runs in: JIT alone (`none`) and with the APK's baseline profile installed as Google
 * Play installs it (`profile`), or only the one the instrumentation argument `peek.compilation` names, to time two
 * builds in turn on one device.
 */
internal fun compilations(): List<CompilationMode> {
    val all = mapOf("none" to CompilationMode.None(), "profile" to CompilationMode.Partial(BaselineProfileMode.UseIfAvailable))
    val chosen = InstrumentationRegistry.getArguments().getString("peek.compilation") ?: return all.values.toList()
    return listOf(all[chosen] ?: error("peek.compilation is none or profile, not $chosen"))
}

/** Waits for the list of parcels, entering the demo first where the welcome asks, as on a fresh install. */
internal fun MacrobenchmarkScope.awaitList(): UiObject2 {
    device.wait(Until.hasObject(By.res(PARCEL_LIST)), 5_000)
    device.findObject(By.res(FIRST_OPEN_DEMO))?.click()
    return checkNotNull(device.wait(Until.findObject(By.res(PARCEL_LIST)), 10_000)) { "The list didn't open" }
}

/** Opens the app on its list, the demo's parcels in it. */
internal fun MacrobenchmarkScope.openList(): UiObject2 {
    startActivityAndWait()
    return awaitList()
}

/** Flings the list to its end and back, waiting for each fling to settle. */
internal fun MacrobenchmarkScope.scrollThrough(list: UiObject2) {
    list.setGestureMarginPercentage(0.2f)
    repeat(3) {
        list.fling(Direction.DOWN)
        device.waitForIdle()
    }
    repeat(3) {
        list.fling(Direction.UP)
        device.waitForIdle()
    }
}

/** Opens the first parcel's page and goes back to the list. */
internal fun MacrobenchmarkScope.openFirstParcel() {
    val card = device.findObject(By.res(PARCEL_LIST))?.findObject(By.clickable(true).descContains(", ")) ?: return
    card.click()
    if (device.wait(Until.hasObject(By.res(PAGE_CONTENT)), 5_000)) {
        device.findObject(By.res(PAGE_CONTENT))?.fling(Direction.DOWN)
        device.waitForIdle()
        device.pressBack()
        device.wait(Until.hasObject(By.res(PARCEL_LIST)), 5_000)
    }
}
