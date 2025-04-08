package com.r4dixx.cats.design.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import com.r4dixx.cats.design.theme.CATSDimension.iconSizeLarge

@Composable
fun CATSBottomBar(modifier: Modifier = Modifier) {
    var rotationState by remember { mutableStateOf(0f) }
    val rotationAngle by animateFloatAsState(
        targetValue = rotationState,
        animationSpec = tween(durationMillis = 1000) 
    )

    val haptic = LocalHapticFeedback.current

    BottomAppBar(modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CATSIconGradient(
                painterResource(com.r4dixx.cats.design.R.drawable.ic_cats),
                contentDescription = null, 
                modifier = Modifier
                    .size(iconSizeLarge)
                    .rotate(rotationAngle)
                    .clickable {
                        rotationState += 360f
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    }
            )
        }
    }
}