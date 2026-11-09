package com.plhery.peekdeliverytracker.ui

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.FirstOpenJourney
import com.plhery.peekdeliverytracker.core.Parcel
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelTrackingLink
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.IDLE_MILLIS
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_LINK
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import java.util.concurrent.atomic.AtomicInteger
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * A tap on a carrier's link (B-G05), on this device's own window, as the iPhone's `Link(destination:)` with its
 * `simultaneousGesture` in ParcelDetailView's and ParcelLinkSheet's `carrierLinks`: the system is asked once to view
 * the link's page (ACTION_VIEW on its URL, left to the reader's browser, no Custom Tab), "parcel-carrier-link" is
 * counted once, and the visit is told to the page's question (`feedback.visited(link.name)`), which asks on the way
 * back from the carrier's site. On the sample (`sample == nil` in ParcelDetailView) there is no link, so nothing is
 * opened, counted or asked. The same in a link's sheet, its number whole (the carrier's tracking page) or hidden (the
 * carrier's search page). The walk keeps the browser from opening ([ParcelWalk]'s monitor answers each view for the
 * system): the window is never stopped and keeps the focus, so nothing is left over the next test's. Signed in as the
 * synthetic reader@example.com on fake deliveries and links whose stores answer every read in memory, the page beside
 * the list or over it as the window lays them out. Shots in carrierlink/<row>-<moment>.png.
 */
