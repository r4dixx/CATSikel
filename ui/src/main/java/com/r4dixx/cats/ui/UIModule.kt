package com.r4dixx.cats.ui

import com.r4dixx.cats.ui.account.AccountViewModel
import com.r4dixx.cats.ui.banks.BanksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { BanksViewModel(get()) }
    viewModel { AccountViewModel(get(), get()) }
}
