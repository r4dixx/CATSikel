package com.r4dixx.cats.domain.usecase

import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.repository.BanksRepository

class GetBanksUseCase(private val repository: BanksRepository) {
    operator fun invoke(): Result<List<Bank>> {
        return repository.getBanks()
    }
}
