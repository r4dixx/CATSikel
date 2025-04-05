package com.r4dixx.cats.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorScheme = darkColorScheme(
    primary = CATSColor.Mint,
    onPrimary = Color.White,
    primaryContainer = Color.Transparent,
    onPrimaryContainer = CATSColor.LightGrey,

    secondary = CATSColor.Coral,
    onSecondary = Color.Black,
    secondaryContainer = Color.Transparent,
    onSecondaryContainer = CATSColor.LightGrey,

    tertiary = CATSColor.Lavender,
    onTertiary = Color.Black,
    tertiaryContainer = Color.Transparent,
    onTertiaryContainer = CATSColor.LightGrey,

    background = Color.Black,
    onBackground = CATSColor.LightGrey,

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
