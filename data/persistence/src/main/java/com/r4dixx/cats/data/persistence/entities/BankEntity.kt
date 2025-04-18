package com.r4dixx.cats.data.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banks")
data class BankEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo val name: String,
    @ColumnInfo (name = "is_ca") val isCA: Boolean?,
)