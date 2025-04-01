package com.r4dixx.cats.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
    val name: String,
    val isCA: Boolean,
    val accounts: List<Account>
) : Parcelable
