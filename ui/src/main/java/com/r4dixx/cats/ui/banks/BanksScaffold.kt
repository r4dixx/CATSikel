package com.r4dixx.cats.ui.banks

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel.State.Success
import com.r4dixx.cats.design.components.CATSCard
import com.r4dixx.cats.design.components.CATSExpandable
import com.r4dixx.cats.design.components.CATSScaffold
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingSmall
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.ui.R

@Composable
fun BanksScaffold(
    viewModel: BanksViewModel,
    onAccountClick: (Account) -> Unit,
    modifier: Modifier = Modifier,
) {
    CATSScaffold(
        topBarText = stringResource(R.string.banks_top_bar_text),
        onBackClick = null,
        modifier = modifier,
    ) { paddingValues ->
        val state = viewModel.state.collectAsStateWithLifecycle()

        // TODO - Handle error and loading
        if (state.value !is Success) return@CATSScaffold

        val data = (state.value as Success<BanksViewModel.Data>).data

        BanksScreenContent(
            banksCA = data.banksCA,
            banksNotCA = data.banksNotCA,
            onAccountClick = onAccountClick,
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = spacingDefault)
                .padding(bottom = spacingDefault)
        )
    }
}

@Composable
private fun BanksScreenContent(
    banksCA: List<Bank>,
    banksNotCA: List<Bank>,
    modifier: Modifier = Modifier,
    onAccountClick: (Account) -> Unit,
) {
    LazyColumn(modifier) {
        stickyItems(R.string.header_bank_type_ca, banksCA, onAccountClick)
        item { Spacer(Modifier.height(spacingDefault)) }
        stickyItems(R.string.header_bank_type_not_ca, banksNotCA, onAccountClick)
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.stickyItems(
    @StringRes labelRes: Int,
    banks: List<Bank>,
    onAccountClick: (Account) -> Unit,
) {
    stickyHeader {
        Text(
            text = stringResource(labelRes), style = MaterialTheme.typography.titleLarge
        )
    }
    item { Spacer(Modifier.height(spacingSmall)) }
    items(banks) { bank ->
        BanksScreenItem(
            bank = bank, onAccountClick = onAccountClick
        )
    }
}

@Composable
private fun BanksScreenItem(
    bank: Bank,
    onAccountClick: (Account) -> Unit,
    modifier: Modifier = Modifier,
) {
    CATSExpandable(modifier = modifier, header = {
        Text(
            text = bank.name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = spacingSmall)
        )
    }, content = {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacingDefault)
        ) {
            items(bank.accounts) { account ->
                CATSCard(onClick = { onAccountClick(account) }) {
                    Text(
                        text = account.label,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
    })
}
