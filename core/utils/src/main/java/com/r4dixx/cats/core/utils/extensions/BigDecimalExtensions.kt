package com.r4dixx.cats.core.utils.extensions

import io.kotzilla.sdk.KotzillaSDK
import org.koin.java.KoinJavaComponent.inject
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.toFormattedAmount(): String {
    return try {
        val locale: Locale by inject(Locale::class.java)
        NumberFormat.getCurrencyInstance(locale).format(this)
    } catch (e: NumberFormatException) {
        KotzillaSDK.logError("Error formatting amount", e)
        "0,00 €"
    }
}
