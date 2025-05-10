package com.r4dixx.cats.data.local

import android.content.Context
import androidx.room.Room
import com.r4dixx.cats.data.local.database.CATSDatabase
import com.r4dixx.cats.data.local.source.BanksLocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {
    single { provideDatabase(androidContext()) }
    single { BanksLocalDataSource(database = get()) }
}

fun provideDatabase(context: Context): CATSDatabase {
    return Room.databaseBuilder(
        context = context,
        klass = CATSDatabase::class.java,
        name = "catsikel"
    ).fallbackToDestructiveMigration(true).build()
}