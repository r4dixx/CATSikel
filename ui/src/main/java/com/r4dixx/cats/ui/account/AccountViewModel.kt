package com.r4dixx.cats.ui.account

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.core.utils.toFormattedAmount
import com.r4dixx.cats.core.utils.toFormattedDate
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Operation
import com.r4dixx.cats.ui.account.model.OperationUI
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.time.ExperimentalTime

class AccountViewModel(
    account: Account,
    private val locale: Locale
) : CATSViewModel<AccountViewModel.Data>() {

    override val data = Data()

    init {
        val newData = data.copy(
            label = account.label,
            balance = account.balance.toFormattedAmount(locale),
            operations = account.operations.sorted().toOperationsUI()
        )
        val newState = State.Success(newData)
        updateState(newState)
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
        val label: String = "",
        val balance: String = "",
        val operations: List<OperationUI> = emptyList(),
    )
}
