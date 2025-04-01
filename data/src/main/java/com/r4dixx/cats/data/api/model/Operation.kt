package com.r4dixx.cats.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Operation(
    val id: Long,
    val title: String,
    val amount: String,
    val category: String,
    val date: Long
)
