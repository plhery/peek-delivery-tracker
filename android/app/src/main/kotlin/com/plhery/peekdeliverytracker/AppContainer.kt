package com.plhery.peekdeliverytracker

import android.content.Context
import android.util.Log
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
import com.plhery.peekdeliverytracker.core.ParcelLinkStore
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.PostcodeMemory
import com.plhery.peekdeliverytracker.core.PushNotifications
import com.plhery.peekdeliverytracker.core.ReviewRequester
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.core.TextMeasurer
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.core.UserDefaults
import com.plhery.peekdeliverytracker.core.identifying
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.CacheExportFiles
import com.plhery.peekdeliverytracker.platform.CarrierCatalogLoader
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.platform.DeviceParcelsFile
import com.plhery.peekdeliverytracker.platform.KeystoreSecretStore
import com.plhery.peekdeliverytracker.platform.MemoryCookieJar
import com.plhery.peekdeliverytracker.platform.NoAppleSignIn
import com.plhery.peekdeliverytracker.platform.NoDeviceAttestation
import com.plhery.peekdeliverytracker.platform.NoPushNotifications
import com.plhery.peekdeliverytracker.platform.NoReviews
import com.plhery.peekdeliverytracker.platform.NoTurnstile
import com.plhery.peekdeliverytracker.platform.NoWebAuthentication
import com.plhery.peekdeliverytracker.platform.NoWidgets
import com.plhery.peekdeliverytracker.platform.OkHttpTransport
import com.plhery.peekdeliverytracker.platform.ParcelCacheFolder
import com.plhery.peekdeliverytracker.platform.PeekHttp
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.platform.QueuedDeviceParcelStorage
import com.plhery.peekdeliverytracker.platform.ReadAheadSecretStore
import com.plhery.peekdeliverytracker.ui.draw.MapTextWidths
import java.util.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
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
 * What the app cannot do yet is a stand-in (platform/StandIns.kt): notifications, the widget, sign-in, the browser
 * check and the rating prompt.
 */
