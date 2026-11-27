package com.plhery.peekdeliverytracker.ui.friends

import android.os.VibrationEffect.Composition
import android.os.Vibrator
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsConfiguration
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.PassportBook
import com.plhery.peekdeliverytracker.core.StampProgress
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendCountry
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendUpdate
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.FriendsActivity
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.explanationKey
import com.plhery.peekdeliverytracker.core.titleKey
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.DEMO_EXIT
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.passport.PassportFixtures
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowVibrator
import org.robolectric.shadows.ShadowVibrator.PrimitiveEffect

/**
 * A friend's page (C-S22, C-S29, C-S30, C-AN17) and the reveal's landing and notice (C-S31) on made-up friends: the
 * page out of its card and back, the tab bar hidden; the postcard, the metrics, the friend's stamps beside the reader's
 * in the groups both have, only the friend has with how far the reader is, and only the reader has, by window and text
 * size; the friend's countries; with nothing on either side, the stamps with all of them or the next three, their
 * popovers and progress; stats kept private; manage friendship, the removal asked first, waiting with no back and
 * closing the page, refused, and the demo's; a friend revealed landing in the middle, acknowledged; the accepted notice
 * opening the friend or dismissed, and under a sheet for TalkBack.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class FriendPageTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private val client get() = source.friendsClient
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    private val owner = object : LifecycleOwner {
        val registry = LifecycleRegistry.createUnsafe(this).apply { currentState = Lifecycle.State.RESUMED }
        override val lifecycle: Lifecycle get() = registry
    }

    private var shownSession by mutableStateOf<AuthenticationState>(reader)

    /** The friends tab of [session] with the reader's [parcels] at [fontScale], chosen from the tab bar unless [choose] is false. */
    private fun show(
        session: AuthenticationState = reader,
        fontScale: Float = 1f,
        choose: Boolean = true,
        reduceMotion: Boolean = true,
        recreation: Recreation? = null,
        parcels: List<Parcel> = DemoCards.parcels(),
        friends: FakeFriendsClient.() -> Unit = {},
    ) {
        shownSession = session
        source = FakeDeliveries(parcels, session)
        client.friends()
        compose.waitsForMaps()
        val content = @Composable {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalLifecycleOwner provides owner) {
                RootHost(state = shownSession, dark = false, reduceMotion = reduceMotion, source = source)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        settle()
        if (choose) choose("Friends")
    }

    private fun choose(label: String) {
        compose.onNode(hasContentDescription(label) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private val vibrator: Vibrator get() = compose.activity.getSystemService(VibratorManager::class.java).defaultVibrator

    /** What the phone played since it was last asked, the haptics the test has just set off; the vibrator ready again. */
    private fun felt(): List<PrimitiveEffect> {
        val played = shadowOf(vibrator).primitiveSegmentsInPrimitiveEffects.toList()
        ShadowVibrator.reset()
        shadowOf(vibrator).setSupportedPrimitives(listOf(Composition.PRIMITIVE_TICK, Composition.PRIMITIVE_LOW_TICK))
        return played
    }

    /** A light impact at [intensity], as the phone plays it. */
    private fun light(intensity: Float) = listOf(PrimitiveEffect(Composition.PRIMITIVE_TICK, intensity, 0))

    private fun text(key: String, variables: Map<String, Any> = emptyMap()) = localizer.text(key, variables)

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun shows(text: String) = compose.onAllNodes(hasText(text)).fetchSemanticsNodes().isNotEmpty()

    private fun semantics(tag: String): SemanticsConfiguration = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config

    private fun label(tag: String): String = semantics(tag)[SemanticsProperties.ContentDescription].single()

    private fun words(tag: String): List<String> {
        val words = mutableListOf<String>()
        fun walk(node: SemanticsNode) {
            node.config.getOrNull(SemanticsProperties.Text)?.let { texts -> words += texts.map { it.text } }
            node.children.forEach(::walk)
        }
        walk(compose.onNodeWithTag(tag).fetchSemanticsNode())
        return words
    }

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        settle()
    }

    private fun press(tag: String) {
        compose.onNodeWithTag(tag).performClick()
        settle()
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    private fun cards(): List<String> = compose.onAllNodes(
        SemanticsMatcher("a friend's card") { it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith("friends.card.") == true },
    ).fetchSemanticsNodes().sortedBy { it.boundsInRoot.top }.map { it.config[SemanticsProperties.Text].first().text }

    /** The stamps the page shows with nothing to put side by side, in their order. */
    private fun stamps(): List<FriendStamp> = FriendStamp.entries.filter { exists(friendStampTag(it)) }

    /** The stamps the page puts side by side, in the stamps' order. */
    private fun compared(): List<FriendStamp> = FriendStamp.entries.filter { exists(friendCompareTag(it)) }

    /** The friend's countries, as they read across then down. */
    private fun countries(): List<String> = compose.onAllNodes(
        SemanticsMatcher("a friend's country") { it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith(friendCountryTag("")) == true },
        useUnmergedTree = true,
    ).fetchSemanticsNodes().sortedWith(compareBy({ it.boundsInRoot.top }, { it.boundsInRoot.left }))
        .map { it.config[SemanticsProperties.TestTag].removePrefix(friendCountryTag("")) }

    /** What the stamp's popover says, its title then its explanation; null with none open. */
    private fun popover(): List<String>? = compose.onAllNodes(hasTestTag(FRIENDS_POPOVER), useUnmergedTree = true).fetchSemanticsNodes()
        .singleOrNull()?.children?.map { it.config.getOrNull(SemanticsProperties.Text)?.joinToString().orEmpty() }

    private fun tabBarShows(): Boolean = compose.onAllNodes(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).fetchSemanticsNodes().isNotEmpty()

    private fun requests(action: FriendsAction): List<FriendsActionRequest> = client.requests.filter { it.action == action }

    private fun open(friend: FriendCard) = tap(friendCardTag(friend.id))


    /** How many stamps stand in the row of the first one shown, side by side or not. */
    private fun firstRow(): Int {
        val tag = if (compared().isEmpty()) ::friendStampTag else ::friendCompareTag
        fun top(stamp: FriendStamp) = compose.onNodeWithTag(tag(stamp), useUnmergedTree = true).getUnclippedBoundsInRoot().top
        val shown = FriendStamp.entries.filter { exists(tag(it)) }
        return shown.count { top(it) == top(shown.first()) }
    }

    private fun title(stamp: FriendStamp) = text(stamp.titleKey)

    /** A group's heading as it reads: its title, then how many it holds. */
    private fun heading(group: StampGroup, count: Int, name: String = "") = "${text(group.headingKey, mapOf("name" to name))} $count"

    /** The reader's one delivery, first scanned in Switzerland and delivered 30 hours later: First and Express. */
    private val firstAndExpress = PassportFixtures.fromCountries("CH" to 1)

    /** Mila as the web's page test has her: First, Ten, Connected and Express. */
    private val mila = FriendCard(
        UUID.fromString("15000000-0000-4000-8000-000000000015"), "Mila",
        FriendStats(12, 3, listOf(FriendStamp.First, FriendStamp.Ten, FriendStamp.Connected, FriendStamp.Express)),
    )

    /** Ev, with every stamp. */
    private val ev = FriendCard(UUID.fromString("14000000-0000-4000-8000-000000000014"), "Ev", FriendStats(30, 1, FriendStamp.entries))

    /** Where the friend's card stands from the window's leading edge, as drawn. */
    private fun left(friend: FriendCard) = compose.onNodeWithTag(friendCardTag(friend.id), useUnmergedTree = true).getUnclippedBoundsInRoot().left

    // The page (C-S29) and its zoom (C-AN17).

    @Test
    fun aFriendsPageHidesTheTabBarAndTheCircleAndItsBackGoesBackToThem() {
        show()
        open(FriendFixtures.ada)
        assertTrue(exists(FRIENDS_PAGE))
        assertFalse(tabBarShows())
        // The circle under the page is out of TalkBack's reach; the bar reads its title as a heading, and its back.
        assertEquals(emptyList<String>(), cards())
        assertEquals(listOf(text("passport.title")), words(FRIENDS_PAGE_BAR))
        compose.onNode(hasText(text("passport.title")) and SemanticsMatcher.keyIsDefined(SemanticsProperties.Heading)).assertExists()
        assertEquals(text("friends.title"), label(FRIENDS_PAGE_BACK))
        assertEquals(Role.Button, semantics(FRIENDS_PAGE_BACK)[SemanticsProperties.Role])
        press(FRIENDS_PAGE_BACK)
        assertFalse(exists(FRIENDS_PAGE))
        assertTrue(tabBarShows())
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    @Test
    fun outOfItsCardAndBackIntoItThePageComesAndGoesAndTheTabBarWithIt() {
        show(reduceMotion = false)
        open(FriendFixtures.bo)
        assertTrue(exists(FRIENDS_PAGE))
        assertFalse(tabBarShows())
        back()
        assertFalse(exists(FRIENDS_PAGE))
        // Back in its card, the page leaves the tab bar to come back a little after, as the iPhone's does.
        assertFalse(tabBarShows())
        settle()
        assertTrue(tabBarShows())
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    @Test
    fun thePageShowsThePostcardTheMetricsAndTheFriendsStampsBesideTheReaders() {
        show(parcels = firstAndExpress) { snapshot = FriendFixtures.circle(listOf(mila)) }
        open(mila)
        assertEquals(listOf("Mila"), words(FRIENDS_POSTCARD))
        assertEquals(listOf("12", text("passport.delivered", mapOf("count" to 12)), "3 days", text("passport.average")), words(FRIENDS_METRICS))
        // The reader has First and Express: both have those two; only Mila has Ten and Connected, each with how far the
        // reader is; none is the reader's alone.
        assertEquals(listOf(heading(StampGroup.Both, 2), title(FriendStamp.First), title(FriendStamp.Express)), words(friendGroupTag(StampGroup.Both)))
        assertEquals(
            listOf(heading(StampGroup.Friend, 2, "Mila"), title(FriendStamp.Ten), "You’re at 1 of 10", title(FriendStamp.Connected), "You’re at 1 of 3"),
            words(friendGroupTag(StampGroup.Friend)),
        )
        assertFalse(exists(friendGroupTag(StampGroup.Reader)))
        compose.onNode(hasText(heading(StampGroup.Friend, 2, "Mila")) and SemanticsMatcher.keyIsDefined(SemanticsProperties.Heading)).assertExists()
        // Side by side, no stamp opens a popover, and the stamps on their own don't show.
        assertFalse(SemanticsActions.OnClick in semantics(friendCompareTag(FriendStamp.Ten)))
        assertNull(semantics(friendCompareTag(FriendStamp.Ten)).getOrNull(SemanticsProperties.Role))
        press(friendCompareTag(FriendStamp.Ten))
        assertNull(popover())
        assertEquals(emptyList<FriendStamp>(), stamps())
        assertFalse(exists(FRIENDS_SHOW_ALL))
        assertFalse(exists(FRIENDS_COUNTRIES))
    }

    @Test
    fun theStampsGoInTheirGroupsInTheStampsOrderWithTheReadersProgressUnderTheFriendsOwn() {
        val book = PassportBook(firstAndExpress)
        val groups = compareStamps(book, listOf(FriendStamp.Express, FriendStamp.Connected, FriendStamp.Ten, FriendStamp.First))
        assertEquals(
            listOf(StampGroup.Both to listOf("first", "express"), StampGroup.Friend to listOf("ten", "connected")),
            groups.map { (group, stamps) -> group to stamps.map { it.key } },
        )
        assertEquals(listOf("You’re at 1 of 10", "You’re at 1 of 3"), groups[1].second.map { yourProgress(it, localizer) })
        // A stamp with nothing to count up to says nothing.
        assertNull(yourProgress(book.stamps.first { it.key == "express" }, localizer))
        // Every stamp earned against a friend with First alone: the rest are the reader's own; nothing on either side, no group.
        assertEquals(
            listOf(StampGroup.Both to listOf("first"), StampGroup.Reader to FriendStamp.entries.drop(1).map { it.rawValue }),
            compareStamps(PassportBook(PassportFixtures.everyStamp()), listOf(FriendStamp.First)).map { (group, stamps) -> group to stamps.map { it.key } },
        )
        assertEquals(emptyList<Pair<StampGroup, List<StampProgress>>>(), compareStamps(PassportBook(emptyList()), emptyList()))
    }

    @Test
    fun aFriendWithNoDeliveryYetShowsADashAndBesideTheReadersStampsOnlyTheirs() {
        show(parcels = firstAndExpress)
        open(FriendFixtures.di)
        assertEquals(listOf("Di"), words(FRIENDS_POSTCARD))
        assertEquals(listOf("0", text("passport.delivered", mapOf("count" to 0)), "—", text("passport.average")), words(FRIENDS_METRICS))
        assertEquals(listOf(heading(StampGroup.Reader, 2), title(FriendStamp.First), title(FriendStamp.Express)), words(friendGroupTag(StampGroup.Reader)))
        assertFalse(exists(friendGroupTag(StampGroup.Both)))
        assertFalse(exists(friendGroupTag(StampGroup.Friend)))
    }

    @Test
    fun withNothingOnEitherSideThePageShowsTheFirstThreeStamps() {
        show(parcels = emptyList())
        open(FriendFixtures.di)
        assertEquals(emptyList<FriendStamp>(), compared())
        assertTrue(shows(text("passport.stamps")))
        assertEquals(listOf(FriendStamp.First, FriendStamp.Ten, FriendStamp.Connected), stamps())
        assertEquals(3, firstRow())
        // Each stamp reads as Passport's, a button: its title, and how far along it is or that it isn't.
        assertEquals("${title(FriendStamp.Ten)}, 0 / 10", label(friendStampTag(FriendStamp.Ten)))
        assertEquals("${title(FriendStamp.Connected)}, ${text("passport.notEarned")}", label(friendStampTag(FriendStamp.Connected)))
        assertEquals(Role.Button, semantics(friendStampTag(FriendStamp.Ten))[SemanticsProperties.Role])
        assertEquals(listOf(text("passport.showAll")), words(FRIENDS_SHOW_ALL))
        tap(FRIENDS_SHOW_ALL)
        assertEquals(FriendStamp.entries, stamps())
        assertEquals(4, firstRow())
        assertEquals(listOf(text("passport.showLess")), words(FRIENDS_SHOW_ALL))
        tap(FRIENDS_SHOW_ALL)
        assertEquals(3, stamps().size)
    }

    @Test
    fun aFriendWhoKeepsTheirStatsPrivateShowsNoneButSaysSo() {
        show()
        open(FriendFixtures.cy)
        assertEquals(listOf("Cy"), words(FRIENDS_POSTCARD))
        assertEquals(listOf(text("friends.privateStats")), words(FRIENDS_PRIVATE_STATS))
        assertFalse(exists(FRIENDS_METRICS))
        assertFalse(exists(FRIENDS_COMPARE))
        assertEquals(emptyList<FriendStamp>(), stamps())
        assertFalse(exists(FRIENDS_SHOW_ALL))
        assertTrue(exists(FRIENDS_MANAGE_FRIENDSHIP))
    }

    @Test
    fun withEveryStampEarnedTheReaderHasTheFriendsAndTheirOwn() {
        val nine = FriendCard(ev.id, "Ev", FriendStats(30, 1, FriendStamp.entries.take(9)))
        show(parcels = PassportFixtures.everyStamp()) { snapshot = FriendFixtures.circle(listOf(nine)) }
        open(nine)
        assertEquals(FriendStamp.entries, compared())
        assertEquals(listOf(heading(StampGroup.Both, 9)) + FriendStamp.entries.take(9).map(::title), words(friendGroupTag(StampGroup.Both)))
        assertEquals(listOf(heading(StampGroup.Reader, 3)) + FriendStamp.entries.drop(9).map(::title), words(friendGroupTag(StampGroup.Reader)))
        assertFalse(exists(friendGroupTag(StampGroup.Friend)))
    }

    @Test
    fun sideBySideTheStampsGoFourToARow() {
        show(parcels = emptyList()) { snapshot = FriendFixtures.circle(listOf(ev)) }
        open(ev)
        assertEquals(FriendStamp.entries, compared())
        assertEquals(4, firstRow())
    }

    @Test
    @Config(qualifiers = "w340dp-h844dp-xhdpi")
    fun onAWindowNarrowerThan360TheStampsGoThreeToARow() {
        show(parcels = emptyList()) { snapshot = FriendFixtures.circle(listOf(ev)) }
        open(ev)
        assertEquals(3, firstRow())
    }

    @Test
    fun fromTheXxLargeTextTheStampsGoThreeToARow() {
        show(fontScale = 1.3f, parcels = emptyList()) { snapshot = FriendFixtures.circle(listOf(ev)) }
        open(ev)
        assertEquals(3, firstRow())
    }

    @Test
    fun atTheAccessibilitySizesTheStampsGoTwoToARow() {
        show(fontScale = 2f, parcels = emptyList()) { snapshot = FriendFixtures.circle(listOf(ev)) }
        open(ev)
        assertEquals(2, firstRow())
    }

    // The friend's countries.

    @Test
    fun theFriendsCountriesGoMostParcelsFirstAndSayHowManyBothHave() {
        val travelled = FriendCard(
            mila.id, "Mila",
            FriendStats(13, 3, listOf(FriendStamp.First), listOf(FriendCountry("CH", 9), FriendCountry("DE", 1), FriendCountry("FR", 2), FriendCountry("US", 1))),
        )
        show(parcels = PassportFixtures.fromCountries("CH" to 1, "FR" to 1, "JP" to 1)) { snapshot = FriendFixtures.circle(listOf(travelled)) }
        open(travelled)
        // After the stamps; the reader's parcels went through Switzerland and France too.
        assertTrue(compose.onNodeWithTag(FRIENDS_COUNTRIES).getUnclippedBoundsInRoot().top >= compose.onNodeWithTag(friendGroupTag(StampGroup.Both)).getUnclippedBoundsInRoot().bottom)
        assertEquals(listOf("${text("passport.countriesLabel", mapOf("count" to 4))} 4", "You both have 2"), words(FRIENDS_COUNTRIES))
        // Most parcels first, then by name: Germany before the United States; two to a row.
        assertEquals(listOf("CH", "FR", "DE", "US"), countries())
        assertEquals(
            compose.onNodeWithTag(friendCountryTag("CH"), useUnmergedTree = true).getUnclippedBoundsInRoot().top,
            compose.onNodeWithTag(friendCountryTag("FR"), useUnmergedTree = true).getUnclippedBoundsInRoot().top,
        )
        assertEquals("Switzerland, 9 parcels", label(friendCountryTag("CH")))
        assertEquals("Germany, 1 parcel", label(friendCountryTag("DE")))
    }

    @Test
    fun withNoCountryInCommonTheCountriesSayNothingMore() {
        val travelled = FriendCard(mila.id, "Mila", FriendStats(1, 3, listOf(FriendStamp.First), listOf(FriendCountry("JP", 1))))
        val none = FriendCard(ev.id, "Ev", FriendStats(1, 3, listOf(FriendStamp.First), emptyList()))
        show(parcels = emptyList()) { snapshot = FriendFixtures.circle(listOf(travelled, none)) }
        open(travelled)
        assertEquals(listOf("${text("passport.countriesLabel", mapOf("count" to 1))} 1"), words(FRIENDS_COUNTRIES))
        assertEquals(listOf("JP"), countries())
        // None shared shows no section.
        back()
        open(none)
        assertFalse(exists(FRIENDS_COUNTRIES))
    }

    // The stamp's popover, with nothing on either side to put side by side (C-S30).

    @Test
    fun aStampsPopoverSaysHowItIsEarnedAndUntilThenHowFarAlong() {
        show(parcels = emptyList())
        open(FriendFixtures.di)
        assertNull(popover())
        tap(friendStampTag(FriendStamp.Ten))
        assertEquals(listOf(title(FriendStamp.Ten), text(FriendStamp.Ten.explanationKey) + "\n0 / 10"), popover())
        // The back closes the popover and leaves the page.
        back()
        assertNull(popover())
        assertTrue(exists(FRIENDS_PAGE))
        // With no count to reach: how it is earned alone.
        tap(friendStampTag(FriendStamp.Connected))
        assertEquals(listOf(title(FriendStamp.Connected), text(FriendStamp.Connected.explanationKey)), popover())
    }

    @Test
    fun anUnearnedExpressSaysWithin48Hours() {
        show(parcels = emptyList())
        open(FriendFixtures.di)
        tap(FRIENDS_SHOW_ALL)
        tap(friendStampTag(FriendStamp.Express))
        assertEquals(text(FriendStamp.Express.explanationKey) + "\n" + text("passport.underTwoDays"), popover()?.last())
    }

    @Test
    fun theProgressCountsUpToItsMarkAndTheExpressSaysWithin48Hours() {
        val within = text("passport.underTwoDays")
        assertEquals("\n1 / 1", progress(FriendStamp.First, earned = false, count = 3, underTwoDays = within))
        assertEquals("\n0 / 1", progress(FriendStamp.First, earned = false, count = 0, underTwoDays = within))
        assertEquals("\n10 / 10", progress(FriendStamp.Ten, earned = false, count = 12, underTwoDays = within))
        assertEquals("\n12 / 25", progress(FriendStamp.TheRegular, earned = false, count = 12, underTwoDays = within))
        assertEquals("\n$within", progress(FriendStamp.Express, earned = false, count = 12, underTwoDays = within))
        assertEquals("", progress(FriendStamp.WorthTheWait, earned = false, count = 12, underTwoDays = within))
        assertEquals("", progress(FriendStamp.TheRegular, earned = true, count = 30, underTwoDays = within))
    }

    // Manage friendship and the removal (C-S29).

    @Test
    fun removingAFriendAsksFirstWaitsWithNoBackThenClosesTheirPage() {
        show()
        open(FriendFixtures.bo)
        assertFalse(exists(FRIENDS_REMOVE))
        assertEquals(listOf(text("friends.manageFriendship")), words(FRIENDS_MANAGE_FRIENDSHIP))
        tap(FRIENDS_MANAGE_FRIENDSHIP)
        assertEquals(listOf(text("friends.remove")), words(FRIENDS_REMOVE))
        tap(FRIENDS_REMOVE)
        assertTrue(shows(text("friends.removeTitle", mapOf("name" to "Bo"))))
        assertTrue(shows(text("friends.removeDetail")))
        assertEquals(listOf(text("friends.remove")), words(FRIENDS_CONFIRM_REMOVE))
        // Cancel goes back to Remove friend.
        tap(FRIENDS_CANCEL_REMOVE)
        assertFalse(exists(FRIENDS_CONFIRM_REMOVE))
        tap(FRIENDS_REMOVE)
        val acting = CompletableDeferred<Unit>()
        client.acting = acting
        client.answer = { FriendsActionResponse(snapshot = FriendFixtures.circle(listOf(FriendFixtures.ada, FriendFixtures.cy, FriendFixtures.di))) }
        tap(FRIENDS_CONFIRM_REMOVE)
        assertEquals(listOf(FriendsActionRequest(FriendsAction.RemoveFriend, friendId = FriendFixtures.bo.id)), requests(FriendsAction.RemoveFriend))
        // While it is done: no ‹, the back taken without closing the page, and Remove friend and Cancel off.
        assertFalse(exists(FRIENDS_PAGE_BACK))
        back()
        assertTrue(exists(FRIENDS_PAGE))
        compose.onNodeWithTag(FRIENDS_CONFIRM_REMOVE).assertIsNotEnabled()
        compose.onNodeWithTag(FRIENDS_CANCEL_REMOVE).assertIsNotEnabled()
        acting.complete(Unit)
        settle()
        assertFalse(exists(FRIENDS_PAGE))
        assertTrue(tabBarShows())
        assertEquals(listOf("Ada", "Cy", "Di"), cards())
    }

    @Test
    fun aRemovalTheServiceRefusesSaysWhyOnThePageAndKeepsIt() {
        show()
        open(FriendFixtures.bo)
        tap(FRIENDS_MANAGE_FRIENDSHIP)
        tap(FRIENDS_REMOVE)
        client.actionFailure = FriendFixtures.unavailable()
        tap(FRIENDS_CONFIRM_REMOVE)
        assertTrue(exists(FRIENDS_PAGE))
        // Said once, on the page: the circle's under it is out of reach.
        assertEquals(text("friends.actionFailed"), words(FRIENDS_ERROR).single())
        assertTrue(exists(FRIENDS_PAGE_BACK))
        compose.onNodeWithTag(FRIENDS_CONFIRM_REMOVE).assertIsEnabled()
    }

    @Test
    fun theDemosFriendIsRemovedFromTheirPageInMemory() {
        show(AuthenticationState.Demo)
        tap(friendCardTag(UUID.fromString("33000000-0000-4000-8000-000000000003")))
        assertEquals(listOf("Sam"), words(FRIENDS_POSTCARD))
        // The demo's bar stands on the page too, under its bar.
        assertEquals(1, compose.onAllNodes(hasTestTag(DEMO_EXIT)).fetchSemanticsNodes().size)
        assertTrue(compose.onNodeWithTag(DEMO_EXIT).getUnclippedBoundsInRoot().top >= compose.onNodeWithTag(FRIENDS_PAGE_BAR).getUnclippedBoundsInRoot().bottom)
        tap(FRIENDS_MANAGE_FRIENDSHIP)
        tap(FRIENDS_REMOVE)
        tap(FRIENDS_CONFIRM_REMOVE)
        assertFalse(exists(FRIENDS_PAGE))
        assertEquals(listOf("Mila", "Noé"), cards())
        assertTrue(client.requests.isEmpty())
    }

    @Test
    fun inTheBackgroundAFriendsPageClosesAndTheCircleLoadsAgainInFront() {
        show()
        open(FriendFixtures.ada)
        compose.runOnUiThread { owner.registry.currentState = Lifecycle.State.CREATED }
        settle()
        assertFalse(exists(FRIENDS_PAGE))
        compose.runOnUiThread { owner.registry.currentState = Lifecycle.State.RESUMED }
        settle()
        assertTrue(tabBarShows())
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    // The reveal's landing (C-S31).

    @Test
    fun aFriendRevealedLandsFromTheRightAndIsAcknowledged() {
        show(reduceMotion = false)
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.di.id) }
        compose.mainClock.advanceTimeBy(90)
        compose.waitForIdle()
        // First, out of sight 100 to the right of its place, until the circle has scrolled to it.
        assertEquals(listOf("Di", "Ada", "Bo", "Cy"), cards())
        assertEquals(100f, (left(FriendFixtures.di) - left(FriendFixtures.ada)).value, 0.5f)
        assertEquals(FriendFixtures.di.id, source.friendsActivity.focusId)
        assertTrue(requests(FriendsAction.AcknowledgeFriend).isEmpty())
        compose.mainClock.advanceTimeBy(1_500)
        compose.waitForIdle()
        assertEquals(0f, (left(FriendFixtures.di) - left(FriendFixtures.ada)).value, 0.5f)
        assertEquals(listOf(FriendFixtures.di.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
        assertNull(source.friendsActivity.focusId)
        compose.mainClock.autoAdvance = true
    }

    @Test
    fun underReduceMotionAFriendRevealedComesInWhereItStands() {
        show()
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.di.id) }
        compose.mainClock.advanceTimeBy(90)
        compose.waitForIdle()
        assertEquals(0f, (left(FriendFixtures.di) - left(FriendFixtures.ada)).value, 0.5f)
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertEquals(listOf(FriendFixtures.di.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
        compose.mainClock.autoAdvance = true
    }

    @Test
    fun aFriendRevealedFromFarDownALongCircleIsBroughtIntoSight() {
        val many = (1..14).map { index ->
            FriendCard(UUID.fromString("2%07d-0000-4000-8000-%012d".format(index, index)), "Friend $index", FriendStats(index, 2, listOf(FriendStamp.First)))
        }
        show { snapshot = FriendFixtures.circle(many) }
        val last = friendCardTag(many.last().id)
        compose.onNodeWithTag(last).performScrollTo()
        settle()
        val window = compose.onRoot().getUnclippedBoundsInRoot()
        compose.runOnUiThread { source.friendsActivity.reveal(many.last().id) }
        settle()
        // It comes first, and the circle scrolls back up to it.
        val card = compose.onNodeWithTag(last, useUnmergedTree = true).getUnclippedBoundsInRoot()
        assertTrue(card.top < compose.onNodeWithTag(friendCardTag(many.first().id), useUnmergedTree = true).getUnclippedBoundsInRoot().top)
        assertTrue("${card.top}", card.top > 0.dp && card.bottom < window.height)
        assertEquals(listOf(many.last().id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
    }

    @Test
    fun eachPresentationLandsItsFriendOnce() {
        show()
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.bo.id) }
        settle()
        assertEquals(listOf("Bo", "Ada", "Cy", "Di"), cards())
        // Landed and let go, Bo stays first while the tab shows, through the minute's loads.
        compose.mainClock.advanceTimeBy(60_000)
        compose.waitForIdle()
        assertEquals(listOf("Bo", "Ada", "Cy", "Di"), cards())
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.cy.id) }
        settle()
        assertEquals(listOf("Cy", "Ada", "Bo", "Di"), cards())
        assertEquals(listOf(FriendFixtures.bo.id, FriendFixtures.cy.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
    }

    @Test
    fun aLandedFriendStaysInPlaceThroughTheMinutesLoad() {
        show(reduceMotion = false)
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.di.id) }
        settle()
        settle()
        assertEquals(0f, (left(FriendFixtures.di) - left(FriendFixtures.ada)).value, 0.5f)
        // Frame by frame up to the minute's load and just after it: a landing begun again would put Di back 100 to the
        // right, out of sight, for the 120 ms before it scrolls.
        compose.mainClock.autoAdvance = false
        val loads = client.loads
        var frames = 0
        while (client.loads == loads && frames < 1_000) {
            compose.mainClock.advanceTimeBy(100)
            compose.waitForIdle()
            frames++
        }
        assertEquals(loads + 1, client.loads)
        repeat(8) {
            compose.mainClock.advanceTimeBy(20)
            compose.waitForIdle()
            assertEquals(0f, (left(FriendFixtures.di) - left(FriendFixtures.ada)).value, 0.5f)
            assertEquals(listOf("Di", "Ada", "Bo", "Cy"), cards())
        }
        compose.mainClock.autoAdvance = true
        assertEquals(listOf(FriendFixtures.di.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
    }

    @Test
    fun aLandingCalledOffDuringItsAcknowledgeStillLetsGoOfTheFocus() {
        show()
        val held = CompletableDeferred<Unit>()
        client.acting = held
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.bo.id) }
        settle()
        assertEquals(listOf(FriendFixtures.bo.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
        assertEquals(FriendFixtures.bo.id, source.friendsActivity.focusId)
        // The tab left while the service hasn't answered: the focus goes all the same, as the iPhone's `try?` lets it.
        choose("Passport")
        assertNull(source.friendsActivity.focusId)
        held.complete(Unit)
        settle()
        choose("Friends")
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
        assertFalse(exists(FRIENDS_UNAVAILABLE_FRIEND))
    }

    @Test
    fun aRecreatedWindowKeepsTheFriendRevealedFirstAndTheStampsPopover() {
        val recreation = Recreation(compose)
        show(recreation = recreation, parcels = emptyList())
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.cy.id) }
        settle()
        assertEquals(listOf("Cy", "Ada", "Bo", "Di"), cards())
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        settle()
        assertEquals(listOf("Cy", "Ada", "Bo", "Di"), cards())
        open(FriendFixtures.di)
        tap(friendStampTag(FriendStamp.Ten))
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(exists(FRIENDS_PAGE))
        assertEquals(listOf(title(FriendStamp.Ten), text(FriendStamp.Ten.explanationKey) + "\n0 / 10"), popover())
    }

    // The accepted notice (C-S31).

    private val accepted = FriendsActivity(updates = listOf(FriendUpdate(FriendFixtures.bo.id, "Bo")))

    @Test
    fun theAcceptedNoticeOpensTheFriendOnTheFriendsTabAndAcknowledgesThem() {
        show(choose = false) { activity = accepted }
        assertFalse(exists(PeekTab.Friends.screen))
        assertEquals(listOf(text("friends.invitationAccepted", mapOf("name" to "Bo"))), words(FRIENDS_NOTICE_OPEN))
        assertEquals(Role.Button, semantics(FRIENDS_NOTICE_OPEN)[SemanticsProperties.Role])
        assertEquals(text("friends.dismissUpdate"), label(FRIENDS_NOTICE_DISMISS))
        press(FRIENDS_NOTICE_OPEN)
        assertTrue(exists(PeekTab.Friends.screen))
        assertFalse(exists(FRIENDS_NOTICE))
        assertEquals(listOf("Bo", "Ada", "Cy", "Di"), cards())
        assertEquals(listOf(FriendFixtures.bo.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
        assertNull(source.friendsActivity.focusId)
    }

    @Test
    fun theAcceptedNoticeDismissedIsAcknowledgedAndStaysAway() {
        show(choose = false) { activity = accepted }
        press(FRIENDS_NOTICE_DISMISS)
        assertFalse(exists(FRIENDS_NOTICE))
        assertFalse(exists(PeekTab.Friends.screen))
        assertEquals(listOf(FriendFixtures.bo.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
        // The activity read again as the app comes back to the front still holds Bo: dismissed, the notice stays away.
        compose.runOnUiThread { owner.registry.currentState = Lifecycle.State.CREATED }
        settle()
        compose.runOnUiThread { owner.registry.currentState = Lifecycle.State.RESUMED }
        settle()
        assertFalse(exists(FRIENDS_NOTICE))
    }

    @Test
    fun underASheetTheAcceptedNoticeIsOutOfTalkBacksReach() {
        show { activity = accepted }
        val notice = text("friends.invitationAccepted", mapOf("name" to "Bo"))
        assertTrue(shows(notice))
        tap(FRIENDS_SHARING)
        assertFalse(shows(notice))
        press(FRIENDS_CLOSE)
        assertTrue(shows(notice))
    }

    @Test
    fun theDemoShowsNoNotice() {
        show(AuthenticationState.Demo, choose = false) { activity = accepted }
        assertFalse(exists(FRIENDS_NOTICE))
    }

    /** A stamp's popover opening plays a light impact at 0.6, and closing it another (C-H06). */
    @Test
    fun aStampsPopoverOpeningAndClosingPlaysALightImpactEachTime() {
        show(parcels = emptyList())
        open(FriendFixtures.di)
        felt()
        tap(friendStampTag(FriendStamp.Ten))
        assertEquals(light(0.6f), felt())
        back()
        assertEquals(light(0.6f), felt())
    }
}
