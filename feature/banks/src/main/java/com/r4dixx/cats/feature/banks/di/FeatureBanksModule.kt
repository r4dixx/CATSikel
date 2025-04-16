package com.r4dixx.cats.feature.banks.di

import com.r4dixx.cats.core.state.CATSStateHandler
import com.r4dixx.cats.feature.banks.ui.BanksViewModel
import com.r4dixx.cats.feature.banks.ui.model.UIData
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureBanksModule = module {
    factory { CATSStateHandler<UIData>() }
    viewModel { BanksViewModel(get(), get()) }
}
