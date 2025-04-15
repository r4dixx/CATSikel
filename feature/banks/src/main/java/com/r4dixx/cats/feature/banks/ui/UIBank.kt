package com.r4dixx.cats.feature.banks.ui

import com.r4dixx.cats.common.data.model.Account
import com.r4dixx.cats.common.data.model.Bank
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class UIBank(
    val name: String,
    val isCA: Boolean,
    val accounts: ImmutableList<Account>
)

fun UIBank.toDomainBank(): Bank = Bank(
    name = name,
    isCA = isCA,
    accounts = accounts
)

fun Bank.toUIBank(): UIBank = UIBank(
    name = name,
    isCA = isCA,
    accounts = accounts.toImmutableList()
)

fun List<Bank>.toUIBanks(): ImmutableList<UIBank> = map { it.toUIBank() }.toImmutableList()