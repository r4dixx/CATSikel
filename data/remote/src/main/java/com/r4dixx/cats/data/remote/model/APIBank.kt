package com.r4dixx.cats.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class APIBank(
    val name: String,
    val isCA: Int,
    val accounts: List<APIAccount>
)