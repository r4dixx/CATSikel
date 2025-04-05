package com.r4dixx.cats.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorScheme = darkColorScheme(
    primary = CATSColor.Mint,
    onPrimary = Color.White,
    primaryContainer = Color.Transparent,
    onPrimaryContainer = Color.Black,

    secondary = CATSColor.Coral,
    onSecondary = Color.White,
    secondaryContainer = Color.Transparent,
    onSecondaryContainer = Color.White,

    tertiary = CATSColor.Lavender,
    onTertiary = Color.White,
    tertiaryContainer = Color.Transparent,
    onTertiaryContainer = Color.White,

    background = Color.Black,
    onBackground = Color.White,

    surface = Color.Black,
    onSurface = Color.White,

    scrim = Color.Transparent
)

@Composable
fun CATSTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = CATSTypography.default,
        content = content
    )
}
