package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.NativePushTransport
import com.plhery.peekdeliverytracker.core.api.PushConfigResponse
import com.plhery.peekdeliverytracker.core.api.PushInboxRequest
import com.plhery.peekdeliverytracker.core.api.PushInboxResponse
import com.plhery.peekdeliverytracker.core.api.PushSubscriptionKeys

/**
 * Where the service sends this phone's notifications, as the stores keep it in the one string they call the device's
 * token ([ParcelStore.currentDeviceToken], a link's alert token). Google Play's build hands them a Firebase Cloud
 * Messaging token, which is the address as it is. The build without Google's services names the phone by its
 * installation token ([token], 43 URL-safe base64 characters) and says how it is reached: `inbox <token>`, the inbox
 * the app reads about every quarter of an hour, or `unifiedpush <token> <endpoint> <p256dh> <auth>`, the endpoint and
 * Web Push keys of the distributor app the reader chose. An FCM token has no space, so a space tells them apart.
 *
 * A change of address is a change of the string, so the stores register it again as they do a token Firebase makes
 * anew; the installation token stays the same between the two, and the service keeps the phone's registrations
 * ([sameDevice]).
 */
data class PushAddress(
    val token: String,
    val transport: NativePushTransport = NativePushTransport.Fcm,
    val endpoint: String? = null,
    val keys: PushSubscriptionKeys? = null,
) {
    /** The address in the stores' one string. */
    val text: String
        get() = when (transport) {
            NativePushTransport.Inbox -> "${transport.rawValue} $token"
            NativePushTransport.Unifiedpush -> listOf(transport.rawValue, token, endpoint.orEmpty(), keys?.p256dh.orEmpty(), keys?.auth.orEmpty())
                .joinToString(" ")
            else -> token
        }

    companion object {
        /** The address [text] names: an FCM token where it has no space, or one that names none of the others. */
        fun of(text: String): PushAddress {
            val parts = text.split(' ')
            return when {
                parts.size == 2 && parts[0] == NativePushTransport.Inbox.rawValue ->
                    PushAddress(parts[1], NativePushTransport.Inbox)
                parts.size == 5 && parts[0] == NativePushTransport.Unifiedpush.rawValue ->
                    PushAddress(parts[1], NativePushTransport.Unifiedpush, parts[2], PushSubscriptionKeys(p256dh = parts[3], auth = parts[4]))
                else -> PushAddress(text)
            }
        }

        /** Whether two addresses name the same phone to the service: one token, however it is reached. */
        fun sameDevice(left: String, right: String): Boolean = of(left).token.equalsCanonically(of(right).token)
    }
}

/**
 * What the build without Google's services asks the service for outside the stores, from the background, as public
 * requests with no cookie and no `Authorization`, as the public parcel API's.
 */
object PushInbox {
    /**
     * The notifications waiting in an inbox ([PushAddress]'s `inbox`), which the service hands over once: each the data
     * Firebase Cloud Messaging would have carried, its keys as the service wrote them, oldest first. Asked with the
     * installation token alone.
     */
    suspend fun take(configuration: AppConfiguration, transport: HttpTransport, token: String): List<Map<String, String>> {
        val request = HttpRequest(
            configuration.apiBaseUrl.appendingPath("api/public/push/inbox"), "POST",
            linkedMapOf("Accept" to "application/json", "Content-Type" to "application/json"),
            JsonEncoder.deliveryTracker.encode(PushInboxRequest.serializer(), PushInboxRequest(token = token)),
            timeout = 30.0, handlesCookies = false,
        )
        return decoded(transport.data(request)) { decodeJson(PushInboxResponse.serializer(), it).messages }
    }

    /**
     * The service's VAPID public key, which the phone registers with its UnifiedPush distributor so that the endpoint
     * takes the service's messages alone: null where the service sends no Web Push. Public too, for a reader not signed in.
     */
    suspend fun vapidKey(configuration: AppConfiguration, transport: HttpTransport): String? {
        val request = HttpRequest(
            configuration.apiBaseUrl.appendingPath("api/push/config"), "GET", linkedMapOf("Accept" to "application/json"),
            timeout = 30.0, handlesCookies = false,
        )
        return decoded(transport.data(request)) { body -> decodeJson(PushConfigResponse.serializer(), body).takeIf { it.available }?.publicKey }
    }

    private inline fun <T> decoded(response: HttpResponse, decode: (ByteArray) -> T): T {
        if (response.statusCode == 429) throw DeliveryApiError.RateLimited(response.header("Retry-After")?.let(::swiftDouble) ?: 60.0)
        if (response.statusCode != 200) throw DeliveryApiError.ServiceFailed(response.statusCode)
        return try {
            decode(response.body)
        } catch (_: Exception) {
            throw DeliveryApiError.InvalidResponse()
        }
    }
}
