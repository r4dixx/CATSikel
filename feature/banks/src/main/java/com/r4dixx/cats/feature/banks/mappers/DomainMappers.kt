package com.r4dixx.cats.feature.banks.mappers

import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.feature.banks.model.UIBank

fun UIBank.toDomainBank(): Bank = Bank(
    name = name,
    isCA = isCA,
    accounts = accounts
)