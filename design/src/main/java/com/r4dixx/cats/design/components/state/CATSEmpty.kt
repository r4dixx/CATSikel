package com.r4dixx.cats.design.components.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.r4dixx.cats.design.R

@Composable
fun CATSEmpty(modifier: Modifier = Modifier) {
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