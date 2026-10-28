package com.plhery.peekdeliverytracker.core

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

// Analytics.swift. The iPhone's AnalyticsLifecycleObserver is a SwiftUI view; its logic is the class of that name
// here, which the app's root calls as its state changes.

/** One catalog shared with the web app; only these names can leave the device. */
@Serializable
data class AnalyticsCatalog(
    val screens: List<String>,
    val actions: List<String>,
    val operations: List<Operation>,
    val friendActions: Map<String, String>,
) {
    @Serializable
    data class Operation(val method: String, val path: String, val event: String)

    fun event(path: String, method: String, body: ByteArray?): String? {
        val path = path.characters().takeWhile { it != "?" && it != "#" }.joinToString("")
        if (path == "/api/friends" && method == "POST") {
            val action = body?.let(JsonSerialization::stringsOfObject)?.get("action") ?: return null
            return friendActions[action]
        }
        // The operations hold only ASCII literals and `[^/]`, searched as a Swift string is, over its Characters.
        return operations.firstOrNull { it.method == method && CatalogExpressions.nativeMatches(path, it.path) }?.event
    }

    companion object {
        val bundled: AnalyticsCatalog? by lazy {
            runCatching {
                Json { ignoreUnknownKeys = true }.decodeFromString(serializer(), SharedResources.text("Analytics.json"))
            }.getOrNull()
        }
    }
}

/**
 * The app's one DeliveryAnalytics (`DeliveryAnalytics.shared`). Call it on the main thread, as the iPhone's main actor
 * does, and give it a `scope` on that thread (`Dispatchers.Main`, not `.immediate`: a Swift `Task` runs after the code
 * that made it), where it sends.
 *
 * What the app gives it:
 * - `transport`: its own, as URLSession's `.ephemeral` is: no cache, cookies in memory only, none shared with the API
 *   client's. A request times out after 5 s (`HttpRequest.timeout`).
 * - `defaults`: the app's preferences, where the switch is saved under `PREFERENCE_KEY`. Only this class writes it.
 * - `platform`: the device it runs on, read once (see `Platform`). `version`: the app's version name, as
 *   `AppConfiguration.version`.
 * - `preferredLanguages`: the device's languages as BCP 47 tags, its first one first
 *   (`Resources.getSystem().configuration.locales`), as `Locale.preferredLanguages`; not the app's own language.
 * - `collects`: false on an emulator, which stays out of the counts as the iPhone's simulator and the Mac's Debug build do.
 */
