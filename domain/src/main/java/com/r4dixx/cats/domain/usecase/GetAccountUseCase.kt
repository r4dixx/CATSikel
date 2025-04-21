package com.r4dixx.cats.domain.usecase

import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.repository.BanksRepository

class GetAccountUseCase(private val repository: BanksRepository) {
    suspend operator fun invoke(id: Long): Result<Account> {
        return repository.getAccount(id)
    }
}
