package com.r4dixx.cats.data.remote.model

import android.annotation.SuppressLint
import com.r4dixx.cats.data.remote.serializer.BigDecimalSerializer
import com.r4dixx.cats.data.remote.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@SuppressLint("UnsafeOptInUsageError")
@OptIn(ExperimentalTime::class)
@Serializable
data class Operation(
    val id: Long,
    val title: String,

    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,

    val category: String,

    @Serializable(InstantSerializer::class)
    val date: Instant
)
