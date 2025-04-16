package com.r4dixx.cats.feature.account.di

import com.r4dixx.cats.feature.account.ui.AccountViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureAccountModule = module {
    viewModel { AccountViewModel(get(), get()) }
}
