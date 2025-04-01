package com.r4dixx.cats.data.api.serializer


import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal
import java.math.RoundingMode

object BigDecimalSerializer : KSerializer<BigDecimal> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Currency", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): BigDecimal {
        return try {
            val stringValue = decoder.decodeString().replace(",", ".").trim()
            stringValue.toBigDecimalOrNull() ?: BigDecimal.ZERO
        } catch (e: SerializationException) {
            throw SerializationException("Unable to deserialize BigDecimal from string", e)
        }
    }

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        try {
            val formattedValue = value.setScale(2, RoundingMode.HALF_UP).toString()
            encoder.encodeString(formattedValue)
        } catch (e: SerializationException) {
            throw SerializationException("Unable to serialize BigDecimal", e)
        }
    }
}
