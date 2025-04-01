package com.r4dixx.cats.core

import com.r4dixx.cats.core.utils.toFormattedAmount
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import java.math.BigDecimal
import java.util.Locale
import kotlin.test.Test

class AmountUtilsKtTest {

    private val originalLocale = Locale.getDefault()

    @Before
    fun setUp() {
        Locale.setDefault(Locale.FRANCE)
    }

    @After
    fun tearDown() {
        Locale.setDefault(originalLocale)
    }

    @Test
    fun positive_isCorrect() {
        val amount = BigDecimal("1234.56")
        val formattedAmount = amount.toFormattedAmount()
        assertEquals("1 234,56 €", formattedAmount)
    }

    @Test
    fun negative_isCorrect() {
        val amount = BigDecimal("-1234.56")
        val formattedAmount = amount.toFormattedAmount()
        assertEquals("-1 234,56 €", formattedAmount)
    }

    @Test
    fun zero_isCorrect() {
        val amount = BigDecimal("0.00")
        val formattedAmount = amount.toFormattedAmount()
        assertEquals("0,00 €", formattedAmount)
    }

    @Test
    fun customLocale_isCorrect() {
        val amount = BigDecimal("1234.56")
        val formattedAmount = amount.toFormattedAmount(Locale.CHINA)
        assertEquals("¥1,234.56", formattedAmount)
    }
}
