package com.r4dixx.cats.design.components.scaffold

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.r4dixx.cats.design.components.topBar.CATSTopBarAnimated
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSTheme
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CATSSheetScaffold(
    sheetContent: @Composable () -> Unit,
    onBack: (() -> Unit)?,
    topBarText: String,
    modifier: Modifier = Modifier,
    initialSheetValue: SheetValue = SheetValue.Hidden,
    content: @Composable (PaddingValues) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val density = LocalDensity.current
    var topBarHeightDp by remember { mutableStateOf(0.dp) }

    LaunchedEffect(initialSheetValue) {
        when (initialSheetValue) {
            SheetValue.PartiallyExpanded -> sheetState.partialExpand()
            SheetValue.Expanded -> sheetState.expand()
            else -> Unit
        }
    }

    LaunchedEffect(sheetState, onBack) {
        snapshotFlow { sheetState.currentValue }
            .drop(1) // Skip the initial state
            .filter { it == SheetValue.Hidden }
            .collect { onBack?.invoke() }
    }

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(sheetState),
        modifier = modifier,
        sheetSwipeEnabled = true,
        sheetDragHandle = null,
        sheetPeekHeight = 0.dp,
        sheetShadowElevation = 0.dp,
        sheetTonalElevation = 0.dp,
        sheetShape = RectangleShape,
        sheetContainerColor = Color.Transparent,
        sheetContentColor = MaterialTheme.colorScheme.onSurface,
        topBar = {
            CATSTopBarAnimated(
                modifier = Modifier.onSizeChanged { size -> topBarHeightDp = with(density) { size.height.toDp() } },
                visible = sheetState.targetValue != SheetValue.Hidden,
                text = topBarText,
                onBack = onBack?.let { { coroutineScope.launch { sheetState.hide() } } }
            )
        },
        content = { paddingValues -> content(paddingValues) },
        sheetContent = {
            Box(Modifier.padding(top = topBarHeightDp + spacingDefault)) {
                sheetContent()
            }
        },
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun CATSSheetScaffoldPreview() {
    CATSTheme {
        CATSSheetScaffold(
            initialSheetValue = SheetValue.Expanded,
            sheetContent = { Text(text = "Sheet Content") },
            onBack = {},
            topBarText = "Top Bar",
            content = {}
        )
    }
}