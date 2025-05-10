package com.r4dixx.cats.design.components.topBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.R
import com.r4dixx.cats.design.components.CATSIconGradient
import com.r4dixx.cats.design.components.CATSTextGradient
import com.r4dixx.cats.design.theme.CATSDimension.iconSizeDefault
import com.r4dixx.cats.design.theme.CATSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CATSTopBar(
    text: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    onBack: (() -> Unit)?,
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent,),
        actions = actions,
        title = {
            CATSTextGradient(
                text = text,
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1
            )
        },
        navigationIcon = {
            onBack?.let {
                IconButton(onClick = it) {
                    CATSIconGradient(
                        modifier = Modifier.size(iconSizeDefault),
                        painter = rememberVectorPainter(Icons.AutoMirrored.Default.ArrowBack),
                        contentDescription = stringResource(R.string.cd_nav_back)
                    )
                }
            }
        },
    )
}

// Previews

@Preview
@Composable
private fun CATSTopBarPreview() {
    CATSTheme {
        CATSTopBar(text = "Title", onBack = {})
    }
}
