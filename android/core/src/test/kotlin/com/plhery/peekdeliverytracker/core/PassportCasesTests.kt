package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.nio.ByteBuffer
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The passport's rules as the web, the iPhone and Android replay them (shared/passport-cases.json): each case's parcels
 * as scans, and what its passport holds, compared field by field as the web's `passport.cases.test.ts` does, instants as
 * ISO-8601 with milliseconds. Then the seen list a device keeps, and the news it gives.
 */
class PassportCasesTests {
    private val cases: List<JsonObject> by lazy {
        val text = javaClass.getResourceAsStream("/peek/PassportCases.json")!!.use { it.readBytes().decodeToString() }
        ApiJson.parseToJsonElement(text).jsonObject.getValue("cases").jsonArray.map { it.jsonObject }
    }

    /** A case's id as a UUID whose text sorts as the id does: its letters' bytes, then zeros. */
    private fun uuid(id: String): UUID {
        val bytes = ByteBuffer.allocate(16).put(id.toByteArray().copyOf(16)).flip()
        return UUID(bytes.long, bytes.long)
    }

    private fun id(uuid: UUID): String {
        val bytes = ByteBuffer.allocate(16).putLong(uuid.mostSignificantBits).putLong(uuid.leastSignificantBits).array()
        return bytes.takeWhile { it != 0.toByte() }.toByteArray().decodeToString()
    }

    private fun parcels(case: JsonObject): List<Parcel> = case.getValue("parcels").jsonArray.map { element ->
        val parcel = element.jsonObject
        val id = parcel.getValue("id").jsonPrimitive.content
        val events = parcel.getValue("scans").jsonArray.mapIndexed { index, scan ->
            val fields = scan.jsonArray.map { it.jsonPrimitive.content }
            val location = fields.getOrNull(2)
            TrackingEvent(
                id = uuid("$id-$index"), packageId = uuid(id), stage = requireNotNull(TrackingStage.fromRawValue(fields[0])),
                description = fields[0], location = location, occurredAt = fields[1],
                place = fields.getOrNull(3)?.let { EventPlace(0.0, 0.0, EventPlacePrecision.City, it, location ?: it) },
            )
        }
        Parcel(
            id = uuid(id), trackingNumber = "T$id", label = parcel.getValue("label").jsonPrimitive.content,
            carrier = CarrierId(parcel.getValue("carrier").jsonPrimitive.content), createdAt = "2026-01-01T00:00:00Z",
            syncStatus = SyncStatus.Ok, archivedAt = parcel["archivedAt"]?.jsonPrimitive?.content, notificationsMuted = false,
            trackingEvents = events,
        )
    }

    private val iso = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(java.time.ZoneOffset.UTC)

    private fun iso(at: Instant?): JsonElement = at?.let { JsonPrimitive(iso.format(it)) } ?: JsonNull

    /** What a case's passport holds, as the three platforms compare it. */
    private fun outcome(case: JsonObject): JsonObject {
        val book = PassportBook(parcels(case), ZoneId.of(case.getValue("timeZone").jsonPrimitive.content))
        val year = YearInParcels(book, 2026)
        return buildJsonObject {
            put("deliveredCount", book.stats.deliveredCount)
            put("carrierCount", book.stats.carrierCount)
            put(
                "stamps",
                JsonObject(
                    book.stamps.associate { stamp ->
                        stamp.key to buildJsonObject {
                            put("count", stamp.count)
                            put("earned", stamp.earned)
                            put("earnedAt", iso(stamp.earnedAt))
                            put("parcel", stamp.parcelId?.let { JsonPrimitive(id(it)) } ?: JsonNull)
                        }
                    },
                ),
            )
            put(
                "countries",
                JsonArray(
                    book.countries.map { country ->
                        buildJsonObject {
                            put("code", country.code)
                            put("number", country.number)
                            put("firstAt", iso(country.firstAt))
                            put("parcel", id(country.parcelId))
                            put("count", country.count)
                            put("continent", country.continent.rawValue)
                        }
                    },
                ),
            )
            put(
                "year2026",
                buildJsonObject {
                    put("deliveredCount", year.deliveredCount)
                    put("countryCount", year.countryCount)
                    put("countries", JsonArray(year.countries.map { buildJsonArray { add(JsonPrimitive(it.stamp.code)); add(JsonPrimitive(it.count)) } }))
                    put("stamps", JsonArray(year.stamps.map { JsonPrimitive(it.key) }))
                    put("fastest", year.fastest?.let { JsonPrimitive(Math.round(it * 1000)) } ?: JsonNull)
                },
            )
        }
    }

    /** Numbers compared by value, as the web's `toEqual` compares them: 3 and 3.0 are one. */
    private fun normalized(element: JsonElement): JsonElement = when (element) {
        is JsonObject -> JsonObject(element.mapValues { normalized(it.value) })
        is JsonArray -> JsonArray(element.map(::normalized))
        is JsonPrimitive -> if (!element.isString && element.content.toDoubleOrNull() != null) JsonPrimitive(element.content.toDouble()) else element
        else -> element
    }

