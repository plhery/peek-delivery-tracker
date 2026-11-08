package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateMapOf
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.BuildConfig
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.DeliveryApiClient
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.DeliveryEmail
import com.plhery.peekdeliverytracker.core.FriendInvitationStore
import com.plhery.peekdeliverytracker.core.FriendsActivityStore
import com.plhery.peekdeliverytracker.core.FriendsClient
import com.plhery.peekdeliverytracker.core.FriendsStore
import com.plhery.peekdeliverytracker.core.GivenPostcode
import com.plhery.peekdeliverytracker.core.ImportChargesPreview
import com.plhery.peekdeliverytracker.core.NativeRoute
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import com.plhery.peekdeliverytracker.core.OnTheWayRecord
import com.plhery.peekdeliverytracker.core.ParcelFeedbackClient
import com.plhery.peekdeliverytracker.core.ParcelFeedbackMemory
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel
import com.plhery.peekdeliverytracker.core.ParcelFeedbackPreview
import com.plhery.peekdeliverytracker.core.ParcelShareModel
import com.plhery.peekdeliverytracker.core.ParcelShareNotes
import com.plhery.peekdeliverytracker.core.ParcelSharePreview
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.ReviewPrompt
import com.plhery.peekdeliverytracker.core.ReviewRequester
import com.plhery.peekdeliverytracker.core.SharedParcelDraft
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestApp
import com.plhery.peekdeliverytracker.platform.AppNotifications
import com.plhery.peekdeliverytracker.platform.LauncherShortcuts
import java.io.File
import java.util.UUID
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.transformWhile
import kotlinx.coroutines.launch

/**
 * What the deliveries read and ask of the app's stores: ParcelListView's `store`, `session`, the configuration's pages
 * and `DeliveryAnalytics.shared`.
 */
interface DeliveriesSource {
    /** The app's launch these stores belong to: a window saved in another one comes back after the process died. */
    val launch: UUID

    /**
     * The app's own scope, which outlives the window: the list's changes to the parcels run in it, so that leaving the
     * tab or the window being made again doesn't cancel one half done, which the store would undo.
     */
    val scope: CoroutineScope

    /** What the parcel pages wait for, by page, for as long as the changes they asked for run in [scope]. */
    val pageWork: PageWork

    val parcels: StateFlow<List<Parcel>>
    val loading: StateFlow<Boolean>
    val refreshing: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>

    /** `store.authenticationRequired`: the last load failed as the account's sign-in had expired. */
    val authenticationRequired: StateFlow<Boolean>

    /** `store.usingCachedData`: the parcels shown are those this phone kept from the account's last load. */
    val usingCachedData: StateFlow<Boolean>

    /**
     * `store.load(showSpinner: true)`: the account's parcels (or this phone's, for a guest) asked for again, the first
     * load's spinner showing while there are none to show.
     */
    suspend fun reload()

    val session: StateFlow<AuthenticationState>

    /** A debug build's `sdt.debug.storeScreenshots`: the demo shows without its bar ([DemoModeBar]), for the store. */
    val storeScreenshots: Boolean

    /** What a debug build opens at launch, to look at it (`sdt.debug.parcelShare`, `sdt.debug.parcelFeedback`); none in a release. */
    val launchPreviews: LaunchPreviews

    /** The site's landing page and its privacy notice, the foot's links. */
    val homePageUrl: String
    val privacyUrl: String

    /** The parcel archived last, which its toast's Undo brings back (`store.undoParcel`). */
    val undoParcel: StateFlow<Parcel?>

    /**
     * `store.undoParcel = nil` once the toast has had its time: only while it still offers this very Undo (`parcel` as
     * the store gave it), not one archived since, even of the same parcel.
     */
    fun forgetUndo(parcel: Parcel)

    /** How many of this phone's parcels a full account left here, for the list to say once (`store.leftOnDevice`). */
    val leftOnDevice: StateFlow<ParcelStore.LeftOnDevice?>

    /** `store.leftOnDevice = nil` as the list says it: only this very news, not newer news that came meanwhile. */
    fun forgetLeftOnDevice(news: ParcelStore.LeftOnDevice)

    /** What counts the Undo's seven seconds, for every window this process makes ([UndoClock]). */
    val undoClock: UndoClock

    /**
     * `checkAllParcels()`: checks every parcel and says how it went, waiting for a check the service queued. Null when
     * the check was interrupted, such as by signing out.
     */
    suspend fun checkAllParcels(): ParcelStore.RefreshResult?

    /** `store.refresh(_:)`: asks the parcel's carrier again, then shows what it answered. */
    suspend fun refresh(parcel: Parcel)

    suspend fun archive(parcel: Parcel)

    suspend fun restore(parcel: Parcel)

    suspend fun permanentlyDelete(parcel: Parcel)

    /** `store.rename(_:label:)`: names the parcel `label`, trimmed of whitespace and newlines. */
    suspend fun rename(parcel: Parcel, label: String)

    /** `store.setMuted(_:muted:)`: switches the parcel's notifications off, or back on. Nothing for a guest's parcel. */
    suspend fun setMuted(parcel: Parcel, muted: Boolean)

    /** `store.setEmailMuted(_:muted:)`: switches the delivery email off, or back on, for this parcel only. */
    suspend fun setEmailMuted(parcel: Parcel, muted: Boolean)

    /**
     * `store.changeCarrier(_:carrier:trackingURL:dpdPostcode:dpdPostcodeCountry:phoneDigits:providerPostcode:)`: moves the
     * parcel to `carrier`, with the link and the postcode it needs, the postcode's country where it isn't the carrier's
     * home, the phone digits it shows the parcel with (none removes them), and the postcode a provider asked for. The
     * page's switch to the carrier its number names sends none of them.
     */
    suspend fun changeCarrier(
        parcel: Parcel,
        carrier: CarrierId,
        trackingUrl: String? = null,
        dpdPostcode: String? = null,
        dpdPostcodeCountry: String? = null,
        providerPostcode: String? = null,
        phoneDigits: String? = null,
    )

