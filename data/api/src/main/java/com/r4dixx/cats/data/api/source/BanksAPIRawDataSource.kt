package com.r4dixx.cats.data.api.source

import android.content.Context
import com.r4dixx.cats.data.api.R
import com.r4dixx.cats.data.api.model.APIBank
import kotlinx.serialization.json.Json

/**
 * A fallback service for retrieving banks from a JSON file in the assets folder.
 */
class BanksAPIRawDataSource(private val context: Context) {
    fun getBanks(): Result<List<APIBank>> = runCatching {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.Default.decodeFromString<List<APIBank>>(content).distinct()

        return if (banks.isNotEmpty()) {
            Result.success(banks)
        } else {
            Result.failure(Exception("BanksAPIRawDataSource - No banks found"))
        }
    }
}
