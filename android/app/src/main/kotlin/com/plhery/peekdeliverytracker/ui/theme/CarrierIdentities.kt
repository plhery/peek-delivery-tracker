package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierBrandAssets
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Each carrier's livery (colours, truck decal, names) from Brand.json and the app's carrier catalog, both decoded once
 * and off the main thread: they are over a megabyte each. Made again for each change of the catalog, so a screen that
 * remembers a carrier's identity takes the new one, as the iPhone's views observing the catalog do.
 */
@Stable
class CarrierIdentities private constructor(val catalog: CarrierCatalog) {
    /** `CarrierVisualIdentity(carrier)`, its names in `language`. */
    fun of(carrier: CarrierId, language: AppLanguage): CarrierVisualIdentity =
        CarrierVisualIdentity.of(carrier, catalog, language)

    /** `CarrierVisualIdentity.shown(for:)`: the carrier a parcel's card shows, a probable one named as such. */
    fun shown(parcel: Parcel, localizer: Localizer): CarrierVisualIdentity =
        CarrierVisualIdentity.shown(parcel, catalog, localizer)

    /** `catalog.info(for:language:).displayName`. */
    fun displayName(carrier: CarrierId, language: AppLanguage): String = catalog.info(carrier, language).displayName

    companion object {
        /** Decodes the brand data on the IO dispatcher, or finds it decoded already, for the liveries of `catalog`. */
        suspend fun load(catalog: CarrierCatalog): CarrierIdentities = withContext(Dispatchers.IO) {
            CarrierBrandAssets.shared
            CarrierIdentities(catalog)
        }
    }
}

/** The identities once loaded; null while the catalog or Brand.json is still being read. */
val LocalCarrierIdentities = staticCompositionLocalOf<CarrierIdentities?> { null }

/**
 * The identities of the app's catalog (AppContainer's, null until it is read), made again as it refreshes; null until
 * there is a catalog and Brand.json has been read.
 */
@Composable
fun rememberCarrierIdentities(catalog: CarrierCatalog?): CarrierIdentities? {
    val identities by produceState<CarrierIdentities?>(null, catalog) {
        if (catalog == null) return@produceState
        catalog.definitionsFlow.collect { value = CarrierIdentities.load(catalog) }
    }
    return identities
}

/** A carrier's identity for a screen, or null while the identities are loading. */
@Composable
fun carrierIdentity(carrier: CarrierId, language: AppLanguage): CarrierVisualIdentity? {
    val identities = LocalCarrierIdentities.current ?: return null
    return remember(identities, carrier, language) { identities.of(carrier, language) }
}

/** The carrier a parcel's card shows (`CarrierVisualIdentity.shown(for:)`), or null while the identities are loading. */
@Composable
fun shownCarrierIdentity(parcel: Parcel, localizer: Localizer, language: AppLanguage): CarrierVisualIdentity? {
    val identities = LocalCarrierIdentities.current ?: return null
    return remember(identities, parcel, language) { identities.shown(parcel, localizer) }
}
