package com.r4dixx.cats.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

object CATSGradient {
    val default: Brush
        @Composable
        @ReadOnlyComposable
        get() = Brush.linearGradient(
            colorStops = arrayOf(
                0.0f to MaterialTheme.colorScheme.primary,
                0.7f to MaterialTheme.colorScheme.secondary,
                1.0f to MaterialTheme.colorScheme.tertiary
            ),
            start = Offset.Zero,
            end = Offset.Infinite
        )
}