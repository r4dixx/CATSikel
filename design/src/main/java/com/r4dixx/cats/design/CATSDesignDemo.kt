package com.r4dixx.cats.design

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.r4dixx.cats.design.components.CATSCard
import com.r4dixx.cats.design.components.CATSExpandable
import com.r4dixx.cats.design.components.CATSIconGradient
import com.r4dixx.cats.design.components.CATSTextGradient
import com.r4dixx.cats.design.components.scaffold.CATSScaffold
import com.r4dixx.cats.design.components.state.CATSEmpty
import com.r4dixx.cats.design.components.state.CATSError
import com.r4dixx.cats.design.components.state.CATSProgress
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSTheme

/**
 * Preview of all CATS components.
 * This is a demo of all the components in the CATS design system.
 * It is not meant to be used in production.
 * It is meant to be used as a reference for the CATS design system.
 *
 * Simply run the preview (left gutter icon) to see components on your device
 */
@Preview
@Composable
private fun CATSDesignDemoPreview() = CATSDesignDemo()

// region Components

/**
 * Add new component here
 * It will be displayed in the preview
 */
private fun LazyListScope.components() {

    item { CATSTextGradient(text = "CATS") }

    item {
        CATSIconGradient(
            painter = rememberVectorPainter(Icons.Default.AccountCircle),
            contentDescription = "CATS Logo"
        )
    }

    item {
        CATSExpandable(
            header = {
                Text(text = "Expandable Header")
            },
            content = {
                Text(text = "Expandable Content")
            }
        )
    }

    item {
        CATSCard(onClick = { }) {
            Text(
                text = "CATSCard",
                modifier = Modifier.padding(spacingDefault)
            )
        }
    }

    item { CATSProgress() }

    item { CATSError(message = null) }

    item { CATSEmpty() }
}

// endregion Components

@Composable
private fun CATSDesignDemo() {
    val context = LocalContext.current
    CATSTheme {
        CATSScaffold(
            topBarText = "CATSComponents",
            onBack = { context.mainLooper.quit() },
        ) { paddingValues ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spacingDefault),
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(spacingDefault)
            ) {
                components()
            }
        }
    }
}