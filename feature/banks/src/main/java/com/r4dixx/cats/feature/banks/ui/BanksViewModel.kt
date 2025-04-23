package com.r4dixx.cats.feature.banks.ui

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r4dixx.cats.core.ui.state.CATSState
import com.r4dixx.cats.core.ui.state.CATSStateHandler
import com.r4dixx.cats.core.utils.extensions.sanitized
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.usecase.GetBanksUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
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
            banksCA = banksCA.map { it.toUIBank() }.toImmutableList(),
            banksNotCA = banksNotCA.map { it.toUIBank() }.toImmutableList()
        )
    }
    
    // Data

    @Stable
    data class UIData(
        val banksCA: ImmutableList<UIBank>,
        val banksNotCA: ImmutableList<UIBank>
    )

    @Stable
    data class UIBank(
        val name: String,
        val isCA: Boolean,
        val accounts: ImmutableList<UIAccount>
    )
    
    @Stable
    data class UIAccount(
        val id: Long,
        val label: String
    )
    
    // Mappers

    private fun Bank.toUIBank() = UIBank(
        name = name,
        isCA = isCA,
        accounts = accounts.map { it.toUIAccount() }.toImmutableList()
    )
    
    private fun Account.toUIAccount() = UIAccount(
        id = id,
        label = label.sanitized()
    )
}