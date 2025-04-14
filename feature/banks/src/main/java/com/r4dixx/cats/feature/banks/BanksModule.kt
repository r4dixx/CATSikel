package com.r4dixx.cats.feature.banks

import com.r4dixx.cats.ui.feature.banks.BanksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureBanksModule = module {
    viewModel { BanksViewModel(get()) }
}
