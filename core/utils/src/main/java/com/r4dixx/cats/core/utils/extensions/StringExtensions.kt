package com.r4dixx.cats.core.utils.extensions

import java.text.Normalizer

/**
 * Returns a sanitized version of the string.
 *
 * The sanitization process includes:
 * - Normalizing the string using Unicode normalization.
 * - Replacing multiple whitespace characters with a single space.
 * - Trimming leading and trailing whitespace.
 *
 * @receiver The input string to be sanitized.
 * @return The sanitized string.
 */
fun String.sanitized(): String = this
    .normalize()
    .replace(Regex("\\s+"), " ")
    .trim()

private fun String.normalize(): String = Normalizer.normalize(this, Normalizer.Form.NFKC)
