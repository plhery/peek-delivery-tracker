package com.plhery.peekdeliverytracker.ui

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.tryPerformAccessibilityChecks
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResult.AccessibilityCheckResultType
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityViewCheckResult
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_MENU
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.cardId
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_MARK_NEW_UPDATES
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import java.util.Locale
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * The Accessibility Test Framework's checks (Accessibility Scanner's) on this device's own window, through Compose's
 * accessibility checks, on the app's main screens: the welcome, the first open, the list, a card's menu, a parcel's page
 * (beside the list where the window lays them out side by side) and its menu, Add, and Settings down to the widget's
 * switch. What the checks find in error fails the test, each finding named with its node; what they warn of (the text
 * contrast they estimate from the window's pixels) is written to the log under [TAG]. The checks also run on each action
 * a test performs, so the screens passed on the way are held to them too. Each test runs in the light appearance and in
 * the dark one, each shown from the start: right after a window turns dark, the contrast check still reports the light
 * window's colours.
 */
@RunWith(Parameterized::class)
class AccessibilityChecksDeviceTest(private val appearance: String) {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val demo: List<Parcel> = DemoCards.parcels()
    private val gift: Parcel = DemoCards.parcel("Birthday gift")

    /** The errors found since the last [check], each with the screen it was found on. */
    private val errors = mutableListOf<String>()
    private var screen = "launch"

    /** How many times the checks ran: Compose's skip them where they can't run (Robolectric), saying nothing. */
    @Volatile
    private var runs = 0

    private val validator = AccessibilityValidator()
        .setRunChecksFromRootView(true)
        .setThrowExceptionForErrors(false)
        .addCheckListener(
            AccessibilityValidator.AccessibilityCheckListener { _, results ->
                runs++
                for (result in results) {
                    when (result.type) {
                        AccessibilityCheckResultType.ERROR -> synchronized(errors) { errors += "$screen: ${describe(result)}" }
                        AccessibilityCheckResultType.WARNING -> Log.w(TAG, "$screen: ${describe(result)}")
                        else -> Unit
                    }
                }
            },
        )

    /** The window checked now, as `name`, once still: no error found here or on the way. */
    private fun check(name: String) {
        walk.awaitStill()
        screen = "$name, $appearance"
        val before = runs
        compose.onRoot().tryPerformAccessibilityChecks()
        assertTrue("The checks didn't run on $name", runs > before)
        val found = synchronized(errors) { errors.distinct().also { errors.clear() } }
        found.forEach { Log.e(TAG, it) }
        assertTrue("The accessibility checks found:\n" + found.joinToString("\n"), found.isEmpty())
    }

    private fun show(parcels: List<Parcel> = demo, session: AuthenticationState = ParcelWalk.READER, until: () -> Boolean = { walk.exists(PARCEL_LIST) }, guest: Boolean = false) {
        walk.dark = appearance == DARK
        walk.show(parcels, session = session, until = until) { if (guest) isGuest = true }
        compose.enableAccessibilityChecks(validator)
    }

    @Test
    fun theWelcome() {
        show(emptyList(), session = AuthenticationState.SignedOut, until = { walk.exists(ARRIVAL) })
        check("welcome")
    }

    @Test
    fun theFirstOpen() {
        show(emptyList(), session = AuthenticationState.Welcome, until = { walk.exists(FIRST_OPEN) }, guest = true)
        check("first open")
    }

    @Test
    fun theList() {
        show()
        check("list")
    }

    @Test
    fun aCardsMenu() {
        show()
        val item = checkNotNull(walk.onUi { walk.deliveries.itemKeys.firstOrNull { cardId(it) == gift.id } })
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey(item)
        compose.onAllNodes(hasContentDescription(gift.label, substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST)))
            .onFirst().performTouchInput { longClick() }
        compose.waitUntil(LONG_MILLIS) { walk.exists(CARD_MENU) }
        check("card menu")
    }

    @Test
    fun aParcelsPageAndItsMenu() {
        show()
        walk.open(gift)
        check(if (walk.stacked()) "page" else "list and page")
        walk.tap(PAGE_MENU_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_MENU) }
        check("page menu")
    }

    @Test
    fun add() {
        show()
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.awaitStill()
        check("add")
    }

    @Test
    fun settingsDownToTheNewUpdateDotsSwitch() {
        show()
        walk.tap(ACCOUNT_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SHEET) }
        walk.awaitStill()
        check("settings")
        compose.reveal(SETTINGS_MARK_NEW_UPDATES)
        check("settings, new-update dot")
    }

    private fun describe(result: AccessibilityViewCheckResult): String {
        val element = result.element
        val names = listOfNotNull(
            element?.resourceName,
            element?.className?.toString()?.substringAfterLast('.'),
            element?.contentDescription?.toString()?.let { "\"$it\"" },
            element?.text?.toString()?.let { "\"$it\"" },
            element?.boundsInScreen?.let { "[${it.left},${it.top}][${it.right},${it.bottom}]" },
        )
        return "${result.sourceCheckClass.simpleName} on ${names.joinToString(" ")}: ${result.getMessage(Locale.ENGLISH)}"
    }

    companion object {
        private const val TAG = "PeekAccessibility"
        private const val DARK = "dark"

        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun appearances(): List<String> = listOf("light", DARK)
    }
}
