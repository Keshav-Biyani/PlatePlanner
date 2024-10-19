package com.example.plateplanner.navigation

sealed class AppScreen(val route : String) {
    data object RecipeScreen: AppScreen("recipe_screen")
    data object HomeScreen : AppScreen("home_screen")
    data object ShoppingList : AppScreen("Shopping_List_Screen")
}