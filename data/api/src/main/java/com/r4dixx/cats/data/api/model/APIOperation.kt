package com.r4dixx.cats.data.api.model

import com.r4dixx.cats.data.api.serializer.BigDecimalSerializer
import com.r4dixx.cats.data.api.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class APIOperation(
    val id: Long,
    val title: String,

    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,

    val category: String,

    @Serializable(InstantSerializer::class)
    @OptIn(ExperimentalTime::class)
    val date: Instant
)