    /** `store.isDemo`: the demo's parcels, which no server is asked about. */
    val isDemo: Boolean

    /** `store.isGuest`: tracking on this phone, without an account. */
    val isGuest: Boolean

    /**
     * `store.tracksWithoutAccount`: whether tracking without an account works at all. A build without a server has only
     * its demo, which the first open offers in place of its field.
     */
    val tracksWithoutAccount: Boolean

    /**
     * `store.add(trackingNumber:label:carrier:trackingURL:dpdPostcode:dpdPostcodeCountry:phoneDigits:)`: the parcel
     * added, once [parcels] holds it.
     */
    suspend fun add(
        trackingNumber: String,
        label: String,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String?,
        phoneDigits: String? = null,
    ): Parcel

    /**
     * `store.detectCarrier(trackingNumber:)`: which carrier knows the number, the account's or this phone's lookup.
     * `typed`: the number as typed, whose dashes and spaces tell some carriers' numbers apart.
     */
    suspend fun detectCarrier(trackingNumber: String, typed: String? = null): CarrierDetectionResponse

    /** `store.givenPostcodes`: the postcodes Add suggests, the parcels' newest first, then this phone's. */
    val givenPostcodes: List<GivenPostcode>

    /** `store.forgetPostcode(_:)`: this phone stops suggesting the postcode. */
    fun forgetPostcode(postcode: String)

    /**
     * `DeliveryAPIClient.numberRefusal`: whether the server says `question`'s carrier issues no number like its own,
     * which [com.plhery.peekdeliverytracker.core.NumberRefusals] asks once per pair.
     */
    suspend fun numberRefusal(question: NumberRefusalRequest): Boolean

    /** `store.deliveryEmail`, as the account's preferences and the session change: null without one. */
    val deliveryEmail: StateFlow<DeliveryEmail?>

    /** `store.notificationPreferences`: the account's, once loaded; null before then and without an account. */
    val notificationPreferences: StateFlow<NotificationPreferences?>

    /**
     * `store.setEmailOnDelivery(_:)`: switches the account's delivery email on or off, then, once [deliveryEmail] has
     * what was saved ([saveEmailOnDelivery]), says whether the store has it on (`store.deliveryEmail?.isOn`).
     */
    suspend fun setEmailOnDelivery(enabled: Boolean): Boolean

    /** `store.deliveryWidgetEnabled`: whether the Home Screen widget shows the parcels, on until turned off. */
    val deliveryWidgetEnabled: StateFlow<Boolean>

    /**
     * `store.setDeliveryWidgetEnabled(_:)`: counts `widget-change`, then turns the widget on or off for this phone; off,
     * it forgets the parcels it showed and says it is turned off.
     */
    fun setDeliveryWidgetEnabled(enabled: Boolean)

    /** `store.viewerLink(for:)`: the link someone else's parcel is followed through, which its menu reads. */
    fun viewerLink(parcel: Parcel): String?

    /**
     * `store.shareClient(for:)`: how the share sheet reaches the parcel's link (reads it, makes it, changes what it
     * shows, stops it), through the account, the device's own parcels or the demo's made-up links.
     */
    fun shareClient(parcel: Parcel): ParcelShareModel.Client

    /** `ParcelShareNotes()`: the words this phone keeps for the links it shares, in its preferences, 60 parcels at most. */
    val shareNotes: ParcelShareNotes

    /** The site the parcels' links lead to (`ParcelLinkRoute.address`'s `AppConfiguration.current.apiBaseURL`). */
    val linkBaseUrl: String

    /** The share sheets open, for as long as this process lasts ([ParcelShares]). */
    val shares: ParcelShares

    /** The questions the parcel pages ask, for as long as this process lasts ([ParcelFeedbacks]). */
    val feedbacks: ParcelFeedbacks

    /**
     * A page's question (ParcelDetailView's `ParcelFeedbackModel()`): its answers run in [scope], remembered in this
     * phone's preferences ([ParcelFeedbackMemory], which the store forgets as the account changes), counted with
     * [action], and a "Yes" may make it the moment for [reviewPrompt].
     */
    fun feedbackModel(): ParcelFeedbackModel

    /** `store.feedbackClient(for:)`: how a page sends what its reader says of the parcel. */
    fun feedbackClient(parcel: Parcel): ParcelFeedbackClient

    /** `localizer.errorMessage(error)`. */
    fun errorMessage(error: Throwable): String

    /** Leaves the demo for the first screen. */
    fun showWelcome()

    /** `session.showSignIn()`: the sign-in screen, from a guest's Add or the first open's masthead. */
    fun showSignIn()

    /** `session.enterDemo()`: the demo, from the first open of a build without a server. */
    fun enterDemo()

    fun view(screen: String)

    fun action(name: String, outcome: DeliveryAnalytics.Outcome? = null)

    /** When the list asks for a rating (ReviewPrompt): the days used, the bad days and the asks, kept on this phone. */
    val reviewPrompt: ReviewPrompt

    /** Nothing in the app stands in the way of asking for a rating (`ReviewPrompt.settled`). */
    val reviewSettled: StateFlow<Boolean>

    /**
     * `OnTheWayRecord()`: the parcels the list last showed on their way, kept in the app's preferences across launches
     * and never sent, so that one delivered while the list was out of view arrives on it once it is back.
     */
    val onTheWay: OnTheWayRecord

    /** The system's prompt for a rating, over the window's `activity` (`requestReview`). */
    fun reviews(activity: Activity): ReviewRequester

    /** `AppConfiguration.version`: the version Settings names under its feedback row and in the mail's subject. */
    val version: String

    /**
     * `store.notificationStatus`: what the phone allows the app's notifications, from Android's own setting and
     * permission ([NotificationAuthorizationStatus.fromAndroid]), once [refreshNotificationState] has read it.
     */
    val notificationStatus: StateFlow<NotificationAuthorizationStatus>

