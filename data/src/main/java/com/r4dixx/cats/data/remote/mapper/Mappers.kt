package com.r4dixx.cats.data.remote.mapper

import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.model.Operation
import kotlin.time.ExperimentalTime
import com.r4dixx.cats.data.remote.model.Account as APIAccount
import com.r4dixx.cats.data.remote.model.Bank as APIBank
import com.r4dixx.cats.data.remote.model.Operation as APIOperation

fun APIBank.toDomain() = Bank(
    name = name,
    isCA = isCA == 1,
    accounts = accounts.map { it.toDomain() }
)

fun APIAccount.toDomain() = Account(
    id = id,
    label = label,
    balance = balance,
    operations = operations.map { it.toDomain() }
)

@OptIn(ExperimentalTime::class)
fun APIOperation.toDomain() = Operation(
    title = title,
    amount = amount,
    date = date
)
