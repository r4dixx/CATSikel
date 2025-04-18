package com.r4dixx.cats.data.persistence

import androidx.room.Room
import com.r4dixx.cats.data.persistence.database.CATSDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataPersistenceModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            CATSDatabase::class.java,
            "catsikel"
        ).build()
    }
}