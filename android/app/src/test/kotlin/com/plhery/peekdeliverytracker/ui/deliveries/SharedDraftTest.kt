package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ShareInbox
import com.plhery.peekdeliverytracker.core.SharedParcelDraft
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import java.time.Instant
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * What another app shared, taken by the window (A-L08, A-SE09, B-D42): as the list appears and each time the window
 * comes back to the front, a fresh draft opens Add with its text and its name, counted ("parcel-share-received"), and
 * fills Add in place where it is up already (`fill(_:)`); an expired one or one already taken opens nothing, and the
 * window made again takes none twice. On synthetic numbers, the drafts in memory.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class SharedDraftTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val defaults = MemoryDefaults()
    private val inbox = DeliveriesInbox(sharedDrafts = { ShareInbox.consume(defaults) })
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))

    private fun show(recreation: Recreation? = null) {
        source = FakeDeliveries(emptyList(), reader)
        val content: @Composable () -> Unit = {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = reader, source = source, inbox = inbox)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** A draft saved by the share card, `age` seconds ago. */
    private fun share(text: String, label: String = "", age: Long = 0) {
        assertTrue(ShareInbox.save(SharedParcelDraft(label = label, trackingInput = text, createdAt = Instant.now().minusSeconds(age)), defaults))
    }

    /** The window leaves the front and comes back to it. */
    private fun comeBack() {
        compose.activityRule.scenario.moveToState(Lifecycle.State.STARTED)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        settle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun fieldText(tag: String): String? =
        compose.onNodeWithTag(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun carrier(): String? =
        compose.onNodeWithTag(ADD_CARRIER).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString()

    private fun received(): Int = source.actions.count { it == "parcel-share-received" }

    @Test
    fun aDraftWaitingAsTheListAppearsOpensAddWithItsTextAndNameSettledAndCounted() {
        share("Your parcel 1Z999AA10123456784 is on its way", label = "Desk lamp")
        show()
        assertTrue(exists(ADD_PARCEL))
        assertEquals("Your parcel 1Z999AA10123456784 is on its way", fieldText(ADD_TRACKING))
        assertEquals("Desk lamp", fieldText(ADD_NAME))
        assertEquals("UPS, ${localizer.text("add.detectedCarrier")}, ${localizer.text("add.line.change")}", carrier())
        assertEquals(1, received())
        assertEquals("add-parcel", source.views.last())
        assertFalse("Taken away", defaults.hasObject(forKey = ShareInbox.key))
    }

    @Test
    fun aLongDraftFillsAddWholeAndTheNumberNearItsEndIsFoundAndKeptOnce() {
        val end = " Your parcel 1Z999AA10123456784 is on its way."
        val text = "The parcel left the warehouse today. ".repeat(250).take(9_000 - end.length) + end
        assertEquals(9_000, text.length)
        share(text, label = "Desk lamp")
        show()
        assertEquals(text, fieldText(ADD_TRACKING))
        assertEquals("UPS, ${localizer.text("add.detectedCarrier")}, ${localizer.text("add.line.change")}", carrier())
        assertNull("The window keeps the text in Add's form alone", deliveries.addition?.draft)

        // Typed on, the field keeps it all.
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("$text!")
        settle()
        assertEquals("$text!", fieldText(ADD_TRACKING))
    }

    @Test
    fun aDraftSavedWhileTheAppWasAwayOpensAddAsTheWindowComesBack() {
        show()
        assertNull(deliveries.addition)
        share("1Z999AA10123456784")
        comeBack()
        assertEquals("1Z999AA10123456784", fieldText(ADD_TRACKING))
        assertEquals("", fieldText(ADD_NAME))
        assertEquals(1, received())
    }

    @Test
    fun aDraftSavedWhileAnotherTabIsUpWaitsForTheDeliveries() {
        show()
        compose.onNodeWithContentDescription("Passport").performClick()
        settle()
        share("1Z999AA10123456784")
        // The list's `.onAppear`, not the window coming back: the other tab keeps it until the deliveries show.
        comeBack()
        assertFalse(exists(ADD_PARCEL))
        assertTrue(defaults.hasObject(forKey = ShareInbox.key))
        assertEquals(0, received())

        compose.onNodeWithContentDescription("Deliveries").performClick()
        settle()
        assertEquals("1Z999AA10123456784", fieldText(ADD_TRACKING))
        assertEquals(1, received())
    }

    @Test
    fun aDraftSharedWhileAddIsUpTakesTheFieldsPlaceAndItsNameWhereItHasOne() {
        show()
        compose.onNodeWithTag(ADD_BUTTON).performClick()
        settle()
        val opening = deliveries.addition?.id
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("TESTLAMP0001")
        compose.onNodeWithTag(ADD_NAME).performTextReplacement("Lamp")
        settle()

        share("UPS 1Z999AA10123456784", label = "Running shoes")
        comeBack()
        assertEquals("The same Add", opening, deliveries.addition?.id)
        assertEquals("UPS 1Z999AA10123456784", fieldText(ADD_TRACKING))
        assertEquals("Running shoes", fieldText(ADD_NAME))
        assertEquals("UPS, ${localizer.text("add.detectedCarrier")}, ${localizer.text("add.line.change")}", carrier())

        share("1Z999AA10123456793")
        comeBack()
        assertEquals("1Z999AA10123456793", fieldText(ADD_TRACKING))
        assertEquals("A draft without a name leaves the name", "Running shoes", fieldText(ADD_NAME))
        assertEquals(2, received())
    }

    @Test
    fun anExpiredDraftOrOneAlreadyTakenOpensNothing() {
        share("1Z999AA10123456784", age = 601)
        show()
        assertNull(deliveries.addition)
        assertEquals(0, received())
        assertFalse("Taken away all the same", defaults.hasObject(forKey = ShareInbox.key))

        share("1Z999AA10123456784")
        comeBack()
        assertNotNull(deliveries.addition)
        compose.onNodeWithTag(ADD_CLOSE).performClick()
        settle()
        assertNull(deliveries.addition)
        comeBack()
        assertNull(deliveries.addition)
        assertEquals(1, received())
    }

    @Test
    fun theWindowMadeAgainTakesNoDraftTwiceNorFillsAddAgain() {
        val recreation = Recreation(compose)
        share("1Z999AA10123456784", label = "Desk lamp")
        show(recreation)
        val opening = deliveries.addition?.id
        recreation.recreate()
        settle()
        assertEquals(opening, deliveries.addition?.id)
        assertEquals("1Z999AA10123456784", fieldText(ADD_TRACKING))
        assertEquals(1, received())

        share("UPS 1Z999AA10123456793", label = "Running shoes")
        comeBack()
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("TESTLAMP0001")
        settle()
        recreation.recreate()
        settle()
        assertEquals("What was typed after the fill stays", "TESTLAMP0001", fieldText(ADD_TRACKING))
        assertEquals("Running shoes", fieldText(ADD_NAME))
        assertEquals(2, received())
    }
}
