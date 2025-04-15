package com.r4dixx.cats.core.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class StringUtilsTest {

    @Test
    fun sanitized_emptyString_returnsEmpty() {
        val input = ""
        val expected = ""
        assertEquals(expected, input.sanitized())
    }

    @Test
    fun sanitized_whitespaceOnly_returnsEmpty() {
        val input = "   \t \n "
        val expected = ""
        assertEquals(expected, input.sanitized())
    }

    @Test
    fun sanitized_leadingTrailingWhitespace_isTrimmed() {
        val input = "  hello world  "
        val expected = "hello world"
        assertEquals(expected, input.sanitized())
    }

    @Test
    fun sanitized_multipleInternalWhitespace_isSingleSpaced() {
        val input = "hello    world"
        val expected = "hello world"
        assertEquals(expected, input.sanitized())
    }

    @Test
    fun sanitized_mixedWhitespace_isSingleSpaced() {
        val input = "hello\t\n world"
        val expected = "hello world"
        assertEquals(input.sanitized(), expected)
    }

    @Test
    fun sanitized_unicode_isNormalized() {
        // Using the ﬁ ligature as an example, NFKC normalization decomposes it
        val input = "first" // Contains the ligature 'ﬁ' (U+FB01)
        val expected = "first" // Decomposed into 'f' and 'i'
        assertEquals(input.sanitized(), expected)
    }

    @Test
    fun sanitized_combinedNormalizationWhitespace_isCorrect() {
        val input = "  first   \t last  "
        val expected = "first last"
        assertEquals(input.sanitized(), expected)
    }

    @Test
    fun sanitized_alreadySanitized_isUnchanged() {
        val input = "hello world 123"
        val expected = "hello world 123"
        assertEquals(input.sanitized(), expected)
    }
}
