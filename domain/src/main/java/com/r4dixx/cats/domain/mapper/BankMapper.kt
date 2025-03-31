package com.r4dixx.cats.domain.mapper

import com.r4dixx.cats.data.remote.model.APIBank
import com.r4dixx.cats.domain.model.Bank

fun APIBank.toDomain() = Bank(name)
