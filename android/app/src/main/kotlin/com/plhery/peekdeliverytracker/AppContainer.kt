package com.plhery.peekdeliverytracker

import android.app.Activity
import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.plhery.peekdeliverytracker.core.AnalyticsCatalog
import com.plhery.peekdeliverytracker.core.AnalyticsLifecycleObserver
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierBrandAssets
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierCatalogRefreshResult
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.DeliveryApiClient
import com.plhery.peekdeliverytracker.core.FriendsActivityStore
import com.plhery.peekdeliverytracker.core.FriendInvitationStore
import com.plhery.peekdeliverytracker.core.FriendsClient
import com.plhery.peekdeliverytracker.core.FriendsStore
import com.plhery.peekdeliverytracker.core.friends
import com.plhery.peekdeliverytracker.core.DeliveryEmail
import com.plhery.peekdeliverytracker.core.DeliveryLiveActivities
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DemoRepository
import com.plhery.peekdeliverytracker.core.DeviceParcelClient
import com.plhery.peekdeliverytracker.core.DeviceParcelFiles
import com.plhery.peekdeliverytracker.core.DeviceParcels
import com.plhery.peekdeliverytracker.core.HttpTransport
import com.plhery.peekdeliverytracker.core.LiveActivityRevocations
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NativeVerification
import com.plhery.peekdeliverytracker.core.ParcelLinkReader
import com.plhery.peekdeliverytracker.core.ParcelLinkStore
import com.plhery.peekdeliverytracker.core.ParcelSharePreview
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.PostcodeMemory
import com.plhery.peekdeliverytracker.core.PushNotifications
import com.plhery.peekdeliverytracker.core.ReviewPrompt
import com.plhery.peekdeliverytracker.core.ReviewRequester
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.core.TextMeasurer
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.core.UserDefaults
import com.plhery.peekdeliverytracker.core.WebAuthenticating
import com.plhery.peekdeliverytracker.core.identifying
import com.plhery.peekdeliverytracker.platform.AndroidPushNotifications
import com.plhery.peekdeliverytracker.platform.AppNotifications
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.CacheExportFiles
import com.plhery.peekdeliverytracker.platform.CarrierCatalogLoader
import com.plhery.peekdeliverytracker.platform.CustomTabSignIn
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.platform.DeviceParcelsFile
import com.plhery.peekdeliverytracker.platform.FirebasePushTokens
import com.plhery.peekdeliverytracker.platform.KeystoreSecretStore
import com.plhery.peekdeliverytracker.platform.LauncherShortcuts
import com.plhery.peekdeliverytracker.platform.MemoryCookieJar
import com.plhery.peekdeliverytracker.platform.NotificationPermission
import com.plhery.peekdeliverytracker.platform.NoAppleSignIn
import com.plhery.peekdeliverytracker.platform.NoDeviceAttestation
import com.plhery.peekdeliverytracker.platform.OkHttpTransport
import com.plhery.peekdeliverytracker.platform.ParcelCacheFolder
import com.plhery.peekdeliverytracker.platform.PeekHttp
import com.plhery.peekdeliverytracker.platform.PlayReviews
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults.Companion.USE_12_HOUR_TIME
import com.plhery.peekdeliverytracker.platform.PushTokens
import com.plhery.peekdeliverytracker.platform.QueuedDeviceParcelStorage
import com.plhery.peekdeliverytracker.platform.ReadAheadSecretStore
import com.plhery.peekdeliverytracker.platform.SensorArrivalMotion
import com.plhery.peekdeliverytracker.platform.WebViewTurnstilePages
import com.plhery.peekdeliverytracker.ui.TurnstileSheet
import com.plhery.peekdeliverytracker.ui.arrival.ArrivalMotionSource
import com.plhery.peekdeliverytracker.ui.deliveries.LaunchPreviews
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelLinkSheetState
import com.plhery.peekdeliverytracker.ui.draw.MapTextWidths
import com.plhery.peekdeliverytracker.widget.GlanceWidgetCenter
import com.plhery.peekdeliverytracker.widget.WidgetCardPictures
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

