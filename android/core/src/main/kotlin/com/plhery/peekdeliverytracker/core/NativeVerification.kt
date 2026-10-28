package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import java.io.IOException
import java.security.MessageDigest
import java.time.Instant
import java.util.Base64
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

// NativeVerification.swift. App Attest is a `DeviceAttestation` the app gives (none that the server accepts on Android
// yet) and the browser check a `TurnstilePresenter` (a WebView, by NativeTurnstilePresenter's rules); what is asked,
// signed and sent, and in which order, is here.

class NativeVerificationFailed : Exception()

/**
 * The device's attestation service, which keeps the private key on the device: DCAppAttestService on the iPhone.
 * A key the device no longer has (a reinstall) or refuses fails with `InvalidKey`, and is dropped.
 *
 * The server verifies Apple App Attest alone. On Android `supported()` returns false until it accepts an Android
 * attestation (Play Integrity is the one planned): before then it refuses every registration and assertion, and each
 * refusal pauses attestation for 10 minutes, so the requests and attestations are spent for nothing and lookups reach
 * the browser check no sooner.
 */
interface DeviceAttestation {
    /** Whether this installation attests: the build enables it and the device supports it. */
    fun supported(): Boolean

    /** A new key's id. */
    suspend fun generateKey(): String

    /** The key's attestation of `clientDataHash`, the SHA-256 of the server's challenge; it registers the key. */
    suspend fun attest(keyId: String, clientDataHash: ByteArray): ByteArray

    /** The key's assertion of `clientDataHash`, the SHA-256 of a request's `NativeVerification.payload`. */
    suspend fun assert(keyId: String, clientDataHash: ByteArray): ByteArray

    /** `DCError.invalidKey`. */
    class InvalidKey : Exception()
}

/** Swift's `NativeAttestProvider`, an attestation made of closures; tests sign synthetic requests with one. */
fun NativeAttestProvider(
    supported: () -> Boolean,
    generateKey: suspend () -> String,
    attest: suspend (String, ByteArray) -> ByteArray,
    assert: suspend (String, ByteArray) -> ByteArray,
): DeviceAttestation = object : DeviceAttestation {
    override fun supported(): Boolean = supported()

    override suspend fun generateKey(): String = generateKey()

    override suspend fun attest(keyId: String, clientDataHash: ByteArray): ByteArray = attest(keyId, clientDataHash)

    override suspend fun assert(keyId: String, clientDataHash: ByteArray): ByteArray = assert(keyId, clientDataHash)
}

@Serializable
data class NativeAttestRecord(
    @SerialName("keyID") val keyId: String,
    @SerialName("appID") val appId: String,
    val registered: Boolean,
)

/**
 * Shows the browser check at `url` and gives the token it posts: a sheet the app draws by NativeTurnstilePresenter's
 * rules. A sheet the person cancels throws a CancellationException, one that fails or times out NativeVerificationFailed.
 */
fun interface TurnstilePresenter {
    suspend fun token(url: String): String
}

/**
 * Serialize signing through the response: assertions arriving out of order invalidate their counters. `records` are
 * the keys this installation registered, by base URL, as `save` last kept them; `language` is the app's
 * (`AppLanguage.current`), which the browser check is shown in.
 *
 * The app makes one, as the iPhone's `NativeVerification.shared`, and gives that one to every client
 * (`DeliveryApiClient`, `DeviceParcelClient.api`): its lock is what keeps assertions in order, and its records are
 * the installation's, so a second instance would sign alongside it, out of order, and save over its records.
 */
