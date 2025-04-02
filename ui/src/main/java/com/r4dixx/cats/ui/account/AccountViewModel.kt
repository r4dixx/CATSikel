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
            operations = account.operations.toOperationsUI().sorted()
        )
        val newState = State.Success(newData)
        updateState(newState)
    }

    @OptIn(ExperimentalTime::class)
    private fun List<Operation>.toOperationsUI() = map {
        OperationUI(
            title = it.title,
            amount = it.amount.toFormattedAmount(locale),
            date = it.date.toFormattedDate(SimpleDateFormat.SHORT, locale)
        )
    }

    private fun List<OperationUI>.sorted() = sortedWith(
        compareByDescending<OperationUI> { it.date }.thenBy { it.title }
    )

    data class Data(
        val label: String = "",
        val balance: String = "",
        val operations: List<OperationUI> = emptyList(),
    )
}
