package com.r4dixx.cats.design.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = MintLight,
    secondary = CoralLight,
    tertiary = LavenderLight,
    background = Color.Black,
    surface = Color.Black,
    surfaceVariant = Color.Black,
    primaryContainer = Color.Black,
    secondaryContainer = Color.Black,
    tertiaryContainer = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = MintDark,
    secondary = CoralDark,
    tertiary = LavenderDark,
    background = Color.White,
    surface = Color.White,
    surfaceVariant = Color.White,
    primaryContainer = Color.White,
    secondaryContainer = Color.White,
    tertiaryContainer = Color.White
)


@Composable
fun CATSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}
