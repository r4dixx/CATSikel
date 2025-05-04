package com.r4dixx.cats.domain.repository

import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import kotlinx.coroutines.flow.Flow

interface BanksRepository {
    fun getAccount(id: Long): Flow<Account>
    fun getBanks(): Flow<List<Bank>>
}
