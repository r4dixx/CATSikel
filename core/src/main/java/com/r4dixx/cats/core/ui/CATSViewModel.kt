package com.r4dixx.cats.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class CATSViewModel<T> : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<T>>(UIState.Loading)
    val uiState: StateFlow<UIState<T>> = _uiState.asStateFlow()

    protected fun updateUiState(transform: (UIState<T>) -> UIState<T>) {
        _uiState.value = transform(_uiState.value)
    }

    protected suspend fun emitUiState(newState: UIState<T>) {
        _uiState.emit(newState)
    }

    protected fun setLoading() { _uiState.value = UIState.Loading }
    protected fun setSuccess(data: T) { _uiState.value = UIState.Success(data) }
    protected fun setError(message: String) { _uiState.value = UIState.Error(message) }
    protected fun setError(throwable: Throwable) { _uiState.value = UIState.Error(throwable) }

    protected suspend fun emitLoading() { _uiState.emit(UIState.Loading) }
    protected suspend fun emitSuccess(data: T) { _uiState.emit(UIState.Success(data)) }
    protected suspend fun emitError(message: String) { _uiState.emit(UIState.Error(message)) }
    protected suspend fun emitError(throwable: Throwable) { _uiState.emit(UIState.Error(throwable)) }

    sealed class UIState<out T> {
        data object Loading : UIState<Nothing>()
        data class Success<T>(val data: T) : UIState<T>()
        data class Error(val message: String? = null, val throwable: Throwable? = null) :
            UIState<Nothing>() {
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