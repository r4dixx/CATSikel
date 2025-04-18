package com.r4dixx.cats.design.components.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.graphicsLayer
import com.r4dixx.cats.design.theme.CATSGradient

@Composable
fun CATSProgress(modifier: Modifier = Modifier) {
    val gradient = CATSGradient.default
    Box(
        modifier = Modifier.fillMaxSize() then modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.SrcAtop)
                    }
                }
        )
    }
}