package com.r4dixx.cats.design.components.state

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.R
import com.r4dixx.cats.design.theme.CATSTheme

@Composable
fun CATSEmpty(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.state_empty_text),
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}

// Previews

@Preview
@Composable
private fun CATSEmptyPreview() {
    CATSTheme {
        CATSEmpty()
    }
}