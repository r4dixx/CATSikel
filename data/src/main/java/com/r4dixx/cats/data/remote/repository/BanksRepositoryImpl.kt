package com.r4dixx.cats.data.remote.repository

import com.r4dixx.cats.data.local.service.BanksLocalService
import com.r4dixx.cats.data.remote.mapper.toDomain
import com.r4dixx.cats.data.remote.service.BanksRemoteService
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.repository.BanksRepository

class BanksRepositoryImpl(
    private val remoteService: BanksRemoteService,
    private val localService: BanksLocalService
) : BanksRepository {
    override suspend fun getBanks(): Result<List<Bank>> {
//       return remoteService.getBanks().mapCatching { response ->
//            response.banks.map { it.toDomain() }
//        }
        return localService.getBanks().mapCatching { response ->
            response.map { it.toDomain() }
        }
    }
}
