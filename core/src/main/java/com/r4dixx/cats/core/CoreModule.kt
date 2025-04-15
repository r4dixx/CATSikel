package com.r4dixx.cats.core

import com.r4dixx.cats.core.data.CATSHttpClient
import io.ktor.client.HttpClient
import org.koin.dsl.module
import java.util.Locale

val coreModule = module {
    single { Locale.FRANCE }
    single<HttpClient> { CATSHttpClient().create() }
}