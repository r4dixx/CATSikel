package com.r4dixx.cats.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.r4dixx.cats.data.local.entities.AccountEntity
import com.r4dixx.cats.data.local.entities.OperationEntity

data class AccountWithOperations(
    @Embedded val account: AccountEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "account_id"
    )
    val operations: List<OperationEntity>
)
