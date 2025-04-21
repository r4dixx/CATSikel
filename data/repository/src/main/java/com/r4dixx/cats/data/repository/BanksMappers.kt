package com.r4dixx.cats.data.repository

import com.r4dixx.cats.data.api.model.APIAccount
import com.r4dixx.cats.data.api.model.APIBank
import com.r4dixx.cats.data.api.model.APIOperation
import com.r4dixx.cats.data.local.entities.AccountEntity
import com.r4dixx.cats.data.local.entities.BankEntity
import com.r4dixx.cats.data.local.entities.OperationEntity
import com.r4dixx.cats.data.local.relations.LocalAccount
import com.r4dixx.cats.data.local.relations.LocalBank
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.model.Operation
import kotlin.time.ExperimentalTime

// region API - Local

fun APIBank.toLocalBank() = LocalBank(
    bank = BankEntity(
        name = name,
        isCA = isCA == 1
    ),
    accounts = accounts.distinct().map { it.toLocalAccount(name) }
)

fun APIAccount.toLocalAccount(bankName: String) = LocalAccount(
    account = AccountEntity(
        id = id,
        bankName = bankName,
        label = label,
        balance = balance
    ),
    operations = operations.distinct().map { it.toLocalOperation(id) }
)

@OptIn(ExperimentalTime::class)
fun APIOperation.toLocalOperation(accountId: Long) = OperationEntity(
    id = id,
    accountId = accountId,
    title = title,
    amount = amount,
    date = date
)

// endregion API - Local

// region API - Domain

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
    id = id,
    title = title,
    amount = amount,
    date = date
)

// endregion API - Domain

// region Local - Domain

fun LocalBank.toDomainBank() = Bank(
    name = bank.name,
    isCA = bank.isCA,
    accounts = accounts.distinct().map { it.toDomainAccount() }
)

fun LocalAccount.toDomainAccount() = Account(
    id = account.id,
    label = account.label,
    balance = account.balance,
    operations = operations.distinct().map { it.toDomainOperation() }
)

@OptIn(ExperimentalTime::class)
fun OperationEntity.toDomainOperation() = Operation(
    id = id,
    title = title,
    amount = amount,
    date = date
)

// endregion Local - Domain