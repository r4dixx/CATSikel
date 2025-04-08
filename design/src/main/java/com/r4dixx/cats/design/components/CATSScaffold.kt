package com.r4dixx.cats.design.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CATSScaffold(
    topBarText: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)?,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CATSTopBarAnimated(
                text = topBarText,
                onBackClick = onBackClick
            )
        },
        bottomBar = { CATSBottomBarAnimated() },
        content = { paddingValues -> content(paddingValues) }
    )
}