/**
 * The stores the iPhone app makes at launch (PeekDeliveryTrackerApp's `init`, `DeliveryAnalytics.shared`,
 * `NativeVerification.shared`, `CarrierCatalog.shared`) and what its root view starts with them (RootView's tasks), made
 * once for the process by PeekApplication. Every store shares `scope`, the one `MainScope()` the app keeps: on the main
 * thread, never cancelled while the app runs. The screens read the stores' StateFlows.
 *
 * It is made off the main thread ([make]): the stores read their secrets and files as they are made, and the session's
 * is read then too, for the bootstrap ([ReadAheadSecretStore]). The device's parcels are saved on a thread of their own
 * ([QueuedDeviceParcelStorage]). The session is still saved on the main thread, as the iPhone saves it: SessionStore
 * saves it and then takes it in, with no suspension between, so a sign-out can never land in between and see a late
 * write bring the account back, and a save that failed is the sign-in's or refresh's failure.
 *
 * What the app cannot do yet is a stand-in (platform/StandIns.kt): Sign in with Apple and device attestation. Web
 * sign-in opens a Custom Tab (platform/CustomTabSignIn.kt). Pushes come through Firebase Cloud Messaging in a build
 * with its settings, and in no other (platform/FirebasePush.kt).
 * The home screen widget reads what the store publishes for it, and the cards' pictures it draws (widget/); the next
 * delivery's shortcut on the app's icon follows it (platform/LauncherShortcuts.kt).
 */
