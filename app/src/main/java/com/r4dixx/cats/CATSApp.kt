package com.r4dixx.cats

import android.app.Application
import com.r4dixx.cats.core.ui.coreUiModule
import com.r4dixx.cats.core.utils.coreUtilsModule
import com.r4dixx.cats.data.remote.dataApiModule
import com.r4dixx.cats.domain.domainModule
import com.r4dixx.cats.feature.account.featureAccountModule
import com.r4dixx.cats.feature.banks.featureBanksModule
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
                coreUiModule,
                coreUtilsModule,
                dataApiModule,
                domainModule,
                featureAccountModule,
                featureBanksModule
            )
        }
    }
}
