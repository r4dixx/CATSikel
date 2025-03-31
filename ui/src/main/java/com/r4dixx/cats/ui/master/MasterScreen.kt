package com.r4dixx.cats.ui.master

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4dixx.cats.core.ui.CATSViewModel.State.Error
import com.r4dixx.cats.core.ui.CATSViewModel.State.Loading
import com.r4dixx.cats.core.ui.CATSViewModel.State.Success
import org.koin.androidx.compose.koinViewModel

@Composable
fun MasterScreen(
    modifier: Modifier = Modifier,
    viewModel: MasterViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val text = when (state.value) {
        is Loading -> "Loading"
        is Error -> "Error"
        is Success -> {
            val data = (state.value as Success<MasterViewModel.Data>).data
            "${data.banks}"
        }
    }

    Text(
        text = text,
        modifier = modifier
    )
}
