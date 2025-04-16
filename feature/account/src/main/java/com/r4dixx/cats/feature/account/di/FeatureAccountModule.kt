package com.r4dixx.cats.feature.account.di

import com.r4dixx.cats.core.state.CATSStateHandler
import com.r4dixx.cats.feature.account.ui.model.UIData
import com.r4dixx.cats.feature.account.ui.AccountViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureAccountModule = module {
    factory { CATSStateHandler<UIData>() }
    viewModel { AccountViewModel(get(), get(), get()) }
}
