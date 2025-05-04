package com.r4dixx.cats.data.api

import com.r4dixx.cats.data.api.service.createBanksAPIService
import com.r4dixx.cats.data.api.source.BanksAPIDataSource
import com.r4dixx.cats.data.api.source.BanksAPIRawDataSource
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.FlowConverterFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataApiModule = module {
    single { provideHttpClient() }
    single { provideKtorFit(get(), "https://cdf-test-mobile-default-rtdb.europe-west1.firebasedatabase.app/") }
    single { provideAPIService(get()) }
    single { BanksAPIDataSource(get()) }
    single { BanksAPIRawDataSource(androidContext()) }
}

fun provideHttpClient() = HttpClient(Android) {
    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
        )
    }
}

fun provideKtorFit(httpClient: HttpClient, url: String) = Ktorfit.Builder()
    .httpClient(httpClient)
    .converterFactories(FlowConverterFactory())
    .baseUrl(url)
    .build()

fun provideAPIService(ktorfit: Ktorfit) = ktorfit.createBanksAPIService()