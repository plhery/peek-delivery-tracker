package com.plhery.peekdeliverytracker.platform

/**
 * What the pushes need of the service that reaches this phone, or of a test: its address in the stores' one string
 * ([com.plhery.peekdeliverytracker.core.PushAddress]), and its removal. Google Play's build asks Firebase Cloud
 * Messaging (FirebasePushTokens, src/play), F-Droid's its UnifiedPush distributor or its inbox (src/fdroid). Answers
 * come on the main thread.
 */
interface PushTokens {
    /** `FirebaseMessaging.getToken()`: this phone's address, or why there is none. */
    fun token(answer: (Result<String>) -> Unit)

    /** `FirebaseMessaging.deleteToken()`: the address takes no more pushes, and the next one asked for is new. */
    fun delete()
}
