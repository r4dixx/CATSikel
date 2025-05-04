package com.r4dixx.cats.data.local

import androidx.room.Room
import com.r4dixx.cats.data.local.database.CATSDatabase
import com.r4dixx.cats.data.local.source.BanksLocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {
    single { get<CATSDatabase>() }
    single { BanksLocalDataSource(database = get()) }
    single {
        Room.databaseBuilder(androidContext(), CATSDatabase::class.java, "catsikel")
            .fallbackToDestructiveMigration(true)
            .build()
    }
}