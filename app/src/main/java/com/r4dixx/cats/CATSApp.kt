package com.r4dixx.cats

import android.app.Application
import com.r4dixx.cats.core.coreModule
import com.r4dixx.cats.data.dataModule
import com.r4dixx.cats.domain.domainModule
import com.r4dixx.cats.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CATSApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CATSApp)
            modules(
                coreModule,
                dataModule,
                domainModule,
                uiModule
            )
        }
    }
}
