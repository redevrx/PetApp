package org.redev.rx.app.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.redev.rx.app.features.home.domain.viewModel.HomeViewModel

expect val platformModule:Module


val sharedModule = module {
    viewModelOf(::HomeViewModel)
}
