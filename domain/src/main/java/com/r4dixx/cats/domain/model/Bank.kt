package com.r4dixx.cats.domain.model

data class Bank(
    val name: String,
    val isCA: Boolean,
    val accounts: List<Account>
)
