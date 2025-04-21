package com.r4dixx.cats.data.local.source

import androidx.room.withTransaction
import com.r4dixx.cats.data.local.database.CATSDatabase
import com.r4dixx.cats.data.local.entities.AccountEntity
import com.r4dixx.cats.data.local.entities.BankEntity
import com.r4dixx.cats.data.local.entities.OperationEntity
import com.r4dixx.cats.data.local.relations.AccountWithOperations
import com.r4dixx.cats.data.local.relations.BankWithAccounts
import kotlinx.coroutines.flow.Flow

class BanksLocalDataSource(private val database: CATSDatabase) {
    fun getBanksWithAccounts(): Flow<List<BankWithAccounts>> =
        database.bankDao.queryBanksWithAccounts()

    fun getAccountWithOperations(id: Long): Flow<AccountWithOperations> =
        database.accountDao.queryAccountWithOperations(id)

    suspend fun upsertBanksData(bankWithAccounts: List<BankWithAccounts>) {
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

        database.withTransaction {
            database.bankDao.insertBanks(bankEntities)
            database.accountDao.insertAccounts(accountEntities)
            database.operationDao.insertOperations(operationEntities)
        }
    }
}