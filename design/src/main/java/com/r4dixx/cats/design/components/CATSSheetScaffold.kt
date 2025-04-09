package com.r4dixx.cats.design.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
    onDismiss: (() -> Unit)?,
    modifier: Modifier = Modifier,
    initialShow: Boolean = false,
    sheetContent: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val density = LocalDensity.current
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    var sheetHeightDp by remember { mutableStateOf(sheetHeightDefault) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(initialShow) {
        if (initialShow) sheetState.show()
    }

    var hasBeenShown by remember { mutableStateOf(false) }

    LaunchedEffect(sheetState.isVisible) {
        if (!sheetState.isVisible && hasBeenShown) {
            onDismiss?.invoke()
        } else if (sheetState.isVisible) {
            hasBeenShown = true
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
        sheetPeekHeight = 0.dp,
        sheetShape = BottomSheetDefaults.ExpandedShape,
        sheetContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        topBar = {
            CATSTopBarAnimated(
                text = topBarText,
                onBackClick = { coroutineScope.launch { sheetState.hide() } },
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    val topBarHeightPx = coordinates.size.height
                    val topBarHeightDp = with(density) { topBarHeightPx.toDp() }
                    val availableHeight = (screenHeightDp - topBarHeightDp).coerceAtLeast(0.dp)
                    sheetHeightDp = availableHeight - spacingDefault
                }
            )
        },
        content = { paddingValues -> content(paddingValues) },
        sheetContent = {
            Box(
                Modifier
                    .height(sheetHeightDp) // Apply the calculated height
                    .navigationBarsPadding()
                    .padding(horizontal = spacingDefault)
            ) {
                sheetContent()
            }
        },
    )
}