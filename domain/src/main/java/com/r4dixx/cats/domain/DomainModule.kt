package com.r4dixx.cats.domain

import com.r4dixx.cats.domain.repository.BanksRepository
import com.r4dixx.cats.domain.usecase.GetBanksUseCase
import org.koin.dsl.module

val dataModule = module {
    single { BanksRepository(get()) }
    single { GetBanksUseCase(get()) }
}
