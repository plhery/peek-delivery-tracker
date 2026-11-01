package com.plhery.peekdeliverytracker.core

import java.time.Instant
import java.util.UUID
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * AppRoutingTests.swift's ShareInboxTests, then what the share card reads from what was shared (`SharedContent.read`'s
 * rules, ShareViewController.swift), which the iPhone has no tests of: the name's 80 Characters, the text's 10 000, each
 * text once, links before the text, and nothing readable.
 */
class ShareInboxTests {
    private val key = "sdt.sharedParcelDraft"
    private val now: Instant = Instant.parse("2026-11-01T09:00:00.123456Z")

    @Test
    fun onlySavedFreshDraftsAreConsumedOnce() {
        val defaults = MemoryDefaults()
        val draft = SharedParcelDraft(trackingInput = "12345678", createdAt = now)
        assertNull(ShareInbox.consume(defaults, now))
        assertTrue(ShareInbox.save(draft, defaults))
        assertEquals(draft, ShareInbox.consume(defaults, now))
        assertNull(ShareInbox.consume(defaults, now))
        ShareInbox.save(draft, defaults)
        assertNull(ShareInbox.consume(defaults, now.plusSeconds(601)))
        assertNull(defaults.data(forKey = key))
    }

    @Test
    fun discardLegacyUnconfirmedAndMalformedDrafts() {
        val defaults = MemoryDefaults()
        for (data in listOf("broken", "{\"id\":\"00000000-0000-4000-8000-000000000001\",\"label\":\"\",\"trackingInput\":\"12345678\"}")) {
            defaults.set(data.toByteArray(), forKey = key)
            assertNull(ShareInbox.consume(defaults))
            assertNull(defaults.data(forKey = key))
        }
    }

    /** `(0...600).contains(age)`: a draft saved now or 600 seconds ago, not one a second later nor one from the future. */
    @Test
    fun aDraftWaitsTenMinutesFromWhenItWasSaved() {
        val defaults = MemoryDefaults()
        val draft = SharedParcelDraft(label = "Desk lamp", trackingInput = "TESTLAMP0001", createdAt = now)
        for ((seconds, kept) in listOf(0L to true, 600L to true, 601L to false, -1L to false)) {
            assertTrue(ShareInbox.save(draft, defaults))
            assertEquals("$seconds s", if (kept) draft else null, ShareInbox.consume(defaults, now.plusSeconds(seconds)))
            assertFalse(defaults.hasObject(forKey = key))
        }
    }

    @Test
    fun aDraftWithNothingButSpacesIsDropped() {
        val defaults = MemoryDefaults()
        assertTrue(ShareInbox.save(SharedParcelDraft(label = "Desk lamp", trackingInput = " \n\t", createdAt = now), defaults))
        assertNull(ShareInbox.consume(defaults, now))
        assertFalse(defaults.hasObject(forKey = key))
    }

    /** As the iPhone's JSONEncoder writes it, and reads one it wrote: the id in capitals, the date as seconds since 2001. */
    @Test
    fun theDraftIsWrittenAsTheIphoneWritesIt() {
        val defaults = MemoryDefaults()
        val id = UUID.fromString("0a1b2c3d-0000-4000-8000-000000000002")
        assertTrue(ShareInbox.save(SharedParcelDraft(id, "Desk lamp", "TESTLAMP0001", now), defaults))
        val written = Json.parseToJsonElement(defaults.data(forKey = key)!!.decodeToString()).jsonObject
        assertEquals(setOf("id", "label", "trackingInput", "createdAt"), written.keys)
        assertEquals("0A1B2C3D-0000-4000-8000-000000000002", written.text("id"))
        assertEquals("Desk lamp", written.text("label"))
        assertEquals("TESTLAMP0001", written.text("trackingInput"))
        assertEquals(815_216_400.123456, written.getValue("createdAt").jsonPrimitive.double, 1e-6)

        val iPhone = "{\"label\":\"\",\"createdAt\":815216400,\"trackingInput\":\"TESTLAMP0001\",\"id\":\"0A1B2C3D-0000-4000-8000-000000000002\"}"
        defaults.set(iPhone.toByteArray(), forKey = key)
        assertEquals(SharedParcelDraft(id, "", "TESTLAMP0001", Instant.parse("2026-11-01T09:00:00Z")), ShareInbox.consume(defaults, now))
    }

    /** `save` reads the draft back: defaults that keep nothing fail it. */
    @Test
    fun aDraftThatDoesNotReadBackIsNotSaved() {
        val forgetful = object : UserDefaults by MemoryDefaults() {
            override fun set(value: ByteArray, forKey: String) = Unit
        }
        assertFalse(ShareInbox.save(SharedParcelDraft(trackingInput = "TESTLAMP0001", createdAt = now), forgetful))
    }

    private fun JsonObject.text(name: String): String = getValue(name).jsonPrimitive.content

    @Test
    fun theTitleNamesTheParcelCutTo80Characters() {
        assertEquals("Desk lamp", read(listOf(null, " ", "  Desk lamp \n", "Second"), listOf("TESTLAMP0001"))?.label)
        assertEquals("", read(listOf(null), listOf("TESTLAMP0001"))?.label)
        assertEquals("", read(emptyList(), listOf("TESTLAMP0001"))?.label)
        assertEquals("a".repeat(80), read(listOf("a".repeat(81)), listOf("TESTLAMP0001"))?.label)
        // Characters, not UTF-16 units: a flag is one.
        val flags = "🇨🇭".repeat(81)
        assertEquals("🇨🇭".repeat(80), read(listOf(flags), listOf("TESTLAMP0001"))?.label)
    }

    @Test
    fun eachTextOnceTrimmedInOrderOneToALine() {
        val link = "https://carrier.example/track?n=TESTLAMP0001"
        assertEquals(
            "$link\nYour parcel TESTLAMP0001 is on its way",
            read(texts = listOf(" $link\n", "Your parcel TESTLAMP0001 is on its way", link, "\t"))?.trackingInput,
        )
        // Swift's `contains` finds a text the same as another written with a combining accent.
        assertEquals("Café TESTLAMP0001", read(texts = listOf("Café TESTLAMP0001", "Café TESTLAMP0001"))?.trackingInput)
    }

    @Test
    fun theTextIsCutTo10000Characters() {
        val long = "1".repeat(9_995)
        assertEquals("$long\nTEST", read(texts = listOf(long, "TESTLAMP0001"))?.trackingInput)
        assertEquals(10_000, read(texts = listOf("é".repeat(10_001)))?.trackingInput?.length)
    }

    @Test
    fun nothingReadableIsNothing() {
        assertNull(read(listOf("Desk lamp"), emptyList()))
        assertNull(read(listOf("Desk lamp"), listOf(null, "", " \n ")))
    }

    private fun read(titles: List<String?> = emptyList(), texts: List<String?>) = SharedContent.read(titles, texts)
}