class DeliveryAnalytics(
    private val app: AppConfiguration,
    private val transport: HttpTransport,
    private val defaults: UserDefaults,
    private val scope: CoroutineScope,
    private val platform: Platform,
    private val version: String,
    private val preferredLanguages: () -> List<String>,
    private val collects: Boolean = true,
    private val catalog: AnalyticsCatalog? = AnalyticsCatalog.bundled,
) {
    // `object(forKey:) as? Bool ?? true`: on until switched off.
    private val enabled: Boolean
        get() = defaults.bool(forKey = PREFERENCE_KEY) || !defaults.hasObject(forKey = PREFERENCE_KEY)

    fun setEnabled(value: Boolean) {
        defaults.set(value, forKey = PREFERENCE_KEY)
        if (!value) {
            queue.clear()
            cache = null
        } else {
            disabled = false
            lastView = ""
            scope.launch {
                start()
                view(screen)
            }
        }
    }

    /** What `/api/analytics/config` answers: Umami's collector and the app's property in it. Public ids, no keys. */
    data class Configuration(
        /** A URL's string, as `URL(string:)` reads it. */
        val endpoint: String,
        val hostname: String,
        val iosWebsite: String,
    ) {
        /** `valid(for:)`: collected over HTTPS only, with no credentials or query, for the server the app talks to. */
        fun valid(origin: String): Boolean {
            val endpoint = Url.parse(endpoint) ?: return false
            val origin = Url.parse(origin) ?: return false
            return endpoint.scheme == "https" && endpoint.host != null && endpoint.user == null && endpoint.password == null &&
                endpoint.path == "/api/send" && endpoint.query == null && endpoint.fragment == null &&
                origin.scheme == "https" && hostname.equalsCanonically(origin.host) && uuid(iosWebsite) != null
        }

        companion object {
            /**
             * `JSONDecoder().decode(Configuration?.self, from:)`: nil for JSON's null. Throws a `DecodingError` when the
             * body is no such object or its endpoint no URL. Read as Foundation reads JSON, where `decodeJson` is
             * kotlinx's: a key given twice keeps its first value, a comma may end the object, and a bare word is no value.
             * One difference stays: JSONDecoder checks a value it does not read for its shape alone, where this refuses
             * a body with a malformed number or string anywhere (`01`, `1e400`, a raw tab, a lone surrogate escape).
             */
            fun decode(data: ByteArray): Configuration? {
                if (jsonText(data)?.trim { it == ' ' || it == '\t' || it == '\n' || it == '\r' } == "null") return null
                val fields = JsonSerialization.stringsOfObject(data) ?: throw DecodingError()
                val endpoint = fields["endpoint"] ?: throw DecodingError()
                if (Url.parse(endpoint) == null) throw DecodingError()
                return Configuration(endpoint, fields["hostname"] ?: throw DecodingError(), fields["iosWebsite"] ?: throw DecodingError())
            }
        }
    }

    /** How an action went, as the catalog's events say it. */
    enum class Outcome(val rawValue: String) {
        Success("success"),
        Error("error"),
        Started("started"),
        Accepted("accepted"),
    }

    enum class Mode(val rawValue: String) {
        Anonymous("anonymous"),
        Demo("demo"),
        Account("account"),
    }

    /**
     * The device the app runs on, the iPhone's iphone, ipad and mac. The app reads it: a Chromebook first
     * (`PackageManager.hasSystemFeature("org.chromium.arc")`), then a tablet by its smallest width
     * (`Configuration.smallestScreenWidthDp >= 600`), else a phone. `os` is Umami's own name for the system.
     */
    enum class Platform(val rawValue: String, val device: String, val os: String) {
        Android("android", "mobile", "Android OS"),
        AndroidTablet("android-tablet", "tablet", "Android OS"),
        Chromebook("chromebook", "desktop", "Chrome OS"),
    }

    data class Event(
        val screen: String,
        val name: String?,
        val outcome: Outcome?,
        val mode: Mode,
    )

    private var configuration: Configuration? = null
    private var starting = false
    private var disabled = false
    private var started = false
    private var sending = false
    private var cache: String? = null
    private val queue = ArrayDeque<Event>()
    var screen: String = "welcome"
        private set
    private var mode = Mode.Anonymous
    private var lastView = ""

    suspend fun start(configuration: AppConfiguration = app) {
        if (!enabled || starting || started) return
        if (!collects) {
            disabled = true
            queue.clear()
            return
        }
        if (configuration.mode != AppConfiguration.Mode.Api) {
            disabled = true
            queue.clear()
            return
        }
        starting = true
        try {
            val request = HttpRequest(configuration.apiBaseUrl.appendingPath("api/analytics/config"), timeout = 5.0)
            val response = transport.data(request)
            val value = if (response.statusCode == 200) Configuration.decode(response.body) else null
            if (value == null || !value.valid(configuration.apiBaseUrl)) {
                disabled = true
                queue.clear()
                return
            }
            this.configuration = value
            disabled = false
            started = true
            action("app-open")
            flush()
        } catch (error: Exception) {
            queue.clear() // Try configuration again on next foreground.
            if (error is CancellationException) throw error
        } finally {
            starting = false
        }
    }

    fun view(next: String, mode: Mode? = null) {
        if (catalog?.screens?.any { it.equalsCanonically(next) } != true) return
        screen = next
        if (mode != null) this.mode = mode
        val key = "${this.mode.rawValue}:$next"
        if (key.equalsCanonically(lastView)) return
        lastView = key
        enqueue(Event(screen, null, null, this.mode))
    }

    fun action(name: String, outcome: Outcome? = null) {
        if (catalog?.actions?.any { it.equalsCanonically(name) } != true) return
        enqueue(Event(screen, name, outcome, mode))
    }

    private fun enqueue(event: Event) {
        if (!enabled || disabled || queue.size >= 30) return
        queue.addLast(event)
        flush()
    }

    private fun flush() {
        if (sending || configuration == null) return
        sending = true
        scope.launch {
            try {
                while (enabled && queue.isNotEmpty()) {
                    val configuration = configuration ?: break
                    val event = queue.removeFirst()
                    try {
                        val body = JsonObject(mapOf("type" to JsonPrimitive("event"), "payload" to payload(event, configuration)))
                        val request = HttpRequest(
                            url = Url.parse(configuration.endpoint)?.absoluteString ?: configuration.endpoint,
                            method = "POST",
                            headers = mapOf("Content-Type" to "application/json", "User-Agent" to userAgent(platform, version)),
                            body = JsonEncoder().encode(JsonElement.serializer(), body),
                            timeout = 5.0,
                        ).setting("x-umami-cache", cache)
                        val response = transport.data(request)
                        if (response.statusCode == 200) {
                            JsonSerialization.stringsOfObject(response.body)?.let { cache = it["cache"] }
                        }
                    } catch (error: Exception) {
                        // Drop failures; never block delivery actions or persist analytics.
                        if (error is CancellationException) throw error
                    }
                }
            } finally {
                sending = false
            }
        }
    }

    private fun payload(event: Event, configuration: Configuration): JsonObject =
        payload(event, configuration, platform, preferredLanguages().firstOrNull() ?: "en")

    companion object {
        const val PREFERENCE_KEY = "sdt.analytics.enabled"

        /** No account IDs, device IDs, tokens, query strings, referrers or user-entered text. */
        fun payload(event: Event, configuration: Configuration, platform: Platform, language: String): JsonObject {
            val properties = linkedMapOf<String, JsonElement>("platform" to JsonPrimitive(platform.rawValue), "mode" to JsonPrimitive(event.mode.rawValue))
            event.outcome?.let { properties["outcome"] = JsonPrimitive(it.rawValue) }
            val result = linkedMapOf<String, JsonElement>(
                "website" to JsonPrimitive(configuration.iosWebsite), "hostname" to JsonPrimitive(configuration.hostname),
                "url" to JsonPrimitive("/${event.screen}"), "title" to JsonPrimitive(event.screen),
                "language" to JsonPrimitive(language),
                "browser" to JsonPrimitive("Delivery Android"), "os" to JsonPrimitive(platform.os),
                "device" to JsonPrimitive(platform.device), "data" to JsonObject(properties),
            )
            event.name?.let { result["name"] = JsonPrimitive(it) }
            return JsonObject(result)
        }

        /**
         * Umami filters bare app user agents as bots. Keep the app suffix on a standard Chrome agent, in the reduced
         * form Chrome sends itself (Android 10 and model K whatever the phone); explicit payload fields identify native.
         */
        fun userAgent(platform: Platform, version: String): String = when (platform) {
            Platform.Android -> "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Mobile Safari/537.36"
            Platform.AndroidTablet -> "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36"
            Platform.Chromebook -> "Mozilla/5.0 (X11; CrOS x86_64 14541.0.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36"
        } + " DeliveryTracker/$version"
    }
}

