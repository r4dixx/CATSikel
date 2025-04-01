package com.r4dixx.cats.ui.account

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Operation
import java.math.BigDecimal

class AccountViewModel(account: Account) : CATSViewModel<AccountViewModel.Data>() {

    override val data = Data()

    init {
        val newData = Data(
            label = account.label,
            balance = account.balance.toCurrencyAmount(),
            operations = account.operations.toOperationUIList()
        )
        val newState = State.Success(newData)
        updateState(newState)
    }

    private fun BigDecimal.toCurrencyAmount(): String {
        // TODO
        return ""
    }

    private fun List<Operation>.toOperationUIList(): List<OperationUI> {
        // TODO
        return emptyList()
    }

    data class Data(
        val label: String = "",
        val balance: String = "",
        val operations: List<OperationUI> = emptyList(),
    )

    data class OperationUI(
        val title: String,
        val amount: String,
        val date: String,
    )
}
