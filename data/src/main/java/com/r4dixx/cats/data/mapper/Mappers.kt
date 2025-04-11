package com.r4dixx.cats.data.mapper

import com.r4dixx.cats.core.utils.sanitized
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.model.Operation
import kotlin.time.ExperimentalTime
import com.r4dixx.cats.data.model.Account as APIAccount
import com.r4dixx.cats.data.model.Bank as APIBank
import com.r4dixx.cats.data.model.Operation as APIOperation

fun APIBank.toDomain() = Bank(
    name = name,
    isCA = isCA == 1,
    accounts = accounts.distinct().map { it.toDomain() }
)

fun APIAccount.toDomain() = Account(
    id = id,
    label = label.sanitized(),
    balance = balance,
    operations = operations.distinct().map { it.toDomain() }
)

@OptIn(ExperimentalTime::class)
fun APIOperation.toDomain() = Operation(
    title = title,
    amount = amount,
    date = date
)
