package com.r4dixx.cats.ui.account

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.core.utils.toFormattedAmount
import com.r4dixx.cats.core.utils.toFormattedDate
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.model.Operation
import java.text.SimpleDateFormat
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
            accountOperations = account.operations.sorted().toOpeFormatted()
        )
        setSuccess(data)
    }

    @OptIn(ExperimentalTime::class)
    private fun List<Operation>.toOpeFormatted() = map {
        OpeFormatted(
            title = it.title,
            amount = it.amount.toFormattedAmount(),
            date = it.date.toFormattedDate(SimpleDateFormat.FULL),
        )
    }

    @OptIn(ExperimentalTime::class)
    private fun List<Operation>.sorted() = sortedWith(compareByDescending<Operation> { it.date }.thenBy { it.title })

    data class OpeFormatted(
        val title: String,
        val amount: String,
        val date: String,
    )

    data class Data(
        val bankName: String = "",
        val accountLabel: String = "",
        val accountBalance: String = "",
        val accountOperations: List<OpeFormatted> = emptyList(),
    )
}
