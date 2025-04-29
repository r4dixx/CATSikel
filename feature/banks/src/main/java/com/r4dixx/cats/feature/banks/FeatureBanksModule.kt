package com.r4dixx.cats.feature.banks

import com.r4dixx.cats.core.ui.state.CATSStateHandler
import com.r4dixx.cats.domain.usecase.GetBanksUseCase
import com.r4dixx.cats.feature.banks.ui.BanksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureBanksModule = module {
    viewModel {
        BanksViewModel(
            stateHandler = get<CATSStateHandler<BanksViewModel.UIData>>(),
            getBanks = get<GetBanksUseCase>()
        )
    }
}