class NativeVerification(
    private val provider: DeviceAttestation,
    private val present: TurnstilePresenter,
    records: Map<String, NativeAttestRecord>,
    private val save: (Map<String, NativeAttestRecord>) -> Unit,
    private val language: () -> AppLanguage,
    private val now: () -> Instant = Instant::now,
) {
    /** Keeps the records where the iPhone keeps them in its Keychain, as JSON. */
    constructor(
        provider: DeviceAttestation,
        present: TurnstilePresenter,
        persistence: SessionPersistence,
        language: () -> AppLanguage,
        now: () -> Instant = Instant::now,
    ) : this(
        provider, present,
        savedRecords(persistence),
        { persistence.save(JsonEncoder.deliveryTracker.encode(recordsFormat, it)) },
        language, now,
    )

    @Serializable
    private class Configuration(val appAttestAppId: String? = null, val turnstile: Boolean)

    @Serializable
    private class Challenge(val challenge: String)

    @Serializable
    private class Proof(val proof: String, val expiresAt: Double)

    // Changed and saved under its own monitor, never across a suspension: a signed-in request drops a refused key
    // without waiting for `lock`, which a browser check may hold for minutes.
    private val records = HashMap(records)
    private val configurations = ConcurrentHashMap<String, Pair<Configuration, Instant>>()
    private val proofs = ConcurrentHashMap<String, Proof>()
    private val pauseAttestUntil = ConcurrentHashMap<String, Instant>()
    private val lock = Mutex()

    /** Signed-in requests waiting for their signature or their deadline, whichever comes first. */
    private val accountWaiters = ConcurrentHashMap<UUID, CompletableDeferred<HttpRequest?>>()

    /** Registers this installation's key ahead of the first lookup, at launch. */
    suspend fun prepare(baseUrl: String, using: HttpTransport) {
        if (!provider.supported()) return
        lock.withLock {
            try {
                val config = configuration(baseUrl, using)
                config.appAttestAppId?.let { registeredKey(baseUrl, it, using) }
            } catch (error: Exception) {
                throwIfCancelled(error)
                forgetInvalidKey(error, baseUrl)
                pauseAttestUntil[baseUrl] = now().plusSeconds(600)
            }
        }
    }

    suspend fun send(original: HttpRequest, baseUrl: String, allowPresentation: Boolean = true, using: HttpTransport): HttpResponse =
        lock.withLock {
            val transport = using
            currentCoroutineContext().ensureActive()
            val base = baseUrl
            var request = original.setting("X-Native-Verification", "1")
            proofs[base]?.let { proof ->
                if (proof.expiresAt > millisecondsSince1970(now()) + 5_000) request = request.setting("X-Lookup-Proof", proof.proof)
            }
            val appId = configurations[base]?.first?.appAttestAppId
            if (appId != null && record(base)?.let { it.appId == appId && it.registered } == true && provider.supported() && mayAttest(base)) {
                try {
                    request = signed(request, baseUrl, appId, transport)
                } catch (error: Exception) {
                    throwIfCancelled(error)
                    forgetInvalidKey(error, base)
                    pauseAttestUntil[base] = now().plusSeconds(600)
                }
            }
            // The initial probe also lets installations work when protection is disabled.
            val first = transport.data(request)
            if (!needsVerification(first)) return first
            proofs.remove(base)
            request = request.setting("X-Lookup-Proof", null)
            val config = configuration(baseUrl, transport)
            val attestAppId = config.appAttestAppId
            if (provider.supported() && attestAppId != null && mayAttest(base)) {
                try {
                    request = signed(request, baseUrl, attestAppId, transport)
                    val result = transport.data(request)
                    if (!needsVerification(result)) return result
                    // A reinstall, expired registry entry or rejected key gets a new key after a short backoff.
                    changeRecords { it.remove(base) }
                } catch (error: Exception) {
                    throwIfCancelled(error)
                    forgetInvalidKey(error, base)
                    // Apple or the network can be unavailable; the browser check remains usable.
                }
                pauseAttestUntil[base] = now().plusSeconds(600)
            }
            // Detection runs while typing; only an explicit lookup may interrupt with a sheet.
            if (!config.turnstile || !allowPresentation) throw NativeVerificationFailed()
            for (name in listOf("X-App-Attest-Key-Id", "X-App-Attest-Challenge", "X-App-Attest-Assertion")) {
                request = request.setting(name, null)
            }
            val page = UrlComponents.parse(baseUrl.appendingPath("api/public/native/turnstile"))!!
            page.queryItems = listOf(UrlComponents.QueryItem("language", language().rawValue))
            val token = present.token(page.url!!)
            currentCoroutineContext().ensureActive()
            val proofData = api(baseUrl, "api/public/verification", mapOf("token" to token), transport)
            val proof = decodeJson(Proof.serializer(), proofData)
            proofs[base] = proof
            request = request.setting("X-Lookup-Proof", proof.proof)
            val result = transport.data(request)
            if (needsVerification(result)) {
                proofs.remove(base)
                throw NativeVerificationFailed()
            }
            result
        }

    /**
     * Signs a signed-in request with this installation's registered key and sends it. A device that cannot sign, a
     * failure or a signature not ready within `limit` sends it unsigned: account budgets still apply, so these
     * requests never open the browser check or register a key. A refusal gets one fresh signature; a signed request
     * refused again drops the key for the next launch to replace.
     */
    suspend fun sendSignedIn(original: HttpRequest, baseUrl: String, limit: Duration = 2.seconds, using: HttpTransport): HttpResponse {
        val first = attemptSignedIn(original, baseUrl, limit, using)
        if (!needsVerification(first.response)) return first.response
        currentCoroutineContext().ensureActive()
        val second = attemptSignedIn(original, baseUrl, limit, using)
        if (second.signed && needsVerification(second.response)) changeRecords { it.remove(baseUrl) }
        return second.response
    }

    private class Attempt(val signed: Boolean, val response: HttpResponse)

    private suspend fun attemptSignedIn(original: HttpRequest, baseUrl: String, limit: Duration, transport: HttpTransport): Attempt {
        val request = original.setting("X-Native-Verification", "1")
        val id = UUID.randomUUID()
        val waiter = CompletableDeferred<HttpRequest?>()
        accountWaiters[id] = waiter
        // Swift's detached tasks: the signer may outlive this request once the deadline has sent it unsigned.
        val detached = CoroutineScope(currentCoroutineContext().minusKey(Job))
        detached.launch { signForAccount(id, request, baseUrl, transport) }
        val deadline = detached.launch {
            delay(limit)
            answer(id, null)
        }
        val signed = try {
            waiter.await()
        } catch (error: CancellationException) {
            answer(id, null)
            deadline.cancel()
            // A signature that came as this was cancelled holds the lock for a request that is never sent.
            if (withContext(NonCancellable) { waiter.await() } != null) lock.unlock()
            throw error
        }
        deadline.cancel()
        if (signed == null) return Attempt(false, transport.data(request))
        // The signer holds the lock until this response, so assertions reach the server in order.
        try {
            return Attempt(true, transport.data(signed))
        } finally {
            lock.unlock()
        }
    }

    private suspend fun signForAccount(id: UUID, request: HttpRequest, baseUrl: String, transport: HttpTransport) {
        if (!provider.supported()) {
            answer(id, null)
            return
        }
        lock.lock()
        // Handed over with the signature, the lock is the request's to release after its response.
        var handedOver = false
        try {
            // Once the deadline has sent the request unsigned, no assertion is spent on it.
            if (accountWaiters.containsKey(id)) {
                val signed = accountSignature(request, baseUrl, transport)
                handedOver = signed != null && answer(id, signed)
            }
        } finally {
            if (!handedOver) {
                answer(id, null)
                lock.unlock()
            }
        }
    }

    private suspend fun accountSignature(request: HttpRequest, baseUrl: String, transport: HttpTransport): HttpRequest? {
        val base = baseUrl
        if (!mayAttest(base)) return null
        val appId = try {
            configuration(baseUrl, transport).appAttestAppId
        } catch (_: Exception) {
            null
        } ?: return null
        val record = record(base)
        if (record?.appId != appId || !record.registered) return null
        return try {
            signed(request, baseUrl, appId, transport)
        } catch (error: Exception) {
            forgetInvalidKey(error, base)
            pauseAttestUntil[base] = now().plusSeconds(600)
            null
        }
    }

    private fun answer(id: UUID, request: HttpRequest?): Boolean {
        val waiter = accountWaiters.remove(id) ?: return false
        waiter.complete(request)
        return true
    }

    private fun forgetInvalidKey(error: Exception, base: String) {
        if (error is DeviceAttestation.InvalidKey) changeRecords { it.remove(base) }
    }

    private fun record(base: String): NativeAttestRecord? = synchronized(records) { records[base] }

    /** Changes the records and saves them at once. A save that fails keeps them in memory, as the iPhone's `try?` does. */
    private fun changeRecords(change: (MutableMap<String, NativeAttestRecord>) -> Unit) {
        synchronized(records) {
            change(records)
            try {
                save(records.toMap())
            } catch (_: Exception) {
            }
        }
    }

    private fun mayAttest(base: String): Boolean = (pauseAttestUntil[base] ?: Instant.MIN) <= now()

    private suspend fun signed(original: HttpRequest, baseUrl: String, appId: String, transport: HttpTransport): HttpRequest {
        val key = registeredKey(baseUrl, appId, transport)
        val challenge = challenge(baseUrl, key, "assertion", transport)
        val payload = payload(original, challenge)
        val assertion = provider.assert(key, sha256(payload.wellFormed().toByteArray(Charsets.UTF_8)))
        return original.setting("X-App-Attest-Key-Id", key)
            .setting("X-App-Attest-Challenge", challenge)
            .setting("X-App-Attest-Assertion", Base64.getEncoder().encodeToString(assertion))
    }

    private suspend fun configuration(baseUrl: String, transport: HttpTransport): Configuration {
        val base = baseUrl
        configurations[base]?.let { (saved, until) -> if (until > now()) return saved }
        val data = api(baseUrl, "api/public/native/verification", transport = transport)
        val config = decodeJson(Configuration.serializer(), data)
        configurations[base] = config to now().plusSeconds(600)
        return config
    }

    private suspend fun registeredKey(baseUrl: String, appId: String, transport: HttpTransport): String {
        val base = baseUrl
        synchronized(records) { if (records[base]?.appId != appId) records.remove(base) }
        if (record(base) == null) {
            val keyId = provider.generateKey()
            changeRecords { it[base] = NativeAttestRecord(keyId = keyId, appId = appId, registered = false) }
        }
        var record = record(base) ?: throw NativeVerificationFailed()
        if (!record.registered) {
            val nonce = challenge(baseUrl, record.keyId, "attestation", transport)
            val attestation = provider.attest(record.keyId, sha256(nonce.wellFormed().toByteArray(Charsets.UTF_8)))
            api(
                baseUrl, "api/public/native/verification",
                linkedMapOf("keyId" to record.keyId, "challenge" to nonce, "attestation" to Base64.getEncoder().encodeToString(attestation)),
                transport,
            )
            record = record.copy(registered = true)
            changeRecords { it[base] = record }
        }
        return record.keyId
    }

    private suspend fun challenge(baseUrl: String, key: String, purpose: String, transport: HttpTransport): String {
        val data = api(baseUrl, "api/public/native/challenge", linkedMapOf("keyId" to key, "purpose" to purpose), transport)
        return decodeJson(Challenge.serializer(), data).challenge
    }

    /**
     * A request to the verification API, never cached and without cookies. Its body is an object of strings, whose keys
     * JSONSerialization writes in an order of its own each launch; here they go in the order given.
     */
    private suspend fun api(baseUrl: String, path: String, body: Map<String, String>? = null, transport: HttpTransport): ByteArray {
        val headers = linkedMapOf("Accept" to "application/json")
        if (body != null) headers["Content-Type"] = "application/json"
        val request = HttpRequest(
            url = baseUrl.appendingPath(path),
            method = if (body != null) "POST" else "GET",
            headers = headers,
            body = body?.let(::jsonObject),
            timeout = 15.0,
            handlesCookies = false,
        )
        val response = transport.data(request)
        if (response.statusCode !in 200 until 300) throw NativeVerificationFailed()
        return response.body
    }

    companion object {
        private val recordsFormat = MapSerializer(String.serializer(), NativeAttestRecord.serializer())

        /** The records as the iPhone's Keychain store reads them, with `JSONDecoder.deliveryTracker`; none if unreadable. */
        internal fun savedRecords(persistence: SessionPersistence): Map<String, NativeAttestRecord> =
            persistence.load()?.let { runCatching { decodeSwiftKeys(recordsFormat, it) }.getOrNull() } ?: emptyMap()

        /** What an assertion signs: the challenge, the method, the URL's path and the SHA-256 of the body, in hex. */
        fun payload(request: HttpRequest, challenge: String): String {
            val hash = sha256(request.body ?: ByteArray(0)).joinToString("") { "%02x".format(it) }
            return "native-request-v1\n$challenge\n${request.method}\n${Url.parse(request.url)?.path ?: ""}\n$hash"
        }

        /**
         * A cancelled request ends as a cancellation, as does one whose coroutine was cancelled while its call ended in
         * OkHttp's IOException, as URLSession ends one in URLError.cancelled. A timeout of the call's own is a failure.
         */
        private suspend fun throwIfCancelled(error: Exception) {
            if (error is CancellationException || error is IOException) currentCoroutineContext().ensureActive()
            if (error is CancellationException && error !is TimeoutCancellationException) throw error
        }

        fun needsVerification(response: HttpResponse): Boolean =
            response.statusCode == 403 && response.header("X-Lookup-Verification") == "required"

        private fun sha256(data: ByteArray): ByteArray = MessageDigest.getInstance("SHA-256").digest(data)

        /** `Date().timeIntervalSince1970 * 1000`, as the proof's expiry is given. */
        private fun millisecondsSince1970(date: Instant): Double = (date.epochSecond + date.nano / 1_000_000_000.0) * 1000
    }
}

