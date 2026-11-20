package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ClaimParcelResultOutcome
import com.plhery.peekdeliverytracker.core.api.ClaimParcelLink
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsRequest
import com.plhery.peekdeliverytracker.core.api.CreatePackageRequest
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.NotificationPreferencesClock
import com.plhery.peekdeliverytracker.core.api.NotificationPreferencesDistance
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelAlertPreset
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.ShareParcelRequest
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.io.File
import java.time.Instant
import java.time.ZoneId
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.pow
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

// From ParcelStore.swift: the parcels on screen, and everything done to them, for an account, the demo or this phone
// alone. The offline cache is in ParcelCache.kt and the demo's parcels in DemoRepository.kt.

/**
 * The phone's notifications as the store reaches them: UNUserNotificationCenter, UIApplication's registration and
 * AppDelegate's token on the iPhone. Called on the main thread; `currentDeviceToken` is set on the main thread too
 * (Firebase's callbacks come on others), and `authorizationStatus` never throws.
 *
 * On Android, the permission is POST_NOTIFICATIONS with NotificationManagerCompat, and the token Firebase Cloud
 * Messaging's, which the service sends through FCM (`/api/push/devices` and the parcels' app alerts say `fcm`).
 */
interface PushNotifications {
    /**
     * Whether this phone can receive pushes at all: not on Android in a build without Firebase Cloud Messaging's
     * settings. Without them nothing offers notifications, asks for the permission or tunes them, and a parcel's alerts
     * are its email.
     */
    val available: Boolean get() = true

    /** `notificationSettings().authorizationStatus`: `NotificationAuthorizationStatus.fromAndroid` reads it. */
    suspend fun authorizationStatus(): NotificationAuthorizationStatus

    /**
     * `requestAuthorization(options: [.alert, .badge, .sound])`: asks once, and says whether notifications may be
     * shown. From Android 13 the POST_NOTIFICATIONS request, from the activity on screen; on Android 12, which asks
     * nothing, whether the app's notifications are on. Throws when it cannot ask, as when no activity is on screen.
     */
    suspend fun requestAuthorization(): Boolean

    /**
     * Whether the phone asked before and was answered. iOS's status leaves `notDetermined` with the first answer, so the
     * question after a parcel comes to this phone without an account is asked once; Android 13 may ask a second time,
     * which `fromAndroid` leaves to the account's invitation and switch, and the guest's question skips it. False where
     * nothing is asked.
     */
    val answeredBefore: Boolean get() = false

    /**
     * `registerForRemoteNotifications()`: asks for this launch's token (`FirebaseMessaging.getInstance().token`) and
     * returns at once. When the token arrives, and on each `FirebaseMessagingService.onNewToken`, the app keeps it as
     * `currentDeviceToken` and then, on the main thread, calls `forwardNativePushToken` and `registerDeviceAlerts`
     * with its language, in that order, as the iPhone's app does with each APNs token. It calls both again when the
     * app's language changes.
     */
    fun registerForRemoteNotifications()

    /** `unregisterForRemoteNotifications()`: this phone hears no more pushes (`FirebaseMessaging.deleteToken()`). */
    fun unregisterForRemoteNotifications()

    /** AppDelegate's `currentDeviceToken`: the token this launch was given, kept in memory alone; nil until then. */
    val currentDeviceToken: String?

    /**
     * AppDelegate's `deviceTokenFailures`: how many times this launch Firebase said it could give no token (no Google
     * Play services, no network). A wait for a token ends at the next one.
     */
    val deviceTokenFailures: Int get() = 0

    /** AppDelegate's `clearDeviceToken()`: forgets `currentDeviceToken`. */
    fun clearDeviceToken()
}

/**
 * WidgetKit's `WidgetCenter`: told that what the widget shows changed (DeliveryWidgetSharedStore holds it), so it
 * draws again. On Android an update of the app's Glance widgets.
 */
fun interface WidgetCenter {
    fun reloadTimelines(ofKind: String)
}

/**
 * Where an account's export is written for the share sheet: the iPhone's temporary folder. On Android a file in the
 * app's cache (`cacheDir/exports/<name>`, replacing one of the same name) that a FileProvider hands to the share
 * intent, written whole beside it, synced and renamed over it, as Swift's `.atomic` write: a half-written file is
 * never shared. Throws when it cannot be written.
 */
fun interface ExportFiles {
    suspend fun write(name: String, data: ByteArray): File
}

/**
 * The Live Activities of the parcels out for delivery, which the iPhone keeps on its Lock Screen. Android has none
 * (`None`): the store keeps their setting and withdraws this phone's registration for them, so the service starts
 * none, as the iPhone does while they are switched off.
 */
interface DeliveryLiveActivities {
    /** `ActivityAuthorizationInfo().areActivitiesEnabled`. */
    val areActivitiesEnabled: Boolean

    /** Ends at once every one still showing. */
    suspend fun endAll()

    object None : DeliveryLiveActivities {
        override val areActivitiesEnabled: Boolean = false

        override suspend fun endAll() {}
    }
}

/**
 * How the store reaches the phone for the notifications of the parcels it follows itself: the permission, the
 * question for it, the registration and the token. Tests answer for the phone.
 */
class DeviceNotificationAccess(
    val status: suspend () -> NotificationAuthorizationStatus,
    val request: suspend () -> Unit,
    val register: () -> Unit,
    val token: () -> String?,
) {
    companion object {
        /**
         * `DeviceNotificationAccess.system`: the phone's own, whose question never fails the caller and is asked only
         * while it never was answered ([PushNotifications.answeredBefore]).
         */
        fun system(notifications: PushNotifications): DeviceNotificationAccess = DeviceNotificationAccess(
            status = { notifications.authorizationStatus() },
            request = { if (!notifications.answeredBefore) tryQuietly { notifications.requestAuthorization() } },
            register = { notifications.registerForRemoteNotifications() },
            token = { notifications.currentDeviceToken },
        )
    }
}

/**
 * The parcels on screen: an account's, the demo's, or those this phone follows without an account. Each change is
 * shown at once and kept for an offline launch; work begun for one account never lands in the next one's.
 *
 * Used from the main thread, as the iPhone's store is `@MainActor`; each `…Flow` publishes its property as it
 * changes. What the iPhone leaves to a `Task` runs in `scope`, the app's own on the main thread, which nothing cancels
 * while the app runs (the one `MainScope()` the application keeps and gives every store). Its dispatcher must not be
 * `Main.immediate`: a Swift `Task` starts once the code that made it has returned, never inside it. The store lives as
 * long as the process, as the iPhone's does: it never stops following the session's identity.
 *
 * `parcelsFlow` publishes a list once it is shown, and an equal list not at all, where the iPhone's `$parcels` sends the
 * next list before it is shown, on every assignment. A collector that compares what changed (the delivered postmark,
 * the open parcel kept on screen: ParcelListView's `onReceive(store.$parcels)`) keeps the last list it received and
 * compares the new one with that, never with `parcels`, which already holds the new one.
 *
 * The app makes one, as the iPhone's app makes its ParcelStore, from the session, the account's client, the device's
 * parcels and the Live Activity revocations it gives everything else. `defaults` is the standard UserDefaults
 * stand-in, which also keeps the demo's parcels; `deliveryWidgetStore` the preferences the widget reads;
 * `cacheFiles` the offline lists. `region` is the device's region, whose country an added parcel's lookup is hinted
 * with (`Resources.getSystem().configuration.locales[0]`, never `Locale.getDefault()`, which follows the app's
 * language); `analytics` counts the catalog's actions (`DeliveryAnalytics.shared.action`); `importCharges` is the
 * debug build's import charges setting for the demo (`ImportChargesPreview`), nil in a release build. `widgetPictures`
 * draws the widget's cards, as the iPhone's store does into its App Group; without it (the iPhone without an App Group,
 * and the tests) the widget shows words alone.
 */
