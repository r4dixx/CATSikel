package com.r4dixx.cats.ui

import com.r4dixx.cats.ui.feature.account.AccountViewModel
import com.r4dixx.cats.ui.feature.banks.BanksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { BanksViewModel(get()) }
    viewModel { AccountViewModel(get(), get()) }
}
