package org.redev.rx.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.redev.rx.app.navigation.Nav
import org.redev.rx.app.navigation.NavGraphRoot

@Composable
@Preview
fun App() {
    MaterialTheme {
        CompositionLocalProvider(Nav provides rememberNavController()) {
            NavGraphRoot()
        }
    }
}