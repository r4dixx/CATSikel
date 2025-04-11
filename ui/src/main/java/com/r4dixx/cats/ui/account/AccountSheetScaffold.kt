package com.r4dixx.cats.ui.account

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.r4dixx.cats.design.components.CATSRowItem
import com.r4dixx.cats.design.components.CATSSheetScaffold
import com.r4dixx.cats.design.components.CATSTextGradient
import com.r4dixx.cats.design.components.CATSUIState
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingSmall

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AccountSheetScaffold(
    viewModel: AccountViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CATSUIState(viewModel.state, modifier) { data ->
        CATSSheetScaffold(
            initialSheetValue = SheetValue.Expanded,
            topBarText = data.accountLabel,
            onBack = onBack,
            content = {},
            sheetContent = {
                LazyColumn {
                    stickyHeader {
                        CATSTextGradient(
                            text = data.bankName,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                        )
                    }
                    stickyHeader {
                        CATSTextGradient(
                            text = data.accountBalance,
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                                .padding(bottom = spacingSmall)
                        )
                    }
                    items(data.accountOperations) { operation ->
                        CATSRowItem(modifier = Modifier.padding(spacingDefault)) {
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
                        Spacer(Modifier.height(spacingSmall))
                    }
                }
            }
        )
    }
}