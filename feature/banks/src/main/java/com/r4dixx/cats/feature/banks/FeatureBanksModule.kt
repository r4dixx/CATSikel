package com.r4dixx.cats.feature.banks

import com.r4dixx.cats.feature.banks.data.datasource.BanksLocalDataSource
import com.r4dixx.cats.feature.banks.data.datasource.BanksRemoteDataSource
import com.r4dixx.cats.feature.banks.data.BanksRepositoryImpl
import com.r4dixx.cats.feature.banks.domain.BanksRepository
import com.r4dixx.cats.feature.banks.domain.GetBanksUseCase
import com.r4dixx.cats.feature.banks.ui.BanksViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureBanksModule = module {
    viewModel { BanksViewModel(get()) }
    single { GetBanksUseCase(get()) }
    single< BanksRepository> { BanksRepositoryImpl(get(), get()) }
    single { BanksRemoteDataSource(get()) }
    single { BanksLocalDataSource(androidApplication().baseContext) }
}