class ParcelStore(
    val configuration: AppConfiguration,
    // Background delivery cleanup can outlive the screen that supplied the session.
    private val session: SessionStore,
    private val localizer: Localizer,
    private val api: DeliveryApiClient,
    private val device: DeviceParcels,
    private val liveActivityRevocations: LiveActivityRevocations,
    private val defaults: UserDefaults,
    private val deliveryWidgetStore: DeliveryWidgetSharedStore,
    cacheFiles: ParcelCacheFiles,
    private val notifications: PushNotifications,
    private val widgetCenter: WidgetCenter,
    private val exportFiles: ExportFiles,
    private val region: () -> Locale,
    private val scope: CoroutineScope,
    private val deviceNotifications: DeviceNotificationAccess = DeviceNotificationAccess.system(notifications),
    private val liveActivities: DeliveryLiveActivities = DeliveryLiveActivities.None,
    private val widgetPictures: DeliveryWidgetArtist? = null,
    private val analytics: (event: String, outcome: DeliveryAnalytics.Outcome?) -> Unit = { _, _ -> },
    private val now: () -> Instant = AppClock::now,
    importCharges: () -> String? = { null },
) {
    sealed class RefreshStart {
        data object Queued : RefreshStart()

        data class Completed(val result: RefreshResult) : RefreshStart()

        data object AlreadyRunning : RefreshStart()
    }

    /** How a check ended; a failure's message is shown as is. */
    sealed class RefreshResult {
        data object Updated : RefreshResult()

        data object Unchanged : RefreshResult()

        data object StillChecking : RefreshResult()

        data class Failed(val message: String) : RefreshResult()

        /** Shared with the web app's pull, refresh button and parcel check. */
        val messageKey: String
            get() = when (this) {
                Updated -> "app.refreshComplete"
                Unchanged -> "app.refreshUnchanged"
                StillChecking -> "app.refreshTimeout"
                is Failed -> "detail.checkFailed"
            }
    }

    /** How a background check of every parcel ended. */
    data class RefreshOutcome(val result: RefreshResult, val id: UUID = UUID.randomUUID())

    /** How many of the parcels followed on this phone before signing in stayed here, because the account was full. */
    data class LeftOnDevice(val count: Int, val id: UUID = UUID.randomUUID())

    /**
     * How a read of the account's list ended. `Skipped`: nothing was read or the answer was set aside, because a change
     * was made meanwhile, a newer read began or the account changed.
     */
    private sealed class LoadOutcome {
        data object Loaded : LoadOutcome()

        data class Failed(val message: String) : LoadOutcome()

        data object Skipped : LoadOutcome()
    }

    private val parcelsState = MutableStateFlow<List<Parcel>>(emptyList())
    private val loadingState = MutableStateFlow(false)
    private val refreshingState = MutableStateFlow(false)
    private val refreshOutcomeState = MutableStateFlow<RefreshOutcome?>(null)
    private val errorMessageState = MutableStateFlow<String?>(null)
    private val authenticationRequiredState = MutableStateFlow(false)
    private val usingCachedDataState = MutableStateFlow(false)
    private val notificationEnableInProgressState = MutableStateFlow(false)
    private val notificationStateLoadedState = MutableStateFlow(false)
    private val notificationInvitationDismissedState = MutableStateFlow(false)
    private val notificationStatusState = MutableStateFlow(NotificationAuthorizationStatus.NotDetermined)
    private val notificationsEnabledOnDeviceState = MutableStateFlow(false)
    private val notificationPreferencesState = MutableStateFlow<NotificationPreferences?>(null)
    private val deviceAlertPresetState = MutableStateFlow(
        defaults.string(forKey = NotificationDeviceKeys.ALERT_PRESET)?.let { ParcelAlertPreset.fromRawValue(it) },
    )
    private val notificationErrorState = MutableStateFlow<String?>(null)
    private val nativePushRegistrationFailedState = MutableStateFlow(false)
    private val deliveryLiveActivitiesEnabledState = MutableStateFlow(offersLiveActivities && deliveryWidgetStore.liveActivitiesEnabled)
    private val deliveryLiveActivityErrorState = MutableStateFlow<String?>(null)
    private val undoParcelState = MutableStateFlow<Parcel?>(null)
    private val leftOnDeviceState = MutableStateFlow<LeftOnDevice?>(null)
    private val unlistedDeviceParcelsState = MutableStateFlow(0)
    private val frozenState = MutableStateFlow<Set<UUID>>(emptySet())
    private val serverDownState = MutableStateFlow<MaintenanceNotice?>(null)
    private val listReadAtState = MutableStateFlow<Instant?>(null)

    val parcelsFlow: StateFlow<List<Parcel>> = parcelsState.asStateFlow()
    val loadingFlow: StateFlow<Boolean> = loadingState.asStateFlow()
    val refreshingFlow: StateFlow<Boolean> = refreshingState.asStateFlow()
    val refreshOutcomeFlow: StateFlow<RefreshOutcome?> = refreshOutcomeState.asStateFlow()
    val errorMessageFlow: StateFlow<String?> = errorMessageState.asStateFlow()
    val authenticationRequiredFlow: StateFlow<Boolean> = authenticationRequiredState.asStateFlow()
    val usingCachedDataFlow: StateFlow<Boolean> = usingCachedDataState.asStateFlow()
    val notificationEnableInProgressFlow: StateFlow<Boolean> = notificationEnableInProgressState.asStateFlow()
    val notificationStateLoadedFlow: StateFlow<Boolean> = notificationStateLoadedState.asStateFlow()
    val notificationInvitationDismissedFlow: StateFlow<Boolean> = notificationInvitationDismissedState.asStateFlow()
    val notificationStatusFlow: StateFlow<NotificationAuthorizationStatus> = notificationStatusState.asStateFlow()
    val notificationsEnabledOnDeviceFlow: StateFlow<Boolean> = notificationsEnabledOnDeviceState.asStateFlow()
    val notificationPreferencesFlow: StateFlow<NotificationPreferences?> = notificationPreferencesState.asStateFlow()

    /**
     * The preset chosen on the Delivery updates page without an account, which every parcel this phone follows itself
     * is signed up with; null until one is chosen there, and each parcel keeps the one it was first given.
     */
    val deviceAlertPresetFlow: StateFlow<ParcelAlertPreset?> = deviceAlertPresetState.asStateFlow()
    private val markNewUpdatesChoice = MarkNewUpdatesChoice(defaults)

    /** Whether cards mark their parcel's new updates (#72), "Dot on updated parcels" in Settings. */
    val markNewUpdatesFlow: StateFlow<Boolean> = markNewUpdatesChoice.enabled
    val notificationErrorFlow: StateFlow<String?> = notificationErrorState.asStateFlow()

    /** The last try to register this phone's token, with Firebase or with the server, failed: the invitation offers it again. */
    val nativePushRegistrationFailedFlow: StateFlow<Boolean> = nativePushRegistrationFailedState.asStateFlow()
    val deliveryLiveActivitiesEnabledFlow: StateFlow<Boolean> = deliveryLiveActivitiesEnabledState.asStateFlow()
    val deliveryLiveActivityErrorFlow: StateFlow<String?> = deliveryLiveActivityErrorState.asStateFlow()
    val undoParcelFlow: StateFlow<Parcel?> = undoParcelState.asStateFlow()
    val leftOnDeviceFlow: StateFlow<LeftOnDevice?> = leftOnDeviceState.asStateFlow()

    /**
     * The server is down for maintenance: the list keeps what it shows under a calm notice instead of an error, and asks
     * again by itself until the server answers. Nothing signs out or forgets a parcel for it.
     */
    val serverDownFlow: StateFlow<MaintenanceNotice?> = serverDownState.asStateFlow()

    /**
     * When the list shown was last read from the server: kept on the account's saved list, and a guest's parcels' last
     * answer. Nil when this phone never read it.
     */
    val listReadAtFlow: StateFlow<Instant?> = listReadAtState.asStateFlow()

    /**
     * Signed out, the parcels of this phone's own kept by their key alone, their saved copy lost and not read again yet
     * ([DeviceParcels.unlistedCount]): Settings offers Forget all for them too, and counts them. Taken each time the
     * list is set, the same list again included.
     */
    val unlistedDeviceParcelsFlow: StateFlow<Int> = unlistedDeviceParcelsState.asStateFlow()

    /**
     * Signed out, the parcels whose copy the service forgot ([isFrozen]). A copy freezes without changing its parcel, so
     * the list stays equal and only this tells the screen. Taken each time the list is set, before the list itself.
     */
    val frozenFlow: StateFlow<Set<UUID>> = frozenState.asStateFlow()

    /** `objectWillChange`, for what no flow publishes: the postcodes this phone suggests. */
    val objectWillChange = PassthroughSignal()

    var parcels: List<Parcel>
        get() = parcelsState.value
        private set(value) {
            takeFrozen(value)
            parcelsState.value = value
            takeUnlistedDeviceParcels()
            if (!holdingDeliverySurfaces) publishDeliverySurfaces()
        }

    /** [frozenFlow] for [list]: also when the list stays equal, as a copy freezes without changing its parcel. */
    private fun takeFrozen(list: List<Parcel>) {
        frozenState.value = if (isGuest) list.filter { device.isFrozen(it.id) }.mapTo(HashSet()) { it.id } else emptySet()
    }

    private fun takeUnlistedDeviceParcels() {
        unlistedDeviceParcelsState.value = if (isGuest) device.unlistedCount else 0
    }

    var loading: Boolean
        get() = loadingState.value
        private set(value) {
            loadingState.value = value
        }

    var refreshing: Boolean
        get() = refreshingState.value
        private set(value) {
            refreshingState.value = value
        }

    var refreshOutcome: RefreshOutcome?
        get() = refreshOutcomeState.value
        private set(value) {
            refreshOutcomeState.value = value
        }

    var errorMessage: String?
        get() = errorMessageState.value
        private set(value) {
            errorMessageState.value = value
        }

    var authenticationRequired: Boolean
        get() = authenticationRequiredState.value
        private set(value) {
            authenticationRequiredState.value = value
        }

    var usingCachedData: Boolean
        get() = usingCachedDataState.value
        private set(value) {
            usingCachedDataState.value = value
        }

    var serverDown: MaintenanceNotice?
        get() = serverDownState.value
        private set(value) {
            serverDownState.value = value
        }

    var listReadAt: Instant?
        get() = listReadAtState.value
        private set(value) {
            listReadAtState.value = value
        }

    var notificationEnableInProgress: Boolean
        get() = notificationEnableInProgressState.value
        private set(value) {
            notificationEnableInProgressState.value = value
        }

    var notificationStateLoaded: Boolean
        get() = notificationStateLoadedState.value
        private set(value) {
            notificationStateLoadedState.value = value
        }

    var notificationInvitationDismissed: Boolean
        get() = notificationInvitationDismissedState.value
        private set(value) {
            notificationInvitationDismissedState.value = value
        }

    var notificationStatus: NotificationAuthorizationStatus
        get() = notificationStatusState.value
        private set(value) {
            notificationStatusState.value = value
        }

    var notificationsEnabledOnDevice: Boolean
        get() = notificationsEnabledOnDeviceState.value
        private set(value) {
            notificationsEnabledOnDeviceState.value = value
        }

    var notificationPreferences: NotificationPreferences?
        get() = notificationPreferencesState.value
        private set(value) {
            notificationPreferencesState.value = value
            // An account's choice about the list's mark follows it here from its other devices (#72).
            value?.markNewUpdates?.let(::followMarkNewUpdates)
            saveShownUnits()
        }

    var notificationError: String?
        get() = notificationErrorState.value
        private set(value) {
            notificationErrorState.value = value
        }

    private var nativePushRegistrationFailed: Boolean
        get() = nativePushRegistrationFailedState.value
        set(value) {
            nativePushRegistrationFailedState.value = value
        }

    // Set through its state alone: `setDeliveryLiveActivitiesEnabled` is the switch's own function.
    val deliveryLiveActivitiesEnabled: Boolean
        get() = deliveryLiveActivitiesEnabledState.value

    var deliveryLiveActivityError: String?
        get() = deliveryLiveActivityErrorState.value
        private set(value) {
            deliveryLiveActivityErrorState.value = value
        }

    var undoParcel: Parcel?
        get() = undoParcelState.value
        set(value) {
            undoParcelState.value = value
        }

    /** News for the list to say once, and then set back to null: the account had no room for some of this phone's parcels. */
    var leftOnDevice: LeftOnDevice?
        get() = leftOnDeviceState.value
        set(value) {
            leftOnDeviceState.value = value
        }

    private val demo = DemoRepository(defaults, { localizer.language }, now, importCharges)
    private val demoShares = DemoParcelShares(defaults)
    private val shareNotes = ParcelShareNotes(defaults)
    private val cache = ParcelCache(cacheFiles)
    private var pollingTask: Job? = null
    private var jobMonitoringTask: Job? = null
    private var refreshTask: Job? = null

    /** While the server is down: how many quiet retries ran, the next one, and whether `/maintenance.json` was asked. */
    private var downRetries = 0
    private var downRetryTask: Job? = null
    private var askedEdge = false

    /** Optimistic changes in flight; a list loaded meanwhile could undo them on screen. */
    private val pendingMutations = mutableSetOf<UUID>()

    /**
     * A reopened account shows its saved list at once. Until the service confirms it, the widget and Live Activities
     * keep what they last showed.
     */
    private var holdingDeliverySurfaces = false
    private var showingSavedParcels = false
    private var cacheCurrent = false
    private var publishedWidgetState: DeliveryWidgetState? = null

    /** The cards whose pictures could not be drawn, by the name of their pictures, and the drawing under way. */
    private val widgetPicturesFailed = mutableSetOf<String>()
    private var widgetPicturesTask: Job? = null
    private var deliveryActivityTask: Job? = null

    // A Set in Swift, whose order is its own each launch; here the order the jobs were queued in.
    private val pendingJobIds = LinkedHashSet<UUID>()

    /** Swift's `isActive`: the app is in front. */
    private var appActive = true
    private val identityObservation: AutoCloseable
    private var cacheOwnerId: UUID? = null
    private var mutationRevision = 0
    private var loadSequence = 0

    /**
     * The `seen_at` the account's parcels were last marked with on this phone (#72), until a read of the list brings
     * one as late: opening a parcel leaves a read in flight to land, with the later of the two.
     */
    private val seenHere = mutableMapOf<UUID, String>()
    private var nativePushGeneration = 0

    /** Counts saved preferences: the answer to a read that began before a save is older than it. */
    private var notificationPreferenceSaves = 0

    /** The saves of the list's mark on their way: an answer meanwhile does not say whether the latest choice was taken. */
    private var markNewUpdatesSaving = 0

    /**
     * The clock and units sent with the account's preferences and not yet kept, so a server that does not keep them is
     * not asked again and again.
     */
    private var unitsSent: String? = null

    // The iPhone's Live Activity generation and pending removal guard the registration of their tokens, which Android,
    // without Live Activities, never makes.
    private var deliveryLiveActivitySystemDisabled = false
    private val installationId: UUID = installationIdentifier(defaults)
    private val notificationOptOutKey = NotificationDeviceKeys.OPT_OUT
    private val nativePushRegisteredKey = "sdt.notificationsNativePushRegistered.v1"
    private val notificationOwnerKey = NotificationDeviceKeys.OWNER
    private val demoNotificationsKey = NotificationDeviceKeys.DEMO

    init {
        NotificationDevicePolicy.repairAccountChangeOptOut(defaults)
        NotificationDevicePolicy.carryDeviceAlertsOff(defaults)
        deliveryWidgetStore.setLiveActivitiesEnabled(deliveryLiveActivitiesEnabled)
        cacheOwnerId = session.user?.id
        listReadAt = if (session.isGuest) device.lastCheckedAt else null
        notificationInvitationDismissed = invitationDismissed()
        identityObservation = session.identityChanges.sink { resetForSessionChange() }
        // The delivery email writes a time and a distance as the app does: those chosen in Settings go to the account.
        scope.launch { combine(localizer.use24HourTimeFlow, localizer.distanceUnitFlow) { _, _ -> }.drop(1).collect { saveShownUnits() } }
    }

    /** Whether the list was this phone's own before the session changed ([showsDeviceList]). */
    private var deviceListShown = showsDeviceList

    private fun resetForSessionChange() {
        val reopening = session.reopeningSavedAccount
        // Between a guest's list and the sign-in over it the list, the widget's and its shortcut's, stay as they were.
        val sameList = deviceListShown && showsDeviceList
        deviceListShown = showsDeviceList
        pollingTask?.cancel()
        pollingTask = null
        jobMonitoringTask?.cancel()
        jobMonitoringTask = null
        refreshTask?.cancel()
        refreshTask = null
        noteUp()
        pendingMutations.clear()
        pendingJobIds.clear()
        seenHere.clear()
        markNewUpdatesSaving = 0
        unitsSent = null
        nativePushGeneration += 1
        stopDeliveryLiveActivityObservers()
        cacheOwnerId?.let { owner ->
            quietly { liveActivityRevocations.queue() }
            liveActivityRevocations.retry(scope)
            cache.delete(owner)
            // The words its links carried leave the device with the account.
            shareNotes.forgetAll()
            // So does what it answered about its parcels.
            ParcelFeedbackMemory(defaults, now).forgetAll()
            // The next sign-in hears again about the parcels its account had no room for.
            LeftOnDeviceMemory.forget(owner, defaults)
            releaseDeviceNotifications(from = owner)
        }
        cacheOwnerId = session.user?.id
        notificationInvitationDismissed = invitationDismissed()
        mutationRevision += 1
        loadSequence += 1
        holdingDeliverySurfaces = reopening
        cacheCurrent = false
        val saved = if (reopening) session.user?.let { cache.load(it.id) } else null
        showingSavedParcels = saved != null
        // Open on content: the saved list, or the demo box, instead of an empty frame.
        parcels = saved ?: if (session.isDemo) demo.list() else if (showsDeviceList) device.list() else emptyList()
        listReadAt = if (saved != null) session.user?.let { cache.readAt(it.id) } else if (session.isGuest) device.lastCheckedAt else null
        undoParcel = null
        leftOnDevice = null
        loading = session.isAuthenticated && parcels.isEmpty()
        refreshing = false
        errorMessage = null
        authenticationRequired = false
        usingCachedData = false
        notificationPreferences = null
        notificationError = null
        notificationEnableInProgress = false
        notificationsEnabledOnDevice = false
        nativePushRegistrationFailed = false
        notificationStateLoaded = false
        notificationStatus = NotificationAuthorizationStatus.NotDetermined
        deliveryLiveActivityError = null
        if (!reopening && !sameList) clearDeliverySurfaces()
    }

    val isDemo: Boolean
        get() = session.isDemo

    /** Nobody is signed in: the parcels are the ones this phone follows itself. */
    val isGuest: Boolean
        get() = session.isGuest

    /**
     * The parcels this phone follows without an account, which the counting id's sighting carries: the list for a
     * guest; signed in, those the account could not keep.
     */
    val followedHere: Int
        get() = if (isGuest) parcels.size else device.count

    /** Whether tracking without an account works at all: a build without a server only has its demo. */
    val tracksWithoutAccount: Boolean
        get() = configuration.mode == AppConfiguration.Mode.Api

    /**
     * The postcodes given before, for Add and Change carrier to suggest: the parcels' own, then the ones this phone
     * remembers from lookups without an account.
     */
    val givenPostcodes: List<GivenPostcode>
        get() = GivenPostcode.candidates(parcels, device.postcodes.entries)

    /** This phone stops suggesting the postcode, for every carrier. */
    fun forgetPostcode(postcode: String) {
        objectWillChange.send()
        device.postcodes.forget(postcode)
    }

    /** `typed`: the number as typed, its dashes and spaces kept, which both services read beside it when it differs. */
    suspend fun detectCarrier(trackingNumber: String, typed: String? = null): CarrierDetectionResponse {
        if (isGuest) return device.detect(trackingNumber, typed)
        return api.detectCarrier(trackingNumber, typed)
    }

    val activeCount: Int
        get() = parcels.count { it.isActive }

    val isSynchronizing: Boolean
        get() = parcels.any { it.syncStatus == SyncStatus.Pending || it.syncStatus == SyncStatus.Syncing }

    fun setDeliveryLiveActivitiesEnabled(enabled: Boolean) {
        analytics("live-activities-change", null)
        if (deliveryLiveActivitiesEnabled == enabled) return
        deliveryLiveActivitiesEnabledState.value = enabled
        deliveryWidgetStore.setLiveActivitiesEnabled(enabled)
        deliveryLiveActivitySystemDisabled = false
        deliveryLiveActivityError = null
        if (enabled) {
            startDeliveryLiveActivityObservers()
        } else {
            quietly { liveActivityRevocations.queue() }
            liveActivityRevocations.retry(scope)
            stopDeliveryLiveActivityObservers()
        }
        scheduleDeliveryLiveActivities()
    }

    /** The widget and Live Activities again, as after a change of language. */
    fun refreshDeliverySurfaces() {
        publishDeliverySurfaces()
    }

    /**
     * The widgets' days are worded for the midnights of the time zone the app published them in: in a new zone, the
     * app words them again and reloads the widgets, which also place their redraws at the new midnights.
     */
    fun timeZoneChanged() {
        publishedWidgetState = null
        if (!holdingDeliverySurfaces) publishDeliverySurfaces()
    }

    fun clearDeliverySurfaces() {
        if (!session.isAuthenticated) {
            quietly { liveActivityRevocations.queue() }
            liveActivityRevocations.retry(scope)
        }
        deliveryWidgetStore.setLanguageCode(localizer.language.rawValue)
        deliveryWidgetStore.clearSnapshot()
        publishedWidgetState = null
        widgetPictures?.let { DeliveryWidgetPictures.sweep(keeping = emptyList(), it.folder) }
        widgetCenter.reloadTimelines(DeliveryWidgetSharedStore.kind)
        scheduleDeliveryLiveActivities(forceEnd = true)
    }

    suspend fun start() {
        quietly { liveActivityRevocations.queue(except = session.user?.id) }
        liveActivityRevocations.retry(scope)
        pollingTask?.cancel()
        jobMonitoringTask?.cancel()
        jobMonitoringTask = null
        pendingJobIds.clear()
        val generation = session.generation
        if (errorMessage != null) errorMessage = null
        if (authenticationRequired) authenticationRequired = false
        if (usingCachedData) usingCachedData = false
        if (!session.isAuthenticated) {
            if (loading) loading = false
            if (!isGuest) {
                if (parcels.isNotEmpty()) parcels = emptyList()
                return
            }
            load()
            if (!stillCurrent(generation)) return
            beginDevicePolling()
            // What the phone allows decides whether the list invites it to notifications; a phone without pushes has none.
            if (notifications.available) refreshNotificationState()
            if (!stillCurrent(generation)) return
            resumeDeviceAlerts()
            return
        }
        if (!isDemo) claimDeviceParcels()
        if (!stillCurrent(generation)) return
        load(showSpinner = true)
        if (!stillCurrent(generation)) return
        refreshNotificationState()
        if (!stillCurrent(generation)) return
        startDeliveryLiveActivityObservers()
        if (!isDemo) {
            loadNotificationPreferences()
            if (!stillCurrent(generation)) return
            beginPolling()
        }
    }

    fun setActive(active: Boolean) {
        val returning = active && !appActive
        appActive = active
        if (active) liveActivityRevocations.retry(scope)
        if (active && session.isAuthenticated) {
            scope.launch {
                refreshNotificationState()
                load(showSpinner = false)
            }
            // The launch loads the preferences itself. A return reads them again: the email can be switched off
            // outside the app, from a link in an email.
            if (returning && !isDemo) scope.launch { refreshNotificationPreferences() }
        } else if (active && isGuest) {
            scope.launch {
                if (notifications.available) refreshNotificationState()
                load()
            }
        }
    }

    suspend fun load(showSpinner: Boolean = false) {
        if (isGuest) {
            loadDeviceParcels()
            return
        }
        loadAccountParcels(showSpinner)
        // What was forgotten on this phone before signing in, while the service couldn't be told, is told now rather
        // than at the sign-out's first refresh.
        if (session.isAuthenticated && !isDemo) tryQuietly { device.tellForgotten() }
    }

    /** Reads the account's list and says how the read ended, so a refresh compares parcels only with a list it read. */
    private suspend fun loadAccountParcels(showSpinner: Boolean): LoadOutcome {
        if (!session.isAuthenticated) return LoadOutcome.Skipped
        val generation = session.generation
        val revision = mutationRevision
        val ownerId = session.user?.id
        loadSequence += 1
        val sequence = loadSequence
        if (showSpinner && parcels.isEmpty() && !loading) loading = true
        try {
            val read = list()
            session.checkGeneration(generation)
            if (revision != mutationRevision || sequence != loadSequence || pendingMutations.isNotEmpty()) return LoadOutcome.Skipped
            holdingDeliverySurfaces = false
            showingSavedParcels = false
            val next = seenAsHere(read)
            val changed = next != parcels
            if (changed) {
                parcels = next
            } else {
                // An unchanged poll re-renders nothing; the widget and Live Activities still update if their wording
                // changed, such as "tomorrow" becoming "today".
                publishDeliverySurfaces()
            }
            if ((changed || !cacheCurrent) && ownerId != null) {
                cache.save(next, ownerId)
                cacheCurrent = true
            }
            val readAt = now()
            ownerId?.let { cache.markRead(it, readAt) }
            listReadAt = readAt
            noteUp()
            if (errorMessage != null) errorMessage = null
            if (authenticationRequired) authenticationRequired = false
            if (usingCachedData) usingCachedData = false
            return LoadOutcome.Loaded
        } catch (error: Exception) {
            if (!stillCurrent(generation) || revision != mutationRevision || sequence != loadSequence ||
                error is CancellationException
            ) {
                return LoadOutcome.Skipped
            }
            val message = localizer.errorMessage(error)
            if (error is ServerDown) {
                noteDown(error)
            } else {
                if (errorMessage != message) errorMessage = message
                val expired = error is DeliveryApiError.AuthenticationExpired
                if (authenticationRequired != expired) authenticationRequired = expired
            }
            val cached = if (parcels.isEmpty() && ownerId != null) cache.load(ownerId) else null
            if (cached != null) {
                parcels = cached
                listReadAt = ownerId?.let { cache.readAt(it) }
                usingCachedData = true
            } else if (showingSavedParcels && !usingCachedData) {
                usingCachedData = true
            }
            return LoadOutcome.Failed(message)
        } finally {
            if (generation == session.generation && sequence == loadSequence && loading) loading = false
        }
    }

    suspend fun add(
        trackingNumber: String,
        label: String,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String? = null,
        phoneDigits: String? = null,
    ): Parcel {
        val generation = session.generation
        val request = CreatePackageRequest(
            trackingNumber = CarrierCatalog.normalize(trackingNumber),
            label = label.trimmingWhitespacesAndNewlines(),
            carrier = carrier,
            trackingUrl = trackingUrl?.trimmingWhitespacesAndNewlines()?.nonEmpty,
            dpdPostcode = dpdPostcode?.trimmingWhitespacesAndNewlines()?.nonEmpty,
            dpdPostcodeCountry = dpdPostcodeCountry,
            phoneDigits = phoneDigits?.trimmingWhitespacesAndNewlines()?.nonEmpty,
            lookupCountryHint = ParcelLookupCountry.hint(region()),
        )
        val parcel: Parcel
        if (isDemo) {
            parcel = demo.add(request)
        } else if (isGuest) {
            parcel = device.add(request)
            session.checkGeneration(generation)
            // Its carrier answers in a moment: ask again soon.
            beginDevicePolling()
            val language = localizer.language
            scope.launch { askForDeviceAlerts(language) }
        } else {
            val response = api.add(request)
            parcel = response.parcel
            session.checkGeneration(generation)
            monitorJobs(response.jobIds)
        }
        session.checkGeneration(generation)
        upsert(parcel)
        return parcel
    }

    /**
     * Keeps a parcel shared through a link in this account, under the name the link suggests. The list is then
     * reloaded, so the parcel is on it before the sheet closes. A link the site handed over with its owner key goes
     * with the key, as a lookup made on this phone would.
     */
    suspend fun keep(link: ParcelLinkRoute): ParcelLinkClaim {
        if (session.user == null) throw DeliveryApiError.AuthenticationExpired()
        val generation = session.generation
        val response = api.claimParcels(ClaimParcelsRequest(listOf(ClaimParcelLink(id = link.id, key = link.key, label = link.name))))
        session.checkGeneration(generation)
        val claim = ParcelLinkClaim(response, link.id) ?: throw DeliveryApiError.InvalidResponse()
        when (claim) {
            is ParcelLinkClaim.Added, is ParcelLinkClaim.Already -> load()
            ParcelLinkClaim.Full, ParcelLinkClaim.Unavailable -> {}
        }
        return claim
    }

    /**
     * Follows the parcel behind a link on this phone, without an account: as its own when the site handed its owner
     * key over, else as its reader. The demo or the sign-in screen gives way to the device's parcels, and
     * notifications are asked for. `Already` when it was here.
     */
    fun follow(link: ParcelLinkRoute, response: PublicParcelResponse): ParcelLinkClaim {
        if (!tracksWithoutAccount || session.user != null) throw DeliveryApiError.ServiceFailed(503)
        if (!isGuest) session.showWelcome()
        // A wrapped gift's name is what is inside.
        val followed = device.follow(response, key = link.key, label = if (response.gift == ParcelLinkGift.Wrapped) null else link.name)
        mutationRevision += 1
        parcels = device.list()
        beginDevicePolling()
        val language = localizer.language
        scope.launch { askForDeviceAlerts(language) }
        return if (followed.added) ParcelLinkClaim.Added(followed.id) else ParcelLinkClaim.Already(followed.id)
    }

    /** The parcel this phone follows itself behind a link, by the link or by the parcel. */
    fun followedHere(response: PublicParcelResponse): UUID? {
        if (!tracksWithoutAccount || session.user != null) return null
        // A copy the service forgot of the same parcel gives way to the link, which `follow` takes it over with.
        return device.parcelId(response.link.id) ?: device.list().firstOrNull { it.id == response.parcel.id && !device.isFrozen(it.id) }?.id
    }

    /** The parcel this phone follows through a link, for a notification about it. */
    fun followedHere(linkId: String): UUID? = device.parcelId(linkId)

    /**
     * The sign-in from the tabs: over the parcels this phone follows on its own, if it follows any, which stay the
     * list, the widget's and its shortcut's until someone signs in ([SessionStore.signInOverDeviceList]).
     */
    fun showSignIn() = session.showSignIn(overDeviceList = isGuest && (parcels.isNotEmpty() || device.unlistedCount > 0))

    /** Shows the parcels this phone follows itself, leaving the demo or the sign-in screen. */
    fun showDeviceParcels() {
        if (session.user != null || isGuest) return
        session.showWelcome()
    }

    /**
     * The link of a parcel followed here through someone else's link: all this phone may share of it, and the sign
     * that its carrier and details are not this phone's to change.
     */
    fun viewerLink(parcel: Parcel): String? = if (isGuest) device.viewerLink(parcel.id) else null

    /**
     * Whether the service forgot this lookup made without an account: the phone shows its last answer, no longer
     * updated, with nothing left to share, refresh or be notified about, and removes it without asking the service.
     */
    fun isFrozen(parcel: Parcel): Boolean = isGuest && device.isFrozen(parcel.id)

    /**
     * Whether a postcode went with the lookup behind a parcel followed without an account, which its link never shows.
     * An account's parcel holds its own postcode instead.
     */
    fun postcodeGiven(parcel: Parcel): Boolean = isGuest && device.postcodeGiven(parcel.id) == true

    // Notifications without an account

    /**
     * Asks for notifications once a parcel comes to this phone without an account: the phone asks for permission the
     * first time, and the device's parcels are signed up once the token is known. Nothing on a phone without pushes, or
     * whose notifications were turned off on the Delivery updates page.
     */
    suspend fun askForDeviceAlerts(language: AppLanguage) {
        if (!notifications.available || !tracksWithoutAccount || isDemo || !device.hasParcelsOnTheirWay) return
        if (deviceAlertsHeld) return
        // Never asked yet, the phone is asked only from the list's invitation (`shouldInviteNotifications`).
        if (!NotificationDevicePolicy.isAuthorized(deviceNotifications.status())) return
        deviceNotifications.register()
        deviceNotifications.token()?.let { registerDeviceAlerts(it, language) }
    }

    /** At launch, with permission given already, the phone hands the token over again and the parcels are signed up on it. Nothing is asked. */
    private suspend fun resumeDeviceAlerts() {
        if (!notifications.available || !tracksWithoutAccount || isDemo || !device.hasParcelsOnTheirWay || deviceAlertsHeld ||
            !NotificationDevicePolicy.isAuthorized(deviceNotifications.status())
        ) {
            return
        }
        deviceNotifications.register()
    }

    /**
     * Signs the device's own parcels up for notifications on this token, in this language, with the preset chosen for
     * every parcel ([deviceAlertPresetFlow]). Signed in, these are the parcels the account could not take over; the
     * account's own registration stays as it is. Nothing is signed up while they are turned off ([deviceAlertsHeld]).
     */
    suspend fun registerDeviceAlerts(token: String, language: AppLanguage): Boolean {
        if (!tracksWithoutAccount || isDemo) return true
        if (deviceAlertsHeld) return true
        val locale = NativePushLocale(language)
        val preset = deviceAlertPresetState.value
        device.registerAlerts(token, locale, preset)
        return device.alertsSettled(token, locale, preset)
    }

    /**
     * Signs the device's parcels up again after a round cut short by an outage or a pause, on the conditions of the
     * first. Turned off on the Delivery updates page without an account, it tells the service again about the links it
     * could not tell then ([DeviceParcels.alertsOff]), signed in since or not.
     */
    private suspend fun retryDeviceAlerts() {
        if (!tracksWithoutAccount || isDemo) return
        if (deviceAlertsOff) {
            device.alertsOff()
            return
        }
        if (deviceAlertsHeld) return
        device.retryAlerts(deviceAlertPresetState.value)
    }

    /** The person turned off, on the Delivery updates page without an account, the notifications of this phone's parcels. */
    val deviceAlertsOff: Boolean
        get() = defaults.bool(forKey = NotificationDeviceKeys.DEVICE_ALERTS_OFF)

    /** No parcel this phone follows itself is signed up: turned off without an account, or by the account signed in. */
    private val deviceAlertsHeld: Boolean
        get() = deviceAlertsOff || (session.user != null && defaults.bool(forKey = notificationOptOutKey))

    /** The "off" that applies: a guest's, for the parcels followed without an account, or the account's. */
    private val notificationsOptedOut: Boolean
        get() = if (isGuest) deviceAlertsOff else defaults.bool(forKey = notificationOptOutKey)

    /**
     * Chooses what every parcel this phone follows itself tells, from the Delivery updates page without an account: the
     * choice is kept on the phone, and each parcel on its way is signed up again with it on the token known, or as the
     * next one comes. Muted parcels and copies the service forgot stay as they are, and nothing is signed up while the
     * page's switch is off.
     */
    suspend fun saveDeviceAlertPreset(preset: ParcelAlertPreset) {
        defaults.set(preset.rawValue, forKey = NotificationDeviceKeys.ALERT_PRESET)
        deviceAlertPresetState.value = preset
        if (!notifications.available) return
        deviceNotifications.token()?.let { registerDeviceAlerts(it, localizer.language) }
    }

    /** How the share sheet reaches this parcel's link: through the service, or the demo's own made-up links, which lead nowhere. */
    fun shareClient(parcel: Parcel): ParcelShareModel.Client {
        val id = parcel.id
        return ParcelShareModel.Client(
            current = {
                if (isDemo) {
                    demoShares.link(id)
                } else if (isGuest) {
                    device.share(id)
                } else {
                    val generation = session.generation
                    val link = api.parcelShare(id)
                    session.checkGeneration(generation)
                    link
                }
            },
            share = { settings ->
                if (isDemo) {
                    demoShares.share(id, showNumber = settings.showNumber, gift = settings.gift, giftWords = settings.giftWords, now = now())
                } else if (isGuest) {
                    device.setShare(id, showNumber = settings.showNumber, gift = settings.gift, giftWords = settings.giftWords)
                        ?: throw DeliveryApiError.InvalidResponse()
                } else {
                    val generation = session.generation
                    val link = api.shareParcel(id, ShareParcelRequest(showNumber = settings.showNumber, gift = settings.gift, giftWords = settings.giftWords))
                    session.checkGeneration(generation)
                    link
                }
            },
            stop = {
                if (isDemo) {
                    demoShares.stop(id)
                } else if (isGuest) {
                    device.setShare(id, showNumber = false, gift = false, shared = false)
                } else {
                    val generation = session.generation
                    api.stopSharingParcel(id)
                    session.checkGeneration(generation)
                }
            },
        )
    }

    /**
     * How a parcel's page sends what its reader says of it. The demo's pages ask nothing; an answer for one of its
     * parcels would go nowhere.
     */
    fun feedbackClient(parcel: Parcel): ParcelFeedbackClient {
        val id = parcel.id
        return ParcelFeedbackClient { feedback ->
            if (isDemo) return@ParcelFeedbackClient
            if (isGuest) {
                device.sendFeedback(feedback, id)
                return@ParcelFeedbackClient
            }
            val generation = session.generation
            api.sendFeedback(id, feedback)
            session.checkGeneration(generation)
        }
    }

    /** The same for a parcel opened through a link, which is read with or without an account. */
    fun feedbackClient(linkId: String): ParcelFeedbackClient = ParcelFeedbackClient { feedback ->
        // A build without a server reads no link: what it shows is made up.
        if (!tracksWithoutAccount) return@ParcelFeedbackClient
        device.sendFeedback(feedback, linkId)
    }

    suspend fun rename(parcel: Parcel, label: String) {
        val generation = session.generation
        val cleaned = label.trimmingWhitespacesAndNewlines()
        val updated = if (isDemo) {
            demo.rename(parcel.id, cleaned)
        } else if (isGuest) {
            device.rename(parcel.id, cleaned)
        } else {
            api.rename(parcel.id, cleaned)
        }
        session.checkGeneration(generation)
        upsert(updated)
    }

    suspend fun changeCarrier(
        parcel: Parcel,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String?,
        providerPostcode: String? = null,
        phoneDigits: String? = null,
    ) {
        val generation = session.generation
        val cleanedUrl = trackingUrl?.trimmingWhitespacesAndNewlines()?.nonEmpty
        val cleanedPostcode = dpdPostcode?.trimmingWhitespacesAndNewlines()?.nonEmpty
        // None sent removes the ones the parcel had.
        val cleanedDigits = phoneDigits?.trimmingWhitespacesAndNewlines()?.nonEmpty
        val updated: Parcel
        if (isDemo) {
            updated = demo.changeCarrier(parcel.id, carrier, cleanedUrl, cleanedPostcode, dpdPostcodeCountry, cleanedDigits)
        } else if (isGuest) {
            // The parcel's link moves to the new carrier with its notifications, or a new lookup takes its place.
            device.changeCarrier(parcel.id, carrier, cleanedUrl, cleanedPostcode, dpdPostcodeCountry, cleanedDigits)
            session.checkGeneration(generation)
            mutationRevision += 1
            parcels = device.list()
            beginDevicePolling()
            // A new lookup's link is signed up for notifications at once, as an added one is; a moved link has them.
            val language = localizer.language
            scope.launch { askForDeviceAlerts(language) }
            return
        } else {
            val response = api.changeCarrier(parcel.id, carrier, cleanedUrl, cleanedPostcode, dpdPostcodeCountry, providerPostcode, cleanedDigits)
            updated = response.parcel
            session.checkGeneration(generation)
            monitorJobs(response.jobIds)
        }
        session.checkGeneration(generation)
        upsert(updated)
    }

    suspend fun setMuted(parcel: Parcel, muted: Boolean) {
        val generation = session.generation
        // A parcel followed without an account hears its notifications on this phone alone, which turns them off itself.
        if (isGuest) {
            val updated = device.setMuted(parcel.id, muted)
            session.checkGeneration(generation)
            upsert(updated)
            if (!muted) scope.launch { device.alertsAgain(deviceAlertPresetState.value) }
            return
        }
        val updated = if (isDemo) demo.setMuted(parcel.id, muted) else api.setMuted(parcel.id, muted)
        session.checkGeneration(generation)
        upsert(updated)
    }

    /** Switches the delivery email off, or back on, for this parcel only. */
    suspend fun setEmailMuted(parcel: Parcel, muted: Boolean) {
        val generation = session.generation
        val updated = if (isDemo) demo.setEmailMuted(parcel.id, muted) else api.setEmailMuted(parcel.id, muted)
        session.checkGeneration(generation)
        upsert(updated)
    }

    suspend fun archive(parcel: Parcel) {
        val generation = session.generation
        val updated = parcel.copy(archivedAt = DateParser.isoString(now()))
        // A swiped card leaves at once; it returns only if the service refuses.
        val mutation = UUID.randomUUID()
        pendingMutations += mutation
        upsert(updated)
        try {
            if (isDemo) {
                demo.archive(parcel.id)
            } else if (isGuest) {
                device.setArchived(parcel.id, true)
            } else {
                api.archive(parcel.id)
            }
            session.checkGeneration(generation)
        } catch (error: Exception) {
            if (!pendingMutations.remove(mutation)) throw error
            if (parcels.firstOrNull { it.id == parcel.id } == updated) upsert(parcel)
            throw error
        }
        if (!pendingMutations.remove(mutation)) return
        // Lists requested while the change was in flight may predate it.
        mutationRevision += 1
        undoParcel = parcel
    }

    suspend fun restore(parcel: Parcel) {
        val generation = session.generation
        val restored = if (isDemo) {
            demo.restore(parcel.id)
        } else if (isGuest) {
            device.setArchived(parcel.id, false)
        } else {
            api.restore(parcel.id)
        }
        session.checkGeneration(generation)
        upsert(restored)
        if (undoParcel?.id == parcel.id) undoParcel = null
    }

    suspend fun permanentlyDelete(parcel: Parcel) {
        val generation = session.generation
        var answered = false
        if (isDemo) {
            demo.permanentlyDelete(parcel.id)
            demoShares.stop(parcel.id)
        } else if (isGuest) {
            answered = device.forget(parcel.id)
        } else {
            api.permanentlyDelete(parcel.id)
        }
        session.checkGeneration(generation)
        // A guest's list may hold nothing more to read: the service forgetting the parcel says it is up.
        if (answered) noteUp()
        // What its link carried goes with the parcel.
        shareNotes.forget(parcel.id)
        mutationRevision += 1
        parcels = parcels.filter { it.id != parcel.id }
        persistCache()
        if (undoParcel?.id == parcel.id) undoParcel = null
    }

    /**
     * Asks the service to check every parcel and returns once the check is queued, so pull-to-refresh ends quickly.
     * `refreshing` stays on until the results are loaded, and `refreshOutcome` then reports how the check ended. Signed
     * out, the phone checks its parcels before this returns, and reports how that ended too, to a pull made meanwhile.
     */
    suspend fun refreshAll(): RefreshStart {
        val generation = session.generation
        if (refreshing) return RefreshStart.AlreadyRunning
        refreshing = true
        // Compared with the list as the check began: a push may reload it first.
        val before = parcels
        if (isDemo) {
            parcels = demo.refreshAll()
            refreshing = false
            return RefreshStart.Completed(if (Parcel.trackingChanged(before, parcels)) RefreshResult.Updated else RefreshResult.Unchanged)
        }
        if (isGuest) {
            var trouble: Exception? = null
            var contacted = false
            try {
                contacted = device.refresh(all = true)
            } catch (error: Exception) {
                trouble = error
            }
            if (generation != session.generation) throw CancellationException("The signed-in account changed.")
            parcels = device.list()
            listReadAt = device.lastCheckedAt
            if (trouble is ServerDown) noteDown(trouble) else if (trouble == null && contacted) noteUp()
            // The caller's own cancellation goes on, once the list is shown as it is.
            val givenUp = trouble is CancellationException && !currentCoroutineContext().isActive
            val result = when {
                trouble != null -> RefreshResult.Failed(localizer.errorMessage(trouble))
                Parcel.trackingChanged(before, parcels) -> RefreshResult.Updated
                else -> RefreshResult.Unchanged
            }
            // A pull made while this check ran (`AlreadyRunning`) waits for it and shows how it ended.
            if (!givenUp) refreshOutcome = RefreshOutcome(result)
            refreshing = false
            if (givenUp) currentCoroutineContext().ensureActive()
            return RefreshStart.Completed(result)
        }
        val jobIds = try {
            api.queueRefreshAll().also { session.checkGeneration(generation) }
        } catch (error: Exception) {
            if (generation == session.generation) {
                refreshing = false
                if (error is ServerDown) noteDown(error)
            }
            throw error
        }
        refreshTask = scope.launch { finishRefresh(jobIds, before, generation) }
        return RefreshStart.Queued
    }

    private suspend fun finishRefresh(jobIds: List<UUID>, before: List<Parcel>, generation: UUID) {
        val result: RefreshResult = try {
            api.waitForJobs(jobIds)
            session.checkGeneration(generation)
            when (val read = loadAccountParcels(showSpinner = false)) {
                LoadOutcome.Loaded -> if (Parcel.trackingChanged(before, parcels)) RefreshResult.Updated else RefreshResult.Unchanged
                // The checks are done but the list could not be read: they are not "no new updates".
                is LoadOutcome.Failed -> RefreshResult.Failed(read.message)
                // A newer read, or the next one after a change made meanwhile, shows what the checks found.
                LoadOutcome.Skipped -> RefreshResult.StillChecking
            }
        } catch (_: DeliveryApiError.RefreshTimeout) {
            // The checks keep running; their results arrive with a later load.
            RefreshResult.StillChecking
        } catch (error: Exception) {
            if (error is CancellationException) return
            if (error is ServerDown && generation == session.generation) noteDown(error)
            RefreshResult.Failed(localizer.errorMessage(error))
        }
        if (!stillCurrent(generation)) return
        refreshing = false
        refreshTask = null
        refreshOutcome = RefreshOutcome(result)
    }

    suspend fun refresh(parcel: Parcel) {
        val generation = session.generation
        if (isDemo) {
            session.checkGeneration(generation)
            upsert(demo.refresh(parcel.id))
        } else if (isGuest) {
            device.refresh(only = parcel.id, check = true)
            session.checkGeneration(generation)
            parcels = device.list()
            // The check it asked for is read as it lands, soon and a few times, as a parcel's first check is.
            if (device.isWaiting) beginDevicePolling()
        } else {
            api.refresh(parcel.id)
            session.checkGeneration(generation)
            load(showSpinner = false)
        }
    }

    suspend fun exportAccount(): File {
        val data = if (isDemo || isGuest) {
            exportEncoder.encode(
                DemoExport.serializer(),
                DemoExport(exportedAt = DateParser.isoString(now()), mode = if (isDemo) "demo" else "device", packages = parcels),
            )
        } else {
            api.exportAccount()
        }
        val day = ParcelOrganizer.dayKey(now())
        return exportFiles.write("peek-export-$day.json", data)
    }

    /** The demo writes its sample parcels in the app's language. */
    fun demoLanguageChanged() {
        if (!isDemo) return
        parcels = demo.list()
    }

    suspend fun resetDemoData() {
        analytics("demo-reset", null)
        if (!isDemo) return
        demo.reset()
        demoShares.reset()
        shareNotes.forgetAll()
        undoParcel = null
        endAllDeliveryLiveActivities()
        parcels = demo.list()
    }

    /** Forgets every parcel followed on this phone without an account, here and on the service. */
    suspend fun forgetDeviceParcels() {
        if (!isGuest) return
        val generation = session.generation
        try {
            val answered = device.forgetAll()
            undoParcel = null
            if (answered && generation == session.generation && isGuest) noteUp()
        } finally {
            if (generation == session.generation && isGuest) {
                mutationRevision += 1
                parcels = device.list()
            }
        }
    }

    suspend fun deleteAccount(confirmation: String) {
        val generation = session.generation
        if (isDemo) {
            resetDemoData()
            return
        }
        val appleAuthorizationCode = session.reauthenticateWithAppleBeforeDeletion()
        session.checkGeneration(generation)
        nativePushGeneration += 1
        stopDeliveryLiveActivityObservers()
        jobMonitoringTask?.cancel()
        jobMonitoringTask = null
        pendingJobIds.clear()
        api.deleteAccount(confirmation, appleAuthorizationCode)
        session.checkGeneration(generation)
        endAllDeliveryLiveActivities()
        session.checkGeneration(generation)
        releaseDeviceNotifications(from = session.user?.id)
        notificationsEnabledOnDevice = false
        notificationPreferences = null
        notificationError = null
        session.user?.id?.let { cache.delete(it) }
        session.forceSignOut()
        parcels = emptyList()
    }

    suspend fun signOut() {
        // Save cleanup intent before discarding the account session.
        liveActivityRevocations.queue()
        liveActivityRevocations.retry(scope)
        val generation = session.generation
        nativePushGeneration += 1
        jobMonitoringTask?.cancel()
        jobMonitoringTask = null
        pendingJobIds.clear()
        val token = notifications.currentDeviceToken
        if (token != null && !isDemo) tryQuietly { api.unregisterNativePushToken(token) }
        session.checkGeneration(generation)
        if (!isDemo) liveActivityRevocations.drain()
        stopDeliveryLiveActivityObservers()
        session.checkGeneration(generation)
        endAllDeliveryLiveActivities()
        session.checkGeneration(generation)
        releaseDeviceNotifications(from = session.user?.id)
        notificationsEnabledOnDevice = false
        notificationPreferences = null
        notificationError = null
        session.user?.id?.let { cache.delete(it) }
        session.signOut()
    }

    /**
     * An account leaves the device: its token goes with it, while the person's own on or off for this device stays.
     * The device keeps whose notifications they were, so that account gets them back when it signs in again and
     * another account is asked first.
     */
    private fun releaseDeviceNotifications(from: UUID?) {
        notifications.unregisterForRemoteNotifications()
        notifications.clearDeviceToken()
        if (from != null && defaults.bool(forKey = nativePushRegisteredKey) && defaults.string(forKey = notificationOwnerKey) == null) {
            defaults.set(from.uuidString, forKey = notificationOwnerKey)
        }
        defaults.set(false, forKey = nativePushRegisteredKey)
    }

    private val notificationOwner: UUID?
        get() = defaults.string(forKey = notificationOwnerKey)?.let(::uuid)

    suspend fun refreshNotificationState() {
        val generation = session.generation
        val status = notifications.authorizationStatus()
        if (!stillCurrent(generation)) return
        // Every foreground checks this; publish only what changed.
        if (notificationStatus != status) notificationStatus = status
        val optedOut = notificationsOptedOut
        // Without an account nothing registers the phone itself: its token signs each parcel up as it comes.
        val enabled = if (isGuest) {
            NotificationDevicePolicy.isEnabledWithoutAccount(notificationStatus, optedOut)
        } else {
            NotificationDevicePolicy.isEnabled(
                isDemo = isDemo,
                status = notificationStatus,
                optedOut = optedOut,
                nativePushRegistered = defaults.bool(forKey = nativePushRegisteredKey),
                demoNotificationsEnabled = defaults.bool(forKey = demoNotificationsKey),
            )
        }
        if (notificationsEnabledOnDevice != enabled) notificationsEnabledOnDevice = enabled
        if (!notificationStateLoaded) notificationStateLoaded = true
        // Allowed again in Android's settings, a guest's parcels are signed up as the token comes back, with nothing to tap.
        if (NotificationDevicePolicy.shouldRegisterForRemoteNotifications(notificationStatus, optedOut)) {
            notifications.registerForRemoteNotifications()
        }
    }

    suspend fun enableNotifications(language: AppLanguage): Boolean {
        analytics("notifications-enable", DeliveryAnalytics.Outcome.Started)
        var succeeded = false
        try {
            val generation = session.generation
            notificationEnableInProgress = true
            try {
                val granted = notifications.requestAuthorization()
                session.checkGeneration(generation)
                refreshNotificationState()
                session.checkGeneration(generation)
                if (!granted) {
                    dismissNotificationInvitation()
                    throw DeliveryApiError.NotificationsDenied()
                }
                if (isGuest) {
                    // No account to register: the phone's token signs each parcel on its way up, as it comes. A switch
                    // turned off before is on again, an account's own "off" as it was; a parcel muted on its page stays so.
                    defaults.removeObject(forKey = NotificationDeviceKeys.DEVICE_ALERTS_OFF)
                    notificationsEnabledOnDevice = true
                    dismissNotificationInvitation()
                    deviceNotifications.register()
                    deviceNotifications.token()?.let { registerDeviceAlerts(it, language) }
                    succeeded = true
                    return false
                }
                defaults.set(false, forKey = notificationOptOutKey)
                val failures = notifications.deviceTokenFailures
                notifications.registerForRemoteNotifications()
                if (isDemo) {
                    defaults.set(true, forKey = demoNotificationsKey)
                    notificationsEnabledOnDevice = true
                    succeeded = true
                    return true
                }
                val token = waitForApnsToken(failures) ?: throw DeliveryApiError.PushTokenUnavailable()
                session.checkGeneration(generation)
                val sent = api.registerNativePushToken(token, installationId, language, sendTest = true)
                session.checkGeneration(generation)
                defaults.set(true, forKey = nativePushRegisteredKey)
                session.user?.id?.let { defaults.set(it.uuidString, forKey = notificationOwnerKey) }
                notificationsEnabledOnDevice = true
                nativePushRegistrationFailed = false
                dismissNotificationInvitation()
                succeeded = true
                return sent
            } finally {
                // As the iPhone's `checkGeneration`: a cancelled call leaves it on too.
                if (generation == session.generation && currentCoroutineContext().isActive) notificationEnableInProgress = false
            }
        } finally {
            analytics("notifications-enable", if (succeeded) DeliveryAnalytics.Outcome.Success else DeliveryAnalytics.Outcome.Error)
        }
    }

    suspend fun disableNotifications() {
        analytics("notifications-disable", DeliveryAnalytics.Outcome.Started)
        dismissNotificationInvitation()
        if (isGuest) {
            // No account to tell: each link signed up here hears nothing more on this phone, the choice is kept so that
            // no round, launch or invitation signs one up again until the switch is on, and the phone lets its token go.
            // An account signed in later keeps its own choice.
            defaults.set(true, forKey = NotificationDeviceKeys.DEVICE_ALERTS_OFF)
            notificationsEnabledOnDevice = false
            device.alertsOff()
            notifications.unregisterForRemoteNotifications()
            notifications.clearDeviceToken()
            refreshNotificationState()
            analytics("notifications-disable", DeliveryAnalytics.Outcome.Success)
            return
        }
        nativePushGeneration += 1
        val token = notifications.currentDeviceToken
        if (token != null && !isDemo) api.unregisterNativePushToken(token)
        notifications.unregisterForRemoteNotifications()
        notifications.clearDeviceToken()
        defaults.set(true, forKey = notificationOptOutKey)
        defaults.set(false, forKey = nativePushRegisteredKey)
        defaults.set(false, forKey = demoNotificationsKey)
        notificationsEnabledOnDevice = false
        refreshNotificationState()
        analytics("notifications-disable", DeliveryAnalytics.Outcome.Success)
    }

    /**
     * Whether the list invites the account to turn notifications on; never on a phone without pushes. Without an
     * account, it invites the phone while a parcel is on its way and it was never asked, in place of asking at once.
     */
    val shouldInviteNotifications: Boolean
        get() {
            if (!notifications.available || loading || errorMessage != null || authenticationRequired) return false
            if (isGuest) {
                return notificationStateLoaded && notificationStatus == NotificationAuthorizationStatus.NotDetermined &&
                    !notificationInvitationDismissed && !deviceAlertsOff && device.hasParcelsOnTheirWay
            }
            return NotificationInvitationPolicy.shouldPresent(
                isAuthenticated = session.isAuthenticated,
                isDemo = isDemo,
                hasParcels = parcels.isNotEmpty(),
                status = if (notificationStateLoaded) notificationStatus else null,
                enabled = notificationsEnabledOnDevice,
                optedOut = defaults.bool(forKey = notificationOptOutKey),
                dismissed = notificationInvitationDismissed,
                registering = NotificationDevicePolicy.isRegistering(
                    notificationStatus, session.user?.id, notificationOwner, failed = nativePushRegistrationFailed,
                ),
            )
        }

    fun dismissNotificationInvitation() {
        if (isDemo) return
        val userId = session.user?.id
        if (userId == null && !isGuest) return
        NotificationInvitationPreference.dismiss(userId, defaults)
        notificationInvitationDismissed = true
    }

    /** The account's, or the phone's own without one. */
    private fun invitationDismissed(): Boolean = session.user?.let { NotificationInvitationPreference.isDismissed(it.id, defaults) }
        ?: (session.isGuest && NotificationInvitationPreference.isDismissed(null, defaults))

    suspend fun loadNotificationPreferences() {
        // Alerts belong to an account.
        if (isGuest) return
        val saves = notificationPreferenceSaves
        val generation = session.generation
        try {
            val loaded = if (isDemo) demo.notificationPreferences else api.notificationPreferences()
            // A save was answered meanwhile: its answer is the newer one.
            if (saves != notificationPreferenceSaves) return
            notificationPreferences = loaded
            notificationError = null
        } catch (error: Exception) {
            // Unlike the iPhone, which shows URLError.cancelled, a call cancelled under way ends as a cancellation whatever
            // it threw (OkHttp's IOException).
            currentCoroutineContext().ensureActive()
            // An answer for the account before this one says nothing about this one.
            if (generation != session.generation || error is CancellationException) return
            notificationError = localizer.errorMessage(error)
        }
    }

    /**
     * Saves the events of `value` and clears the retired quiet hours. The delivery email and the display choices save
     * on their own, so what `value` says of them is not sent.
     */
    suspend fun saveNotificationPreferences(value: NotificationPreferences) {
        val saved = if (isDemo) {
            demo.saveNotificationPreferences(
                demo.notificationPreferences.copy(
                    enabledStages = value.enabledStages, quietHoursStart = null, quietHoursEnd = null, timezone = value.timezone,
                ),
            )
        } else {
            api.saveNotificationEvents(value.enabledStages, timezone = value.timezone)
        }
        settleNotificationPreferences(saved)
    }

    private fun settleNotificationPreferences(saved: NotificationPreferences) {
        notificationPreferenceSaves += 1
        notificationPreferences = saved
        notificationError = null
    }

    /** The account's delivery email, when the server can write to the address it signs in with. */
    val deliveryEmail: DeliveryEmail?
        get() = DeliveryEmail(isDemo = isDemo, preferences = notificationPreferences, address = session.user?.email)

    /**
     * Switches the account's delivery email at once and apart from the preset: only the choice and this device's time
     * zone are sent, so events chosen elsewhere stay, and the server's answer becomes the state.
     */
    suspend fun setEmailOnDelivery(enabled: Boolean) {
        // Nothing to switch in the demo, or while the server cannot email the account.
        if (deliveryEmail == null) return
        settleNotificationPreferences(api.saveEmailOnDelivery(enabled, timezone = ZoneId.systemDefault().id))
    }

    /**
     * Switches "Dot on updated parcels" (#72) at once on this phone; what was seen is kept either way. An account's choice is
     * saved with its preferences. A choice the account has not taken, made without one or while the server could not
     * take it, stands on this phone until it is saved, and goes to the account when its preferences are next read.
     */
    suspend fun setMarkNewUpdates(enabled: Boolean) {
        if (markNewUpdatesFlow.value == enabled) return
        keepMarkNewUpdates(enabled)
        if (isDemo) return
        markNewUpdatesChoice.unsaved = true
        saveMarkNewUpdates(enabled)?.join()
    }

    /** The choices made about "Dot on updated parcels", counted: the answer to an earlier one does not undo a later one. */
    private var markNewUpdatesWrite: Job? = null
    private var markNewUpdatesChoices = 0

    /**
     * Saves "Dot on updated parcels" with the account's preferences as stored and this phone's time zone. Taken, the choice
     * follows the account again.
     */
    private fun saveMarkNewUpdates(enabled: Boolean): Job? {
        if (!session.isAuthenticated || isDemo || isGuest) return null
        // A read of the preferences that lands before the answer leaves the choice standing (followMarkNewUpdates), and
        // the answer's count turns away one that lands after it. Counting here too would turn away the launch's read
        // when this save fails.
        markNewUpdatesChoices += 1
        markNewUpdatesSaving += 1
        val choice = markNewUpdatesChoices
        val generation = session.generation
        val previous = markNewUpdatesWrite
        return scope.launch {
            try {
                previous?.join()
                if (generation != session.generation) return@launch
                var saved = api.saveDisplayPreferences(markNewUpdates = enabled)
                if (generation != session.generation) return@launch
                if (choice == markNewUpdatesChoices) {
                    markNewUpdatesChoice.unsaved = false
                } else {
                    // Switched again meanwhile: that choice stands, and its own save is on its way.
                    saved = saved.copy(markNewUpdates = markNewUpdatesFlow.value)
                }
                notificationPreferenceSaves += 1
                notificationPreferences = saved
            } catch (error: Exception) {
                currentCoroutineContext().ensureActive()
            } finally {
                if (generation == session.generation) markNewUpdatesSaving -= 1
            }
        }.also { markNewUpdatesWrite = it }
    }

    /**
     * The account's choice, as read or saved, unless one made on this phone has not been saved: that one stands and
     * goes to the account, or, the account holding it already, follows the account again.
     */
    private fun followMarkNewUpdates(stored: Boolean) {
        if (!markNewUpdatesChoice.unsaved) return keepMarkNewUpdates(stored)
        if (markNewUpdatesSaving > 0) return
        if (stored == markNewUpdatesFlow.value) markNewUpdatesChoice.unsaved = false else saveMarkNewUpdates(markNewUpdatesFlow.value)
    }

    /**
     * The delivery email writes a time and a distance as the app last opened does: the account learns this phone's clock
     * and units whenever its preferences, as read or saved, have others. The rest goes as stored, and the email choice
     * is left out, so the server keeps it.
     */
    private fun saveShownUnits() {
        if (!session.isAuthenticated || isDemo || isGuest) return
        val stored = notificationPreferences ?: return
        val clock = if (localizer.use24HourTime) NotificationPreferencesClock.TwentyFour else NotificationPreferencesClock.Twelve
        val distance = if (localizer.distanceUnit == DistanceUnit.Miles) NotificationPreferencesDistance.Mi else NotificationPreferencesDistance.Km
        val sent = "${clock.rawValue} ${distance.rawValue}"
        if ((stored.clock == clock && stored.distance == distance) || unitsSent == sent) return
        unitsSent = sent
        val generation = session.generation
        scope.launch {
            try {
                val saved = api.saveDisplayPreferences(clock = clock.takeIf { it != stored.clock }, distance = distance.takeIf { it != stored.distance })
                if (generation != session.generation) return@launch
                // Kept: should another device change them, the next difference read here is sent again.
                if (unitsSent == sent && saved.clock == clock && saved.distance == distance) unitsSent = null
                notificationPreferenceSaves += 1
                notificationPreferences = saved
            } catch (error: Exception) {
                currentCoroutineContext().ensureActive()
                if (generation == session.generation && unitsSent == sent) unitsSent = null
            }
        }
    }

    /** "Dot on updated parcels" as this phone or the account chose it: the widgets show their dots as the cards do. */
    private fun keepMarkNewUpdates(enabled: Boolean) {
        if (markNewUpdatesFlow.value == enabled) return
        markNewUpdatesChoice.keep(enabled)
        if (!holdingDeliverySurfaces) publishDeliveryWidget()
    }

    /**
     * The parcel's page is in front (#72): a parcel with news is seen now, whether or not its dot shows, as of the
     * `news_at` the page showed in [parcel], so that a scan stored meanwhile stays new. Its dot goes at once, its
     * `seen_at` then being that `news_at`, and the server's moment comes after; the account's other devices hear of it
     * at their next read. A read of the list in flight lands all the same, so the page shows the scans it brings, seen
     * as late as this phone marked the parcel. A call that fails says nothing: the next read of the list brings what
     * the server holds.
     */
    suspend fun markSeen(parcel: Parcel) {
        val shown = parcels.firstOrNull { it.id == parcel.id } ?: return
        if (!shown.hasNewUpdate) return
        val news = parcel.newsAt ?: return
        if (isGuest) {
            device.markSeen(parcel.id, news)
            mutationRevision += 1
            parcels = device.list()
            return
        }
        if (isDemo) {
            upsert(demo.markSeen(parcel.id))
            return
        }
        seeHere(parcel.id, news)
        val generation = session.generation
        val seenAt = try {
            api.markSeen(parcel.id, news)
        } catch (error: Exception) {
            currentCoroutineContext().ensureActive()
            if (generation == session.generation) seenHere.remove(parcel.id)
            return
        }
        if (generation != session.generation) return
        seeHere(parcel.id, seenAt)
    }

    /**
     * The account's parcel is seen at [seenAt] on this phone, unless it was seen later already. Unlike the list's other
     * changes this one lets a read of the list in flight land, which then keeps it ([seenAsHere]): its newer scans show
     * on the open page.
     */
    private fun seeHere(id: UUID, seenAt: String) {
        val index = parcels.indexOfFirst { it.id == id }
        if (index < 0) return
        val marked = DateParser.date(seenAt) ?: return
        val already = parcels[index].seenAt?.let(DateParser::date)
        if (already != null && already > marked) return
        seenHere[id] = seenAt
        parcels = parcels.toMutableList().also { it[index] = it[index].copy(seenAt = seenAt) }
        persistCache()
    }

    /** The account's list as read, each parcel seen as late as this phone marked it; a mark the read has caught up with goes. */
    private fun seenAsHere(read: List<Parcel>): List<Parcel> {
        if (seenHere.isEmpty()) return read
        seenHere.keys.retainAll(read.map { it.id }.toSet())
        return read.map { parcel ->
            val mark = seenHere[parcel.id] ?: return@map parcel
            val markedAt = DateParser.date(mark)
            val readAt = parcel.seenAt?.let(DateParser::date)
            if (markedAt == null || (readAt != null && readAt >= markedAt)) {
                seenHere.remove(parcel.id)
                parcel
            } else {
                parcel.copy(seenAt = mark)
            }
        }
    }

    /** Reads the preferences again without a word when that fails: what is loaded stays. */
    private suspend fun refreshNotificationPreferences() {
        val saves = notificationPreferenceSaves
        val loaded = try {
            api.notificationPreferences()
        } catch (error: Exception) {
            if (error is CancellationException) currentCoroutineContext().ensureActive()
            return
        }
        if (saves != notificationPreferenceSaves || loaded == notificationPreferences) return
        notificationPreferences = loaded
    }

    suspend fun forwardNativePushToken(token: String, language: AppLanguage): Boolean {
        if (notificationEnableInProgress) return false
        if (isDemo || !session.isAuthenticated || defaults.bool(forKey = notificationOptOutKey)) return true
        val user = session.user?.id ?: return false
        if (!NotificationDevicePolicy.belongs(user, notificationOwner)) return true
        val generation = nativePushGeneration
        val sessionGeneration = session.generation
        try {
            api.registerNativePushToken(token, installationId, language, sendTest = false)
            if (generation != nativePushGeneration || !session.isAuthenticated || defaults.bool(forKey = notificationOptOutKey)) {
                tryQuietly { api.unregisterNativePushToken(token) }
                return false
            }
            defaults.set(true, forKey = nativePushRegisteredKey)
            defaults.set(user.uuidString, forKey = notificationOwnerKey)
            notificationsEnabledOnDevice = session.isAuthenticated
            nativePushRegistrationFailed = false
            notificationError = null
            return true
        } catch (error: Exception) {
            currentCoroutineContext().ensureActive()
            if (sessionGeneration == session.generation && error !is CancellationException) {
                nativePushRegistrationFailed = true
                notificationError = localizer.errorMessage(error)
            }
            return false
        }
    }

    /** AppDelegate's `.didFailToReceiveAPNSToken`: Firebase gave this phone no token, so the invitation offers it again. */
    fun nativePushTokenFailed() {
        nativePushRegistrationFailed = true
    }

    private suspend fun list(): List<Parcel> {
        if (isDemo) return demo.list()
        return api.listPackages()
    }

    private fun upsert(parcel: Parcel) {
        mutationRevision += 1
        val index = parcels.indexOfFirst { it.id == parcel.id }
        parcels = if (index >= 0) parcels.toMutableList().also { it[index] = parcel } else parcels + parcel
        persistCache()
    }

    private fun persistCache() {
        val userId = session.user?.id ?: return
        cache.save(parcels, userId)
    }

    private fun publishDeliverySurfaces() {
        publishDeliveryWidget()
        scheduleDeliveryLiveActivities()
    }

    /** The parcels on their way, the most pressing first, as the widget picks from them. */
    val nextDeliveries: List<DeliveryWidgetParcel>
        get() = nextDeliveries(followed, localizer, now())

    /** The parcels still followed: a guest's copy the service forgot is history, which the widget and its shortcut pass over. */
    private val followed: List<Parcel>
        get() = if (showsDeviceList) parcels.filter { !device.isFrozen(it.id) } else parcels

    /** The list is this phone's own: a guest's, or the one a sign-in shows over until someone signs in. */
    private val showsDeviceList: Boolean
        get() = isGuest || session.signInOverDeviceList

    private fun publishDeliveryWidget() {
        val snapshot = if (session.isAuthenticated || showsDeviceList) {
            DeliveryWidgetSnapshot(
                generatedAt = now(),
                languageCode = localizer.language.rawValue,
                parcels = carded(widgetOrder(followed, localizer, now(), markNewUpdatesFlow.value)),
            )
        } else {
            null
        }
        // Cards still being drawn come with the next publication, so that the widget reloads once for them.
        if (snapshot != null && widgetPicturesTask != null) return

        // Reloads spend the widget's system budget; skip them when nothing it shows changed.
        val state = DeliveryWidgetState(languageCode = localizer.language.rawValue, parcels = snapshot?.parcels)
        if (state == publishedWidgetState) return
        publishedWidgetState = state
        deliveryWidgetStore.setLanguageCode(localizer.language.rawValue)
        if (snapshot != null) {
            deliveryWidgetStore.save(snapshot)
        } else {
            deliveryWidgetStore.clearSnapshot()
        }
        widgetPictures?.let { DeliveryWidgetPictures.sweep(keeping = snapshot?.parcels.orEmpty().mapNotNull { it.card }, it.folder) }
        widgetCenter.reloadTimelines(DeliveryWidgetSharedStore.kind)
    }

    /**
     * Gives each of the first [DeliveryWidgetSelection.limit] parcels its card once the card's pictures are drawn, and
     * starts drawing those that aren't, once each: a card that could not be drawn is not drawn again while the app
     * runs. The widget shows the others as rows.
     */
    private fun carded(shown: List<DeliveryWidgetParcel>): List<DeliveryWidgetParcel> {
        val artist = widgetPictures ?: return shown
        val missing = mutableListOf<Pair<Parcel, DeliveryWidgetCard>>()
        val carded = shown.mapIndexed { index, item ->
            if (index >= DeliveryWidgetSelection.limit) return@mapIndexed item
            val parcel = parcels.firstOrNull { it.id == item.id } ?: return@mapIndexed item
            val card = DeliveryWidgetPictures.card(parcel, localizer, artist.scale)
            if (!DeliveryWidgetPictures.isDrawn(card, artist.folder)) {
                if (card.pictures !in widgetPicturesFailed) missing += parcel to card
                return@mapIndexed item
            }
            item.copy(card = card)
        }
        if (missing.isNotEmpty() && widgetPicturesTask == null) {
            val language = localizer.language
            val distanceUnit = localizer.distanceUnit
            widgetPicturesTask = scope.launch {
                for ((parcel, card) in missing) {
                    if (!artist.draw(parcel, card, language, distanceUnit)) widgetPicturesFailed += card.pictures
                }
                widgetPicturesTask = null
                publishDeliveryWidget()
            }
        }
        return carded
    }

    // Live Activities. Android has none to start, update or end: what is left is their setting, the message when the
    // system turns them off, and the withdrawal of this phone's registration while they are off.

    private fun scheduleDeliveryLiveActivities(forceEnd: Boolean = false) {
        deliveryActivityTask?.cancel()
        deliveryActivityTask = scope.launch {
            if (forceEnd) endAllDeliveryLiveActivities() else updateDeliveryLiveActivities()
        }
    }

    private suspend fun updateDeliveryLiveActivities() {
        if (!currentCoroutineContext().isActive) return
        // A Live Activity is kept up to date by pushes, which need an account.
        if (isGuest) return
        if (!deliveryLiveActivitiesEnabled) {
            endAllDeliveryLiveActivities()
            if (!currentCoroutineContext().isActive || deliveryLiveActivitiesEnabled) return
            unregisterDeliveryLiveActivityDevice()
            return
        }
        if (!liveActivities.areActivitiesEnabled) {
            deliveryLiveActivitySystemDisabled = true
            val message = localizer.text("liveActivity.systemDisabled")
            if (deliveryLiveActivityError != message) deliveryLiveActivityError = message
            return
        }
        if (deliveryLiveActivitySystemDisabled) {
            deliveryLiveActivitySystemDisabled = false
            deliveryLiveActivityError = null
        }
    }

    private suspend fun endAllDeliveryLiveActivities() {
        liveActivities.endAll()
    }

    // The iPhone observes its Live Activities and their push tokens here, and registers them with the service.
    private fun startDeliveryLiveActivityObservers() {
        stopDeliveryLiveActivityObservers()
    }

    private fun stopDeliveryLiveActivityObservers() {}

    private suspend fun unregisterDeliveryLiveActivityDevice() {
        if (!currentCoroutineContext().isActive || deliveryLiveActivitiesEnabled) return
        try {
            liveActivityRevocations.queue()
            liveActivityRevocations.drain()
            liveActivityRevocations.retry(scope)
            if (deliveryLiveActivityError != null) deliveryLiveActivityError = null
        } catch (error: Exception) {
            if (error is CancellationException) currentCoroutineContext().ensureActive()
            deliveryLiveActivityError = localizer.errorMessage(error)
        }
    }

    private fun beginPolling() {
        pollingTask?.cancel()
        pollingTask = scope.launch {
            while (true) {
                delay(30.seconds)
                if (appActive) load(showSpinner = false)
            }
        }
    }

    /**
     * Brings the device's own parcels up to date, quietly: trouble leaves the list as it is. A round of notification
     * requests cut short is tried again once its time comes.
     */
    private suspend fun loadDeviceParcels() {
        val generation = session.generation
        var trouble: Exception? = null
        var contacted = false
        try {
            contacted = device.refresh(all = serverDown != null)
        } catch (error: Exception) {
            if (error is CancellationException) currentCoroutineContext().ensureActive()
            trouble = error
        }
        if (generation != session.generation || !isGuest) return
        // A round the service answered nothing says nothing of it; one that failed for it says it is down.
        if (trouble is ServerDown) noteDown(trouble) else if (trouble == null && contacted) noteUp()
        listReadAt = device.lastCheckedAt
        if (pendingMutations.isEmpty()) {
            val next = device.list()
            if (next != parcels) {
                parcels = next
            } else {
                // A key let go, a copy the service forgot, or one read again into a parcel already listed through
                // another link, leaves the list as it was.
                takeFrozen(next)
                takeUnlistedDeviceParcels()
                publishDeliverySurfaces()
            }
        }
        retryDeviceAlerts()
    }

    /**
     * The server is down: no error, and a quiet retry, as soon as it asked for and at least once a minute. The edge is
     * asked once whether the maintenance was announced, and until when.
     */
    private fun noteDown(down: ServerDown) {
        if (errorMessage != null) errorMessage = null
        if (authenticationRequired) authenticationRequired = false
        val notice = down.notice ?: serverDown ?: MaintenanceNotice.Unannounced
        if (serverDown != notice) serverDown = notice
        if (down.notice == null && !askedEdge) {
            askedEdge = true
            val generation = session.generation
            scope.launch {
                val announced = api.maintenance() ?: return@launch
                if (session.generation == generation && serverDown != null) serverDown = announced
            }
        }
        downRetryTask?.cancel()
        val wait = Maintenance.retryDelay(downRetries, down.retryAfter)
        downRetries += 1
        downRetryTask = scope.launch {
            delay(wait.seconds)
            if (appActive) load()
        }
    }

    /** The server answered, or the account changed: the notice goes, and with it the retries. */
    private fun noteUp() {
        downRetryTask?.cancel()
        downRetryTask = null
        downRetries = 0
        askedEdge = false
        if (serverDown != null) serverDown = null
    }

    /**
     * Asks again while the list is on screen: soon and a few times while a parcel waits for its carrier's first
     * answer, then at the pace of the signed-in list.
     */
    private fun beginDevicePolling() {
        pollingTask?.cancel()
        pollingTask = scope.launch {
            var round = 0
            while (true) {
                val soon = device.isWaiting && round < 6
                delay((if (soon) 2 * 1.5.pow(round) else 30.0).seconds)
                if (!isGuest) return@launch
                round = if (soon) round + 1 else 0
                if (appActive) loadDeviceParcels()
            }
        }
    }

    /**
     * The parcels followed on this phone before signing in join the account, under the names they had here, each time
     * the account's list starts: at sign-in, at each launch and as an invitation comes or goes. The device lets go of
     * each one the account now has. One the account has no room for stays here, and the list says how many stayed once
     * a sign-in, and again only when that number changes; a claim cut short says nothing.
     */
    private suspend fun claimDeviceParcels() {
        val user = session.user ?: return
        val claims = device.claims
        val generation = session.generation
        var full = 0
        for (start in claims.indices step 20) {
            val batch = claims.subList(start, minOf(start + 20, claims.size)).toList()
            val response = try {
                api.claimParcels(ClaimParcelsRequest(batch))
            } catch (error: Exception) {
                if (error is CancellationException) currentCoroutineContext().ensureActive()
                return
            }
            if (generation != session.generation) return
            for (result in response.results) {
                if (result.outcome != ClaimParcelResultOutcome.Kept) continue
                // What was archived here stays archived there.
                val id = result.packageId
                if (id != null && device.isArchived(result.id)) tryQuietly { api.archive(id) }
            }
            // A parcel the account has no room for stays on the device, and so does someone else's that it could not keep.
            device.release(
                response.results.filter { result ->
                    when (result.outcome) {
                        ClaimParcelResultOutcome.Kept, ClaimParcelResultOutcome.Already -> true
                        ClaimParcelResultOutcome.Quota -> false
                        ClaimParcelResultOutcome.Unavailable -> !device.isViewer(result.id)
                    }
                }.map { it.id }.toSet(),
            )
            full += response.results.count { it.outcome == ClaimParcelResultOutcome.Quota }
        }
        if (LeftOnDeviceMemory.isNews(full, user.id, defaults)) leftOnDevice = LeftOnDevice(full)
    }

    private fun monitorJobs(jobIds: List<UUID>) {
        pendingJobIds += jobIds
        if (jobMonitoringTask != null || pendingJobIds.isEmpty()) return
        val generation = session.generation
        jobMonitoringTask = scope.launch {
            while (isActive && pendingJobIds.isNotEmpty()) {
                val current = pendingJobIds.toList()
                try {
                    api.waitForJobs(current)
                    pendingJobIds -= current.toSet()
                    load(showSpinner = false)
                } catch (_: Exception) {
                    if (!stillCurrent(generation)) return@launch
                    pendingJobIds -= current.toSet()
                    // A failed check says why on its parcel, read again here: the list's notice is for a list that can't be
                    // read. A lapsed sign-in signs out on its own.
                    load(showSpinner = false)
                }
            }
            // A monitor a sign-out or deletion cancelled ends in stillCurrent, with no error and the next one's handle left alone.
            if (!stillCurrent(generation)) return@launch
            jobMonitoringTask = null
            if (pendingJobIds.isNotEmpty()) monitorJobs(emptyList())
        }
    }

    /**
     * The token Firebase gives, waited for up to 8 seconds; null as soon as Firebase says it can give none after the
     * question asked when [failures] were counted, so the reader is told at once instead of after the whole wait.
     */
    private suspend fun waitForApnsToken(failures: Int): String? {
        notifications.currentDeviceToken?.let { return it }
        repeat(80) {
            delay(100.milliseconds)
            notifications.currentDeviceToken?.let { return it }
            if (notifications.deviceTokenFailures != failures) return null
        }
        return null
    }

    /**
     * Swift's `(try? session.checkGeneration(generation)) != nil`: the identity is still the one the work began for.
     * The caller's own cancellation goes on.
     */
    private suspend fun stillCurrent(generation: UUID): Boolean {
        currentCoroutineContext().ensureActive()
        return session.generation == generation
    }

    companion object {
        /**
         * Live Activities are switched off: the store ends any still showing and withdraws this phone's registration,
         * so the server starts no more. The iPhone's Info.plist has them off with it.
         */
        const val offersLiveActivities = false

        private const val installationIdKey = "sdt.installationID.v1"

        // As JSONEncoder.deliveryTracker writes the iPhone's models.
        private val exportEncoder = JsonEncoder(sortedKeys = true, swiftKeys = true)

        /** `midnights` are those the details are also worded for, as the widgets show them while the app stays closed. */
        fun nextDeliveries(
            parcels: List<Parcel>,
            localizer: Localizer,
            now: Instant = AppClock.now(),
            midnights: List<Instant> = emptyList(),
        ): List<DeliveryWidgetParcel> {
            val ordered = ParcelOrganizer.visible(parcels, query = "", status = ParcelStatusFilter.Active, carrier = null, sort = ParcelSort.Eta, now = now)
            return ordered.map { parcel -> widgetParcel(parcel, localizer, now, midnights) { localizer.parcelDeliveryEstimate(parcel, it) } }
        }

        /** The first [DeliveryWidgetSelection.limit] parcels in the widget's order ([widgetOrder]). */
        fun widgetDeliveries(parcels: List<Parcel>, localizer: Localizer, now: Instant = AppClock.now(), markNewUpdates: Boolean = true): List<DeliveryWidgetParcel> =
            widgetOrder(parcels, localizer, now, markNewUpdates).take(DeliveryWidgetSelection.limit)

        /**
         * The widget's parcels, in its order ([DeliveryWidgetSelection.ordered]): those on their way, then those
         * delivered within the last day, the latest first. Their details are also worded for the next midnights. Each
         * carries the dot its card in the app shows while "Dot on updated parcels" is on ([DeliveryWidgetParcel.newUpdate]).
         */
        fun widgetOrder(parcels: List<Parcel>, localizer: Localizer, now: Instant = AppClock.now(), markNewUpdates: Boolean = true): List<DeliveryWidgetParcel> {
            val zone = ZoneId.systemDefault()
            val midnights = DeliveryWidgetDays.midnights(after = now, zone = zone).toSortedSet()
            for (parcel in parcels) {
                for (value in listOfNotNull(parcel.expectedDelivery, parcel.carrierData?.expectedDeliveryFrom)) {
                    val day = DateParser.deliveryDate(value)?.atZone(zone)?.toLocalDate() ?: continue
                    for (offset in -1L..1L) {
                        val transition = day.plusDays(offset).atStartOfDay(zone).toInstant()
                        if (transition > now) midnights.add(transition)
                    }
                }
            }
            val days = midnights.toList()
            val delivered = ParcelOrganizer
                .visible(parcels, query = "", status = ParcelStatusFilter.Delivered, carrier = null, sort = ParcelSort.Updated, now = now)
                .mapNotNull { parcel ->
                    val arrived = parcel.currentEvent?.displayDate ?: return@mapNotNull null
                    widgetParcel(parcel, localizer, now, days) { localizer.parcelCompletionDate(parcel, it) }.copy(deliveredAt = arrived)
                }
            // The iPhone's nextDeliveries estimates with Date() rather than this `now`; passing it on can only make the two agree.
            val ordered = DeliveryWidgetSelection.ordered(
                onTheWay = nextDeliveries(parcels, localizer, now, days), delivered = delivered, now = now,
            )
            // What the widget shows besides: where the app's list puts each parcel, which is its Next up, the
            // carrier shown, and the last steps of the first parcels. Whichever ends up alone in the list is among
            // them: one on its way comes first, and with none, the one delivered last stays longest and leads. The
            // sections and Next up are also written for the next midnights, as the details are.
            fun sections(at: Instant) = ParcelOrganizer.sections(parcels, at).flatMap { section -> section.parcels.map { it.id to section.kind.rawValue } }.toMap()
            val sections = sections(now)
            val next = ParcelOrganizer.nextDelivery(parcels, now)?.id
            val sectionsLater = days.map(::sections)
            val nextLater = days.map { ParcelOrganizer.nextDelivery(parcels, it)?.id }
            val byId = parcels.associateBy { it.id }
            return ordered.mapIndexed { index, item ->
                val parcel = byId[item.id] ?: return@mapIndexed item
                val section = sections[item.id]
                val nextUp = item.id == next
                val later = DeliveryWidgetDays.later(DeliveryWidgetWording(now, item.detail, section, nextUp), days) { midnight ->
                    val at = days.indexOf(midnight)
                    DeliveryWidgetWording(midnight, item.worded(midnight).detail, sectionsLater[at][item.id], item.id == nextLater[at])
                }
                item.copy(
                    later = later, section = section, nextUp = nextUp, carrierId = parcel.displayedCarrier.rawValue,
                    steps = if (index < DeliveryWidgetSelection.limit) widgetSteps(parcel, localizer).ifEmpty { null } else null,
                    newUpdate = true.takeIf { parcel.marksNewUpdate(markNewUpdates) },
                )
            }
        }

        /**
         * The parcel's last steps, latest first, as its page words them: when, the carrier's words (else its stage's), the
         * place, else its name on the map, and the time.
         */
        private fun widgetSteps(parcel: Parcel, localizer: Localizer): List<DeliveryWidgetStep> = parcel.trackingEvents
            .mapNotNull { event -> DateParser.date(event.occurredAt)?.let { event to it } }
            .sortedByDescending { it.second }
            .take(DeliveryWidgetSelection.steps)
            .map { (event, at) ->
                val day = event.calendarDay
                DeliveryWidgetStep(
                    // A step dated by its day alone is on that day where the reader is, and has no time.
                    at = day ?: at,
                    text = localizer.journalWording(event),
                    place = event.journalLocation?.let { TrackingLocation.spoken(it, event.place, localizer.language) } ?: event.place?.name,
                    clock = if (day == null) localizer.clockTime(at) else "",
                )
            }

        /** `detail` words when the parcel is expected or arrived, as of a moment. */
        private fun widgetParcel(
            parcel: Parcel,
            localizer: Localizer,
            now: Instant,
            midnights: List<Instant>,
            detail: (Instant) -> String?,
        ): DeliveryWidgetParcel {
            val status = localizer.parcelStatus(parcel)
            val first = detail(now) ?: status
            return DeliveryWidgetParcel(
                id = parcel.id,
                label = parcel.label.nonEmpty ?: localizer.text("common.parcel"),
                carrier = CarrierCatalog.shared.info(parcel.displayedCarrier, localizer.language).displayName,
                trackingNumber = CarrierCatalog.format(parcel.trackingNumber, parcel.carrier),
                detail = first,
                isOutForDelivery = parcel.currentStage == TrackingStage.OutForDelivery,
                // As the list's rows mark it.
                stage = DeliveryWidgetStage.of(parcel.currentStage, status, localizer.widgetShortStatus(parcel.displayStatus.key, status)),
                named = parcel.label.nonEmpty != null,
                later = DeliveryWidgetDays.later(than = first, at = midnights) { detail(it) ?: status },
            )
        }

        private fun installationIdentifier(defaults: UserDefaults): UUID {
            defaults.string(forKey = installationIdKey)?.let(::uuid)?.let { return it }
            val value = UUID.randomUUID()
            defaults.set(value.uuidString, forKey = installationIdKey)
            return value
        }
    }
}

