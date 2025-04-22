package com.r4dixx.cats.feature.banks.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.design.components.CATSCard
import com.r4dixx.cats.design.components.CATSExpandable
import com.r4dixx.cats.design.components.scaffold.CATSScaffold
import com.r4dixx.cats.design.components.state.CATSStatefulBox
import com.r4dixx.cats.design.theme.CATSDimension
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingSmall
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.feature.banks.R
import com.r4dixx.cats.feature.banks.model.UIAccount
import com.r4dixx.cats.feature.banks.model.UIBank
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BanksScaffold(
    viewModel: BanksViewModel,
    onAccountClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    CATSScaffold(
        topBarText = stringResource(R.string.banks_top_bar_text),
        onBack = null,
        modifier = modifier
    ) { paddingValues ->

        val state by viewModel.state.collectAsStateWithLifecycle()

        CATSStatefulBox(
            state = state,
            modifier = Modifier
                .padding(paddingValues)
                .padding(spacingDefault)
        ) { data ->
            BanksContent(
                banksCA = data.banksCA,
                banksNotCA = data.banksNotCA,
                onAccountClick = onAccountClick,
            )
        }
    }
}

@Composable
private fun BanksContent(
    banksCA: ImmutableList<UIBank>,
    banksNotCA: ImmutableList<UIBank>,
    modifier: Modifier = Modifier,
    onAccountClick: (Long) -> Unit,
) {
    LazyColumn(modifier) {
        stickyItems(R.string.header_bank_type_ca, banksCA, onAccountClick)
        item { Spacer(modifier = Modifier.height(spacingDefault)) }
        stickyItems(R.string.header_bank_type_not_ca, banksNotCA, onAccountClick)
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.stickyItems(
    @StringRes labelRes: Int,
    banks: ImmutableList<UIBank>,
    onAccountClick: (Long) -> Unit,
) {
    stickyHeader {
        Text(
            text = stringResource(labelRes),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface) // Necessary to avoid overlap when scrolling
                .padding(bottom = spacingSmall)

        )
    }
    items(banks) { bank ->
        BankItem(
            bank = bank,
            onAccountClick = onAccountClick,
            modifier = Modifier.padding(top = CATSDimension.spacingSmall)
        )
    }
}

@Composable
private fun BankItem(
    bank: UIBank,
    onAccountClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    CATSExpandable(
        modifier = modifier,
        header = {
            Text(
                text = bank.name,
                style = MaterialTheme.typography.titleMedium
            )
        },
        content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(CATSDimension.spacingSmall),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = CATSDimension.spacingSmall)
            ) {
                items(bank.accounts) { account ->
                    CATSCard(onClick = { onAccountClick(account.id) }) {
                        Text(
                            text = account.label,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(spacingDefault)
                        )
                    }
                }
            }
        }
    )
}

// Preview

@Preview
@Composable
private fun BanksContentPreview() {
    CATSTheme {
        BanksContent(
            onAccountClick = {},
            banksCA = persistentListOf(
                UIBank(
                    name = "Credit Agricole",
                    isCA = true,
                    accounts = persistentListOf(
                        UIAccount(id = 1, label = "Compte Courant"),
                        UIAccount(id = 2, label = "Compte Joint"),
                    )
                )
            ),
            banksNotCA = persistentListOf(
                UIBank(
                    name = "Banque Populaire",
                    isCA = false,
                    accounts = persistentListOf(
                        UIAccount(id = 1, label = "Compte Courant"),
                        UIAccount(id = 2, label = "Compte Epargne"),
                        UIAccount(id = 3, label = "Compte Titres")
                    )
                )
            )
        )
    }
}


