package com.r4dixx.cats.feature.account

import com.r4dixx.cats.feature.account.ui.AccountViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureAccountModule = module {
    viewModel { AccountViewModel(get(), get(), get()) }
}
