package com.plhery.peekdeliverytracker.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.os.ParcelFileDescriptor
import android.os.storage.StorageManager
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.platform.app.InstrumentationRegistry
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import org.junit.Assert.assertEquals
import org.junit.rules.ExternalResource

/**
 * The whole screen of this device, bars and all, as [folder]/<name>.png in the test package's external files, which adb
 * pulls: a device walk's shots, each once the window has drawn what its test settled.
 */
internal class DeviceShots(private val compose: AndroidComposeTestRule<*, *>, name: String) {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    /** The test package's external files, as its own context names them: the app's process may not write there. */
    val folder: File = File(
        checkNotNull(instrumentation.targetContext.getSystemService(StorageManager::class.java).primaryStorageVolume.directory),
        "Android/data/${instrumentation.context.packageName}/files/$name",
    )

    /** Waits until the window has committed two frames asked for after the test settled: the screen shows them. */
    private fun awaitFrames() {
        repeat(2) {
            val drawn = CountDownLatch(1)
            compose.runOnUiThread {
                val view = compose.activity.window.decorView
                view.viewTreeObserver.registerFrameCommitCallback { drawn.countDown() }
                view.invalidate()
            }
            check(drawn.await(5, TimeUnit.SECONDS)) { "The window drew no frame" }
        }
    }

    /** `command`'s output, run by the shell through UiAutomation, once it has ended. */
    private fun shell(command: String): String =
        ParcelFileDescriptor.AutoCloseInputStream(instrumentation.uiAutomation.executeShellCommand(command)).use { it.readBytes().decodeToString() }

    /**
     * The whole screen as [folder]/<name>.png, which it returns. Written by the shell: the app's process, where the test
     * runs, may neither make nor write another package's external files (scoped storage), and adb reads them.
     */
    fun take(name: String): Bitmap {
        compose.waitForIdle()
        awaitFrames()
        val taken = checkNotNull(instrumentation.uiAutomation.takeScreenshot()) { "No screenshot" }
        val screen = if (taken.config == Bitmap.Config.HARDWARE) taken.copy(Bitmap.Config.ARGB_8888, false) else taken
        val png = ByteArrayOutputStream().use { out ->
            check(screen.compress(Bitmap.CompressFormat.PNG, 100, out))
            out.toByteArray()
        }
        val file = File(folder, "$name.png")
        shell("mkdir -p $folder")
        val (output, input) = instrumentation.uiAutomation.executeShellCommandRw("dd of=$file")
        ParcelFileDescriptor.AutoCloseOutputStream(input).use { it.write(png) }
        // dd has ended once its output closes.
        ParcelFileDescriptor.AutoCloseInputStream(output).use { it.readBytes() }
        assertEquals("$file written", png.size.toString(), shell("stat -c %s $file").trim())
        return screen
    }
}

/** Edge to edge with the bars' icons for the app's appearance, without scrim, as MainActivity's `systemBars`. */
internal fun ComponentActivity.edgeToEdge(dark: Boolean) {
    val style = if (dark) SystemBarStyle.dark(Color.TRANSPARENT) else SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
    enableEdgeToEdge(statusBarStyle = style, navigationBarStyle = style)
}

/**
 * The preferences files a test made, the fakes' and the demo's, which a device keeps on disk where Robolectric forgets
 * them: deleted once its window is gone, whose stop wrote what they had waiting.
 */
internal class MadePreferences : ExternalResource() {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val folder = File(context.dataDir, "shared_prefs")
    private var before = emptySet<String>()

    private fun names(): Set<String> = folder.list().orEmpty().filter { it.endsWith(".xml") }.map { it.removeSuffix(".xml") }.toSet()

    override fun before() {
        before = names()
    }

    override fun after() {
        for (made in names() - before) context.deleteSharedPreferences(made)
    }
}
