package com.r4dixx.cats.ui.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel.State.Success
import com.r4dixx.cats.design.components.CATSSheet
import com.r4dixx.cats.design.components.CATSTextGradient
import com.r4dixx.cats.design.theme.Dimension.spacingDefault
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.ui.account.model.OperationUI
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AccountSheet(
    account: Account,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = koinViewModel(parameters = { parametersOf(account) }),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    // TODO - Handle error and loading
    if (state.value !is Success) return
    val data = (state.value as Success<AccountViewModel.Data>).data

    Box(modifier) {
        CATSSheetBehind(
            label = data.label,
            balance = data.balance,
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 2)
                .align(Alignment.TopCenter)
        )
        CATSSheet(onDismiss = onDismiss) {
            CATSSheetInside(data.operations)
        }
    }
}

@Composable
private fun CATSSheetBehind(
    label: String,
    balance: String,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column() {
            Text(
                text = label,
                style = MaterialTheme.typography.displaySmall.copy(color = Color.White)
            )
            CATSTextGradient(
                text = balance,
                style = MaterialTheme.typography.displayLarge
            )
        }
    }
}

@Composable
private fun CATSSheetInside(
    operations: List<OperationUI>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        operations.forEach { operation ->
            Text(operation.title)
            Text(operation.amount)
            Text(operation.date)
            Spacer(Modifier.height(spacingDefault))
        }
    }
}
