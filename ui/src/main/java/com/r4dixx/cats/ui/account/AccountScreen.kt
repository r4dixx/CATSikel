@file:OptIn(ExperimentalFoundationApi::class)

package com.r4dixx.cats.ui.account

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel.State.Success
import com.r4dixx.cats.design.components.CATSScreen
import com.r4dixx.cats.design.components.CATSTextGradient
import com.r4dixx.cats.design.theme.Dimension.spacingDefault
import com.r4dixx.cats.design.theme.Dimension.spacingSmall
import com.r4dixx.cats.design.theme.Gradient
import com.r4dixx.cats.ui.account.model.OperationUI

@Composable
fun AccountScreen(
    viewModel: AccountViewModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    // TODO - Handle error and loading
    if (state.value !is Success) return
    val data = (state.value as Success<AccountViewModel.Data>).data

    CATSScreen(
        topBarTitle = data.label,
        onDismiss = onDismiss,
        modifier = modifier,
    ) {
        LazyColumn {
            stickyHeader {
                CATSTextGradient(
                    text = data.balance,
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(bottom = spacingDefault)
                )
            }
            repeat(30) {
                items(data.operations) { operation ->
                    OperationItem(
                        operation = operation,
                        modifier = Modifier
                            .padding(bottom = spacingDefault)
                            .background(brush = Gradient.default, shape = MaterialTheme.shapes.medium)
                            .padding(spacingDefault)
                    )
                }
            }
        }
    }
}

@Composable
private fun OperationItem(
    operation: OperationUI,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacingSmall),
        modifier = modifier
    ) {
        Column {
            Text(
                text = operation.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = operation.date,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Spacer(Modifier.weight(1f))

        Text(
            text = operation.amount,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}