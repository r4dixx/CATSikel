package com.r4dixx.cats.feature.banks.data.model

import com.r4dixx.cats.common.data.serializer.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class APIAccount(
    val order: Int,
    val id: Long,
    val holder: String,
    val role: Int,

    @SerialName("contract_number")
    val contractNumber: String,

    val label: String,

    @SerialName("product_code")
    val productCode: String,

    @Serializable(BigDecimalSerializer::class)
    val balance: BigDecimal,
    
    val operations: List<APIOperation>
)
