package com.r4dixx.cats.feature.banks.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r4dixx.cats.core.ui.state.CATSState
import com.r4dixx.cats.core.ui.state.CATSStateHandler
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.usecase.GetBanksUseCase
import com.r4dixx.cats.feature.banks.model.UIData
import com.r4dixx.cats.feature.banks.model.toUIBanks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BanksViewModel(
    stateHandler: CATSStateHandler<UIData>,
    getBanks: GetBanksUseCase
) : ViewModel() {

    val state: StateFlow<CATSState<UIData>> = stateHandler.state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getBanks().onSuccess { banks ->
                banks
                    .sorted()
                    .withAccounts()
                    .toData()
                    .also { data -> stateHandler.setSuccess(data) }
            }
        }
    }

    private fun List<Bank>.sorted() = sortedBy { bank -> bank.name }

    private fun List<Bank>.withAccounts() = map { bank ->
        val accounts = bank.accounts.sortedBy { account -> account.label.lowercase() }
        bank.copy(accounts = accounts)
    }

    private fun List<Bank>.toData(): UIData {
        val banksCA = mutableListOf<Bank>()
        val banksNotCA = mutableListOf<Bank>()
        forEach { bank ->
            if (bank.isCA) {
                banksCA.add(bank)
            } else {
                banksNotCA.add(bank)
            }
        }

        val data = UIData(
            banksCA = banksCA.toUIBanks(),
            banksNotCA = banksNotCA.toUIBanks()
        )

        return data
    }
}
