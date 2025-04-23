package com.r4dixx.cats.di

import com.r4dixx.cats.ui.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single { MainViewModel(get()) }
}