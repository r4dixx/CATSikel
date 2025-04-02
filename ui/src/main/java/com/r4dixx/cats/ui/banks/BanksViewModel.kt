package com.r4dixx.cats.ui.banks

import androidx.lifecycle.viewModelScope
import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.core.utils.sanitized
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.usecase.GetBanksUseCase
import kotlinx.coroutines.launch

class BanksViewModel(getBanks: GetBanksUseCase) : CATSViewModel<BanksViewModel.Data>() {

    override val data = Data()

    init {
        viewModelScope.launch {
            getBanks().onSuccess { banks ->
                val newData = banks
                    .sanitized()
                    .withAccountsSanitized()
                    .toData()
                val newState = State.Success(newData)
                updateState(newState)
            }
        }
    }

    /**
     * For each bank, check if it is CA or not and add it to the corresponding list.
     */
    private fun List<Bank>.toData(): Data {
        val banksCA = mutableListOf<Bank>()
        val banksNotCA = mutableListOf<Bank>()
        forEach { bank ->
            if (bank.isCA) {
                banksCA.add(bank)
            } else {
                banksNotCA.add(bank)
            }
        }

        val newData = data.copy(
            banksCA = banksCA,
            banksNotCA = banksNotCA
        )

        return newData
    }

    /**
     * Remove duplicate accounts and sort by label.
     */
    private fun List<Bank>.withAccountsSanitized() = map { bank ->
        val accounts = bank.accounts
            .distinctBy { it.id }
            .sortedBy { account -> account.label.sanitized() }
        bank.copy(accounts = accounts)
    }

    /**
     * Remove duplicate banks and sort by name.
     */
    private fun List<Bank>.sanitized() = distinct().sortedBy { bank -> bank.name }

    data class Data(
        val banksCA: List<Bank> = emptyList(),
        val banksNotCA: List<Bank> = emptyList(),
    )
}
