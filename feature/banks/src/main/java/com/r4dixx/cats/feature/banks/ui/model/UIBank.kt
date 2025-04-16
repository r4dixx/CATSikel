package com.r4dixx.cats.feature.banks.ui.model

import androidx.compose.runtime.Stable
import com.r4dixx.cats.domain.model.Bank
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Stable
data class UIBank(
    val name: String,
    val isCA: Boolean,
    val accounts: ImmutableList<UIAccount>
)

fun Bank.toUIBank() = UIBank(
    name = name,
    isCA = isCA,
    accounts = accounts.toUIAccounts()
)

fun List<Bank>.toUIBanks(): ImmutableList<UIBank> = map { it.toUIBank() }.toImmutableList()