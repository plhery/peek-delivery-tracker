package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.plhery.peekdeliverytracker.core.AppleSignInAuthorizing
import com.plhery.peekdeliverytracker.core.AppleSignInCredential
import com.plhery.peekdeliverytracker.core.AuthenticationError
import com.plhery.peekdeliverytracker.core.DeviceAttestation
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import com.plhery.peekdeliverytracker.core.PushNotifications
import com.plhery.peekdeliverytracker.core.WebAuthenticating

// What the app does not do yet, each in the way its interface allows for a phone that cannot: a refusal, a false or
// nothing at all. Each names the milestone that replaces it.

/**
 * Notifications never asked for and no token, until M6 brings POST_NOTIFICATIONS' request and Firebase Messaging. What
 * the phone allows is read from Android's own setting and permission ([NotificationAuthorizationStatus.fromAndroid]),
 * as Settings' Delivery updates shows it: nothing has asked yet, so on Android 13 and later a permission not granted
 * is still to be asked, and only the app's notifications turned off in the phone's settings are blocked.
 *
 * Its question asks nothing and answers what Android already allows, as iOS's answers at once for a choice already
 * made: a phone that allows them gets the iPhone's wait for a token that never comes (`native.apnsTokenError`), the
 * demo turns them on, and only a refusal keeps the invitation away (`dismissNotificationInvitation()`).
 */
class NoPushNotifications(private val context: Context) : PushNotifications {
    override suspend fun authorizationStatus(): NotificationAuthorizationStatus = NotificationAuthorizationStatus.fromAndroid(
        sdkInt = Build.VERSION.SDK_INT,
        granted = Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED,
        askedBefore = false,
        shouldShowRationale = false,
        areNotificationsEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled(),
    )

    override suspend fun requestAuthorization(): Boolean = authorizationStatus() == NotificationAuthorizationStatus.Authorized

    override fun registerForRemoteNotifications() {}

    override fun unregisterForRemoteNotifications() {}

    override val currentDeviceToken: String? get() = null

    override fun clearDeviceToken() {}
}

/** No attestation the server accepts (DeviceAttestation): unsupported until it takes Play Integrity, after M6. */
object NoDeviceAttestation : DeviceAttestation {
    override fun supported(): Boolean = false

    override suspend fun generateKey(): String = throw DeviceAttestation.InvalidKey()

    override suspend fun attest(keyId: String, clientDataHash: ByteArray): ByteArray = throw DeviceAttestation.InvalidKey()

    override suspend fun assert(keyId: String, clientDataHash: ByteArray): ByteArray = throw DeviceAttestation.InvalidKey()
}

/** No browser to sign in with, as when none can open the page, until M5 opens a Custom Tab. */
object NoWebAuthentication : WebAuthenticating {
    override suspend fun authenticate(url: String, callbackScheme: String): String = throw AuthenticationError.InvalidResponse()
}

/** Sign in with Apple cannot start, until M5 opens Apple's web sign-in. */
object NoAppleSignIn : AppleSignInAuthorizing {
    override suspend fun credential(nonce: String): AppleSignInCredential = throw AuthenticationError.InvalidResponse()
}
