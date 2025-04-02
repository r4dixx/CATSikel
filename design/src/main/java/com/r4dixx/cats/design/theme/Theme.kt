package com.r4dixx.cats.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = darkColorScheme(
    primary = Color.Mint,
    secondary = Color.Coral,
    tertiary = Color.Lavender,
    background = androidx.compose.ui.graphics.Color.Black,
    surface = androidx.compose.ui.graphics.Color.Black,
    surfaceVariant = androidx.compose.ui.graphics.Color.Black,
    primaryContainer = androidx.compose.ui.graphics.Color.Black,
    secondaryContainer = androidx.compose.ui.graphics.Color.Black,
    tertiaryContainer = androidx.compose.ui.graphics.Color.Black
)

@Composable
fun CATSTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}
