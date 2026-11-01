package com.plhery.peekdeliverytracker.ui.deliveries

import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelLinkClaim
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkStore
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import java.util.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

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

    /** `parcels.keep(_:)`: the parcel kept in the account. */
    suspend fun keep(link: ParcelLinkRoute): ParcelLinkClaim

    /** `parcels.follow(_:_:)`: the parcel followed on this phone, without an account. */
    fun follow(link: ParcelLinkRoute, response: PublicParcelResponse): ParcelLinkClaim

    /** `parcels.followedHere(_:)`: the parcel this phone follows itself behind the link, if any. */
    fun followedHere(response: PublicParcelResponse): UUID?

    /** `parcels.showDeviceParcels()`: the parcels this phone follows itself come forward. */
    fun showDeviceParcels()

    /** `session.showSignIn()`. */
    fun showSignIn()

    /** `localizer.errorMessage(_:)`. */
    fun errorMessage(error: Throwable): String

    /** `DeliveryAnalytics.shared.action(_:)`. */
    fun action(name: String)
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

    override suspend fun keep(link: ParcelLinkRoute) = container.parcels.keep(link)

    override fun follow(link: ParcelLinkRoute, response: PublicParcelResponse) = container.parcels.follow(link, response)

    override fun followedHere(response: PublicParcelResponse) = container.parcels.followedHere(response)

    override fun showDeviceParcels() = container.parcels.showDeviceParcels()

    override fun showSignIn() = container.session.showSignIn()

    override fun errorMessage(error: Throwable) = container.localizer.errorMessage(error)

    override fun action(name: String) = container.analytics.action(name)
}
