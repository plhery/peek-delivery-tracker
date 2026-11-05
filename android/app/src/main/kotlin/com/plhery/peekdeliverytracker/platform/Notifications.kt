package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import com.plhery.peekdeliverytracker.core.PushNotifications
import java.io.File
import kotlinx.coroutines.CompletableDeferred

/**
 * POST_NOTIFICATIONS' question (Android 13 and later), asked from the window on screen as iOS's
 * `requestAuthorization` shows its alert over the app: each window registers its launcher as it is made ([attach]),
 * and [request] waits for the answer, which comes to the window made again after a turn as well. A window that asked
 * and closes for good before the answer, or a question that couldn't be shown, ends it unanswered, so the next can be
 * asked. The answer counts once given ([answered]), kept in the app's files that no backup carries, so a phone set up
 * from another asks again; a question dismissed without an answer, which Android 13 asks again, doesn't count. Whether
 * Android would still ask after a refusal ([rationale]) is read from the window on screen, or as last read while none
 * is. On the main thread.
 */
class NotificationPermission(context: Context) {
    private class Window(val activity: ComponentActivity, val launcher: ActivityResultLauncher<String>)

    private val windows = mutableListOf<Window>()
    private val marker = File(context.noBackupFilesDir, MARKER)
    private var answer: CompletableDeferred<Boolean>? = null

    /** The window that asked, or the next one made after it while it is made again; null while none waits for it. */
    private var asker: ComponentActivity? = null
    private var rationaleBefore = false
    private var lastRationale = false

    /** The window `activity` can ask; in its `onCreate`, before it starts, as Android registers each question. */
    fun attach(activity: ComponentActivity) {
        val launcher = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            // Only Android 13 and later ask ([request]).
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) answered(activity, granted)
        }
        val window = Window(activity, launcher)
        windows += window
        // The window that asked made again (a turn): the answer comes to this one.
        if (answer != null && asker == null) asker = activity
        activity.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                windows -= window
                if (asker !== activity) return
                asker = null
                // Closed for good, the window will hear no answer.
                if (!activity.isChangingConfigurations) end(false)
            }
        })
    }

    /** Whether the question was answered before, granted or refused. */
    val answered: Boolean get() = marker.exists()

    /** `shouldShowRequestPermissionRationale`: refused once, Android would still ask. */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun rationale(): Boolean {
        val window = front() ?: return lastRationale
        return window.activity.shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS).also { lastRationale = it }
    }

    /**
     * Asks, and says whether it was granted: one question at a time, each caller waiting for the same answer. Throws
     * when no window is on screen to ask from, or the question can't be shown.
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    suspend fun request(): Boolean {
        val pending = answer ?: run {
            val window = front() ?: throw IllegalStateException("No window on screen to ask from")
            rationaleBefore = rationale()
            CompletableDeferred<Boolean>().also {
                answer = it
                asker = window.activity
                try {
                    window.launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                } catch (error: RuntimeException) {
                    answer = null
                    asker = null
                    throw error
                }
            }
        }
        return pending.await()
    }

    private fun front(): Window? = windows.lastOrNull { it.activity.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun answered(activity: ComponentActivity, granted: Boolean) {
        val rationale = activity.shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)
        lastRationale = rationale
        // A first refusal asks for a rationale, a second ends it; a dismissal leaves it as it was, unanswered.
        if (granted || rationale || rationaleBefore) runCatching { marker.createNewFile() }
        end(granted)
    }

    /** The question over, `granted` or not, for everyone waiting for it. */
    private fun end(granted: Boolean) {
        val pending = answer
        answer = null
        asker = null
        pending?.complete(granted)
    }

    private companion object {
        const val MARKER = "notification-permission-answered"
    }
}

/**
 * The phone's notifications (`PushNotifications`): Android's permission, and Firebase Cloud Messaging's token.
 *
 * What the phone allows is read from POST_NOTIFICATIONS and the app's notification setting
 * ([NotificationAuthorizationStatus.fromAndroid]): on Android 13 and later not asked, may ask again, or denied once the
 * system no longer asks; on Android 12 the setting alone. The question ([requestAuthorization]) is asked from the
 * window on screen ([NotificationPermission]), only where the permission isn't granted yet.
 *
 * The token is [tokens]' ([FirebasePushTokens]), in a build with Firebase's settings; in one without, pushes are not
 * [available] and nothing offers them. [registerForRemoteNotifications] asks for it and keeps the answer as
 * [currentDeviceToken], as AppDelegate keeps APNs', then hands it to [received]; a token Firebase makes anew
 * ([tokenRefreshed], from `FirebaseMessagingService.onNewToken`) goes the same way. An answer to a question asked before
 * [unregisterForRemoteNotifications], which deletes the token, is dropped. On the main thread.
 */
class AndroidPushNotifications(
    private val context: Context,
    private val permission: NotificationPermission,
    /** Firebase Cloud Messaging's tokens, or a test's; null in a build without Firebase's settings. */
    private val tokens: PushTokens? = null,
    /**
     * What each token kept is handed to, as AppDelegate posts `.didReceiveAPNSToken` and the app's root view forwards
     * it: `ParcelStore.forwardNativePushToken` and `registerDeviceAlerts`, in the app's language.
     */
    private val received: (String) -> Unit = {},
) : PushNotifications {
    private var token: String? = null

    /** Raised by each [unregisterForRemoteNotifications]: an answer to a question asked before it is dropped. */
    private var registrations = 0

    override val available: Boolean get() = tokens != null

    override suspend fun authorizationStatus(): NotificationAuthorizationStatus = NotificationAuthorizationStatus.fromAndroid(
        sdkInt = Build.VERSION.SDK_INT,
        granted = granted(),
        askedBefore = permission.answered,
        shouldShowRationale = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && permission.rationale(),
        areNotificationsEnabled = enabled(),
    )

    /** Android 12 asks nothing; granted already, nothing is asked either; else the question, from the window on screen. */
    override suspend fun requestAuthorization(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || granted()) return enabled()
        return permission.request() && enabled()
    }

    override val answeredBefore: Boolean get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && permission.answered

    override fun registerForRemoteNotifications() {
        val tokens = tokens ?: return
        val asked = registrations
        tokens.token { answer ->
            // A failure is AppDelegate's `didFailToRegisterForRemoteNotificationsWithError`: nothing is kept, and the
            // store's wait for a token ends without one.
            if (asked == registrations) answer.onSuccess(::keep)
        }
    }

    /** A token Firebase made (`onNewToken`): kept and handed on, unless it is the one kept already. */
    fun tokenRefreshed(value: String) {
        if (tokens == null || value == token) return
        keep(value)
    }

    private fun keep(value: String) {
        token = value
        received(value)
    }

    override fun unregisterForRemoteNotifications() {
        val tokens = tokens ?: return
        registrations++
        tokens.delete()
    }

    override val currentDeviceToken: String? get() = token

    override fun clearDeviceToken() {
        token = null
    }

    private fun granted(): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
        ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED

    private fun enabled(): Boolean = NotificationManagerCompat.from(context).areNotificationsEnabled()
}
