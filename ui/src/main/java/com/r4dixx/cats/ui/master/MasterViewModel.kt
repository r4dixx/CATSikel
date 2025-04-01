package com.r4dixx.cats.ui.master

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.usecase.GetBanksUseCase

class MasterViewModel(getBanks: GetBanksUseCase) : CATSViewModel<MasterViewModel.Data>() {

    override val data = Data()

    init {
        getBanks()
            .onSuccess {
                val newData = it.sorted().toData()
                val newState = State.Success(newData)
                updateState(newState)
            }
            .onFailure {
                // TODO: Handle error
            }
    }

    private fun List<Bank>.sorted() = sortedBy { bank -> bank.name }

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

    data class Data(
        val banksCA: List<Bank> = emptyList(),
        val banksNotCA: List<Bank> = emptyList(),
    )
}
