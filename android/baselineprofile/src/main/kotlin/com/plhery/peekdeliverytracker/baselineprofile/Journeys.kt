package com.plhery.peekdeliverytracker.baselineprofile

import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import android.content.Intent
import android.net.Uri
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
private const val ADD_SAVE = "addParcel.save"

/** The parcels the demo opens with. */
private const val DEMO_PARCELS = 17

/** The parcels on the list [lengthenTheList] makes. */
internal const val LONG_LIST = 60

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

/**
 * Adds synthetic numbers to the demo's parcels, each through Add as the app's own link opens it filled in, until the
 * list holds [LONG_LIST]. A build without a server keeps them on the phone, in the demo's store.
 */
internal fun MacrobenchmarkScope.lengthenTheList() {
    val context = InstrumentationRegistry.getInstrumentation().context
    for (index in 1..LONG_LIST - DEMO_PARCELS) {
        val link = Uri.parse("peekdeliverytracker://add?tracking=TESTLIST%04d".format(index))
        context.startActivity(Intent(Intent.ACTION_VIEW, link).setPackage(PACKAGE).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        checkNotNull(device.wait(Until.findObject(By.res(ADD_SAVE).enabled(true)), 10_000)) { "Add didn't open" }.click()
        check(device.wait(Until.gone(By.res(ADD_SAVE)), 10_000)) { "Add didn't save TESTLIST%04d".format(index) }
    }
}

/** Opens the first parcel's page and waits for it to settle. */
internal fun MacrobenchmarkScope.openFirstParcelAndWait() {
    val card = checkNotNull(device.findObject(By.res(PARCEL_LIST))?.findObject(By.clickable(true).descContains(", "))) { "No parcel" }
    card.click()
    check(device.wait(Until.hasObject(By.res(PAGE_CONTENT)), 5_000)) { "The page didn't open" }
    device.waitForIdle()
}

/** Goes back from a parcel's page to the list, or waits for the list where no page shows. */
internal fun MacrobenchmarkScope.backToTheList() {
    if (device.wait(Until.hasObject(By.res(PAGE_CONTENT)), 1_000)) device.pressBack()
    awaitList()
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
