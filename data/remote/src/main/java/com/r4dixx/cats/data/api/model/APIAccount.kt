package com.r4dixx.cats.data.api.model

import com.r4dixx.cats.data.api.serializer.BigDecimalSerializer
import com.r4dixx.cats.domain.model.Account
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

fun APIAccount.toDomainAccount() = Account(
    id = id,
    label = label,
    balance = balance,
    operations = operations.distinct().map { it.toDomainOperation() }
)
