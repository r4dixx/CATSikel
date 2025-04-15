package com.r4dixx.cats.feature.banks.ui

import androidx.lifecycle.viewModelScope
import com.r4dixx.cats.common.data.model.Bank
import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.feature.banks.domain.GetBanksUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BanksViewModel(getBanks: GetBanksUseCase) : CATSViewModel<BanksViewModel.Data>() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getBanks().onSuccess { banks ->
                banks
                    .sorted()
                    .withAccounts()
                    .toData()
                    .also { data -> setSuccess(data) }
            }
        }
    }

    private fun List<Bank>.sorted() = sortedBy { bank -> bank.name }

    private fun List<Bank>.withAccounts() = map { bank ->
        val accounts = bank.accounts.sortedBy { account -> account.label.lowercase() }
        bank.copy(accounts = accounts)
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

        val data = Data (
            banksCA = banksCA.toUIBanks(),
            banksNotCA = banksNotCA.toUIBanks()
        )

        return data
    }

    data class Data(
        val banksCA: ImmutableList<UIBank>,
        val banksNotCA: ImmutableList<UIBank>
    )
}
