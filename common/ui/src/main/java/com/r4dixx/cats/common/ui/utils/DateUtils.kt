package com.r4dixx.cats.common.ui.utils

import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun Instant.toFormattedDate(dateFormat: Int = SimpleDateFormat.FULL): String {
    val locale: Locale by inject(Locale::class.java)

    val date = Date(this.toEpochMilliseconds())
    val formatter = SimpleDateFormat.getDateInstance(dateFormat, locale)
    return formatter.format(date)
}

