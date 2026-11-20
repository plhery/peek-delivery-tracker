package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import com.plhery.peekdeliverytracker.BuildConfig
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.HttpTransport
import com.plhery.peekdeliverytracker.core.PushAddress
import com.plhery.peekdeliverytracker.core.PushInbox
import com.plhery.peekdeliverytracker.core.api.NativePushTransport
import com.plhery.peekdeliverytracker.core.api.PushSubscriptionKeys
import com.plhery.peekdeliverytracker.core.identifying
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.unifiedpush.android.connector.FailedReason
import org.unifiedpush.android.connector.PushService
import org.unifiedpush.android.connector.UnifiedPush
import org.unifiedpush.android.connector.data.PushEndpoint
import org.unifiedpush.android.connector.data.PushMessage
import org.unifiedpush.android.connector.data.ResolvedDistributor
import java.security.SecureRandom

/**
 * How the service reaches a phone without Google's services ([PushAddress]): through the UnifiedPush distributor app
 * the reader has (ntfy and the like), or else through the inbox the app reads about every quarter of an hour
 * ([InboxCheck]). The phone is named by an installation token made here, kept with the distributor's endpoint and Web
 * Push keys in preferences of their own.
 *
 * Each ask for the address ([UnifiedPushTokens.token], at every return to the app while notifications are on) answers
 * at once: the endpoint where the distributor gave one, the inbox otherwise. Without an endpoint the app registers with
 * the phone's distributor, its default or else the first, signed with the service's VAPID key, and checks the inbox
 * meanwhile; the endpoint the distributor answers with ([PeekPushService]) goes to the stores as a new address, as a
 * token Firebase makes anew does. A distributor that unregisters, fails or is uninstalled sends the phone back to the
 * inbox the same way.
 */
internal object PushRoute {
    private const val PREFERENCES = "push-route"

    /** Where 1.1.20 and earlier kept the route. */
    private const val LEGACY_PREFERENCES = "unifiedpush"
    private const val INSTALLATION = "installation"
    private const val ENDPOINT = "endpoint"
    private const val P256DH = "p256dh"
    private const val AUTH = "auth"

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val random = SecureRandom()

    /** Whether a registration with the distributor is under way: one at a time. */
    @Volatile
    private var registering = false

    private val distributorState = MutableStateFlow<String?>(null)

    /** The package of the distributor the pushes come through; null while they wait in the inbox. */
    val distributor: StateFlow<String?> = distributorState.asStateFlow()

    /** The phone's address now, its installation token made the first time. */
    @Synchronized
    fun address(context: Context): PushAddress {
        val preferences = preferences(context)
        if (!preferences.contains(INSTALLATION)) adoptLegacy(context, preferences)
        val token = preferences.getString(INSTALLATION, null) ?: newToken().also { preferences.edit { putString(INSTALLATION, it) } }
        val endpoint = preferences.getString(ENDPOINT, null)
        val p256dh = preferences.getString(P256DH, null)
        val auth = preferences.getString(AUTH, null)
        // `getAckDistributor` also tells the service an uninstalled distributor's registration is gone (onUnregistered).
        val distributor = if (endpoint != null) UnifiedPush.getAckDistributor(context) else null
        distributorState.value = distributor
        return if (endpoint != null && p256dh != null && auth != null && distributor != null) {
            PushAddress(token, NativePushTransport.Unifiedpush, endpoint, PushSubscriptionKeys(p256dh = p256dh, auth = auth))
        } else {
            PushAddress(token, NativePushTransport.Inbox)
        }
    }

    /**
     * The address to hand the stores, and the work it needs: the inbox checked, and, where the stores asked for it
     * ([registers]), the distributor asked for an endpoint.
     */
    fun asked(context: Context, registers: Boolean = true): String {
        val address = address(context)
        InboxCheck.schedule(context)
        if (address.transport == NativePushTransport.Inbox) {
            if (registers) register(context.applicationContext)
        }
        return address.text
    }

    fun acknowledged(context: Context, address: PushAddress) =
        preferences(context).getString("acknowledged", null) == address.text

    fun acknowledge(context: Context, route: PushAddress) {
        if (existingAddress(context) == route) preferences(context).edit { putString("acknowledged", route.text) }
    }

    fun existingAddress(context: Context): PushAddress? =
        preferences(context).getString(INSTALLATION, null)?.let { address(context) }

    /** The distributor's new endpoint, which the stores register in place of the inbox or the endpoint before. */
    fun endpointChanged(context: Context, endpoint: PushEndpoint) {
        val keys = endpoint.pubKeySet ?: return lost(context)
        preferences(context).edit {
            putString(ENDPOINT, endpoint.url)
            putString(P256DH, keys.pubKey)
            putString(AUTH, keys.auth)
        }
        refreshed(context)
    }

