package com.r4dixx.cats.domain.model

import java.math.BigDecimal
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class Operation(
    val title: String,
    val amount: BigDecimal,
    val date: Instant
)
