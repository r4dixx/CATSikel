package com.r4dixx.cats.data.remote.repository

import com.r4dixx.cats.data.local.service.BanksLocalService
import com.r4dixx.cats.data.remote.mapper.toDomain
import com.r4dixx.cats.data.remote.service.BanksRemoteService
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
            .mapCatching { banks -> banks.map { it.toDomain() } }
            .recoverCatching { localService.getBanks().getOrThrow().map { it.toDomain() } }
    }
}
