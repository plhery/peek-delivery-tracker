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
 * Each carrier's livery (colours, truck decal, names) from Brand.json and the carrier catalog, both decoded once and
 * off the main thread: they are over a megabyte each.
 */
@Stable
class CarrierIdentities private constructor(private val catalog: CarrierCatalog) {
    /** `CarrierVisualIdentity(carrier)`, its names in `language`. */
    fun of(carrier: CarrierId, language: AppLanguage): CarrierVisualIdentity =
        CarrierVisualIdentity.of(carrier, catalog, language)

    companion object {
        /** Decodes the brand data and the app's catalog on the IO dispatcher, or finds them decoded already. */
        suspend fun load(): CarrierIdentities = withContext(Dispatchers.IO) {
            CarrierBrandAssets.shared
            CarrierIdentities(CarrierCatalog.shared)
        }
    }
}

/** The identities once loaded; null while Brand.json is still being read. */
val LocalCarrierIdentities = staticCompositionLocalOf<CarrierIdentities?> { null }

/** Starts loading the identities, giving null until they are ready. */
@Composable
fun rememberCarrierIdentities(): CarrierIdentities? {
    val identities by produceState<CarrierIdentities?>(null) { value = CarrierIdentities.load() }
    return identities
}

/** A carrier's identity for a screen, or null while the identities are loading. */
@Composable
fun carrierIdentity(carrier: CarrierId, language: AppLanguage): CarrierVisualIdentity? {
    val identities = LocalCarrierIdentities.current ?: return null
    return remember(identities, carrier, language) { identities.of(carrier, language) }
}
