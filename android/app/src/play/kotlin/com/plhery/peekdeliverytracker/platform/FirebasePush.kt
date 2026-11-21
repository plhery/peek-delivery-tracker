package com.plhery.peekdeliverytracker.platform

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailabilityLight
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.plhery.peekdeliverytracker.BuildConfig
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.UserDefaults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

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
        /** The build's (SDT_FIREBASE_*, Android's alone), or null in a build without all four. */
        val build: FirebaseSettings? by lazy {
            of(
                projectId = BuildConfig.SDTFirebaseProjectID, applicationId = BuildConfig.SDTFirebaseAppID,
                apiKey = BuildConfig.SDTFirebaseAPIKey, senderId = BuildConfig.SDTFirebaseSenderID,
            )
        }

        fun of(projectId: String, applicationId: String, apiKey: String, senderId: String): FirebaseSettings? {
            val values = listOf(projectId, applicationId, apiKey, senderId).map(String::trim)
            if (values.any(String::isEmpty)) return null
            return FirebaseSettings(values[0], values[1], values[2], values[3])
        }
    }
}

/**
 * Firebase Cloud Messaging's tokens, where the iPhone registers with APNs. The app starts Firebase itself ([start]),
 * from the build's settings: the manifest removes Firebase's own start-up, which would look for google-services.json's
 * resources, and turns its automatic token off, so nothing reaches Google before the app asks for a token. A token
 * asked for while a deletion is under way waits for it, so the answer is the new one. A deletion that can't reach
 * Google's servers, offline, leaves Firebase handing out the same token: [defaults] remembers it, and it is tried again
 * as the app next starts, before any token is asked for. FirebaseMessaging's token calls are deprecated for its
 * installation registrations (`register()`), which this version of the service doesn't use.
 */
class FirebasePushTokens private constructor(private val defaults: UserDefaults) : PushTokens {
    private var deletion: Task<Void>? = null

    init {
        if (defaults.bool(forKey = DELETION_PENDING)) delete()
    }

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
        defaults.set(true, forKey = DELETION_PENDING)
        val task = FirebaseMessaging.getInstance().deleteToken()
        deletion = task
        task.addOnCompleteListener { if (it.isSuccessful && deletion === task) defaults.removeObject(forKey = DELETION_PENDING) }
    }

    companion object {
        /** Set while a deletion of the token hasn't gone through. */
        private const val DELETION_PENDING = "sdt.firebaseTokenDeletionPending.v1"

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
            return FirebasePushTokens(PreferencesDefaults.standard(context))
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
        ContextCompat.getMainExecutor(this).execute {
            if (app.container.value != null) app.pushTokenRefreshed(token) else PushTokenHandover.enqueue(app, token)
        }
    }
}

/**
 * A token Firebase made while no window had made the stores, as it does in a process it starts for that alone: kept
 * in the work, it goes to the account's devices and to the parcels this phone follows without one once the stores are
 * made for it, and again on the network's return until the service has taken both. One more token replaces it.
 */
class PushTokenHandover(context: Context, parameters: WorkerParameters) : CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result = hand(inputData.getString(TOKEN)) { token ->
        val app = applicationContext as PeekApplication
        withContext(Dispatchers.Main) {
            app.makeContainer()
            val stores = app.container.filterNotNull().first()
            stores.session.stateFlow.first { it != AuthenticationState.Loading }
            val language = stores.localizer.language
            val account = stores.parcels.forwardNativePushToken(token, language)
            val guests = stores.parcels.registerDeviceAlerts(token, language)
            account && guests
        }
    }

    companion object {
        internal const val NAME = "push-token"
        private const val TOKEN = "token"

        /** Done once [register] has handed the token on; tried again later while the service hasn't taken it. */
        internal suspend fun hand(token: String?, register: suspend (String) -> Boolean): Result = when {
            token.isNullOrEmpty() -> Result.success()
            register(token) -> Result.success()
            else -> Result.retry()
        }

        fun enqueue(context: Context, token: String) {
            val request = OneTimeWorkRequestBuilder<PushTokenHandover>()
                .setInputData(workDataOf(TOKEN to token))
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()
            WorkManager.getInstance(context).enqueueUniqueWork(NAME, ExistingWorkPolicy.REPLACE, request)
        }
    }
}
