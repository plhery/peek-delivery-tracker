package com.plhery.peekdeliverytracker.core

import java.time.Instant
import java.util.Locale
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.CoroutineScope

/** The offline lists in memory: a relaunched store given the same instance finds what the first one saved. */
internal class MemoryParcelCacheFiles : ParcelCacheFiles {
    private val files = ConcurrentHashMap<UUID, ByteArray>()

    val saved: Map<UUID, ByteArray>
        get() = HashMap(files)

    override fun read(userId: UUID): ByteArray? = files[userId]

    override fun write(userId: UUID, data: ByteArray) {
        files[userId] = data
    }

    override fun delete(userId: UUID) {
        files.remove(userId)
    }
}

/** The device's parcels in memory, as DeviceParcelTests keeps them. */
internal class MemoryDeviceParcelStorage : DeviceParcelStorage {
    var parcels: List<DeviceParcel> = emptyList()
    var keys: Map<String, String> = emptyMap()

    override fun load(): DeviceParcelStorage.Contents = DeviceParcelStorage.Contents(parcels, keys)

    override fun save(parcels: List<DeviceParcel>, keys: Map<String, String>) {
        this.parcels = parcels
        this.keys = keys
    }
}

/** The phone's notifications as a test answers for them; `calls` notes what the store asked, in order. */
internal class StubPushNotifications(
    var status: NotificationAuthorizationStatus = NotificationAuthorizationStatus.NotDetermined,
    var granted: Boolean = false,
    override var currentDeviceToken: String? = null,
) : PushNotifications {
    val calls = mutableListOf<String>()

    override suspend fun authorizationStatus(): NotificationAuthorizationStatus {
        calls += "status"
        return status
    }

    override suspend fun requestAuthorization(): Boolean {
        calls += "request"
        return granted
    }

    override fun registerForRemoteNotifications() {
        calls += "register"
    }

    override fun unregisterForRemoteNotifications() {
        calls += "unregister"
    }

    override fun clearDeviceToken() {
        calls += "clear"
        currentDeviceToken = null
    }
}

/** A NativeVerification on a phone without App Attest, which no test expects to show the browser check. */
internal fun verificationWithoutAppAttest(): NativeVerification {
    val unsupported = NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) })
    return NativeVerification(unsupported, TurnstilePresenter { throw AssertionError("Unexpected browser check") }, emptyMap(), {}, language = { AppLanguage.En })
}

/**
 * `ParcelStore(configuration:session:localizer:transport:device:deviceNotifications:)` as the Swift tests make it: the
 * account's client, the Live Activity revocations and the device's parcels all on `transport`, and `scope` a test's
 * `backgroundScope`, where what the store leaves to a `Task` runs. A relaunch passes the first store's `defaults` and
 * `cacheFiles` again, as the iPhone's tests share UserDefaults.standard and the cache's folder.
 */
internal fun testParcelStore(
    configuration: AppConfiguration,
    session: SessionStore,
    transport: HttpTransport,
    scope: CoroutineScope,
    defaults: UserDefaults = MemoryDefaults(),
    cacheFiles: ParcelCacheFiles = MemoryParcelCacheFiles(),
    localizer: Localizer = Localizer(AppLanguage.En),
    region: () -> Locale = { Locale.UK },
    device: DeviceParcels = DeviceParcels(
        if (configuration.mode == AppConfiguration.Mode.Api) DeviceParcelClient.api(configuration, transport, verificationWithoutAppAttest(), region) else null,
        MemoryDeviceParcelStorage(), PostcodeMemory(defaults), scope, region, language = { localizer.language },
    ),
    notifications: PushNotifications = StubPushNotifications(),
    deviceNotifications: DeviceNotificationAccess = DeviceNotificationAccess.system(notifications),
    revocations: SessionPersistence = MemorySessionPersistence(),
    widgetCenter: WidgetCenter = WidgetCenter {},
    exportFiles: ExportFiles = ExportFiles { name, _ -> throw AssertionError("Unexpected export $name") },
    analytics: (String, DeliveryAnalytics.Outcome?) -> Unit = { _, _ -> },
    now: () -> Instant = Instant::now,
): ParcelStore = ParcelStore(
    configuration = configuration,
    session = session,
    localizer = localizer,
    api = DeliveryApiClient(configuration, session, transport, verificationWithoutAppAttest(), deviceName = "Synthetic phone", region = region),
    device = device,
    liveActivityRevocations = LiveActivityRevocations(configuration, transport, revocations),
    defaults = defaults,
    deliveryWidgetStore = DeliveryWidgetSharedStore(defaults),
    cacheFiles = cacheFiles,
    notifications = notifications,
    widgetCenter = widgetCenter,
    exportFiles = exportFiles,
    region = region,
    scope = scope,
    deviceNotifications = deviceNotifications,
    analytics = analytics,
    now = now,
)

