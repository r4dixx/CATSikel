package com.r4dixx.cats.core.utils.extensions

import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import java.math.BigDecimal
import java.util.Locale
import kotlin.test.Test

class BigDecimalExtensionsKtTest {

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single { Locale.FRANCE }
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun positive_isCorrect() {
        val amount = BigDecimal("1234.56")
        val formattedAmount = amount.toFormattedAmount()
        assertEquals("1\u202f234,56\u00a0€", formattedAmount)
    }

    @Test
    fun negative_isCorrect() {
        val amount = BigDecimal("-1234.56")
        val formattedAmount = amount.toFormattedAmount()
        assertEquals("-1\u202f234,56\u00a0€", formattedAmount)
    }

    @Test
    fun zero_isCorrect() {
        val amount = BigDecimal("0.00")
        val formattedAmount = amount.toFormattedAmount()
        assertEquals("0,00\u00a0€", formattedAmount)
    }

    @Test
    fun customLocale_isCorrect() {
        stopKoin()
        startKoin {
            modules(
                module {
                    single { Locale.CHINA }
                }
            )
        }
        val amount = BigDecimal("1234.56")
        val formattedAmount = amount.toFormattedAmount()
        assertEquals("¥1,234.56", formattedAmount)
    }
}
