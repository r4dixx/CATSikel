package com.r4dixx.cats.feature.account.mappers

import com.r4dixx.cats.core.utils.toFormattedAmount
import com.r4dixx.cats.core.utils.toFormattedDate
import com.r4dixx.cats.domain.model.Operation
import com.r4dixx.cats.feature.account.model.UIOperation
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.text.SimpleDateFormat
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun List<Operation>.toUIOperations() : ImmutableList<UIOperation>  = map {
    UIOperation(
        title = it.title,
        amount = it.amount.toFormattedAmount(),
        date = it.date.toFormattedDate(SimpleDateFormat.FULL),
    )
}.toImmutableList()