/**
 * NativeTurnstilePresenter's rules for the sheet that shows the browser check, which the app draws in a WebView over
 * whatever is in front: only an https page opens, one sheet at a time; only a message from the page's own main frame
 * counts; and the sheet goes nowhere but its own page and the check's frames. After `timeoutSeconds` it fails, as does
 * a page that does not load or whose renderer goes away (NativeVerificationFailed); Cancel, or closing the sheet,
 * throws a CancellationException.
 *
 * The page is the iPhone's, as the server serves it, and posts each message only through
 * `window.webkit.messageHandlers.peekVerification.postMessage({type, token})`. So in the WebView:
 * - Messages: `WebViewCompat.addWebMessageListener` adds an object, say `peekBridge`, and
 *   `WebViewCompat.addDocumentStartJavaScript` a script that defines the handler on it, both for the page's origin
 *   alone: `window.webkit={messageHandlers:{peekVerification:{postMessage:m=>peekBridge.postMessage(JSON.stringify(m))}}}`.
 *   Without `WebViewFeature.WEB_MESSAGE_LISTENER` and `DOCUMENT_START_SCRIPT` the check fails at once. The listener
 *   gives `answer` its `sourceOrigin`'s scheme and host, `isMainFrame` and `body(message.data)`, and for the frame's
 *   URL, which Android does not report, `webView.url` read there on the main thread: the main frame's document, the
 *   only frame whose messages count. `answer` ignores a message whose frame URL is nil or has a path not the page's.
 * - Navigation: `shouldOverrideUrlLoading` lets the main frame go only where `allows(page, url, true)`. It does not see
 *   a frame's own loads (an iframe's first, for one), so `shouldInterceptRequest` answers every request not for the
 *   main frame that `allows(page, url, false)` refuses with an empty 403: what the page and the check load comes from
 *   challenges.cloudflare.com. Multiple windows stay off.
 */
