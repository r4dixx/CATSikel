package com.r4dixx.cats.feature.banks.model

import kotlinx.collections.immutable.ImmutableList

data class UIData(
    val banksCA: ImmutableList<UIBank>,
    val banksNotCA: ImmutableList<UIBank>
)