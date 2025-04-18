package com.r4dixx.cats.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.r4dixx.cats.design.theme.CATSDimension.cardHeightDefault
import com.r4dixx.cats.design.theme.CATSGradient

@Composable
fun CATSCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier
            .background(
                brush = CATSGradient.default,
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
