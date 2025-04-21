package com.r4dixx.cats.data.local.source

import com.r4dixx.cats.data.local.database.CATSDatabase
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
        return try {
            database.upsertBanksData(bankWithAccounts)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
