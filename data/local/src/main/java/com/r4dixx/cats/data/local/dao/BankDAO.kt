package com.r4dixx.cats.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.r4dixx.cats.data.local.relations.BankWithAccounts

@Dao
interface BankDAO {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertBanks(banks: List<BankEntity>)

    @Transaction
    @Query("SELECT * FROM banks")
    suspend fun queryBanks(): List<BankWithAccounts>
}