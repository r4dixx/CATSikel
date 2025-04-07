package com.r4dixx.cats.design.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CATSSheetScaffold(
    topBarText: String,
    onDismiss: () -> Unit,
    onBackClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    sheetContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.systemBarsPadding().then(modifier),
        sheetDragHandle = {},
        sheetTonalElevation = 0.dp,
        sheetShadowElevation = 0.dp,
        sheetContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        sheetContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        topBar = { CATSTopBarAnimated(text = topBarText, onBackClick = onBackClick) },
        content = {
            Column(Modifier.fillMaxSize()) {
                content()
                Spacer(Modifier.weight(1f))
                CATSBottomBarAnimated()
            }
        },
        sheetContent = { sheetContent() },
    )
    // onDismiss()

}