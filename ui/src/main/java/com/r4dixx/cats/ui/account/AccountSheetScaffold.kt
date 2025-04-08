package com.r4dixx.cats.ui.account

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.r4dixx.cats.design.components.CATSRowItem
import com.r4dixx.cats.design.components.CATSSheetScaffold
import com.r4dixx.cats.design.components.CATSTextGradient
import com.r4dixx.cats.design.components.CATSUIState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountSheetScaffold(
    viewModel: AccountViewModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CATSUIState(modifier = modifier, state = viewModel.state) { data ->
        CATSSheetScaffold(
            topBarText = data.accountLabel,
            onDismiss = onDismiss,
            content = {},
            sheetContent = {
                LazyColumn() {
                    stickyHeader {
                        CATSTextGradient(
                            text = data.bankName,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .fillMaxWidth()
                        )
                    }
                    stickyHeader {
                        CATSTextGradient(
                            text = data.accountBalance,
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .fillMaxWidth()
                        )
                    }
                    items(data.accountOperations) { operation ->
                        CATSRowItem {
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
}