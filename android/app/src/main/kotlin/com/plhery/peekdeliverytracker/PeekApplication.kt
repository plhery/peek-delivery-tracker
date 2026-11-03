package com.plhery.peekdeliverytracker

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.FriendInvitationLink
import com.plhery.peekdeliverytracker.core.LinkDeliveries
import com.plhery.peekdeliverytracker.core.NativeRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.SharedResources
import com.plhery.peekdeliverytracker.core.UserDefaults
import com.plhery.peekdeliverytracker.platform.AppNotifications
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.CustomTabSignIn
import com.plhery.peekdeliverytracker.platform.NotificationPermission
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import java.io.FileNotFoundException
import java.time.Instant
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** The process: :core reads the shared data (brand, catalog, copy, map) from the app's assets. */
class PeekApplication : Application() {
    private val containerState = MutableStateFlow<AppContainer?>(null)

    /** The app's stores once made and started ([makeContainer]); null until then. */
    val container: StateFlow<AppContainer?> = containerState.asStateFlow()

    private var making = false

    /** How the stores are made: [AppContainer.make], or a test's own, set before the first window asks for them. */
    @VisibleForTesting
    internal var makeStores: suspend (PeekApplication) -> AppContainer = { AppContainer.make(it) }

    /**
     * Where what another app shared waits for Add (`ShareInbox.defaults`): the app's own defaults, which [ShareActivity]
     * saves to, reading the app's language there, and the window takes from; or a test's.
     */
    @VisibleForTesting
    internal var shareDefaults: () -> UserDefaults = { PreferencesDefaults.standard(this) }

    /** A parcel's link that came before the stores were made, which opens once they are. */
    private var waitingLink: ParcelLinkRoute? = null

    /** A friend's invitation that came before the stores were made, which shows once they are. */
    private var waitingInvitation: String? = null

    /** The last link the app saw and when, so the same one again within a second is followed once ([isRepeat]). */
    private val deliveries = LinkDeliveries()

    /** The clock [isRepeat] reads: the phone's, or a test's. */
    @VisibleForTesting
    internal var now: () -> Instant = Instant::now

    /** The links the app was opened with before the stores were made, counted once they are ([linkOpened]). */
    private var waitingOpens = 0

    /** The friends' notifications opened before the stores were made, counted once they are ([notificationOpened]). */
    private var waitingNotificationOpens = 0

    /** The app in front or left, as the stores follow it: its windows', the share card's aside ([AppForeground]). */
    internal val foreground = AppForeground()

    /** POST_NOTIFICATIONS' question, which each window registers with as it is made ([MainActivity]). */
    internal val notificationPermission by lazy { NotificationPermission(this) }

    /**
     * Web sign-in (ASWebAuthenticationSession), opened over the window in front, which each window registers with as
     * it is made ([MainActivity]), and which its callback hands the address back to ([CustomTabSignIn]).
     */
    internal val signInTab by lazy { CustomTabSignIn() }

    /**
     * The notifications posted from a push ([AppNotifications]), on whatever thread it comes: whether the app is on
     * screen is its windows', read from the state they publish to any thread, and a friend's that comes then refreshes
     * the stores' notices on the main thread, once there are stores.
     */
    internal val posted by lazy {
        AppNotifications(
            this,
            onScreen = { foreground.lifecycle.currentStateFlow.value.isAtLeast(Lifecycle.State.STARTED) },
            friendActivity = { container.value?.friendActivity() },
        )
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(foreground)
        val assets = assets
        SharedResources.install { name ->
            try {
                assets.open("peek/$name")
            } catch (_: FileNotFoundException) {
                null
            }
        }
    }

    /**
     * Makes the app's stores off the main thread and starts them on it: once, when the first window asks, as the
     * iPhone makes its stores with the app, and not for every test's process. On the main thread.
     */
    fun makeContainer() {
        if (making) return
        making = true
        MainScope().launch {
            // The carriers' brand data is decoded beside the stores rather than after them: the first window waits for both.
            CarrierIdentities.prefetch(this)
            val container = makeStores(this@PeekApplication)
            container.start(foreground.lifecycle)
            waitingLink?.let(container.links::open)
            waitingLink = null
            waitingInvitation?.let(container.invitation::open)
            waitingInvitation = null
            repeat(waitingOpens) { container.linkOpened() }
            waitingOpens = 0
            repeat(waitingNotificationOpens) { container.notificationOpened() }
            waitingNotificationOpens = 0
            containerState.value = container
        }
    }

