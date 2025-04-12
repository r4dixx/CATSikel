package com.r4dixx.cats.data.service

import android.content.Context
import com.r4dixx.cats.data.R
import com.r4dixx.cats.data.model.APIBank
import kotlinx.serialization.json.Json

/**
 * A local service for retrieving banks from a JSON file in the assets folder.
 */
class BanksLocalService(private val context: Context) {
    fun getBanks(): Result<List<APIBank>> = runCatching {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.Default.decodeFromString<List<APIBank>>(content)
        return@runCatching banks
    }
}
