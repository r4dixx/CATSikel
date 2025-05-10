package com.r4dixx.cats.core.ui

import com.r4dixx.cats.core.ui.state.CATSStateHandler
import org.koin.dsl.module

val coreUiModule = module {
    factory { CATSStateHandler<Any?>() }
}