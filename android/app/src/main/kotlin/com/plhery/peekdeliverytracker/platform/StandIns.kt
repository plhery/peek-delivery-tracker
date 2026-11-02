package com.plhery.peekdeliverytracker.platform

import com.plhery.peekdeliverytracker.core.AppleSignInAuthorizing
import com.plhery.peekdeliverytracker.core.AppleSignInCredential
import com.plhery.peekdeliverytracker.core.AuthenticationError
import com.plhery.peekdeliverytracker.core.DeviceAttestation

// What the app does not do yet, each in the way its interface allows for a phone that cannot: a refusal, a false or
// nothing at all. Each names the milestone that replaces it.

/** No attestation the server accepts (DeviceAttestation): unsupported until it takes Play Integrity, after M6. */
object NoDeviceAttestation : DeviceAttestation {
    override fun supported(): Boolean = false

    override suspend fun generateKey(): String = throw DeviceAttestation.InvalidKey()

    override suspend fun attest(keyId: String, clientDataHash: ByteArray): ByteArray = throw DeviceAttestation.InvalidKey()

    override suspend fun assert(keyId: String, clientDataHash: ByteArray): ByteArray = throw DeviceAttestation.InvalidKey()
}

/** Sign in with Apple cannot start, until M5 opens Apple's web sign-in. */
object NoAppleSignIn : AppleSignInAuthorizing {
    override suspend fun credential(nonce: String): AppleSignInCredential = throw AuthenticationError.InvalidResponse()
}
