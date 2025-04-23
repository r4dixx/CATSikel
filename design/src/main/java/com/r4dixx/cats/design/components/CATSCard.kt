package com.r4dixx.cats.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.theme.CATSDimension.cardHeightDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.design.theme.gradients

@Composable
fun CATSCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        modifier = Modifier
            .background(
                brush = MaterialTheme.gradients.default,
                shape = MaterialTheme.shapes.medium
            )
                then modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .heightIn(cardHeightDefault)
                .widthIn(cardHeightDefault)
        ) {
            content()
        }
    }
}

// Previews

@Preview
@Composable
private fun CATSCardPreview() {
    CATSTheme {
        CATSCard(onClick = { }) {
            Text(
                text = "CATSCard",
                modifier = Modifier.padding(spacingDefault)
            )
        }
    }
}