package com.r4dixx.cats.design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.r4dixx.cats.design.theme.CATSDimension.sheetPaddingTop
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CATSSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val sheetProperties = ModalBottomSheetProperties(
        isAppearanceLightStatusBars = false,
        isAppearanceLightNavigationBars = true
    )

    ModalBottomSheet(
        properties = sheetProperties,
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        dragHandle = {},
        tonalElevation = 0.dp,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sheetPaddingTop)
                .padding(spacingDefault),
        ) {
            content()
        }
    }
}
