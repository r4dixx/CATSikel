package com.r4dixx.cats.design.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.r4dixx.cats.design.R
import com.r4dixx.cats.design.theme.CATSDimension.iconSizeDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CATSTopBar(
    text: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)?
) {
    TopAppBar(
        modifier = Modifier.then(modifier),
        title = {
            CATSTextGradient(
                text = text,
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1
            )
        }, navigationIcon = {
            onBackClick?.let {
                IconButton(
                    onClick = onBackClick
                ) {
                    CATSIconGradient(
                        modifier = Modifier.size(iconSizeDefault),
                        painter = rememberVectorPainter(Icons.AutoMirrored.Default.ArrowBack),
                        contentDescription = stringResource(R.string.cd_nav_back)
                    )
                }
            }
        }
    )
}