    /**
     * `open(_:)`'s first route (A-L02): a parcel's link, on the site (`https://<the site>/p/<id>`, or a host it
     * answered on before) or in the app's scheme (`peekdeliverytracker://p/<id>`), opens its sheet over whatever is up,
     * signed in or not; false for any other address. [ParcelLinkActivity] hands each one here. One that comes before
     * the stores are made waits for them, in the process rather than in a window's saved state, so the owner key a
     * handed-over link carries is never written down; it goes with the process, as the iPhone's does. On the main thread.
     */
    fun openParcelLink(url: String): Boolean {
        val configuration = BuildSettings.configuration
        val route = ParcelLinkRoute(url, configuration.apiBaseUrl, configuration.linkHosts) ?: return false
        val container = container.value
        if (container != null) container.links.open(route) else waitingLink = route
        return true
    }

    /**
     * `open(_:)`'s last route (C-S02): a friend's invitation, on the site (`https://<the site>/i/<id>` or
     * `/invite#<code>`, or a host it answered on before) or in the app's scheme (`peekdeliverytracker://invite#<code>`),
     * shown over everything (`FriendInvitationStore.open(_:)`), which keeps its code for a week in the app's defaults;
     * false for any other address. One that comes before the stores are made waits for them. On the main thread.
     */
    fun openInvitation(url: String): Boolean {
        val configuration = BuildSettings.configuration
        if (!FriendInvitationLink.isInvitation(url, configuration.apiBaseUrl, configuration.linkHosts)) return false
        val container = container.value
        if (container != null) container.invitation.open(url) else waitingInvitation = url
        return true
    }

    /**
     * A link [ParcelLinkActivity] was handed: a parcel's ([openParcelLink]) or else a friend's invitation
     * ([openInvitation]), unless the app has just followed it ([isRepeat]).
     */
    fun openLink(url: String): Boolean = !isRepeat(url) && (openParcelLink(url) || openInvitation(url))

    /**
     * `open(_:)`'s `deliveries.isRepeat(url)` (A-L01): whether this is the address the app saw last, less than a
     * second ago, which it doesn't follow again where `open(_:)` would drop it; a tapped link can reach the app twice.
     * Each address asked about becomes the last seen, the list's own routes too, which open again all the same
     * (`DeliveriesInbox.follow`). The iPhone's one window keeps one `LinkDeliveries`; the app keeps one for
     * [ParcelLinkActivity] and every window's links alike, and a notification opened is no link to it. On the main
     * thread.
     */
    fun isRepeat(url: String): Boolean = deliveries.isRepeat(url, now())

    /**
     * `.onOpenURL`'s count (`deep-link-open`, [AppContainer.linkOpened]): once for each link the app is opened with,
     * whatever it leads to, by [ParcelLinkActivity] or to the window itself, the same one again within a second too, as
     * the iPhone's analytics hear each before `open(_:)` drops a repeat; one that comes before the stores are made is
     * counted once they are. Sign-in's way back (`peekdeliverytracker://auth-callback`, [CustomTabSignIn]) is no link
     * the app opens, and isn't counted. On the main thread.
     */
    fun linkOpened() {
        val container = container.value
        if (container != null) container.linkOpened() else waitingOpens++
    }

    /**
     * `notification-open` for a notification opened, as the iPhone counts it: a friend's always (`.didOpenFriendNotification`),
     * once the stores are made if it comes before; a parcel's only when the deliveries are there to hear it, signed in, in
     * the demo or following parcels on this phone (ParcelListView's `.onReceive`), so not at a cold start, whose list
     * takes it as it appears; a link's as it opens ([com.plhery.peekdeliverytracker.ui.NotificationLinkEffects]). On
     * the main thread.
     */
    fun notificationOpened(route: NativeRoute) {
        val container = container.value
        when (route) {
            is NativeRoute.Friend -> if (container != null) container.notificationOpened() else waitingNotificationOpens++
            is NativeRoute.Parcel -> if (container != null && container.session.state.listShown) container.notificationOpened()
            else -> Unit
        }
    }
}

/** Whether the deliveries are what the session shows (ParcelListView): signed in, the demo, or this phone's own parcels. */
private val AuthenticationState.listShown: Boolean
    get() = this is AuthenticationState.SignedIn || this == AuthenticationState.Demo || this == AuthenticationState.Welcome
