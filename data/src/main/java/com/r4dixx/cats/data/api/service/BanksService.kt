package com.r4dixx.cats.data.api.service

import android.content.Context
import com.r4dixx.cats.data.R
import com.r4dixx.cats.data.api.model.Response
import kotlinx.serialization.json.Json

class BanksService(private val context: Context) {
    fun getResult(): Result<Response> = runCatching {
        val input = context.resources.openRawResource(R.raw.banks)
        val content = input.bufferedReader().use { it.readText() }
        val banks = Json.decodeFromString<Response>(content)
        return@runCatching banks
    }
}
