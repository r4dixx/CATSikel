package com.r4dixx.cats.feature.account.model

import kotlinx.collections.immutable.ImmutableList

data class UIData(
    val bankName: String,
    val accountLabel: String,
    val accountBalance: String,
    val accountOperations: ImmutableList<UIOperation>,
)