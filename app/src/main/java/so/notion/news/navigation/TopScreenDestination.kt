package so.notion.news.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import so.notion.news.common.enterTransition
import so.notion.news.common.exitTransition
import so.notion.news.common.mvi.CollectSideEffect
import so.notion.news.common.mvi.unpack
import so.notion.news.common.popEnterTransition
import so.notion.news.common.popExitTransition
import so.notion.news.screens.top.TopNewsContract
import so.notion.news.screens.top.TopNewsScreen
import so.notion.news.screens.top.TopNewsViewModel

fun NavGraphBuilder.topNews(navController: NavHostController) {
    composable(
        route = Navigation.Routes.TOP,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
    ) {
        TopNewsScreenDestination(navController)
    }
}

@Composable
fun TopNewsScreenDestination(
    navController: NavHostController,
    viewModel: TopNewsViewModel = koinViewModel(),
) {

    val (uiState, onAction, sideEffect) = viewModel.unpack()

    CollectSideEffect(sideEffect) {
        when (it) {
            TopNewsContract.SideEffect.OnBackPressed -> navController.popBackStack()
        }
    }

    TopNewsScreen(uiState, onAction)
}