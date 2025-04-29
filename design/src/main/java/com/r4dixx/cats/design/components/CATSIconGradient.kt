package com.r4dixx.cats.design.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.design.theme.gradient

@Composable
fun CATSIconGradient(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    val gradient = MaterialTheme.gradient.default
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .graphicsLayer(alpha = 0.99f)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(gradient, blendMode = BlendMode.SrcAtop)
                }
            } then modifier
    )
}

// Previews

@Preview
@Composable
private fun CATSIconGradientPreview() {
    CATSTheme {
        CATSIconGradient(
            painter = rememberVectorPainter(Icons.Default.AccountCircle),
            contentDescription = "CATS Logo"
        )
    }
}
