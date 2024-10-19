package com.example.plateplanner.navigation

import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plateplanner.MainViewModel
import com.example.plateplanner.ui.RecipeDetailScreen
import com.example.plateplanner.ui.RecipeDetailScreenStateFul
import com.example.plateplanner.ui.ShoppingListScreen
import com.example.plateplanner.ui.ShoppingListScreenStateful
import com.example.plateplanner.ui.homeScreen.HomeScreenStateful

@Composable
fun Navigation(ttsObject: TextToSpeech, viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = AppScreen.HomeScreen.route
    ) {
        composable(AppScreen.HomeScreen.route) {
            HomeScreenStateful(ttsObject, viewModel) { recipeName ->
                navController.navigate("${AppScreen.RecipeScreen.route}/$recipeName")
            }
        }
        composable(AppScreen.ShoppingList.route) {
            ShoppingListScreenStateful(viewModel) {
                navController.popBackStack()
            }
        }
        // Corrected route definition for the RecipeScreen
        composable(AppScreen.RecipeScreen.route + "/{recipeName}") { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName")
            RecipeDetailScreenStateFul(recipeName, viewModel)
        }
    }
}
