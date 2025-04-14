package com.r4dixx.cats.feature.account.ui

import com.r4dixx.cats.common.ui.utils.toFormattedAmount
import com.r4dixx.cats.common.ui.utils.toFormattedDate
import com.r4dixx.cats.common.data.model.Operation
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.text.SimpleDateFormat
import kotlin.time.ExperimentalTime

data class UIOperation(
    val title: String,
    val amount: String,
    val date: String
)

@OptIn(ExperimentalTime::class)
fun List<Operation>.toUIOperations() : ImmutableList<UIOperation> = map {
    UIOperation(
        title = it.title,
        amount = it.amount.toFormattedAmount(),
        date = it.date.toFormattedDate(SimpleDateFormat.FULL),
    )
}.toImmutableList()