    /** `store.notificationsEnabledOnDevice`: pushes registered for this phone. */
    val notificationsEnabledOnDevice: StateFlow<Boolean>

    /**
     * `PushNotifications.available`: whether this phone can receive pushes, which it can't in a build without
     * Firebase's settings. Without them Settings, the bell and the parcel's alerts offer the delivery email alone.
     */
    val pushAvailable: Boolean

    /** `store.shouldInviteNotifications`, as the store changes: the invitation to turn notifications on is due. */
    val shouldInviteNotifications: StateFlow<Boolean>

    /** `store.notificationEnableInProgress`: notifications are being turned on, the phone's question included. */
    val notificationEnableInProgress: StateFlow<Boolean>

    /** `store.dismissNotificationInvitation()`: the invitation stays away for this account. */
    fun dismissNotificationInvitation()

    /** `store.notificationError`: why the account's preferences failed to load or save. */
    val notificationError: StateFlow<String?>

    /** `store.refreshNotificationState()`: reads the phone's notification setting again. */
    suspend fun refreshNotificationState()

    /** `store.loadNotificationPreferences()`: the account's or the demo's preferences; none for a guest. */
    suspend fun loadNotificationPreferences()

    /** `store.enableNotifications(language:)`: whether the welcome notification was sent. */
    suspend fun enableNotifications(): Boolean

    /** `store.disableNotifications()`. */
    suspend fun disableNotifications()

    /** `store.saveNotificationPreferences(_:)`. */
    suspend fun saveNotificationPreferences(value: NotificationPreferences)

    /** `store.exportAccount()`: the account's, the demo's or this phone's parcels, written to a file to share. */
    suspend fun exportAccount(): File

    /** `store.resetDemoData()`. */
    suspend fun resetDemoData()

    /** `store.forgetDeviceParcels()`: every parcel this phone follows without an account, here and on the service. */
    suspend fun forgetDeviceParcels()

    /** `store.deleteAccount(confirmation:)`: the account and all it keeps, `confirmation` the address typed. */
    suspend fun deleteAccount(confirmation: String)

    /** `store.signOut()`. */
    suspend fun signOut()

    /**
     * FriendsView's `FriendsStore()`, for as long as this process lasts, as the tab's `@StateObject` outlives a change of
     * tab: the circle, what its panels ask and what went wrong, the account's or the demo's.
     */
    val friends: FriendsStore

    /** The app's `FriendsActivityStore()`: the friends who accepted an invitation, and the friend to reveal. */
    val friendsActivity: FriendsActivityStore

    /** The account's friends, which the acceptance's own `FriendsStore()` asks. */
    val friendsClient: FriendsClient

    /** The app's `FriendInvitationStore()`: the invitation a link brought, shown over everything until it goes. */
    val invitation: FriendInvitationStore
}

/**
 * What a debug build opens at launch, to look at it: the iPhone's `#if DEBUG` launch arguments, read from the app's
 * defaults under the same names. The question's state on a page (`sdt.debug.parcelFeedback`, on the parcel
 * `sdt.debug.parcelFeedback.parcel` names, [ParcelFeedbackPreview]), the share sheet's (`sdt.debug.parcelShare`,
 * [ParcelSharePreview]) and the page with import charges (`sdt.debug.importCharges`, [ImportChargesPreview]). A release
 * build has none.
 */
class LaunchPreviews(
    val feedback: String? = null,
    private val feedbackParcel: String? = null,
    val share: String? = null,
    private val importCharges: String? = null,
    /** `ParcelSharePreview.prepare`: the demo's link to a parcel and this phone's words for it, in the share's state. */
    private val prepare: (parcelId: UUID, variant: String) -> Unit = { _, _ -> },
) {
    /** ParcelListView's `#if DEBUG` `.task`: the page that opens at launch, the share's, else the question's, else the charges'. */
    fun parcel(parcels: List<Parcel>): Parcel? = ParcelSharePreview.parcel(parcels, share)
        ?: ParcelFeedbackPreview.parcel(parcels, feedback, feedbackParcel)
        ?: ImportChargesPreview.parcel(parcels, importCharges)

    /** Some page opens at launch: a share's, a question's or the charges'. */
    val opensPage: Boolean get() = share != null || feedback != null || ImportChargesPreview.charges(importCharges) != null

    /** The share sheet's `ParcelSharePreview.prepare(parcel)`, before it reads the link. */
    fun prepareShare(parcelId: UUID) {
        share?.let { prepare(parcelId, it) }
    }

    /** The sheet stops the link once it has read it (`if ParcelSharePreview.variant == "stopped" { await model.stop() }`). */
    val stopsShare: Boolean get() = share == "stopped"
}

/** The deliveries from the app's container. */
class ContainerDeliveries(private val container: AppContainer) : DeliveriesSource {
    override val launch: UUID get() = LAUNCH
    override val scope: CoroutineScope get() = container.scope
    override val pageWork: PageWork get() = PAGE_WORK
    override val parcels get() = container.parcels.parcelsFlow
    override val loading get() = container.parcels.loadingFlow
    override val refreshing get() = container.parcels.refreshingFlow
    override val errorMessage get() = container.parcels.errorMessageFlow
    override val authenticationRequired get() = container.parcels.authenticationRequiredFlow
    override val usingCachedData get() = container.parcels.usingCachedDataFlow

    override suspend fun reload() = container.parcels.load(showSpinner = true)

    override val session get() = container.session.stateFlow
    override val storeScreenshots get() = container.storeScreenshots
    override val launchPreviews get() = container.launchPreviews
    override val homePageUrl get() = container.configuration.homePageUrl
    override val privacyUrl get() = container.configuration.privacyUrl

    override val undoParcel get() = container.parcels.undoParcelFlow

    override fun forgetUndo(parcel: Parcel) {
        if (container.parcels.undoParcel === parcel) container.parcels.undoParcel = null
    }

    override val leftOnDevice get() = container.parcels.leftOnDeviceFlow

