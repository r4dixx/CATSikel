package com.r4dixx.cats.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class CATSViewModel<T> : ViewModel() {
    
    private val _state = MutableStateFlow<State<T>>(State.Loading)
    val state: StateFlow<State<T>> = _state.asStateFlow()
    
    protected fun setLoading() {
        _state.value = State.Loading
    }
    
    protected fun setSuccess(data: T) {
        _state.value = State.Success(data)
    }
    
    protected fun setError(errorMessage: String) {
        _state.value = State.Error(errorMessage)
    }

    sealed class State<out T> {
        data object Loading : State<Nothing>()
        data class Success<T>(val data: T) : State<T>()
        data class Error(val message: String) : State<Nothing>()
    }
}