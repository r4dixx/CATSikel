package com.r4dixx.cats.design.components.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.core.ui.state.CATSState
import com.r4dixx.cats.design.theme.CATSTheme

@Composable
fun <T> CATSStatefulBox(
    state: CATSState<T>,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = { CATSProgress() },
    errorContent: @Composable (message: String?) -> Unit = { message -> CATSError(message) },
    emptyContent: @Composable () -> Unit = { CATSEmpty() },
    content: @Composable BoxScope. (data: T) -> Unit
) {
    when {
        state.isLoading -> LoadingBox(loadingContent, modifier)
        state.isSuccess -> {
            val data = state.dataOrNull
            if (data == null || (data is Collection<*> && data.isEmpty()) || (data is Map<*, *> && data.isEmpty())) {
                EmptyBox(emptyContent, modifier)
            } else {
                SuccessBox(content, data, modifier)
            }
        }

        state.isError -> {
            val errorState = state as? CATSState.Error
            ErrorBox(errorContent, errorState?.message, modifier)
        }
    }
}

@Composable
fun ErrorBox(
    errorContent: @Composable (message: String?) -> Unit,
    message: String?,
    modifier: Modifier = Modifier
) {
    Box(Modifier.fillMaxSize() then modifier) {
        errorContent(message)
    }
}

@Composable
private fun LoadingBox(
    loadingContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize() then modifier,
        contentAlignment = Alignment.Center
    ) {
        loadingContent()
    }
}

@Composable
private fun EmptyBox(
    emptyContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize() then modifier,
        contentAlignment = Alignment.Center
    ) {
        emptyContent()
    }
}

@Composable
fun <T> SuccessBox(
    content: @Composable (BoxScope.(data: T) -> Unit),
    data: T,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        content(data)
    }
}

// Previews

@Preview
@Composable
private fun CATSStatefulBoxLoadingPreview() {
    CATSTheme {
        CATSStatefulBox(state = CATSState.Loading) {}
    }
}

@Preview
@Composable
private fun CATSStatefulBoxEmptyPreview() {
    CATSTheme {
        CATSStatefulBox(state = CATSState.Success(null)) {}
    }
}

@Preview
@Composable
private fun CATSStatefulBoxSuccessPreview() {
    CATSTheme {
        CATSStatefulBox(
            state = CATSState.Success(
                listOf(
                    "Item 1",
                    "Item 2",
                    "Item 3",
                    "Item 4",
                    "Item 5"
                )
            )
        ) { data ->
            LazyColumn {
                items(data) { item ->
                    Text(text = item)
                }
            }
        }
    }
}

@Preview
@Composable
private fun CATSStatefulBoxErrorPreview() {
    CATSTheme {
        CATSStatefulBox(state = CATSState.Error("An error occurred")) {}
    }
}
