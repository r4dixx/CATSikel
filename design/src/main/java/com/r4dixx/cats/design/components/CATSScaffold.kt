package com.r4dixx.cats.design.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun CATSScaffold(
    topBarText: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)?,
    content: @Composable ((PaddingValues) -> Unit)
) {
    var topBarVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        topBarVisible = true
    }

    onBack?.let {
        BackHandler {
            topBarVisible = false
            it.invoke()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CATSTopBarAnimated(
                isVisible = topBarVisible,
                text = topBarText,
                onBack = onBack
            )
        },
        bottomBar = { CATSBottomBarAnimated() },
        content = { paddingValues -> content(paddingValues) }
    )
}