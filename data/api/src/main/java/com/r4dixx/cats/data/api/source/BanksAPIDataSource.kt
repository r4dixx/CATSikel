package com.r4dixx.cats.data.api.source

import com.r4dixx.cats.data.api.model.APIBank
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import logcat.LogPriority
import logcat.asLog
import logcat.logcat

class BanksAPIDataSource(private val httpClient: HttpClient) {
    companion object {
        private const val ENDPOINT_BANKS = "banks.json"
    }

    fun getBanks(): Flow<List<APIBank>> = flow {
        val response = httpClient.get(ENDPOINT_BANKS)
        val banks = response.body<List<APIBank>>()
        emit(banks)
    }.catch { e ->
        logcat(LogPriority.ERROR) {"Error fetching banks from API. ${e.asLog()}" }
        emit(emptyList())
    }
}
