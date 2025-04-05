package com.r4dixx.cats.design.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault

@Composable
fun CATSScreen(
    topBarTitle: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier) {
        CATSAnimatedTopBar(
            text = topBarTitle,
            modifier = Modifier.padding(spacingDefault)
        )
        CATSSheet(onDismiss = onDismiss) {
            content()
        }
    }
}