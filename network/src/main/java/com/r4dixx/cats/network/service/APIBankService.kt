package com.r4dixx.cats.network.service

import android.content.Context
import com.r4dixx.cats.network.R
import com.r4dixx.cats.network.model.APIResult
import kotlinx.serialization.json.Json

class APIBankService(private val context: Context) {
    fun getBanks(): Result<APIResult> = runCatching {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.decodeFromString<APIResult>(content)
        return@runCatching banks
    }
}
