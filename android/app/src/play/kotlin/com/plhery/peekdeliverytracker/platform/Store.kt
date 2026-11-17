package com.plhery.peekdeliverytracker.platform

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ReviewRequester
import com.plhery.peekdeliverytracker.core.StandardIntegrity
import com.plhery.peekdeliverytracker.ui.deliveries.LabelScanner

/**
 * What Google Play's build has of Google's services, where F-Droid's has its own (src/fdroid): Firebase Cloud
 * Messaging's pushes, Play In-App Review's prompt, ML Kit's label scanner and Play Integrity's tokens.
 */
internal object Store {
    /** Firebase, which the manifest no longer starts: before a push or a new token reaches PeekMessagingService. */
    fun start(application: Application) {
        FirebaseSettings.build?.let { FirebasePushTokens.start(application, it) }
    }

    /**
     * The pushes' tokens: Firebase Cloud Messaging's in a build with its settings, on a phone with Google Play services;
     * null otherwise.
     */
    fun pushTokens(context: Context): PushTokens? = FirebasePushTokens.of(context, FirebaseSettings.build)

    /** The system's prompt for a rating over `activity`: Play In-App Review, where the iPhone asks StoreKit. */
    fun reviews(activity: Activity): ReviewRequester = PlayReviews(activity)

    /** Play Integrity's standard requests, whose tokens go with the key's registration where the server asks for them. */
    fun standardIntegrity(context: Context): StandardIntegrity? = GooglePlayIntegrity(context)

    /** CameraX and ML Kit's bundled barcode model. */
    val scanner: LabelScanner = CameraLabelScanner

    /** How the notifications reach the phone, which Delivery updates says where it is worth saying: here, nothing. */
    @Suppress("UNUSED_PARAMETER")
    @Composable
    fun pushRoute(localizer: Localizer): String? = null
}
