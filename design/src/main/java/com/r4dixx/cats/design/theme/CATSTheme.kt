package com.r4dixx.cats.design.theme

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@SuppressLint("ComposeCompositionLocalUsage")
private val LocalCATSGradient = staticCompositionLocalOf { CATSGradient(enabled = false) }

@Suppress("ComposeUnstableReceiver")
val MaterialTheme.gradient: CATSGradient
    @Composable
    @ReadOnlyComposable
    get() = LocalCATSGradient.current

@Composable
fun CATSTheme(
    dynamicColorEnabled: Boolean = true,
    darkGradientEnabled: Boolean = false,
    content: @Composable () -> Unit
) {
    val supportsDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val targetColorScheme = when {
        dynamicColorEnabled && supportsDynamicColor -> {
            val context = LocalContext.current
            if (darkGradientEnabled) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkGradientEnabled -> darkColorScheme()
        else -> lightColorScheme()
    }

    val animationSpec = tween<Color>(durationMillis = 1500)

    val animatedColorScheme = ColorScheme(
        primary = animateColorAsState(targetColorScheme.primary, animationSpec).value,
        onPrimary = animateColorAsState(targetColorScheme.onPrimary, animationSpec).value,
        primaryContainer = animateColorAsState(targetColorScheme.primaryContainer, animationSpec).value,
        onPrimaryContainer = animateColorAsState(targetColorScheme.onPrimaryContainer, animationSpec).value,
        inversePrimary = animateColorAsState(targetColorScheme.inversePrimary, animationSpec).value,
        secondary = animateColorAsState(targetColorScheme.secondary, animationSpec).value,
        onSecondary = animateColorAsState(targetColorScheme.onSecondary, animationSpec).value,
        secondaryContainer = animateColorAsState(targetColorScheme.secondaryContainer, animationSpec).value,
        onSecondaryContainer = animateColorAsState(targetColorScheme.onSecondaryContainer, animationSpec).value,
        tertiary = animateColorAsState(targetColorScheme.tertiary, animationSpec).value,
        onTertiary = animateColorAsState(targetColorScheme.onTertiary, animationSpec).value,
        tertiaryContainer = animateColorAsState(targetColorScheme.tertiaryContainer, animationSpec).value,
        onTertiaryContainer = animateColorAsState(targetColorScheme.onTertiaryContainer, animationSpec).value,
        background = animateColorAsState(targetColorScheme.background, animationSpec).value,
        onBackground = animateColorAsState(targetColorScheme.onBackground, animationSpec).value,
        surface = animateColorAsState(targetColorScheme.surface, animationSpec).value,
        onSurface = animateColorAsState(targetColorScheme.onSurface, animationSpec).value,
        surfaceVariant = animateColorAsState(targetColorScheme.surfaceVariant, animationSpec).value,
        onSurfaceVariant = animateColorAsState(targetColorScheme.onSurfaceVariant, animationSpec).value,
        surfaceTint = animateColorAsState(targetColorScheme.surfaceTint, animationSpec).value,
        inverseSurface = animateColorAsState(targetColorScheme.inverseSurface, animationSpec).value,
        inverseOnSurface = animateColorAsState(targetColorScheme.inverseOnSurface, animationSpec).value,
        error = animateColorAsState(targetColorScheme.error, animationSpec).value,
        onError = animateColorAsState(targetColorScheme.onError, animationSpec).value,
        errorContainer = animateColorAsState(targetColorScheme.errorContainer, animationSpec).value,
        onErrorContainer = animateColorAsState(targetColorScheme.onErrorContainer, animationSpec).value,
        outline = animateColorAsState(targetColorScheme.outline, animationSpec).value,
        outlineVariant = animateColorAsState(targetColorScheme.outlineVariant, animationSpec).value,
        scrim = animateColorAsState(targetColorScheme.scrim, animationSpec).value,
        surfaceBright = animateColorAsState(targetColorScheme.surfaceBright, animationSpec).value,
        surfaceDim = animateColorAsState(targetColorScheme.surfaceDim, animationSpec).value,
        surfaceContainer = animateColorAsState(targetColorScheme.surfaceContainer, animationSpec).value,
        surfaceContainerHigh = animateColorAsState(targetColorScheme.surfaceContainerHigh, animationSpec).value,
        surfaceContainerHighest = animateColorAsState(targetColorScheme.surfaceContainerHighest, animationSpec).value,
        surfaceContainerLow = animateColorAsState(targetColorScheme.surfaceContainerLow, animationSpec).value,
        surfaceContainerLowest = animateColorAsState(targetColorScheme.surfaceContainerLowest, animationSpec).value,
    )

    val gradient = CATSGradient(darkGradientEnabled)

    CompositionLocalProvider(LocalCATSGradient provides gradient) {
        MaterialTheme(
            colorScheme = animatedColorScheme,
            typography = CATSTypography.default,
            content = content
        )
    }
}