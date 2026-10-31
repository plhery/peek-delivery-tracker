package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PixelMap
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierPickerSearch
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.ProvideLocalizer
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.awaitFrame
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetAttachment
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.PeekColors
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import kotlin.math.abs
import kotlin.math.roundToInt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The carrier picker on its own, in English on the sheet's grouped ground, as the change-carrier sheet will push it:
 * attached, unless [attachment] says the sheet floats on its glass, and with the identities read unless [identities] is null.
 */
@Composable
internal fun PickerHost(
    dark: Boolean = false,
    fontScale: Float = 1f,
    identities: CarrierIdentities? = DemoCards.identities,
    attachment: () -> Float = { 1f },
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalSheetAttachment provides attachment) {
        PeekTheme(dark = dark, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = identities) {
            ProvideLocalizer(remember { Localizer(AppLanguage.En, variant = "android") }) {
                Box(Modifier.fillMaxSize().background(PeekTheme.colors.groupedBackground).testTag(SHOT)) { content() }
            }
        }
    }
}

/** The sections a number that fits two Swiss carriers opens the picker with: none current, two that fit, one used. */
internal val PICKER_SECTIONS = listOf(
    CarrierPickerSection("current", "Current", emptyList()),
    CarrierPickerSection("fits", "Fits this number", listOf(CarrierId.SwissPost, CarrierId.Planzer)),
    CarrierPickerSection("used", "You’ve used", listOf(CarrierId.Dpd)),
)

internal val PICKER_AUTOMATIC = CarrierPickerAutomatic("Peek finds the carrier for you.", recommended = true, busy = false)

internal val PICKER_TAGS = mapOf(CarrierId.SwissPost to CarrierPickerTag("Has this parcel", found = true))