    override fun forgetLeftOnDevice(news: ParcelStore.LeftOnDevice) {
        if (container.parcels.leftOnDevice == news) container.parcels.leftOnDevice = null
    }

    override val undoClock: UndoClock get() = UNDO_CLOCK

    override suspend fun checkAllParcels(): ParcelStore.RefreshResult? {
        val store = container.parcels
        val previousOutcome = store.refreshOutcomeFlow.value?.id
        return try {
            when (val start = store.refreshAll()) {
                is ParcelStore.RefreshStart.Completed -> start.result
                else -> {
                    // The store finishes a queued check in the background; wait for its outcome.
                    store.refreshingFlow.first { !it }
                    store.refreshOutcomeFlow.value?.takeIf { it.id != previousOutcome }?.result
                }
            }
        } catch (error: CancellationException) {
            // The caller's own cancellation goes on; the store's, as the account changed, is an interrupted check.
            currentCoroutineContext().ensureActive()
            null
        } catch (error: Exception) {
            ParcelStore.RefreshResult.Failed(container.localizer.errorMessage(error))
        }
    }

    override suspend fun refresh(parcel: Parcel) = container.parcels.refresh(parcel)

    override suspend fun archive(parcel: Parcel) = container.parcels.archive(parcel)

    override suspend fun restore(parcel: Parcel) = container.parcels.restore(parcel)

    override suspend fun permanentlyDelete(parcel: Parcel) = container.parcels.permanentlyDelete(parcel)

    override suspend fun rename(parcel: Parcel, label: String) = container.parcels.rename(parcel, label)

    override suspend fun setMuted(parcel: Parcel, muted: Boolean) = container.parcels.setMuted(parcel, muted)

    override suspend fun setEmailMuted(parcel: Parcel, muted: Boolean) = container.parcels.setEmailMuted(parcel, muted)

    override suspend fun changeCarrier(
        parcel: Parcel,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String?,
        providerPostcode: String?,
        phoneDigits: String?,
    ) = container.parcels.changeCarrier(parcel, carrier, trackingUrl, dpdPostcode, dpdPostcodeCountry, providerPostcode, phoneDigits)

    override val isDemo: Boolean get() = container.parcels.isDemo

    override val isGuest: Boolean get() = container.parcels.isGuest

    override val tracksWithoutAccount: Boolean get() = container.parcels.tracksWithoutAccount

    override suspend fun add(
        trackingNumber: String,
        label: String,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String?,
        phoneDigits: String?,
    ): Parcel = container.parcels.add(trackingNumber, label, carrier, trackingUrl, dpdPostcode, dpdPostcodeCountry, phoneDigits)

    override suspend fun detectCarrier(trackingNumber: String, typed: String?): CarrierDetectionResponse =
        container.parcels.detectCarrier(trackingNumber, typed)

    override val givenPostcodes: List<GivenPostcode> get() = container.parcels.givenPostcodes

    override fun forgetPostcode(postcode: String) = container.parcels.forgetPostcode(postcode)

    override suspend fun numberRefusal(question: NumberRefusalRequest): Boolean =
        DeliveryApiClient.numberRefusal(question, container.configuration, container.transport)

    override val deliveryEmail get() = container.deliveryEmail

    override val notificationPreferences get() = container.parcels.notificationPreferencesFlow

    override suspend fun setEmailOnDelivery(enabled: Boolean): Boolean =
        saveEmailOnDelivery(container.deliveryEmail, { container.parcels.deliveryEmail }) { container.parcels.setEmailOnDelivery(enabled) }

    override val deliveryWidgetEnabled get() = container.parcels.deliveryWidgetEnabledFlow

    override fun setDeliveryWidgetEnabled(enabled: Boolean) = container.parcels.setDeliveryWidgetEnabled(enabled)

    override fun viewerLink(parcel: Parcel): String? = container.parcels.viewerLink(parcel)

    override fun shareClient(parcel: Parcel): ParcelShareModel.Client = container.parcels.shareClient(parcel)

    override val shareNotes: ParcelShareNotes by lazy { ParcelShareNotes(container.defaults) }

    override val linkBaseUrl: String get() = container.configuration.apiBaseUrl

    override val shares: ParcelShares get() = SHARES

    override val feedbacks: ParcelFeedbacks get() = FEEDBACKS

    override fun feedbackModel(): ParcelFeedbackModel = ParcelFeedbackModel(
        scope = container.scope, memory = ParcelFeedbackMemory(container.defaults),
        report = { name, outcome -> container.analytics.action(name, outcome) }, review = container.reviewPrompt, app = FEEDBACK_APP,
    )

    /**
     * Through the account, or this phone's link to the parcel without one; the demo's answers go nowhere and are taken
     * as sent, as on the iPhone.
     */
    override fun feedbackClient(parcel: Parcel): ParcelFeedbackClient = container.parcels.feedbackClient(parcel)

    override fun errorMessage(error: Throwable): String = container.localizer.errorMessage(error)

    override fun showWelcome() = container.session.showWelcome()

    override fun showSignIn() = container.session.showSignIn()

    override fun enterDemo() = container.session.enterDemo()

    override fun view(screen: String) = container.analytics.view(screen)

    override fun action(name: String, outcome: DeliveryAnalytics.Outcome?) = container.analytics.action(name, outcome)

    override val reviewPrompt get() = container.reviewPrompt

    override val reviewSettled get() = container.reviewSettled

    override val onTheWay = OnTheWayRecord(container.defaults)

    override fun reviews(activity: Activity): ReviewRequester = container.reviews(activity)

    override val version: String get() = BuildConfig.VERSION_NAME

    override val notificationStatus get() = container.parcels.notificationStatusFlow
    override val shouldInviteNotifications get() = container.notificationInvitation
    override val notificationEnableInProgress get() = container.parcels.notificationEnableInProgressFlow

    override fun dismissNotificationInvitation() = container.parcels.dismissNotificationInvitation()

    override val notificationsEnabledOnDevice get() = container.parcels.notificationsEnabledOnDeviceFlow

    override val pushAvailable: Boolean get() = container.notifications.available

