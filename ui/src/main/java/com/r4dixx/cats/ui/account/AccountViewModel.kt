package com.r4dixx.cats.ui.account

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.core.utils.toFormattedAmount
import com.r4dixx.cats.core.utils.toFormattedDate
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.model.Operation
import com.r4dixx.cats.ui.account.model.OperationUI
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.time.ExperimentalTime

class AccountViewModel(
    bank: Bank,
    account: Account,
    private val locale: Locale
) : CATSViewModel<AccountViewModel.Data>() {

    init {
        val data = Data(
            bankName = bank.name,
            accountLabel = account.label,
            accountBalance = account.balance.toFormattedAmount(locale),
            accountOperations = account.operations.sorted().toOperationsUI()
        )
        setSuccess(data)
    }

    /**
     * Map [Operation] to [OperationUI]
     * Converts amount to formatted [String]
     * Converts [Operation.date] to [SimpleDateFormat.FULL]
     * @return List of [OperationUI]
     */
    @OptIn(ExperimentalTime::class)
    private fun List<Operation>.toOperationsUI() = map {
        OperationUI(
            title = it.title,
            amount = it.amount.toFormattedAmount(locale),
            date = it.date.toFormattedDate(SimpleDateFormat.FULL, locale),
        )
    }

    /**
     * Sort by date descending.
     * If date is the same, sort by title
     */
    @OptIn(ExperimentalTime::class)
    private fun List<Operation>.sorted() = sortedWith(
        compareByDescending<Operation> { it.date }.thenBy { it.title }
    )

    data class Data(
        val bankName: String = "",
        val accountLabel: String = "",
        val accountBalance: String = "",
        val accountOperations: List<OperationUI> = emptyList(),
    )
}
