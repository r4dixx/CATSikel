package com.r4dixx.cats.data.api.repository

import com.r4dixx.cats.data.api.mapper.toDomain
import com.r4dixx.cats.data.api.service.BanksService
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.repository.BanksRepository

// TODO get API if API fails get local file
class BanksRepositoryImpl(private val service: BanksService) : BanksRepository {
    override fun getBanks(): Result<List<Bank>> {
        return service.getResult().mapCatching { response ->
            response.banks.map { it.toDomain() }
        }
    }
}
