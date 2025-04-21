package com.r4dixx.cats.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Transaction
import androidx.room.TypeConverters
import com.r4dixx.cats.data.local.BuildConfig
import com.r4dixx.cats.data.local.dao.AccountDAO
import com.r4dixx.cats.data.local.dao.BankDAO
import com.r4dixx.cats.data.local.dao.OperationDAO
import com.r4dixx.cats.data.local.entities.AccountEntity
import com.r4dixx.cats.data.local.entities.BankEntity
import com.r4dixx.cats.data.local.entities.OperationEntity
import com.r4dixx.cats.data.local.relations.BankWithAccounts

@Database(
    entities = [BankEntity::class, AccountEntity::class, OperationEntity::class],
    version = BuildConfig.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(CATSTypeConverters::class)
abstract class CATSDatabase : RoomDatabase() {
    abstract val bankDao: BankDAO
    abstract val accountDao: AccountDAO
    abstract val operationDao: OperationDAO

    @Transaction
    open suspend fun upsertBanksData(banks: List<BankWithAccounts>) {
        val bankEntities = mutableListOf<BankEntity>()
        val accountEntities = mutableListOf<AccountEntity>()
        val operationEntities = mutableListOf<OperationEntity>()

        banks.forEach { localBank ->
            bankEntities.add(localBank.bank)
            localBank.accountsWithOperations.forEach { localAccount ->
                accountEntities.add(localAccount.account)
                operationEntities.addAll(localAccount.operations)
            }
        }

        bankDao.insertBanks(bankEntities)
        accountDao.insertAccounts(accountEntities)
        operationDao.insertOperations(operationEntities)
    }
}