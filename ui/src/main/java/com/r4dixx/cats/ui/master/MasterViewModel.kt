package com.r4dixx.cats.ui.master

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.usecase.GetBanksUseCase

class MasterViewModel(getBanks: GetBanksUseCase) : CATSViewModel<MasterViewModel.Data>() {

    override val data = Data()

    init {
        getBanks()
            .onSuccess { banks ->
                val banksCA = mutableListOf<Bank>()
                val banksNotCA = mutableListOf<Bank>()
                banks.forEach {
                    if (it.isCA) {
                        banksCA.add(it)
                    } else {
                        banksNotCA.add(it)
                    }
                }

                val newData = data.copy(
                    banksCA = banksCA,
                    banksNotCA = banksNotCA
                )

                val newState = State.Success(newData)
                updateState(newState)
            }
            .onFailure {
                // TODO: Handle error
            }
    }

    data class Data(
        val banksCA: List<Bank> = emptyList(),
        val banksNotCA: List<Bank> = emptyList()
    )
}
