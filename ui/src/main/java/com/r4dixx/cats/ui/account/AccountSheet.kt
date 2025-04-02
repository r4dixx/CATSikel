package com.r4dixx.cats.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel.State.Success
import com.r4dixx.cats.design.components.CATSSheet
import com.r4dixx.cats.design.theme.Dimension.spacingDefault
import com.r4dixx.cats.domain.model.Account
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

    Text(data.balance)

    CATSSheet(
        onDismiss = onDismiss,
        modifier = modifier
    ) {
        Column {
            Text(data.balance)
            Text(data.label)

            Spacer(Modifier.height(spacingDefault))

            data.operations.forEach { operation ->
                Text(operation.title)
                Text(operation.amount)
                Text(operation.date)
                Spacer(Modifier.height(spacingDefault))
            }
        }
    }
}
