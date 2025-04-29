package com.r4dixx.cats.feature.account.ui

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r4dixx.cats.core.ui.state.CATSState
import com.r4dixx.cats.core.ui.state.CATSStateHandler
import com.r4dixx.cats.core.utils.extensions.toFormattedAmount
import com.r4dixx.cats.core.utils.extensions.toFormattedDate
import com.r4dixx.cats.domain.model.Operation
import com.r4dixx.cats.domain.usecase.GetAccountUseCase
import io.kotzilla.sdk.KotzillaSDK
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
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
        viewModelScope.launch(Dispatchers.IO) {
            when (accountId) {
                0L, null -> {
                    val exception = NullPointerException("Account id does not exist")
                    KotzillaSDK.logError("Error fetching account", exception)
                    stateHandler.emitError(exception)
                }
                else ->
                    getAccount(accountId)
                        .catch { error -> stateHandler.emitError(error) }
                        .collect { account ->
                            val uiData = UIData(
                                bankName = account.bankName.orEmpty(),
                                accountLabel = account.label,
                                accountBalance = account.balance.toFormattedAmount(),
                                accountOperations = account.operations.sorted().map { it.toUIOperation() }.toImmutableList()
                            )
                            stateHandler.emitSuccess(uiData)
                        }
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun List<Operation>.sorted() = sortedWith(compareByDescending<Operation> { it.date }.thenBy { it.title })

    // Model

    @Stable
    data class UIData(
        val bankName: String,
        val accountLabel: String,
        val accountBalance: String,
        val accountOperations: ImmutableList<UIOperation>,
    )

    @Stable
    data class UIOperation(
        val title: String,
        val amount: String,
        val date: String
    )

    // Mappers

    @OptIn(ExperimentalTime::class)
    private fun Operation.toUIOperation() = UIOperation(
        title = title,
        amount = amount.toFormattedAmount(),
        date = date.toFormattedDate(SimpleDateFormat.FULL),
    )
}

