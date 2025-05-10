package com.r4dixx.cats.design.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.R
import com.r4dixx.cats.design.theme.CATSTheme

@Composable
fun CATSExpandable(
    header: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    onClick: () -> Unit = {},
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(initiallyExpanded) }

    Column(modifier) {
        Row(
            modifier = Modifier.clickable {
                expanded = !expanded
                onClick()
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f)) { header() }

            val rotationAngle by animateFloatAsState(if (expanded) 180f else 0f)

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = if (expanded) stringResource(R.string.cd_collapse) else stringResource(R.string.cd_expand),
                modifier = Modifier.rotate(rotationAngle)
            )
        }

        AnimatedVisibility(visible = expanded) {
            content()
        }
    }
}

// Previews

@Preview
@Composable
private fun CATSExpandablePreview() {
    CATSTheme {
        CATSExpandable(
            header = {
                Text(text = "Expandable Header")
            },
            content = {
                Text(text = "Expandable Content")
            }
        )
    }
}
