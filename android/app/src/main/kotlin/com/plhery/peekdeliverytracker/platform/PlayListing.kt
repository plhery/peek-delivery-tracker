package com.plhery.peekdeliverytracker.platform

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.UriHandler
import androidx.core.net.toUri

/**
 * Peek's page on Google Play, which Settings' "Rate Peek" opens where the iPhone's opens the App Store's page for a
 * review (`AppConfiguration.reviewURL`): in the Play Store app, or, on a phone without it, the same page on the web as
 * the app's other links open. A plain link, as the iPhone's: Play In-App Review may show nothing after a tap.
 */
object PlayListing {
    /** The Play Store app, which takes `market://` addresses (other stores may too, without Peek). */
    const val STORE_PACKAGE = "com.android.vending"

    /**
     * Whether Peek's page on Google Play is public. Until it is, only the closed test's testers can open it, and
     * Settings has no "Rate Peek": set it to true in the release that goes public. Tests set it either way.
     */
    var listed: Boolean = false

    fun storeUri(packageName: String): String = "market://details?id=$packageName"

    fun webUrl(packageName: String): String = "https://play.google.com/store/apps/details?id=$packageName"

    fun open(context: Context, elsewhere: UriHandler) {
        val store = Intent(Intent.ACTION_VIEW, storeUri(context.packageName).toUri()).setPackage(STORE_PACKAGE)
        if (context !is Activity) store.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(store)
        } catch (_: ActivityNotFoundException) {
            elsewhere.openUri(webUrl(context.packageName))
        }
    }
}
