package com.r4dixx.cats.data.api.source

import com.r4dixx.cats.data.api.model.APIBank
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BanksRemoteDataSource(private val httpClient: HttpClient) {
    suspend fun getBanks(): Result<List<APIBank>> {
        return try {
            val response = httpClient.get(ENDPOINT_BANKS)
            val banks = response.body<List<APIBank>>()
            Result.success(banks)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        private const val ENDPOINT_BANKS = "banks.json"
    }
}
