package com.r4dixx.cats.data.api.repository

import com.r4dixx.cats.data.api.mapper.toDomain
import com.r4dixx.cats.data.api.service.BanksService
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.repository.BanksRepository

class BanksRepositoryImpl(private val service: BanksService) : BanksRepository {
    override fun getBanks(): Result<List<Bank>> {
        return service.getBanks().mapCatching { banks -> banks.map { it.toDomain() } }
    }
}
