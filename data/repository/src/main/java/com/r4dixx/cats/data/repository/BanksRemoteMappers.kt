package com.r4dixx.cats.data.repository

import com.r4dixx.cats.data.network.model.APIAccount
import com.r4dixx.cats.data.network.model.APIBank
import com.r4dixx.cats.data.network.model.APIOperation
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
    label = label,
    balance = balance,
    operations = operations.distinct().map { it.toDomainOperation() }
)

@OptIn(ExperimentalTime::class)
fun APIOperation.toDomainOperation() = Operation(
    title = title,
    amount = amount,
    date = date
)