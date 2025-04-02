package com.r4dixx.cats.design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.r4dixx.cats.design.theme.Dimension
import com.r4dixx.cats.design.theme.Gradient

@Composable
fun CATSCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(Dimension.strokeDefault, Gradient.default),
        modifier = modifier
    ) {
        content()
    }
}
