package com.r4dixx.cats.feature.account.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.design.components.CATSCard
import com.r4dixx.cats.design.components.CATSTextGradient
import com.r4dixx.cats.design.components.scaffold.CATSSheetScaffold
import com.r4dixx.cats.design.components.state.CATSStatefulBox
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingSmall
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.feature.account.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSheetScaffold(
    viewModel: AccountViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CATSSheetScaffold(
        modifier = modifier,
        initialSheetValue = SheetValue.Expanded,
        topBarText = state.dataOrNull?.accountLabel ?: stringResource(R.string.header_account_default),
        onBack = onBack,
        content = {},
        sheetContent = {
            CATSStatefulBox(
                state = state,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacingDefault),
            ) { data ->
                val coroutineScope = rememberCoroutineScope()
                val listState = rememberLazyListState()
                val isItemFiveFirstVisible by remember {
                    derivedStateOf {
                        listState.firstVisibleItemIndex >= 5
                    }
                }

                AccountSheetContent(
                    listState = listState,
                    bankName = data.bankName,
                    accountBalance = data.accountBalance,
                    accountOperations = data.accountOperations
                )

                AccountSheetAnimatedFAB(
                    visible = isItemFiveFirstVisible,
                    onClick = { coroutineScope.launch { listState.animateScrollToItem(0) } },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(vertical = spacingDefault, horizontal = spacingSmall)
                )
            }
        }
    )
}


@Composable
private fun AccountSheetContent(
    listState: LazyListState,
    bankName: String,
    accountBalance: String,
    accountOperations: ImmutableList<AccountViewModel.UIOperation>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        stickyHeader {
            CATSTextGradient(
                text = bankName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface) // Necessary to avoid overlap when scrolling
            )
        }

        stickyHeader {
            CATSTextGradient(
                text = accountBalance,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)  // Necessary to avoid overlap when scrolling
                    .padding(bottom = spacingSmall)
            )
        }

        items(accountOperations) { operation ->
            CATSCard(onClick = {}) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(spacingDefault)
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
            Spacer(Modifier.height(spacingSmall))
        }
    }
}

@Composable
private fun AccountSheetAnimatedFAB(
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = scaleIn() + fadeIn(),
        exit = scaleOut() + fadeOut()
    ) {
        FloatingActionButton(
            onClick = onClick,
            content = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = stringResource(R.string.cd_fab_scroll_top)
                )
            }
        )
    }
}

// Previews

@Preview
@Composable
private fun AccountSheetContentPreview() {
    CATSTheme {
        AccountSheetContent(
            listState = rememberLazyListState(),
            bankName = "Banque Populaire",
            accountBalance = "125,56 €",
            accountOperations = persistentListOf(
                AccountViewModel.UIOperation(
                    title = "Auchan",
                    amount = "-12,50 €",
                    date = "01/01/2021",
                ),
                AccountViewModel.UIOperation(
                    title = "Carrefour",
                    amount = "-14,20 €",
                    date = "01/12/2021",
                )
            )
        )
    }
}

@Preview
@Composable
private fun AccountSheetAnimatedFABPreview() {
    CATSTheme {
        AccountSheetAnimatedFAB(
            visible = true,
            onClick = {}
        )
    }
}