package com.r4dixx.cats.design.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import com.r4dixx.cats.design.theme.CATSGradient

@Composable
fun CATSIconGradient(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    val gradient = CATSGradient.default
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
            }.then(modifier)
    )
}
