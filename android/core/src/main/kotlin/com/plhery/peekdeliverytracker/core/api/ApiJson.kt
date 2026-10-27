package com.plhery.peekdeliverytracker.core.api

import java.util.UUID
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json

/** Reads and writes the API's JSON the way the iPhone app's Codable models do. */
val ApiJson = Json {
    // Codable skips keys a model doesn't name, so a field the server adds never breaks a release.
    ignoreUnknownKeys = true
    explicitNulls = false
}

/** A UUID in its canonical 8-4-4-4-12 form, as Foundation's UUID reads it. */
object UuidSerializer : KSerializer<UUID> {
    private val canonical = Regex("^[0-9A-Fa-f]{8}-([0-9A-Fa-f]{4}-){3}[0-9A-Fa-f]{12}$")

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("java.util.UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        val text = decoder.decodeString()
        if (!canonical.matches(text)) throw SerializationException("Not a UUID: $text")
        return UUID.fromString(text)
    }

    override fun serialize(encoder: Encoder, value: UUID) = encoder.encodeString(value.toString())
}

/** A parcel's history: missing or null reads as none, like the iPhone app's Parcel decoder. */
object TrackingEventsSerializer : KSerializer<List<TrackingEvent>> {
    private val list = ListSerializer(TrackingEvent.serializer())
    private val nullableList = list.nullable

    override val descriptor: SerialDescriptor = nullableList.descriptor

    override fun deserialize(decoder: Decoder): List<TrackingEvent> = nullableList.deserialize(decoder) ?: emptyList()

    override fun serialize(encoder: Encoder, value: List<TrackingEvent>) = list.serialize(encoder, value)
}
