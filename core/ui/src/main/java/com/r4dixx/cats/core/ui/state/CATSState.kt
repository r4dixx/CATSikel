package com.r4dixx.cats.core.ui.state

sealed class CATSState<out T> {
    data object Loading : CATSState<Nothing>()
    data class Success<T>(val data: T) : CATSState<T>()
    data class Error(val message: String? = null, val throwable: Throwable? = null) :
        CATSState<Nothing>() {
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
