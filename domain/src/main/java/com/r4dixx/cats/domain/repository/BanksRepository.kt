package com.r4dixx.cats.domain.repository

import com.r4dixx.cats.data.remote.service.APIBanksService
import com.r4dixx.cats.domain.mapper.toDomain
import com.r4dixx.cats.domain.model.Bank


class BanksRepository(private val service: APIBanksService) {
    fun getBanks(): Result<List<Bank>> {
        return service.getBanks().mapCatching { banks -> banks.map { it.toDomain() } }
    }
}
