package com.plhery.peekdeliverytracker.ui.passport

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.ViewRootForTest
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
import androidx.compose.ui.unit.DpSize
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
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.PassportStatistics
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.DEMO_EXIT
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
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
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
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

    /** The app's language, which a test may change as it runs. */
    private var language by mutableStateOf(AppLanguage.En)

    /**
     * The passport tab of [session] on [parcels], chosen from the tab bar, as large as [fontScale] makes its text, the
     * haptics played on [feeling] where a test gives it, with motion unless [reduceMotion].
     */
    private fun show(
        parcels: List<Parcel> = DemoCards.parcels(),
        session: AuthenticationState = reader,
        fontScale: Float = 1f,
        recreation: Recreation? = null,
        feeling: View? = null,
        reduceMotion: Boolean = true,
    ) {
        source = FakeDeliveries(parcels, session)
        compose.waitsForMaps()
        val content: @Composable () -> Unit = {
            view = LocalView.current
            activity = LocalActivity.current
            val density = LocalDensity.current
            val root: @Composable () -> Unit = {
                CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                    RootHost(
                        state = session, language = language, dark = false, reduceMotion = reduceMotion, onView = { views += it }, source = source,
                    )
                }
            }
            if (feeling != null) CompositionLocalProvider(LocalView provides feeling, content = root) else root()
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        settle()
        choose("Passport")
    }

    /** The tab labelled [label] chosen from the tab bar. */
    private fun choose(label: String) {
        compose.onNode(hasContentDescription(label) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
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

    /** The cover's words, drawn now in software, as Robolectric's hardware capture may return a frame before. */
    private fun coverDrawing(): Bitmap {
        val root = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.waitForIdle()
        val whole = Bitmap.createBitmap(root.width, root.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { root.draw(Canvas(whole)) }
        val cover = compose.onNodeWithTag(PASSPORT_COVER, useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        return Bitmap.createBitmap(whole, cover.left.toInt(), cover.top.toInt(), (cover.width * 0.6f).toInt(), cover.height.toInt())
    }

    /**
     * A-AN15: a new count shows at once, as `.contentTransition(.numericText())` does outside an animated change, which
     * is how the count always changes.
     */
    @Test
    fun aNewCountShowsAtOnce() {
        show(PassportFixtures.delivered(11), reduceMotion = false)
        assertEquals("11 Delivered", label(PASSPORT_COVER))
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread {
            source.parcels.value = PassportFixtures.delivered(12)
            Snapshot.sendApplyNotifications()
        }
        repeat(3) {
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
        }
        assertEquals("12 Delivered", label(PASSPORT_COVER))
        val next = coverDrawing()
        compose.mainClock.advanceTimeBy(1_000)
        assertTrue("The count is drawn as it rests", next.sameAs(coverDrawing()))
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

    /**
     * `toggle` plays the light impact (`touchCount`); a popover closed by a tap anywhere, its item's too, or by the back
     * goes through the binding's setter and plays nothing.
     */
    @Test
    fun eachToggleAndNoDismissalPlaysTheLightImpact() {
        val feeling = FeelingView(compose.activity)
        show(session = AuthenticationState.Demo, feeling = feeling)
        val impact = hapticFeedbackConstant(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light, 0.65))
        // The tab's selection played a tick of its own.
        assertTrue(feeling.played.isNotEmpty())
        fun impacts() = feeling.played.count { it == impact }
        assertEquals(0, impacts())

        tap(PASSPORT_COVER)
        assertTrue(popover()!!.startsWith("Delivered"))
        assertEquals(1, impacts())
        // A tap on the cover while its popover is open only closes it.
        tapWhereItShows(PASSPORT_COVER)
        assertEquals(null, popover())
        assertEquals(1, impacts())

        tap(stamp("carriers"))
        assertTrue(popover()!!.startsWith("Well connected"))
        assertEquals(2, impacts())
        back()
        assertEquals(null, popover())
        assertEquals(2, impacts())

        tap(PASSPORT_AVERAGE)
        assertTrue(popover()!!.startsWith("Average delivery time"))
        assertEquals(3, impacts())
        tapWhereItShows(stamp("first"))
        assertEquals(null, popover())
        assertEquals(3, impacts())
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
        assertTrue(popover()!!.startsWith("First arrival"))
        assertEquals(12, stamps().size)
        // With the popover closed, the tab bar over the first page, clear of the fold, the account still at the window's
        // trailing edge.
        back()
        assertTrue("${bounds(TAB_BAR)}", bounds(TAB_BAR).right <= middle)
        assertTheAccountStandsInTheTabBarsRow()
    }

    /**
     * Where the tab bar stands at the top, the iPad's (ipad-07): no title, the account's glass in the tab bar's row,
     * centred on it, 10 from the window's trailing edge, clear of the tab bar.
     */
    private fun assertTheAccountStandsInTheTabBarsRow() {
        assertTrue(compose.onAllNodes(hasText("Passport") and isHeading(), useUnmergedTree = true).fetchSemanticsNodes().isEmpty())
        val bar = bounds(TAB_BAR)
        val account = bounds(PASSPORT_ACCOUNT_BUTTON)
        val window = compose.onRoot().getBoundsInRoot()
        assertEquals(((bar.top + bar.bottom) / 2).value, ((account.top + account.bottom) / 2).value, 0.5f)
        // The 44-point button in the middle of the 52-point glass.
        assertEquals((window.right - 10.dp - 26.dp).value, ((account.left + account.right) / 2).value, 0.5f)
        assertTrue("$bar $account", account.left - 4.dp > bar.right)
    }

    /**
     * The demo's bar, or with none the pages, 18 into theirs (the cover's contents 26 into its card), from 10 under the
     * tab bar's foot.
     */
    private fun assertWhatIsUnderTheBarStartsTenUnderTheTabBar(demo: Boolean) {
        val foot = bounds(TAB_BAR).bottom
        if (demo) {
            assertEquals((foot + 10.dp).value, bounds(DEMO_EXIT).top.value, 0.5f)
        } else {
            assertEquals((foot + 10.dp + 18.dp + 26.dp).value, bounds(PASSPORT_COVER).top.value, 0.5f)
        }
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun onATabletTheAccountStandsInTheTabBarsRowWithNoTitle() {
        show(session = AuthenticationState.Demo)
        assertTheAccountStandsInTheTabBarsRow()
        assertWhatIsUnderTheBarStartsTenUnderTheTabBar(demo = true)
    }

    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun onAnUnfoldedFoldableTheAccountStandsInTheTabBarsRowWithNoTitle() {
        show(session = AuthenticationState.Demo)
        assertTheAccountStandsInTheTabBarsRow()
        assertWhatIsUnderTheBarStartsTenUnderTheTabBar(demo = true)
        compose.onNodeWithTag(PASSPORT_ACCOUNT_BUTTON).performClick()
        settle()
        assertTrue(exists(SETTINGS_SHEET))
    }

    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun onATabletSidewaysWithNoDemoThePagesStartTenUnderTheTabBar() {
        show()
        assertTheAccountStandsInTheTabBarsRow()
        assertWhatIsUnderTheBarStartsTenUnderTheTabBar(demo = false)
    }

    /**
     * The narrowest window with the tab bar at the top, in every language: the account's glass clear of the tab bar, the
     * window flat and with a fold standing half open in its middle, where the tab bar keeps to the first half when it
     * fits there.
     */
    @Test
    @Config(qualifiers = "w600dp-h800dp-xhdpi")
    fun inTheNarrowestWindowTheAccountKeepsClearOfTheTabBarInEveryLanguage() {
        show(session = AuthenticationState.Demo)
        fun everyLanguage() {
            for (each in AppLanguage.entries) {
                language = each
                settle()
                assertTheAccountStandsInTheTabBarsRow()
            }
        }
        everyLanguage()
        val hinge = FoldingFeature(requireNotNull(activity), center = view.width / 2, size = 0, state = State.HALF_OPENED, orientation = Orientation.VERTICAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        settle()
        everyLanguage()
    }

    /** On a phone, the tab bar at the foot: the inline title, the account beside it on the bar's row (checks-m4/04). */
    @Test
    fun onAPhoneTheBarKeepsItsInlineTitle() {
        show()
        val title = compose.onNode(hasText("Passport") and isHeading(), useUnmergedTree = true).getBoundsInRoot()
        val account = bounds(PASSPORT_ACCOUNT_BUTTON)
        assertEquals(((title.top + title.bottom) / 2).value, ((account.top + account.bottom) / 2).value, 0.5f)
        assertTrue(bounds(TAB_BAR).top > bounds(PASSPORT_COVER).bottom)
    }

    /**
     * A window too short for the tab bar at the top, across a fold standing half open: the inline title stays, over the
     * first page, clear of the fold.
     */
    @Test
    @Config(qualifiers = "w673dp-h460dp-xxhdpi")
    fun aShortWindowAcrossAFoldStandingHalfOpenKeepsTheTitleOverTheFirstPage() {
        show()
        val hinge = FoldingFeature(requireNotNull(activity), center = view.width / 2, size = 0, state = State.HALF_OPENED, orientation = Orientation.VERTICAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        settle()
        val title = compose.onNode(hasText("Passport") and isHeading(), useUnmergedTree = true).getBoundsInRoot()
        assertTrue("$title", title.right <= 673.dp / 2)
    }

    /**
     * A flat fold isn't `proxy.fold`: on the unfolded foldable with a flat hinge splitting it (a dual screen's, which
     * has width), one page, nothing split at the hinge, though the tab bar keeps to the first half.
     */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun aFlatFoldKeepsOnePage() {
        show(PassportFixtures.delivered(3))
        val hingeWidth = with(compose.density) { 20.dp.roundToPx() }
        val hinge = FoldingFeature(requireNotNull(activity), center = view.width / 2, size = hingeWidth, state = State.FLAT, orientation = Orientation.VERTICAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        settle()
        val middle = 673.dp / 2
        // The hinge reached the window: the tab bar centres in the first half.
        assertEquals((middle - 10.dp).value / 2, ((bounds(TAB_BAR).left + bounds(TAB_BAR).right) / 2).value, 1f)
        val cover = bounds(PASSPORT_COVER)
        val average = bounds(PASSPORT_AVERAGE)
        assertTrue("$cover $average", average.top > cover.bottom)
        assertTrue("$cover", cover.left < middle && cover.right > middle)
        // The cover's contents 24 into its card, which starts where the times do.
        assertEquals((cover.left - 24.dp).value, average.left.value, 0.5f)
    }

    /** Settings from one tab's account button, where it stands, closed again. */
    private fun settingsFrom(button: String): DpRect {
        compose.onNodeWithTag(button).performClick()
        settle()
        val sheet = bounds(SETTINGS_SHEET)
        compose.onNodeWithTag(SETTINGS_CLOSE).performClick()
        settle()
        return sheet
    }

    /**
     * Settings from the passport as the deliveries would show it in the window now, slack included: a window shrunk
     * from wide into the slack keeps them side by side, so a form; below it, the page sheet. They turn only as they are
     * shown, so a window that went below the slack and back while the passport showed finds them side by side still.
     */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun settingsFromThePassportIsAFormWhereTheDeliveriesWouldStandSideBySide() {
        var deliveries: DeliveriesState? = null
        var size by mutableStateOf(DpSize(1032.dp, 1376.dp))
        source = FakeDeliveries(DemoCards.parcels(), reader)
        compose.waitsForMaps()
        compose.setContent {
            Box(Modifier.requiredSize(size)) {
                CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                    RootHost(state = reader, source = source)
                }
            }
        }
        settle()
        fun arrangement() = requireNotNull(deliveries).arrangement
        fun width(sheet: DpRect) = (sheet.right - sheet.left).value
        assertEquals(DeliveriesArrangement.SideBySide, arrangement())
        // Into the slack: 700 is under the 730 a stacked window turns at, over the 690 a side by side one does.
        size = DpSize(700.dp, 1376.dp)
        settle()
        assertEquals(DeliveriesArrangement.SideBySide, arrangement())
        val form = settingsFrom(ACCOUNT_BUTTON)
        assertEquals(580f, width(form), 0.5f)
        choose("Passport")
        assertEquals(form, settingsFrom(PASSPORT_ACCOUNT_BUTTON))

        // Below the slack with the passport shown: the page sheet, the window's width less 52 either side.
        size = DpSize(680.dp, 1376.dp)
        settle()
        assertEquals(576f, width(settingsFrom(PASSPORT_ACCOUNT_BUTTON)), 0.5f)
        // Back into the slack: the deliveries, which haven't turned, would stand side by side.
        size = DpSize(700.dp, 1376.dp)
        settle()
        assertEquals(form, settingsFrom(PASSPORT_ACCOUNT_BUTTON))
        choose("Deliveries")
        assertEquals(DeliveriesArrangement.SideBySide, arrangement())

        // Turned stacked below the slack, they stay so back in it, and Settings from the passport is the page.
        size = DpSize(680.dp, 1376.dp)
        settle()
        assertEquals(DeliveriesArrangement.Stacked, arrangement())
        size = DpSize(700.dp, 1376.dp)
        settle()
        assertEquals(DeliveriesArrangement.Stacked, arrangement())
        val page = settingsFrom(ACCOUNT_BUTTON)
        assertEquals(596f, width(page), 0.5f)
        choose("Passport")
        assertEquals(page, settingsFrom(PASSPORT_ACCOUNT_BUTTON))
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

/** A window's view that keeps the haptics it is asked to play. */
private class FeelingView(context: Context) : View(context) {
    val played = mutableListOf<Int>()

    override fun performHapticFeedback(feedbackConstant: Int): Boolean {
        played += feedbackConstant
        return true
    }
}
