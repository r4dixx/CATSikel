package com.r4dixx.cats.data

import com.r4dixx.cats.data.repository.BanksRepositoryImpl
import com.r4dixx.cats.data.service.BanksLocalService
import com.r4dixx.cats.data.service.BanksRemoteService
import com.r4dixx.cats.domain.repository.BanksRepository
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

val dataModule = module {
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

    single { BanksLocalService(androidApplication().applicationContext) }
    single { BanksRemoteService(get()) }
    single<BanksRepository> { BanksRepositoryImpl(get(), get()) }
}
