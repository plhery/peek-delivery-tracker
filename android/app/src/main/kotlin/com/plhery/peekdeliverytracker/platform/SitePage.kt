package com.plhery.peekdeliverytracker.platform

import android.content.ActivityNotFoundException
import android.content.Context
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.ui.platform.UriHandler
import androidx.core.net.toUri
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore

/**
 * `SafariPage` (SettingsViews.swift): a page of the site read without leaving the app, as the iPhone's
 * SFSafariViewController shows it in a sheet over the window, in the window's style, with its close button. Here a
 * Custom Tab of the phone's default browser, in the app's appearance (light or dark as the reader picked, the phone's
 * for System). Where the default browser has no Custom Tabs, or none is chosen, [elsewhere] opens the page as the
 * app's other links open.
 */
object SitePage {
    fun open(context: Context, url: String, elsewhere: UriHandler) {
        val browser = CustomTabsClient.getPackageName(context, null) ?: return elsewhere.openUri(url)
        val tab = CustomTabsIntent.Builder()
            .setColorScheme(AppearanceStore.of(context).appearance.value.customTabColorScheme)
            .build()
        // The browser's own tab rather than an app that takes the address as its own link, as Safari's sheet opens it.
        tab.intent.setPackage(browser)
        try {
            tab.launchUrl(context, url.toUri())
        } catch (_: ActivityNotFoundException) {
            elsewhere.openUri(url)
        }
    }
}

/** The browser's colours for an appearance (the Auth Tab takes the Custom Tabs' schemes). */
internal val AppAppearance.customTabColorScheme: Int
    get() = when (this) {
        AppAppearance.System -> CustomTabsIntent.COLOR_SCHEME_SYSTEM
        AppAppearance.Light -> CustomTabsIntent.COLOR_SCHEME_LIGHT
        AppAppearance.Dark -> CustomTabsIntent.COLOR_SCHEME_DARK
    }
