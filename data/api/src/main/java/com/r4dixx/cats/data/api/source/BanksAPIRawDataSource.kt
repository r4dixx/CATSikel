package com.r4dixx.cats.data.api.source

import android.content.Context
import com.r4dixx.cats.data.api.R
import com.r4dixx.cats.data.api.model.APIBank
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

/**
 * A fallback service for retrieving banks from a JSON file in the assets folder.
 */
class BanksAPIRawDataSource(private val context: Context) {
    fun getBanks(): Flow<List<APIBank>> = flow {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.Default.decodeFromString<List<APIBank>>(content).distinct()
        emit(banks)
    }
}
