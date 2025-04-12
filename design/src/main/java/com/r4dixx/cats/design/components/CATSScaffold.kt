package com.r4dixx.cats.design.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CATSScaffold(
    onBack: (() -> Unit)?,
    topBarText: String,
    modifier: Modifier = Modifier,
    content: @Composable ((PaddingValues) -> Unit)
) {
    onBack?.let {
        BackHandler {
            it.invoke()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CATSTopBarAnimated(
                visible = true,
                text = topBarText,
                onBack = onBack
            )
        },
        bottomBar = { CATSBottomBarAnimated() },
        content = { paddingValues -> content(paddingValues) }
    )
}