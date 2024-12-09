package com.example.plateplanner.ui.navigation


sealed class AppNavigation(val route : String) {
    data object RecipeScreen: AppNavigation("recipe_screen")
}
