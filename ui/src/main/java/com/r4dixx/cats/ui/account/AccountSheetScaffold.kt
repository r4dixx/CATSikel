package com.r4dixx.cats.ui.account

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel.State.Success
import com.r4dixx.cats.design.components.CATSItem
import com.r4dixx.cats.design.components.CATSSheetScaffold
import com.r4dixx.cats.design.components.CATSTextGradient
import com.r4dixx.cats.design.theme.CATSDimension.spacingSmall

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountSheetScaffold(
    viewModel: AccountViewModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    // TODO - Handle error and loading
    if (state.value !is Success) return
    val data = (state.value as Success<AccountViewModel.Data>).data

    CATSSheetScaffold(
        topBarText = data.label,
        onDismiss = onDismiss,
        modifier = modifier,
        sheetContent = {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(spacingSmall)) {
                stickyHeader {
                    CATSTextGradient(
                        text = data.balance,
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxWidth()
                            .padding(bottom = spacingSmall)
                    )
                }
                items(data.operations) { operation ->
                    CATSItem {
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
            }
        }
    )
}