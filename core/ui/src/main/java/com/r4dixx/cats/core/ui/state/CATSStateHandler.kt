package com.r4dixx.cats.core.ui.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CATSStateHandler<T>(initialState: CATSState<T> = CATSState.Loading) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<CATSState<T>> = _state.asStateFlow()

    fun setLoading() {
        _state.value = CATSState.Loading
    }

    fun setSuccess(data: T) {
        _state.value = CATSState.Success(data)
    }

    fun setError(message: String) {
        _state.value = CATSState.Error(message)
    }

    fun setError(throwable: Throwable) {
        _state.value = CATSState.Error(throwable)
    }

    suspend fun emitLoading() {
        _state.emit(CATSState.Loading)
    }

    suspend fun emitSuccess(data: T) {
        _state.emit(CATSState.Success(data))
    }

    suspend fun emitError(message: String) {
        _state.emit(CATSState.Error(message))
    }

    suspend fun emitError(throwable: Throwable) {
        _state.emit(CATSState.Error(throwable))
    }

    fun updateState(transform: (CATSState<T>) -> CATSState<T>) {
        _state.update(transform) // Uses kotlinx.coroutines.flow.update
    }

    val current: CATSState<T>
        get() = _state.value
}
