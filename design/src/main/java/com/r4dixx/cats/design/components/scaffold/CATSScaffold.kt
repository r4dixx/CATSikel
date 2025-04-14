package com.r4dixx.cats.design.components.scaffold

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.r4dixx.cats.design.components.topBar.CATSTopBarAnimated

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
        content = { paddingValues -> content(paddingValues) }
    )
}