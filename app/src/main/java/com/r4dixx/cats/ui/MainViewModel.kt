package com.r4dixx.cats.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val initialState: UIData = UIData(darkDynamicGradientEnabled = false)
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<UIData> = _state.asStateFlow()

    fun toggleDynamicColor() {
        viewModelScope.launch {
            val currentState = _state.value
            val newDarkDynamicGradient = !currentState.darkDynamicGradientEnabled
            val newState = _state.value.copy(darkDynamicGradientEnabled = newDarkDynamicGradient)
            _state.emit(newState)
        }
    }

    data class UIData(
        val darkDynamicGradientEnabled: Boolean
    )
}