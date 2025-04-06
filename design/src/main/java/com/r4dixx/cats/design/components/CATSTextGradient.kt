package com.r4dixx.cats.design.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.r4dixx.cats.design.theme.CATSGradient

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
        modifier = modifier
    )
}
