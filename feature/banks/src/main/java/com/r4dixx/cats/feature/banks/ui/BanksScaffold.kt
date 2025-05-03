package com.r4dixx.cats.feature.banks.ui

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.design.components.CATSCard
import com.r4dixx.cats.design.components.CATSExpandable
import com.r4dixx.cats.design.components.CATSIconGradient
import com.r4dixx.cats.design.components.scaffold.CATSScaffold
import com.r4dixx.cats.design.components.state.CATSStatefulBox
import com.r4dixx.cats.design.theme.CATSDimension.spacingDefault
import com.r4dixx.cats.design.theme.CATSDimension.spacingSmall
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.feature.banks.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BanksScaffold(
    viewModel: BanksViewModel,
    onAccountClick: (Long) -> Unit,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CATSScaffold(
        topBarText = stringResource(R.string.banks_top_bar_text),
        topBarActions = { BanksTopBarAction(onIconClick) },
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
                onAccountClick = onAccountClick
            )
        }
    }
}

@Composable
private fun BanksContent(
    banksCA: ImmutableList<BanksViewModel.UIBank>,
    banksNotCA: ImmutableList<BanksViewModel.UIBank>,
    onAccountClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        stickyItems(R.string.header_bank_type_ca, banksCA, onAccountClick)
        item { Spacer(modifier = Modifier.height(spacingDefault)) }
        stickyItems(R.string.header_bank_type_not_ca, banksNotCA, onAccountClick)
    }
}

private fun LazyListScope.stickyItems(
    @StringRes labelRes: Int,
    banks: ImmutableList<BanksViewModel.UIBank>,
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
            modifier = Modifier.padding(top = spacingSmall)
        )
    }
}

@Composable
private fun BankItem(
    bank: BanksViewModel.UIBank,
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
                horizontalArrangement = Arrangement.spacedBy(spacingSmall),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = spacingSmall)
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

@Composable
private fun BanksTopBarAction(onIconClick: () -> Unit) {
    val haptic = LocalHapticFeedback.current

    var targetRotationAngle by remember { mutableFloatStateOf(0f) }
    var isClickAnimating by remember { mutableStateOf(false) }

    val animatedRotationAngle by animateFloatAsState(
        targetValue = targetRotationAngle,
        animationSpec = tween(durationMillis = 1000),
        label = "IconRotation",
        finishedListener = { finalValue ->
            if (finalValue == targetRotationAngle && isClickAnimating) {
                onIconClick()
                haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                isClickAnimating = false
            } else {
                isClickAnimating = false
            }
        }
    )

    IconButton(
        onClick = {
            if (!isClickAnimating) {
                targetRotationAngle = if (targetRotationAngle == 0f) 360f else 0f
                haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                isClickAnimating = true
            }
        }
    ) {
        CATSIconGradient(
            painter = painterResource(com.r4dixx.cats.design.R.drawable.ic_cats),
            contentDescription = stringResource(R.string.cd_cats_icon),
            modifier = Modifier
                .fillMaxSize()
                .rotate(animatedRotationAngle)
        )
    }
}

// Previews

@Preview
@Composable
private fun BanksContentPreview() {
    CATSTheme {
        BanksContent(
            banksCA = persistentListOf(
                BanksViewModel.UIBank(
                    name = "Credit Agricole",
                    isCA = true,
                    accounts = persistentListOf(
                        BanksViewModel.UIAccount(id = 1, label = "Compte Courant"),
                        BanksViewModel.UIAccount(id = 2, label = "Compte Joint"),
                    )
                )
            ),
            banksNotCA = persistentListOf(
                BanksViewModel.UIBank(
                    name = "Banque Populaire",
                    isCA = false,
                    accounts = persistentListOf(
                        BanksViewModel.UIAccount(id = 1, label = "Compte Courant"),
                        BanksViewModel.UIAccount(id = 2, label = "Compte Epargne"),
                        BanksViewModel.UIAccount(id = 3, label = "Compte Titres")
                    )
                )
            ),
            onAccountClick = {}
        )
    }
}

@Preview
@Composable
private fun BanksTopBarActionPreview() {
    BanksTopBarAction(
        onIconClick = {}
    )
}


