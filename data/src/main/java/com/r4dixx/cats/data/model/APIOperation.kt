package com.r4dixx.cats.data.model

import com.r4dixx.cats.data.serializer.BigDecimalSerializer
import com.r4dixx.cats.data.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Serializable
data class APIOperation(
    val id: Long,
    val title: String,

    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,

    val category: String,

    @Serializable(InstantSerializer::class)
    val date: Instant
)
