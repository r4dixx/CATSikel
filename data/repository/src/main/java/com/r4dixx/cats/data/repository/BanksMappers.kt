package com.r4dixx.cats.data.repository

import com.r4dixx.cats.data.api.model.APIAccount
import com.r4dixx.cats.data.api.model.APIBank
import com.r4dixx.cats.data.api.model.APIOperation
import com.r4dixx.cats.data.local.entities.AccountEntity
import com.r4dixx.cats.data.local.entities.BankEntity
import com.r4dixx.cats.data.local.entities.OperationEntity
import com.r4dixx.cats.data.local.relations.AccountWithOperations
import com.r4dixx.cats.data.local.relations.BankWithAccounts
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.model.Operation
import kotlin.time.ExperimentalTime

// region API - Domain

fun APIBank.toDomainBank() = Bank(
    name = name,
    isCA = isCA == 1,
    accounts = accounts.distinct().map { it.toDomainAccount() }
)

fun APIAccount.toDomainAccount() = Account(
    id = id,
    bankName = null,
    label = label,
    balance = balance,
    operations = operations.distinct().map { it.toDomainOperation() }
)

@OptIn(ExperimentalTime::class)
fun APIOperation.toDomainOperation() = Operation(
    id = id,
    title = title,
    amount = amount,
    instant = instant
)

// endregion API - Domain

// region Local - Domain

fun BankWithAccounts.toDomainBank() = Bank(
    name = bank.name,
    isCA = bank.isCA,
    accounts = accountsWithOperations.distinct().map { it.toDomainAccount() }
)

fun AccountWithOperations.toDomainAccount() = Account(
    id = account.id,
    bankName = account.bankName,
    label = account.label,
    balance = account.balance,
    operations = operations.distinct().map { it.toDomainOperation() }
)

@OptIn(ExperimentalTime::class)
fun OperationEntity.toDomainOperation() = Operation(
    id = id,
    title = title,
    amount = amount,
    instant = instant
)

// endregion Local - Domain

// region Domain - Local

fun Bank.toLocalBank() = BankWithAccounts(
    bank = BankEntity(
        name = name,
        isCA = isCA
    ),
    accountsWithOperations = accounts.distinct().map { it.toLocalAccount(name) }
)

fun Account.toLocalAccount(bankName: String) = AccountWithOperations(
    account = AccountEntity(
        id = id,
        bankName = bankName,
        label = label,
        balance = balance
    ),
    operations = operations.distinct().map { it.toLocalOperation(id) }
)

@OptIn(ExperimentalTime::class)
fun Operation.toLocalOperation(accountId: Long) = OperationEntity(
    id = id,
    accountId = accountId,
    title = title,
    amount = amount,
    instant = instant
)

// endregion Domain - Local