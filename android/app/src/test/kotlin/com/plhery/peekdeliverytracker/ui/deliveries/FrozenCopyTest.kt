package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.isArchived
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A guest's parcel the service forgot, whose last answer the phone keeps (`store.isFrozen`), as the website shows one:
 * its page says since when it shows that answer, offers nothing that needs the service, and removes it from the phone
 * at once; on the list, still on its way, it reads "No longer updated" in place of its date and never leads, and
 * delivered it needs no mark.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class FrozenCopyTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val gift = DemoCards.parcel("Birthday gift")

    /** The demo's parcels followed on this phone without an account, its stores as `prepare` leaves them. */
    private fun show(prepare: FakeDeliveries.() -> Unit = {}) {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Welcome).apply {
            isGuest = true
            prepare()
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = AuthenticationState.Welcome, source = source)
            }
        }
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** Birthday gift, forgotten by the service, its page open and landed. */
    private fun openTheForgottenGift() {
        show { frozen = setOf(gift.id) }
        compose.openCard(gift.label)
        settle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun shownItems(): Set<ParcelMenuItem> = ParcelMenuItem.entries.filterTo(HashSet()) { exists(menuItemTag(it)) }

    @Test
    fun aForgottenCopysPageSaysSoAndOffersNothingThatNeedsTheService() {
        openTheForgottenGift()
        // The banner gives the moment of the answer it shows, as a notice gives one.
        val read = checkNotNull(DateParser.date(gift.lastSyncedAt ?: gift.createdAt))
        val banner = localizer.text("link.frozen.banner", mapOf("time" to localizer.moment(read, CAPTURED)))
        compose.onNode(hasTestTag(PAGE_FROZEN) and hasText(banner)).assertExists()
        compose.onNode(hasTestTag(PAGE_REMOVE) and hasText(localizer.text("link.remove.now"))).assertExists()
        // No share, bell, freshness line or check: nothing is left on the service to share, follow or ask about.
        assertFalse(exists(PAGE_SHARE))
        assertFalse(exists(PAGE_BELL))
        assertFalse(exists(PAGE_SYNC_LINE))
        assertFalse(exists(PAGE_REFRESH))

        // Its menu keeps what the phone holds of it: no carrier to change.
        compose.onNodeWithTag(PAGE_MENU_BUTTON).performClick()
        settle()
        assertEquals(
            setOf(ParcelMenuItem.Rename, ParcelMenuItem.CopyTrackingNumber, ParcelMenuItem.Archive, ParcelMenuItem.Delete),
            shownItems(),
        )
    }

    @Test
    fun removeFromThisDeviceRemovesAForgottenCopyAtOnce() {
        openTheForgottenGift()
        compose.onNodeWithTag(PAGE_REMOVE).performScrollTo()
        compose.assertAnnounced(localizer.text("link.remove.done")) { compose.onNodeWithTag(PAGE_REMOVE).performClick() }
        settle()
        // Gone without the delete question, and the page with it.
        assertNull(deliveries.deleting)
        assertFalse(exists(DELETE_POPOVER))
        assertEquals(listOf(gift.id), source.deleted)
        assertTrue("parcel-link-remove" in source.actions)
        compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true).assertDoesNotExist()
        assertTrue(deliveries.navigator.navigation.path.isEmpty())
    }

    @Test
    fun aForgottenCopyOnItsWayIsNoLongerUpdatedAndNeverLeads() {
        show()
        val lead = checkNotNull(deliveries.layout.next)
        compose.runOnUiThread { source.frozen = setOf(lead.id) }
        compose.waitForIdle()
        assertNotEquals(lead.id, deliveries.layout.next?.id)
        assertTrue(lead.id in deliveries.layout.remaining.map { it.id })
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed(lead.label))
        compose.card(lead.label).assert(hasContentDescription(localizer.text("link.frozen.label"), substring = true))
    }

    @Test
    fun aDeliveredForgottenCopyNeedsNoMark() {
        val delivered = DemoCards.parcels().first { it.isDelivered && !it.isArchived }
        show { frozen = setOf(delivered.id) }
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed(delivered.label))
        compose.card(delivered.label).assert(!hasContentDescription(localizer.text("link.frozen.label"), substring = true))
    }
}
