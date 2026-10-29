package com.plhery.peekdeliverytracker.platform

import com.plhery.peekdeliverytracker.core.AppleSignInAuthorizing
import com.plhery.peekdeliverytracker.core.AppleSignInCredential
import com.plhery.peekdeliverytracker.core.AuthenticationError
import com.plhery.peekdeliverytracker.core.DeviceAttestation
import com.plhery.peekdeliverytracker.core.NativeVerificationFailed
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import com.plhery.peekdeliverytracker.core.PushNotifications
import com.plhery.peekdeliverytracker.core.TurnstilePresenter
import com.plhery.peekdeliverytracker.core.WebAuthenticating
import com.plhery.peekdeliverytracker.core.WidgetCenter

// What the app does not do yet, each in the way its interface allows for a phone that cannot: a refusal, a false or
// nothing at all. Each names the milestone that replaces it.

/** Notifications switched off, never asked and no token, until M6 brings POST_NOTIFICATIONS and Firebase Messaging. */
object NoPushNotifications : PushNotifications {
    override suspend fun authorizationStatus(): NotificationAuthorizationStatus = NotificationAuthorizationStatus.Denied

    override suspend fun requestAuthorization(): Boolean = false

    override fun registerForRemoteNotifications() {}

    override fun unregisterForRemoteNotifications() {}

    override val currentDeviceToken: String? get() = null

    override fun clearDeviceToken() {}
}

/** No widget to draw again until M6 adds the home screen widget. */
object NoWidgets : WidgetCenter {
    override fun reloadTimelines(ofKind: String) {}
}

/** No attestation the server accepts (DeviceAttestation): unsupported until it takes Play Integrity, after M6. */
object NoDeviceAttestation : DeviceAttestation {
    override fun supported(): Boolean = false

    override suspend fun generateKey(): String = throw DeviceAttestation.InvalidKey()

    override suspend fun attest(keyId: String, clientDataHash: ByteArray): ByteArray = throw DeviceAttestation.InvalidKey()

    override suspend fun assert(keyId: String, clientDataHash: ByteArray): ByteArray = throw DeviceAttestation.InvalidKey()
}

/** The browser check fails, as a sheet that could not load does, until M4 shows it while adding a parcel. */
object NoTurnstile : TurnstilePresenter {
    override suspend fun token(url: String): String = throw NativeVerificationFailed()
}

/** No browser to sign in with, as when none can open the page, until M5 opens a Custom Tab. */
object NoWebAuthentication : WebAuthenticating {
    override suspend fun authenticate(url: String, callbackScheme: String): String = throw AuthenticationError.InvalidResponse()
}

/** Sign in with Apple cannot start, until M5 opens Apple's web sign-in. */
object NoAppleSignIn : AppleSignInAuthorizing {
    override suspend fun credential(nonce: String): AppleSignInCredential = throw AuthenticationError.InvalidResponse()
}
