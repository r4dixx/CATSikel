package com.r4dixx.cats.feature.banks.data

import com.r4dixx.cats.common.data.model.Bank
import com.r4dixx.cats.feature.banks.data.datasource.BanksLocalDataSource
import com.r4dixx.cats.feature.banks.data.datasource.BanksRemoteDataSource
import com.r4dixx.cats.feature.banks.domain.BanksRepository
import com.r4dixx.cats.feature.banks.domain.toDomainBank

/**
 * A repository for retrieving banks.
 * It first tries to retrieve banks from the remote service,
 * and if it fails, it tries to retrieve banks from the local service.
 */
class BanksRepositoryImpl(
    private val remoteService: BanksRemoteDataSource,
    private val localService: BanksLocalDataSource,
) : BanksRepository {
    override suspend fun getBanks(): Result<List<Bank>> {
        return remoteService
            .getBanks()
            .mapCatching { banks -> banks.distinct().map { it.toDomainBank() } }
            .recoverCatching { localService.getBanks().getOrThrow().distinct().map { it.toDomainBank() } }
    }
}
