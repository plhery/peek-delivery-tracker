package com.plhery.peekdeliverytracker.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.os.ParcelFileDescriptor
import android.os.storage.StorageManager
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.Density
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.Parcel
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkStopped
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.ui.deliveries.FakeParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_ADD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_BOX
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_FAILED
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_FOLLOW
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_GIFT_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_GIFT_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_NOTHING
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_PIP
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_ROUTE
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SIGN_IN
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SURPRISE
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.pinnedIdentities
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith

/**
 * A parcel's link in its sheet over the demo's list on this device's own window, its bars and density the device's
 * (B-S41-B-S50, B-AN20, B-AN32, B-AX05, B-AX15, B-G09, B-G12, B-L03): each phase ParcelLinkScreenshotTest draws with
 * Robolectric's, its words or parts checked once what it plays on showing has settled, the box's unwrapping too, then
 * the whole screen saved as linkwalk/<test>.png in the test package's external files, which adb pulls. On the sheet's
 * stores in memory ([FakeParcelLinks]), which answer every read: nothing goes to the network. Each test keeps Zurich's
 * time as the JVM's do and puts the zone back after; the largest text is its composition's own Density, not the
 * phone's setting; the preferences files the fakes write go once its window has.
 */
@RunWith(AndroidJUnit4::class)
class ParcelLinkSheetDeviceShots {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val name = TestName()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(name).around(MadePreferences()).around(ZurichTime()).around(compose)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val localizer = Localizer(AppLanguage.En, variant = "android")

    /** The test package's external files, as its own context names them: the app's process may not write there. */
    private val folder: File = File(
        checkNotNull(instrumentation.targetContext.getSystemService(StorageManager::class.java).primaryStorageVolume.directory),
        "Android/data/${instrumentation.context.packageName}/files/linkwalk",
    )

    private fun text(key: String) = localizer.text(key)

