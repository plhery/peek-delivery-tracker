package com.plhery.peekdeliverytracker.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

// From Analytics.swift, the catalog that names what is counted and the outcomes an action is counted with. Sending the
// events comes with the analytics port.

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
            val value = body?.let { runCatching { Json.parseToJsonElement(it.decodeToString()) }.getOrNull() } as? JsonObject
            val action = (value?.get("action") as? JsonPrimitive)?.takeIf { it.isString }?.content ?: return null
            return friendActions[action]
        }
        return operations.firstOrNull { it.method == method && CatalogExpressions.foundationMatches(path, it.path) }?.event
    }

    companion object {
        val bundled: AnalyticsCatalog? by lazy {
            runCatching {
                Json { ignoreUnknownKeys = true }.decodeFromString(serializer(), SharedResources.text("Analytics.json"))
            }.getOrNull()
        }
    }
}

object DeliveryAnalytics {
    /** How an action went, as the catalog's events say it. */
    enum class Outcome(val rawValue: String) {
        Success("success"),
        Error("error"),
        Started("started"),
        Accepted("accepted"),
    }
}
