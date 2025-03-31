package com.r4dixx.cats.ui.master

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.usecase.GetBanksUseCase

class MasterViewModel(getBanks: GetBanksUseCase) : CATSViewModel<MasterViewModel.Data>() {

    override val data = Data()

    init {
        getBanks()
            .onSuccess {
                val newData = data.copy(banks = it)
                val newState = State.Success(newData)
                updateState(newState)
            }
            .onFailure {
                // TODO: Handle error
            }
    }

    data class Data(val banks: List<Bank> = emptyList())
}
