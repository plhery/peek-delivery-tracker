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
 * and [request] waits for the answer, which comes to the window made again after a turn as well. The answer counts
 * once given ([answered]), kept in the app's files that no backup carries, so a phone set up from another asks again;
 * a question dismissed without an answer, which Android 13 asks again, doesn't count. Whether Android would still ask
 * after a refusal ([rationale]) is read from the window on screen, or as last read while none is. On the main thread.
 */
class NotificationPermission(context: Context) {
    private class Window(val activity: ComponentActivity, val launcher: ActivityResultLauncher<String>)

    private val windows = mutableListOf<Window>()
    private val marker = File(context.noBackupFilesDir, MARKER)
    private var answer: CompletableDeferred<Boolean>? = null
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
        activity.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                windows -= window
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
     * when no window is on screen to ask from.
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    suspend fun request(): Boolean {
        val pending = answer ?: run {
            val window = front() ?: throw IllegalStateException("No window on screen to ask from")
            rationaleBefore = rationale()
            CompletableDeferred<Boolean>().also {
                answer = it
                window.launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
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
        val pending = answer
        answer = null
        pending?.complete(granted)
    }

    private companion object {
        const val MARKER = "notification-permission-answered"
    }
}

/**
 * The phone's notifications (`PushNotifications`): Android's permission, and no token yet.
 *
 * What the phone allows is read from POST_NOTIFICATIONS and the app's notification setting
 * ([NotificationAuthorizationStatus.fromAndroid]): on Android 13 and later not asked, may ask again, or denied once the
 * system no longer asks; on Android 12 the setting alone. The question ([requestAuthorization]) is asked from the
 * window on screen ([NotificationPermission]), only where the permission isn't granted yet.
 *
 * The token is absent until Firebase Cloud Messaging is set up: [registerForRemoteNotifications] gets none, so turning
 * notifications on ends as the iPhone's does when APNs never answers (`native.apnsTokenError`), and a guest's parcels
 * are signed up for none. Firebase plugs in here: [registerForRemoteNotifications] asks
 * `FirebaseMessaging.getInstance().token` and keeps the answer as [currentDeviceToken], then hands it to
 * `ParcelStore.forwardNativePushToken` and `registerDeviceAlerts` on the main thread, as `FirebaseMessagingService
 * .onNewToken` does with each new one; [unregisterForRemoteNotifications] calls `FirebaseMessaging.deleteToken()`. Its
 * `onMessageReceived` hands each push's data to [AppNotifications.post].
 */
class AndroidPushNotifications(private val context: Context, private val permission: NotificationPermission) : PushNotifications {
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

    override fun registerForRemoteNotifications() {}

    override fun unregisterForRemoteNotifications() {}

    override val currentDeviceToken: String? get() = null

    override fun clearDeviceToken() {}

    private fun granted(): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
        ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED

    private fun enabled(): Boolean = NotificationManagerCompat.from(context).areNotificationsEnabled()
}
