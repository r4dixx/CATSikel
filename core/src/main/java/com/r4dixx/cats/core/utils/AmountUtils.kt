package com.r4dixx.cats.core.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.toFormattedAmount(locale: Locale = Locale.getDefault()): String {
    return try {
        val currencyInstance = NumberFormat.getCurrencyInstance(locale)
        currencyInstance.format(this)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        "0,00 €"
    }
}