    @Test
    fun everyCaseHoldsWhatTheWebsPassportHolds() {
        assertTrue(cases.isNotEmpty())
        for (case in cases) {
            val name = case.getValue("name").jsonPrimitive.content
            assertEquals(name, normalized(case.getValue("expect")), normalized(outcome(case)))
        }
    }

    @Test
    fun theStampsComeInPageOrderWithTheirGlyphOrNumeral() {
        val keys = PassportData.stamps.map { it.key }
        assertEquals(listOf("first", "rightNextDoor", "express", "familiarFace"), keys.take(4))
        assertEquals(19, keys.size)
        for (stamp in PassportData.stamps) assertTrue(stamp.key, (stamp.glyph != null) != (stamp.numeral != null))
        assertEquals(StampTone.Rose, PassportData.stamps.first { it.key == "familiarFace" }.tone)
        // A code with no continent takes Europe's outline.
        assertEquals(Continent.Europe, PassportData.countryInk("XX").continent)
        assertEquals(Continent.Asia, PassportData.continentOf("JP"))
    }

    @Test
    fun theFirstReadCountsEverythingAsSeenThenEachNewStampIsNewUntilMarked() {
        val defaults = MemoryDefaults()
        val record = PassportSeenRecord(defaults)
        val scope = "guest"
        fun parcel(id: String, carrier: String, at: String) = Parcel(
            id = uuid(id), trackingNumber = "T$id", label = "Parcel $id", carrier = CarrierId(carrier), createdAt = "2026-09-01T00:00:00Z",
            syncStatus = SyncStatus.Ok, notificationsMuted = false,
            trackingEvents = listOf(
                TrackingEvent(uuid("$id-1"), uuid(id), TrackingStage.InTransit, "In transit", occurredAt = at.replace("T12", "T08")),
                TrackingEvent(uuid("$id-2"), uuid(id), TrackingStage.Delivered, "Delivered", occurredAt = at),
            ),
        )
        val first = listOf(parcel("a", "dhl", "2026-09-03T12:00:00Z"), parcel("b", "ups", "2026-09-04T12:00:00Z"))
        // Nothing kept: nothing is new, and the first read keeps all it holds.
        assertNull(record.seen(scope))
        val opened = PassportNews(PassportBook(first), record.seen(scope))
        assertTrue(opened.unseen.isEmpty())
        record.store(scope, opened.earnedKeys)
        assertEquals("""["stamp:express","stamp:first"]""", defaults.string(forKey = "peek-passport-seen:guest"))
        assertEquals(1, record.changes.value)
        // A third carrier's delivery earns Well connected, on its parcel's card.
        val next = first + parcel("c", "fedex", "2026-09-05T12:00:00Z")
        val news = PassportNews(PassportBook(next), record.seen(scope))
        assertEquals(listOf("stamp:connected"), news.unseen.map { it.seenKey })
        assertEquals(setOf(uuid("c")), news.byParcel.keys)
        // Opening the passport marks it seen; the same list again writes nothing.
        record.store(scope, news.earnedKeys)
        assertTrue(PassportNews(PassportBook(next), record.seen(scope)).unseen.isEmpty())
        record.store(scope, news.earnedKeys)
        assertEquals(2, record.changes.value)
        // Each account keeps its own list; an unreadable one counts as none.
        assertNull(record.seen("11111111-1111-4111-8111-111111111111"))
        defaults.set("not json", forKey = "peek-passport-seen:guest")
        assertNull(record.seen(scope))
    }

    @Test
    fun aDeliveryAfterAnEmptyPassportNamesEveryStampItBrought() {
        val record = PassportSeenRecord(MemoryDefaults())
        record.store("guest", emptyList())
        val parcel = Parcel(
            id = uuid("a"), trackingNumber = "Ta", label = "Parcel a", carrier = CarrierId("dhl"), createdAt = "2026-09-01T00:00:00Z",
            syncStatus = SyncStatus.Ok, notificationsMuted = false,
            trackingEvents = listOf(
                TrackingEvent(uuid("a-1"), uuid("a"), TrackingStage.InTransit, "In transit", occurredAt = "2026-12-03T08:00:00Z"),
                TrackingEvent(uuid("a-2"), uuid("a"), TrackingStage.Delivered, "Delivered", occurredAt = "2026-12-03T12:00:00Z"),
            ),
        )
        val news = PassportNews(PassportBook(listOf(parcel), ZoneId.of("Europe/Zurich")), record.seen("guest"))
        assertEquals(listOf("first", "express", "homeForHolidays"), news.byParcel.getValue(uuid("a")).map { it.stamp.key })
        assertFalse(news.unseen.any { it.parcelId == null })
    }
}
