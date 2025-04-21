package com.r4dixx.cats.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.r4dixx.cats.data.local.entities.AccountEntity
import com.r4dixx.cats.data.local.entities.BankEntity

data class LocalBank(
    @Embedded val bank: BankEntity,
    @Relation(
        entity = AccountEntity::class,
        parentColumn = "name",
        entityColumn = "bank_name"
    )
    val accounts: List<LocalAccount>
)

