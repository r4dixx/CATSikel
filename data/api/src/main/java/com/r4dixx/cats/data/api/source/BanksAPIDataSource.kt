package com.r4dixx.cats.data.api.source

import com.r4dixx.cats.data.api.model.APIBank
import com.r4dixx.cats.data.api.service.BanksAPIService
import kotlinx.coroutines.flow.Flow

class BanksAPIDataSource(private val apiService: BanksAPIService) {
    fun getBanks(): Flow<List<APIBank>> = apiService.getBanks()
}
