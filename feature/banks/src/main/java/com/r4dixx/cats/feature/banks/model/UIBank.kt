package com.r4dixx.cats.feature.banks.model

import com.r4dixx.cats.domain.model.Account
import kotlinx.collections.immutable.ImmutableList

data class UIBank(
    val name: String,
    val isCA: Boolean,
    val accounts: ImmutableList<Account>
)