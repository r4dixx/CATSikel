package com.r4dixx.cats.core

import com.r4dixx.cats.core.data.CATSHttpClient
import org.koin.dsl.module
import java.util.Locale

val coreModule = module {
    single { Locale.FRANCE }
    single { CATSHttpClient() }
}
