package com.r4dixx.cats.feature.banks.data.model

import com.r4dixx.cats.common.data.model.Bank
import kotlinx.serialization.Serializable

@Serializable
data class APIBank(
    val name: String,
    val isCA: Int,
    val accounts: List<APIAccount>
)

fun APIBank.toDomainBank() = Bank(
    name = name,
    isCA = isCA == 1,
    accounts = accounts.distinct().map { it.toDomainAccount() }
)