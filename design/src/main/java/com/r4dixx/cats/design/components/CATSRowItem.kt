package com.r4dixx.cats.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.theme.CATSGradient

@Composable
fun CATSRowItem(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(brush = CATSGradient.default, shape = MaterialTheme.shapes.medium)
             then modifier
    ) {
        content()
    }
}

// Previews

@Preview
@Composable
private fun CATSRowItemPreview() {
    CATSRowItem {
        Text(text = "CATSRowItem")
    }
}