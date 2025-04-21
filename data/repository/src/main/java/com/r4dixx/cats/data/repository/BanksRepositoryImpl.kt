package com.r4dixx.cats.data.repository

import android.util.Log
import com.r4dixx.cats.data.api.source.BanksAPIDataSource
import com.r4dixx.cats.data.api.source.BanksAPIRawDataSource
import com.r4dixx.cats.data.local.source.BanksLocalDataSource
import com.r4dixx.cats.domain.model.Account
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
        val apiResult = api.getBanks().mapCatching { apiBanks ->
            val localBanks = apiBanks.map { it.toLocalBank() }
            Log.d("BanksRepositoryImpl", "Fetched banks from API")
            local.upsertBanksData(localBanks).getOrThrow()
            Log.i("BanksRepositoryImpl", "Upserted API banks data into local storage")
            apiBanks.map { it.toDomainBank() }
        }

        if (apiResult.isSuccess) {
            return apiResult
        } else {
            Log.w("BanksRepositoryImpl", "Failed to fetch banks from API: ${apiResult.exceptionOrNull()?.message}")
        }

        // Try to fetch banks from local storage
        val localResult = local.getBanksWithAccounts().mapCatching { localBanks ->
            Log.i("BanksRepositoryImpl", "Fetched banks from local storage")
            localBanks.map { it.toDomainBank() }
        }

        if (localResult.isSuccess) {
            // Only return if the list is not empty, otherwise try raw fallback
            localResult.getOrNull()?.takeIf { it.isNotEmpty() }?.let {
                Log.d("BanksRepositoryImpl", "Returning non-empty local storage result")
                return localResult
            } ?: Log.i("BanksRepositoryImpl", "Local storage was empty, trying fallback.")

        } else {
            Log.w("BanksRepositoryImpl", "Failed to fetch banks from local storage: ${localResult.exceptionOrNull()?.message}")
        }

        // Try to fetch banks from the fallback JSON file
        val rawResult = raw.getBanks().mapCatching { rawBanks ->
            Log.d("BanksRepositoryImpl", "Fetched banks from fallback JSON file")
            val localBanks = rawBanks.map { it.toLocalBank() }
            local.upsertBanksData(localBanks).getOrThrow()
            Log.i("BanksRepositoryImpl", "Upserted fallback banks data into local storage")
            rawBanks.map { it.toDomainBank() }
        }

        if (rawResult.isSuccess) {
            return rawResult
        } else {
            Log.w("BanksRepositoryImpl", "Failed to fetch banks from fallback JSON file: ${rawResult.exceptionOrNull()?.message}")
        }

        val finalException = apiResult.exceptionOrNull()
            ?: localResult.exceptionOrNull()
            ?: rawResult.exceptionOrNull()
            ?: IllegalStateException("Failed to fetch banks from all sources")

        Log.e("BanksRepositoryImpl", "All sources failed to provide banks data", finalException)
        return Result.failure(finalException)
    }

    override suspend fun getAccount(id: Long): Result<Account> {
        return local.getAccountWithOperations(id).mapCatching { it.toDomainAccount() }
    }
}