package com.r4dixx.cats.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Account(
    val id: Long,
    val label: String,
    val balance: BigDecimal,
    val operations: List<Operation>
) : Parcelable
