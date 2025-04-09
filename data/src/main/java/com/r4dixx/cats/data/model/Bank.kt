package com.r4dixx.cats.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Bank(
    val name: String,
    val isCA: Int,
    val accounts: List<Account>
)
