package com.r4dixx.cats.data.repository

import com.r4dixx.cats.data.api.source.BanksAPIRawDataSource
import com.r4dixx.cats.data.local.source.BanksLocalDataSource
import com.r4dixx.cats.domain.repository.BanksRepository
import org.koin.dsl.module

val dataRepositoryModule = module {
    single<BanksRepository> {
        BanksRepositoryImpl(
//            api = get(),
            raw = get(),
            local = get()
        )
    }
}