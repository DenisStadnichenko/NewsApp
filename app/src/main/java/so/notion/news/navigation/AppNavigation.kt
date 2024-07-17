package so.notion.news.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.TOP
    ) {
        topNews(navController)
    }
}

object Navigation {
    object Routes {
        const val TOP = "topNews"
    }
}