    override val friends: FriendsStore get() = container.friends

    override val friendsActivity: FriendsActivityStore get() = container.friendsActivity

    override val friendsClient: FriendsClient get() = container.friendsClient

    override val invitation: FriendInvitationStore get() = container.invitation

    override val notificationError get() = container.parcels.notificationErrorFlow

    override suspend fun refreshNotificationState() = container.parcels.refreshNotificationState()

    override suspend fun loadNotificationPreferences() = container.parcels.loadNotificationPreferences()

    override suspend fun enableNotifications(): Boolean = container.parcels.enableNotifications(container.localizer.language)

    override suspend fun disableNotifications() = container.parcels.disableNotifications()

    override suspend fun saveNotificationPreferences(value: NotificationPreferences) = container.parcels.saveNotificationPreferences(value)

    override suspend fun exportAccount(): File = container.parcels.exportAccount()

    override suspend fun resetDemoData() = container.parcels.resetDemoData()

    override suspend fun forgetDeviceParcels() = container.parcels.forgetDeviceParcels()

    override suspend fun deleteAccount(confirmation: String) = container.parcels.deleteAccount(confirmation)

    override suspend fun signOut() = container.parcels.signOut()

    private companion object {
        /** This process's launch: the same for every window it makes, another once the system made the process again. */
        val LAUNCH: UUID = UUID.randomUUID()

        /** What this process's pages wait for, for every window it makes, as its launch is. */
        val PAGE_WORK = PageWork()

        /** This process's share sheets, for every window it makes, as its pages' work is. */
        val SHARES = ParcelShares()

        /** This process's pages' questions, for every window it makes, as its pages' work is. */
        val FEEDBACKS = ParcelFeedbacks()

        /** What counts this process's Undo, for every window it makes, as its pages' work is. */
        val UNDO_CLOCK = UndoClock()

        /** What a page's answers say they come from, which the service counts them under. */
        val FEEDBACK_APP = ParcelFeedbackRequestApp.Android
    }
}

/**
 * The delivery email's choice saved (`save`), returning once `shown`, the email the pages read, holds what the store
 * now has (`stored`), and whether that has it on (`store.deliveryEmail?.isOn`). The app's `shown` follows the store a
 * frame later; the iPhone sets the store and the offer in one turn (`setEmailOnDelivery`, then `answerEmailOffer`), so
 * an offer answered never shows open again, its answers back, for the frame before the account's choice reaches it.
 */
internal suspend fun saveEmailOnDelivery(shown: StateFlow<DeliveryEmail?>, stored: () -> DeliveryEmail?, save: suspend () -> Unit): Boolean {
    save()
    return shown.first { it == stored() }?.isOn == true
}

/**
 * The seven seconds of an archive's Undo, counted in the app's scope, as the iPhone counts them in a task of its own:
 * leaving the tab or the window being made again doesn't give it seven more. Each archive counts for its own Undo, so
 * one after it, even of the same parcel put back in between, keeps its whole seven. While TalkBack, or Switch Access,
 * is on ([held]), which take longer than seven seconds to reach it, the Undo stays until it is used or another message
 * takes its place, and has its seven once neither is (`.task(id: "\(undoParcel):\(holdsUndo)")`).
 */
@Stable
class UndoClock {
    /** Whether TalkBack, another service that explores by touch, or Switch Access is on, as the window last read it. */
    val held = MutableStateFlow(false)

    /** Counts `undo`'s seven seconds, while it is the Undo `shown` and isn't [held], then has it forgotten. */
    fun count(undo: Parcel, shown: StateFlow<Parcel?>, scope: CoroutineScope, forget: (Parcel) -> Unit) {
        scope.launch {
            combine(shown, held) { now, held -> if (now === undo) !held else null }
                .distinctUntilChanged()
                // Over once another Undo, or none, shows: the last null cancels what was counting.
                .transformWhile { counting ->
                    emit(counting)
                    counting != null
                }
                .collectLatest { counting ->
                    if (counting != true) return@collectLatest
                    delay(UNDO_DURATION)
                    forget(undo)
                }
        }
    }

    private companion object {
        const val UNDO_DURATION = 7_000L
    }
}

/**
 * What each parcel page waits for (ParcelDetailView's `working`) and what came of it, by the page's id: a page's change
 * holds only that page's bell, refresh, switch and delete, and only the page that asked hears why it failed (its
 * `errorMessage`), or leaves once it was saved. Each page's answer to the delivery email's offer stands here too (its
 * `emailOffer`), saved apart from its changes.
 *
 * The source holds it for the launch, as long as the changes run, in the app's scope: a page drawn again as the window
 * is made again (a rotation, a fold) still waits for its change and hears how it went, as the iPhone's `@State` lasts
 * through a rotation. Kept with the window, it would come back as a copy the change, once done, never reaches. A page
 * that goes forgets what it asked for ([forget]), as the iPhone's page goes with its state: the change goes on, and the
 * page opened again starts afresh.
 */
@Stable
class PageWork {
    /** The change each page waits for, each its own: one its page forgot comes to nothing once done. */
    private val runs = mutableStateMapOf<UUID, Any>()
    private val failures = mutableStateMapOf<UUID, String>()
    private val leaves = mutableStateMapOf<UUID, List<UUID>>()
    private val offers = mutableStateMapOf<UUID, EmailOfferPhase>()
    private val offerNews = mutableStateMapOf<UUID, EmailOfferPhase>()
    private val additions = mutableStateMapOf<UUID, UUID>()
    private val duplicates = mutableStateMapOf<UUID, UUID>()
    private val exports = mutableStateMapOf<UUID, File>()
    private val emailChoices = mutableStateMapOf<UUID, EmailChoice>()
    private val emailFailures = mutableStateMapOf<UUID, Unit>()

    /** Whether the page with `pageId` waits for a change it asked for. */
    fun working(pageId: UUID): Boolean = pageId in runs

