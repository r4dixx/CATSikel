package com.r4dixx.cats.data.remote.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@ExperimentalTime
object InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Instant {
        return try {
            val input = decoder.decodeLong()
            Instant.fromEpochSeconds(input)
        } catch (e: SerializationException) {
            throw SerializationException("Unable to deserialize to Instant", e)
        }
    }

    override fun serialize(encoder: Encoder, value: Instant) {
        try {
            encoder.encodeLong(value.epochSeconds)
        } catch (e: SerializationException) {
            throw SerializationException("Unable to serialize Instant", e)
        }
    }
}
