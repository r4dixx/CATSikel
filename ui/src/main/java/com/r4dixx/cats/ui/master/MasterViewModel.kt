package com.r4dixx.cats.ui.master

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.core.utils.sanitized
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.usecase.GetBanksUseCase

class MasterViewModel(getBanks: GetBanksUseCase) : CATSViewModel<MasterViewModel.Data>() {

    override val data = Data()

    init {
        getBanks()
            .onSuccess { banks ->
                val newData = banks
                    .sanitized()
                    .withAccountsSanitized()
                    .toData()
                val newState = State.Success(newData)
                updateState(newState)
            }
            .onFailure {
                // TODO: Handle error
            }
    }

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
        return Data(banksCA, banksNotCA)
    }

    private fun List<Bank>.withAccountsSanitized() = map { bank ->
        val accounts = bank.accounts
            .distinctBy { it.id }
            .sortedBy { account -> account.label.sanitized() }
        bank.copy(accounts = accounts)
    }

    private fun List<Bank>.sanitized() = distinct().sortedBy { bank -> bank.name }

    data class Data(
        val banksCA: List<Bank> = emptyList(),
        val banksNotCA: List<Bank> = emptyList(),
    )
}
