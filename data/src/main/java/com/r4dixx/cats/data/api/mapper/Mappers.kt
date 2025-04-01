package com.r4dixx.cats.data.api.mapper

import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.data.api.model.Account as APIAccount
import com.r4dixx.cats.data.api.model.Bank as APIBank

fun APIBank.toDomain() = Bank(
    name = name,
    isCA = isCA == 1,
    accounts = accounts.map { it.toDomain() }
)

fun APIAccount.toDomain() = Account(label)
