package org.redev.rx.app.navigation

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import org.redev.rx.app.features.home.view.HomeView
import org.redev.rx.app.router.Router

val Nav = compositionLocalOf<NavHostController> {
    error("create provider nav controller error")
}

fun NavHostController.push(destination: Router) {
    navigate(destination)
}

fun NavHostController.pop() {
    navigateUp()
}

@Composable
fun NavGraphRoot(nav: NavHostController = Nav.current) {
    NavHost(nav, startDestination = Router.Home) {
        composable<Router.Home> {
            HomeView()
        }
        
        composable<Router.DetailView> {
            Text(
                "DetailView: ${it.toRoute<Router.DetailView>().route}",
                modifier = Modifier.clickable(onClick = nav::pop)
            )
        }
    }
}
