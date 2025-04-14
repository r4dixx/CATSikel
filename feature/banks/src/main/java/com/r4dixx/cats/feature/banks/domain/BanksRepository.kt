package com.r4dixx.cats.feature.banks.domain

import com.r4dixx.cats.common.data.model.Bank

interface BanksRepository {
    suspend fun getBanks(): Result<List<Bank>>
}
