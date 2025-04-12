package com.r4dixx.cats.data.repository

import com.r4dixx.cats.data.mapper.toDomainBank
import com.r4dixx.cats.data.service.BanksLocalService
import com.r4dixx.cats.data.service.BanksRemoteService
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.repository.BanksRepository

/**
 * A repository for retrieving banks.
 * It first tries to retrieve banks from the remote service,
 * and if it fails, it tries to retrieve banks from the local service.
 */
class BanksRepositoryImpl(
    private val remoteService: BanksRemoteService,
    private val localService: BanksLocalService,
) : BanksRepository {
    override suspend fun getBanks(): Result<List<Bank>> {
        return remoteService
            .getBanks()
            .mapCatching { banks -> banks.distinct().map { it.toDomainBank() } }
            .recoverCatching { localService.getBanks().getOrThrow().distinct().map { it.toDomainBank() } }
    }
}
