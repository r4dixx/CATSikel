package com.r4dixx.cats.feature.banks.mappers

import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.feature.banks.model.UIBank
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

fun List<Bank>.toUIBanks(): ImmutableList<UIBank> = map { bank ->
    UIBank(
        name = bank.name,
        isCA = bank.isCA,
        accounts = bank.accounts.toImmutableList()
    )
}.toImmutableList()