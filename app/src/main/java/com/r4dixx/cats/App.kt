package com.r4dixx.cats

import android.app.Application
import com.r4dixx.cats.data.coreModule
import com.r4dixx.cats.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                coreModule,
                dataModule
            )
        }
    }
}
