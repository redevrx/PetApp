package org.redev.rx.app.router

import kotlinx.serialization.Serializable

@Serializable
sealed class Router(val route:String) {
    @Serializable
    object Home: Router(Route.HOME)

    @Serializable
    object DetailView: Router(Route.DETAIL_VIEW)
}

