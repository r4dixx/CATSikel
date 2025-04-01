package com.r4dixx.cats.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val order: Int,
    val id: Long,
    val holder: String,
    val role: Int,
    @SerialName("contract_number") val contractNumber: String,
    val label: String,
    @SerialName("product_code") val productCode: String,
    val balance: Double,
//    @Serializable(CurrencyAmountSerializer::class) val balance: BigDecimal,
    val operations: List<Operation>
)
