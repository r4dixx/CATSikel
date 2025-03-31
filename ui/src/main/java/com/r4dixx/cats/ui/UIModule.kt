package com.r4dixx.cats.ui

import com.r4dixx.cats.ui.master.MasterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { MasterViewModel(get()) }
}
