package com.r4dixx.cats.network.model

import kotlinx.serialization.Serializable

@Serializable
data class APIOperation(
    val id: String,
    val title: String,
    val amount: String,
    val category: String,
    val date: String
)
