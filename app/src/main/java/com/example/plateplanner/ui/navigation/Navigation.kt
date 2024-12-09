package com.example.plateplanner.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.plateplanner.ui.component.BottomNavigationBar
import com.example.plateplanner.viewModel.MainViewModel
import com.example.plateplanner.ui.recipeScreen.RecipeDetailScreenStateFul
import com.example.plateplanner.ui.screen.shoppingListScreen.ShoppingListScreenStateful
import com.example.plateplanner.ui.screen.homeScreen.HomeScreenStateful


@Composable
fun Navigation( viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val context  = LocalContext.current
    Scaffold(bottomBar = {
            BottomNavigationBar(navController)
    })
    {innerPadding->


        NavHost(
            navController = navController, startDestination = BottomNavigation.HomeScreen.route
            ,modifier = Modifier.padding(innerPadding)
        ) {
            // Home Screen
            composable(BottomNavigation.HomeScreen.route) {
                HomeScreenStateful( viewModel) { recipeId ->
                    Log.e("recipe",recipeId.toString())
                    navController.navigate("${AppNavigation.RecipeScreen.route}/${recipeId}")
                }
            }
            // Shopping List Screen
            composable(BottomNavigation.ShoppingList.route) {
                ShoppingListScreenStateful(viewModel.shoppingList.collectAsState().value) {
                    viewModel.updateShoppingItem(it)
                }
            }
            // Corrected route definition for the RecipeScreen
            composable(
                route = "${AppNavigation.RecipeScreen.route}/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: run {
                    Log.e("Navigation", "Invalid recipeId")
                    return@composable
                }
                RecipeDetailScreenStateFul(recipeId, viewModel){
                    navController.popBackStack()
                }
            }
        }
    }
}


