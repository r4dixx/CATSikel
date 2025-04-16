package com.r4dixx.cats

import android.app.Application
import com.r4dixx.cats.core.di.coreModule
import com.r4dixx.cats.data.api.di.dataApiModule
import com.r4dixx.cats.domain.di.domainModule
import com.r4dixx.cats.feature.account.di.featureAccountModule
import com.r4dixx.cats.feature.banks.di.featureBanksModule
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
                dataApiModule,
                domainModule,
                featureAccountModule,
                featureBanksModule
            )
        }
    }
}
