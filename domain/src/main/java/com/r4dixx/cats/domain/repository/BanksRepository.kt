package com.r4dixx.cats.domain.repository

import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank

interface BanksRepository {
    suspend fun getBanks(): Result<List<Bank>>
    suspend fun getAccount(id: Long): Result<Account>
}
