package com.r4dixx.cats.core.utils

import java.text.Normalizer

fun String.sanitized(): String = this
    .normalize()
    .replace(Regex("\\s+"), " ")
    .trim()
    .lowercase()

private fun String.normalize(): String = Normalizer.normalize(this, Normalizer.Form.NFKC)
