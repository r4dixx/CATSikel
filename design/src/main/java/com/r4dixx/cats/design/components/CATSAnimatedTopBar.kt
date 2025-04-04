package com.r4dixx.cats.design.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.IntOffset

@Composable
fun CATSAnimatedTopBar(
    text: String,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(visible) { 
        isVisible = visible
    }

    val offsetY by animateFloatAsState(
        targetValue = if (isVisible) 0f else -100f,
        animationSpec = tween(),
        label = "slide_animation"
    )

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(),
        label = "fade_animation"
    )

    CATSTextGradient(
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .offset { IntOffset(0, offsetY.toInt()) }
            .alpha(alpha)
            .then(modifier)
    )
}