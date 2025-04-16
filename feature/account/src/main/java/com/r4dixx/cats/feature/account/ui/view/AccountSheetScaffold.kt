package com.r4dixx.cats.feature.account.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.design.components.CATSRowItem
import com.r4dixx.cats.design.components.CATSTextGradient
import com.r4dixx.cats.design.components.scaffold.CATSSheetScaffold
import com.r4dixx.cats.design.components.state.CATSStateful
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingSmall
import com.r4dixx.cats.feature.account.R
import com.r4dixx.cats.feature.account.ui.AccountViewModel
import com.r4dixx.cats.feature.account.ui.model.UIOperation
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSheetScaffold(
    viewModel: AccountViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CATSStateful(state, modifier) { data ->
        CATSSheetScaffold(
            initialSheetValue = SheetValue.Expanded,
            topBarText = data.accountLabel,
            onBack = onBack,
            content = {},
            sheetContent = {
                val listState = rememberLazyListState()
                Box(Modifier.padding(bottom = spacingSmall)) {
                    AccountSheetContent(
                        listState = listState,
                        bankName = data.bankName,
                        accountBalance = data.accountBalance,
                        accountOperations = data.accountOperations
                    )
                    AccountSheetAnimatedFAB(
                        listState = listState,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(spacingDefault)
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AccountSheetContent(
    listState: LazyListState,
    bankName: String,
    accountBalance: String,
    accountOperations: ImmutableList<UIOperation>,
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
                    .background(MaterialTheme.colorScheme.background)
            )
        }

        stickyHeader {
            CATSTextGradient(
                text = accountBalance,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(bottom = spacingSmall)
            )
        }

        items(accountOperations) { operation ->
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

@Composable
private fun AccountSheetAnimatedFAB(
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    val isItemFiveFirstVisible by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex >= 5
        }
    }

    AnimatedVisibility(
        visible = isItemFiveFirstVisible,
        modifier = modifier,
        enter = scaleIn() + fadeIn(),
        exit = scaleOut() + fadeOut()
    ) {
        FloatingActionButton(
            onClick = { coroutineScope.launch { listState.animateScrollToItem(0) } },
            content = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = stringResource(R.string.cd_fab_scroll_top)
                )
            }
        )
    }
}
