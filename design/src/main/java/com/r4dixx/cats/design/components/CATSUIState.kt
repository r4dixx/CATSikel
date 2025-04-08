package com.r4dixx.cats.design.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T> CATSUIState(
    state: StateFlow<CATSViewModel.State<T>>,
    loadingContent: @Composable () -> Unit = { /* Default loading UI */ },
    errorContent: @Composable (message: String?, throwable: Throwable?) -> Unit = { _, _ -> /* Default error UI */ },
    emptyContent: @Composable () -> Unit = { /* Default empty UI */ },
    content: @Composable (data: T) -> Unit
) {
    val uiState by state.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> loadingContent()
        uiState.isSuccess -> {
            val data = uiState.dataOrNull
            if (data != null) {
                if ((data is Collection<*> && data.isEmpty()) || (data is Map<*, *> && data.isEmpty())) {
                    emptyContent()
                } else {
                    content(data)
                }
            }
        }
        uiState.isError -> {
            val errorState = uiState as CATSViewModel.State.Error
            errorContent(errorState.message, errorState.throwable)
        }
    }
}