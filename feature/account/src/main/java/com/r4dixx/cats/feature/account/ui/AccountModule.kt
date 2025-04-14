package com.r4dixx.cats.feature.account.ui

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureAccountModule = module {
    viewModel { AccountViewModel(get(), get()) }
}
