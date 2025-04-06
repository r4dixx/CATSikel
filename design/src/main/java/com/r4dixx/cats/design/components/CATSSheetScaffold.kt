package com.r4dixx.cats.design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.r4dixx.cats.design.R
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CATSSheetScaffold(
    topBarTitle: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        modifier = Modifier.systemBarsPadding().then(modifier),
        scaffoldState = scaffoldState,
        sheetTonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        sheetContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        sheetContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        sheetDragHandle = {},
        sheetContent = {
            Box(Modifier.padding(spacingDefault)) {
                sheetContent()
            }
        },
        content = { content() },
        topBar = {
            CATSTopBarAnimated(
                text = topBarTitle,
                navIcon = rememberVectorPainter(Icons.AutoMirrored.Filled.ArrowBack),
                navIconContentDesc = stringResource(R.string.cd_nav_back),
                navOnClick = onDismiss,
            )
        }
    )
}