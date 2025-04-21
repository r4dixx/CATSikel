package com.r4dixx.cats.data.repository

import android.util.Log
import com.r4dixx.cats.data.api.source.BanksAPIDataSource
import com.r4dixx.cats.data.api.source.BanksAPIRawDataSource
import com.r4dixx.cats.data.local.source.BanksLocalDataSource
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.repository.BanksRepository

/**
 * A repository for retrieving banks.
 * It first tries to retrieve banks from the remote service (api).
 * If the remote service fails, it tries local storage (local).
 * If local fails or is empty, it tries the local fallback JSON file (raw).
 * Successfully fetched API or fallback data is stored locally.
 */
class BanksRepositoryImpl(
    private val api: BanksAPIDataSource,
    private val raw: BanksAPIRawDataSource,
    private val local: BanksLocalDataSource
) : BanksRepository {
    override suspend fun getBanks(): Result<List<Bank>> {
        // Try to fetch banks from the API
        Log.d("BanksRepositoryImpl", "Trying to fetch banks from API")
        val apiResult = api.getBanks().mapCatching { apiBanks ->
            val localBanks = apiBanks.map { it.toLocalBank() }
            Log.d("BanksRepositoryImpl", "Successfully fetched banks from API")
            local.upsertBanksData(localBanks).getOrThrow()
            Log.i("BanksRepositoryImpl", "Successfully inserted banks data into local storage")
            apiBanks.map { it.toDomainBank() }
        }

        if (apiResult.isSuccess) {
            Log.d("BanksRepositoryImpl", "Returning API result")
            return apiResult
        } else {
            Log.w("BanksRepositoryImpl", "Failed to fetch banks from API")
        }

        // Try to fetch banks from local storage
        Log.d("BanksRepositoryImpl", "Trying to fetch banks from local storage")
        val localResult = local.getBanksWithAccounts().mapCatching { localBanks ->
            Log.i("BanksRepositoryImpl", "Successfully fetched banks from local storage")
            localBanks.map { it.toDomainBank() }
        }

        if (localResult.isSuccess) {
            Log.d("BanksRepositoryImpl", "Returning local storage result")
            return localResult
        } else {
            Log.w("BanksRepositoryImpl", "Failed to fetch banks from local storage")
        }

        // Try to fetch banks from the fallback JSON file
        Log.d("BanksRepositoryImpl", "Trying to fetch banks from fallback JSON file")
        val rawResult = raw.getBanks().mapCatching { rawBanks ->
            Log.d("BanksRepositoryImpl", "Successfully fetched banks from fallback JSON file")
            val localBanks = rawBanks.map { it.toLocalBank() }
            local.upsertBanksData(localBanks).getOrThrow()
            Log.d("BanksRepositoryImpl", "Successfully inserted banks into local storage")
            rawBanks.map { it.toDomainBank() }
        }

        if (rawResult.isSuccess) {
            Log.i("BanksRepositoryImpl", "Returning fallback JSON file result")
            return rawResult
        } else {
            Log.w("BanksRepositoryImpl", "Failed to fetch banks from fallback JSON file: ${rawResult.exceptionOrNull()?.message}")
        }

        val finalException = apiResult.exceptionOrNull()
            ?: rawResult.exceptionOrNull()
            ?: Exception("Failed to fetch banks from all sources")

        return Result.failure(finalException)
    }
}