package com.plhery.peekdeliverytracker.platform

import com.plhery.peekdeliverytracker.core.AuthenticationError
import com.plhery.peekdeliverytracker.core.WebAuthenticating

/** No browser to sign in with, as when none can open the page: the tests' sign-in, which opens nothing. */
object NoWebAuthentication : WebAuthenticating {
    override suspend fun authenticate(url: String, callbackScheme: String): String = throw AuthenticationError.InvalidResponse()
}
