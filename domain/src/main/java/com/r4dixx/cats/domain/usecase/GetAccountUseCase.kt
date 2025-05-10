package com.r4dixx.cats.domain.usecase

import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.repository.BanksRepository
import kotlinx.coroutines.flow.Flow

class GetAccountUseCase(private val repository: BanksRepository) {
    operator fun invoke(id: Long): Flow<Account> {
        return repository.getAccount(id)
    }
}
