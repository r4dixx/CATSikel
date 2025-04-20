package com.r4dixx.cats.data.api

import com.r4dixx.cats.data.api.source.BanksAPIDataSource
import com.r4dixx.cats.data.api.source.BanksAPIRawDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataApiModule = module {
    single { BanksAPIDataSource(get()) }
    single { BanksAPIRawDataSource(androidApplication().baseContext) }
    single {
        HttpClient(Android) {
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
            defaultRequest {
                url("https://cdf-test-mobile-default-rtdb.europe-west1.firebasedatabase.app/")
            }
        }
    }
}