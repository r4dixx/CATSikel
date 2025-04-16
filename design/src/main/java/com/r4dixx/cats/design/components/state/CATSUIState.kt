package com.r4dixx.cats.design.components.state

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.r4dixx.cats.core.ui.CATSViewModel

@Composable
fun <T> CATSUIState(
    uiState: CATSViewModel.UIState<T>,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = { CATSProgress() },
    errorContent: @Composable (message: String?) -> Unit = { message -> CATSError(message = message) },
    emptyContent: @Composable () -> Unit = { CATSEmpty() },
    content: @Composable (data: T) -> Unit
) {
    when {
        uiState.isLoading -> loadingContent()
        uiState.isSuccess -> {
            val data = uiState.dataOrNull
            if (data == null || (data is Collection<*> && data.isEmpty()) || (data is Map<*, *> && data.isEmpty())) {
                emptyContent()
            } else {
                Box(modifier) { content(data) }
            }
        }
        uiState.isError -> {
            val errorState = uiState as? CATSViewModel.UIState.Error
            errorContent(errorState?.message)
        }
    }
}
