package com.r4dixx.cats.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.r4dixx.cats.design.theme.CATSDimension.iconDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CATSTopBar(
    text: String,
    modifier: Modifier = Modifier,
    navIcon: Painter? = null,
    navIconContentDesc: String? = null,
    navOnClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier
            .padding(vertical = spacingDefault)
            .then(modifier),
        title = {
            CATSTextGradient(
                text = text,
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1
            )
        },
        navigationIcon = {
            navIcon?.let {
                IconButton(onClick = navOnClick) {
                    CATSIconGradient(
                        painter = navIcon,
                        contentDescription = navIconContentDesc,
                        modifier = Modifier.size(iconDefault)
                    )
                }
            }
        }
    )
}