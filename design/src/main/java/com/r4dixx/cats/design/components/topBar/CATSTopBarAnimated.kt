package com.r4dixx.cats.design.components.topBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CATSTopBarAnimated(
    text: String,
    show: Boolean,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    onBack: (() -> Unit)?,
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(show) {
        visible = show
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
        modifier = modifier
    ) {
        CATSTopBar(
            text = text,
            actions = actions,
            onBack = onBack
        )
    }
}

// Previews

@Preview
@Composable
private fun CATSTopBarAnimatedPreview() {
    CATSTopBarAnimated(text = "Title", show = true, onBack = {})
}