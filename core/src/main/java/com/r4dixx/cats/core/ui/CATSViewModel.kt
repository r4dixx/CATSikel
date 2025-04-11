package com.r4dixx.cats.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class CATSViewModel<T> : ViewModel() {

    private val _state = MutableStateFlow<State<T>>(State.Loading)
    val state: StateFlow<State<T>> = _state.asStateFlow()

    protected fun updateState(transform: (State<T>) -> State<T>) {
        _state.value = transform(_state.value)
    }

    protected suspend fun emitState(newState: State<T>) {
        _state.emit(newState)
    }

    protected fun setLoading() { _state.value = State.Loading }
    protected fun setSuccess(data: T) { _state.value = State.Success(data) }
    protected fun setError(message: String) { _state.value = State.Error(message) }
    protected fun setError(throwable: Throwable) { _state.value = State.Error(throwable) }

    protected suspend fun emitLoading() { _state.emit(State.Loading) }
    protected suspend fun emitSuccess(data: T) { _state.emit(State.Success(data)) }
    protected suspend fun emitError(message: String) { _state.emit(State.Error(message)) }
    protected suspend fun emitError(throwable: Throwable) { _state.emit(State.Error(throwable)) }

    sealed class State<out T> {
        data object Loading : State<Nothing>()
        data class Success<T>(val data: T) : State<T>()
        data class Error(val message: String? = null, val throwable: Throwable? = null) :
            State<Nothing>() {
            constructor(message: String) : this(message = message, throwable = null)
            constructor(throwable: Throwable) : this(
                message = throwable.message,
                throwable = throwable
            )
        }

        val isSuccess: Boolean
            get() = this is Success

        val isError: Boolean
            get() = this is Error

        val isLoading: Boolean
            get() = this is Loading

        val dataOrNull: T?
            get() = if (this is Success) data else null
    }
}