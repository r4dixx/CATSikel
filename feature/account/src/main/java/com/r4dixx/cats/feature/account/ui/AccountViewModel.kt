package com.r4dixx.cats.feature.account.ui

import androidx.lifecycle.ViewModel
import com.r4dixx.cats.core.state.CATSState
import com.r4dixx.cats.core.state.CATSStateHandler
import com.r4dixx.cats.domain.model.Operation
import com.r4dixx.cats.feature.account.ui.model.UIData
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.ExperimentalTime

class AccountViewModel(
    stateHandler: CATSStateHandler<UIData>,
    bankName: String,
    accountId: Long
) : ViewModel() {

    val state: StateFlow<CATSState<UIData>> = stateHandler.state

    init {
        bankName
        accountId
        /*
        *             accountLabel = accountId.label,
            accountBalance = accountId.balance.toFormattedAmount(),
            accountOperations = accountId.operations.sorted().toUIOperations()*/
    }

    @OptIn(ExperimentalTime::class)
    private fun List<Operation>.sorted() =
        sortedWith(compareByDescending<Operation> { it.date }.thenBy { it.title })
}
