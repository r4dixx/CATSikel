package com.r4dixx.cats.design.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun CATSExpandable(
    header: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(initiallyExpanded) }

    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = !expanded
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            header()
            Spacer(modifier = Modifier.weight(1f))

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
    CATSExpandable(
        header = {
            Text(text = "Expandable Header")
        },
        content = {
            Text(text = "Expandable Content")
        }
    )
}
