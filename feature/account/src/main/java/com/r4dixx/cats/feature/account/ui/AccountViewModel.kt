package com.r4dixx.cats.feature.account.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r4dixx.cats.core.ui.state.CATSState
import com.r4dixx.cats.core.ui.state.CATSStateHandler
import com.r4dixx.cats.core.utils.extensions.toFormattedAmount
import com.r4dixx.cats.domain.model.Operation
import com.r4dixx.cats.domain.usecase.GetAccountUseCase
import com.r4dixx.cats.feature.account.model.UIData
import com.r4dixx.cats.feature.account.model.toUIOperations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

class AccountViewModel(
    accountId: Long?,
    private val stateHandler: CATSStateHandler<UIData>,
    private val getAccount: GetAccountUseCase
) : ViewModel() {

    val state: StateFlow<CATSState<UIData>> = stateHandler.state

    init {
        fetchAccount(accountId)
    }

    private fun fetchAccount(accountId: Long?) {
        if (accountId == null) {
            stateHandler.setError(NullPointerException("Account id is null"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            getAccount(accountId).onSuccess {
                val uiData = UIData(
                    bankName = it.bankName.orEmpty(),
                    accountLabel = it.label,
                    accountBalance = it.balance.toFormattedAmount(),
                    accountOperations = it.operations.sorted().toUIOperations()
                )
                Log.d("AccountViewModel", "uiData: $uiData")
                stateHandler.setSuccess(uiData)
            }.onFailure { error ->
                stateHandler.setError(error)
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun List<Operation>.sorted() = sortedWith(compareByDescending<Operation> { it.date }.thenBy { it.title })
}
