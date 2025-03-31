package com.r4dixx.cats.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Operation(
    val id: String,
    val title: String,
    val amount: String,
    val category: String,
    val date: String
)
