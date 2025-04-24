package com.r4dixx.cats.design.theme

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

@SuppressLint("ComposeCompositionLocalUsage")
private val LocalCATSGradient = staticCompositionLocalOf { CATSGradient(enabled = false) }

@Suppress("ComposeUnstableReceiver")
val MaterialTheme.gradients: CATSGradient
    @Composable
    @ReadOnlyComposable
    get() = LocalCATSGradient.current

@Composable
fun CATSTheme(
    dynamicColorEnabled: Boolean = true,
    darkDynamicGradientEnabled: Boolean = false,
    content: @Composable () -> Unit
) {
    val supportsDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = if (dynamicColorEnabled && supportsDynamicColor) {
        val context = LocalContext.current
        if (darkDynamicGradientEnabled) {
            dynamicDarkColorScheme(context)
        } else {
            dynamicLightColorScheme(context)
        }
    } else {
        if (darkDynamicGradientEnabled) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    }

    val gradient = CATSGradient(darkDynamicGradientEnabled)

    CompositionLocalProvider(LocalCATSGradient provides gradient) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = CATSTypography.default,
            content = content
        )
    }
}