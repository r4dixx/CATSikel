package com.r4dixx.cats.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.r4dixx.cats.data.local.entities.AccountEntity
import com.r4dixx.cats.data.local.relations.AccountWithOperations
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDAO {
    @Transaction
    @Query("SELECT * FROM accounts WHERE id = :id")
    fun queryAccountWithOperations(id: Long) : Flow<AccountWithOperations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccounts(accounts: List<AccountEntity>)
}