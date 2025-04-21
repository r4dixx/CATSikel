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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BanksViewModel(
    private val stateHandler: CATSStateHandler<UIData>,
    private val getBanks: GetBanksUseCase
) : ViewModel() {

    val state: StateFlow<CATSState<UIData>> = stateHandler.state

    init {
        fetchBanks()
    }

    private fun fetchBanks() {
        viewModelScope.launch(Dispatchers.IO) {
            getBanks()
                .catch { error -> stateHandler.emitError(error) }
                .collect { banks ->
                    val uiData = banks.sorted().withAccountsSorted().toUIData()
                    stateHandler.emitSuccess(uiData)
                }
        }
    }

    private fun List<Bank>.sorted() = sortedBy { bank -> bank.name }

    private fun List<Bank>.withAccountsSorted() = map { bank ->
        val accounts = bank.accounts.sortedBy { account -> account.label.lowercase() }
        bank.copy(accounts = accounts)
    }

    private fun List<Bank>.toUIData(): UIData {
        val (banksCA, banksNotCA) = partition { it.isCA }
        return UIData(
            banksCA = banksCA.toUIBanks(),
            banksNotCA = banksNotCA.toUIBanks()
        )
    }
}