package com.r4dixx.cats.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val initialState: UIData = UIData(dynamicGradient = false)
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<UIData> = _state.asStateFlow()

    fun toggleDynamicColor() {
        viewModelScope.launch {
            val currentState = _state.value
            val newDynamicGradient = !currentState.dynamicGradient
            val newState = currentState.copy(dynamicGradient = newDynamicGradient)
            _state.emit(newState)
        }
    }

    data class UIData(
        val dynamicGradient: Boolean
    )
}