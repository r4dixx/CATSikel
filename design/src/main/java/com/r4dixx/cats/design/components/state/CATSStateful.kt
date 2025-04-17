package com.r4dixx.cats.design.components.state

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.r4dixx.cats.core.ui.state.CATSState

@Composable
fun <T> CATSStateful(
    state: CATSState<T>,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = { CATSProgress() },
    errorContent: @Composable (message: String?) -> Unit = { message -> CATSError(message = message) },
    emptyContent: @Composable () -> Unit = { CATSEmpty() },
    content: @Composable (data: T) -> Unit
) {
    when {
        state.isLoading -> loadingContent()
        state.isSuccess -> {
            val data = state.dataOrNull
            if (data == null || (data is Collection<*> && data.isEmpty()) || (data is Map<*, *> && data.isEmpty())) {
                emptyContent()
            } else {
                Box(modifier) { content(data) }
            }
        }
        state.isError -> {
            val errorState = state as? CATSState.Error
            errorContent(errorState?.message)
        }
    }
}
