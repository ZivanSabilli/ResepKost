package com.zivansabilli3153.resepkost.navigation

sealed class Screen(val route: String) {
    data object Main : Screen("main")

    data object Detail : Screen("detail/{recipeId}") {
        fun createRoute(recipeId: Long): String {
            return "detail/$recipeId"
        }
    }
}