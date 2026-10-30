package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import java.time.Duration
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Each parcel page waits for its own change (ParcelDetailView's `working`, B-ST14): another page opened meanwhile, over
 * the list or beside it, starts idle and hears nothing of the first one's failure, which goes with its page but keeps
 * the rating away for the day; one page's archive or delete, saved one at a time, holds no other page's; and through a
 * rotation the page still waits, ignores a second tap, and hears how its change went, failed or saved.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PageWorkTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    private val gift: UUID = DemoCards.parcel("Birthday gift").id
    private val chocolate: UUID = DemoCards.parcel("Belgian chocolate").id

    /** The demo's deliveries as `prepare` sets the stores up, made again by `recreation`. */
    private fun show(recreation: Recreation? = null, prepare: FakeDeliveries.() -> Unit = {}) {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo).apply(prepare)
        compose.waitsForMaps()
        val content: @Composable () -> Unit = {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = AuthenticationState.Demo, source = source)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    /** Opens the page of the parcel with `id`, as a notification does, over the list or beside it. */
    private fun open(id: UUID) {
        compose.runOnUiThread { deliveries.navigator.open(id) }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun parcel(id: UUID): Parcel = source.parcels.value.first { it.id == id }

    private fun refresh() = compose.onNodeWithTag(PAGE_REFRESH).performScrollTo()

    private fun item(item: ParcelMenuItem) = compose.onNodeWithTag(menuItemTag(item))

    private fun openMenu() {
        compose.onNodeWithTag(PAGE_MENU_BUTTON).performClick()
        settle()
    }

    private fun choose(item: ParcelMenuItem) {
        item(item).performClick()
        settle()
    }

    /** The window made again, with the test's clock running by itself again. */
    private fun Recreation.rotate() {
        recreate()
        compose.mainClock.autoAdvance = true
        settle()
    }

    private fun assertFailureShown(shown: Boolean) {
        assertEquals(shown, exists(ACTION_ERROR))
        assertEquals(shown, compose.onAllNodes(hasText("No connection")).fetchSemanticsNodes().isNotEmpty())
    }

    // Two pages.

    @Test
    fun anotherPageOpenedWhileOneChecksStartsIdleAndHearsNothingOfItsFailure() {
        show { checkingParcel = CompletableDeferred() }
        // In use on the two days before: today could ask for a rating.
        source.reviewPrompt.used(CAPTURED.minus(Duration.ofDays(2)))
        source.reviewPrompt.used(CAPTURED.minus(Duration.ofDays(1)))
        open(gift)
        refresh().performClick()
        settle()
        assertTrue(deliveries.pageWorking(gift))
        assertTrue(source.reviewPrompt.isDue(CAPTURED, calm = true))

        // As a notification opens another parcel.
        open(chocolate)
        assertFalse(deliveries.pageWorking(chocolate))
        refresh().assertIsEnabled()
        compose.onNodeWithTag(PAGE_BELL).assertIsEnabled()
        openMenu()
        item(ParcelMenuItem.Delete).assertIsEnabled()
        compose.runOnUiThread { deliveries.pageMenu = null }
        settle()

        // The first page's check fails once the page has gone, as the iPhone's goes with its page.
        source.failure = IllegalStateException("No connection")
        compose.runOnUiThread { source.checkingParcel?.complete(Unit) }
        settle()
        assertFailureShown(false)
        refresh().assertIsEnabled()
        // Though nothing says so, the failure keeps the rating away for the day, as the iPhone's `run` notes it.
        assertFalse(source.reviewPrompt.isDue(CAPTURED, calm = true))
        // Opened again, the first page starts afresh too.
        open(gift)
        assertFalse(deliveries.pageWorking(gift))
        assertFailureShown(false)
        refresh().assertIsEnabled()
    }

    @Test
    fun aFailureIsShownOverThePageThatAskedWhileItShows() {
        show {
            checkingParcel = CompletableDeferred()
            failure = IllegalStateException("No connection")
        }
        open(gift)
        refresh().performClick()
        settle()
        compose.runOnUiThread { source.checkingParcel?.complete(Unit) }
        settle()
        assertFailureShown(true)
        compose.onNodeWithTag(ACTION_ERROR_CLOSE).performClick()
        settle()
        assertFailureShown(false)
        assertFalse(deliveries.pageWorking(gift))
        refresh().assertIsEnabled()
    }

    /** ↓ beside the list shows the next card's page, which starts idle; the first one's failure goes with its page. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun besideTheListTheNextCardsPageStartsIdle() {
        show { checkingParcel = CompletableDeferred() }
        open(gift)
        assertEquals(DeliveriesArrangement.SideBySide, deliveries.arrangement)
        refresh().performClick()
        settle()
        assertTrue(deliveries.pageWorking(gift))
        compose.runOnUiThread { deliveries.navigator.step(1, deliveries.rows) }
        settle()
        val next = deliveries.navigator.navigation.shown!!
        assertTrue(next != gift)
        assertFalse(deliveries.pageWorking(next))
        refresh().assertIsEnabled()

        source.failure = IllegalStateException("No connection")
        compose.runOnUiThread { source.checkingParcel?.complete(Unit) }
        settle()
        assertFailureShown(false)
        compose.runOnUiThread { deliveries.navigator.step(-1, deliveries.rows) }
        settle()
        assertEquals(gift, deliveries.navigator.navigation.shown)
        assertFailureShown(false)
        refresh().assertIsEnabled()
    }

    // Archive and delete.

    @Test
    fun archiveAndDeleteFromThePageSaveOneChangeAtATime() {
        show { archiving = CompletableDeferred() }
        open(gift)
        openMenu()
        choose(ParcelMenuItem.Archive)
        assertTrue(deliveries.pageWorking(gift))
        refresh().assertIsNotEnabled()
        // A second archive, a restore or a delete, however they come, are not asked for: a second archive would wait
        // on the same answer, so once it comes only the first was made.
        compose.runOnUiThread {
            deliveries.choseOnPage(gift, ParcelMenuItem.Archive, parcel(gift))
            deliveries.choseOnPage(gift, ParcelMenuItem.Restore, parcel(gift))
            deliveries.confirmDelete(DeleteRequest(gift, gift))
        }
        compose.runOnUiThread { source.archiving?.complete(Unit) }
        settle()
        assertEquals(listOf(gift), source.archived)
        assertEquals(emptyList<Any>(), source.restored)
        assertEquals(emptyList<Any>(), source.deleted)
        assertTrue(deliveries.navigator.navigation.path.isEmpty())
        assertFalse(deliveries.pageWorking(gift))
    }

    @Test
    fun whileOnePageArchivesAnotherDeletesAtOnce() {
        show { archiving = CompletableDeferred() }
        open(gift)
        openMenu()
        choose(ParcelMenuItem.Archive)
        open(chocolate)
        openMenu()
        item(ParcelMenuItem.Delete).assertIsEnabled()
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(DELETE_CONFIRM).performClick()
        settle()
        assertEquals(listOf(chocolate), source.deleted)
        assertTrue(deliveries.navigator.navigation.path.isEmpty())

        // The first page's archive is saved after its page has gone, and leaves nothing.
        compose.runOnUiThread { source.archiving?.complete(Unit) }
        settle()
        assertEquals(listOf(gift), source.archived)
        assertTrue(deliveries.navigator.navigation.path.isEmpty())
        assertTrue(source.pageWork.leaving.isEmpty())
    }

    // Through a rotation.

    @Test
    fun throughARotationThePageStillChecksIgnoresASecondTapAndIsDoneOnceChecked() {
        val recreation = Recreation(compose)
        show(recreation) { checkingParcel = CompletableDeferred() }
        open(gift)
        refresh().performClick()
        settle()
        recreation.rotate()
        assertEquals(listOf(gift), deliveries.navigator.navigation.path)
        assertTrue(deliveries.pageWorking(gift))
        refresh().assertIsNotEnabled()
        compose.onNodeWithTag(PAGE_BELL).assertIsNotEnabled()
        // A second check, however it comes, is not asked for.
        compose.runOnUiThread { deliveries.refreshOnPage(gift, parcel(gift)) }
        compose.runOnUiThread { source.checkingParcel?.complete(Unit) }
        settle()
        assertEquals(listOf(gift), source.checked)
        assertFalse(deliveries.pageWorking(gift))
        refresh().assertIsEnabled()
    }

    @Test
    fun aCheckThatFailsAfterARotationSaysWhyOnTheRestoredPage() {
        val recreation = Recreation(compose)
        show(recreation) {
            checkingParcel = CompletableDeferred()
            failure = IllegalStateException("No connection")
        }
        open(gift)
        refresh().performClick()
        settle()
        recreation.rotate()
        compose.runOnUiThread { source.checkingParcel?.complete(Unit) }
        settle()
        assertFailureShown(true)
        compose.onNodeWithTag(ACTION_ERROR_CLOSE).performClick()
        settle()
        assertFailureShown(false)
        refresh().assertIsEnabled()
    }

    @Test
    fun anArchiveSavedAfterARotationLeavesTheRestoredPage() {
        val recreation = Recreation(compose)
        show(recreation) { archiving = CompletableDeferred() }
        open(gift)
        openMenu()
        choose(ParcelMenuItem.Archive)
        recreation.rotate()
        assertTrue(deliveries.pageWorking(gift))
        compose.runOnUiThread { source.archiving?.complete(Unit) }
        settle()
        assertEquals(listOf(gift), source.archived)
        assertTrue(deliveries.navigator.navigation.path.isEmpty())
        compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true).assertDoesNotExist()
    }
}
