package com.r4dixx.cats.design.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.theme.CATSGradient
import com.r4dixx.cats.design.theme.CATSTheme

@Composable
fun CATSTextGradient(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTextStyle.current,
) {
    Text(
        text = text,
        maxLines = maxLines,
        style = style.copy(CATSGradient.default),
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

// Previews

@Preview
@Composable
private fun CATSTextGradientPreview() {
    CATSTheme {
        CATSTextGradient(text = "CATS")
    }
}
