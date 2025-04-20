package com.r4dixx.cats.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banks")
data class BankEntity(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "is_ca") val isCA: Boolean
)