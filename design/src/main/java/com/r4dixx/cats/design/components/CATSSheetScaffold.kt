package com.r4dixx.cats.design.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

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
    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { newValue ->
            if (newValue == SheetValue.Hidden ) onDismiss()
            true
        }
    )

    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            scaffoldState.bottomSheetState.expand()
        }
    }

    BackHandler {
        coroutineScope.launch {
            scaffoldState.bottomSheetState.hide()
            onDismiss()
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.systemBarsPadding().then(modifier),
        sheetDragHandle = {},
        sheetPeekHeight = 0.dp,
        sheetTonalElevation = 0.dp,
        sheetShadowElevation = 0.dp,
        sheetContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        sheetContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        topBar = { CATSTopBarAnimated(text = topBarText, onBackClick = onBackClick) },
        sheetContent = { sheetContent() },
        content = {
            Column(Modifier.fillMaxSize()) {
                content()
                Spacer(Modifier.weight(1f))
                CATSBottomBarAnimated()
            }
        },
    )
}