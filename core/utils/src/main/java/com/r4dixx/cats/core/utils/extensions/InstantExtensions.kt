package com.r4dixx.cats.core.utils.extensions

import io.kotzilla.sdk.KotzillaSDK
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun Instant.toFormattedDate(dateFormat: Int = SimpleDateFormat.FULL): String {
    return try {
        val locale: Locale by inject(Locale::class.java)

        val date = Date(this.toEpochMilliseconds())
        val formatter = SimpleDateFormat.getDateInstance(dateFormat, locale)
        formatter.format(date)
    } catch (e: Exception) {
        KotzillaSDK.logError("Error formatting data", e)
        Instant.DISTANT_PAST.toString()
    }
}