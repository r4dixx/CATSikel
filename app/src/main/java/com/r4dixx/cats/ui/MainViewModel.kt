package com.r4dixx.cats.ui

import androidx.lifecycle.ViewModel
import com.r4dixx.cats.domain.model.Account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _selectedAccount = MutableStateFlow<Account?>(null)
    val selectedAccount: StateFlow<Account?> = _selectedAccount.asStateFlow()

    fun setSelectedAccount(account: Account) {
        _selectedAccount.value = account
    }
}