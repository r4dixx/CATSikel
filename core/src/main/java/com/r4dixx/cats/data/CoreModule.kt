package com.r4dixx.cats.data

import com.r4dixx.cats.data.network.AppHTTPClient
import org.koin.dsl.module

val coreModule = module {
    single { AppHTTPClient() }
}
