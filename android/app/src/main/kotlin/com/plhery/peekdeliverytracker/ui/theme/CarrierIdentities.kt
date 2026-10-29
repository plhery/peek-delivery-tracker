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
import com.plhery.peekdeliverytracker.core.api.CarrierId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Each carrier's livery (colours, truck decal, names) from Brand.json and the app's carrier catalog, both decoded once
 * and off the main thread: they are over a megabyte each. Made again for each change of the catalog, so a screen that
 * remembers a carrier's identity takes the new one, as the iPhone's views observing the catalog do.
 */
@Stable
class CarrierIdentities private constructor(private val catalog: CarrierCatalog) {
    /** `CarrierVisualIdentity(carrier)`, its names in `language`. */
    fun of(carrier: CarrierId, language: AppLanguage): CarrierVisualIdentity =
        CarrierVisualIdentity.of(carrier, catalog, language)

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
