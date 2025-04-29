package com.r4dixx.cats.design.components.scaffold

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.components.topBar.CATSTopBarAnimated
import com.r4dixx.cats.design.theme.CATSTheme

@Composable
fun CATSScaffold(
    onBack: (() -> Unit)?,
    topBarText: String,
    modifier: Modifier = Modifier,
    topBarActions: @Composable RowScope.() -> Unit = {},
    content: @Composable ((PaddingValues) -> Unit)
) {
    onBack?.let {
        BackHandler {
            it.invoke()
        }
    }

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface) then modifier,
        topBar = {
            CATSTopBarAnimated(
                visible = true,
                text = topBarText,
                onBack = onBack,
                actions = topBarActions
            )
        },
        content = { paddingValues -> content(paddingValues) }
    )
}

// Previews

@Preview
@Composable
private fun CATSScaffoldPreview() {
    CATSTheme {
        CATSScaffold(
            onBack = {},
            topBarText = "Top Bar",
            content = { paddingValues ->
                Text(
                    text = "Content",
                    modifier = Modifier.padding(paddingValues)
                )
            }
        )
    }
}