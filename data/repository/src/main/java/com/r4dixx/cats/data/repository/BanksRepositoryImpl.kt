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
            val banks = apiBanks.map { it.toLocalBank() }
            Log.d("BanksRepositoryImpl", "Successfully fetched banks from API")
//            local.insertBanks(banks)
//            Log.d("BanksRepositoryImpl", "Successfully inserted banks into local storage")
            apiBanks.map { it.toDomainBank() }
        }
        if (apiResult.isSuccess) {
            Log.d("BanksRepositoryImpl", "Returning API result")
            return apiResult
        }

        // Try to fetch banks from local storage
        Log.d("BanksRepositoryImpl", "Trying to fetch banks from local storage")
        val localResult = local.getBanks().mapCatching { localBanks ->
            Log.d("BanksRepositoryImpl", "Successfully fetched banks from local storage")
            localBanks.map { it.toDomainBank() }
        }
        if (localResult.isSuccess) {
            Log.d("BanksRepositoryImpl", "Returning local storage result")
            return localResult
        }

        // Try to fetch banks from the fallback JSON file
        Log.d("BanksRepositoryImpl", "Trying to fetch banks from fallback JSON file")
        val rawResult = raw.getBanks().mapCatching { rawBanks ->
            Log.d("BanksRepositoryImpl", "Successfully fetched banks from fallback JSON file")
            val banks = rawBanks.map { it.toLocalBank() }
//            local.insertBanks(banks)
//            Log.d("BanksRepositoryImpl", "Successfully inserted banks into local storage")
            rawBanks.map { it.toDomainBank() }
        }
        if (rawResult.isSuccess) {
            Log.d("BanksRepositoryImpl", "Returning fallback JSON file result")
            return rawResult
        }

        return Result.failure(Exception("Failed to fetch banks from all sources"))
    }
}