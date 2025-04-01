package com.r4dixx.cats.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Parcelize
data class Operation(
    val title: String,
    val amount: BigDecimal,
    val date: Instant
) : Parcelable
