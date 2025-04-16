package com.r4dixx.cats.data.api.repository

import com.r4dixx.cats.data.api.model.toDomainBank
import com.r4dixx.cats.data.api.source.BanksFallbackDataSource
import com.r4dixx.cats.data.api.source.BanksRemoteDataSource
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.repository.BanksRepository

/**
 * A repository for retrieving banks.
 * It first tries to retrieve banks from the remote service,
 * and if it fails, it tries to retrieve banks from fallback file.
 */
class BanksRepositoryImpl(
    private val fallback: BanksFallbackDataSource,
    private val remote: BanksRemoteDataSource,
) : BanksRepository {
    override suspend fun getBanks(): Result<List<Bank>> {
        return remote
            .getBanks()
            .mapCatching { banks -> banks.distinct().map { it.toDomainBank() } }
            .recoverCatching { fallback.getBanks().getOrThrow().distinct().map { it.toDomainBank() } }
    }
}
