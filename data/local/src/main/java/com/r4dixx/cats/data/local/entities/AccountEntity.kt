package com.r4dixx.cats.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.r4dixx.cats.data.local.entities.BankEntity
import java.math.BigDecimal

@Entity(
    tableName = "accounts",
    foreignKeys = [
        ForeignKey(
            entity = BankEntity::class,
            parentColumns = ["id"],
            childColumns = ["bank_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AccountEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "bank_id", index = true) val bankId: String,
    @ColumnInfo(name = "bank_name", index = true) val bankName: String,
    @ColumnInfo val label: String?,
    @ColumnInfo val balance: BigDecimal?
)