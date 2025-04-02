package com.r4dixx.cats.data.local.service

import android.content.Context
import com.r4dixx.cats.data.R
import com.r4dixx.cats.data.remote.model.Bank
import kotlinx.serialization.json.Json

class BanksLocalService(private val context: Context) {
    fun getBanks(): Result<List<Bank>> = runCatching {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.Default.decodeFromString<List<Bank>>(content)
        return@runCatching banks
    }
}
