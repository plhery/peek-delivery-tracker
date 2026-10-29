package com.plhery.peekdeliverytracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesTab
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelListPlaceholder
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelPagePlaceholder
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme

/** Made-up parcels for the list's placeholder. */
val SAMPLE_PARCELS = List(40) { "parcel-${it + 1}" to "Parcel ${it + 1}" }

/**
 * The root as the app shows it, in the app's copy for `language`, the deliveries' placeholders showing [parcels],
 * under Reduce Motion unless asked otherwise.
 */
@Composable
fun RootHost(
    state: AuthenticationState = AuthenticationState.Welcome,
    language: AppLanguage = AppLanguage.En,
    dark: Boolean = false,
    reduceMotion: Boolean = true,
    onView: (String) -> Unit = {},
    parcels: List<Pair<String, String>> = SAMPLE_PARCELS,
    deliveries: (@Composable () -> Unit)? = null,
) {
    val localizer = remember(language) { Localizer(language, variant = "android") }
    PeekTheme(dark = dark, highContrast = false, reduceMotion = reduceMotion, reduceTransparency = false, carrierIdentities = null) {
        PeekRoot(state, localizer, onView) {
            if (deliveries != null) {
                deliveries()
            } else {
                DeliveriesTab(
                    list = { open -> ParcelListPlaceholder(parcels, open) },
                    page = { id -> ParcelPagePlaceholder(parcels.firstOrNull { it.first == id }?.second) },
                )
            }
        }
    }
}
