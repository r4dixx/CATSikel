package com.r4dixx.cats.data.remote.service

import android.content.Context
import com.r4dixx.cats.data.R
import com.r4dixx.cats.data.remote.model.APIBank
import kotlinx.serialization.json.Json

class APIBanksService(private val context: Context) {
    fun getBanks(): Result<List<APIBank>> = runCatching {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.decodeFromString<List<APIBank>>(content)
        return@runCatching banks
    }
}
