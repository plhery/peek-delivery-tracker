package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.NotificationPreferencesClock
import com.plhery.peekdeliverytracker.core.api.NotificationPreferencesDistance
import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import com.plhery.peekdeliverytracker.core.api.PackageSeenResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.io.IOException
import java.time.Instant
import java.time.ZoneId
import java.util.Locale
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * "New since you last looked" (#72): the dot on a parcel with a scan its reader hasn't seen, for an account (`news_at`
 * against the server's `seen_at`) and for a guest (against the `news_at` this phone last saw).
 */
class NewUpdateTests {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://news.test", supabaseUrl = "https://news.test", supabasePublishableKey = "public",
        googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "news.test",
    )
    private val now = Instant.parse("2026-09-08T12:00:00Z")
    private val parcelId = UUID.randomUUID()

    // Moments as the server writes them: microseconds and an offset, never compared with the phone's clock.
    private val opened = "2026-09-08T09:00:00.250000+00:00"
    private val scanned = "2026-09-08T10:15:30.123456+00:00"
    private val seenNow = "2026-09-08T11:00:00.654321+00:00"

    /** The account's side: one parcel, its moments, its preferences, and the seen calls it was sent. */
    private inner class Server {
        var newsAt: String? = scanned
        var seenAt: String = opened
        var markNewUpdates = true
        var seenFails = false
        val seenCalls = mutableListOf<String>()
        val preferenceSaves = mutableListOf<Boolean?>()

        // The account's other preferences, its clock and units as this phone writes them unless a test says otherwise.
        var clock: NotificationPreferencesClock? = NotificationPreferencesClock.TwentyFour
        var distance: NotificationPreferencesDistance? = NotificationPreferencesDistance.Km
        var keepsUnits = true
        var emailOnDelivery: Boolean? = null
        var emailAvailable: Boolean? = null

        /** The calls about the preferences, each its method and what a save sent. */
        val preferenceCalls = mutableListOf<Pair<String, JsonObject?>>()

        /** Run before each call about the preferences is answered: it may hold the call, or fail it. */
        var beforePreferences: suspend (HttpRequest) -> Unit = {}

        fun parcel() = Parcel(
            id = parcelId, trackingNumber = "TESTPARCEL123789", label = "Synthetic parcel", carrier = CarrierId.Dhl,
            createdAt = "2026-09-07T08:00:00Z", syncStatus = SyncStatus.Ok, notificationsMuted = false, lastSyncedAt = "2026-09-08T10:16:00Z",
            newsAt = newsAt, seenAt = seenAt,
        )

        private fun preferences() = NotificationPreferences(
            enabledStages = NotificationPreset.Important.stages, timezone = "Europe/Paris", emailOnDelivery = emailOnDelivery,
            emailAvailable = emailAvailable, markNewUpdates = markNewUpdates, clock = clock, distance = distance,
        )

        suspend fun answer(request: HttpRequest): HttpResponse = when {
            request.path == "/api/packages" && request.method == "GET" -> json(PackageListResponse(listOf(parcel())))
            request.path == "/api/packages/${parcelId.uuidString}/seen" && request.method == "POST" -> {
                seenCalls += request.header("Authorization").orEmpty()
                if (seenFails) {
                    jsonResponse(500, """{"error":"unavailable"}""")
                } else {
                    seenAt = seenNow
                    jsonResponse(200, JsonEncoder.deliveryTracker.encode(PackageSeenResponse.serializer(), PackageSeenResponse(seenNow)).decodeToString())
                }
            }
            request.path == "/api/push/preferences" && request.method in setOf("GET", "PATCH") -> preferences(request)
            else -> throw AssertionError("Unexpected request ${request.method} ${request.url}")
        }

        private suspend fun preferences(request: HttpRequest): HttpResponse {
            val sent = request.body?.let { Json.parseToJsonElement(it.decodeToString()).jsonObject }
            preferenceCalls += request.method to sent
            beforePreferences(request)
            if (request.method == "PATCH") {
                val choice = sent?.get("markNewUpdates")?.jsonPrimitive?.boolean
                preferenceSaves += choice
                choice?.let { markNewUpdates = it }
                sent?.get("emailOnDelivery")?.jsonPrimitive?.boolean?.let { emailOnDelivery = it }
                if (keepsUnits) {
                    sent?.get("clock")?.jsonPrimitive?.content?.let { clock = NotificationPreferencesClock.fromRawValue(it) }
                    sent?.get("distance")?.jsonPrimitive?.content?.let { distance = NotificationPreferencesDistance.fromRawValue(it) }
                }
            }
            return preferenceJson()
        }

        private fun json(list: PackageListResponse) =
            jsonResponse(200, JsonEncoder.deliveryTracker.encode(PackageListResponse.serializer(), list).decodeToString())

        private fun preferenceJson() =
            jsonResponse(200, JsonEncoder.deliveryTracker.encode(NotificationPreferences.serializer(), preferences()).decodeToString())
    }

    /** A device signed in to the account the server holds, with its own session, settings and cache. */
    private suspend fun TestScope.signedIn(
        server: Server, defaults: UserDefaults = MemoryDefaults(), localizer: Localizer = Localizer(AppLanguage.En), account: UUID = accountId,
        widgetCenter: WidgetCenter = WidgetCenter {},
    ): ParcelStore {
        val transport = RecordingTransport()
        val session = testSessionStore(configuration, backgroundScope, transport = transport, now = { now })
        val user = AuthUser(id = account, email = "reader@example.com", isAnonymous = false)
        val auth = AuthSession(
            accessToken = "token-${UUID.randomUUID()}", tokenType = "bearer", expiresIn = 3600, expiresAt = now.epochSecond + 3600,
            refreshToken = "refresh", user = user,
        )
        transport.handler = { jsonResponse(200, JsonEncoder.deliveryTracker.encode(AuthSession.serializer(), auth).decodeToString()) }
        session.verifyCode(email = "reader@example.com", code = "123456")
        transport.handler = { server.answer(it) }
        val store = testParcelStore(
            configuration, session, transport, backgroundScope, defaults, localizer = localizer, widgetCenter = widgetCenter, now = { now },
        )
        store.setDeliveryLiveActivitiesEnabled(false)
        return store
    }

    private val accountId = UUID.randomUUID()

    private val ParcelStore.shown: Parcel get() = parcels.single { it.id == parcelId }
    private val ParcelStore.dotShown: Boolean get() = shown.marksNewUpdate(markNewUpdatesFlow.value)

    // A guest's parcel, read from the public parcel API.
    private val key = "k".repeat(43)
    private val linkId = "22222222222B"

    private fun publicPackage(newsAt: String?, scans: Int = 1, sync: SyncStatus = SyncStatus.Ok, lastSyncedAt: String? = "2026-09-08T10:16:00Z") =
        PublicPackage(
            id = parcelId, trackingNumber = "TESTPARCEL123789", label = "", carrier = CarrierId.Dhl, createdAt = "2026-09-07T08:00:00Z",
            lastSyncedAt = lastSyncedAt, syncStatus = sync, carrierData = PublicPackageCarrierData(), notificationsMuted = false,
            trackingEvents = (0 until scans).map {
                TrackingEvent(id = UUID.randomUUID(), packageId = parcelId, stage = TrackingStage.InTransit, description = "", occurredAt = "2026-09-08T0${it}:00:00Z")
            },
            newsAt = newsAt,
        )

    private fun response(pkg: PublicPackage) = PublicParcelResponse(
        link = ParcelLink(
            id = linkId, role = ParcelLinkRole.Owner, kind = ParcelLinkKind.Lookup, createdAt = "2026-09-07T08:00:00Z", numberShown = true,
            canKeep = true, showNumber = false, gift = false, shared = true,
        ),
        parcel = pkg,
    )

    private fun guestClient(read: () -> PublicParcelResponse?) = DeviceParcelClient(
        lookup = { throw AssertionError("Unexpected lookup") },
        read = { _, _ -> read() },
        forget = { _, _ -> throw AssertionError("Unexpected forget") },
        update = { _, _, _ -> throw AssertionError("Unexpected update") },
        detect = { _, _, _ -> throw AssertionError("Unexpected detection") },
    )

    private fun guestDevice(storage: MemoryDeviceParcelStorage, scope: CoroutineScope, read: () -> PublicParcelResponse? = { null }) =
        DeviceParcels(guestClient(read), storage, PostcodeMemory(MemoryDefaults()), scope, region = { Locale.UK }, language = { AppLanguage.En }, now = { now })

    /** This phone's copy of the parcel as an earlier version kept it, before it noted what was seen. */
    private fun keptBefore(pkg: PublicPackage, seenNewsAt: String? = null) = MemoryDeviceParcelStorage().apply {
        keys = mapOf(linkId to key)
        parcels = listOf(
            DeviceParcel(
                linkId = linkId, label = "Synthetic parcel", archivedAt = null, parcel = Parcel(shared = pkg),
                checkedAt = now.minusSeconds(3600).timeIntervalSinceReferenceDate, seenNewsAt = seenNewsAt,
            ),
        )
    }

    @Test
    fun theLastSwitchChoiceReachesTheAccountLast() = runTest {
        val server = Server()
        val store = signedIn(server)
        store.loadNotificationPreferences()
        val first = CompletableDeferred<Unit>()
        server.beforePreferences = { request ->
            if (request.body?.decodeToString()?.contains("\"markNewUpdates\":false") == true) first.await()
        }
        val off = launch { store.setMarkNewUpdates(false) }
        runCurrent()
        val on = launch { store.setMarkNewUpdates(true) }
        runCurrent()
        first.complete(Unit)
        off.join()
        on.join()
        assertEquals(listOf(false, true), server.preferenceSaves)
        assertTrue(server.markNewUpdates)
    }

    @Test
    fun aScanAfterTheLastOpenShowsTheDot() = runTest {
        val server = Server()
        server.newsAt = null
        val store = signedIn(server)
        store.load()
        assertFalse("A parcel without a scan has nothing new", store.dotShown)

        server.newsAt = scanned
        store.load()
        assertTrue("A scan stored after the parcel was last opened is news", store.dotShown)

        // A guest: the scan that arrives after the phone last saw the parcel is news too.
        var pkg = publicPackage(newsAt = opened)
        val storage = keptBefore(pkg, seenNewsAt = opened)
        val device = guestDevice(storage, backgroundScope) { response(pkg) }
        assertFalse(device.list().single().hasNewUpdate)
        pkg = publicPackage(newsAt = scanned, scans = 2)
        device.refresh(all = true)
        assertTrue(device.list().single().hasNewUpdate)
        assertEquals("What the phone saw is kept until the parcel is opened", opened, storage.parcels.single().seenNewsAt)
    }

    @Test
    fun openingTheParcelClearsItHereAndOnTheOtherDevice() = runTest {
        val server = Server()
        val phone = signedIn(server)
        val tablet = signedIn(server)
        phone.load()
        tablet.load()
        assertTrue(phone.dotShown)
        assertTrue(tablet.dotShown)

        phone.markSeen(phone.shown)
        assertFalse("The dot goes on the device it was opened on", phone.dotShown)
        assertEquals("The server's moment takes the place of the cleared one", seenNow, phone.shown.seenAt)
        assertEquals(1, server.seenCalls.size)
        assertTrue("The call is the account's", server.seenCalls.single().startsWith("Bearer token-"))

        tablet.load()
        assertFalse("The account's other device finds it seen at its next read", tablet.dotShown)

        // Opened again with nothing new, nothing is sent.
        phone.markSeen(phone.shown)
        assertEquals(1, server.seenCalls.size)

        // A failed call says nothing, and the dot stays cleared here until the list is read again.
        server.newsAt = "2026-09-08T11:30:00.000001+00:00"
        phone.load()
        assertTrue(phone.dotShown)
        server.seenFails = true
        phone.markSeen(phone.shown)
        assertFalse(phone.dotShown)
        assertNull(phone.notificationError)

        // A guest's parcel is seen on the phone, and stays seen after a relaunch.
        val storage = keptBefore(publicPackage(newsAt = scanned, scans = 2), seenNewsAt = opened)
        val device = guestDevice(storage, backgroundScope)
        assertTrue(device.list().single().hasNewUpdate)
        device.markSeen(parcelId, opened)
        assertTrue(device.list().single().hasNewUpdate)
        device.markSeen(parcelId, scanned)
        assertFalse(device.list().single().hasNewUpdate)
        assertFalse(guestDevice(storage, backgroundScope).list().single().hasNewUpdate)
    }

    @Test
    fun startingTheAppWithExistingParcelsShowsNoDots() = runTest {
        // Kept before this version: what the parcel carries is taken as seen.
        val withScans = keptBefore(publicPackage(newsAt = scanned, scans = 3))
        assertFalse(guestDevice(withScans, backgroundScope).list().single().hasNewUpdate)

        // Kept before the service sent `news_at`: nothing is noted, and its first `news_at` is taken as seen.
        var pkg = publicPackage(newsAt = null, scans = 2)
        val older = keptBefore(pkg)
        val device = guestDevice(older, backgroundScope) { response(pkg) }
        assertFalse(device.list().single().hasNewUpdate)
        pkg = publicPackage(newsAt = scanned, scans = 2)
        device.refresh(all = true)
        assertFalse(device.list().single().hasNewUpdate)
        assertEquals(scanned, older.parcels.single().seenNewsAt)

        // Still waiting for its carrier's first answer: its first scans aren't news either.
        pkg = publicPackage(newsAt = null, scans = 0, sync = SyncStatus.Pending, lastSyncedAt = null)
        val waiting = keptBefore(pkg)
        val waitingDevice = guestDevice(waiting, backgroundScope) { response(pkg) }
        assertNull(waiting.parcels.single().seenNewsAt)
        pkg = publicPackage(newsAt = scanned, scans = 4)
        waitingDevice.refresh(all = true)
        assertFalse(waitingDevice.list().single().hasNewUpdate)

        // Kept before this version with no scan yet: nothing is noted, and its next answer starts it as seen.
        pkg = publicPackage(newsAt = null, scans = 0)
        val kept = keptBefore(pkg)
        val keptDevice = guestDevice(kept, backgroundScope) { response(pkg) }
        pkg = publicPackage(newsAt = scanned, scans = 1)
        keptDevice.refresh(all = true)
        assertFalse(keptDevice.list().single().hasNewUpdate)

        // Followed with no scan yet: the first scan that comes is news.
        pkg = publicPackage(newsAt = null, scans = 0)
        val quietDevice = guestDevice(MemoryDeviceParcelStorage(), backgroundScope) { response(pkg) }
        quietDevice.follow(response(pkg), key = key, label = null)
        assertFalse(quietDevice.list().single().hasNewUpdate)
        pkg = publicPackage(newsAt = scanned, scans = 1)
        quietDevice.refresh(all = true)
        assertTrue(quietDevice.list().single().hasNewUpdate)

        // An account's parcels come with the server's `seen_at`, which starts as their `news_at`.
        val server = Server()
        server.seenAt = scanned
        val store = signedIn(server)
        store.load()
        assertFalse(store.dotShown)
    }

    /**
     * A gift followed while it is wrapped: its link tells no `news_at` until the gift is delivered, though it shows
     * where it set off from. Its delivery is news, as on the iPhone and the web.
     */
    @Test
    fun aWrappedGiftsDeliveryIsNews() = runTest {
        fun gift(pkg: PublicPackage) = PublicParcelResponse(
            link = ParcelLink(
                id = "33333333333G", role = ParcelLinkRole.Viewer, kind = ParcelLinkKind.Shared, createdAt = "2026-09-07T08:00:00Z",
                numberShown = false, canKeep = false, gift = true,
            ),
            parcel = pkg,
        )
        var pkg = publicPackage(newsAt = null, scans = 2)
        val device = guestDevice(MemoryDeviceParcelStorage(), backgroundScope) { gift(pkg) }
        device.follow(gift(pkg), key = null, label = null)
        assertFalse(device.list().single().hasNewUpdate)
        pkg = publicPackage(newsAt = scanned, scans = 3)
        device.refresh(all = true)
        assertTrue("The wrapped gift's delivery shows no dot", device.list().single().hasNewUpdate)
    }

    /**
     * The parcel's carrier or number changed, and the service checks it anew: its first answer then, a history the
     * phone has not seen, is no news, as an account's parcel starts. The scan after it is.
     */
    @Test
    fun aParcelAnsweredAnewIsNoNews() = runTest {
        var pkg = publicPackage(newsAt = opened, scans = 1)
        val storage = keptBefore(pkg, seenNewsAt = opened)
        val device = guestDevice(storage, backgroundScope) { response(pkg) }
        // Waiting for the new carrier's first answer.
        pkg = publicPackage(newsAt = opened, scans = 0, sync = SyncStatus.Pending, lastSyncedAt = null)
        device.refresh(all = true)
        pkg = publicPackage(newsAt = scanned, scans = 3)
        device.refresh(all = true)
        assertFalse("The new carrier's first answer shows a dot", device.list().single().hasNewUpdate)
        assertEquals(scanned, storage.parcels.single().seenNewsAt)

        pkg = publicPackage(newsAt = "2026-09-08T11:45:00.000001+00:00", scans = 4)
        device.refresh(all = true)
        assertTrue(device.list().single().hasNewUpdate)
    }

    @Test
    fun theDemosMadeUpScanIsNewUntilItsParcelIsOpened() {
        val demo = DemoRepository(MemoryDefaults(), language = { AppLanguage.En }, now = { now })
        assertTrue("The demo starts without dots", demo.list().none { it.hasNewUpdate })
        val refreshed = demo.refreshAll()
        val news = refreshed.filter { it.hasNewUpdate }
        assertTrue("A refresh's made-up scans are news", news.isNotEmpty())
        val opened = demo.markSeen(news.first().id)
        assertFalse(opened.hasNewUpdate)
        assertFalse(demo.list().single { it.id == opened.id }.hasNewUpdate)
    }

    @Test
    fun theWidgetsShowTheDotTheCardShowsAndLoseItWithIt() = runTest {
        val server = Server()
        val defaults = MemoryDefaults()
        val reloads = mutableListOf<String>()
        val store = signedIn(server, defaults) { reloads += it }
        val shared = DeliveryWidgetSharedStore(defaults)
        fun published() = requireNotNull(shared.snapshot).parcels.single { it.id == parcelId }.newUpdate
        store.load()
        assertTrue(store.dotShown)
        assertEquals("The widgets are told which parcels have news", true, published())

        // The setting off: the dots go from the widgets as from the cards, and the widgets reload to lose them.
        var before = reloads.size
        store.setMarkNewUpdates(false)
        assertNull(published())
        assertEquals(before + 1, reloads.size)
        // Back on, a choice made on another device: the dot comes back.
        server.markNewUpdates = true
        before = reloads.size
        store.loadNotificationPreferences()
        assertTrue(store.markNewUpdatesFlow.value)
        assertEquals(true, published())
        assertEquals(before + 1, reloads.size)

        // Opened: the parcel is seen, and the widgets lose its dot.
        store.markSeen(store.shown)
        assertFalse(store.dotShown)
        assertNull(published())

        // With news again, read on another device first: the next read of the list takes it away there too.
        server.newsAt = "2026-09-08T11:30:00.000001+00:00"
        store.load()
        assertEquals(true, published())
        server.seenAt = "2026-09-08T11:45:00.000001+00:00"
        store.load()
        assertNull(published())

        // The order and the dot as the widget is told them, worked out from the cards.
        val parcel = store.shown.copy(newsAt = scanned, seenAt = opened)
        val localizer = Localizer(AppLanguage.En)
        assertEquals(true, ParcelStore.widgetOrder(listOf(parcel), localizer, now).single().newUpdate)
        assertNull(ParcelStore.widgetOrder(listOf(parcel), localizer, now, markNewUpdates = false).single().newUpdate)
        assertNull(ParcelStore.widgetOrder(listOf(parcel.copy(seenAt = seenNow)), localizer, now).single().newUpdate)
    }

    @Test
    fun theSettingTurnedOffHidesTheDot() = runTest {
        val server = Server()
        val defaults = MemoryDefaults()
        val store = signedIn(server, defaults)
        store.load()
        assertTrue(store.markNewUpdatesFlow.value)
        assertTrue(store.dotShown)

        store.setMarkNewUpdates(false)
        assertFalse(store.markNewUpdatesFlow.value)
        assertFalse("Off, no card shows the dot", store.dotShown)
        assertTrue("What was seen is still kept", store.shown.hasNewUpdate)
        assertEquals("The account keeps the choice with its preferences", listOf<Boolean?>(false), server.preferenceSaves)
        assertFalse(server.markNewUpdates)

        // Opening the parcel still marks it seen.
        store.markSeen(store.shown)
        assertEquals(1, server.seenCalls.size)

        // A choice made on another device arrives with the account's preferences.
        server.markNewUpdates = true
        store.loadNotificationPreferences()
        assertTrue(store.markNewUpdatesFlow.value)

        // A guest's choice is this phone's, and outlasts a relaunch.
        val guestDefaults = MemoryDefaults()
        val choice = MarkNewUpdatesChoice(guestDefaults)
        assertTrue(choice.enabled.value)
        choice.keep(false)
        assertFalse(MarkNewUpdatesChoice(guestDefaults).enabled.value)
        val parcel = Parcel(shared = publicPackage(newsAt = scanned)).copy(seenAt = opened)
        assertTrue(parcel.hasNewUpdate)
        assertFalse(parcel.marksNewUpdate(MarkNewUpdatesChoice(guestDefaults).enabled.value))
    }
    @Test
    fun aMarkChoiceTheServerCouldNotTakeStandsUntilItIsSaved() = runTest {
        val server = Server()
        val store = signedIn(server)
        store.loadNotificationPreferences()
        assertTrue(store.markNewUpdatesFlow.value)

        // Switched off while the server cannot take it: the next read, which still says on, leaves it off and saves it.
        server.beforePreferences = { throw IOException("The Internet connection appears to be offline.") }
        store.setMarkNewUpdates(false)
        server.beforePreferences = {}
        server.preferenceCalls.clear()
        store.loadNotificationPreferences()
        assertFalse(store.markNewUpdatesFlow.value)
        runCurrent()
        assertEquals(listOf("GET", "PATCH"), server.preferenceCalls.map { it.first })
        assertEquals(false, server.preferenceCalls.last().second?.get("markNewUpdates")?.jsonPrimitive?.boolean)
        assertEquals(ZoneId.systemDefault().id, server.preferenceCalls.last().second?.get("timezone")?.jsonPrimitive?.content)
        assertEquals(false, store.notificationPreferences?.markNewUpdates)

        // Saved, it follows the account again.
        server.markNewUpdates = true
        store.loadNotificationPreferences()
        assertTrue(store.markNewUpdatesFlow.value)
    }

    @Test
    fun aMarkChoiceMadeWithoutAnAccountGoesToTheAccountSignedInTo() = runTest {
        val defaults = MemoryDefaults()
        val guestSession = testSessionStore(configuration, backgroundScope, transport = RecordingTransport(), now = { now })
        guestSession.bootstrap()
        val guest = testParcelStore(configuration, guestSession, RecordingTransport(), backgroundScope, defaults, now = { now })
        assertTrue(guest.isGuest)
        guest.setMarkNewUpdates(false)

        val server = Server()
        val store = signedIn(server, defaults)
        store.loadNotificationPreferences()
        assertFalse(store.markNewUpdatesFlow.value)
        runCurrent()
        assertEquals("PATCH", server.preferenceCalls.last().first)
        assertEquals(false, server.preferenceCalls.last().second?.get("markNewUpdates")?.jsonPrimitive?.boolean)
        assertEquals(false, store.notificationPreferences?.markNewUpdates)
    }

    @Test
    fun aMarkChoiceAnAccountCouldNotSaveWaitsForThatAccountAndNotTheNextOne() = runTest {
        val defaults = MemoryDefaults()
        val reader = Server()
        val first = signedIn(reader, defaults)
        first.loadNotificationPreferences()
        // Switched off while the server cannot take it.
        reader.beforePreferences = { throw IOException("The Internet connection appears to be offline.") }
        first.setMarkNewUpdates(false)
        reader.beforePreferences = {}

        // Another account signed in on this phone keeps its own choice, and the first one's isn't saved to it.
        val other = Server()
        val second = signedIn(other, defaults, account = UUID.randomUUID())
        second.loadNotificationPreferences()
        runCurrent()
        assertTrue(second.markNewUpdatesFlow.value)
        assertTrue(other.preferenceCalls.none { it.second?.containsKey("markNewUpdates") == true })

        // The first account back: its choice stands, and goes to it.
        reader.preferenceCalls.clear()
        val back = signedIn(reader, defaults)
        back.loadNotificationPreferences()
        runCurrent()
        assertFalse(back.markNewUpdatesFlow.value)
        assertEquals(false, reader.preferenceCalls.last().second?.get("markNewUpdates")?.jsonPrimitive?.boolean)
        assertEquals(false, back.notificationPreferences?.markNewUpdates)
    }

    @Test
    fun aMarkChoiceWhoseSaveFailsLetsTheReadAlreadyUnderWayLand() = runTest {
        val server = Server()
        val store = signedIn(server)
        // The launch's read is under way when the switch is flipped, and the save's own read of the preferences fails.
        val held = CompletableDeferred<Unit>()
        var calls = 0
        server.beforePreferences = { if (calls++ == 0) held.await() else throw IOException("The Internet connection appears to be offline.") }
        val reading = launch { store.loadNotificationPreferences() }
        runCurrent()
        store.setMarkNewUpdates(false)
        assertEquals(2, calls)
        held.complete(Unit)
        reading.join()
        assertNotNull("The launch's read lands", store.notificationPreferences)
        assertFalse("The choice stands until it is saved", store.markNewUpdatesFlow.value)
    }

    @Test
    fun theAccountLearnsHowThisPhoneWritesATimeAndADistanceForTheDeliveryEmail() = runTest {
        val server = Server()
        server.clock = null
        server.distance = null
        server.emailOnDelivery = true
        server.emailAvailable = true
        val localizer = Localizer(AppLanguage.En, systemUses24HourTime = true, systemDistanceUnit = DistanceUnit.Kilometres)
        val store = signedIn(server, localizer = localizer)
        fun methods() = server.preferenceCalls.map { it.first }
        fun lastSent(name: String) = server.preferenceCalls.last().second?.get(name)?.jsonPrimitive?.content

        // Read without any: this phone's go to the account with its time zone, the rest as stored and the email choice
        // left out.
        store.loadNotificationPreferences()
        runCurrent()
        assertEquals(listOf("GET", "PATCH"), methods())
        assertEquals(setOf("clock", "distance", "timezone"), server.preferenceCalls.last().second?.keys)
        assertEquals(ZoneId.systemDefault().id, lastSent("timezone"))
        assertEquals("24", lastSent("clock"))
        assertEquals("km", lastSent("distance"))
        assertEquals(NotificationPreferencesClock.TwentyFour, store.notificationPreferences?.clock)

        // Chosen in Settings: sent at once, both in one save.
        localizer.clockPreference = ClockPreference.TwelveHour
        localizer.distancePreference = DistancePreference.Miles
        runCurrent()
        assertEquals(listOf("GET", "PATCH", "PATCH"), methods())
        assertEquals("12", lastSent("clock"))
        assertEquals("mi", lastSent("distance"))
        assertEquals(NotificationPreferencesDistance.Mi, store.notificationPreferences?.distance)

        // Another save leaves them out, so the server keeps the account's.
        store.setEmailOnDelivery(false)
        assertEquals("PATCH", methods().last())
        assertNull(lastSent("clock"))
        assertNull(lastSent("distance"))
        assertEquals(NotificationPreferencesClock.Twelve, store.notificationPreferences?.clock)

        // Changed since on another device: the next read brings it, and this phone sends its own again.
        server.clock = NotificationPreferencesClock.TwentyFour
        store.loadNotificationPreferences()
        runCurrent()
        assertEquals(listOf("GET", "PATCH"), methods().takeLast(2))
        assertEquals("12", lastSent("clock"))

        assertNull("Only the clock changed", lastSent("distance"))

        // A server that does not keep them is not asked again and again.
        server.keepsUnits = false
        server.clock = null
        server.distance = null
        val before = server.preferenceCalls.size
        store.loadNotificationPreferences()
        runCurrent()
        store.loadNotificationPreferences()
        runCurrent()
        assertEquals(listOf("GET", "PATCH", "GET"), methods().drop(before))
    }
}