    /** The link `answer` reads as, its sheet up over the list as `state` sees it, edge to edge as the app's window. */
    private fun show(
        answer: suspend () -> PublicParcelResponse?,
        state: AuthenticationState = AuthenticationState.Welcome,
        dark: Boolean = false,
        fontScale: Float = 1f,
    ) {
        val links = FakeParcelLinks(session = state).apply { read = { _, _ -> answer() } }
        compose.waitsForMaps()
        compose.runOnUiThread {
            // The bars' icons for the app's appearance, without scrim, as MainActivity's `systemBars`.
            val style = if (dark) SystemBarStyle.dark(Color.TRANSPARENT) else SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
            compose.activity.enableEdgeToEdge(statusBarStyle = style, navigationBarStyle = style)
        }
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                RootHost(state = state, dark = dark, reduceMotion = false, identities = pinnedIdentities, links = links)
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = LinkFixtures.viewer().link.id)) }
        // Read, then the sheet's rise, the route's entrance and the box's unwrapping (0.25 s, then 0.9 s) played out.
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(SETTLE_MILLIS)
        compose.waitForIdle()
    }

    /** Whether the sheet holds a part tagged `tag`. */
    private fun exists(tag: String) =
        compose.onAllNodes(hasTestTag(tag) and hasAnyAncestor(hasTestTag(LINK_SHEET)), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** Whether the sheet says `text`. */
    private fun shows(text: String) =
        compose.onAllNodes(hasText(text, substring = true) and hasAnyAncestor(hasTestTag(LINK_SHEET)), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** One heading of the sheet reads `text`. */
    private fun assertHeading(text: String) = assertEquals(
        text, 1,
        compose.onAllNodes(isHeading() and hasText(text) and hasAnyAncestor(hasTestTag(LINK_SHEET)), useUnmergedTree = true).fetchSemanticsNodes().size,
    )

    /** Its title, its status as a heading, and the keeping block someone signed out sees. */
    private fun assertSharedParcel(response: PublicParcelResponse) {
        assertTrue(exists(LINK_CARD))
        assertTrue(shows(text("link.shared")))
        assertHeading(localizer.parcelStatus(Parcel(shared = response.parcel)))
    }

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
     * The whole screen, bars and all, as [folder]/<test>.png. Written by the shell: the app's process, where the test
     * runs, may neither make nor write another package's external files (scoped storage), and adb reads them.
     */
    private fun shot() {
        compose.waitForIdle()
        awaitFrames()
        val taken = checkNotNull(instrumentation.uiAutomation.takeScreenshot()) { "No screenshot" }
        val screen = if (taken.config == Bitmap.Config.HARDWARE) taken.copy(Bitmap.Config.ARGB_8888, false) else taken
        val png = ByteArrayOutputStream().use { out ->
            check(screen.compress(Bitmap.CompressFormat.PNG, 100, out))
            out.toByteArray()
        }
        val file = File(folder, "${name.methodName}.png")
        shell("mkdir -p $folder")
        val (output, input) = instrumentation.uiAutomation.executeShellCommandRw("dd of=$file")
        ParcelFileDescriptor.AutoCloseOutputStream(input).use { it.write(png) }
        // dd has ended once its output closes.
        ParcelFileDescriptor.AutoCloseInputStream(output).use { it.readBytes() }
        assertEquals("$file written", png.size.toString(), shell("stat -c %s $file").trim())
    }

    @Test
    fun withARoute() {
        show({ LinkFixtures.routed() })
        assertSharedParcel(LinkFixtures.routed())
        assertTrue(exists(LINK_ROUTE))
        assertTrue(shows(text("native.follow.hint")))
        shot()
    }

    @Test
    fun withARouteInDark() {
        show({ LinkFixtures.routed() }, dark = true)
        assertSharedParcel(LinkFixtures.routed())
        assertTrue(exists(LINK_ROUTE))
        shot()
    }

    @Test
    fun withARouteAtTheLargestTextSize() {
        show({ LinkFixtures.routed() }, fontScale = 2f)
        assertSharedParcel(LinkFixtures.routed())
        assertTrue(exists(LINK_ROUTE))
        assertTrue(shows(text("native.follow.hint")))
        shot()
    }

    @Test
    fun arrived() {
        show({ LinkFixtures.arrived() })
        assertSharedParcel(LinkFixtures.arrived())
        // Its box, opened as it showed, in place of the route and Pip.
        assertTrue(exists(LINK_BOX))
        assertFalse(exists(LINK_ROUTE))
        assertFalse(exists(LINK_PIP))
        shot()
    }

    @Test
    fun withNeitherRouteNorArrival() {
        show({ LinkFixtures.keepable() })
        assertSharedParcel(LinkFixtures.keepable())
        assertTrue(exists(LINK_PIP))
        assertFalse(exists(LINK_ROUTE))
        assertTrue(shows(text("native.follow.hint")))
        assertTrue(exists(LINK_FOLLOW))
        assertTrue(exists(LINK_SIGN_IN))
        shot()
    }

    @Test
    fun keptBySomeoneSignedIn() {
        show({ LinkFixtures.keepable() }, state = FakeParcelLinks.READER)
        assertSharedParcel(LinkFixtures.keepable())
        assertTrue(shows(text("link.addHint")))
        assertTrue(exists(LINK_ADD))
        assertFalse(exists(LINK_FOLLOW))
        shot()
    }

    @Test
    fun aGiftOnItsWay() {
        show({ LinkFixtures.wrappedGift() })
        assertTrue(exists(LINK_GIFT_CARD))
        assertTrue(exists(LINK_BOX))
        assertTrue(exists(LINK_SURPRISE))
        assertHeading(text("share.gift.headline"))
        assertFalse(shows(text("link.shared")))
        assertFalse(shows("6789"))
        assertFalse(exists(LINK_GIFT_NOTE))
        shot()
    }

    /** A gift delivered, its box open: the note, who it is from and what is inside, its number, and Follow. */
    private fun assertGiftDelivered() {
        assertTrue(exists(LINK_BOX))
        assertHeading(text("share.gift.here"))
        assertFalse(exists(LINK_SURPRISE))
        compose.onNodeWithTag(LINK_GIFT_NOTE).assert(hasText("Happy birthday, Alex!", substring = true))
        compose.onNodeWithTag(LINK_GIFT_NOTE).assert(hasText("— Sam", substring = true))
        compose.onNodeWithTag(LINK_GIFT_NOTE).assert(hasText("trail running shoes", substring = true))
        assertTrue(shows("TESTPARCEL123789"))
        assertTrue(exists(LINK_FOLLOW))
    }

    @Test
    fun aGiftDelivered() {
        show({ LinkFixtures.openedGift() })
        assertGiftDelivered()
        shot()
    }

    @Test
    fun aGiftDeliveredInDark() {
        show({ LinkFixtures.openedGift() }, dark = true)
        assertGiftDelivered()
        shot()
    }

    @Test
    fun gone() {
        show({ null })
        assertTrue(exists(LINK_NOTHING))
        assertHeading(text("link.gone.title"))
        assertTrue(shows(text("link.gone.body")))
        shot()
    }

    @Test
    fun stopped() {
        show({ throw ParcelLinkStopped() })
        assertTrue(exists(LINK_NOTHING))
        assertHeading(text("share.stopped.title"))
        assertTrue(shows(text("share.stopped.body")))
        // A stopped link keeps the sheet to itself: no title.
        assertFalse(shows(text("link.shared")))
        shot()
    }

    @Test
    fun failedOffline() {
        show({ throw IOException("offline") })
        assertTrue(exists(LINK_FAILED))
        assertTrue(shows(text("error.connection")))
        shot()
    }

    @Test
    fun failedOtherwise() {
        show({ throw IllegalStateException("broken") })
        assertTrue(exists(LINK_FAILED))
        assertTrue(shows(text("native.error.serviceFailed")))
        shot()
    }

    private companion object {
        /** Long enough for all the sheet plays as it shows, the box's spring and its stars included. */
        const val SETTLE_MILLIS = 3_000L
    }
}

/**
 * The preferences files a test made, the fakes' and the demo's, which a device keeps on disk where Robolectric forgets
 * them: deleted once its window is gone, whose stop wrote what they had waiting.
 */
private class MadePreferences : ExternalResource() {
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
