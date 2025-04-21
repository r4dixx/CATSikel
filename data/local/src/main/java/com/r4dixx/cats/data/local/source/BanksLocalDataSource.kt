package com.r4dixx.cats.data.local.source

import androidx.room.withTransaction
import com.r4dixx.cats.data.local.database.CATSDatabase
import com.r4dixx.cats.data.local.entities.AccountEntity
import com.r4dixx.cats.data.local.entities.BankEntity
import com.r4dixx.cats.data.local.entities.OperationEntity
import com.r4dixx.cats.data.local.relations.BankWithAccounts

class BanksLocalDataSource(private val database: CATSDatabase) {
    suspend fun getBanksWithAccounts(): Result<List<BankWithAccounts>> {
        return try {
            val banks = database.bankDao.queryBanksWithAccounts().distinct()
            if (banks.isNotEmpty()) {
                Result.success(banks)
            } else {
                Result.failure(Exception("BanksLocalDataSource - No banks found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun upsertBanksData(bankWithAccounts: List<BankWithAccounts>): Result<Unit> {
        val bankEntities = mutableListOf<BankEntity>()
        val accountEntities = mutableListOf<AccountEntity>()
        val operationEntities = mutableListOf<OperationEntity>()

        bankWithAccounts.forEach { localBank ->
            bankEntities.add(localBank.bank)
            localBank.accountsWithOperations.forEach { localAccount ->
                accountEntities.add(localAccount.account)
                operationEntities.addAll(localAccount.operations)
            }
        }

        return try {
            database.withTransaction {
                database.bankDao.insertBanks(bankEntities)
                database.accountDao.insertAccounts(accountEntities)
                database.operationDao.insertOperations(operationEntities)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}