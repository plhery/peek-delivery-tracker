package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.runtime.compositionLocalOf
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelFeedbackClient
import com.plhery.peekdeliverytracker.core.ParcelFeedbackMemory
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel
import com.plhery.peekdeliverytracker.core.ParcelLinkClaim
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkStore
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestApp
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import java.util.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

/** Whether a parcel link's sheet is up (`links.route != nil`): the first open's journey waits under it. */
val LocalParcelLinkOpen = compositionLocalOf { false }

/**
 * What a parcel link's sheet reads and asks of the app's stores: ParcelLinkSheet's `links`, `session`, `parcels` and
 * `DeliveryAnalytics.shared`.
 */
interface ParcelLinkSource {
    /** The link on screen and what reading it gave (`ParcelLinkStore`). */
    val links: ParcelLinkStore

    val session: StateFlow<AuthenticationState>

    /** The parcels the list shows, an account's or this phone's own, which name a parcel already followed. */
    val parcels: StateFlow<List<Parcel>>

    /** The app's own scope: a read, a keep or a follow under way outlives the window made again. */
    val scope: CoroutineScope

    /** What the sheet's presentation does, kept by the app so that the window made again finds it as it was. */
    val sheet: ParcelLinkSheetState

    /** `session.configuration.authenticationConfigured`: the build has a way to sign in. */
    val authenticationConfigured: Boolean

    /** `parcels.tracksWithoutAccount`: the build has a server, so this phone may follow a parcel without an account. */
    val tracksWithoutAccount: Boolean

    /**
     * `PushNotifications.available`: whether this phone can receive pushes, which it can't in a build without
     * Firebase's settings; without them, following a parcel here promises no notifications.
     */
    val pushAvailable: Boolean get() = true

    /** `parcels.keep(_:)`: the parcel kept in the account. */
    suspend fun keep(link: ParcelLinkRoute): ParcelLinkClaim

    /** `parcels.follow(_:_:)`: the parcel followed on this phone, without an account. */
    fun follow(link: ParcelLinkRoute, response: PublicParcelResponse): ParcelLinkClaim

    /** `parcels.followedHere(_:)`: the parcel this phone follows itself behind the link, if any. */
    fun followedHere(response: PublicParcelResponse): UUID?

    /** `parcels.followedHere(linkID:)`: the parcel this phone follows through link `linkId`, for a notification about it. */
    fun followedHere(linkId: String): UUID?

    /** `parcels.showDeviceParcels()`: the parcels this phone follows itself come forward. */
    fun showDeviceParcels()

    /** `session.showSignIn()`. */
    fun showSignIn()

    /** `localizer.errorMessage(_:)`. */
    fun errorMessage(error: Throwable): String

    /** `DeliveryAnalytics.shared.action(_:)`. */
    fun action(name: String)

    /** `ParcelFeedbackModel()`: the question under the parcel's history, as a page's. */
    fun feedbackModel(): ParcelFeedbackModel

    /** `parcels.feedbackClient(forLink:)`: where an answer about the parcel behind link `linkId` goes. */
    fun feedbackClient(linkId: String): ParcelFeedbackClient

    /** A debug build's `sdt.debug.parcelFeedback`: the question's state shown under the history ([LaunchPreviews]). */
    val feedbackPreview: String? get() = null
}

/** The sheet's stores: the app's container. */
class ContainerParcelLinks(private val container: AppContainer) : ParcelLinkSource {
    override val links get() = container.links
    override val session get() = container.session.stateFlow
    override val parcels get() = container.parcels.parcelsFlow
    override val scope get() = container.scope
    override val sheet get() = container.linkSheet
    override val authenticationConfigured get() = container.configuration.authenticationConfigured
    override val tracksWithoutAccount get() = container.parcels.tracksWithoutAccount
    override val pushAvailable get() = container.notifications.available

    override suspend fun keep(link: ParcelLinkRoute) = container.parcels.keep(link)

    override fun follow(link: ParcelLinkRoute, response: PublicParcelResponse) = container.parcels.follow(link, response)

    override fun followedHere(response: PublicParcelResponse) = container.parcels.followedHere(response)

    override fun followedHere(linkId: String) = container.parcels.followedHere(linkId)

    override fun showDeviceParcels() = container.parcels.showDeviceParcels()

    override fun showSignIn() = container.session.showSignIn()

    override fun errorMessage(error: Throwable) = container.localizer.errorMessage(error)

    override fun action(name: String) = container.analytics.action(name)

    override fun feedbackModel() = ParcelFeedbackModel(
        scope = container.scope, memory = ParcelFeedbackMemory(container.defaults),
        report = { name, outcome -> container.analytics.action(name, outcome) }, review = container.reviewPrompt,
        // What the answers say they come from, as a page's.
        app = ParcelFeedbackRequestApp.Android,
    )

    /**
     * Through the link, with its owner key when this phone holds it, signed in or not; nowhere in a build without a
     * server, whose links are made up.
     */
    override fun feedbackClient(linkId: String) = container.parcels.feedbackClient(linkId)

    override val feedbackPreview get() = container.launchPreviews.feedback
}
