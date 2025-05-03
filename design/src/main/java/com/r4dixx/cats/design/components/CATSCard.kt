package com.r4dixx.cats.design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.theme.CATSDimension.cardSizeDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.design.theme.gradient

@Composable
fun CATSCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val gradientEnabled = MaterialTheme.gradient.isEnabled
    val cardShape = CardDefaults.shape

    val cardModifier = if (gradientEnabled) {
        Modifier.background(MaterialTheme.gradient.default, cardShape) then modifier
    } else {
        modifier
    }

    val border = if (gradientEnabled) {
        null
    } else {
        BorderStroke(CardDefaults.outlinedCardBorder().width, MaterialTheme.gradient.default)
    }

    val contentColor = if (gradientEnabled) {
        MaterialTheme.colorScheme.inverseOnSurface
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Card(
        modifier = cardModifier,
        onClick = onClick,
        shape = cardShape,
        border = border,
        colors = CardDefaults.cardColors(Color.Transparent, contentColor)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.sizeIn(cardSizeDefault)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun CATSCardPreview() {
    CATSTheme(darkGradientEnabled = false) {
        CATSCard(onClick = {}) {
            Text(
                text = "CATSCard",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(spacingDefault)
            )
        }
    }
}

@Preview
@Composable
private fun CATSCardGradientPreview() {
    CATSTheme(darkGradientEnabled = true) {
        CATSCard(onClick = {}) {
            Text(
                text = "CATSCard",
                modifier = Modifier.padding(spacingDefault)
            )
        }
    }
}