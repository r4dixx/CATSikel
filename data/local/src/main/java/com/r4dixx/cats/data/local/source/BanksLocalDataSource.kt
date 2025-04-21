package com.r4dixx.cats.data.local.source

import com.r4dixx.cats.data.local.dao.BankDAO
import com.r4dixx.cats.data.local.entities.BankEntity
import com.r4dixx.cats.data.local.relations.LocalBank

class BanksLocalDataSource(private val bankDao: BankDAO) {
    suspend fun getBanks(): Result<List<LocalBank>> {
        return try {
            val banks = bankDao.queryBanks().distinct()
            Result.success(banks)
            if (banks.isNotEmpty()) {
                Result.success(banks)
            } else {
                Result.failure(Exception("BanksLocalDataSource - No banks found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

//    suspend fun insertBanks(banks: List<BankEntity>): Result<Unit> {
//        return try {
//            bankDao.insertBanks(banks)
//            Result.success(Unit)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
}
