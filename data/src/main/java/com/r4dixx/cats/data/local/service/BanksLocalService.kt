package com.r4dixx.cats.data.local.service

import android.content.Context
import com.r4dixx.cats.data.R
import com.r4dixx.cats.data.remote.model.Response
import kotlinx.serialization.json.Json

class BanksLocalService(private val context: Context) {
    fun getResult(): Result<Response> = runCatching {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.Default.decodeFromString<Response>(content)
        return@runCatching banks
    }
}
