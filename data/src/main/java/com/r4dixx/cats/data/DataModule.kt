package com.r4dixx.cats.data

import com.r4dixx.cats.data.repository.BanksRepositoryImpl
import com.r4dixx.cats.data.service.BanksLocalService
import com.r4dixx.cats.data.service.BanksRemoteService
import com.r4dixx.cats.domain.repository.BanksRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single { BanksLocalService(androidApplication().applicationContext) }
    single { BanksRemoteService(get()) }
    single<BanksRepository> { BanksRepositoryImpl(get(), get()) }
}
