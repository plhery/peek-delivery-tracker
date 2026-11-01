package com.plhery.peekdeliverytracker.ui.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.click
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.core.content.IntentCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import com.plhery.peekdeliverytracker.core.NotificationPreset
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.DEVICE_ACCOUNT_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.appearanceSetting
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import java.io.IOException
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Settings (C-S35 to C-S45, A-S19): opened from the account button for an account, a guest and the demo, each row's
 * action, the appearance and the language, the data page's actions with their alerts, one at a time and closing as the
 * iPhone's do, the error under the data, Close and the back while it waits, the pages pushed and the back, Delivery
 * updates' presets, switch and email, and the guest's offer of an account in the list.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class SettingsTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    @After
    fun forgetAppearance() {
        AppearanceStore.of(compose.activity).set(AppAppearance.DEFAULT)
    }

    private fun show(session: AuthenticationState = reader, view: View? = null, prepare: FakeDeliveries.() -> Unit = {}) {
        source = FakeDeliveries(DemoCards.parcels(), session).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            val root: @Composable () -> Unit = {
                CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                    // The window's theme follows the setting, as MainActivity's does.
                    RootHost(state = session, dark = appearanceSetting().isDark(systemDark = false), source = source)
                }
            }
            if (view != null) CompositionLocalProvider(LocalView provides view, content = root) else root()
        }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun text(key: String, variables: Map<String, Any> = emptyMap()) = localizer.text(key, variables)

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun shows(text: String) = compose.onAllNodes(hasText(text)).fetchSemanticsNodes().isNotEmpty()

    private fun tap(tag: String) {
        node(tag).performScrollTo().performClick()
        settle()
    }

    /** A bar's button, which stays where it is as the page scrolls. */
    private fun press(tag: String) {
        node(tag).performClick()
        settle()
    }

    /** The switch at a row's end, as only the switch takes a tap in a Form's toggle row. */
    private fun flip(tag: String) {
        node(tag).performScrollTo().performTouchInput { click(Offset(width - 40.dp.toPx(), height / 2f)) }
        settle()
    }

    private fun open() {
        compose.runOnUiThread { deliveries.account() }
        settle()
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    /** How light Settings' ground is, at its leading margin halfway down. */
    private fun shade(): Float {
        val pixels = node(SETTINGS_SHEET).captureToImage().toPixelMap()
        val color = pixels[3, pixels.height / 2]
        return (color.red + color.green + color.blue) / 3
    }

    private fun started(): Intent? = shadowOf(compose.activity).nextStartedActivity

    @Test
    fun theAccountButtonOpensSettingsForTheAccountAndCloseClosesIt() {
        show()
        node(ACCOUNT_BUTTON).performClick()
        settle()
        assertTrue(deliveries.showingAccount)
        assertEquals("account", source.views.last())
        assertTrue(exists(SETTINGS_SHEET))
        assertTrue(shows("reader@example.com"))
        assertTrue(shows(text("account.signedIn")))
        assertFalse(exists(SETTINGS_SIGN_IN))
        // Delivery updates for an account, off while the device has no push.
        assertTrue(shows(text("settings.off")))
        assertTrue(source.accountCalls.containsAll(listOf("refreshNotificationState", "loadNotificationPreferences")))
        assertTrue(shows(text("settings.version", mapOf("version" to "1.0"))))
        // The groups' titles are read as one each.
        assertTrue(shows(text("settings.preferences").uppercase()))
        press(SETTINGS_CLOSE)
        assertFalse(deliveries.showingAccount)
        assertFalse(exists(SETTINGS_SHEET))
        assertEquals("deliveries", source.views.last())
    }

    @Test
    fun aGuestWithParcelsIsOfferedAnAccountInTheListAndInSettings() {
        show(AuthenticationState.Welcome) { isGuest = true }
        node(PARCEL_LIST).performScrollToKey("deviceAccount")
        settle()
        compose.onNode(hasTestTag(DEVICE_ACCOUNT_ROW) and hasText(text("link.account.title"))).performClick()
        settle()
        assertEquals(1, source.signIns)

        open()
        assertFalse(exists(SETTINGS_DELIVERY_UPDATES))
        assertFalse(exists(SETTINGS_ACCOUNT))
        tap(SETTINGS_ACCOUNT_DATA)
        assertTrue(shows(text("peek.onThisDevice")))
        assertTrue(shows(text("native.guest.kept")))
        assertTrue(exists(SETTINGS_FORGET_ALL))
        assertFalse(exists(SETTINGS_SIGN_OUT))
        back()
        tap(SETTINGS_SIGN_IN)
        assertEquals(2, source.signIns)
        assertFalse(deliveries.showingAccount)
    }

    @Test
    fun aGuestWithoutParcelsHasNothingToForgetAndNoOfferInTheList() {
        show(AuthenticationState.Welcome) {
            isGuest = true
            parcels.value = emptyList()
        }
        assertTrue(compose.onAllNodes(hasTestTag(DEVICE_ACCOUNT_ROW) and hasText(text("link.account.title"))).fetchSemanticsNodes().isEmpty())
        open()
        assertTrue(exists(SETTINGS_SIGN_IN))
        tap(SETTINGS_ACCOUNT_DATA)
        assertFalse(exists(SETTINGS_FORGET_ALL))
        assertTrue(exists(SETTINGS_EXPORT))
    }

    @Test
    fun forgetAllAsksWithTheCountAndLeavesTheSheetOpen() {
        show(AuthenticationState.Welcome) { isGuest = true }
        val count = source.parcels.value.size
        open()
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_FORGET_ALL)
        assertTrue(shows(text("door.recents.forget.many", mapOf("count" to count))))
        tap(SETTINGS_ALERT_CANCEL)
        assertFalse(exists(SETTINGS_ALERT))
        assertEquals(emptyList<String>(), source.accountCalls.filter { it == "forgetDeviceParcels" })
        tap(SETTINGS_FORGET_ALL)
        tap(SETTINGS_ALERT_ACTION)
        assertTrue("forgetDeviceParcels" in source.accountCalls)
        assertTrue(deliveries.showingAccount)
        assertFalse(exists(SETTINGS_FORGET_ALL))
    }

    @Test
    fun theDemoResetsWithItsAlertAndExitsClosingTheSheet() {
        show(AuthenticationState.Demo)
        open()
        assertTrue(shows(text("app.demo")))
        assertFalse(shows(text("account.signedIn")))
        tap(SETTINGS_ACCOUNT_DATA)
        assertTrue(shows(text("settings.demoData")))
        tap(SETTINGS_RESET_DEMO)
        assertTrue(shows(text("native.resetDemoQuestion")))
        assertTrue(shows(text("native.resetDemoDescription")))
        tap(SETTINGS_ALERT_ACTION)
        assertTrue("resetDemoData" in source.accountCalls)
        assertFalse(deliveries.showingAccount)

        open()
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_EXIT_DEMO)
        assertEquals(1, source.welcomes)
        assertFalse(deliveries.showingAccount)
    }

    @Test
    fun signingOutClosesTheSheet() {
        show()
        open()
        tap(SETTINGS_ACCOUNT_DATA)
        assertTrue(shows("reader@example.com"))
        tap(SETTINGS_SIGN_OUT)
        assertEquals(listOf("signOut"), source.accountCalls.filter { it == "signOut" })
        assertFalse(deliveries.showingAccount)
    }

    @Test
    fun deletingWaitsForTheAddressTypedThenClosesTheSheet() {
        show()
        open()
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_DELETE)
        assertTrue(shows(text("account.deleteQuestion")))
        assertTrue(shows(text("account.deleteDescription")))
        node(SETTINGS_ALERT_ACTION).assertIsNotEnabled()
        node(SETTINGS_ALERT_FIELD).performTextReplacement("reader@example")
        settle()
        node(SETTINGS_ALERT_ACTION).assertIsNotEnabled()
        // As `cleaned` compares them: without the spaces around it, whatever its case.
        node(SETTINGS_ALERT_FIELD).performTextReplacement("  Reader@Example.com ")
        settle()
        node(SETTINGS_ALERT_ACTION).assertIsEnabled()
        tap(SETTINGS_ALERT_ACTION)
        assertTrue("deleteAccount:  Reader@Example.com " in source.accountCalls)
        assertFalse(exists(SETTINGS_ALERT))
        assertFalse(deliveries.showingAccount)
    }

    @Test
    fun oneRunAtATimeWithCloseAndTheBackOffThenTheErrorUnderTheData() {
        show { accountWork = CompletableDeferred() }
        open()
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_EXPORT)
        tap(SETTINGS_EXPORT)
        assertEquals(1, source.accountCalls.count { it == "exportAccount" })
        assertTrue(exists(SETTINGS_WORKING))
        node(SETTINGS_BACK).assertIsNotEnabled()
        back()
        assertTrue(exists(SETTINGS_EXPORT))
        assertTrue(deliveries.showingAccount)

        source.accountFailure = IOException("The export failed.")
        compose.runOnUiThread { source.accountWork!!.complete(Unit) }
        settle()
        assertFalse(exists(SETTINGS_WORKING))
        assertEquals("The export failed.", node(SETTINGS_ERROR).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text)?.joinToString())
        assertNull(started())
        assertTrue(deliveries.showingAccount)
    }

    @Test
    fun theSheetStaysWhileSigningOutWaitsThenCloses() {
        show { accountWork = CompletableDeferred() }
        open()
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_SIGN_OUT)
        back()
        assertTrue(exists(SETTINGS_WORKING))
        node(SETTINGS_BACK).assertIsNotEnabled()
        assertTrue(deliveries.showingAccount)
        compose.runOnUiThread { source.accountWork!!.complete(Unit) }
        settle()
        assertFalse(deliveries.showingAccount)
    }

    @Test
    fun theExportGoesToTheShareSheetAsAFileTheChosenAppMayRead() {
        show()
        open()
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_EXPORT)
        val chooser = started()
        assertNotNull(chooser)
        assertEquals(Intent.ACTION_CHOOSER, chooser!!.action)
        val send = IntentCompat.getParcelableExtra(chooser, Intent.EXTRA_INTENT, Intent::class.java)!!
        assertEquals(Intent.ACTION_SEND, send.action)
        val uri = IntentCompat.getParcelableExtra(send, Intent.EXTRA_STREAM, Uri::class.java)!!
        assertEquals("content", uri.scheme)
        assertEquals("${compose.activity.packageName}.exports", uri.authority)
        assertTrue(uri.toString().endsWith("peek-export-2026-05-01.json"))
        assertTrue(send.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)
        // The sheet stays open, as iOS's share sheet stands over it.
        assertTrue(deliveries.showingAccount)
    }

    @Test
    fun theAppearanceIsStoredWithATickAndCounted() {
        val view = FeelingView(compose.activity)
        show(view = view)
        open()
        val dark = node(appearanceTag(AppAppearance.Dark))
        assertEquals(false, dark.fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        assertEquals(true, node(appearanceTag(AppAppearance.Light)).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        val light = shade()
        tap(appearanceTag(AppAppearance.Dark))
        assertTrue("$light then ${shade()}", light > 0.8f && shade() < 0.2f)
        assertEquals(AppAppearance.Dark, AppearanceStore.of(compose.activity).appearance.value)
        assertEquals(true, node(appearanceTag(AppAppearance.Dark)).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        assertEquals(listOf(hapticFeedbackConstant(SensoryFeedback.Selection)), view.played)
        assertEquals(listOf("appearance-change"), source.actions.filter { it == "appearance-change" })
    }

    @Test
    fun theLanguageMenuChangesTheCopy() {
        show()
        open()
        assertTrue(shows("Settings"))
        tap(SETTINGS_LANGUAGE)
        tap(settingsLanguageTag(AppLanguage.Fr))
        assertTrue(shows(Localizer(AppLanguage.Fr, variant = "android").text("settings.title")))
        assertTrue(deliveries.showingAccount)
    }

    @Test
    fun pagesPushAndTheBackPopsThemBeforeClosingTheSheet() {
        show { signedInWithEmail(choice = false) }
        open()
        tap(SETTINGS_ACCOUNT_DATA)
        assertTrue(exists(SETTINGS_EXPORT))
        back()
        assertFalse(exists(SETTINGS_EXPORT))
        assertTrue(deliveries.showingAccount)
        tap(SETTINGS_DELIVERY_UPDATES)
        assertTrue(exists(presetTag(NotificationPreset.All)))
        press(SETTINGS_BACK)
        assertFalse(exists(presetTag(NotificationPreset.All)))
        back()
        assertFalse(deliveries.showingAccount)
        assertEquals("deliveries", source.views.last())
    }

    @Test
    fun deliveryUpdatesSavesAPresetAndTheDeviceSwitchSaysWhetherTheWelcomeWentOut() {
        show { signedInWithEmail(choice = false, stages = NotificationPreset.Important.stages) }
        open()
        assertTrue(shows(text("settings.off")))
        tap(SETTINGS_DELIVERY_UPDATES)
        assertTrue(shows(text("notifications.state.prompt")))
        assertEquals(true, node(presetTag(NotificationPreset.Important)).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        tap(presetTag(NotificationPreset.DeliveryDay))
        tap(SETTINGS_SAVE)
        assertEquals(NotificationPreset.DeliveryDay.stages, source.savedPreferences.single().enabledStages)
        assertEquals(text("notifications.saved"), node(SETTINGS_NOTICE).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text)?.joinToString())

        source.welcomeSent = false
        flip(SETTINGS_DEVICE_SWITCH)
        assertTrue("enableNotifications" in source.accountCalls)
        assertTrue(shows(text("notifications.state.enabled")))
        assertTrue(shows(text("notifications.error.welcome")))
        press(SETTINGS_BACK)
        assertTrue(shows(text(NotificationPreset.DeliveryDay.titleKey)))
    }

    @Test
    fun theDeliveryEmailSwitchSavesAndItsExampleOpens() {
        show { signedInWithEmail(choice = false) }
        open()
        tap(SETTINGS_DELIVERY_UPDATES)
        flip(SETTINGS_DELIVERY_EMAIL)
        assertEquals(listOf(true), source.emailChoices)
        assertEquals(DeliveryAnalytics.Outcome.Success, source.outcomes.last())
        assertEquals("email-delivery-change", source.actions.last())
        tap(SETTINGS_DELIVERY_EMAIL_EXAMPLE)
        assertEquals("email-example-open", source.actions.last())
        assertEquals("https://example.com/email/example?lang=en", started()?.dataString)
        tap(SETTINGS_DELIVERY_EMAIL_PRIVACY)
        assertEquals(source.privacyUrl, started()?.dataString)
    }

    @Test
    fun aFailedEmailChoiceSaysSo() {
        show {
            signedInWithEmail(choice = false)
            failure = IOException("offline")
        }
        open()
        tap(SETTINGS_DELIVERY_UPDATES)
        flip(SETTINGS_DELIVERY_EMAIL)
        assertTrue(exists(SETTINGS_DELIVERY_EMAIL_ERROR))
        assertEquals(DeliveryAnalytics.Outcome.Error, source.outcomes.last())
    }

    @Test
    fun blockedNotificationsOpenAndroidsSettings() {
        show {
            signedInWithEmail(choice = null)
            notificationStatus.value = NotificationAuthorizationStatus.Denied
        }
        open()
        tap(SETTINGS_DELIVERY_UPDATES)
        assertTrue(shows(text("notifications.state.blocked")))
        assertTrue(shows(text("native.openSettings")))
        assertFalse(exists(SETTINGS_DEVICE_SWITCH))
        tap(SETTINGS_OPEN_SETTINGS)
        val opened = started()!!
        assertEquals(Settings.ACTION_APP_NOTIFICATION_SETTINGS, opened.action)
        assertEquals(compose.activity.packageName, opened.getStringExtra(Settings.EXTRA_APP_PACKAGE))
    }

    @Test
    fun theSiteRowsOpenTheHomePageTheMailAndThePrivacyNotice() {
        show()
        open()
        assertTrue(shows("example.com"))
        tap(SETTINGS_HOME_PAGE)
        assertEquals(source.homePageUrl, started()?.dataString)
        tap(SETTINGS_FEEDBACK)
        val mail = started()?.dataString.orEmpty()
        assertTrue(mail, mail.startsWith("mailto:hello@peektracker.com") && "1.0" in Uri.decode(mail))
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_PRIVACY)
        assertEquals(source.privacyUrl, started()?.dataString)
    }
}

/** A window's view that keeps the ticks it is asked to play. */
private class FeelingView(context: Context) : View(context) {
    val played = mutableListOf<Int>()

    override fun performHapticFeedback(feedbackConstant: Int): Boolean {
        played += feedbackConstant
        return true
    }
}
