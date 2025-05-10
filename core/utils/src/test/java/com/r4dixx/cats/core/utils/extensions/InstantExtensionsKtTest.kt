package com.r4dixx.cats.core.utils.extensions

import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.test.Test
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class InstantExtensionsKtTest {

    private val epochMilliSeconds = 1609459200000 // Friday, January 1, 2021

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
    fun full_isCorrect() {
        val instant = Instant.fromEpochMilliseconds(epochMilliSeconds)
        val expectedDate = "vendredi 1 janvier 2021"

        val formattedDate = instant.toFormattedDate()
        assertEquals(expectedDate, formattedDate)
    }

    @Test
    fun short_isCorrect() {
        val instant = Instant.fromEpochMilliseconds(epochMilliSeconds)
        val expectedDate = "01/01/2021"

        val formattedDate = instant.toFormattedDate(dateFormat = SimpleDateFormat.SHORT)
        assertEquals(expectedDate, formattedDate)
    }

    @Test
    fun customLocale_isCorrect() {
        stopKoin()
        startKoin {
            modules(
                module {
                    single { Locale.US }
                }
            )
        }
        val instant = Instant.fromEpochMilliseconds(epochMilliSeconds)
        val expectedDate = "Friday, January 1, 2021"

        val formattedDate = instant.toFormattedDate()
        assertEquals(expectedDate, formattedDate)
    }

    @Test
    fun leapYear_isHandled() {
        val leapYearMilliSeconds = 1582934400000 // Thursday, February 29, 2020
        val instant = Instant.fromEpochMilliseconds(leapYearMilliSeconds)
        val expectedDate = "samedi 29 février 2020"

        val formattedDate = instant.toFormattedDate()
        assertEquals(expectedDate, formattedDate)
    }
}
