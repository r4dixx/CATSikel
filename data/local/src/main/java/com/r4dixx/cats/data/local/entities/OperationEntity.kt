package com.r4dixx.cats.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal
import kotlin.time.Instant


@Entity(
    tableName = "operations",
    foreignKeys = [ForeignKey(
        entity = AccountEntity::class,
        parentColumns = ["id"],
        childColumns = ["account_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class OperationEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "account_id", index = true) val accountId: Long,
    @ColumnInfo val title: String,
    @ColumnInfo val amount: BigDecimal,
    @ColumnInfo val date: Instant
)