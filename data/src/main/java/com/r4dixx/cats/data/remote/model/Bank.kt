package com.r4dixx.cats.data.remote.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Bank(
    val name: String,
    val isCA: Int,
    val accounts: List<Account>
)
