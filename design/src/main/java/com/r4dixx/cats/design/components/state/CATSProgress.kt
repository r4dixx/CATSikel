package com.r4dixx.cats.design.components.state

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.theme.CATSGradient
import com.r4dixx.cats.design.theme.CATSTheme

@Composable
fun CATSProgress(modifier: Modifier = Modifier) {
    val gradient = CATSGradient.default
    CircularProgressIndicator(
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

@Preview
@Composable
private fun CATSProgressPreview() {
    CATSTheme {
        CATSProgress()
    }
}