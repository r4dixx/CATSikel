package com.r4dixx.cats.feature.banks.ui.model

import com.r4dixx.cats.core.utils.sanitized
import com.r4dixx.cats.domain.model.Account
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class UIAccount(
    val id: Long,
    val label: String
)

fun Account.toUIAccount() = UIAccount(
    id = id,
    label = label.sanitized()
)

fun List<Account>.toUIAccounts(): ImmutableList<UIAccount> = map { it.toUIAccount() }.toImmutableList()