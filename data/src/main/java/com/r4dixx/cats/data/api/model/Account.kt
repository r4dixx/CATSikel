package com.r4dixx.cats.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val order: Int,
    val id: String,
    val holder: String,
    val role: Int,
    @SerialName("contract_number") val contractNumber: String,
    val label: String,
    @SerialName("product_code") val productCode: String,
    val balance: Double,
    val operations: List<Operation>
)
