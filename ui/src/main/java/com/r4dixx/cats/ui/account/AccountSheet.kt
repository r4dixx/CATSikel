package com.r4dixx.cats.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel.State.Success
import com.r4dixx.cats.design.components.CATSBottomSheet
import com.r4dixx.cats.domain.model.Account
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSheet(
    account: Account,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = koinViewModel(parameters = { parametersOf(account) }),
) {
    CATSBottomSheet(
        onDismiss = onDismiss,
        modifier = modifier
    ) {
        Column {
            val state = viewModel.state.collectAsStateWithLifecycle()
            // TODO - Handle error and loading
            if (state.value !is Success) return@CATSBottomSheet
            val data = (state.value as Success<AccountViewModel.Data>).data

            Text("$data")
        }
    }
}
