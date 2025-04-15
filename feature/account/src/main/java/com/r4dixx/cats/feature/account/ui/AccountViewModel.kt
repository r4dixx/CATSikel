package com.r4dixx.cats.feature.account.ui

import com.r4dixx.cats.common.data.model.Operation
import com.r4dixx.cats.core.ui.CATSViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlin.time.ExperimentalTime

class AccountViewModel(
    bankName: String,
    accountId: Long
) : CATSViewModel<AccountViewModel.Data>() {

    init {
        bankName
        accountId
        /*
        *             accountLabel = accountId.label,
            accountBalance = accountId.balance.toFormattedAmount(),
            accountOperations = accountId.operations.sorted().toUIOperations()*/
    }

    @OptIn(ExperimentalTime::class)
    private fun List<Operation>.sorted() = sortedWith(compareByDescending<Operation> { it.date }.thenBy { it.title })

    data class Data(
        val bankName: String,
        val accountLabel: String,
        val accountBalance: String,
        val accountOperations: ImmutableList<UIOperation>,
    )
}
