package com.r4dixx.cats.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.r4dixx.cats.data.local.entities.AccountEntity
import com.r4dixx.cats.data.local.relations.AccountWithOperations

@Dao
interface AccountDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccounts(accounts: List<AccountEntity>)

    @Transaction
    @Query("SELECT * FROM accounts WHERE id = :id")
    suspend fun queryAccountWithOperations(id: Long) : AccountWithOperations
}