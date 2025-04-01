package com.r4dixx.cats.ui.account

import com.r4dixx.cats.core.ui.CATSViewModel
import com.r4dixx.cats.domain.model.Operation

class AccountViewModel : CATSViewModel<AccountViewModel.Data>() {

    override val data = Data()

    data class Data(
        val label: String = "",
        val balance: String = "",
        val operations: List<Operation> = emptyList()
    )
}
