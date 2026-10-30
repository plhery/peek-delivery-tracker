package com.plhery.peekdeliverytracker.core

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SharedResourcesTest {
    @Test
    fun everyLanguageHasEveryKey() {
        val catalogs = Json.parseToJsonElement(SharedResources.text("Localization.json")).jsonObject
        assertEquals(setOf("en", "de", "fr", "it", "es", "pt", "pt-br", "pl", "ru", "zh"), catalogs.keys)
        val english = catalogs.getValue("en").jsonObject.keys
        for ((language, catalog) in catalogs) {
            assertEquals("keys of $language", english, catalog.jsonObject.keys)
        }
    }

    @Test
    fun anInstalledReaderComesFirstAndTheClassPathCoversWhatItLacks() {
        try {
            SharedResources.install { name -> if (name == "Probe.json") "{}".byteInputStream() else null }
            assertEquals("{}", SharedResources.text("Probe.json"))
            assertTrue(SharedResources.text("Reading.json").isNotEmpty())
        } finally {
            SharedResources.install { null }
        }
    }

    @Test
    fun carrierCatalogListsCarriers() {
        val catalog = Json.parseToJsonElement(SharedResources.text("CarrierCatalog.json")).jsonObject
        assertTrue(catalog.getValue("x-carriers").jsonObject.size > 100)
    }
}
