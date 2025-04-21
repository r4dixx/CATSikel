package com.r4dixx.cats.design.components.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.R
import com.r4dixx.cats.design.theme.CATSTheme

@Composable
fun CATSError(message: String?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
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

// Previews

@Preview
@Composable
private fun CATSErrorPreview() {
    CATSTheme {
        CATSError(message = "42 is not the answer")
    }
}

@Preview
@Composable
private fun CATSErrorNullPreview() {
    CATSTheme {
        CATSError(message = null)
    }
}