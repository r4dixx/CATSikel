package com.r4dixx.cats.feature.account.model

import androidx.compose.runtime.Stable
import com.r4dixx.cats.core.utils.extensions.toFormattedAmount
import com.r4dixx.cats.core.utils.extensions.toFormattedDate
import com.r4dixx.cats.domain.model.Operation
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.text.SimpleDateFormat
import kotlin.time.ExperimentalTime

@Stable
data class UIOperation(
    val title: String,
    val amount: String,
    val date: String
)

@OptIn(ExperimentalTime::class)
fun Operation.toUIOperation() = UIOperation(
    title = title,
    amount = amount.toFormattedAmount(),
    date = date.toFormattedDate(SimpleDateFormat.FULL),
)

fun List<Operation>.toUIOperations(): ImmutableList<UIOperation> = map { it.toUIOperation() }.toImmutableList()