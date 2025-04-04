package com.r4dixx.cats

import com.r4dixx.cats.ui.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel() }
}
