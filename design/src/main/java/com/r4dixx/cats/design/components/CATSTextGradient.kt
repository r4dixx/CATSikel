package com.r4dixx.cats.design.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.r4dixx.cats.design.theme.CATSGradient

@Composable
fun CATSTextGradient(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = LocalTextStyle.current,
) {
    Text(
        text = text,
        style = style.copy(CATSGradient.default),
        modifier = modifier
    )
}
