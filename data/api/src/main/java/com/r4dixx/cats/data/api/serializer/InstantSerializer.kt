package com.r4dixx.cats.data.api.serializer

import io.kotzilla.sdk.KotzillaSDK
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
            KotzillaSDK.logError("Unable to deserialize to Instant", e)
            Instant.DISTANT_PAST
        }
    }

    override fun serialize(encoder: Encoder, value: Instant) {
        val serializedValue = try {
            value.epochSeconds
        } catch (e: SerializationException) {
            KotzillaSDK.logError("Unable to serialize Instant", e)
            0
        }

        encoder.encodeLong(serializedValue)
    }
}
