package com.plhery.peekdeliverytracker.core

// From Authentication.swift, the errors the localizer explains and where a secret is kept. The session store comes
// with its own port.

/**
 * Where a secret is kept on this device: the Keychain on the iPhone (KeychainStore), the app's encrypted storage on
 * Android. The value is held as the bytes JsonEncoder.deliveryTracker writes, and read back with ApiJson.
 */
interface SessionPersistence {
    /** Throws when the value could not be saved. */
    fun save(data: ByteArray)

    /** Nil when nothing is saved, or when it cannot be read (a Keystore key lost, say): it never throws for that. */
    fun load(): ByteArray?

    /** Never throws: nothing left to delete is no failure. */
    fun delete()
}

sealed class AuthenticationError(message: String) : Exception(message) {
    class NotConfigured : AuthenticationError("Authentication is not configured.")
    class InvalidResponse : AuthenticationError("The authentication service returned an invalid response.")
    class MissingSession : AuthenticationError("The sign-in code did not create a session.")
    class OauthCancelled : AuthenticationError("Sign-in was cancelled.")
    class RequestFailed(val status: Int) : AuthenticationError("Sign-in failed ($status).")
    class SecureRequestFailed : AuthenticationError("A secure sign-in request could not be created.")
    class SessionSaveFailed : AuthenticationError("The secure session could not be saved.")
    class OtherAppleAccount : AuthenticationError("This Apple Account doesn't sign in to this Peek account.")
    class Server(val serverMessage: String) : AuthenticationError(serverMessage)
}