    /** Why the last change the page with `pageId` asked for failed, until its alert closes ([dismiss]). */
    fun failure(pageId: UUID): String? = failures[pageId]

    fun dismiss(pageId: UUID) {
        failures -= pageId
        duplicates -= pageId
    }

    /**
     * The pages whose change was saved and which leave for it, each with the list's cards as they stood when it was
     * asked for: the window that shows them by then takes them ([left]).
     */
    val leaving: Map<UUID, List<UUID>> get() = leaves

    fun left(pageId: UUID) {
        leaves -= pageId
    }

    /**
     * The page's `run`: `work` in `scope` while the page with `pageId` waits, and nothing while it waits for another.
     * Once done, unless the page forgot it: why it failed, in `message`'s words, or the page's leaving, where it leaves
     * once saved (`cards`).
     */
    fun run(pageId: UUID, scope: CoroutineScope, message: (Throwable) -> String, cards: List<UUID>? = null, work: suspend () -> Unit) {
        if (pageId in runs) return
        val run = Any()
        runs[pageId] = run
        failures -= pageId
        scope.launch {
            val failure = try {
                work()
                null
            } catch (error: CancellationException) {
                end(pageId, run)
                throw error
            } catch (error: Exception) {
                message(error)
            }
            if (!end(pageId, run)) return@launch
            if (failure != null) {
                failures[pageId] = failure
            } else if (cards != null) {
                leaves[pageId] = cards
            }
        }
    }

    /**
     * The account's export the page with `pageId` asked for (AccountView's `exportURL`), once written, until the page
     * has handed it to the share sheet ([shared]).
     */
    fun exported(pageId: UUID): File? = exports[pageId]

    /** Keeps `file` for the page with `pageId` from its change [run] writing it, unless the page forgot that change. */
    fun export(pageId: UUID, file: File) {
        if (pageId in runs) exports[pageId] = file
    }

    fun shared(pageId: UUID) {
        exports -= pageId
    }

    /** The parcel the Add sheet with `id` added, once saved, until it has gone ([forget]). */
    fun added(id: UUID): UUID? = additions[id]

    /** The parcel that has the number the Add sheet with `id` tried to add, with why it failed (`duplicateParcelID`). */
    fun duplicate(id: UUID): UUID? = duplicates[id]

    /**
     * Add's `save()` for the sheet with `id`: `work` adds the parcel in `scope` while the sheet waits, as [run] does.
     * Once saved, the parcel it added ([added]); once failed, why, and the parcel that has the number already where
     * that is why ([duplicate]).
     */
    fun add(id: UUID, scope: CoroutineScope, message: (Throwable) -> String, work: suspend () -> UUID) {
        if (working(id)) return
        duplicates -= id
        run(
            id, scope,
            message = { error ->
                if (error is DeliveryApiError.DuplicateTracking && working(id)) duplicates[id] = error.parcelId
                message(error)
            },
        ) {
            val parcelId = work()
            if (working(id)) additions[id] = parcelId
        }
    }

    /** Where the answer to the delivery email's offer on the page with `pageId` stands (its `emailOffer`). */
    fun emailOffer(pageId: UUID): EmailOfferPhase = offers[pageId] ?: EmailOfferPhase.Open

    /**
     * The answer just saved on the page with `pageId`, accepted or failed, until the page has said so ([heard]): the
     * page shown by then, made again or not, gives the tap and tells TalkBack.
     */
    fun emailOfferNews(pageId: UUID): EmailOfferPhase? = offerNews[pageId]

    fun heard(pageId: UUID) {
        offerNews -= pageId
    }

    /**
     * `answerEmailOffer(_:)` on the page with `pageId`: saves the answer in `scope` (`save`, which says whether the email
     * is on once saved), one at a time, saying it saves meanwhile. Once saved the offer says the email is on where it was
     * turned on and is, and goes back to open otherwise, which leaves the offer once the account has chosen; once failed
     * it says so, for another try. Nothing once the page forgot it.
     */
    fun answerEmailOffer(pageId: UUID, accepted: Boolean, scope: CoroutineScope, save: suspend () -> Boolean) {
        if (offers[pageId] is EmailOfferPhase.Saving) return
        val saving = EmailOfferPhase.Saving(accepted)
        offers[pageId] = saving
        scope.launch {
            val phase = try {
                if (save() && accepted) EmailOfferPhase.Accepted else EmailOfferPhase.Open
            } catch (error: CancellationException) {
                if (offers[pageId] === saving) offers[pageId] = EmailOfferPhase.Open
                throw error
            } catch (_: Exception) {
                EmailOfferPhase.Failed
            }
            if (offers[pageId] !== saving) return@launch
            offers[pageId] = phase
            if (phase != EmailOfferPhase.Open) offerNews[pageId] = phase
        }
    }

    /**
     * The position the delivery email's switch on the page with `pageId` was flipped to, until the server has answered
     * (`emailChoice`).
     */
    fun emailChoice(pageId: UUID): Boolean? = emailChoices[pageId]?.enabled

    /** The last flip of that switch failed (`emailFailed`), until the next one or [emailFailureSeen]. */
    fun emailFailed(pageId: UUID): Boolean = pageId in emailFailures

    fun emailFailureSeen(pageId: UUID) {
        emailFailures -= pageId
    }

    /**
     * Delivery updates' `setEmail(_:)` on the page with `pageId`: `save` in `scope`, one at a time, the switch held at
     * `enabled` meanwhile ([emailChoice]) and, once failed, [emailFailed], then `failed`. Nothing reaches the page once
     * it forgot the flip.
     */
    fun chooseEmail(pageId: UUID, enabled: Boolean, scope: CoroutineScope, failed: () -> Unit, save: suspend () -> Unit) {
        if (pageId in emailChoices) return
        val choice = EmailChoice(enabled)
        emailChoices[pageId] = choice
        emailFailures -= pageId
        scope.launch {
            val ok = try {
                save()
                true
            } catch (error: CancellationException) {
                if (emailChoices[pageId] === choice) emailChoices -= pageId
                throw error
            } catch (_: Exception) {
                false
            }
            if (emailChoices[pageId] !== choice) return@launch
            if (!ok) {
                emailFailures[pageId] = Unit
                failed()
            }
            emailChoices -= pageId
        }
    }

