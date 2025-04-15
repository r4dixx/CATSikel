package com.r4dixx.cats.common.ui.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.r4dixx.cats.common.ui.R
import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.design.theme.CATSGradient

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

@Composable
private fun CATSEmpty(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize() then modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.state_empty_text),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
private fun CATSError(message: String?, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize() then modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.state_error_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
        Text(
            text = message?.takeIf { it.isNotBlank() } ?: stringResource(R.string.state_error_desc_fallback),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun CATSProgress(modifier: Modifier = Modifier) {
    val gradient = CATSGradient.default
    Box(
        modifier = Modifier.fillMaxSize() then modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.SrcAtop)
                    }
                }
        )
    }
}
