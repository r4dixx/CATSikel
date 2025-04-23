package com.r4dixx.cats.core.ui.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CATSStateHandler<T>(initialState: CATSState<T> = CATSState.Loading) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<CATSState<T>> = _state.asStateFlow()

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
}