object NativeTurnstilePresenter {
    const val timeoutSeconds = 90L

    /** What a message from the page says. */
    sealed class Answer {
        data class Token(val token: String) : Answer()

        data object Failed : Answer()
    }

    /** Whether the check may open at `url`. */
    fun opens(url: String): Boolean = Url.parse(url)?.scheme == "https"

    /**
     * A message posted by a frame: its origin's scheme and host, the URL of the frame's document and the message body.
     * Nil for one to ignore.
     */
    fun answer(page: String, isMainFrame: Boolean, originScheme: String, originHost: String, frameUrl: String?, body: Map<String, Any?>?): Answer? {
        val shown = Url.parse(page) ?: return null
        if (!isMainFrame || originScheme != shown.scheme || originHost != shown.host) return null
        if (frameUrl?.let(Url::parse)?.path != shown.path || body == null) return null
        val token = body["token"] as? String
        if (body["type"] as? String == "token" && token != null && token.isNotEmpty() && token.characterCount <= 2048) return Answer.Token(token)
        if (body["type"] as? String == "error") return Answer.Failed
        return null
    }

    /**
     * A message's body as the iPhone's WebKit gives it, from the JSON the page's handler forwards: an object's strings
     * are Strings, its other values something else; nil for anything but an object.
     */
    fun body(message: String?): Map<String, Any?>? {
        val value = message?.let { runCatching { ApiJson.parseToJsonElement(it) }.getOrNull() } as? JsonObject ?: return null
        return value.mapValues { (_, item) -> if (item is JsonPrimitive && item.isString) item.content else item }
    }

    /** Whether the sheet may load `url`, in its main frame or (`targetIsMainFrame` false) in a frame of the page; nil is a new window. */
    fun allows(page: String, url: String?, targetIsMainFrame: Boolean?): Boolean {
        val target = url?.let(Url::parse) ?: return false
        val shown = Url.parse(page) ?: return false
        val own = target.scheme == shown.scheme && target.host == shown.host && target.port == shown.port && target.path == shown.path
        val child = targetIsMainFrame == false &&
            ((target.scheme == "https" && target.host == "challenges.cloudflare.com") || target.scheme == "about")
        return own || child
    }
}
