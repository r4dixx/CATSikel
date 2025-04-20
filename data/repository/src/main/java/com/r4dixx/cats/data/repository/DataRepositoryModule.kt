package com.r4dixx.cats.data.repository

import com.r4dixx.cats.domain.repository.BanksRepository
import org.koin.dsl.module

val dataRepositoryModule = module {
    single<BanksRepository> { BanksRepositoryImpl(get(), get(), get()) }
}