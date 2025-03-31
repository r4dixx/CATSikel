package com.r4dixx.cats.data.api.service

import android.content.Context
import com.r4dixx.cats.data.R
import com.r4dixx.cats.data.api.model.Bank
import kotlinx.serialization.json.Json

class BanksService(private val context: Context) {
    // TODO get API if API fails get local file
    fun getBanks(): Result<List<Bank>> = runCatching {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.decodeFromString<List<Bank>>(content)
        return@runCatching banks
    }
}
