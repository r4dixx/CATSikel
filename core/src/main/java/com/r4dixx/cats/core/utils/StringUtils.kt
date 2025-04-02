package com.r4dixx.cats.core.utils

import java.text.Normalizer

/**
 * Returns a sanitized version of the string.
 *
 * The sanitization process includes:
 * - Normalizing the string using Unicode normalization.
 * - Replacing multiple whitespace characters with a single space.
 * - Trimming leading and trailing whitespace.
 * - Converting the string to lowercase.
 *
 * @receiver The input string to be sanitized.
 * @return The sanitized string.
 */
fun String.sanitized(): String = this
    .normalize()
    .replace(Regex("\\s+"), " ")
    .trim()
    .lowercase()

private fun String.normalize(): String = Normalizer.normalize(this, Normalizer.Form.NFKC)
