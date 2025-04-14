package com.r4dixx.cats.feature.banks.data.datasource

import android.content.Context
import com.r4dixx.cats.feature.banks.R
import com.r4dixx.cats.feature.banks.data.model.APIBank
import kotlinx.serialization.json.Json

/**
 * A local service for retrieving banks from a JSON file in the assets folder.
 */
class BanksLocalDataSource(private val context: Context) {
    fun getBanks(): Result<List<APIBank>> = runCatching {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.Default.decodeFromString<List<APIBank>>(content)
        return@runCatching banks
    }
}