class AppContainer(
    private val context: Context,
    val configuration: AppConfiguration = BuildSettings.configuration,
    http: OkHttpClient = PeekHttp.client(MemoryCookieJar()),
    val scope: CoroutineScope = MainScope(),
    private val debug: Boolean = BuildConfig.DEBUG,
    /** What a parcel's link reads: the API's, unless a test gives its own. */
    linkReader: ParcelLinkReader? = null,
    /** POST_NOTIFICATIONS' question from the window on screen: the process's, which each window registers with. */
    notificationPermission: NotificationPermission = (context as? PeekApplication)?.notificationPermission ?: NotificationPermission(context),
    /** The notifications the app posts and takes away: the process's. */
    val posted: AppNotifications = (context as? PeekApplication)?.posted ?: AppNotifications(context),
    /** Web sign-in in an Auth Tab or a Custom Tab over the window in front: the process's, which each window registers with. */
    webAuthentication: WebAuthenticating = (context as? PeekApplication)?.signInTab ?: CustomTabSignIn(),
    /** The pushes' tokens: Firebase Cloud Messaging's in a build with its settings, or a test's; null without them. */
    pushTokens: PushTokens? = FirebasePushTokens.of(context, BuildSettings.firebase),
) {
    /** `UserDefaults.standard`. */
    val defaults: UserDefaults = PreferencesDefaults.standard(context)

    /** The app group's defaults, which the iPhone shares with its widget. */
    private val groupDefaults: UserDefaults = PreferencesDefaults.appGroup(context, configuration.appGroupIdentifier)

    /** What the store publishes for the widget, which the next delivery's shortcut on the app's icon follows too. */
    private val deliveryWidgetStore = DeliveryWidgetSharedStore(groupDefaults)

    private val userAgent = PeekHttp.userAgent(BuildConfig.VERSION_CODE)
    private val log: ((String) -> Unit)? = if (debug) { line -> Log.d("PeekHttp", line) } else null

    /** URLSession.shared: the API, sign-in, catalog and link requests, cookies kept for the process. */
    val transport: HttpTransport = OkHttpTransport(http, log).identifying(userAgent)

    /**
     * `Localizer()`: the language saved here, else the phone's first the app speaks, and the phone's again when they
     * change ([deviceLanguagesChanged]). Where the copy names the iPhone, its `.android` variant names this device and
     * Android's settings, as an iPad reads `.ipad`.
     */
    val localizer = Localizer(deviceLanguage(), variant = "android", use24HourTime = !defaults.bool(USE_12_HOUR_TIME))

    /** The language the phone's languages gave, taken without being chosen; null once a change has been seen. */
    private var followed: AppLanguage? = null

    /** `DeliveryAnalytics.shared`, through an ephemeral session of its own: its cookies are its alone, in memory. */
    val analytics = DeliveryAnalytics(
        app = configuration,
        transport = OkHttpTransport(http.newBuilder().cookieJar(MemoryCookieJar()).build(), log).identifying(userAgent),
        defaults = defaults,
        scope = scope,
        platform = { Device.analyticsPlatform(context) },
        version = BuildConfig.VERSION_NAME,
        preferredLanguages = Device::systemLanguages,
        collects = !Device.isEmulator,
        catalog = AnalyticsCatalog.bundled,
    )

    private val analyticsObserver = AnalyticsLifecycleObserver(analytics, scope)

    /** Whether the root has shown, its first screen counted ([analytics]); the links opened before then wait for it. */
    private var rootShown = false
    private var linksBeforeRoot = 0

    /** `ArrivalMotion`'s device motion, which the welcome's parcel tilts with: the phone's sensors. */
    val motion: ArrivalMotionSource = SensorArrivalMotion(context)

    val session = SessionStore(
        configuration = configuration,
        defaults = defaults,
        persistence = ReadAheadSecretStore(KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.auth")),
        transport = transport,
        appleSignIn = NoAppleSignIn,
        webAuthentication = webAuthentication,
        scope = scope,
        analytics = analytics::action,
    )

    /** `NativeTurnstilePresenter.shared`: the browser check's sheet, in a WebView, over whichever window is in front. */
    val turnstile = TurnstileSheet(WebViewTurnstilePages)

    /**
     * `NativeVerification.shared`, its records in the keychain service the iPhone keeps them in. The browser check is
     * its only way through (X-AD29): no device attestation stands in for App Attest.
     */
    val verification = NativeVerification(
        provider = NoDeviceAttestation,
        present = turnstile,
        persistence = KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.appAttest"),
        language = { localizer.language },
    )

    /** `DeliveryLiveActivities`: none on Android. */
    val liveActivities: DeliveryLiveActivities = DeliveryLiveActivities.None

    private val pushes = AndroidPushNotifications(context, notificationPermission, pushTokens, received = { forwardPushToken(it) })

    val notifications: PushNotifications = pushes

    /**
     * When the app asks for a rating (ReviewPrompt), in this version. A debug build's `sdt.debug.reviewDue` asks at
     * every calm moment, as the iPhone's `-sdt.debug.reviewDue YES`, over the demo too.
     */
    val reviewPrompt = ReviewPrompt(defaults, BuildConfig.VERSION_NAME, forced = ::reviewForced)

    /** `ReviewPrompt.forced`: a debug build's `sdt.debug.reviewDue`, read at each use as the iPhone reads it. */
    private fun reviewForced(): Boolean = debug && defaults.bool(DEBUG_REVIEW_DUE)

    /**
     * `DemoModeBar.hidden`: a debug build's `sdt.debug.storeScreenshots` leaves the demo's bar out, for the store's
     * screenshots, as the iPhone's `-sdt.debug.storeScreenshots YES`; read at each use as the iPhone reads it.
     */
    val storeScreenshots: Boolean get() = debug && defaults.bool(DEBUG_STORE_SCREENSHOTS)

    /** The system's prompt for a rating over `activity`: Play In-App Review, where the iPhone asks StoreKit. */
    fun reviews(activity: Activity): ReviewRequester = PlayReviews(activity)

    /** The map's names, measured as the renderer writes them; made when a map first needs it. */
    val mapText: TextMeasurer by lazy { MapTextWidths.of(context) }

    /** The iPhone's `-sdt.debug.importCharges`: a debug build's import charges preview (ImportChargesPreview), from the defaults. */
    private val importCharges: () -> String? = { if (debug) defaults.string(DEBUG_IMPORT_CHARGES) else null }

    /**
     * What a debug build opens at launch ([LaunchPreviews]), as the iPhone's `#if DEBUG` launch arguments: the share
     * sheet in `sdt.debug.parcelShare`'s state, the question in `sdt.debug.parcelFeedback`'s (on the parcel
     * `sdt.debug.parcelFeedback.parcel` names) and the import charges; read at each use. A release build opens none.
     */
    val launchPreviews: LaunchPreviews get() = if (!debug) LaunchPreviews() else LaunchPreviews(
        feedback = defaults.string(DEBUG_PARCEL_FEEDBACK),
        feedbackParcel = defaults.string(DEBUG_PARCEL_FEEDBACK_PARCEL),
        share = defaults.string(DEBUG_PARCEL_SHARE),
        importCharges = importCharges(),
        prepare = { parcelId, variant -> ParcelSharePreview.prepare(parcelId, variant, defaults) },
    )

    /** The account's service, which the parcels and the friends ask (`DeliveryAPIClient`). */
    private val api = DeliveryApiClient(
        configuration, session, transport, verification, deviceName = Device.name, region = Device::region, debug = debug,
        analytics = analytics::action,
    )

    val parcels: ParcelStore = run {
        val region = Device::region
        val deviceParcels = DeviceParcels(
            client = if (configuration.mode == AppConfiguration.Mode.Api) {
                DeviceParcelClient.api(configuration, transport, verification, region)
            } else {
                null
            },
            storage = QueuedDeviceParcelStorage(
                DeviceParcelFiles(
                    KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.deviceParcels"), DeviceParcelsFile.of(context),
                    forgets = KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.deviceParcelForgets"),
                    log = { line -> Log.w("PeekDeviceParcels", line) },
                ),
            ),
            postcodes = PostcodeMemory(defaults),
            scope = scope,
            region = region,
            language = { localizer.language },
        )
        ParcelStore(
            configuration = configuration,
            session = session,
            localizer = localizer,
            api = api,
            device = deviceParcels,
            liveActivityRevocations = LiveActivityRevocations(
                configuration, transport, KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.liveActivityRevocations"),
            ),
            defaults = defaults,
            deliveryWidgetStore = deliveryWidgetStore,
            cacheFiles = ParcelCacheFolder.of(context),
            notifications = notifications,
            widgetCenter = LauncherShortcuts.widgetCenter(context, deliveryWidgetStore, scope, GlanceWidgetCenter(context)),
            exportFiles = CacheExportFiles.of(context),
            region = region,
            scope = scope,
            liveActivities = liveActivities,
            widgetPictures = WidgetCardPictures(context),
            analytics = analytics::action,
            importCharges = importCharges,
        )
    }

    /** The account's friends (`DeliveryAPIClient`'s), which the friends' stores ask. */
    val friendsClient: FriendsClient = api.friends()

    /** FriendsView's `FriendsStore()`, kept for the process as the tab's `@StateObject` is kept for the window. */
    val friends = FriendsStore(friendsClient)

    /** The app's `FriendsActivityStore()`: who accepted an invitation, while someone is signed in. */
    val friendsActivity = FriendsActivityStore(friendsClient, signedIn = { session.user != null }, removeNotifications = posted::removeFriend)

    /**
     * The app's `FriendInvitationStore()`: the invitation a link brought, its code waiting in the app's defaults, its
     * sender's name asked of the service without signing in (`invitationPreview(code:)`).
     */
    val invitation = FriendInvitationStore(
        defaults, configuration.apiBaseUrl, configuration.linkHosts,
        preview = { DeliveryApiClient.invitationPreview(it, configuration, transport) },
        friends = friendsClient,
    )

    /** `ParcelLinkStore()`, or in a debug build the preview `sdt.debug.parcelLink` asks for; a test's reads before both. */
    val links: ParcelLinkStore = linkReader?.let { ParcelLinkStore(defaults, read = it) }
        ?: (if (debug) debugLinkPreview() else null)
        ?: ParcelLinkStore(defaults, configuration, api = { id, key -> DeliveryApiClient.publicParcel(id, key, configuration, transport) })

    /** The link sheet's presentation (ParcelLinkSheet's `@State`), which outlives the window made again. */
    val linkSheet = ParcelLinkSheetState()

    /**
     * `ReviewPrompt.settled(session:store:links:invitation:)` as the stores change: someone's own parcels, loaded and
     * current, with nothing over them.
     */
    val reviewSettled: StateFlow<Boolean> = combine(
        listOf(
            session.stateFlow, parcels.parcelsFlow, parcels.loadingFlow, parcels.errorMessageFlow, parcels.authenticationRequiredFlow,
            parcels.usingCachedDataFlow, parcels.notificationEnableInProgressFlow, links.routeFlow, invitation.presentingFlow,
        ),
    ) { reviewSettledNow() }.stateIn(scope, SharingStarted.Eagerly, reviewSettledNow())

    private fun reviewSettledNow(): Boolean = ReviewPrompt.settled(
        session = session.stateFlow.value,
        hasParcels = parcels.parcelsFlow.value.isNotEmpty(),
        loading = parcels.loadingFlow.value,
        errorMessage = parcels.errorMessageFlow.value,
        authenticationRequired = parcels.authenticationRequiredFlow.value,
        usingCachedData = parcels.usingCachedDataFlow.value,
        notificationEnableInProgress = parcels.notificationEnableInProgressFlow.value,
        linkOpen = links.routeFlow.value != null,
        invitationShown = invitation.isPresenting,
        asksInDemo = reviewForced(),
    )

    /** `parcels.shouldInviteNotifications` as the session, the parcels and what the phone allows change. */
    val notificationInvitation: StateFlow<Boolean> = combine(
        listOf(
            session.stateFlow, parcels.parcelsFlow, parcels.loadingFlow, parcels.errorMessageFlow, parcels.authenticationRequiredFlow,
            parcels.notificationStateLoadedFlow, parcels.notificationStatusFlow, parcels.notificationsEnabledOnDeviceFlow,
            parcels.notificationInvitationDismissedFlow,
        ),
    ) { parcels.shouldInviteNotifications }.stateIn(scope, SharingStarted.Eagerly, parcels.shouldInviteNotifications)

    /** `parcels.deliveryEmail` as the account's preferences and the session change. */
    val deliveryEmail: StateFlow<DeliveryEmail?> = combine(session.stateFlow, parcels.notificationPreferencesFlow) { _, _ ->
        parcels.deliveryEmail
    }.stateIn(scope, SharingStarted.Eagerly, parcels.deliveryEmail)

    private val catalogState = MutableStateFlow<CarrierCatalog?>(null)

    /** The app's carrier catalog, installed as `CarrierCatalog.shared` once read; null until then. */
    val catalog: StateFlow<CarrierCatalog?> = catalogState.asStateFlow()

    private var started = false

    /** The app is in front (`scenePhase == .active`); it opens so unless it was left before its stores were made. */
    private var active = true

    init {
        // Localizer's `saveSharedLanguage()`: the widget reads the app's language from the group.
        groupDefaults.set(localizer.language.rawValue, forKey = AppLanguage.storageKey)
    }

    /**
     * Reads the carrier catalog off the main thread and installs it, then starts what RootView's tasks start: the
     * session's bootstrap, the catalog's refresh, the parcels of each identity, the browser check's preparation and the
     * sign-in emails' language. Once.
     *
     * `lifecycle` is the app's windows' ([AppForeground]), whose start and stop are the app coming to the front and
     * leaving it (`scenePhase`): a dialog, the share sheet, a permission prompt or a window beside it in split screen
     * covers the app without stopping it, a rotation or a foldable opened makes the window again without a stop, and the
     * share card, the iPhone's extension, neither starts nor stops it.
     */
    fun start(lifecycle: Lifecycle) {
        check(!started) { "The app's stores start once." }
        started = true
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) sceneActive(false)
        // The notifications' channels, named in the app's language, before anything is posted in them.
        posted.updateChannels(localizer)
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) = sceneActive(true)
            override fun onStop(owner: LifecycleOwner) = sceneActive(false)
        })
        // The iPhone's detached lookup tables, built before the first card or parcel needs them; the copy too.
        scope.launch(Dispatchers.Default) {
            CarrierBrandAssets.shared
            TrackingLocation.prepare()
            // Any line of copy reads Localization.json, half a megabyte.
            Localizer(AppLanguage.En).text("")
        }
        scope.launch {
            val catalog = withContext(Dispatchers.Default) {
                CarrierCatalog(CarrierCatalog.cacheFile(context.cacheDir), CarrierCatalogLoader(transport))
            }
            CarrierCatalog.install(catalog)
            catalogState.value = catalog
            root()
        }
    }

    private fun root() {
        scope.launch { if (session.state == AuthenticationState.Loading) session.bootstrap() }
        scope.launch { refreshCatalog() }
        val identity = session.stateFlow.map { it.rootIdentity }.distinctUntilChanged()
        // `.task(id: sessionIdentity + (invitation.isPresenting ? "-invitation" : ""))`: an invitation coming or going
        // starts the parcels again.
        val parcelsIdentity = combine(identity, invitation.presentingFlow) { id, presenting -> if (presenting) "$id-invitation" else id }
        scope.launch {
            parcelsIdentity.distinctUntilChanged().collectLatest {
                if (session.isAuthenticated || session.isGuest) {
                    parcels.start()
                } else if (session.state != AuthenticationState.Loading) {
                    parcels.clearDeliverySurfaces()
                }
            }
        }
        if (configuration.mode == AppConfiguration.Mode.Api) {
            scope.launch { verification.prepare(configuration.apiBaseUrl, transport) }
        }
        scope.launch {
            combine(identity, localizer.languageFlow) { _, language -> language }.collectLatest { session.saveEmailLanguage(it) }
        }
        scope.launch { localizer.languageFlow.drop(1).collect(::languageChanged) }
        scope.launch {
            localizer.use24HourTimeFlow.collect { enabled ->
                if (defaults.bool(USE_12_HOUR_TIME) == enabled) {
                    defaults.set(!enabled, forKey = USE_12_HOUR_TIME)
                    parcels.refreshDeliverySurfaces()
                }
            }
        }
        context.registerComponentCallbacks(object : ComponentCallbacks {
            override fun onConfigurationChanged(newConfig: Configuration) = deviceLanguagesChanged()

            @Deprecated("Deprecated in Java")
            override fun onLowMemory() = Unit
        })
        analytics()
    }

    /**
     * AnalyticsLifecycleObserver's view: the screen the session shows (`invitation` while one is presented), in its mode,
     * and whether a link covers it.
     */
    private fun analytics() {
        analyticsObserver.appeared()
        scope.launch {
            combine(
                session.stateFlow, parcels.parcelsFlow.map { it.isEmpty() }.distinctUntilChanged(), links.routeFlow, invitation.presentingFlow,
            ) { state, empty, route, presenting ->
                Triple(AnalyticsLifecycleObserver.screen(presenting, state, hasParcels = !empty), analyticsMode(), route != null)
            }.distinctUntilChanged().collect { (screen, mode, linkOpen) ->
                analyticsObserver.update(screen, mode, linkOpen)
                if (!rootShown) {
                    rootShown = true
                    repeat(linksBeforeRoot) { analyticsObserver.urlOpened() }
                    linksBeforeRoot = 0
                }
            }
        }
    }

    /**
     * `.onOpenURL`'s `deep-link-open`: the app was opened with a link, whatever it leads to. One that comes with the
     * app's start is counted once the root has shown and counted its first screen, as `.onOpenURL` comes to a root
     * already shown. On the main thread.
     */
    fun linkOpened() {
        if (rootShown) analyticsObserver.urlOpened() else linksBeforeRoot++
    }

    /**
     * `.didReceiveFriendActivity`: a friend accepted while the app is on screen, so the notices are read again at once
     * while someone is signed in, rather than at the next of their 30 s. On the main thread.
     */
    fun friendActivity() {
        if (session.user == null || !active) return
        scope.launch { friendsActivity.refresh() }
    }

    /** `notification-open`: a notification was opened, counted as the iPhone counts it ([PeekApplication.notificationOpened]). */
    fun notificationOpened() = analytics.action("notification-open")

    private fun analyticsMode(): DeliveryAnalytics.Mode = when {
        session.isDemo -> DeliveryAnalytics.Mode.Demo
        session.user == null -> DeliveryAnalytics.Mode.Anonymous
        else -> DeliveryAnalytics.Mode.Account
    }

    private fun deviceLanguage(): AppLanguage =
        AppLanguage.current(defaults.string(AppLanguage.storageKey), Device.preferredLanguages())

    /**
     * The phone's languages changed, which relaunches the iPhone app: with no language saved, the app takes the one
     * they now give, as the relaunched app would, without saving it or counting a change of language.
     */
    private fun deviceLanguagesChanged() {
        val language = deviceLanguage()
        if (language == localizer.language) return
        followed = language
        localizer.language = language
    }

    /**
     * Localizer's `didSet` and RootView's `onChange(of: localizer.language)`. The language the phone's gave
     * ([deviceLanguagesChanged]) is the relaunched iPhone app's: saved for the widget alone, and no change counted.
     */
    private fun languageChanged(language: AppLanguage) {
        val chosen = language != followed
        followed = null
        if (chosen) defaults.set(language.rawValue, forKey = AppLanguage.storageKey)
        groupDefaults.set(language.rawValue, forKey = AppLanguage.storageKey)
        if (chosen) analytics.action("language-change")
        parcels.demoLanguageChanged()
        parcels.refreshDeliverySurfaces()
        posted.updateChannels(localizer)
        notifications.currentDeviceToken?.let { forwardPushToken(it, language) }
    }

    /** A token Firebase made (`FirebaseMessagingService.onNewToken`), kept and handed on unless it is the one kept. */
    fun pushTokenRefreshed(token: String) = pushes.tokenRefreshed(token)

    /**
     * RootView's `.didReceiveAPNSToken`, and its language's change: the token goes to the account's devices and to the
     * parcels this phone follows without one, in `language`.
     */
    private fun forwardPushToken(token: String, language: AppLanguage = localizer.language) {
        scope.launch {
            parcels.forwardNativePushToken(token, language)
            parcels.registerDeviceAlerts(token, language)
        }
    }

    private suspend fun refreshCatalog() {
        val catalog = catalogState.value ?: return
        if (catalog.refresh(configuration.apiBaseUrl) == CarrierCatalogRefreshResult.Updated) parcels.refreshDeliverySurfaces()
    }

    /**
     * The app came to the front or left it (`scenePhase`): the parcels poll only while it is in front, an invitation's
     * sender is forgotten as it leaves, and coming back counts a foreground and asks for a newer catalog.
     */
    private fun sceneActive(active: Boolean) {
        if (active == this.active) return
        this.active = active
        parcels.setActive(active)
        // RootView's `onChange(of: scenePhase)`: who sent an invitation is asked afresh on the way back.
        if (!active) invitation.clearPreview()
        if (!active) return
        analyticsObserver.foreground()
        scope.launch { refreshCatalog() }
    }

    private fun debugLinkPreview(): ParcelLinkStore? = ParcelLinkStore.debugPreview(
        variant = defaults.string("sdt.debug.parcelLink"),
        defaults = defaults,
        signedIn = defaults.bool("sdt.debug.parcelLink.signedIn"),
        importCharges = importCharges(),
        demoParcels = { DemoRepository(defaults, { localizer.language }, importCharges = importCharges).list() },
    )

    companion object {
        private const val DEBUG_IMPORT_CHARGES = "sdt.debug.importCharges"
        private const val DEBUG_PARCEL_FEEDBACK = "sdt.debug.parcelFeedback"
        private const val DEBUG_PARCEL_FEEDBACK_PARCEL = "sdt.debug.parcelFeedback.parcel"
        private const val DEBUG_PARCEL_SHARE = "sdt.debug.parcelShare"
        private const val DEBUG_REVIEW_DUE = "sdt.debug.reviewDue"
        private const val DEBUG_STORE_SCREENSHOTS = "sdt.debug.storeScreenshots"

        /** A container made on the IO dispatcher, where its stores first read the Keystore and the disk. */
        suspend fun make(context: Context, configuration: AppConfiguration = BuildSettings.configuration): AppContainer =
            withContext(Dispatchers.IO) { AppContainer(context, configuration) }

        /** RootView's `sessionIdentity`. */
        private val AuthenticationState.rootIdentity: String
            get() = when (this) {
                AuthenticationState.Loading -> "loading"
                AuthenticationState.Welcome -> "welcome"
                AuthenticationState.Demo -> "demo"
                AuthenticationState.Unconfigured -> "unconfigured"
                AuthenticationState.SignedOut -> "signed-out"
                is AuthenticationState.SignedIn -> user.id.toString().uppercase()
            }
    }
}
