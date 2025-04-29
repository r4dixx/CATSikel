package com.r4dixx.cats

import android.app.Application
import com.r4dixx.cats.core.ui.coreUiModule
import com.r4dixx.cats.core.utils.coreUtilsModule
import com.r4dixx.cats.data.api.dataApiModule
import com.r4dixx.cats.data.local.dataLocalModule
import com.r4dixx.cats.data.repository.dataRepositoryModule
import com.r4dixx.cats.di.appModule
import com.r4dixx.cats.domain.domainModule
import com.r4dixx.cats.feature.account.featureAccountModule
import com.r4dixx.cats.feature.banks.featureBanksModule
import io.kotzilla.sdk.analytics.koin.analytics
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CATSApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            analytics()
            androidLogger()
            androidContext(this@CATSApp)
            modules(
                appModule,
                coreUiModule,
                coreUtilsModule,
                dataLocalModule,
                dataApiModule,
                dataRepositoryModule,
                domainModule,
                featureAccountModule,
                featureBanksModule
            )
        }
    }
}
