package com.plhery.peekdeliverytracker.platform

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailabilityLight
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.plhery.peekdeliverytracker.PeekApplication

/**
 * The Firebase project's Android app the pushes come through, as its google-services.json gives it: the build's
 * SDT_FIREBASE_PROJECT_ID, SDT_FIREBASE_APP_ID, SDT_FIREBASE_API_KEY and SDT_FIREBASE_SENDER_ID (android/README.md).
 * None of them is a secret, but none is committed either. Null ([of]) unless all four are given: such a build has no
 * pushes.
 */
class FirebaseSettings private constructor(
    val projectId: String,
    val applicationId: String,
    val apiKey: String,
    val senderId: String,
) {
    /** What `FirebaseApp.initializeApp` takes in place of google-services.json's resources. */
    val options: FirebaseOptions
        get() = FirebaseOptions.Builder()
            .setProjectId(projectId)
            .setApplicationId(applicationId)
            .setApiKey(apiKey)
            .setGcmSenderId(senderId)
            .build()

    companion object {
        fun of(projectId: String, applicationId: String, apiKey: String, senderId: String): FirebaseSettings? {
            val values = listOf(projectId, applicationId, apiKey, senderId).map(String::trim)
            if (values.any(String::isEmpty)) return null
            return FirebaseSettings(values[0], values[1], values[2], values[3])
        }
    }
}

/**
 * What the pushes need of Firebase Cloud Messaging ([FirebasePushTokens]), or of a test: this phone's registration
 * token, and its removal. Answers come on the main thread.
 */
interface PushTokens {
    /** `FirebaseMessaging.getToken()`: this phone's token, or why there is none. */
    fun token(answer: (Result<String>) -> Unit)

    /** `FirebaseMessaging.deleteToken()`: the token takes no more pushes, and the next one asked for is new. */
    fun delete()
}

/**
 * Firebase Cloud Messaging's tokens, where the iPhone registers with APNs. The app starts Firebase itself ([start]),
 * from the build's settings: the manifest removes Firebase's own start-up, which would look for google-services.json's
 * resources, and turns its automatic token off, so nothing reaches Google before the app asks for a token. A token
 * asked for while a deletion is under way waits for it, so the answer is the new one. FirebaseMessaging's token calls
 * are deprecated for its installation registrations (`register()`), which this version of the service doesn't use.
 */
class FirebasePushTokens private constructor() : PushTokens {
    private var deletion: Task<Void>? = null

    @Suppress("DEPRECATION")
    override fun token(answer: (Result<String>) -> Unit) {
        deletion?.takeIf { !it.isComplete }?.let { pending ->
            pending.addOnCompleteListener { token(answer) }
            return
        }
        // Without an executor, Task's listeners run on the main thread.
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            val token = if (task.isSuccessful) task.result else null
            answer(
                if (!token.isNullOrEmpty()) Result.success(token) else Result.failure(task.exception ?: IllegalStateException("Firebase gave no token")),
            )
        }
    }

    @Suppress("DEPRECATION")
    override fun delete() {
        deletion = FirebaseMessaging.getInstance().deleteToken()
    }

    companion object {
        /**
         * Starts Firebase with [settings], once for the process: from [PeekApplication.onCreate], before a push or a
         * new token can reach [PeekMessagingService].
         */
        fun start(context: Context, settings: FirebaseSettings) {
            if (FirebaseApp.getApps(context).isEmpty()) FirebaseApp.initializeApp(context, settings.options)
        }

        /**
         * The tokens of a build with Firebase's [settings], Firebase started; null without them, or on a phone without
         * Google Play services ([playServices]), which Firebase gets its tokens from: such a phone has no pushes, and
         * nothing offers them, where the iPhone always has APNs.
         */
        fun of(context: Context, settings: FirebaseSettings?, playServices: (Context) -> Boolean = ::hasPlayServices): PushTokens? {
            settings ?: return null
            if (!playServices(context)) return null
            start(context.applicationContext, settings)
            return FirebasePushTokens()
        }

        /**
         * Whether the phone has Google Play services: missing, turned off or not Google's, no token ever comes; out of
         * date or updating, they still count, as the token's own answer says whether one comes.
         */
        private fun hasPlayServices(context: Context): Boolean =
            when (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context)) {
                ConnectionResult.SERVICE_MISSING, ConnectionResult.SERVICE_DISABLED, ConnectionResult.SERVICE_INVALID -> false
                else -> true
            }
    }
}

/**
 * Firebase Cloud Messaging's side of the pushes, which the iPhone's AppDelegate and notification service take: each
 * push's data goes to the notifications the app posts ([AppNotifications.post]), on the thread it comes on, and each
 * token Firebase makes to the stores, on the main thread ([PeekApplication.pushTokenRefreshed]). The service sends data
 * alone, so the app writes every notification itself, in front or not. Not exported: Firebase's receiver hands it the
 * pushes inside the app.
 */
class PeekMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        (application as PeekApplication).posted.post(message.data)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun onNewToken(token: String) {
        val app = application as PeekApplication
        ContextCompat.getMainExecutor(this).execute { app.pushTokenRefreshed(token) }
    }
}
