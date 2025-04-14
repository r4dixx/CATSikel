package com.r4dixx.cats.feature.banks.domain

import com.r4dixx.cats.common.data.model.Bank

class GetBanksUseCase(private val repository: BanksRepository) {
    suspend operator fun invoke(): Result<List<Bank>> {
        return repository.getBanks()
    }
}
