package com.r4dixx.cats.ui.banks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel.State.Success
import com.r4dixx.cats.design.components.CATSExpandable
import com.r4dixx.cats.design.theme.spacingDefault
import com.r4dixx.cats.domain.model.Bank
import org.koin.androidx.compose.koinViewModel

@Composable
fun BanksScreen(viewModel: BanksViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    // TODO - Handle error and loading
    if (state.value !is Success) return
    BanksSuccess((state.value as Success<BanksViewModel.Data>).data)
}

@Composable
private fun BanksSuccess(data: BanksViewModel.Data) {
    Scaffold(
        topBar = { MasterTopBar() },
        content = { paddingValues ->
            BanksContent(
                banksCA = data.banksCA,
                banksNotCA = data.banksNotCA,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = spacingDefault)
            )
        }
    )
}

@Composable
private fun BanksContent(
    banksCA: List<Bank>,
    banksNotCA: List<Bank>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(spacingDefault),
        modifier = modifier
    ) {
        stickyItems("- Crédit Agricole", banksCA)
        stickyItems("- Autres Banques", banksNotCA)
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.stickyItems(label: String, banksCA: List<Bank>) {
    stickyHeader { Text(text = label) }
    items(banksCA) { bank ->
        CATSExpandable(
            header = { Text(text = bank.name) },
            content = {
                Column {
                    bank.accounts.forEach { account ->
                        Text(text = account.label)
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MasterTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = "Mes Comptes") },
        modifier = modifier
    )
}
