package com.r4dixx.cats.ui.master

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel.State.Success
import com.r4dixx.cats.design.theme.spacingDefault
import com.r4dixx.cats.domain.model.Bank
import org.koin.androidx.compose.koinViewModel

@Composable
fun MasterScreen(viewModel: MasterViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    // TODO - Handle error and loading
    if (state.value !is Success) return
    MasterSuccess((state.value as Success<MasterViewModel.Data>).data)
}

@Composable
private fun MasterSuccess(data: MasterViewModel.Data) {
    Scaffold(
        topBar = { MasterTopBar() },
        content = { paddingValues ->
            MasterContent(
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
private fun MasterContent(
    banksCA: List<Bank>,
    banksNotCA: List<Bank>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        item { Text(text = "Crédit Agricole") }
        items(banksCA) { bank -> Text(text = bank.name) }
        item { Text(text = "Autres Banques") }
        items(banksNotCA) { bank -> Text(text = bank.name) }
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
