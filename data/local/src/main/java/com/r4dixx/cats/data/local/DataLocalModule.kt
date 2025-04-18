package com.r4dixx.cats.data.local

import androidx.room.Room
import com.r4dixx.cats.data.local.database.CATSDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            CATSDatabase::class.java,
            "catsikel"
        ).build()
    }
}