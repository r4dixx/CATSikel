package com.r4dixx.cats.ui

import androidx.lifecycle.ViewModel
import com.r4dixx.cats.common.data.model.Account
import com.r4dixx.cats.common.data.model.Bank
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<Data?>(null)
    val uiState: StateFlow<Data?> = _uiState.asStateFlow()

    fun setBankAndAccount(bank: Bank, account: Account) {
        _uiState.update { Data(bank, account) }
    }

    data class Data(
        val bank: Bank,
        val account: Account
    )
}