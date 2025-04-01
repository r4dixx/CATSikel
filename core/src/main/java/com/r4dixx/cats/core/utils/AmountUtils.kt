package com.r4dixx.cats.core.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.toFormattedAmount(locale: Locale = Locale.getDefault()): String {
    return try {
        NumberFormat.getCurrencyInstance(locale).format(this)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        "0,00 €"
    }
}
