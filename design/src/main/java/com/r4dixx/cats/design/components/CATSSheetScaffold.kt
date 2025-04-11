package com.r4dixx.cats.design.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.r4dixx.cats.design.theme.CATSDimension.sheetHeightDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CATSSheetScaffold(
    topBarText: String,
    onBack: (() -> Unit)?,
    modifier: Modifier = Modifier,
    initialSheetValue: SheetValue = SheetValue.Hidden,
    sheetContent: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val density = LocalDensity.current
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    var sheetHeightDp by remember { mutableStateOf(sheetHeightDefault) }

    val sheetState = rememberStandardBottomSheetState(
        initialValue = initialSheetValue,
        skipHiddenState = false
    )

    LaunchedEffect(initialSheetValue) {
        if (initialSheetValue == SheetValue.Expanded) {
            sheetState.show()
        }
    }

    onBack?.let {
        LaunchedEffect(sheetState.isVisible) {
            if (!sheetState.isVisible) it.invoke()
        }
    }

    BackHandler {
        coroutineScope.launch { sheetState.hide() }
    }

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(sheetState),
        modifier = modifier,
        sheetSwipeEnabled = true,
        sheetDragHandle = {},
        sheetPeekHeight = sheetHeightDp,
        sheetShape = BottomSheetDefaults.ExpandedShape,
        sheetContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        topBar = {
            CATSTopBarAnimated(
                text = topBarText,
                onBack = { coroutineScope.launch { sheetState.hide() } },
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    val topBarHeightDp = with(density) { coordinates.size.height.toDp() }
                    val availableHeight = (screenHeightDp - topBarHeightDp).coerceAtLeast(0.dp)
                    sheetHeightDp =
                        availableHeight.coerceAtLeast(BottomSheetDefaults.SheetPeekHeight) - spacingDefault
                }
            )
        },
        content = { paddingValues -> content(paddingValues) },
        sheetContent = {
            Box(
                Modifier
                    .navigationBarsPadding()
                    .padding(horizontal = spacingDefault)
            ) {
                sheetContent()
            }
        },
    )
}