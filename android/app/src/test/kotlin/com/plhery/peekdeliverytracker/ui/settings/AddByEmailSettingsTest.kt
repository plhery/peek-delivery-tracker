package com.plhery.peekdeliverytracker.ui.settings

import android.content.ClipboardManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.click
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierCatalogRefreshResult
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.AddByEmailEntry
import com.plhery.peekdeliverytracker.core.api.AddByEmailResult
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.ANNOUNCEMENT
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.guideCatalogData
import com.plhery.peekdeliverytracker.ui.deliveries.guideIdentities
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.settings.AddByEmailFixtures.ADDRESS
import com.plhery.peekdeliverytracker.ui.settings.AddByEmailFixtures.RENEWED
import com.plhery.peekdeliverytracker.ui.settings.AddByEmailFixtures.mailbox
import com.plhery.peekdeliverytracker.ui.settings.AddByEmailFixtures.offer
import java.io.IOException
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Settings' "Add parcels by email": its row only for an account the server offers an address (the server's flag), the
 * intro's link to the whole guide, the address turned on, renewed after asking and turned off, one change at a time with the back off meanwhile and why it
 * failed, the address copied, the notification of each parcel an email adds switched, and the forwarding steps:
 * Gmail's while its confirmation waits, the filter's languages.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class AddByEmailSettingsTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))
    private val opened = mutableListOf<String>()
    private val links = object : UriHandler {
        override fun openUri(uri: String) {
            opened += uri
        }
    }

    /** The app as [session], its carriers [identities]': the bundled catalog's, which says nothing of the guides. */
    private fun show(
        session: AuthenticationState = reader,
        identities: CarrierIdentities = DemoCards.identities,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        source = FakeDeliveries(DemoCards.parcels(), session).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }, LocalUriHandler provides links) {
                RootHost(state = session, dark = false, source = source, identities = identities)
            }
        }
        settle()
        compose.runOnUiThread { deliveries.account() }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun text(key: String) = localizer.text(key)

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun shows(text: String) = compose.onAllNodes(hasText(text)).fetchSemanticsNodes().isNotEmpty()

    private fun words(tag: String) = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text)?.joinToString()

    private fun tap(tag: String) {
        node(tag).performScrollTo().performClick()
        settle()
    }

    /** A finger on the row's switch, at its trailing end. */
    private fun flip(tag: String) {
        node(tag).performScrollTo().performTouchInput { click(Offset(width - 40.dp.toPx(), height / 2f)) }
        settle()
    }

    private fun toggled(tag: String): ToggleableState? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ToggleableState)

    private fun enabled(tag: String): Boolean = SemanticsProperties.Disabled !in node(tag).fetchSemanticsNode().config

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    private fun announced(): String? = node(ANNOUNCEMENT).fetchSemanticsNode()
        .config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull()?.ifEmpty { null }

    @Test
    fun anAccountTheServerOffersAnAddressHasTheRowWithItsBadge() {
        show { addByEmailClient.offer = offer(inbox = null) }
        assertTrue(exists(SETTINGS_ADD_BY_EMAIL))
        assertTrue(shows(text("inbox.experimental").uppercase()))
        assertEquals(listOf("read"), source.addByEmailClient.requests)
        // Under Delivery updates.
        val updates = node(SETTINGS_DELIVERY_UPDATES).fetchSemanticsNode().boundsInRoot
        val row = node(SETTINGS_ADD_BY_EMAIL).fetchSemanticsNode().boundsInRoot
        assertTrue(row.top >= updates.bottom)
    }

    /** The server answers 404 until the feature is set up and open to the account. */
    @Test
    fun withoutAnOfferThereIsNoRow() {
        show()
        assertEquals(listOf("read"), source.addByEmailClient.requests)
        assertFalse(exists(SETTINGS_ADD_BY_EMAIL))
        assertTrue(exists(SETTINGS_DELIVERY_UPDATES))
    }

    @Test
    fun aFailedReadShowsNoRow() {
        show { addByEmailClient.failure = IOException("Offline") }
        assertFalse(exists(SETTINGS_ADD_BY_EMAIL))
    }

    @Test
    fun aGuestAndTheDemoAreNeverOfferedOne() {
        show(AuthenticationState.Welcome) {
            isGuest = true
            addByEmailClient.offer = offer()
        }
        assertFalse(exists(SETTINGS_ADD_BY_EMAIL))
        assertEquals(emptyList<String>(), source.addByEmailClient.requests)
    }

    @Test
    fun theDemoIsNeverOfferedOne() {
        show(AuthenticationState.Demo) { addByEmailClient.offer = offer() }
        assertFalse(exists(SETTINGS_ADD_BY_EMAIL))
        assertEquals(emptyList<String>(), source.addByEmailClient.requests)
    }

    /** Under the intro, the guide on the site in the language the guides are read in, where the server serves it. */
    @Test
    fun theIntroLinksTheWholeGuide() {
        show(identities = guideIdentities) { addByEmailClient.offer = offer(inbox = null) }
        tap(SETTINGS_ADD_BY_EMAIL)
        assertTrue(shows(text("inbox.guide")))
        tap(ADD_BY_EMAIL_GUIDE)
        assertEquals(listOf("https://example.com/guides/add-parcels-by-email"), opened)
    }

    /** A server that serves no guides: the intro alone, until a refreshed catalog says it serves them. */
    @Test
    fun theIntroLinksNoGuideUntilTheServerServesThem() {
        val catalog = CarrierCatalog(loader = { CarrierCatalog.Response(200, body = guideCatalogData) })
        show(identities = CarrierIdentities(catalog)) { addByEmailClient.offer = offer(inbox = null) }
        tap(SETTINGS_ADD_BY_EMAIL)
        assertTrue(shows(text("inbox.intro")))
        assertFalse(exists(ADD_BY_EMAIL_GUIDE))
        assertFalse(shows(text("inbox.guide")))

        assertEquals(CarrierCatalogRefreshResult.Updated, runBlocking { catalog.refresh("https://example.com", force = true) })
        settle()
        assertTrue(exists(ADD_BY_EMAIL_GUIDE))
    }

    @Test
    fun turningOnShowsTheAddressAndTurningOffOffersItAgain() {
        show { addByEmailClient.offer = offer(inbox = null) }
        tap(SETTINGS_ADD_BY_EMAIL)
        assertTrue(shows(text("inbox.intro")))
        assertFalse(exists(ADD_BY_EMAIL_ADDRESS))
        tap(ADD_BY_EMAIL_START)
        assertEquals(ADDRESS, words(ADD_BY_EMAIL_ADDRESS))
        tap(ADD_BY_EMAIL_OFF)
        assertFalse(exists(ADD_BY_EMAIL_ADDRESS))
        assertTrue(exists(ADD_BY_EMAIL_START))
        assertEquals(listOf("read", "open", "close"), source.addByEmailClient.requests)
        // The page stays while the offer does.
        assertTrue(exists(SETTINGS_BACK))
    }

    @Test
    fun aNewAddressAsksFirst() {
        show { addByEmailClient.offer = offer() }
        tap(SETTINGS_ADD_BY_EMAIL)
        tap(ADD_BY_EMAIL_RENEW)
        assertTrue(shows(text("inbox.renewNote")))
        tap(ADD_BY_EMAIL_RENEW_CANCEL)
        assertFalse(shows(text("inbox.renewNote")))
        assertEquals(listOf("read"), source.addByEmailClient.requests)
        tap(ADD_BY_EMAIL_RENEW)
        tap(ADD_BY_EMAIL_RENEW_CONFIRM)
        assertEquals(RENEWED, words(ADD_BY_EMAIL_ADDRESS))
        assertFalse(shows(text("inbox.renewNote")))
        assertEquals(listOf("read", "renew"), source.addByEmailClient.requests)
    }

    @Test
    fun whileAChangeWaitsTheBackIsOffThenWhyItFailedShows() {
        show {
            addByEmailClient.offer = offer(inbox = null)
            addByEmailClient.changing = CompletableDeferred()
        }
        tap(SETTINGS_ADD_BY_EMAIL)
        tap(ADD_BY_EMAIL_START)
        assertTrue(exists(ADD_BY_EMAIL_WORKING))
        back()
        assertTrue(exists(ADD_BY_EMAIL_START))
        assertTrue(deliveries.showingAccount)

        source.addByEmailClient.changeFailure = IOException("Offline")
        compose.runOnUiThread { source.addByEmailClient.changing!!.complete(Unit) }
        settle()
        assertFalse(exists(ADD_BY_EMAIL_WORKING))
        assertEquals(localizer.errorMessage(IOException("Offline"), fallback = "inbox.failed"), words(ADD_BY_EMAIL_ERROR))
        back()
        assertFalse(exists(ADD_BY_EMAIL_START))
        assertTrue(exists(SETTINGS_ADD_BY_EMAIL))
    }

    @Test
    fun copyingPutsTheAddressOnTheClipboardAndSaysSo() {
        show { addByEmailClient.offer = offer() }
        tap(SETTINGS_ADD_BY_EMAIL)
        tap(ADD_BY_EMAIL_COPY)
        val clip = compose.activity.getSystemService(ClipboardManager::class.java).primaryClip?.getItemAt(0)?.text?.toString()
        assertEquals(ADDRESS, clip)
        assertTrue(shows(text("inbox.copied")))
        assertEquals(text("inbox.copied"), announced())
    }

    @Test
    fun theLastEmailsShowWhatEachDid() {
        show { addByEmailClient.offer = offer(mailbox(recent = AddByEmailFixtures.recent)) }
        tap(SETTINGS_ADD_BY_EMAIL)
        assertTrue(shows("Example Books"))
        assertTrue(shows(text("inbox.noNumber")))
    }

    @Test
    fun anEmailWithoutANumberNamesTheAccountThatShowsIt() {
        val amazon = AddByEmailEntry(at = "2026-10-29T09:58:00.000Z", sender = "Amazon.it", result = AddByEmailResult.None, added = 0, account = "Amazon")
        show { addByEmailClient.offer = offer(mailbox(recent = listOf(amazon))) }
        tap(SETTINGS_ADD_BY_EMAIL)
        assertTrue(shows("Amazon.it"))
        assertTrue(shows(localizer.text("inbox.result.account", mapOf("shop" to "Amazon"))))
        assertFalse(shows(text("inbox.noNumber")))
        // The bundled catalog says nothing of the guides: the row alone says where the number is.
        assertFalse(exists(ADD_BY_EMAIL_AMAZON_GUIDE))
    }

    /** Where the server serves its guides, the Amazon guide under the last emails. */
    @Test
    fun anEmailWhoseNumberIsInTheAmazonAccountLinksTheGuide() {
        val amazon = AddByEmailEntry(at = "2026-10-29T09:58:00.000Z", sender = "Amazon.it", result = AddByEmailResult.None, added = 0, account = "Amazon")
        show(identities = guideIdentities) { addByEmailClient.offer = offer(mailbox(recent = listOf(amazon))) }
        tap(SETTINGS_ADD_BY_EMAIL)
        tap(ADD_BY_EMAIL_AMAZON_GUIDE)
        assertEquals(listOf("https://example.com/guides/amazon-orders"), opened)
    }

    /**
     * The switch is one line in the last group, between a new address and turning it off, on for an address the server
     * sends without `notify`, and read by TalkBack as one switch; a finger on it saves the other side, which it then shows.
     */
    @Test
    fun theNotificationIsOnUntilSwitchedOff() {
        show { addByEmailClient.offer = offer(mailbox(recent = AddByEmailFixtures.recent)) }
        tap(SETTINGS_ADD_BY_EMAIL)
        node(ADD_BY_EMAIL_NOTIFY).performScrollTo()
        settle()
        // Rows outside the window have their bounds clipped away, so their positions are compared.
        val top = { tag: String -> node(tag).fetchSemanticsNode().positionInRoot.y }
        assertTrue(top(ADD_BY_EMAIL_AUTOMATIC) < top(ADD_BY_EMAIL_RENEW))
        assertTrue(top(ADD_BY_EMAIL_RENEW) < top(ADD_BY_EMAIL_NOTIFY) && top(ADD_BY_EMAIL_NOTIFY) < top(ADD_BY_EMAIL_OFF))
        assertEquals(ToggleableState.On, toggled(ADD_BY_EMAIL_NOTIFY))
        assertEquals(Role.Switch, node(ADD_BY_EMAIL_NOTIFY).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Role))
        val said = node(ADD_BY_EMAIL_NOTIFY).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text)?.map { it.text }
        assertEquals(listOf(text("inbox.notify")), said)

        flip(ADD_BY_EMAIL_NOTIFY)
        assertEquals(ToggleableState.Off, toggled(ADD_BY_EMAIL_NOTIFY))
        assertEquals(false, source.addByEmailClient.offer?.inbox?.notify)
        // TalkBack's double tap reaches the row as one switch.
        node(ADD_BY_EMAIL_NOTIFY).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        assertEquals(ToggleableState.On, toggled(ADD_BY_EMAIL_NOTIFY))
        assertEquals(listOf("read", "notify false", "notify true"), source.addByEmailClient.requests)
    }

    @Test
    fun anAddressSwitchedOffShowsItOff() {
        show { addByEmailClient.offer = offer(mailbox(notify = false)) }
        tap(SETTINGS_ADD_BY_EMAIL)
        assertEquals(ToggleableState.Off, toggled(ADD_BY_EMAIL_NOTIFY))
    }

    /** While the switch saves it can't be flipped again and keeps what was saved; a save that fails leaves it so. */
    @Test
    fun aSwitchThatFailsToSaveStaysAsItWas() {
        show {
            addByEmailClient.offer = offer()
            addByEmailClient.changing = CompletableDeferred()
        }
        tap(SETTINGS_ADD_BY_EMAIL)
        flip(ADD_BY_EMAIL_NOTIFY)
        assertTrue(exists(ADD_BY_EMAIL_WORKING))
        assertFalse(enabled(ADD_BY_EMAIL_NOTIFY))
        assertEquals(ToggleableState.On, toggled(ADD_BY_EMAIL_NOTIFY))

        source.addByEmailClient.changeFailure = IOException("Offline")
        compose.runOnUiThread { source.addByEmailClient.changing!!.complete(Unit) }
        settle()
        assertFalse(exists(ADD_BY_EMAIL_WORKING))
        assertEquals(localizer.errorMessage(IOException("Offline"), fallback = "inbox.failed"), words(ADD_BY_EMAIL_ERROR))
        assertTrue(enabled(ADD_BY_EMAIL_NOTIFY))
        assertEquals(ToggleableState.On, toggled(ADD_BY_EMAIL_NOTIFY))
        assertEquals(ADDRESS, words(ADD_BY_EMAIL_ADDRESS))
        assertEquals(listOf("read", "notify false"), source.addByEmailClient.requests)
    }

    /** The steps stay folded until wanted, or until Gmail's confirmation waits: then they open on Gmail. */
    @Test
    fun gmailsConfirmationOpensTheStepsOnGmail() {
        show { addByEmailClient.offer = offer(mailbox(confirmation = AddByEmailFixtures.confirmation)) }
        tap(SETTINGS_ADD_BY_EMAIL)
        assertTrue(exists(ADD_BY_EMAIL_CONFIRMATION))
        assertTrue(exists(ADD_BY_EMAIL_FILTER))
        assertTrue(shows(localizer.text("inbox.confirm.code", mapOf("code" to "123456789"))))
        tap("$ADD_BY_EMAIL_TAG.service.outlook")
        assertFalse(exists(ADD_BY_EMAIL_FILTER))
        assertTrue(exists("$ADD_BY_EMAIL_TAG.word.shipped"))
    }

    @Test
    fun theStepsOpenAndFoldAndTheFilterKeepsALanguage() {
        show { addByEmailClient.offer = offer() }
        tap(SETTINGS_ADD_BY_EMAIL)
        assertFalse(exists(ADD_BY_EMAIL_FILTER))
        tap(ADD_BY_EMAIL_AUTOMATIC)
        // The reader's address isn't Outlook's, iCloud's or Yahoo's: Gmail's steps.
        assertTrue(words(ADD_BY_EMAIL_FILTER)!!.startsWith("subject:(shipped OR "))
        val english = "$ADD_BY_EMAIL_TAG.language.en"
        assertEquals(ToggleableState.On, node(english).fetchSemanticsNode().config[SemanticsProperties.ToggleableState])
        // The last language chosen stays so.
        tap(english)
        assertEquals(ToggleableState.On, node(english).fetchSemanticsNode().config[SemanticsProperties.ToggleableState])
        tap("$ADD_BY_EMAIL_TAG.language.fr")
        assertTrue(" colis " in words(ADD_BY_EMAIL_FILTER)!!)
        tap(english)
        assertTrue(words(ADD_BY_EMAIL_FILTER)!!.startsWith("subject:(expédié OR "))
        tap(ADD_BY_EMAIL_AUTOMATIC)
        assertFalse(exists(ADD_BY_EMAIL_FILTER))
    }
}
