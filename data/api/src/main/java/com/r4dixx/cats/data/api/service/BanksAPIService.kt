package com.r4dixx.cats.data.api.service

import com.r4dixx.cats.data.api.model.APIBank
import de.jensklingenberg.ktorfit.http.GET
import kotlinx.coroutines.flow.Flow

interface BanksAPIService {
    @GET("banks.json")
    fun getBanks(): Flow<List<APIBank>>
}
