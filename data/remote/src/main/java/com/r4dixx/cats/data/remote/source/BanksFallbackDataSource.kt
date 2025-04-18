package com.r4dixx.cats.data.remote.source

import android.content.Context
import com.r4dixx.cats.data.remote.model.APIBank
import com.r4dixx.cats.data.remote.R
import kotlinx.serialization.json.Json

/**
 * A local service for retrieving banks from a JSON file in the assets folder.
 */
class BanksFallbackDataSource(private val context: Context) {
    fun getBanks(): Result<List<APIBank>> = runCatching {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.Default.decodeFromString<List<APIBank>>(content)
        return@runCatching banks
    }
}