    /** No endpoint any more: the phone goes back to the inbox. */
    fun lost(context: Context) {
        val preferences = preferences(context)
        if (preferences.getString(ENDPOINT, null) == null) return
        preferences.edit {
            remove(ENDPOINT)
            remove(P256DH)
            remove(AUTH)
        }
        refreshed(context)
    }

    /** `deleteToken()`: the distributor's registration goes, and the next address is a new one. */
    fun delete(context: Context) {
        try {
            UnifiedPush.unregister(context)
        } catch (_: Exception) {
            // A registration the distributor already forgot.
        }
        preferences(context).edit { clear() }
        distributorState.value = null
        InboxCheck.cancel(context)
    }

    /**
     * Sends the new address to the stores, on the main thread, as `onNewToken`: dropped before they are made. A lost
     * endpoint is asked for again at the next return to the app, not straight after the distributor let it go.
     */
    private fun refreshed(context: Context) {
        val app = context.applicationContext as PeekApplication
        val address = asked(app, registers = false)
        InboxCheck.handover(app)
        ContextCompat.getMainExecutor(app).execute { app.pushTokenRefreshed(address) }
    }

    private fun register(context: Context) {
        if (registering) return
        val distributor = UnifiedPush.getSavedDistributor(context) ?: when (val resolved = UnifiedPush.resolveDefaultDistributor(context)) {
            is ResolvedDistributor.Found -> resolved.packageName
            ResolvedDistributor.ToSelect -> UnifiedPush.getDistributors(context).filter { it != context.packageName }.minOrNull()
            ResolvedDistributor.NoneAvailable -> null
        } ?: return
        registering = true
        scope.launch {
            try {
                val key = PushInbox.vapidKey(BuildSettings.configuration, transport) ?: return@launch
                UnifiedPush.saveDistributor(context, distributor)
                UnifiedPush.register(context, messageForDistributor = "Peek", vapid = key)
            } catch (_: Exception) {
                // No key from the service, or a distributor that refused it: the inbox carries on, and the next ask tries again.
            } finally {
                registering = false
            }
        }
    }

    /** The service's public requests, without the stores: no cookie, the app's user agent. */
    val transport: HttpTransport by lazy {
        OkHttpTransport(PeekHttp.client(MemoryCookieJar())).identifying(PeekHttp.userAgent(BuildConfig.VERSION_CODE))
    }

    /**
     * The route 1.1.20 and earlier kept under another name. An update on this phone carries its installation, endpoint
     * and keys on, so the service's inbox and registrations for them stay this phone's rather than a second address
     * starting beside them. A backup restored with a fresh install brought another phone's, which stay behind.
     */
    private fun adoptLegacy(context: Context, preferences: SharedPreferences) {
        val legacy = context.getSharedPreferences(LEGACY_PREFERENCES, Context.MODE_PRIVATE)
        val token = legacy.getString(INSTALLATION, null) ?: return
        if (updatedInPlace(context)) {
            preferences.edit {
                putString(INSTALLATION, token)
                for (key in listOf(ENDPOINT, P256DH, AUTH)) legacy.getString(key, null)?.let { putString(key, it) }
            }
        }
        legacy.edit { clear() }
    }

    /** 32 random bytes as 43 URL-safe base64 characters, the service's installation token. */
    private fun newToken(): String {
        val bytes = ByteArray(32).also(random::nextBytes)
        return Base64.encodeToString(bytes, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
    }

    private fun preferences(context: Context): SharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
}

/** [PushTokens] without Google's services: the address [PushRoute] gives, an endpoint or the inbox. */
internal class UnifiedPushTokens(private val context: Context) : PushTokens {
    override fun token(answer: (Result<String>) -> Unit) {
        answer(Result.success(PushRoute.asked(context)))
    }

    override fun delete() {
        PushRoute.delete(context)
    }
}

/**
 * The distributor's side of the pushes, which Firebase's service takes in Google Play's build: each message's data,
 * the same JSON object FCM carries, goes to the notifications the app posts ([AppNotifications.post]); each endpoint,
 * failure and unregistration to [PushRoute]. Not exported: the connector's receiver hands it the events inside the app.
 */
class PeekPushService : PushService() {
    override fun onNewEndpoint(endpoint: PushEndpoint, instance: String) {
        PushRoute.endpointChanged(this, endpoint)
    }

    override fun onMessage(message: PushMessage, instance: String) {
        if (!message.decrypted) return
        val data = try {
            val json = JSONObject(message.content.decodeToString())
            json.keys().asSequence().associateWith { json.optString(it) }
        } catch (_: Exception) {
            return
        }
        (application as PeekApplication).posted.post(data)
    }

    override fun onRegistrationFailed(reason: FailedReason, instance: String) {
        PushRoute.lost(this)
    }

    override fun onUnregistered(instance: String) {
        PushRoute.lost(this)
    }
}
