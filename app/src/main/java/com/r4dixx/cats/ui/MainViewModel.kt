package com.r4dixx.cats.ui

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank

class MainViewModel: CATSViewModel<MainViewModel.Data>() {
    
    fun setBankAndAccount(bank: Bank, account: Account) {
        val data = Data(bank, account)
        setSuccess(data)
    }

    data class Data(
        val bank: Bank,
        val account: Account
    )
}