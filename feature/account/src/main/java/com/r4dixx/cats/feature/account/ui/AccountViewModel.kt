package com.r4dixx.cats.feature.account.ui

import com.r4dixx.cats.common.data.model.Account
import com.r4dixx.cats.common.data.model.Bank
import com.r4dixx.cats.common.data.model.Operation
import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.core.utils.toFormattedAmount
import kotlinx.collections.immutable.ImmutableList
import kotlin.time.ExperimentalTime

class AccountViewModel(
    bank: Bank,
    account: Account
) : CATSViewModel<AccountViewModel.Data>() {

    init {
        val data = Data(
            bankName = bank.name,
            accountLabel = account.label,
            accountBalance = account.balance.toFormattedAmount(),
            accountOperations = account.operations.sorted().toUIOperations()
        )
        setSuccess(data)
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
