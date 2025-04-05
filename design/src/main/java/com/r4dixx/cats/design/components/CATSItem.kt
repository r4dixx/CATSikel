package com.r4dixx.cats.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingSmall
import com.r4dixx.cats.design.theme.CATSGradient

@Composable
fun CATSItem(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacingSmall),
        modifier = Modifier
            .padding(bottom = spacingDefault)
            .background(brush = CATSGradient.default, shape = MaterialTheme.shapes.medium)
            .padding(spacingDefault)
            .then(modifier)
    ) {
        content()
    }
}