package com.plhery.peekdeliverytracker.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PASTE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Add's Paste on this device (B-P02): the phone's own clipboard read as the button is tapped, as the iPhone reads
 * `UIPasteboard` on its explicit tap. Android asks no permission for it (from Android 12 the system says the app
 * pasted). A number on the clipboard comes into the field, trimmed; a link's address too; an empty clipboard, or one
 * that holds only a file of the phone's, says "add.pasteFailed" and leaves the field as it was. Each tap counts
 * "parcel-paste". Signed in as the synthetic reader@example.com on fake deliveries ([ParcelWalk]), which reach no
 * server; the clipboard is put back after each test where it held something, and emptied otherwise. Each saved as
 * addpaste/<name>.png in the test package's external files, which adb pulls.
 */
@RunWith(AndroidJUnit4::class)
class AddPasteDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val clipboard = KeptClipboard()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(clipboard).around(walk)

    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")
    private val packageName: String get() = InstrumentationRegistry.getInstrumentation().targetContext.packageName

    /** B-P02: a number on the clipboard, with spaces and a new line round it, comes into the field trimmed. */
    @Test
    fun aNumberOnTheClipboardComesIntoTheFieldTrimmed() {
        openAdd()
        clipboard.put(ClipData.newPlainText("Tracking number", "  $UPS_SAMPLE\n"))
        paste()
        compose.waitUntil(LONG_MILLIS) { field() == UPS_SAMPLE }
        assertFalse(failedShown())
        assertEquals(listOf("parcel-paste"), pastes())
        walk.shot("B-P02-number", alsoDark = false)
    }

    /** B-P02: a link on the clipboard (a URI, no text) comes into the field as its address, which Add reads. */
    @Test
    fun aLinkOnTheClipboardComesInAsItsAddress() {
        openAdd()
        clipboard.put(ClipData.newRawUri("Tracking link", Uri.parse(LINK)))
        paste()
        compose.waitUntil(LONG_MILLIS) { field().isNotEmpty() }
        assertEquals(LINK, field())
        assertFalse(failedShown())
        assertEquals(listOf("parcel-paste"), pastes())
    }

    /**
     * B-P02: an empty clipboard says "add.pasteFailed" under the field and leaves it empty; so does one that holds only
     * a file (`content:`, here the app's own, as Android lets an app put on the clipboard only a file it may read),
     * which the iPhone's `Clipboard.string` has no text for either. Each tap counts.
     */
    @Test
    fun anEmptyClipboardOrALocalFileSaysNothingCouldBePasted() {
        openAdd()
        clipboard.empty()
        paste()
        compose.waitUntil(LONG_MILLIS) { failedShown() }
        assertEquals("", field())
        walk.shot("B-P02-empty", alsoDark = false)

        clipboard.put(ClipData.newRawUri("Photo", Uri.parse("content://$packageName.exports/pictures/parcel.png")))
        paste()
        assertTrue(failedShown())
        assertEquals("", field())
        assertEquals(listOf("parcel-paste", "parcel-paste"), pastes())
    }

    private fun openAdd() {
        walk.show(listOf(lamp))
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        walk.awaitKeyboard(required = false)
        walk.settle()
    }

    private fun paste() {
        walk.node(ADD_PASTE).performClick()
        walk.settle()
    }

    private fun field(): String =
        walk.node(ADD_TRACKING).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text.orEmpty()

    private fun failedShown(): Boolean =
        compose.onAllNodes(hasText(walk.text("add.pasteFailed")), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun pastes(): List<String> = walk.onUi { walk.source.actions.filter { it == "parcel-paste" } }

    /** The phone's clipboard, set on the main thread, as it stood before the test put back after it. */
    private inner class KeptClipboard : ExternalResource() {
        private val manager: ClipboardManager get() =
            InstrumentationRegistry.getInstrumentation().targetContext.getSystemService(ClipboardManager::class.java)
        private var before: ClipData? = null

        override fun before() {
            before = onMain { manager.primaryClip }
        }

        override fun after() {
            val kept = before
            onMain { if (kept != null) manager.setPrimaryClip(kept) else manager.clearPrimaryClip() }
        }

        fun put(clip: ClipData) = onMain { manager.setPrimaryClip(clip) }

        fun empty() = onMain { manager.clearPrimaryClip() }

        private fun <T> onMain(work: () -> T): T {
            var result: Result<T>? = null
            InstrumentationRegistry.getInstrumentation().runOnMainSync { result = runCatching(work) }
            return checkNotNull(result).getOrThrow()
        }
    }

    private companion object {
        const val FOLDER = "addpaste"

        /** The public UPS sample. */
        const val UPS_SAMPLE = "1Z999AA10123456784"

        /** A synthetic tracking link for the UPS sample. */
        const val LINK = "https://www.ups.com/track?tracknum=1Z999AA10123456784"
    }
}
