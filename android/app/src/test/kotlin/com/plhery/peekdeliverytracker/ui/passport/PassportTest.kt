package com.plhery.peekdeliverytracker.ui.passport

import android.app.Activity
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsConfiguration
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.window.layout.FoldingFeature.Orientation
import androidx.window.layout.FoldingFeature.State
import androidx.window.testing.layout.FoldingFeature
import androidx.window.testing.layout.TestWindowLayoutInfo
import androidx.window.testing.layout.WindowLayoutInfoPublisherRule
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.PassportStatistics
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_CLOSE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The passport (A-S87 to A-S92, A-S100) on made-up parcels: none, the demo's, a set earning every stamp and one just
 * short of each, the countries' order; all the stamps and fewer; each item's popover, one at a time, a tap elsewhere
 * only closing it and the back too; the layouts by window and fold, kept through a change of window; the account
 * button's Settings with the analytics PassportView sends; the light impact on each toggle; and what TalkBack reads.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PassportTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val fold = WindowLayoutInfoPublisherRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(fold).around(compose)

    private lateinit var source: FakeDeliveries
    private lateinit var view: View
    private var activity: Activity? = null
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** The screens counted, the tabs' and the deliveries'. */
    private val views = mutableListOf<String>()

    /** The passport tab of [session] on [parcels], chosen from the tab bar, as large as [fontScale] makes its text. */
    private fun show(
        parcels: List<Parcel> = DemoCards.parcels(),
        session: AuthenticationState = reader,
        fontScale: Float = 1f,
        recreation: Recreation? = null,
    ) {
        source = FakeDeliveries(parcels, session)
        compose.waitsForMaps()
        val content: @Composable () -> Unit = {
            view = LocalView.current
            activity = LocalActivity.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                RootHost(state = session, dark = false, onView = { views += it }, source = source)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        settle()
        compose.onNode(hasContentDescription("Passport") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun text(key: String, variables: Map<String, Any> = emptyMap()) = localizer.text(key, variables)

    // While a popover is open TalkBack reaches only it, so the passport is looked for in the unmerged tree.
    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun stamp(id: String) = "$PASSPORT_STAMP.$id"

    /** The stamps shown, in their order. */
    private fun stamps(): List<String> = compose.onAllNodes(
        SemanticsMatcher("a stamp") { it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith("$PASSPORT_STAMP.") == true }, useUnmergedTree = true,
    )
        .fetchSemanticsNodes()
        .map { it.config[SemanticsProperties.TestTag].removePrefix("$PASSPORT_STAMP.") }

    private fun semantics(tag: String): SemanticsConfiguration = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config

    private fun label(tag: String): String = semantics(tag)[SemanticsProperties.ContentDescription].single()

    private fun value(tag: String): String? = semantics(tag).getOrNull(SemanticsProperties.StateDescription)

    private fun hint(tag: String): String? = semantics(tag).getOrNull(SemanticsActions.OnClick)?.label

    /** A tap on what has [tag], scrolled into view and, as a finger would, up clear of the tab bar floating over the page. */
    private fun tap(tag: String) {
        val node = compose.onNodeWithTag(tag).performScrollTo()
        val bounds = node.getBoundsInRoot()
        // A phone's tab bar, at the foot of the window; a tablet's stands at its top, over nothing it scrolls.
        val bar = compose.onAllNodes(hasTestTag(TAB_BAR)).fetchSemanticsNodes().firstOrNull()?.boundsInRoot
        val window = compose.onRoot().fetchSemanticsNode().boundsInRoot
        val hidden = if (bar != null && bar.top > window.height / 2) {
            bounds.bottom - with(compose.density) { bar.top.toDp() }
        } else {
            0.dp
        }
        if (hidden > 0.dp) {
            compose.onNode(hasScrollAction()).performSemanticsAction(SemanticsActions.ScrollBy) { scroll ->
                scroll(0f, with(compose.density) { (hidden + 16.dp).toPx() })
            }
            settle()
        }
        node.performClick()
        settle()
    }

    /** A tap where [tag] shows, which goes to whatever stands over it there. */
    private fun tapWhereItShows(tag: String) {
        compose.onNodeWithTag(tag, useUnmergedTree = true).performClick()
        settle()
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    /** What the popover open says, its title over its explanation; null with none open. */
    private fun popover(): String? = compose.onAllNodes(hasTestTag(PASSPORT_POPOVER), useUnmergedTree = true).fetchSemanticsNodes().singleOrNull()?.let { bubble ->
        bubble.children.joinToString("\n") { it.config.getOrNull(SemanticsProperties.Text)?.joinToString().orEmpty() }
    }

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag, useUnmergedTree = true).getBoundsInRoot()

    private fun shows(text: String) = compose.onAllNodes(hasText(text)).fetchSemanticsNodes().isNotEmpty()

    private fun rows(): List<String> = compose.onAllNodes(SemanticsMatcher("a country") { node ->
        node.config.getOrNull(SemanticsProperties.ContentDescription)?.singleOrNull()?.let { it.endsWith(" parcels") || it.endsWith(" parcel") } == true
    }).fetchSemanticsNodes().sortedBy { it.boundsInRoot.top }.map { it.config[SemanticsProperties.ContentDescription].single() }

    @Test
    fun withNoParcelsNoneIsDeliveredTheFirstStampIsNextAndNoCountryShows() {
        show(emptyList())
        assertEquals("0 ${text("passport.delivered", mapOf("count" to 0))}", label(PASSPORT_COVER))
        assertEquals(listOf("first", "ten", "carriers"), stamps())
        assertEquals("First arrival, 0 / 1", label(stamp("first")))
        assertEquals("Double digits, 0 / 10", label(stamp("ten")))
        assertEquals("Well connected, 0 / 3", label(stamp("carriers")))
        assertTrue(exists(PASSPORT_SHOW_ALL))
        assertTrue(shows(text("passport.waitingForTimes")))
        assertFalse(exists(PASSPORT_AVERAGE))
        assertFalse(exists(PASSPORT_PERSONAL_BEST))
        assertFalse(exists(PASSPORT_COUNTRIES))
        assertEquals(emptyList<String>(), rows())
    }

    @Test
    fun theDemosParcels() {
        show(session = AuthenticationState.Demo)
        assertEquals("10 Delivered", label(PASSPORT_COVER))
        // Nine stamps earned and the three to come: all twelve, with nothing more to show.
        assertEquals(
            listOf("first", "ten", "carriers", "speed", "acrossBorders", "aroundWorld", "theRegular", "rightNextDoor", "worthTheWait", "busyDoorstep", "pickedUp", "homeForHolidays"),
            stamps(),
        )
        assertFalse(exists(PASSPORT_SHOW_ALL))
        assertEquals("Well connected", label(stamp("carriers")))
        assertEquals("The regular, 10 / 25", label(stamp("theRegular")))
        assertEquals("Average delivery time, 5 days, 5 hours", label(PASSPORT_AVERAGE))
        assertEquals("Fastest delivery, 18 hours", label(PASSPORT_PERSONAL_BEST))
        // Abbreviated on the cards.
        val stats = PassportStatistics(DemoCards.parcels())
        assertEquals("5d 5h", passportDuration(requireNotNull(stats.averageDeliveryDuration), localizer))
        assertEquals("18h", passportDuration(requireNotNull(stats.fastestDelivery).duration, localizer))
        assertEquals(listOf("France, 4 parcels", "Switzerland, 3 parcels", "Germany, 3 parcels"), rows())
    }

    @Test
    fun aSetThatEarnsEveryStamp() {
        show(PassportFixtures.everyStamp())
        assertEquals("25 Delivered", label(PASSPORT_COVER))
        val all = listOf("first", "ten", "carriers", "speed", "acrossBorders", "aroundWorld", "theRegular", "rightNextDoor", "worthTheWait", "busyDoorstep", "pickedUp", "homeForHolidays")
        assertEquals(all, stamps())
        // Each earned stamp reads as its title alone.
        val titles = listOf(
            "First arrival", "Double digits", "Well connected", "Express arrival", "Across borders", "Around the world", "The regular",
            "Right next door", "Worth the wait", "A busy doorstep", "Picked up", "Home for the holidays",
        )
        assertEquals(titles, all.map { label(stamp(it)) })
        assertFalse(exists(PASSPORT_SHOW_ALL))
        assertEquals("Fastest delivery, 1 day, 12 hours", label(PASSPORT_PERSONAL_BEST))
    }

    @Test
    fun aSetJustShortOfEachStampShowsHowFarAlongItIs() {
        show(PassportFixtures.justShort())
        assertEquals(listOf("first", "ten", "carriers", "speed", "acrossBorders"), stamps())
        assertEquals("Well connected, 2 / 3", label(stamp("carriers")))
        assertEquals("Express arrival, ${text("passport.underTwoDays")}", label(stamp("speed")))
        assertEquals("Across borders, 0 / 1", label(stamp("acrossBorders")))
        assertEquals("Fastest delivery, 2 days", label(PASSPORT_PERSONAL_BEST))

        tap(PASSPORT_SHOW_ALL)
        assertEquals(12, stamps().size)
        assertEquals("Around the world, 4 / 5", label(stamp("aroundWorld")))
        assertEquals("The regular, 24 / 25", label(stamp("theRegular")))
        assertEquals("Right next door, 0 / 1", label(stamp("rightNextDoor")))
        assertEquals("Worth the wait, 0 / 1", label(stamp("worthTheWait")))
        assertEquals("A busy doorstep, 2 / 3", label(stamp("busyDoorstep")))
        assertEquals("Picked up, 0 / 1", label(stamp("pickedUp")))
        assertEquals("Home for the holidays, 0 / 1", label(stamp("homeForHolidays")))

        // The express stamp's popover adds what it is short of.
        tap(stamp("speed"))
        assertEquals("Express arrival\n${text("passport.expressExplanation")}\n${text("passport.underTwoDays")}", popover())
    }

    @Test
    fun nineDeliveredAreOneShortOfDoubleDigits() {
        show(PassportFixtures.delivered(9))
        assertEquals("Double digits, 9 / 10", label(stamp("ten")))
        assertEquals("Well connected, 1 / 3", label(stamp("carriers")))
        assertEquals(listOf("first", "ten", "carriers", "speed", "acrossBorders", "rightNextDoor"), stamps())
    }

    @Test
    fun theCountriesMostParcelsWereFirstScannedInTheTopThree() {
        show(PassportFixtures.fromCountries("DE" to 2, "FR" to 1, "AT" to 5, "CH" to 2))
        // The most first; a tie in the codes' order; the fourth not shown.
        assertEquals(listOf("Austria, 5 parcels", "Switzerland, 2 parcels", "Germany, 2 parcels"), rows())
        assertFalse(compose.onAllNodes(hasContentDescription("France, 1 parcel")).fetchSemanticsNodes().isNotEmpty())
        // The flags are not read.
        assertFalse(shows("🇦🇹"))
    }

    @Test
    fun oneCountryWithOneParcel() {
        show(PassportFixtures.fromCountries("IT" to 1))
        assertEquals(listOf("Italy, 1 parcel"), rows())
    }

    @Test
    fun allStampsAndShowLess() {
        show(PassportFixtures.delivered(3))
        assertEquals(listOf("first", "ten", "carriers", "speed", "acrossBorders", "rightNextDoor"), stamps())
        assertTrue(shows(text("passport.showAll")))
        val roleOf = semantics(PASSPORT_SHOW_ALL)[SemanticsProperties.Role]
        assertEquals(Role.Button, roleOf)

        tap(PASSPORT_SHOW_ALL)
        assertEquals(12, stamps().size)
        assertTrue(shows(text("passport.showLess")))

        tap(PASSPORT_SHOW_ALL)
        assertEquals(listOf("first", "ten", "carriers", "speed", "acrossBorders", "rightNextDoor"), stamps())
        assertTrue(shows(text("passport.showAll")))
    }

    @Test
    fun eachItemsPopoverSaysWhatItMeans() {
        show(session = AuthenticationState.Demo)
        val timed = PassportStatistics(DemoCards.parcels()).durationSampleCount
        val items = listOf(
            PASSPORT_COVER to "Delivered\n${text("passport.deliveredExplanation")}",
            stamp("carriers") to "Well connected\n${text("passport.carrierExplanation")}",
            stamp("theRegular") to "The regular\n${text("passport.regularExplanation")}\n10 / 25",
            PASSPORT_AVERAGE to "Average delivery time\n${text("passport.timedJourneys.many", mapOf("count" to timed))}. ${text("passport.timingExplanation")}",
            PASSPORT_COUNTRIES to "First scanned in\n${text("passport.countryExplanation")}",
        )
        for ((tag, says) in items) {
            assertEquals(null, popover())
            tap(tag)
            assertEquals(tag, says, popover())
            back()
        }
        assertEquals(null, popover())
        // The fastest: its parcel and day, then how it is counted.
        tap(PASSPORT_PERSONAL_BEST)
        val said = requireNotNull(popover())
        assertTrue(said, said.startsWith("Fastest delivery\n"))
        assertTrue(said, said.endsWith("\n\n${text("passport.timingExplanation")}"))
        assertTrue(said, " · " in said)
    }

    /** ipad-03: a tap on another stamp while a popover is open only closes it; the next tap opens that stamp's. */
    @Test
    fun aTapOnAnotherItemWhileOneIsOpenOnlyClosesIt() {
        show(session = AuthenticationState.Demo)
        tap(stamp("carriers"))
        assertTrue(popover()!!.startsWith("Well connected"))
        tapWhereItShows(stamp("pickedUp"))
        assertEquals(null, popover())

        tapWhereItShows(stamp("pickedUp"))
        assertTrue(popover()!!.startsWith("Picked up"))
    }

    @Test
    fun theBackClosesThePopoverAndNotTheApp() {
        show(session = AuthenticationState.Demo)
        tap(PASSPORT_AVERAGE)
        assertTrue(popover() != null)
        back()
        assertEquals(null, popover())
        assertFalse(compose.activity.isFinishing)
    }

    /** A tap with the popover open on all the stamps' button leaves them as they were. */
    @Test
    fun aTapWithAPopoverOpenReachesNothingUnderIt() {
        show(PassportFixtures.delivered(3))
        tap(PASSPORT_COVER)
        tapWhereItShows(PASSPORT_SHOW_ALL)
        assertEquals(null, popover())
        assertEquals(6, stamps().size)
    }

    @Test
    fun eachToggleAndNoDismissalPlaysTheLightImpact() {
        show(session = AuthenticationState.Demo)
        val impact = hapticFeedbackConstant(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light, 0.65))
        // The tab's selection played last, a tick of its own.
        assertNotEquals(impact, shadowOf(view).lastHapticFeedbackPerformed())
        tap(PASSPORT_COVER)
        assertEquals(impact, shadowOf(view).lastHapticFeedbackPerformed())
    }

    @Test
    fun talkBackReadsEachItemsLabelStateAndHint() {
        show(session = AuthenticationState.Demo)
        val collapsed = text("passport.expansionState.collapsed")
        assertEquals(collapsed, value(PASSPORT_COVER))
        assertEquals(text("passport.detailsHint"), hint(PASSPORT_COVER))
        assertEquals(Role.Button, semantics(PASSPORT_COVER)[SemanticsProperties.Role])
        assertEquals(collapsed, value(stamp("first")))
        assertEquals(collapsed, value(PASSPORT_AVERAGE))
        assertEquals("First scanned in", label(PASSPORT_COUNTRIES))
        assertEquals(collapsed, value(PASSPORT_COUNTRIES))
        assertEquals(text("passport.detailsHint"), hint(PASSPORT_COUNTRIES))

        tap(PASSPORT_COVER)
        assertEquals("${text("passport.expansionState.expanded")}. ${text("passport.deliveredExplanation")}", value(PASSPORT_COVER))
        assertEquals(text("passport.expansionHint"), hint(PASSPORT_COVER))
        back()

        // The countries' title reads its state alone, without the explanation.
        tap(PASSPORT_COUNTRIES)
        assertEquals(text("passport.expansionState.expanded"), value(PASSPORT_COUNTRIES))
        assertEquals(text("passport.expansionHint"), hint(PASSPORT_COUNTRIES))
    }

    /** The account button opens Settings over the passport; PassportView sends no view as it opens or closes. */
    @Test
    fun theAccountButtonOpensSettingsFromThePassport() {
        show()
        assertEquals(listOf("passport"), views)
        compose.onNodeWithTag(PASSPORT_ACCOUNT_BUTTON).performClick()
        settle()
        assertTrue(exists(SETTINGS_SHEET))
        assertTrue(shows("reader@example.com"))
        assertEquals(listOf("passport"), views)
        compose.onNodeWithTag(SETTINGS_CLOSE).performClick()
        settle()
        assertFalse(exists(SETTINGS_SHEET))
        assertEquals(listOf("passport"), views)
        assertTrue(exists(PASSPORT_COVER))
    }

    /** Settings from the passport, then from the deliveries: where each stands. */
    private fun settingsFromBothTabs(): Pair<DpRect, DpRect> {
        show()
        compose.onNodeWithTag(PASSPORT_ACCOUNT_BUTTON).performClick()
        settle()
        val passport = bounds(SETTINGS_SHEET)
        compose.onNodeWithTag(SETTINGS_CLOSE).performClick()
        settle()
        compose.onNode(hasContentDescription("Deliveries") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        settle()
        compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
        settle()
        return passport to bounds(SETTINGS_SHEET)
    }

    /** On a tablet, Settings is the deliveries' form in the middle of the window, 580 wide. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun onATabletSettingsIsTheDeliveriesForm() {
        val (passport, deliveries) = settingsFromBothTabs()
        assertEquals(deliveries, passport)
        assertTrue("$passport", passport.right - passport.left <= 580.dp)
    }

    /** On an unfolded foldable, whose deliveries don't stand side by side, the deliveries' page sheet. */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun onAnUnfoldedFoldableSettingsIsTheDeliveriesPageSheet() {
        val (passport, deliveries) = settingsFromBothTabs()
        assertEquals(deliveries, passport)
    }

    @Test
    fun theLayoutsByWindowTextAndFold() {
        fun layout(width: Int, windowWidth: Int = 1000, windowHeight: Int = 800, largeText: Boolean = false, hinge: Pair<Int, Int>? = null) =
            passportLayout(width.dp, windowWidth.dp, windowHeight.dp, largeText, hinge, content = 1_000)
        assertEquals(PassportLayout.Single, layout(759))
        assertEquals(PassportLayout.Spread, layout(760))
        assertEquals(PassportLayout.Single, layout(919, largeText = true))
        assertEquals(PassportLayout.Spread, layout(920, largeText = true))
        // A compact width or height keeps one page, however wide.
        assertEquals(PassportLayout.Single, layout(900, windowWidth = 599))
        assertEquals(PassportLayout.Spread, layout(900, windowWidth = 600))
        assertEquals(PassportLayout.Single, layout(900, windowHeight = 479))
        assertEquals(PassportLayout.Spread, layout(900, windowHeight = 480))
        // A fold standing half open: either side of it, whatever the width.
        assertEquals(PassportLayout.Fold(480, 40, 480), layout(400, windowWidth = 400, hinge = 480 to 520))
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aTabletShowsTheSpread() {
        show()
        val cover = bounds(PASSPORT_COVER)
        val average = bounds(PASSPORT_AVERAGE)
        assertTrue("$cover $average", average.left > cover.right)
        assertTrue("$cover", cover.right - cover.left <= 480.dp)
    }

    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun anUnfoldedFoldableShowsOnePageThenTwoEitherSideOfTheFoldAsABookKeepingThePopoverAndAllTheStamps() {
        show(PassportFixtures.delivered(3))
        assertTrue(bounds(PASSPORT_AVERAGE).top > bounds(PASSPORT_COVER).bottom)
        tap(PASSPORT_SHOW_ALL)
        tap(stamp("first"))
        val hinge = FoldingFeature(requireNotNull(activity), center = view.width / 2, size = 0, state = State.HALF_OPENED, orientation = Orientation.VERTICAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        settle()
        val middle = 673.dp / 2
        assertTrue("${bounds(PASSPORT_COVER)}", bounds(PASSPORT_COVER).right <= middle)
        assertTrue("${bounds(PASSPORT_AVERAGE)}", bounds(PASSPORT_AVERAGE).left >= middle)
        // The bar's title over the first page, clear of the fold.
        val title = compose.onNode(hasText("Passport") and isHeading(), useUnmergedTree = true).getBoundsInRoot()
        assertTrue("$title", title.right <= middle)
        assertTrue(popover()!!.startsWith("First arrival"))
        assertEquals(12, stamps().size)
    }

    /** The window made again (a rotation): the popover and all the stamps as they were. */
    @Test
    fun aRecreatedWindowKeepsThePopoverAndAllTheStamps() {
        val recreation = Recreation(compose)
        show(PassportFixtures.delivered(3), recreation = recreation)
        tap(PASSPORT_SHOW_ALL)
        tap(stamp("ten"))
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        settle()
        assertEquals(12, stamps().size)
        assertTrue(popover()!!.startsWith("Double digits"))
    }
}
