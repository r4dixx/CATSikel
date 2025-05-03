package com.r4dixx.cats.domain

import com.r4dixx.cats.domain.repository.BanksRepository
import com.r4dixx.cats.domain.usecase.GetAccountUseCase
import com.r4dixx.cats.domain.usecase.GetBanksUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetAccountUseCase(repository = get()) }
    single { GetBanksUseCase(repository = get()) }
}