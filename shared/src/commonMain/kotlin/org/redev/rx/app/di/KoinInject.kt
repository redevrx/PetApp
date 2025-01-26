package org.redev.rx.app.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.redev.rx.app.features.home.domain.viewModel.HomeViewModel


class KoinInject : KoinComponent {
    val homeViewModel: HomeViewModel = get()
}
