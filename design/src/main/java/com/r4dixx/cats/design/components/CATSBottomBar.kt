package com.r4dixx.cats.design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault

@Composable
fun CATSBottomBar(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Icon(
            painterResource(com.r4dixx.cats.design.R.drawable.ic_cats),
            contentDescription = null, // This is a decorative icon
            modifier = Modifier
                .padding(bottom = spacingDefault)
        )
    }
}