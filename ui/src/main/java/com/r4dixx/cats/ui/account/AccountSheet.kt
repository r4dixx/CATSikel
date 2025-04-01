package com.r4dixx.cats.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.r4dixx.cats.design.components.CATSBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    CATSBottomSheet(
        sheetState = sheetState,
        onDismiss = onDismiss,
        modifier = modifier
    ) {
        Column {
            Text("Hello World")
        }
    }
}