/**
 * AnalyticsLifecycleObserver: keep telemetry observation separate from the app's lifecycle. The app's root calls it as
 * the iPhone's invisible view observes the stores; `scope` is the analytics' own.
 */
class AnalyticsLifecycleObserver(private val analytics: DeliveryAnalytics, private val scope: CoroutineScope) {
    /** The screen a parcel link's sheet covers, to return to when it closes. */
    private var beneathLink: String? = null
    private var shown: String? = null
    private var linkOpen: Boolean? = null

    /** `.task`: once, as the root first shows. */
    fun appeared() {
        scope.launch { analytics.start() }
    }

    /**
     * The two `onChange(…, initial: true)`, in their order: call it as the root first shows and whenever `screen`, `mode`
     * or whether a parcel link is open (`links.route != nil`) changes. Each part acts only on a change of its own.
     */
    fun update(screen: String, mode: DeliveryAnalytics.Mode, linkOpen: Boolean) {
        val key = screen + mode.rawValue
        if (key != shown) {
            shown = key
            if (beneathLink == null) {
                analytics.view(screen, mode)
            } else {
                beneathLink = screen
                analytics.view("parcel-link", mode)
            }
        }
        // A parcel link is one screen, whatever its address, over the screen it was opened on.
        if (linkOpen != this.linkOpen) {
            this.linkOpen = linkOpen
            val beneath = beneathLink
            if (linkOpen) {
                beneathLink = analytics.screen
                analytics.view("parcel-link", mode)
            } else if (beneath != null) {
                beneathLink = null
                analytics.view(beneath, mode)
            }
        }
    }

    /** The app came to the foreground (`scenePhase` became `.active`). */
    fun foreground() {
        analytics.action("app-foreground")
        scope.launch { analytics.start() }
    }

    fun languageChanged() = analytics.action("language-change")

    /** `.onOpenURL`: a link opened the app. */
    fun urlOpened() = analytics.action("deep-link-open")

    /** `.didOpenFriendNotification`: a friend's notification was opened. */
    fun friendNotificationOpened() = analytics.action("notification-open")

    companion object {
        fun mode(isDemo: Boolean, signedIn: Boolean): DeliveryAnalytics.Mode = when {
            isDemo -> DeliveryAnalytics.Mode.Demo
            signedIn -> DeliveryAnalytics.Mode.Account
            else -> DeliveryAnalytics.Mode.Anonymous
        }

        fun screen(invitationPresenting: Boolean, state: AuthenticationState, hasParcels: Boolean): String {
            if (invitationPresenting) return "invitation"
            return when (state) {
                AuthenticationState.Loading -> "welcome"
                // Nobody is signed in: the first screen, then the deliveries this phone follows itself.
                AuthenticationState.Welcome -> if (hasParcels) "deliveries" else "front-door"
                AuthenticationState.SignedOut, AuthenticationState.Unconfigured -> "sign-in"
                AuthenticationState.Demo, is AuthenticationState.SignedIn -> "deliveries"
            }
        }
    }
}
