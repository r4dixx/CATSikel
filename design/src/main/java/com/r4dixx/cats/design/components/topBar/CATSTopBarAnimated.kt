package com.r4dixx.cats.design.components.topBar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset

@Composable
fun CATSTopBarAnimated(
    text: String,
    visible: Boolean,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)?,
) {
    var hasAppeared by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        hasAppeared = true
    }

    val targetVisible = visible && hasAppeared

    val offsetY by animateFloatAsState(
        targetValue = if (targetVisible) 0f else -100f,
        animationSpec = tween(),
        label = "slide_animation"
    )

    val alpha by animateFloatAsState(
        targetValue = if (targetVisible) 1f else 0f,
        animationSpec = tween(),
        label = "fade_animation"
    )

    CATSTopBar(
        text = text,
        onBack = onBack,
        modifier = Modifier
            .offset { IntOffset(0, offsetY.toInt()) }
            .alpha(alpha)
                then modifier
    )
}

// Previews

@Preview
@Composable
private fun CATSTopBarAnimatedPreview() {
    CATSTopBarAnimated(text = "Title", visible = true, onBack = {})
}