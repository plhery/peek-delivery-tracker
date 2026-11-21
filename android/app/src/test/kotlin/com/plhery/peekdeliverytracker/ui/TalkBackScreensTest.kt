package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.arrival.signInConfiguration
import com.plhery.peekdeliverytracker.ui.arrival.signInSession
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.card
import com.plhery.peekdeliverytracker.ui.deliveries.cardNamed
import com.plhery.peekdeliverytracker.ui.deliveries.guideIdentities
import com.plhery.peekdeliverytracker.ui.deliveries.openCard
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.settings.AddByEmailFixtures
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ADD_BY_EMAIL
import com.plhery.peekdeliverytracker.ui.settings.ASSISTANTS_SETUP
import com.plhery.peekdeliverytracker.ui.settings.AssistantsFixtures
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ASSISTANTS
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_UPDATES
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.util.UUID

/**
 * Everything TalkBack reads, in order and as it says it, on a phone in English, on the screens the accessibility checks
 * cover (AccessibilityChecksDeviceTest): the welcome, the first open, the list, a card's menu, a parcel's page and its
 * menu, Add, and Settings past the widgets' switch. Each stop is its label and, in brackets, the role, field, heading
 * and state TalkBack says after it ([talkBackStops]). A pass with TalkBack on a phone then only has to confirm these.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class TalkBackScreensTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    /** The app as [state], from a server that serves its guides ([guideIdentities]), as Peek's does. */
    private fun show(state: AuthenticationState, parcels: List<Parcel> = DemoCards.parcels(), prepare: FakeDeliveries.() -> Unit = {}) {
        val source = FakeDeliveries(parcels, state).apply(prepare)
        compose.waitsForMaps()
        compose.setContent { RootHost(state = state, source = source, identities = guideIdentities) }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun assertSpoken(screen: String, expected: List<String>) =
        assertEquals("What TalkBack reads on $screen", expected, compose.talkBackStops(compose.activity.window.decorView, spoken = true))

    /** With sign-in set up: Google and an email code offered. */
    @Test
    fun theWelcome() {
        compose.setContent {
            val scope = rememberCoroutineScope()
            RootHost(session = remember { signInSession(scope, signInConfiguration(google = true)).also { it.showSignIn() } })
        }
        settle()
        assertSpoken(
            "the welcome",
            listOf(
                "Back [button]",
                "EN, Language [dropdownlist, English]",
                "Sign in [heading]",
                "Keep your deliveries synced.",
                "Continue with Google [button]",
                "Sign in with email [button]",
                "Your tracking details stay private. Privacy",
                "Explore the demo [button]",
            ),
        )
    }

    /** The debug build's welcome, which has no sign-in. */
    @Test
    fun theWelcomeWithoutSignIn() {
        show(AuthenticationState.SignedOut, emptyList())
        assertSpoken(
            "the welcome without sign-in",
            listOf(
                "Back [button]",
                "EN, Language [dropdownlist, English]",
                "Sign in [heading]",
                "Keep your deliveries synced.",
                "Sign-in is temporarily unavailable. Please try again later.",
                "You can explore the app with demo parcels while sign-in is unavailable.",
                "Your tracking details stay private. Privacy",
                "Explore the demo [button]",
            ),
        )
    }

    /** The picture first, as VoiceOver reaches the iPhone's: its button starts at the window's top, under the masthead. */
    @Test
    fun theFirstOpen() {
        show(AuthenticationState.Welcome, emptyList())
        assertSpoken(
            "the first open",
            listOf(
                "Open a sample parcel [button]",
                "Peek, Parcel Tracker",
                "Sign in [button]",
                "Where’s my parcel? [heading]",
                "Paste a number, a link or a whole email.",
                "Paste a number, link or message, Tracking number or link [button]",
                "Paste and track [button]",
                "Works with Swiss Post, DHL, UPS, DPD Switzerland, GLS Germany and thousands more carriers",
            ),
        )
    }

    @Test
    fun theListAndACardsMenu() {
        show(READER)
        assertSpoken(
            "the list",
            listOf(
                "Add a parcel [button]",
                "Deliveries [heading]",
                "Account [button]",
                "On the way [heading]",
                "7",
                "Sort & filter [button]",
                "Search parcels [button]",
                "DHL, Next up, New trainers 👟, Ready for pickup · Kiosk im Hauptbahnhof [button]",
                "Chronopost, Birthday gift 🎁, At customs [button]",
                "FedEx, Belgian chocolate 🍫, Out for delivery [button]",
                "DPD Switzerland, tomorrow, Trail weekend kit 🏕️, Received by carrier [button]",
                "GLS Germany, Sat 31 Oct, Moon lamp 🌙, In transit [button]",
                "An Post, Sun 1 Nov, 35mm film rolls 🎞️, Label created [button]",
                "Deliveries [tab, selected]",
                "Passport [tab]",
                "Friends [tab]",
            ),
        )

        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed(GIFT))
        compose.card(GIFT).performTouchInput { longClick() }
        settle()
        assertSpoken(
            "a card's menu",
            listOf(
                "Open parcel [button]",
                "Edit parcel name [button]",
                "Share… [button]",
                "Copy tracking number [button]",
                "Change carrier [button]",
                "Archive parcel [button]",
                "Delete permanently [button]",
            ),
        )
    }

    /** The page's bar first, before the page that scrolls under it, as VoiceOver reaches the navigation bar. */
    @Test
    fun aParcelsPageAndItsMenu() {
        show(READER)
        compose.openCard(GIFT)
        settle()
        assertSpoken(
            "a parcel's page",
            listOf(
                "Deliveries [button]",
                "Parcel actions [button]",
                "Chronopost. Change carrier from Chronopost [button]",
                "Open the map [button]",
                "Share this parcel [button]",
                "Turn off parcel notifications [button]",
                "Birthday gift 🎁 [button]",
                "From Mamie",
                "At customs",
                "Delivery progress [Step 4 of 6: At customs]",
                "Tracking number",
                "LX123456789DE",
                "Copy tracking number [button]",
                "Open the Chronopost website [button]",
                "Weight",
                "1.2 kg",
                "Tracking history, 3 updates [button, Show less]",
                "yesterday [heading]",
                "18:11, Customs is taking a look. No peeking., Basel, Switzerland",
                "Tue 27 Oct [heading]",
                "10:11, Handed over at the post office counter, Lyon, France",
                "04:11, A little surprise is on its way",
            ),
        )

        compose.onNodeWithTag(PAGE_MENU_BUTTON).performClick()
        settle()
        assertSpoken(
            "a parcel's menu",
            listOf(
                "Edit parcel name [button]",
                "Copy tracking number [button]",
                "Change carrier [button]",
                "Archive parcel [button]",
                "Delete permanently [button]",
            ),
        )
    }

    @Test
    fun add() {
        show(READER)
        compose.onNodeWithTag(ADD_BUTTON).performClick()
        settle()
        assertSpoken(
            "Add",
            listOf(
                "Add a parcel [heading]",
                "Close [button]",
                "Tracking number or link",
                "Paste [button]",
                "Tracking number or link, Paste a number, link or message [edit box]",
                "Name",
                "Optional",
                "Name, Coffee beans, new shoes… [edit box]",
                "Add parcel [button, unavailable]",
            ),
        )
    }

    @Test
    fun settingsDownToAccountAndData() {
        show(READER)
        compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
        settle()
        assertSpoken(
            "Settings",
            listOf(
                "Drag handle",
                "Close [button]",
                "Settings [heading]",
                "R",
                "reader@example.com",
                "Signed in as",
                "DELIVERIES [heading]",
                "Notifications, Off [button]",
                "PREFERENCES [heading]",
                "Appearance",
                "System [button]",
                "Light [button, selected]",
                "Dark [button]",
                "Language [dropdownlist, English]",
                "Time format [dropdownlist, Automatic (24-hour)]",
                "Distance [dropdownlist, Automatic (km)]",
                "Weight [dropdownlist, Automatic (kg)]",
                "Dot on updated parcels, A dot appears on a parcel with a new update since you last opened it. [switch, on]",
                "Account & data [button]",
            ),
        )
    }

    @Test
    fun theEmailPage() {
        show(READER) { addByEmailClient.offer = AddByEmailFixtures.offer() }
        compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
        settle()
        compose.onNodeWithTag(SETTINGS_ADD_BY_EMAIL).performScrollTo().performClick()
        settle()
        assertSpoken(
            "the email page",
            listOf(
                "Drag handle",
                "Settings [button]",
                "Add parcels by email [heading]",
                "BETA",
                "Forward a shipping email to your Peek address and Peek adds the parcel. This feature is in beta: it can miss a parcel, and it may still change.",
                "Read the whole guide [button]",
                "YOUR ADDRESS [heading]",
                "add+abcdefghijklmnop@in.example.com",
                "Copy address [button]",
                "Share [button]",
                "Forward automatically [button]",
                "Get a new address [button]",
                "Notify me when an email adds a parcel [switch, on]",
                "Turn off [button]",
            ),
        )
    }

    /** A guest's Delivery updates page: this device's switch, the preset and what it covers, and the email a sign-in away. */
    @Test
    fun aGuestsDeliveryUpdatesPage() {
        show(AuthenticationState.Welcome) { isGuest = true }
        compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
        settle()
        compose.onNodeWithTag(SETTINGS_DELIVERY_UPDATES).performScrollTo().performClick()
        settle()
        assertSpoken(
            "a guest's Delivery updates page",
            listOf(
                "Drag handle",
                "Settings [button]",
                "Notifications [heading]",
                "Choose your updates, then turn on notifications for this device.",
                "Notifications [switch, off]",
                "WHICH UPDATES? [heading]",
                "Every scan, Every new tracking event, including routine carrier scans. [button]",
                "Important steps, Delivery, pickup, customs, missed attempts and returns. [button, selected]",
                "Delivery only, When your parcel goes out for delivery and when it arrives. [button]",
                "The parcels you track here get these notifications too. Mute one, and it stays quiet.",
                "Save preferences [button]",
                "BY EMAIL [heading]",
                "An email when it arrives, Sign in, and Peek writes to the address you sign in with. [button]",
                "Parcels are checked hourly, day and night, and up to every 10 minutes on their delivery day or while you look at them.",
            ),
        )
    }

    /** Settings › Assistants with Claude's steps open, down to where the screen ends. */
    @Test
    fun theAssistantsPage() {
        show(READER) { assistantsClient.offer = AssistantsFixtures.offer() }
        compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
        settle()
        compose.onNodeWithTag(SETTINGS_ASSISTANTS).performScrollTo().performClick()
        settle()
        compose.onNodeWithTag("$ASSISTANTS_SETUP.Claude").performClick()
        settle()
        assertSpoken(
            "the assistants page",
            listOf(
                "Drag handle",
                "Settings [button]",
                "Assistants [heading]",
                "BETA",
                "Connect Claude, ChatGPT, Gemini or another assistant to Peek: it can then tell you where your parcels are and add the " +
                    "tracking numbers you give it. This feature is in beta, and it may still change.",
                "How it works [button]",
                "PEEK’S ADDRESS FOR ASSISTANTS [heading]",
                "https://example.com/mcp",
                "Copy address [button]",
                "ADD PEEK TO YOUR ASSISTANT [heading]",
                "Claude, Add Peek Parcel Tracker from Claude’s connector directory. [button, selected]",
                "On claude.ai, in the Claude desktop app and in the Claude app for iPhone or Android, on any plan. On a Team or " +
                    "Enterprise plan, an owner adds Peek first: Organization settings › Connectors, Browse connectors, then Add to your team.",
                "Open Peek Parcel Tracker in Claude’s connector directory, or in Claude open Customize › Connectors, select + and " +
                    "search for Peek.",
                "Select Connect.",
                "Sign in to Peek when it asks, then select Allow.",
                "In a chat, select + at the bottom left, then Connectors, and turn Peek on.",
            ),
        )
    }

    private companion object {
        const val GIFT = "Birthday gift"
        val READER = AuthenticationState.SignedIn(AuthUser(UUID(0, 13), email = "reader@example.com"))
    }
}
