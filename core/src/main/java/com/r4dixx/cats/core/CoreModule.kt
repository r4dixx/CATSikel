package com.r4dixx.cats.core

import org.koin.dsl.module
import java.util.Locale

val coreModule = module {
    single { Locale.FRANCE }
}
