package com.r4dixx.cats.feature.banks.domain

import com.r4dixx.cats.common.data.model.Bank
import com.r4dixx.cats.common.ui.utils.sanitized
import com.r4dixx.cats.common.data.model.Account
import com.r4dixx.cats.common.data.model.Operation
import com.r4dixx.cats.feature.banks.data.model.APIAccount
import com.r4dixx.cats.feature.banks.data.model.APIBank
import com.r4dixx.cats.feature.banks.data.model.APIOperation
import kotlin.time.ExperimentalTime

fun APIBank.toDomainBank() = Bank(
    name = name,
    isCA = isCA == 1,
    accounts = accounts.distinct().map { it.toDomainAccount() }
)

fun APIAccount.toDomainAccount() = Account(
    id = id,
    label = label.sanitized(),
    balance = balance,
    operations = operations.distinct().map { it.toDomainOperation() }
)

@OptIn(ExperimentalTime::class)
fun APIOperation.toDomainOperation() = Operation(
    title = title,
    amount = amount,
    date = date
)