    /** The page with `pageId` has gone: it waits for nothing more, and hears nothing of what it asked for. */
    fun forget(pageId: UUID) {
        runs -= pageId
        failures -= pageId
        leaves -= pageId
        offers -= pageId
        offerNews -= pageId
        additions -= pageId
        duplicates -= pageId
        exports -= pageId
        emailChoices -= pageId
        emailFailures -= pageId
    }

    /** A flip of the delivery email's switch, each its own: one its page forgot comes to nothing once answered. */
    private class EmailChoice(val enabled: Boolean)

    /** The change `run` of the page with `pageId` is over: whether the page still waited for it. */
    private fun end(pageId: UUID, run: Any): Boolean {
        if (runs[pageId] !== run) return false
        runs -= pageId
        return true
    }
}

/**
 * What reaches a window's deliveries from outside the list: a parcel a link asked for (`.onOpenURL`'s
 * `parcel/<uuid>`), which the list shows and then takes. Each window has its own ([of]), as each iPhone scene follows
 * its own links, so the window the link came to opens it. The tabs bring the deliveries forward while one waits.
 * A friend a link asked for (`friend/<uuid>`) waits here too, for the window's root to take ([friend]), and Add with
 * the text a link gave (`add?tracking=<text>`), for the deliveries ([add]).
 */
