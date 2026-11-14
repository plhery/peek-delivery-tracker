package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** How Brand.json is read: all of it at once but the truck's decals, each found and decoded when it's asked for. */
class BrandReadingTests {
    private fun text(data: ByteArray, range: IntRange) = String(data, range.first, range.last + 1 - range.first, Charsets.UTF_8)

    @Test
    fun anObjectsMembersAreFoundByNameWithTheirKeysAndValues() {
        val data = """{ "a" : 1,"b\"c": {"x": [1, {"y": "}]"}], "z": null} ,
            "d": "e\"f", "g": [ ] }""".toByteArray()
        val members = JsonMembers(data).of(0)
        assertEquals(listOf("a", "b\"c", "d", "g"), members.keys.toList())
        assertEquals("1", text(data, members.getValue("a").value))
        assertEquals("\"b\\\"c\"", text(data, members.getValue("b\"c").key))
        assertEquals("""{"x": [1, {"y": "}]"}], "z": null}""", text(data, members.getValue("b\"c").value))
        assertEquals("\"e\\\"f\"", text(data, members.getValue("d").value))
        assertEquals("[ ]", text(data, members.getValue("g").value))
        val inner = JsonMembers(data).of(members.getValue("b\"c").value.first)
        assertEquals(listOf("x", "z"), inner.keys.toList())
    }

    @Test
    fun aTruckAnywhereInTheFileIsReadApartFromTheRest() {
        val data = """
            {
              "defaultColor": "#8e8e93",
              "truck": {
                "viewBox": {"width": 32, "height": 21},
                "decals": {
                  "default": [{"type": "circle", "cx": 1.5, "cy": 2, "r": 0.5, "fill": "brand"}],
                  "stripe": [{"type": "line", "segments": [[[0, 1], [2, 3]]], "stroke": "accent", "strokeWidth": 0.4}]
                }
              },
              "fallbackColor": "#657060",
              "properties": ["truck"],
              "derivation": [],
              "litEdge": {"below": 2.2, "lift": 0.2, "floor": 0.5, "maxChroma": 0.1},
              "families": {},
              "palettes": {},
              "decals": {"a": "stripe"}
            }
        """.trimIndent().toByteArray()
        val assets = CarrierBrandAssets.read(data)
        assertEquals("#8e8e93", assets.defaultColor)
        assertEquals("#657060", assets.fallbackColor)
        assertEquals(mapOf("a" to "stripe"), assets.decals)
        val decals = assets.truck.decals
        assertEquals(setOf("default", "stripe"), decals.keys)
        assertEquals(2, decals.size)
        assertTrue("stripe" in decals)
        assertNull(decals["missing"])
        val circle = decals.getValue("default").single()
        assertEquals(CarrierBrandAssets.DecalShape.Kind.Circle, circle.type)
        assertEquals(listOf(1.5, 2.0, 0.5), listOf(circle.cx, circle.cy, circle.r))
        assertEquals(listOf(listOf(listOf(0.0, 1.0), listOf(2.0, 3.0))), decals.getValue("stripe").single().segments)
        // Asked again, the same shapes.
        assertTrue(decals.getValue("stripe") === decals.getValue("stripe"))
    }
}
