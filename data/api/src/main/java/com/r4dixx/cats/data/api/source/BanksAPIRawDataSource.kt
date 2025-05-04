package com.r4dixx.cats.data.api.source

import android.content.Context
import android.content.res.Resources
import com.r4dixx.cats.data.api.R
import com.r4dixx.cats.data.api.model.APIBank
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import logcat.LogPriority
import logcat.asLog
import logcat.logcat
import java.io.IOException

/**
 * A fallback service for retrieving banks from a JSON file in the assets folder.
 */
class BanksAPIRawDataSource(private val context: Context) {
    fun getBanks(): Flow<List<APIBank>> = flow {
        val content: String
        try {
            context.resources.openRawResource(R.raw.banks).use { input ->
                content = input.bufferedReader().use { it.readText() }
            }
        } catch (e: Resources.NotFoundException) {
            logcat(LogPriority.ERROR) { "Raw resource not found. ${e.asLog()}"}
            throw e
        } catch (e: IOException) {
            logcat(LogPriority.ERROR) { "Error reading raw resource. ${e.asLog()}"}
            throw e
        }

        val banks = try {
            Json.Default.decodeFromString<List<APIBank>>(content).distinct()
        } catch (e: SerializationException) {
            logcat(LogPriority.ERROR) { "Error parsing JSON. ${e.asLog()}"}
            throw e
        }
        emit(banks)
    }.catch { e ->
        logcat(LogPriority.ERROR) { "Failed to get banks from raw resource. ${e.asLog()}"}
        emit(emptyList())
    }
}