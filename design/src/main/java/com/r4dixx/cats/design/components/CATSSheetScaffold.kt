package com.r4dixx.cats.design.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CATSSheetScaffold(
    topBarText: String,
    onDismiss: (() -> Unit)?,
    modifier: Modifier = Modifier,
    sheetContent: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    var isDismissCalled by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { sheetValue ->
            if (sheetValue == SheetValue.Hidden && !isDismissCalled) {
                isDismissCalled = true
                onDismiss?.invoke()
            }
            true
        }
    )

    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    LaunchedEffect(Unit) {
        sheetState.expand()
    }

    LaunchedEffect(sheetState.currentValue) {
        if (sheetState.currentValue == SheetValue.Expanded) {
            isDismissCalled = false
        }
    }

    BackHandler {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    val screenHeightDp = LocalConfiguration.current.screenHeightDp
    var sheetHeightDp by remember { mutableStateOf(0.dp) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = modifier,
        sheetSwipeEnabled = true,
        sheetDragHandle = {},
        sheetPeekHeight = 0.dp,
        sheetShape = RectangleShape,
        sheetContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
        sheetContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        topBar = {
            CATSTopBarAnimated(
                text = topBarText,
                onBackClick = { coroutineScope.launch { sheetState.hide() } },
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    sheetHeightDp =
                        screenHeightDp.dp - coordinates.size.center.y.dp - spacingDefault * 2
                }
            )
        },
        content = {},
        sheetContent = {
            Box(
                Modifier
                    .systemBarsPadding()
                    .height(sheetHeightDp)
            ) {
                sheetContent()
            }
        },
    )
}