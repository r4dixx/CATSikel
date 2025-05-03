package com.r4dixx.cats.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor

@Stable
class CATSGradient(private val enabled: Boolean = false) {
    val isEnabled: Boolean
        get() = enabled
    val default: Brush
        @Composable
        @ReadOnlyComposable
        get() =
            if (enabled) {
                Brush.linearGradient(
                    colorStops = arrayOf(
                        0.0f to MaterialTheme.colorScheme.primary,
                        0.7f to MaterialTheme.colorScheme.secondary,
                        1.0f to MaterialTheme.colorScheme.tertiary
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            } else {
                SolidColor(MaterialTheme.colorScheme.primary)
            }
}