package com.r4dixx.cats.feature.banks.data.model

import kotlinx.serialization.Serializable

@Serializable
data class APIBank(
    val name: String,
    val isCA: Int,
    val accounts: List<APIAccount>
)
