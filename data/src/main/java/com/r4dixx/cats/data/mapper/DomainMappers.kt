package com.r4dixx.cats.data.mapper

import com.r4dixx.cats.core.utils.sanitized
import com.r4dixx.cats.data.model.APIAccount
import com.r4dixx.cats.data.model.APIBank
import com.r4dixx.cats.data.model.APIOperation
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.model.Operation
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
