package com.r4dixx.cats.feature.account

import com.r4dixx.cats.core.ui.state.CATSStateHandler
import com.r4dixx.cats.domain.usecase.GetAccountUseCase
import com.r4dixx.cats.feature.account.ui.AccountViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureAccountModule = module {
    viewModel { params ->
        AccountViewModel(
            accountId = params.getOrNull<Long>(),
            stateHandler = get<CATSStateHandler<AccountViewModel.UIData>>(),
            getAccount = get<GetAccountUseCase>()
        )
    }
}