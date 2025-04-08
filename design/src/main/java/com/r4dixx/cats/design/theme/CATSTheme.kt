package com.r4dixx.cats.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorScheme = darkColorScheme(
    primary = CATSColor.Mint,
    onPrimary = CATSColor.LightGrey,
    primaryContainer = CATSColor.DeepBlack,
    onPrimaryContainer = CATSColor.LightGrey,

    secondary = CATSColor.Lavender,
    onSecondary = CATSColor.DeepBlack,
    secondaryContainer = Color.Transparent,
    onSecondaryContainer = CATSColor.DeepBlack,

    tertiary = CATSColor.Coral,
    onTertiary = CATSColor.DeepBlack,
    tertiaryContainer = CATSColor.DeepBlack,
    onTertiaryContainer = CATSColor.DeepBlack,

    background = CATSColor.DeepBlack,
    onBackground = CATSColor.LightGrey,

    surface = CATSColor.DeepBlack,
    surfaceContainer = CATSColor.DeepBlack,
    surfaceContainerLow = CATSColor.DeepBlack,
    onSurface = CATSColor.LightGrey,
    surfaceVariant = CATSColor.Mint,
    onSurfaceVariant = CATSColor.DeepBlack,

    error = CATSColor.MediumRed,
)

@Composable
fun CATSTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = CATSTypography.default,
        content = content
    )
}
