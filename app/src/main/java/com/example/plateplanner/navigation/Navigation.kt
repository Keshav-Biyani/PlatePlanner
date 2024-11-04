package com.example.plateplanner.navigation

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
    Scaffold(bottomBar = {
            BottomNavigationBar(navController)
    })
    {innerPadding->


        NavHost(
            navController = navController, startDestination = BottomBarScreen.HomeScreen.route
            ,modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarScreen.HomeScreen.route) {
                HomeScreenStateful(ttsObject, viewModel) { recipeName ->
                    navController.navigate("${AppScreen.RecipeScreen.route}/$recipeName")
                }
            }
            composable(BottomBarScreen.ShoppingList.route) {
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
}
@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = BottomBarScreen.bottomNavScreens.any{it.route == currentDestination?.route}
    if(bottomBarDestination) {
        BottomAppBar {
            BottomBarScreen.bottomNavScreens.forEach { screen ->
                NavigationBarItem(
                    icon = { Icon(screen.icon, contentDescription = screen.label) },
                    label = { Text(screen.label) },
                    selected = navController.currentBackStackEntry?.destination?.route == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}


