package com.r4dixx.cats.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.r4dixx.cats.data.local.entities.BankEntity
import com.r4dixx.cats.data.local.relations.BankWithAccounts

@Dao
interface BankDAO {
    @Transaction
    @Query("SELECT * FROM banks")
    suspend fun queryBanksWithAccounts(): List<BankWithAccounts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanks(banks: List<BankEntity>)
}