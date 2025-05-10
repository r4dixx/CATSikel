package com.r4dixx.cats.core.utils

import org.koin.dsl.module
import java.util.Locale

val coreUtilsModule = module {
    single { Locale.FRANCE }
}