@RunWith(AndroidJUnit4::class)
class CarrierLinkDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /** A parcel its carrier answers for, with one number and its carrier's one page. */
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")

    private fun inPage(tag: String): SemanticsMatcher = hasTestTag(tag) and hasAnyAncestor(hasTestTag(PARCEL_PAGE))

    private fun found(matcher: SemanticsMatcher): Int = compose.onAllNodes(matcher, useUnmergedTree = true).fetchSemanticsNodes().size

    /** How often the deliveries counted a carrier's page opened. */
    private fun counted(): Int = walk.onUi { walk.source.actions.count { it == CARRIER_LINK_ACTION } }

    private fun word(parcel: Parcel): ParcelFeedbackModel.Word? = walk.onUi { walk.source.feedbacks.of(parcel.id)?.word }

    /** The button that opens `link`'s page, as TalkBack meets it. */
    private fun button(link: ParcelTrackingLink, place: String): SemanticsMatcher =
        hasTestTag(PAGE_CARRIER_LINK) and hasText(walk.text("detail.carrierWebsite", "carrier" to link.name)) and hasAnyAncestor(hasTestTag(place))

    /** What `matcher` finds first, scrolled into the window clear of the tab bar where it scrolls. */
    private fun reveal(matcher: SemanticsMatcher) {
        if (compose.onAllNodes(matcher and hasAnyAncestor(hasScrollAction())).fetchSemanticsNodes().isNotEmpty()) {
            compose.reveal(compose.onAllNodes(matcher).onFirst(), hasScrollAction() and hasAnyDescendant(matcher))
        }
        walk.settle()
    }

    /** The system asked once to view `link`'s page in the reader's own browser: no app named, no Custom Tab. */
    private fun assertOpened(link: ParcelTrackingLink) {
        val asked = walk.asked()
        assertEquals("One page asked of the system: $asked", 1, asked.size)
        val intent = asked.single()
        assertEquals(Intent.ACTION_VIEW, intent.action)
        assertEquals(link.url, intent.dataString)
        assertNull("No app named", intent.component)
        assertNull("No app named", intent.`package`)
        assertFalse("No Custom Tab", intent.hasExtra(CustomTabsIntent.EXTRA_SESSION))
    }

    /**
     * `act`, after which the window was never stopped, is still resumed and has the focus once the screen is idle: no
     * browser, nor anything else, came over it. It is paused and resumed once, as the system hands it the monitor's
     * answer to its view (`Activity.startActivityForResult` sends an answered start back as a result, which the
     * window takes paused): that is the walk's, not the app's.
     */
    private fun staysInFront(act: () -> Unit) {
        val stops = AtomicInteger()
        val observer = LifecycleEventObserver { _, event -> if (event == Lifecycle.Event.ON_STOP) stops.incrementAndGet() }
        walk.onUi { compose.activity.lifecycle.addObserver(observer) }
        try {
            act()
            walk.settle()
            runCatching { instrumentation.uiAutomation.waitForIdle(IDLE_MILLIS, LONG_MILLIS) }
            walk.settle()
            assertEquals("Nothing came over the window", 0, stops.get())
            assertEquals(Lifecycle.State.RESUMED, walk.onUi { compose.activity.lifecycle.currentState })
            assertTrue("The window kept the focus", walk.onUi { compose.activity.hasWindowFocus() })
        } finally {
            walk.onUi { compose.activity.lifecycle.removeObserver(observer) }
        }
    }

    /**
     * The window away for two seconds and back, as [ParcelWalk.awayAndBack] does, with the links' questions' clock
     * moved on as much; the test's clock then stands still.
     */
    private fun awayAndBack(links: FakeParcelLinks) {
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        links.now = links.now.plusSeconds(2)
        compose.mainClock.autoAdvance = false
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        walk.awaitInFront()
    }

    /**
     * B-G05: on a parcel's page, a tap on its carrier's link asks the system once to view the carrier's page, counts
     * "parcel-carrier-link" once and keeps the window in front; back from the site two seconds later, the question asks
     * whether that carrier says the same. Away and back before the tap, nothing asks: the tap told the visit.
     */
    @Test
    fun onThePageTheCarriersLinkOpensItsSiteIsCountedAndAsksOnTheWayBack() {
        val gift = gift
        walk.show(listOf(gift, lamp))
        walk.open(gift)
        val link = walk.identities.catalog.trackingLinks(gift, AppLanguage.En).single()
        val page = button(link, PARCEL_PAGE)
        assertEquals("The page's one carrier link", 1, found(inPage(PAGE_CARRIER_LINK)))

        // Away and back with no visit told: nothing asks.
        walk.awayAndBack()
        walk.advanceTo(walk.now() + 100)
        assertNull("No visit, no question on the way back", word(gift))
        compose.mainClock.autoAdvance = true
        walk.settle()

        reveal(page)
        staysInFront {
            compose.onNode(page).performClick()
            compose.waitUntil(LONG_MILLIS) { walk.asked().isNotEmpty() }
        }
        assertOpened(link)
        assertEquals(1, counted())
        walk.shot("B-G05-page")

        walk.awayAndBack()
        val back = walk.now()
        walk.advanceTo(back + 100)
        assertEquals(ParcelFeedbackModel.Word.Back(link.name), word(gift))
        walk.advanceTo(back + 1_000)
        walk.shot("B-G05-back", alsoDark = false)
        assertEquals("Still one page asked", 1, walk.asked().size)
        assertEquals("Still counted once", 1, counted())
        compose.mainClock.autoAdvance = true
    }

    /**
     * B-G05: the sample shows no carrier link, though its carrier has pages for its number: nothing is opened or
     * counted, and the way back asks nothing.
     */
    @Test
    fun theSampleHasNoCarriersLinkAndNothingIsOpenedCountedOrAskedOnTheWayBack() {
        walk.show(DemoCards.parcels())
        walk.onUi { walk.deliveries.openSample() }
        compose.waitUntil(LONG_MILLIS) {
            walk.shown() == FirstOpenJourney.parcelId && walk.count(PARCEL_PAGE) == 1 && found(inPage(PAGE_NAME)) > 0
        }
        walk.settle()
        val sample = checkNotNull(walk.onUi { walk.deliveries.sample(FirstOpenJourney.parcelId) }) { "No sample shown" }
        assertTrue(
            "The sample's carrier has pages for its number, which the sample leaves out",
            walk.identities.catalog.trackingLinks(sample, AppLanguage.En).isNotEmpty(),
        )
        assertEquals("No carrier link on the sample", 0, found(inPage(PAGE_CARRIER_LINK)))
        walk.shot("B-G05-sample")

        walk.awayAndBack()
        walk.advanceTo(walk.now() + 100)
        assertTrue("Nothing asked of the system: ${walk.asked()}", walk.asked().isEmpty())
        assertEquals(0, counted())
        assertNull(walk.onUi { walk.source.feedbacks.of(FirstOpenJourney.parcelId)?.word })
        compose.mainClock.autoAdvance = true
    }

    /**
     * B-G05: in a link's sheet whose number is whole, a tap on the carrier's link asks the system once to view the
     * carrier's page for the number, counts "parcel-carrier-link" once and keeps the window in front; back two seconds
     * later, the sheet's question asks whether that carrier says the same.
     */
    @Test
    fun inALinksSheetTheCarriersLinkOpensItsSiteIsCountedAndAsksOnTheWayBack() {
        val answer = LinkFixtures.keepable()
        val link = walk.identities.catalog.trackingLinks(Parcel(shared = answer.parcel), AppLanguage.En).first()
        visitFromTheSheet(answer, link, "B-G05-link-sheet")
    }

    /**
     * B-G05: in a link's sheet whose number is hidden, the carrier's search page, which takes a number typed in, opens
     * the same way: asked once, counted once, and asked about on the way back.
     */
    @Test
    fun inALinksSheetWithItsNumberHiddenTheCarriersSearchPageOpensTheSameWay() {
        val answer = LinkFixtures.viewer()
        val link = checkNotNull(walk.identities.catalog.searchLink(Parcel(shared = answer.parcel), AppLanguage.En)) { "No search page" }
        visitFromTheSheet(answer, link, "B-G05-link-hidden")
    }

    /**
     * `answer`'s sheet, opened over the deliveries; away and back first with no visit told (nothing asks), then the
     * carrier's `link` tapped, the system asked once for its page, the tap counted once, the window kept in front;
     * shot as `moment`, then back two seconds later with the sheet's question asking, shot as `moment`-back.
     */
    private fun visitFromTheSheet(answer: PublicParcelResponse, link: ParcelTrackingLink, moment: String) {
        val links = FakeParcelLinks(answer = answer, session = FakeParcelLinks.READER)
        compose.waitsForMaps()
        compose.runOnUiThread { compose.activity.edgeToEdge(dark = false) }
        compose.setContent { RootHost(state = FakeParcelLinks.READER, identities = walk.identities, links = links) }
        compose.waitForIdle()
        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = answer.link.id)) }
        val sheet = button(link, LINK_SHEET)
        compose.waitUntil(LONG_MILLIS) { compose.onAllNodes(sheet).fetchSemanticsNodes().isNotEmpty() }
        walk.settle()
        val word = { walk.onUi { links.sheet.feedback(links).word } }
        val counted = { walk.onUi { links.actions.count { it == CARRIER_LINK_ACTION } } }

        awayAndBack(links)
        walk.advanceTo(walk.now() + 100)
        assertNull("No visit, no question on the way back", word())
        compose.mainClock.autoAdvance = true
        walk.settle()

        reveal(sheet)
        staysInFront {
            compose.onAllNodes(sheet).onFirst().performClick()
            compose.waitUntil(LONG_MILLIS) { walk.asked().isNotEmpty() }
        }
        assertOpened(link)
        assertEquals(1, counted())
        walk.shot(moment, alsoDark = false)

        awayAndBack(links)
        val back = walk.now()
        walk.advanceTo(back + 100)
        assertEquals(ParcelFeedbackModel.Word.Back(link.name), word())
        walk.advanceTo(back + 1_000)
        walk.shot("$moment-back", alsoDark = false)
        assertEquals("Still one page asked", 1, walk.asked().size)
        assertEquals("Still counted once", 1, counted())
        compose.mainClock.autoAdvance = true
    }

    private companion object {
        const val FOLDER = "carrierlink"
        const val CARRIER_LINK_ACTION = "parcel-carrier-link"
    }
}
