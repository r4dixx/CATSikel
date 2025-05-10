package com.r4dixx.cats.domain.usecase

import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.repository.BanksRepository
import kotlinx.coroutines.flow.Flow

class GetBanksUseCase(private val repository: BanksRepository) {
    operator fun invoke(): Flow<List<Bank>> {
        return repository.getBanks()
    }
}
