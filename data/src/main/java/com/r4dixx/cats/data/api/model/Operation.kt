package com.r4dixx.cats.data.api.model

import com.r4dixx.cats.data.api.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Serializable
data class Operation(
    val id: Long,
    val title: String,
    val amount: String,
    val category: String,
    @Serializable(with = InstantSerializer::class)
    val date: Instant
)
