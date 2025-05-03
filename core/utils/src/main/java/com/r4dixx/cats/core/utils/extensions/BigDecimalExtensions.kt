package com.r4dixx.cats.core.utils.extensions

import logcat.asLog
import logcat.logcat
import org.koin.java.KoinJavaComponent.inject
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.toFormattedAmount(): String {
    return try {
        val locale: Locale by inject(Locale::class.java)
        NumberFormat.getCurrencyInstance(locale).format(this)
    } catch (e: NumberFormatException) {
        logcat { e.asLog() }
        "0,00 €"
    }
}
