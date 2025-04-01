package com.r4dixx.cats.ui.master

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel.State.Success
import com.r4dixx.cats.design.theme.spacingDefault
import com.r4dixx.cats.domain.model.Account
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
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(spacingDefault),
        modifier = modifier
    ) {
        stickyItems("- Crédit Agricole", banksCA)
        stickyItems("- Autres Banques", banksNotCA)
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.stickyItems(s: String, banksCA: List<Bank>) {
    stickyHeader { HeaderBank(s) }
    items(banksCA) { bank -> ExpandableItemBank(bank) }
}

@Composable
private fun HeaderBank(label: String,modifier: Modifier = Modifier) {
    Text(text = label, modifier = modifier)
}

@Composable
private fun ExpandableItemBank(bank: Bank, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = bank.name, modifier = Modifier.weight(1f))

            val rotationAngle by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "Icon Rotation")

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = if (expanded) "Show less" else "Show more",
                modifier = Modifier.rotate(rotationAngle)
            )
        }

        AnimatedVisibility(visible = expanded) {
            Column {
                bank.accounts.forEach { account ->
                    ItemAccount(account)
                }
            }
        }
    }
}

@Composable
private fun ItemAccount(account: Account) {
    Text(text = account.label)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MasterTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = "Mes Comptes") },
        modifier = modifier
    )
}
