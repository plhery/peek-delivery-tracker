package com.plhery.peekdeliverytracker

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Looper
import android.view.WindowInsetsController
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.ShareInbox
import com.plhery.peekdeliverytracker.core.SharedContent
import com.plhery.peekdeliverytracker.core.UserDefaults
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.SHARE_CARD
import com.plhery.peekdeliverytracker.ui.SHARE_CLOSE
import com.plhery.peekdeliverytracker.ui.SHARE_SAVE
import com.plhery.peekdeliverytracker.ui.SHARE_STATUS
import com.plhery.peekdeliverytracker.ui.SHARE_TITLE
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * "Add to Peek" (B-S70, B-ST36, A-L08, B-SE10): the share sheet offers it for plain text, under its name in each of the
 * app's languages, with the app's icon, in a task Recents doesn't keep; its card reads the text and its subject, says
 * what it found, saves it where the app's window takes it, and goes back to the app that shared, saved or not, by its
 * Cancel or Done, the back or a tap beside it, a turn on the way down included. Its words, and its window's name, are
 * the app's language's. On synthetic numbers, the drafts in memory.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ShareActivityTest {
    @get:Rule
    val compose = createEmptyComposeRule()

    private val app = ApplicationProvider.getApplicationContext<PeekApplication>()
    private val defaults = MemoryDefaults()
    private val english = ExtensionLocalizer("en")
    private val windows = mutableListOf<ActivityController<ShareActivity>>()

    @Before
    fun inMemory() {
        app.shareDefaults = { defaults }
    }

    /** Every card the test opened gone, and its composition let finish, so none waits on in the tests after this one. */
    @After
    fun closeWindows() {
        windows.forEach { it.close() }
        shadowOf(Looper.getMainLooper()).idle()
    }

    private fun share(text: String? = null, subject: String? = null, type: String = "text/plain") = Intent(Intent.ACTION_SEND).apply {
        setType(type)
        text?.let { putExtra(Intent.EXTRA_TEXT, it) }
        subject?.let { putExtra(Intent.EXTRA_SUBJECT, it) }
    }

    /** The card for `intent`, up. */
    private fun open(intent: Intent): ActivityController<ShareActivity> =
        Robolectric.buildActivity(ShareActivity::class.java, intent).also { windows += it }.setup().also { settle() }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun text(tag: String): String? =
        compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text)?.joinToString()

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag, useUnmergedTree = true).performClick()
        settle()
    }

    @Test
    fun theShareSheetOffersItForPlainTextUnderItsNameWithTheAppsIconInATaskRecentsKeepsNot() {
        fun takers(type: String) = app.packageManager.queryIntentActivities(share("TESTLAMP0001", type = type).setPackage(app.packageName), 0)
            .map { it.activityInfo.name }
        assertEquals(listOf(ShareActivity::class.java.name), takers("text/plain"))
        assertEquals(emptyList<String>(), takers("image/png"))
        val info = app.packageManager.getActivityInfo(ComponentName(app, ShareActivity::class.java), 0)
        assertTrue(info.exported)
        assertEquals(R.string.share_title, info.labelRes)
        assertEquals("No icon of its own", 0, info.icon)
        assertEquals("The app's icon", R.mipmap.ic_launcher, info.iconResource)
        val taker = app.packageManager.queryIntentActivities(share("TESTLAMP0001").setPackage(app.packageName), 0).single()
        assertEquals("The share sheet's row shows the app's icon", R.mipmap.ic_launcher, taker.iconResource)
        assertEquals(R.style.Theme_Peek_Share, info.theme)
        assertTrue("Left out of Recents", info.flags and ActivityInfo.FLAG_EXCLUDE_FROM_RECENTS != 0)
        assertFalse("Kept over the app that shared", info.flags and ActivityInfo.FLAG_NO_HISTORY != 0)
        assertTrue("No affinity for the app's task: ${info.taskAffinity}", info.taskAffinity.isNullOrEmpty())
    }

    /** The Android side of B-T176: the share target's name, written at build time, is the shared copy's in each language. */
    @Test
    fun itsNameIsAddToPeekInEachLanguageTheAppSpeaks() {
        for (language in AppLanguage.entries) {
            val configuration = Configuration(app.resources.configuration).apply { setLocale(language.locale) }
            val name = app.createConfigurationContext(configuration).getString(R.string.share_title)
            assertEquals(language.rawValue, ExtensionLocalizer(language.rawValue).text("shareExtension.title"), name)
        }
        assertEquals("Add to Peek", app.getString(R.string.share_title))
    }

    @Test
    fun itReadsTheTextAndItsSubjectTheTextAloneALinkAndNothingInAnythingElse() {
        val link = "https://carrier.example/track?n=TESTLAMP0001"
        assertEquals(SharedContent.Read("Desk lamp", "TESTLAMP0001"), ShareActivity.sharedContent(share(" TESTLAMP0001\n", subject = " Desk lamp ")))
        assertEquals(SharedContent.Read("", "TESTLAMP0001"), ShareActivity.sharedContent(share("TESTLAMP0001")))
        assertEquals(SharedContent.Read("", link), ShareActivity.sharedContent(share(link, subject = " ")))
        assertNull(ShareActivity.sharedContent(share(" \n", subject = "Desk lamp")))
        assertNull(ShareActivity.sharedContent(share(subject = "Desk lamp")))
        assertNull(ShareActivity.sharedContent(Intent(Intent.ACTION_VIEW).putExtra(Intent.EXTRA_TEXT, "TESTLAMP0001")))
        assertNull(ShareActivity.sharedContent(null))
    }

    @Test
    fun textWithASubjectIsReadyToSaveThenSavedWhereTheWindowTakesItAndDoneGoesBackSaved() {
        val window = open(share("Your parcel TESTLAMP0001 is on its way", subject = "Desk lamp"))
        assertTrue(exists(SHARE_CARD))
        assertEquals("Add to Peek", text(SHARE_TITLE))
        assertEquals("Add to Peek", window.get().title.toString())
        assertEquals(english.text("shareExtension.ready"), text(SHARE_STATUS))
        compose.onNodeWithTag(SHARE_SAVE).assertIsEnabled()
        assertEquals(english.text("common.cancel"), text(SHARE_CLOSE))

        tap(SHARE_SAVE)
        assertEquals(english.text("shareExtension.saved"), text(SHARE_STATUS))
        assertFalse("Save goes", exists(SHARE_SAVE))
        assertEquals(english.text("native.done"), text(SHARE_CLOSE))
        assertFalse(window.get().isFinishing)

        tap(SHARE_CLOSE)
        assertTrue(window.get().isFinishing)
        assertEquals(Activity.RESULT_OK, shadowOf(window.get()).resultCode)
        val draft = ShareInbox.consume(defaults)
        assertEquals("Desk lamp", draft?.label)
        assertEquals("Your parcel TESTLAMP0001 is on its way", draft?.trackingInput)
    }

    @Test
    fun aLinkAloneIsReadyWithNoName() {
        open(share("https://carrier.example/track?n=TESTLAMP0001"))
        assertEquals(english.text("shareExtension.ready"), text(SHARE_STATUS))
        tap(SHARE_SAVE)
        val draft = ShareInbox.consume(defaults)
        assertEquals("", draft?.label)
        assertEquals("https://carrier.example/track?n=TESTLAMP0001", draft?.trackingInput)
    }

    @Test
    fun emptyTextOrNoneFindsNothingToSaveAndCancelGoesBackWithNothingSaved() {
        for (intent in listOf(share(" \n "), share(subject = "Desk lamp"))) {
            val window = open(intent)
            assertEquals(english.text("shareExtension.notFound"), text(SHARE_STATUS))
            compose.onNodeWithTag(SHARE_SAVE).assertIsNotEnabled()
            tap(SHARE_SAVE)
            assertEquals(english.text("shareExtension.notFound"), text(SHARE_STATUS))
            tap(SHARE_CLOSE)
            assertTrue(window.get().isFinishing)
            assertEquals(Activity.RESULT_CANCELED, shadowOf(window.get()).resultCode)
            assertFalse(defaults.hasObject(forKey = ShareInbox.key))
        }
    }

    @Test
    fun cancelGoesBackWithNothingSaved() {
        val window = open(share("TESTLAMP0001"))
        tap(SHARE_CLOSE)
        assertTrue(window.get().isFinishing)
        assertEquals(Activity.RESULT_CANCELED, shadowOf(window.get()).resultCode)
        assertFalse(defaults.hasObject(forKey = ShareInbox.key))
    }

    /** The system's back. */
    private fun back(window: ActivityController<ShareActivity>) {
        window.get().onBackPressedDispatcher.onBackPressed()
        settle()
    }

    /** A tap on the app that shared, dimmed beside the card's form sheet in a regular window. */
    private fun besideTheCard() {
        compose.onRoot().performTouchInput { click(Offset(40.dp.toPx(), centerY)) }
        settle()
    }

    /** Each way down but Cancel and Done: before Save it goes back with nothing saved, after it as Done does. */
    private fun goesBackAsCancelOrDone(close: (ActivityController<ShareActivity>) -> Unit) {
        val unsaved = open(share("TESTLAMP0001", subject = "Desk lamp"))
        close(unsaved)
        assertTrue(unsaved.get().isFinishing)
        assertEquals(Activity.RESULT_CANCELED, shadowOf(unsaved.get()).resultCode)
        assertFalse(defaults.hasObject(forKey = ShareInbox.key))
        unsaved.pause().stop().destroy()

        val saved = open(share("TESTLAMP0001", subject = "Desk lamp"))
        tap(SHARE_SAVE)
        close(saved)
        assertTrue(saved.get().isFinishing)
        assertEquals(Activity.RESULT_OK, shadowOf(saved.get()).resultCode)
        assertEquals("TESTLAMP0001", ShareInbox.consume(defaults)?.trackingInput)
    }

    @Test
    fun theBackGoesBackToTheAppThatSharedAsCancelBeforeSaveAndAsDoneAfter() = goesBackAsCancelOrDone(::back)

    @Test
    @Config(qualifiers = TABLET)
    fun aTapBesideTheCardGoesBackToTheAppThatSharedAsCancelBeforeSaveAndAsDoneAfter() = goesBackAsCancelOrDone { besideTheCard() }

    /** The window's bar icons, light where its appearance asks dark icons, and whether the system scrims the buttons. */
    private fun bars(window: Activity): Triple<Boolean, Boolean, Boolean> {
        val appearance = checkNotNull(window.window.insetsController).systemBarsAppearance
        return Triple(
            appearance and WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS != 0,
            appearance and WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS != 0,
            window.window.isNavigationBarContrastEnforced,
        )
    }

    /**
     * X-AD04: on a dark phone, the status bar's icons light over the dimmed app; the buttons' dark over the light card
     * where its sheet reaches the window's foot, with no scrim under them, as the iPhone's sheet has none.
     */
    @Test
    fun onADarkPhoneTheButtonsAreDarkOverTheCardWithNoScrim() {
        RuntimeEnvironment.setQualifiers("+night")
        val window = open(share("TESTLAMP0001"))
        assertEquals(Triple(false, true, false), bars(window.get()))
    }

    /**
     * X-AD04: beside a regular window's form sheet the buttons are over the dimmed app, for the phone's appearance, set
     * again as the phone goes dark under the card.
     */
    @Test
    @Config(qualifiers = TABLET)
    fun besideTheFormSheetTheButtonsFollowThePhoneWithNoScrim() {
        val window = open(share("TESTLAMP0001"))
        val shown = window.get()
        assertEquals(Triple(true, true, false), bars(shown))
        val night = Configuration(shown.resources.configuration).apply {
            uiMode = (uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or Configuration.UI_MODE_NIGHT_YES
        }
        window.configurationChange(night)
        settle()
        assertEquals(Triple(false, false, false), bars(shown))
    }

    @Test
    fun aTurnAsTheCardGoesDownDoesntBringItBackAndItStillGoesBack() {
        val window = open(share("TESTLAMP0001"))
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(SHARE_CLOSE, useUnmergedTree = true).performClick()
        compose.mainClock.advanceTimeBy(100)
        assertFalse("On its way down", window.get().isFinishing)
        window.recreate()
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(window.get().isFinishing)
        assertEquals(Activity.RESULT_CANCELED, shadowOf(window.get()).resultCode)
        assertFalse("Not back", exists(SHARE_CARD))
        assertFalse(defaults.hasObject(forKey = ShareInbox.key))
    }

    @Test
    fun aSaveThatDoesNotReadBackSaysSoAndCanBeTriedAgain() {
        var keeps = false
        val flaky = object : UserDefaults by defaults {
            override fun set(value: ByteArray, forKey: String) {
                if (keeps) defaults.set(value, forKey)
            }
        }
        app.shareDefaults = { flaky }
        open(share("TESTLAMP0001"))
        tap(SHARE_SAVE)
        assertEquals(english.text("shareExtension.saveFailed"), text(SHARE_STATUS))
        compose.onNodeWithTag(SHARE_SAVE).assertIsEnabled()
        assertEquals(english.text("common.cancel"), text(SHARE_CLOSE))
        keeps = true
        tap(SHARE_SAVE)
        assertEquals(english.text("shareExtension.saved"), text(SHARE_STATUS))
        assertEquals("TESTLAMP0001", ShareInbox.consume(defaults)?.trackingInput)
    }

    @Test
    fun savedItStaysSavedThroughTheWindowMadeAgain() {
        val window = open(share("TESTLAMP0001"))
        tap(SHARE_SAVE)
        ShareInbox.consume(defaults)
        window.recreate()
        settle()
        assertEquals(english.text("shareExtension.saved"), text(SHARE_STATUS))
        assertFalse(exists(SHARE_SAVE))
        assertFalse("Not saved again", defaults.hasObject(forKey = ShareInbox.key))
    }

    @Test
    fun itSpeaksTheAppsLanguage() {
        defaults.set("fr", forKey = AppLanguage.storageKey)
        val window = open(share("TESTLAMP0001"))
        val french = ExtensionLocalizer("fr")
        assertEquals("Ajouter à Peek", text(SHARE_TITLE))
        // The window's name, which TalkBack reads first, whatever the phone's language names the share target.
        assertEquals("Ajouter à Peek", window.get().title.toString())
        assertEquals(french.text("shareExtension.ready"), text(SHARE_STATUS))
        assertEquals(french.text("common.cancel"), text(SHARE_CLOSE))
    }
}

/** A regular window, where the card is a form sheet in its middle. */
private const val TABLET = "w1032dp-h1376dp-xhdpi"
