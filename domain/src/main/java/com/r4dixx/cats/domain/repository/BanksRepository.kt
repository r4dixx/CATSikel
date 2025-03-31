package com.r4dixx.cats.domain.repository

import com.r4dixx.cats.domain.model.Bank

interface BanksRepository {
    fun getBanks(): Result<List<Bank>>
}
