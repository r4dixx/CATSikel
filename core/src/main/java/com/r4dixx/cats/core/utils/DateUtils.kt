package com.r4dixx.cats.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun Instant.toFormattedDate(
    dateFormat: Int = SimpleDateFormat.FULL,
    locale: Locale = Locale.getDefault(),
): String {
    val date = Date(this.toEpochMilliseconds())
    val formatter = SimpleDateFormat.getDateInstance(dateFormat, locale)
    return formatter.format(date)
}
