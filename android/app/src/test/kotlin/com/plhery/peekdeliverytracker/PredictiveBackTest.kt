package com.plhery.peekdeliverytracker

import androidx.test.ext.junit.runners.AndroidJUnit4
import java.io.File
import java.util.Properties
import javax.xml.parsers.DocumentBuilderFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.w3c.dom.Document
import org.w3c.dom.Element

/**
 * X-AD01: the back is predictive on every version that has it, as it is by default from Android 16. The application
 * opts in, so that the system's gesture drives the windows' OnBackPressedDispatcher on Android 13 to 15 too, and no
 * window takes the back by itself, which the system would no longer send it.
 */
@RunWith(AndroidJUnit4::class)
class PredictiveBackTest {
    private val android = "http://schemas.android.com/apk/res/android"

    /**
     * The manifest the app is built with, the libraries' merged in, as the unit tests' configuration names it (on the
     * test's own class path, outside Robolectric's).
     */
    private fun mergedManifest(): Document {
        val config = Properties().apply {
            ClassLoader.getSystemResourceAsStream("com/android/tools/test_config.properties")!!.use(::load)
        }
        val manifest = File(System.getProperty("user.dir")).resolve(config.getProperty("android_merged_manifest"))
        return DocumentBuilderFactory.newInstance().apply { isNamespaceAware = true }.newDocumentBuilder().parse(manifest)
    }

    @Test
    fun theApplicationTakesThePredictiveBack() {
        val application = mergedManifest().getElementsByTagName("application").item(0) as Element
        assertEquals("true", application.getAttributeNS(android, "enableOnBackInvokedCallback"))
    }

    /** None of the app's windows overrides the old back or reads the back key: Compose's back handlers take it. */
    @Test
    fun noWindowTakesTheBackByItself() {
        val activities = mergedManifest().getElementsByTagName("activity")
        val windows = (0 until activities.length).map { (activities.item(it) as Element).getAttributeNS(android, "name") }
            .filter { it.startsWith("com.plhery.peekdeliverytracker.") }
        assertTrue(windows.toString(), "com.plhery.peekdeliverytracker.MainActivity" in windows)
        val back = setOf("onBackPressed", "onKeyDown", "onKeyUp", "dispatchKeyEvent")
        for (window in windows) {
            val taken = Class.forName(window).declaredMethods.map { it.name }.filter { it in back }
            assertEquals(window, emptyList<String>(), taken)
        }
    }
}