/**
 * How many of this phone's parcels an account was last told had stayed here for want of room, so that bringing them
 * along again at a launch, or as an invitation comes or goes, says nothing new. Signing out forgets it.
 */
object LeftOnDeviceMemory {
    private fun key(userId: UUID): String = "sdt.leftOnDevice.v1.${userId.uuidString}"

    /**
     * Whether `count` parcels staying here is news to the account: some stay, and not as many as it was last told.
     * Remembers the count as told; none staying forgets it.
     */
    fun isNews(count: Int, userId: UUID, defaults: UserDefaults): Boolean {
        val key = key(userId)
        val told = defaults.string(forKey = key)?.toIntOrNull() ?: 0
        if (count <= 0) {
            if (told != 0) defaults.removeObject(forKey = key)
            return false
        }
        if (count == told) return false
        defaults.set(count.toString(), forKey = key)
        return true
    }

    fun forget(userId: UUID, defaults: UserDefaults) {
        defaults.removeObject(forKey = key(userId))
    }
}

/** What the widget displays; the snapshot's timestamp alone never needs a reload. */
private data class DeliveryWidgetState(val languageCode: String, val parcels: List<DeliveryWidgetParcel>?)

@Serializable
private class DemoExport(val exportedAt: String, val mode: String, val packages: List<Parcel>)

/**
 * Swift's `try?` (or an empty `catch`) on the caller's path. The caller's own cancellation goes on, as every catch on
 * its path lets it; any other CancellationException is the account changing under the call (`checkGeneration`),
 * which ends here as the Swift ends it.
 */
private suspend inline fun tryQuietly(block: () -> Unit) {
    try {
        block()
    } catch (_: CancellationException) {
        currentCoroutineContext().ensureActive()
    } catch (_: Exception) {
    }
}

/** Swift's `try?` on a platform store, which never stops the caller. */
private inline fun <T> quietly(block: () -> T): T? = try {
    block()
} catch (_: Exception) {
    null
}
