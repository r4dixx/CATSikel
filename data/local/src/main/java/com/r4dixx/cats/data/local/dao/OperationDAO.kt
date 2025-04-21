package com.r4dixx.cats.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.r4dixx.cats.data.local.entities.OperationEntity

@Dao
interface OperationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOperations(operations: List<OperationEntity>)
}