class AppContainer(
    private val context: Context,
    val configuration: AppConfiguration = BuildSettings.configuration,
    http: OkHttpClient = PeekHttp.client(MemoryCookieJar()),
    val scope: CoroutineScope = MainScope(),
    private val debug: Boolean = BuildConfig.DEBUG,
) {
    /** `UserDefaults.standard`. */
    val defaults: UserDefaults = PreferencesDefaults.standard(context)

    /** The app group's defaults, which the iPhone shares with its widget. */
    private val groupDefaults: UserDefaults = PreferencesDefaults.appGroup(context, configuration.appGroupIdentifier)

    private val userAgent = PeekHttp.userAgent(BuildConfig.VERSION_CODE)
    private val log: ((String) -> Unit)? = if (debug) { line -> Log.d("PeekHttp", line) } else null

    /** URLSession.shared: the API, sign-in, catalog and link requests, cookies kept for the process. */
    val transport: HttpTransport = OkHttpTransport(http, log).identifying(userAgent)

    /** `Localizer()`: the language saved here, else the phone's first the app speaks. */
    val localizer = Localizer(AppLanguage.current(defaults.string(AppLanguage.storageKey), Device.preferredLanguages()))

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

    val session = SessionStore(
        configuration = configuration,
        defaults = defaults,
        persistence = ReadAheadSecretStore(KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.auth")),
        transport = transport,
        appleSignIn = NoAppleSignIn,
        webAuthentication = NoWebAuthentication,
        scope = scope,
        analytics = analytics::action,
    )

    /** `NativeVerification.shared`, its records in the keychain service the iPhone keeps them in. */
    val verification = NativeVerification(
        provider = NoDeviceAttestation,
        present = NoTurnstile,
        persistence = KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.appAttest"),
        language = { localizer.language },
    )

    /** `DeliveryLiveActivities`: none on Android. */
    val liveActivities: DeliveryLiveActivities = DeliveryLiveActivities.None

    val notifications: PushNotifications = NoPushNotifications

    /** The rating prompt's (ReviewPrompt). */
    val reviews: ReviewRequester = NoReviews

    /** The map's names, measured as the renderer writes them; made when a map first needs it. */
    val mapText: TextMeasurer by lazy { MapTextWidths.of(context) }

    /** The iPhone's `-sdt.debug.importCharges`: a debug build's import charges preview (ImportChargesPreview), from the defaults. */
    private val importCharges: () -> String? = { if (debug) defaults.string(DEBUG_IMPORT_CHARGES) else null }

    val parcels: ParcelStore = run {
        val region = Device::region
        val deviceParcels = DeviceParcels(
            client = if (configuration.mode == AppConfiguration.Mode.Api) {
                DeviceParcelClient.api(configuration, transport, verification, region)
            } else {
                null
            },
            storage = QueuedDeviceParcelStorage(
                DeviceParcelFiles(KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.deviceParcels"), DeviceParcelsFile.of(context)),
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
            api = DeliveryApiClient(
                configuration, session, transport, verification, deviceName = Device.name, region = region, debug = debug,
                analytics = analytics::action,
            ),
            device = deviceParcels,
            liveActivityRevocations = LiveActivityRevocations(
                configuration, transport, KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.liveActivityRevocations"),
            ),
            defaults = defaults,
            deliveryWidgetStore = DeliveryWidgetSharedStore(groupDefaults),
            cacheFiles = ParcelCacheFolder.of(context),
            notifications = notifications,
            widgetCenter = NoWidgets,
            exportFiles = CacheExportFiles.of(context),
            region = region,
            scope = scope,
            liveActivities = liveActivities,
            analytics = analytics::action,
            importCharges = importCharges,
        )
    }

    /** `ParcelLinkStore()`, or in a debug build the preview `sdt.debug.parcelLink` asks for. */
    val links: ParcelLinkStore = (if (debug) debugLinkPreview() else null)
        ?: ParcelLinkStore(defaults, configuration, api = { id, key -> DeliveryApiClient.publicParcel(id, key, configuration, transport) })

    private val catalogState = MutableStateFlow<CarrierCatalog?>(null)

    /** The app's carrier catalog, installed as `CarrierCatalog.shared` once read; null until then. */
    val catalog: StateFlow<CarrierCatalog?> = catalogState.asStateFlow()

    private var started = false

    /** The app is in front (`scenePhase == .active`); it opens so. */
    private var active = true

    init {
        // Localizer's `saveSharedLanguage()`: the widget reads the app's language from the group.
        groupDefaults.set(localizer.language.rawValue, forKey = AppLanguage.storageKey)
    }

    /**
     * Reads the carrier catalog off the main thread and installs it, then starts what RootView's tasks start: the
     * session's bootstrap, the catalog's refresh, the parcels of each identity, the browser check's preparation and the
     * sign-in emails' language. Once.
     */
    fun start() {
        check(!started) { "The app's stores start once." }
        started = true
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
        scope.launch {
            identity.collectLatest {
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
        // A link opened before signing in comes back once someone is signed in.
        scope.launch {
            session.stateFlow.map { it.signedInUser }.distinctUntilChanged().collect { user -> if (user != null) links.resume() }
        }
        analytics()
    }

    /** AnalyticsLifecycleObserver's view: the screen the session shows, in its mode, and whether a link covers it. */
    private fun analytics() {
        analyticsObserver.appeared()
        scope.launch {
            combine(session.stateFlow, parcels.parcelsFlow.map { it.isEmpty() }.distinctUntilChanged(), links.routeFlow) { state, empty, route ->
                Triple(screen(state, empty), analyticsMode(), route != null)
            }.distinctUntilChanged().collect { (screen, mode, linkOpen) -> analyticsObserver.update(screen, mode, linkOpen) }
        }
    }

    private fun screen(state: AuthenticationState, noParcels: Boolean): String = when (state) {
        AuthenticationState.Loading -> "welcome"
        // Nobody is signed in: the first screen, then the deliveries this phone follows itself.
        AuthenticationState.Welcome -> if (noParcels) "front-door" else "deliveries"
        AuthenticationState.SignedOut, AuthenticationState.Unconfigured -> "sign-in"
        AuthenticationState.Demo, is AuthenticationState.SignedIn -> "deliveries"
    }

    private fun analyticsMode(): DeliveryAnalytics.Mode = when {
        session.isDemo -> DeliveryAnalytics.Mode.Demo
        session.user == null -> DeliveryAnalytics.Mode.Anonymous
        else -> DeliveryAnalytics.Mode.Account
    }

    /** Localizer's `didSet` and RootView's `onChange(of: localizer.language)`. */
    private fun languageChanged(language: AppLanguage) {
        defaults.set(language.rawValue, forKey = AppLanguage.storageKey)
        groupDefaults.set(language.rawValue, forKey = AppLanguage.storageKey)
        analytics.action("language-change")
        parcels.demoLanguageChanged()
        parcels.refreshDeliverySurfaces()
        val token = notifications.currentDeviceToken ?: return
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
     * The window came to the front or left it (`scenePhase`): the parcels poll only while it is in front, and coming
     * back asks for a newer catalog. A change of configuration (a rotation) is no change.
     */
    fun sceneActive(active: Boolean) {
        if (active == this.active) return
        this.active = active
        parcels.setActive(active)
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

        private val AuthenticationState.signedInUser: UUID? get() = (this as? AuthenticationState.SignedIn)?.user?.id
    }
}
