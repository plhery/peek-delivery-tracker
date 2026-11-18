package com.plhery.peekdeliverytracker.baselineprofile

import android.os.SystemClock
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

/** Runs in the benchmark test APK's own process on a phone with Peek's demo build. */
@RunWith(AndroidJUnit4::class)
class ProcessRestorationTest {
    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private fun find(selector: BySelector): UiObject2 =
        checkNotNull(device.wait(Until.findObject(selector), 10_000)) { "Missing $selector" }

    private fun reveal(selector: BySelector): UiObject2 {
        repeat(12) {
            device.findObject(selector)?.let { return it }
            find(By.scrollable(true)).scroll(Direction.DOWN, 0.7f)
        }
        return find(selector)
    }

    private fun launchFromHome() {
        device.executeShellCommand("am start -W -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -f 0x10200000 -n $PACKAGE/.MainActivity")
    }

    @Test
    fun theFrenchParcelPageSurvivesProcessDeath() {
        launchFromHome()
        device.wait(Until.findObject(By.res("welcome.demo")), 5_000)?.click()
        find(By.res("deliveries.accountButton")).click()
        reveal(By.res("settings.language.picker")).click()
        find(By.res("settings.language.fr")).click()
        find(By.res("settings.close")).click()

        val gift = "Cadeau d’anniversaire"
        reveal(By.clickable(true).descContains(gift)).click()
        fun assertParcel() {
            find(By.res("parcelPage.menuButton").desc("Actions du colis"))
            find(By.res("parcelPage.content").hasDescendant(By.textContains(gift)))
        }
        assertParcel()

        fun pid() = device.executeShellCommand("pidof $PACKAGE").trim()
        val original = pid()
        check(original.isNotEmpty())
        device.pressHome()
        check(device.wait(Until.gone(By.pkg(PACKAGE)), 5_000))
        val deadline = SystemClock.elapsedRealtime() + 10_000
        while (pid().isNotEmpty() && SystemClock.elapsedRealtime() < deadline) {
            device.executeShellCommand("am kill --user current $PACKAGE")
            SystemClock.sleep(200)
        }
        check(pid().isEmpty()) { "The background process did not exit" }
        launchFromHome()
        assertParcel()
        assertNotEquals(original, pid())
    }
}
