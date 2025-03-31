package com.r4dixx.cats.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class CATSViewModel<T> : ViewModel() {

    protected abstract val data: T

    private val _state = MutableStateFlow<State<T>>(State.Loading)
    val state = _state.asStateFlow()

    fun updateState(state: State<T>) {
        _state.value = state
    }

    sealed class State<out T> {
        data object Loading : State<Nothing>()
        data class Error(val throwable: Throwable) : State<Nothing>()
        data class Success<T>(val data: T) : State<T>()
    }
}
