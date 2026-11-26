package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ShareInbox
import com.plhery.peekdeliverytracker.core.SharedParcelDraft
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ShopLinkFailure
import com.plhery.peekdeliverytracker.core.api.ShopLinkParcel
import com.plhery.peekdeliverytracker.core.api.ShopLinkParcelConfidence
import com.plhery.peekdeliverytracker.core.api.ShopLinkResponse
import com.plhery.peekdeliverytracker.core.api.ShopPlatform
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.UnresolvedShopLink
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import java.io.IOException
import java.time.Duration
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
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A shop's tracking-page link in Add (the web's `useShopLinkReading`, `ShopLinkNotice` and their use in
 * `AddParcelSheet`): typed, pasted or shared in with no number of its own, Add asks what it names, signed in or as a
 * guest, while it says so in place of "no number found"; a parcel fills the number in with its carrier, a shop that
 * hasn't shipped is offered a wait signed in ("Wait for H&M") and a sign-in as a guest, and a page Peek can't read or
 * that doesn't answer says so. On a made-up link, the answers the test's.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ShopLinkAddTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val defaults = MemoryDefaults()
    private val inbox = DeliveriesInbox(sharedDrafts = { ShareInbox.consume(defaults) })
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))

    private val link = "https://hm.delivery-status.com/de/de/?s=synthetic-key"
    private val email = "Hello Synthetic Reader, your order is on its way.\nFollow package: $link\nExample Shop"

    private val lamp = Parcel(
        id = UUID.fromString("00000000-0000-4000-8000-000000000701"), trackingNumber = "TESTLAMP0001", label = "Desk lamp",
        carrier = CarrierId.Unknown, createdAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))),
        syncStatus = SyncStatus.Ok, notificationsMuted = false,
    )

    private fun unresolved(waitable: Boolean = true, shop: String? = "H&M") = ShopLinkResponse(
        links = 1, parcels = emptyList(),
        unresolved = listOf(
            UnresolvedShopLink(
                platform = ShopPlatform.Parcellab, platformName = "parcelLab", shopName = shop,
                reason = if (waitable) ShopLinkFailure.NoTrackingYet else ShopLinkFailure.Unsupported, waitable = waitable,
            ),
        ),
    )

    private fun found(number: String, carrier: String) = ShopLinkResponse(
        links = 1, parcels = listOf(ShopLinkParcel(trackingNumber = number, carrier = carrier, confidence = ShopLinkParcelConfidence.High)), unresolved = emptyList(),
    )

    private fun show(session: AuthenticationState = reader, prepare: FakeDeliveries.() -> Unit = {}) {
        source = FakeDeliveries(listOf(lamp), session).apply {
            isGuest = session == AuthenticationState.Welcome
            prepare()
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = session, source = source, reduceMotion = true, inbox = inbox)
            }
        }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun text(key: String, variables: Map<String, Any> = emptyMap()) = localizer.text(key, variables)

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun fieldText(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun description(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString()

    /** What the shop link's notice says, title and words, as its texts read. */
    private fun notice(): List<String> =
        compose.onNodeWithTag(ADD_SHOP_LINK).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text }

    /** Whether Add's button reads [words]. */
    private fun buttonReads(words: String): Boolean =
        compose.onAllNodes(hasText(words) and hasAnyAncestor(hasTestTag(ADD_SAVE)), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun open() {
        node(ADD_BUTTON).performClick()
        settle()
    }

    private fun type(text: String) {
        node(ADD_TRACKING).performTextReplacement(text)
        compose.waitForIdle()
    }

    private fun share(text: String, label: String = "") {
        assertTrue(ShareInbox.save(SharedParcelDraft(label = label, trackingInput = text, createdAt = AppClock.now()), defaults))
    }

    @Test
    fun aTypedLinkIsReadOnceTypingPausesWhileAddSaysSoInPlaceOfNoNumber() {
        show { shopLinkHolding = CompletableDeferred(); shopLinkAnswer = unresolved() }
        open()
        compose.mainClock.autoAdvance = false
        type(link)
        compose.mainClock.advanceTimeBy(500)
        // Typing on: nothing is asked yet, and Add already says it reads the page rather than that no number is found.
        assertEquals(emptyList<Pair<String, Boolean>>(), source.shopLinkReads)
        assertEquals(listOf(text("shopLink.checking")), notice())
        assertFalse(exists(ADD_NOT_FOUND))
        compose.mainClock.advanceTimeBy(500)
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        // The links alone go, from the account.
        assertEquals(listOf(link to true), source.shopLinkReads)
        assertEquals(listOf(text("shopLink.checking")), notice())
        assertFalse(exists(ADD_NOT_FOUND))
        node(ADD_SAVE).assertIsNotEnabled()

        source.shopLinkHolding!!.complete(Unit)
        settle()
        assertEquals(listOf(text("shopLink.noParcelYet", mapOf("shop" to "H&M")), text("shopLink.waitHint")), notice())
        assertFalse(exists(ADD_NOT_FOUND))

        // More text around the same link asks nothing again; a text with no link brings "no number found" back.
        type("$link and some words")
        settle()
        assertEquals(1, source.shopLinkReads.size)
        assertEquals(listOf(text("shopLink.noParcelYet", mapOf("shop" to "H&M")), text("shopLink.waitHint")), notice())
        type("no number here")
        settle()
        assertFalse(exists(ADD_SHOP_LINK))
        assertTrue(exists(ADD_NOT_FOUND))
        assertEquals(1, source.shopLinkReads.size)
    }

    @Test
    fun onlyTheLinksOfAnEmailGoAndALookalikeIsNoLink() {
        show { shopLinkAnswer = unresolved() }
        open()
        type(email)
        settle()
        assertEquals(listOf(link to true), source.shopLinkReads)

        type("Follow package: https://hm.delivery-status.com.example.test/de/de/?s=synthetic-key")
        settle()
        assertEquals(1, source.shopLinkReads.size)
        assertFalse(exists(ADD_SHOP_LINK))
        assertTrue(exists(ADD_NOT_FOUND))
    }

    @Test
    fun aParcelTheLinkNamesFillsInItsNumberAndItsCarrier() {
        show { shopLinkAnswer = found("1Z999AA10123456784", "ups") }
        open()
        type(link)
        settle()
        assertEquals("1Z999AA10123456784", fieldText(ADD_TRACKING))
        assertEquals("UPS, ${text("add.line.chosen")}, ${text("add.line.change")}", description(ADD_CARRIER))
        assertFalse(exists(ADD_SHOP_LINK))
        assertFalse(exists(ADD_NOT_FOUND))
        node(ADD_SAVE).assertIsEnabled().performClick()
        settle()
        assertEquals(CarrierId("ups"), source.additions.single().carrier)
        assertEquals("1Z999AA10123456784", source.additions.single().trackingNumber)
        assertEquals(1, source.shopLinkReads.size)
    }

    @Test
    fun aParcelWhoseCarrierIsUnknownLeavesTheCarrierToTheCheck() {
        show { shopLinkAnswer = found("1Z999AA10123456784", "unknown") }
        open()
        type(link)
        settle()
        assertEquals("1Z999AA10123456784", fieldText(ADD_TRACKING))
        assertEquals("UPS, ${text("add.detectedCarrier")}, ${text("add.line.change")}", description(ADD_CARRIER))
    }

    @Test
    fun signedInAShopThatHasntShippedIsOfferedAWaitThatTheListShows() {
        show {
            shopLinkAnswer = unresolved()
            shopWaitsClient.keeping = ShopWaitFixtures.coat
        }
        open()
        type(link)
        node(ADD_NAME).performTextReplacement("  Wool coat  ")
        settle()
        val wait = text("shopLink.wait", mapOf("shop" to "H&M"))
        assertEquals("Wait for H&M", wait)
        assertTrue(buttonReads(wait))
        node(ADD_SAVE).assertIsEnabled().performClick()
        settle()

        assertEquals(listOf(link to "Wool coat"), source.shopWaitsClient.kept)
        assertEquals(listOf("keep", "list"), source.shopWaitsClient.requests.takeLast(2))
        assertNull(deliveries.addition)
        assertFalse(exists(ADD_PARCEL))
        assertTrue(exists(SHOP_WAIT_CARD))
        assertEquals(emptyList<FakeDeliveries.AdditionRequest>(), source.additions)
    }

    @Test
    fun aShopWithoutItsNameIsWaitedForAsTheShop() {
        show { shopLinkAnswer = unresolved(shop = null) }
        open()
        type(link)
        settle()
        assertEquals(listOf(text("shopLink.noParcelYet.unnamed"), text("shopLink.waitHint")), notice())
        assertTrue(buttonReads(text("shopLink.wait.unnamed")))
    }

    @Test
    fun aWaitThatFailsSaysWhyAndAddStaysWithTheOffer() {
        show {
            shopLinkAnswer = unresolved()
            shopWaitsClient.keepFailure = IllegalStateException("No connection")
        }
        open()
        type(link)
        settle()
        node(ADD_SAVE).performClick()
        settle()
        assertTrue(exists(ADD_ERROR))
        compose.onNode(hasText("No connection")).assertExists()
        assertTrue(exists(ADD_PARCEL))
        assertTrue(buttonReads(text("shopLink.wait", mapOf("shop" to "H&M"))))
        node(ADD_SAVE).assertIsEnabled()
        assertFalse(exists(SHOP_WAIT_CARD))
    }

    @Test
    fun aGuestIsAskedThroughThePublicRequestAndOfferedToSignIn() {
        show(session = AuthenticationState.Welcome) { shopLinkAnswer = unresolved() }
        open()
        type(link)
        settle()
        assertEquals(listOf(link to false), source.shopLinkReads)
        assertEquals(listOf(text("shopLink.noParcelYet", mapOf("shop" to "H&M")), text("shopLink.signIn")), notice())
        assertTrue(buttonReads(text("app.addParcel")))
        assertFalse(buttonReads(text("shopLink.wait", mapOf("shop" to "H&M"))))
        node(ADD_SAVE).assertIsNotEnabled()
    }

    @Test
    fun aPageThatCantBeReadOrDoesntAnswerSaysSo() {
        show { shopLinkAnswer = unresolved(waitable = false) }
        open()
        type(link)
        settle()
        assertEquals(listOf(text("shopLink.unreadable")), notice())
        assertFalse(exists(ADD_NOT_FOUND))
        node(ADD_SAVE).assertIsNotEnabled()

        source.shopLinkFailure = IOException("offline")
        type("$link#again")
        settle()
        assertEquals(2, source.shopLinkReads.size)
        assertEquals(listOf(text("shopLink.unavailable")), notice())
        assertFalse(exists(ADD_NOT_FOUND))
        node(ADD_SAVE).assertIsNotEnabled()
    }

    @Test
    fun aLinkSharedFromAnotherAppIsReadAsATypedOneIs() {
        share(email, label = "Wool coat")
        show { shopLinkAnswer = unresolved(); shopWaitsClient.keeping = ShopWaitFixtures.coat }
        assertTrue(exists(ADD_PARCEL))
        assertEquals(email, fieldText(ADD_TRACKING))
        assertEquals(listOf(link to true), source.shopLinkReads)
        assertEquals(listOf(text("shopLink.noParcelYet", mapOf("shop" to "H&M")), text("shopLink.waitHint")), notice())
        node(ADD_SAVE).performClick()
        settle()
        assertEquals(listOf(link to "Wool coat"), source.shopWaitsClient.kept)
        assertTrue(exists(SHOP_WAIT_CARD))
    }

    @Test
    fun aLinkSharedWhileAddIsUpFillsItAndIsReadAtOnce() {
        show { shopLinkAnswer = unresolved() }
        open()
        type("typed")
        share(link)
        compose.activityRule.scenario.moveToState(Lifecycle.State.STARTED)
        compose.mainClock.autoAdvance = false
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        // A share, as a paste, is read without the typing pause.
        compose.mainClock.advanceTimeBy(300)
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertEquals(link, fieldText(ADD_TRACKING))
        assertEquals(listOf(link to true), source.shopLinkReads)
    }
}