/**
 * `CarrierPickerView` and its `LetterRail` (B-S37 to B-S40, B-ST26, B-AN19, B-G10, B-G11, B-H06): the automatic row, the
 * sections it opened with and the letters; a search narrowing them with its count or saying none matches, the rail gone
 * meanwhile; a choice going to onSelect, then leaving; the rail answering from the first touch, jumping at once to the
 * letter under the finger and ticking for each new one, its bubble level with it; TalkBack's rows and rail; the tag
 * under the name at an accessibility text size; the query and sections through a window made again; the form's
 * ground on a sheet's glass; and everything but the marks before the identities are read.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class CarrierPickerTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val events = mutableListOf<String>()
    private var sections by mutableStateOf(PICKER_SECTIONS)
    private var tags by mutableStateOf(PICKER_TAGS)
    private var identities by mutableStateOf<CarrierIdentities?>(DemoCards.identities)
    private var attachment by mutableFloatStateOf(1f)
    private val catalog get() = DemoCards.identities.catalog
    private val letters get() = CarrierPickerSearch.letterSections(catalog, AppLanguage.En).map { it.letter }
    private val localizer = Localizer(AppLanguage.En, variant = "android")

    private fun show(
        selection: CarrierId? = CarrierId.SwissPost,
        automatic: CarrierPickerAutomatic? = PICKER_AUTOMATIC,
        fontScale: Float = 1f,
        view: View? = null,
        recreation: Recreation? = null,
        roomForKeyboard: Boolean = false,
    ) {
        val content: @Composable () -> Unit = {
            val picker: @Composable () -> Unit = {
                PickerHost(fontScale = fontScale, identities = identities, attachment = { attachment }) {
                    // A host that makes room for the keyboard itself, as one padding for it would.
                    Box(if (roomForKeyboard) Modifier.imePadding() else Modifier) {
                        CarrierPicker(
                            selection, sections,
                            onSelect = { events += "select ${it?.rawValue}" },
                            onLeave = { events += "leave" },
                            automatic = automatic, tags = tags,
                        )
                    }
                }
            }
            if (view != null) CompositionLocalProvider(LocalView provides view, content = picker) else picker()
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun row(carrier: CarrierId): SemanticsNodeInteraction = compose.onAllNodesWithTag(carrierPickerRowTag(carrier)).onFirst()

    private fun header(key: String): SemanticsNodeInteraction = compose.onNodeWithTag(carrierPickerHeaderTag(key))

    private fun field() = compose.onNodeWithTag(CARRIER_PICKER_SEARCH)

    private fun fieldText(): String? = field().fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun SemanticsNodeInteraction.top(): Float = fetchSemanticsNode().boundsInRoot.top

    private fun SemanticsNodeInteraction.state(): String? = fetchSemanticsNode().config.getOrNull(SemanticsProperties.StateDescription)

    /** What TalkBack reads of a row: its name, then its subtitle and tag. */
    private fun SemanticsNodeInteraction.description(): String? =
        fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString()

    /** Selected, as only the chosen row is; the others say nothing of it, as iOS adds `.isSelected` only to it. */
    private fun SemanticsNodeInteraction.assertOnlySelectedWhileChosen(chosen: Boolean) {
        if (chosen) assertIsSelected() else assert(SemanticsMatcher.keyNotDefined(SemanticsProperties.Selected))
    }

    /** The window's view, which TalkBack reads, with TalkBack on. */
    private fun talkBack(): View {
        val root = compose.onRoot().fetchSemanticsNode().root as ViewRootForTest
        val manager = shadowOf(compose.activity.getSystemService(AccessibilityManager::class.java))
        compose.runOnUiThread {
            manager.setEnabled(true)
            manager.setTouchExplorationEnabled(true)
            root.forceAccessibilityForTesting(true)
        }
        return root.view
    }

    /** What TalkBack is given of `node`. */
    private fun SemanticsNodeInteraction.info(view: View): AccessibilityNodeInfo {
        val id = fetchSemanticsNode().id
        var info: AccessibilityNodeInfo? = null
        compose.runOnUiThread { info = view.accessibilityNodeProvider.createAccessibilityNodeInfo(id) }
        return checkNotNull(info)
    }

    /** The next frame, after what a touch changed is seen, as the window would draw it, the clock held. */
    private fun frame() {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        compose.mainClock.advanceTimeByFrame()
    }

    private fun px(dp: Float): Float = dp * compose.activity.resources.displayMetrics.density

    private fun title() = compose.onNodeWithText(localizer.text("add.carrier"))

    private fun cancel() = compose.onNodeWithTag(CARRIER_PICKER_CANCEL)

    /** The keyboard `height` points tall, as the window's insets tell it. */
    private fun keyboard(height: Int) {
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.runOnUiThread {
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.ime(), Insets.of(0, 0, 0, px(height.toFloat()).roundToInt()))
                .setVisible(WindowInsetsCompat.Type.ime(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    /**
     * With the field focused, a keyboard `height` tall comes up: the rail stands in the middle of the room left above
     * it, `height / 2` higher, and the list scrolled to its end ends 20 above it, its last section's 6 under its last row.
     */
    private fun assertEndsAboveAKeyboard(height: Int) {
        val window = compose.onRoot().fetchSemanticsNode().size.height.toFloat()
        field().performClick()
        fun rail() = compose.onNodeWithTag(LETTER_RAIL).fetchSemanticsNode().boundsInRoot.center.y
        val open = rail()
        keyboard(height)
        val foot = window - px(height.toFloat())
        assertEquals(open - px(height / 2f), rail(), px(1f))
        val opened = sections.filter { it.carriers.isNotEmpty() }
        val count = 2 + opened.sumOf { 1 + it.carriers.size } + CarrierPickerSearch.letterSections(catalog, AppLanguage.En).sumOf { 1 + it.carriers.size }
        compose.onNodeWithTag(CARRIER_PICKER_LIST).performScrollToIndex(count - 1)
        val last = CarrierPickerSearch.letterSections(catalog, AppLanguage.En).last().carriers.last()
        val row = compose.onAllNodesWithTag(carrierPickerRowTag(last)).onLast().fetchSemanticsNode().boundsInRoot
        assertEquals(foot - px(20f + 6f), row.bottom, px(1.5f))
    }

    /**
     * What `tag` shows, once the window has drawn it. A capture straight after the looper's frame races the renderer,
     * which may draw that frame a moment later, or put it off to its next, and then shows the window a frame early.
     */
    private fun image(tag: String): PixelMap {
        compose.awaitFrame()
        return compose.onAllNodesWithTag(tag).onFirst().captureToImage().toPixelMap()
    }

    private fun assertColor(expected: Color, actual: Color) {
        val close = listOf(expected.red to actual.red, expected.green to actual.green, expected.blue to actual.blue)
            .all { (wanted, got) -> abs(wanted - got) <= 2.5f / 255 }
        assertTrue("$expected, not $actual", close)
    }

    /**
     * The header `key` heads the list under the bar: its words 21 under the list's top, which is the bar's foot, 16 under
     * the search field's capsule.
     */
    private fun assertAtTop(key: String) {
        val under = compose.onNodeWithTag(CARRIER_PICKER_FIELD).fetchSemanticsNode().boundsInRoot.bottom
        val top = header(key).top()
        assertTrue("$key at $top, the field ending at $under", top - under in px(16f + 21f)..px(16f + 21f + 24f))
    }

    /** Two carriers with short names and no line under them, which the sections can show tagged and not. */
    private fun plainCarriers(): List<CarrierId> = catalog.selectableCarriers.filter {
        val info = catalog.info(it, AppLanguage.En)
        info.displayName.length <= 10 && CarrierPickerSearch.subtitle(info, null, localizer).isEmpty()
    }.take(2).also { assertEquals(2, it.size) }

    private fun height(carrier: CarrierId): Int = row(carrier).fetchSemanticsNode().size.height

    @Test
    fun opensOnTheAutomaticRowItsSectionsAndTheLettersAndAChoiceLeaves() {
        show()
        compose.onNodeWithTag(CARRIER_PICKER_AUTOMATIC).assertOnlySelectedWhileChosen(false)
        compose.onNode(hasText("Recommended"), useUnmergedTree = true).assertExists()
        compose.onNode(hasText("Peek finds the carrier for you."), useUnmergedTree = true).assertExists()
        // The empty section is left out; the others keep their order, over the letters'.
        header("section:current").assertDoesNotExist()
        header("section:fits").assertTextEquals("Fits this number")
        header("section:used").assertTextEquals("You’ve used")
        assertTrue(header("section:used").top() > header("section:fits").top())
        assertTrue(header("letter:A").top() > header("section:used").top())
        compose.onNodeWithTag(LETTER_RAIL).assertExists()
        compose.onNodeWithText("Carrier").assertExists()

        row(CarrierId.Planzer).performClick()
        assertEquals(listOf("select planzer", "leave"), events)
        events.clear()
        compose.onNodeWithTag(CARRIER_PICKER_AUTOMATIC).performClick()
        assertEquals(listOf("select null", "leave"), events)
    }

    @Test
    fun automaticDetectionIsCheckedWhileChosenAndSpinsWhileBusy() {
        show(selection = null, automatic = PICKER_AUTOMATIC.copy(busy = true))
        compose.onNodeWithTag(CARRIER_PICKER_AUTOMATIC).assertOnlySelectedWhileChosen(true)
        row(CarrierId.SwissPost).assertOnlySelectedWhileChosen(false)
        compose.onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate), useUnmergedTree = true).assertExists()
    }

    @Test
    fun theSectionsStayAsTheyOpenedWhileTheTagsFollowTheAnswers() {
        show()
        row(CarrierId.Dpd).assertExists()
        assertEquals(0, compose.onAllNodesWithTag(carrierPickerRowTag(CarrierId.GlsCh)).fetchSemanticsNodes().size)
        sections = listOf(
            CarrierPickerSection("current", "Current", listOf(CarrierId.GlsCh)),
            CarrierPickerSection("fits", "Fits this number", listOf(CarrierId.SwissPost)),
        )
        tags = mapOf(CarrierId.Planzer to CarrierPickerTag("Knows this number", found = false))
        compose.waitForIdle()
        header("section:current").assertDoesNotExist()
        header("section:used").assertExists()
        row(CarrierId.Dpd).assertExists()
        assertEquals(0, compose.onAllNodesWithTag(carrierPickerRowTag(CarrierId.GlsCh)).fetchSemanticsNodes().size)
        assertTrue(row(CarrierId.Planzer).description().orEmpty().endsWith(", Knows this number"))
        assertTrue(row(CarrierId.SwissPost).description().orEmpty().endsWith("Has this parcel").not())
    }

    @Test
    fun aSearchNarrowsTheListUnderItsCountOrSaysNoneMatchesAndTheRailGoesMeanwhile() {
        show()
        field().performTextInput("colis")
        val results = CarrierPickerSearch.search("colis", catalog, AppLanguage.En, preferred = setOf(CarrierId.SwissPost, CarrierId.Planzer))
        assertTrue(results.size > 1)
        header("results").assertTextEquals("${results.size} carriers")
        row(results.first().carrier).assertExists()
        compose.onNodeWithTag(LETTER_RAIL).assertDoesNotExist()
        compose.onNodeWithTag(CARRIER_PICKER_AUTOMATIC).assertDoesNotExist()
        header("section:fits").assertDoesNotExist()
        assertAtTop("results")

        // One match is counted in the singular; one found by another name says so.
        field().performTextReplacement(" hugger ")
        assertEquals(1, CarrierPickerSearch.search("hugger", catalog, AppLanguage.En).size)
        header("results").assertTextEquals("1 carrier")
        val cargo = catalog.info(CarrierId.SwissPostCargo, AppLanguage.En).displayName
        assertTrue(row(CarrierId.SwissPostCargo).description().orEmpty().startsWith("$cargo, Also called Hugger"))

        field().performTextReplacement("zzqx")
        compose.onNodeWithText("No carrier matches “zzqx”.").assertExists()
        header("results").assertDoesNotExist()

        compose.onNodeWithTag(CARRIER_PICKER_CLEAR).performClick()
        assertEquals("", fieldText())
        compose.onNodeWithTag(LETTER_RAIL).assertExists()
        compose.onNodeWithTag(CARRIER_PICKER_AUTOMATIC).assertExists()

        // Spaces alone search nothing.
        field().performTextReplacement("   ")
        compose.onNodeWithTag(LETTER_RAIL).assertExists()
        header("section:fits").assertExists()
    }

    @Test
    fun searchingTheBarGivesTheFieldItsPlaceAndTheCloseCircleEndsTheSearch() {
        show()
        title().assertExists()
        cancel().assertDoesNotExist()
        val top = field().top()
        field().performClick()
        field().assertIsFocused()
        cancel().assertExists().assertHasClickAction()
        title().assertDoesNotExist()
        // The bar's row and the 10 under it.
        assertEquals(top - px(54f), field().top(), px(1f))

        // The keyboard's Search puts the keyboard away; the search stays on, the ✕ with it.
        field().performTextInput("colis")
        field().performImeAction()
        field().assertIsNotFocused()
        cancel().assertExists()
        header("results").assertExists()

        cancel().performClick()
        assertEquals("", fieldText())
        field().assertIsNotFocused()
        cancel().assertDoesNotExist()
        title().assertExists()
        assertEquals(top, field().top(), px(1f))
        header("section:fits").assertExists()

        // The system's back ends a search as the ✕ does, before it would leave the picker.
        field().performTextInput("dpd")
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
        assertEquals("", fieldText())
        cancel().assertDoesNotExist()
        title().assertExists()
        assertTrue(compose.activity.onBackPressedDispatcher.hasEnabledCallbacks().not())
    }

    /**
     * iOS 26's: the field a 44 capsule of glass 20 in from the sides, its magnifying glass in the label's colour; while
     * searching a 44 glass circle 10 from it, at the side's margin, its xmark in the label's colour, which TalkBack
     * reads "Close" as a button.
     */
    @Test
    fun theFieldIsA44GlassCapsuleAndWhileSearchingA44CircleClosesIt() {
        val colors = PeekColors(dark = false)
        show()
        val window = compose.onRoot().fetchSemanticsNode().size.width.toFloat()
        fun capsule() = compose.onNodeWithTag(CARRIER_PICKER_FIELD).fetchSemanticsNode().boundsInRoot
        val idle = capsule()
        assertEquals(px(44f), idle.height, px(0.5f))
        assertEquals(px(20f), idle.left, px(0.5f))
        assertEquals(window - px(20f), idle.right, px(0.5f))
        val face = image(CARRIER_PICKER_FIELD)
        // Glass, lighter than the ground round it, where the grey fill was darker.
        val glass = face[face.width - px(10f).roundToInt(), face.height / 2]
        assertTrue("$glass", glass.red > colors.groupedBackground.red)
        assertTrue("the magnifying glass in grey", darkest(face, 0 until px(44f).roundToInt()) < 0.15f)

        field().performClick()
        compose.waitForIdle()
        val searching = capsule()
        val close = cancel().fetchSemanticsNode().boundsInRoot
        assertEquals(px(44f), searching.height, px(0.5f))
        assertEquals(px(44f), close.width, px(0.5f))
        assertEquals(px(44f), close.height, px(0.5f))
        assertEquals(searching.right + px(10f), close.left, px(1f))
        assertEquals(window - px(20f), close.right, px(1f))
        assertEquals(searching.center.y, close.center.y, px(0.5f))
        cancel().assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
        assertEquals(localizer.text("common.close"), cancel().description())
        val circle = image(CARRIER_PICKER_CANCEL)
        assertTrue("the xmark not in the label's colour", darkest(circle, 0 until circle.width) < 0.15f)
        val rim = circle[circle.width / 2, px(6f).roundToInt()]
        assertTrue("$rim", rim.red > colors.groupedBackground.red)

        // The clear button, once the field holds something, in the label's colour too.
        field().performTextInput("colis")
        val clear = image(CARRIER_PICKER_CLEAR)
        assertTrue("the clear button in grey", darkest(clear, 0 until clear.width) < 0.15f)
    }

    /** The darkest red of `image`'s columns `xs`, 0 to 1. */
    private fun darkest(image: PixelMap, xs: IntRange): Float =
        xs.minOf { x -> (0 until image.height).minOf { y -> image[x, y].red } }

    @Test
    fun withTheKeyboardUpTheListsFootAndTheRailEndAboveIt() {
        show()
        assertEndsAboveAKeyboard(300)
    }

    @Test
    fun aHostThatMakesRoomForTheKeyboardIsntGivenItTwice() {
        show(roomForKeyboard = true)
        assertEndsAboveAKeyboard(300)
    }

    @Test
    fun theMatchedPartIsMarkedInTheAccentAndSemibold() {
        val name = highlighted("Relais Colis", 7 until 12, Color.Yellow)
        val span = name.spanStyles.single()
        assertEquals(7 to 12, span.start to span.end)
        assertEquals(FontWeight.SemiBold, span.item.fontWeight)
        assertEquals(Color.Yellow, span.item.background)
        assertTrue(highlighted("Colis", 3 until 6, Color.Yellow).spanStyles.isEmpty())
    }

    @Test
    fun theRailAnswersTheFirstTouchJumpingAtOnceAndTicksForEachNewLetter() {
        val view = TickingView(compose.activity)
        show(view = view)
        val rail = compose.onNodeWithTag(LETTER_RAIL)
        val bounds = rail.fetchSemanticsNode().boundsInRoot
        val padding = px(12f)
        val row = (bounds.height - 2 * padding) / letters.size
        assertTrue(row in px(8f)..px(16f))
        fun at(letter: String) = Offset(bounds.width / 2, padding + row * letters.indexOf(letter) + row / 2)

        compose.mainClock.autoAdvance = false
        rail.performTouchInput { down(at("M")) }
        frame()
        assertAtTop("letter:M")
        // The bubble stands 64 to the rail's left, level with the letter under the finger.
        val bubble = compose.onNodeWithTag(LETTER_BUBBLE).fetchSemanticsNode().boundsInRoot
        assertEquals(bounds.left - px(64f), bubble.left, 1f)
        assertEquals(bounds.top + at("M").y, bubble.center.y, 1f)

        rail.performTouchInput { moveTo(at("M") + Offset(0f, row / 4)) }
        frame()
        rail.performTouchInput { moveTo(at("S")) }
        frame()
        assertAtTop("letter:S")
        rail.performTouchInput { up() }
        frame()
        compose.onNodeWithTag(LETTER_BUBBLE).assertDoesNotExist()

        // Past either end the first or last letter holds.
        rail.performTouchInput { down(Offset(bounds.width / 2, 1f)) }
        frame()
        assertAtTop("letter:A")
        rail.performTouchInput { moveTo(Offset(bounds.width / 2, bounds.height + px(200f))) }
        frame()
        val last = compose.onNodeWithTag(LETTER_BUBBLE).fetchSemanticsNode().boundsInRoot
        assertEquals(bounds.top + at(letters.last()).y, last.center.y, 1f)
        rail.performTouchInput { up() }
        frame()

        val tick = hapticFeedbackConstant(SensoryFeedback.FrequentTick)
        assertEquals(listOf(tick, tick, tick, tick), view.played)
    }

    /**
     * The rows of the rail's picture its ochre plate covers, in pixels from the rail's top, read down a column 3.6 into
     * the plate's 20, clear of its rounded corners and of the letter on it, in light.
     */
    private fun plate(): IntRange {
        // Once the window has drawn the touch ([image]).
        compose.awaitFrame()
        val image = compose.onNodeWithTag(LETTER_RAIL).captureToImage().toPixelMap()
        val x = px(44f - 4f - 20f + 3.6f).roundToInt()
        // Ochre, (116, 83, 0), on the greys of the ground and of the scroll edge's band.
        val covered = (0 until image.height).filter { image[x, it].let { color -> color.red - color.blue > 0.25f } }
        assertTrue("no plate on the rail", covered.isNotEmpty())
        return covered.first()..covered.last()
    }

    @Test
    @Config(qualifiers = "w411dp-h493dp-420dpi")
    fun whereARowIsntWholePixelsEachLetterStandsWhereTheFingerFindsIt() {
        show()
        val rail = compose.onNodeWithTag(LETTER_RAIL)
        val bounds = rail.fetchSemanticsNode().boundsInRoot
        val window = compose.onRoot().fetchSemanticsNode().boundsInRoot
        // The rail stands in the middle of the room under the bar, its rows that room's share, as the finger reads them.
        val room = 2 * (window.bottom - bounds.center.y)
        val padding = px(12f)
        val row = (room - 2 * padding) / letters.size
        // Shorter than 16 and a fraction of a pixel off a whole number of them: rounded one by one, the rows would
        // drift from the finger by pixels down to the foot.
        assertTrue("$row", row < px(16f) && abs(row - row.roundToInt()) * (letters.size - 1) > 8f)

        compose.mainClock.autoAdvance = false
        val last = letters.lastIndex
        for (y in listOf(padding + row * last + 3f, padding + row * (last + 1) - 3f)) {
            rail.performTouchInput { down(Offset(bounds.width / 2, y)) }
            frame()
            // The plate stands under the finger, the bubble level with the plate.
            val plate = plate()
            assertTrue("$y on $plate", y.roundToInt() in plate)
            val bubble = compose.onNodeWithTag(LETTER_BUBBLE).fetchSemanticsNode().boundsInRoot
            assertEquals(bounds.top + (plate.first + plate.last + 1) / 2f, bubble.center.y, 2f)
            rail.performTouchInput { up() }
            frame()
        }
    }

    @Test
    fun talkBackReadsEachRowAsOneAndAdjustsTheRail() {
        show()
        val view = talkBack()
        val swissPost = catalog.info(CarrierId.SwissPost, AppLanguage.En)
        val subtitle = CarrierPickerSearch.subtitle(swissPost, null, localizer)
        // The chosen row: its name, then its subtitle and tag, a button, and selected.
        row(CarrierId.SwissPost).info(view).apply {
            assertEquals("${swissPost.displayName}, ${CarrierPickerSearch.accessibilityValue(subtitle, "Has this parcel")}", contentDescription)
            assertEquals("android.widget.Button", className)
            assertEquals("Selected", stateDescription)
            assertTrue(isChecked)
        }
        // The others, and the automatic row while a carrier is chosen, say nothing of being chosen, not "Not selected".
        val planzer = catalog.info(CarrierId.Planzer, AppLanguage.En)
        row(CarrierId.Planzer).info(view).apply {
            assertEquals(listOf(planzer.displayName, CarrierPickerSearch.subtitle(planzer, null, localizer)).filter(String::isNotEmpty).joinToString(", "), contentDescription)
            assertEquals("android.widget.Button", className)
            assertNull(stateDescription)
            assertFalse(isCheckable)
        }
        compose.onNodeWithTag(CARRIER_PICKER_AUTOMATIC).info(view).apply {
            assertNull(stateDescription)
            assertFalse(isCheckable)
        }

        val rail = compose.onNodeWithTag(LETTER_RAIL)
        assertEquals(listOf("Jump to a letter"), rail.fetchSemanticsNode().config[SemanticsProperties.ContentDescription])
        assertEquals("A", rail.state())
        rail.performSemanticsAction(SemanticsActions.SetProgress) { it(letters.indexOf("M").toFloat()) }
        compose.waitForIdle()
        assertEquals("M", rail.state())
        assertAtTop("letter:M")
    }

    @Test
    fun atAnAccessibilityTextSizeTheBarsTitleStaysAtTheDefaultSize() {
        show(fontScale = 2f)
        val layouts = mutableListOf<TextLayoutResult>()
        compose.onNode(isHeading() and hasText("Carrier")).fetchSemanticsNode().config[SemanticsActions.GetTextLayoutResult].action!!.invoke(layouts)
        val layout = layouts.single()
        // Headline's 17 points, whatever the reader's size, as UIKit's inline title.
        assertEquals(17f, with(layout.layoutInput.density) { layout.layoutInput.style.fontSize.toDp().value }, 0.01f)
    }

    @Test
    fun atAnAccessibilityTextSizeTheTagsStandUnderTheWords() {
        val (tagged, plain) = plainCarriers()
        sections = listOf(CarrierPickerSection("fits", "Fits this number", listOf(tagged, plain)))
        tags = mapOf(tagged to CarrierPickerTag("Knows this number", found = false))
        show(selection = null, fontScale = 2f)
        val title = compose.onNode(hasText("Detect automatically"), useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val tag = compose.onNode(hasText("Recommended"), useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val description = compose.onNode(hasText("Peek finds the carrier for you."), useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        assertTrue(tag.top >= title.bottom && description.top >= tag.bottom)
        // The tag's words stand 8 in from its capsule's edge, which lines up with the title.
        assertEquals(title.left + px(8f), tag.left, 1f)
        // A carrier's tag takes a line of its own under its name.
        assertTrue("${height(tagged)} against ${height(plain)}", height(tagged) > height(plain) + px(10f))
    }

    @Test
    fun atTheUsualTextSizesTheTagsStandBesideTheWords() {
        val (tagged, plain) = plainCarriers()
        sections = listOf(CarrierPickerSection("fits", "Fits this number", listOf(tagged, plain)))
        tags = mapOf(tagged to CarrierPickerTag("Knows this number", found = false))
        show(selection = null)
        val tag = compose.onNode(hasText("Recommended"), useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val description = compose.onNode(hasText("Peek finds the carrier for you."), useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        assertTrue(tag.right <= description.left && abs(tag.center.y - description.center.y) < px(8f))
        assertEquals(height(plain), height(tagged))
    }

    @Test
    fun onASheetsGlassTheRowsAndTheScrollEdgeAreTheFormsAsTheyAreOnItsGround() {
        attachment = 0f
        show()
        compose.onNodeWithTag(CARRIER_PICKER_LIST).performScrollToIndex(3)
        val colors = PeekColors(dark = false)
        val planzer = row(CarrierId.Planzer).fetchSemanticsNode().boundsInRoot
        // A row's trailing margin, where nothing is written, and the corner above the bar, where only the edge's band is.
        val margin = (planzer.right - px(4f)).roundToInt() to planzer.center.y.roundToInt()
        val shot = image(SHOT)
        assertColor(Color.Black.copy(alpha = 0.07f).compositeOver(colors.groupedBackground), shot[margin.first, margin.second])
        assertColor(colors.groupedBackground, shot[2, 2])
        attachment = 1f
        val attached = image(SHOT)
        assertColor(colors.secondaryGroupedBackground, attached[margin.first, margin.second])
        assertColor(colors.secondaryGroupedBackground.copy(alpha = 0.95f).compositeOver(colors.groupedBackground), attached[2, 2])
    }

    @Test
    fun beforeTheIdentitiesAreReadTheBarTheFieldAndTheRowsShowAndOnlyTheMarksWait() {
        identities = null
        show()
        compose.onNodeWithText(localizer.text("add.carrier")).assertExists()
        field().assertExists()
        header("section:fits").assertExists()
        compose.onNodeWithTag(CARRIER_PICKER_AUTOMATIC).assertExists()
        val read = row(CarrierId.Planzer).description()
        row(CarrierId.SwissPost).assertIsSelected()
        // The truck's room, 27 by 18 at the row's leading margin: empty, then the truck's colours once they are read.
        fun mark(): Set<Color> {
            val image = image(carrierPickerRowTag(CarrierId.Planzer))
            val xs = px(20f).roundToInt() until px(20f + 27f).roundToInt()
            val ys = (image.height / 2 - px(9f).roundToInt()) until (image.height / 2 + px(9f).roundToInt())
            return xs.flatMap { x -> ys.map { y -> image[x, y] } }.toSet()
        }
        assertEquals(1, mark().size)
        identities = DemoCards.identities
        compose.waitForIdle()
        assertTrue(mark().size > 1)
        assertEquals(read, row(CarrierId.Planzer).description())
        assertTrue(read.orEmpty().startsWith(catalog.info(CarrierId.Planzer, AppLanguage.En).displayName))
    }

    @Test
    fun theFieldWithTheKeyboardKeepsItThroughAWindowMadeAgainAndWithoutLeavesItAway() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        field().performClick()
        field().performTextInput("colis")
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        field().assertIsFocused()
        cancel().assertExists()
        // The keyboard's Search puts it away, and the next window leaves it away, the search still on.
        field().performImeAction()
        field().assertIsNotFocused()
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        field().assertIsNotFocused()
        cancel().assertExists()
        assertEquals("colis", fieldText())
    }

    @Test
    fun theQueryAndTheSectionsStayThroughAWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        field().performTextInput("colis")
        recreation.recreate { sections = listOf(CarrierPickerSection("current", "Current", listOf(CarrierId.GlsCh))) }
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertEquals("colis", fieldText())
        header("results").assertExists()
        compose.onNodeWithTag(CARRIER_PICKER_CLEAR).performClick()
        compose.waitForIdle()
        header("section:fits").assertExists()
        header("section:current").assertDoesNotExist()
    }
}

/** A window's view that keeps each haptic feedback asked of it. */
private class TickingView(context: Context) : View(context) {
    val played = mutableListOf<Int>()

    override fun performHapticFeedback(feedbackConstant: Int): Boolean {
        played += feedbackConstant
        return true
    }
}
