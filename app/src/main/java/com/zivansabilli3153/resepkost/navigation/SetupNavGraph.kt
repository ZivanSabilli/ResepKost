package com.zivansabilli3153.resepkost.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zivansabilli3153.resepkost.ui.screen.DetailScreen
import com.zivansabilli3153.resepkost.ui.screen.DetailViewModel
import com.zivansabilli3153.resepkost.ui.screen.MainScreen
import com.zivansabilli3153.resepkost.ui.screen.MainViewModel
import com.zivansabilli3153.resepkost.util.ViewModelFactory

@Composable
fun SetupNavGraph(
    factory: ViewModelFactory
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            val viewModel: MainViewModel = viewModel(factory = factory)

            MainScreen(
                viewModel = viewModel,
                onAddClick = {
                    navController.navigate(Screen.Detail.createRoute(0L))
                },
                onItemClick = { recipeId ->
                    navController.navigate(Screen.Detail.createRoute(recipeId))
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getLong("recipeId") ?: 0L
            val viewModel: DetailViewModel = viewModel(factory = factory)

            DetailScreen(
                recipeId = recipeId,
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}