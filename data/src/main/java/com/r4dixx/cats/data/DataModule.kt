package com.r4dixx.cats.data

import com.r4dixx.cats.data.remote.service.APIBanksService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single { APIBanksService(androidApplication().applicationContext) }
}
