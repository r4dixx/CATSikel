package com.r4dixx.cats.feature.account.ui

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.core.utils.toFormattedAmount
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.model.Operation
import com.r4dixx.cats.feature.account.model.UIOperation
import com.r4dixx.cats.feature.account.mappers.toUIOperations
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
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
        val bankName: String = "",
        val accountLabel: String = "",
        val accountBalance: String = "",
        val accountOperations: ImmutableList<UIOperation> = persistentListOf(),
    )
}
