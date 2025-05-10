package com.r4dixx.cats.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(
    tableName = "accounts",
    foreignKeys = [ForeignKey(
        entity = BankEntity::class,
        parentColumns = ["name"],
        childColumns = ["bank_name"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AccountEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "bank_name", index = true) val bankName: String,
    @ColumnInfo val label: String,
    @ColumnInfo val balance: BigDecimal
)