class DeliveriesInbox(
    parcel: UUID? = null,
    private val parcelLink: (String) -> Boolean = { false },
    private val sharedDrafts: () -> SharedParcelDraft? = { null },
    friend: UUID? = null,
    private val invitationLink: (String) -> Boolean = { false },
    private val opened: () -> Unit = {},
    notifiedFriend: UUID? = null,
    notifiedLink: String? = null,
    private val notificationOpened: (NativeRoute) -> Unit = {},
    add: String? = null,
    private val repeated: (String) -> Boolean = { false },
    private val shortcutUsed: (String) -> Unit = {},
) {
    private val parcelState = MutableStateFlow(parcel)
    private val addState = MutableStateFlow(add?.let(::AddLink))
    private val friendState = MutableStateFlow(friend)
    private val notifiedFriendState = MutableStateFlow(notifiedFriend)
    private val notifiedLinkState = MutableStateFlow(notifiedLink)
    private val linkAddedState = MutableStateFlow<LinkAdded?>(null)

    /** The parcel waiting to be shown. */
    val parcel: StateFlow<UUID?> = parcelState.asStateFlow()

    /** A link asked for the parcel. */
    fun open(parcelId: UUID) {
        parcelState.value = parcelId
    }

    /** The list has shown the parcel. */
    fun consume(parcelId: UUID) {
        parcelState.compareAndSet(parcelId, null)
    }

    /** The friend a link asked to reveal, which the window's root reveals while someone is signed in, and then takes. */
    val friend: StateFlow<UUID?> = friendState.asStateFlow()

    /** The window's root has taken the friend. */
    fun consumeFriend(friendId: UUID) {
        friendState.compareAndSet(friendId, null)
    }

    /**
     * AppDelegate's `pendingFriendID`: the friend whose notification was opened, which the window's root reveals once
     * someone is signed in, and then takes.
     */
    val notifiedFriend: StateFlow<UUID?> = notifiedFriendState.asStateFlow()

    /** `consumePendingFriendID()`. */
    fun consumeNotifiedFriend(friendId: UUID) {
        notifiedFriendState.compareAndSet(friendId, null)
    }

    /**
     * AppDelegate's `pendingLinkID`: the link of a parcel followed without an account whose notification was opened,
     * which the window's root opens once the session is read, and then takes.
     */
    val notifiedLink: StateFlow<String?> = notifiedLinkState.asStateFlow()

    /** `consumePendingLinkID()`. */
    fun consumeNotifiedLink(linkId: String) {
        notifiedLinkState.compareAndSet(linkId, null)
    }

    /**
     * `handleURL`'s `.add(trackingInput:)`: Add, filled with the text a link gave, which the deliveries open (or fill,
     * when Add is up already) and then take. The tabs bring the deliveries forward while one waits. A window that shows
     * no list (nobody signed in, or an invitation over everything) lets it go, as the iPhone's has no list to hear it.
     */
    val add: StateFlow<AddLink?> = addState.asStateFlow()

    /** The deliveries have opened Add with the link's text, or the window had no list to open it on. */
    fun consume(add: AddLink) {
        addState.compareAndSet(add, null)
    }

    /** A parcel kept or followed from its link, which the list receives as one just added (`Arrival.added`). */
    val linkAdded: StateFlow<LinkAdded?> = linkAddedState.asStateFlow()

    /** The link's sheet has closed on a parcel it added: `parcelId`, when the service said which. */
    fun added(parcelId: UUID?) {
        linkAddedState.value = LinkAdded(parcelId)
    }

    /** The list has received the parcel the link added. */
    fun consume(added: LinkAdded) {
        linkAddedState.compareAndSet(added, null)
    }

    /**
     * `.onOpenURL` (A-L02, A-L14), in `open(_:)`'s order: a parcel's link first, which [parcelLink] opens in its sheet,
     * then `peekdeliverytracker://parcel/<uuid>`, which opens the parcel on the deliveries, then
     * `peekdeliverytracker://friend/<uuid>`, the friend to reveal ([friend]), then `peekdeliverytracker://add`, Add with
     * the link's text ([add]), and last a friend's invitation, which [invitationLink] shows over everything. The app's
     * parcel and invitation links come by ParcelLinkActivity, which keeps them out of the window's intent; one sent to
     * the window itself comes here. Each is counted ([opened], `deep-link-open`), whatever it leads to, as the iPhone's
     * analytics hear every `.onOpenURL`. Each is noted as the address seen last ([repeated], `LinkDeliveries.isRepeat`),
     * and the same again within a second isn't followed again where RootView's `open(_:)` would drop it: a parcel's link,
     * a friend, an invitation. The list's own routes, `parcel/<uuid>` and `add`, which ParcelListView's `handleURL`
     * follows unguarded, open again. One of the shortcuts on the app's icon opens one of those, and is reported used
     * ([shortcutUsed], [LauncherShortcuts]). A notification the app posted, opened, comes before them all
     * ([AppNotifications.payload], which reads the app's own alone).
     */
    fun follow(intent: Intent?) {
        AppNotifications.payload(intent)?.let {
            notificationTapped(it)
            return
        }
        if (intent?.action != Intent.ACTION_VIEW) return
        val url = intent.dataString ?: return
        opened()
        LauncherShortcuts.shortcut(intent)?.let(shortcutUsed)
        val repeat = repeated(url)
        when (val route = NativeRoute(url)) {
            is NativeRoute.Parcel -> open(route.id)
            is NativeRoute.Add -> addState.value = AddLink(route.trackingInput)
            else -> if (!repeat && !parcelLink(url)) {
                if (route is NativeRoute.Friend) friendState.value = route.id else invitationLink(url)
            }
        }
    }

    /**
     * `userNotificationCenter(_:didReceive:)` (C-W15): the route of the notification opened (`NativeRoute
     * (remoteNotification:)`), held until it is taken, as it is when the window starts with it: a parcel opens on the
     * deliveries as a link's does ([parcel], `openParcel`), a friend waits to be revealed ([notifiedFriend]) and a link
     * to be opened ([notifiedLink]). Each is counted as the iPhone counts it ([notificationOpened]).
     */
    private fun notificationTapped(payload: Map<String, String>) {
        val route = NativeRoute(remoteNotification = payload) ?: return
        notificationOpened(route)
        when (route) {
            is NativeRoute.Parcel -> open(route.id)
            is NativeRoute.Friend -> notifiedFriendState.value = route.id
            is NativeRoute.Link -> notifiedLinkState.value = route.id
            is NativeRoute.Add -> Unit
        }
    }

    /** `ShareInbox.consume()`: what another app shared with this one through the share card, for Add, once ([sharedDrafts]). */
    fun consumeSharedDraft(): SharedParcelDraft? = sharedDrafts()

    companion object {
        private const val SAVED = "deliveries.inbox"
        private const val PARCEL = "parcel"
        private const val FRIEND = "friend"
        private const val NOTIFIED_FRIEND = "notifiedFriend"
        private const val NOTIFIED_LINK = "notifiedLink"
        private const val ADD = "add"

        /**
         * The window's inbox, after its `super.onCreate`: it follows the link that started the window, and each link
         * that comes to it after, a parcel's link through [parcelLink], once each, as the iPhone hears a link once.
         * Made again, the window finds the parcel (or the friend) still waiting, and doesn't follow the link that
         * started it again once its list has shown it. Nor does a window that Recents starts again with the link that
         * began its task, after the process or the phone restarted: the system keeps that link as the task's own and
         * hands it over again (`FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY`). The window's intent forgets the link once it has
         * been read, and with it any owner key it carried. What another app shared comes from [sharedDrafts], which
         * takes it away. Each link followed is counted once ([opened]), and not again as the window is made again.
         * A notification opened is followed the same way, the window's own or one that comes after, its push's data
         * forgotten once read, and counted once ([notificationOpened]). Add's text waits as the parcel does. A link
         * the app had just followed, in this window or another, is not followed again ([repeated]). A shortcut on the
         * app's icon that opened the window is reported used once ([shortcutUsed]).
         */
        fun of(
            activity: ComponentActivity,
            sharedDrafts: () -> SharedParcelDraft? = { null },
            parcelLink: (String) -> Boolean = { false },
            invitationLink: (String) -> Boolean = { false },
            opened: () -> Unit = {},
            notificationOpened: (NativeRoute) -> Unit = {},
            repeated: (String) -> Boolean = { false },
            shortcutUsed: (String) -> Unit = {},
        ): DeliveriesInbox {
            val registry = activity.savedStateRegistry
            val saved = registry.consumeRestoredStateForKey(SAVED)
            val inbox = DeliveriesInbox(
                saved?.getString(PARCEL)?.let(UUID::fromString), parcelLink, sharedDrafts, saved?.getString(FRIEND)?.let(UUID::fromString),
                invitationLink, opened, saved?.getString(NOTIFIED_FRIEND)?.let(UUID::fromString), saved?.getString(NOTIFIED_LINK),
                notificationOpened, saved?.getString(ADD), repeated, shortcutUsed,
            )
            registry.registerSavedStateProvider(SAVED) {
                Bundle().apply {
                    putString(PARCEL, inbox.parcel.value?.toString())
                    putString(FRIEND, inbox.friend.value?.toString())
                    putString(NOTIFIED_FRIEND, inbox.notifiedFriend.value?.toString())
                    putString(NOTIFIED_LINK, inbox.notifiedLink.value)
                    putString(ADD, inbox.add.value?.trackingInput)
                }
            }
            val intent = activity.intent
            if (saved == null && intent != null && intent.flags and Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY == 0) inbox.follow(intent)
            if (intent?.data != null) activity.intent = Intent(intent).setData(null)
            if (AppNotifications.payload(intent) != null) activity.intent = Intent(intent).setAction(Intent.ACTION_MAIN).replaceExtras(Bundle())
            activity.addOnNewIntentListener { inbox.follow(it) }
            return inbox
        }
    }
}

/** One parcel a link added, told to the list once ([DeliveriesInbox.added]); a new one for each, even of the same parcel. */
class LinkAdded(val parcelId: UUID?)

/**
 * Add with the text of one `peekdeliverytracker://add` link, opened once ([DeliveriesInbox.add]); a new one for each,
 * even with the same text, as each link makes the iPhone a new `SharedParcelDraft`.
 */
class AddLink(val trackingInput: String)
