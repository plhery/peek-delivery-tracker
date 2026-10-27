package com.plhery.peekdeliverytracker.core

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

// The helper from ParcelPassDesignSystem.swift that the carrier catalog uses.

object CarrierVisualIdentity {
    /** The colour of a carrier the brand data does not name (Brand.json's `defaultColor`). */
    val defaultCarrierColor: String by lazy {
        Json.parseToJsonElement(SharedResources.text("Brand.json")).jsonObject.getValue("defaultColor").jsonPrimitive.content
    }
}
