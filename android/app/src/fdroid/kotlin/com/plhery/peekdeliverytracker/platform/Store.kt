package com.plhery.peekdeliverytracker.platform

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ReviewRequester
import com.plhery.peekdeliverytracker.core.StandardIntegrity
import com.plhery.peekdeliverytracker.ui.deliveries.LabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.NoLabelScanner

/**
 * F-Droid's build, without Google's libraries, where Google Play's has Google's services (src/play): the pushes through
 * UnifiedPush or the inbox ([PushRoute]), no rating prompt, no label scanner and no Play Integrity.
 */
internal object Store {
    /** Nothing to start: the distributor's connector and WorkManager start themselves. */
    @Suppress("UNUSED_PARAMETER")
    fun start(application: Application) = Unit

    /**
     * The pushes' addresses, on any phone: a UnifiedPush distributor's endpoint, or the inbox. None in a build without
     * the service (`SDT_USE_API = NO`), which has nothing to send them, as Google Play's without Firebase's settings.
     */
    fun pushTokens(context: Context): PushTokens? =
        if (BuildSettings.configuration.mode == AppConfiguration.Mode.Api) UnifiedPushTokens(context.applicationContext) else null

    /** No rating prompt: In-App Review is Play's alone, so nothing is ever asked. */
    @Suppress("UNUSED_PARAMETER")
    fun reviews(activity: Activity): ReviewRequester = object : ReviewRequester {
        override suspend fun prepare(): Boolean = false
        override fun launch() = Unit
    }

    /** No Play Integrity: the key's attestation registers it alone. */
    @Suppress("UNUSED_PARAMETER")
    fun standardIntegrity(context: Context): StandardIntegrity? = null

    /** No label scanner: ML Kit's barcode model is Google's. */
    val scanner: LabelScanner = NoLabelScanner

    /** How the notifications reach the phone, which Delivery updates says: the distributor app by its name, or the inbox. */
    @Composable
    fun pushRoute(localizer: Localizer): String? {
        val context = LocalContext.current
        val distributor by PushRoute.distributor.collectAsState()
        return distributor?.let { localizer.text("notifications.route.unifiedpush", mapOf("app" to label(context, it))) }
            ?: localizer.text("notifications.route.inbox")
    }

    private fun label(context: Context, packageName: String): String = try {
        val manager = context.packageManager
        manager.getApplicationLabel(manager.getApplicationInfo(packageName, 0)).toString()
    } catch (_: PackageManager.NameNotFoundException) {
        packageName
    }
}
