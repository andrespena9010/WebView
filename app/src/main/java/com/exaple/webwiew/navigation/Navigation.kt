package com.exaple.webwiew.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exaple.webwiew.ui.theme.WebWiewTheme
import com.exaple.webwiew.ui.views.URLList
import com.exaple.webwiew.ui.views.WebViewCompose
import kotlinx.serialization.Serializable


interface NoParamView

@Serializable
sealed class Views {

    @Serializable
    object URLList : NoParamView

    @Serializable
    object WebViewCompose : NoParamView

}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Views.URLList
    ) {

        composable<Views.URLList> {
            WebWiewTheme {
                URLList(nav = navController)
            }
        }

        composable<Views.WebViewCompose> {
            WebWiewTheme {
                WebViewCompose( navController )
            }
        }

    }
}
