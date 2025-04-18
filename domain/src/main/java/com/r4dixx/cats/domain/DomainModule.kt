package com.r4dixx.cats.domain

import com.r4dixx.cats.domain.usecase.GetBanksUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetBanksUseCase(get()) }
}