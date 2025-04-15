package com.r4dixx.cats.core.utils

import com.r4dixx.cats.core.utils.toFormattedDate
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.test.Test
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class DateUtilsKtTest {

    private val originalLocale = Locale.getDefault()
    private val epochMilliSeconds = 1609459200000 // Friday, January 1, 2021

    @Before
    fun setUp() {
        Locale.setDefault(Locale.FRANCE)
    }

    @After
    fun tearDown() {
        Locale.setDefault(originalLocale)
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
        val instant = Instant.fromEpochMilliseconds(epochMilliSeconds)
        val expectedDate = "Friday, January 1, 2021"

        val formattedDate = instant.toFormattedDate(locale = Locale.US)
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
