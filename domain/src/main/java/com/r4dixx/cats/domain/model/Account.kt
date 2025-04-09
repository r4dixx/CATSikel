package com.r4dixx.cats.domain.model

import java.math.BigDecimal

data class Account(
    val id: Long,
    val label: String,
    val balance: BigDecimal,
    val operations: List<Operation>
)
