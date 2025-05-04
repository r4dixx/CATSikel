package com.r4dixx.cats.data.repository

import com.r4dixx.cats.data.api.source.BanksAPIDataSource
import com.r4dixx.cats.data.api.source.BanksAPIRawDataSource
import com.r4dixx.cats.data.local.source.BanksLocalDataSource
import com.r4dixx.cats.data.repository.BanksRepositoryImpl.DataSource.API
import com.r4dixx.cats.data.repository.BanksRepositoryImpl.DataSource.LOCAL
import com.r4dixx.cats.data.repository.BanksRepositoryImpl.DataSource.RAW
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.repository.BanksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import logcat.LogPriority
import logcat.logcat

class BanksRepositoryImpl(
    private val api: BanksAPIDataSource,
    private val local: BanksLocalDataSource,
    private val raw: BanksAPIRawDataSource,
) : BanksRepository {

    override fun getAccount(id: Long): Flow<Account> =
        local.getAccountWithOperations(id).map { localAccount ->
            localAccount.toDomainAccount()
        }.catch { e ->
            logcat(LogPriority.ERROR) { "Failed to fetch account details for id: $id" }
            throw DataSourceException("Failed to fetch account details from local source.", e)
        }

    override fun getBanks(): Flow<List<Bank>> = flow {
        emitAll(getBanksFromApi())
    }.catch {
        emitAll(getBanksFromLocal())
    }.catch {
        emitAll(getBanksFromRaw())
    }.catch { rawError ->
        logcat(LogPriority.ERROR) { "$RAW fetch failed: ${rawError.message}. All sources failed." }
        throw DataSourceException("Failed to fetch banks from all sources.", rawError)
    }

    private fun getBanksFromApi(): Flow<List<Bank>> =
        processAndCacheRemoteSource(api.getBanks(), API) { it.toDomainBank() }

    private fun getBanksFromLocal(): Flow<List<Bank>> =
        local.getBanksWithAccounts().map { localBanksWithAccounts ->
            localBanksWithAccounts
                .map { it.toDomainBank() }
                .takeIf { it.isNotEmpty() } ?: throw EmptyDataSourceException(LOCAL)
        }

    private fun getBanksFromRaw(): Flow<List<Bank>> =
        processAndCacheRemoteSource(raw.getBanks(), RAW) { it.toDomainBank() }

    private fun <T> processAndCacheRemoteSource(
        sourceFlow: Flow<List<T>>,
        dataSource: DataSource,
        mapSourceToDomain: (T) -> Bank
    ): Flow<List<Bank>> = sourceFlow.map { sourceData ->
        val domainBanks = sourceData
            .map(mapSourceToDomain)
            .takeIf { it.isNotEmpty() } ?: throw EmptyDataSourceException(dataSource)

        try {
            val localBanks = domainBanks.map { it.toLocalBank() }
            local.upsertBanksData(localBanks)
        } catch (e: Exception) {
            logcat(LogPriority.ERROR) { "Failed to update cache from $dataSource source." }
            throw CacheUpdateException(e)
        }
        domainBanks
    }

    private class EmptyDataSourceException(dataSource: DataSource) : DataSourceException("$dataSource source is empty")
    private class CacheUpdateException(cause: Throwable) : DataSourceException("Failed to update local cache", cause)
    private open class DataSourceException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
    
    enum class DataSource { API, LOCAL, RAW }
}