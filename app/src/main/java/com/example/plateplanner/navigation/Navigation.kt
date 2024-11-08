package com.example.plateplanner.navigation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.plateplanner.MainViewModel
import com.example.plateplanner.ui.RecipeDetailScreenStateFul
import com.example.plateplanner.ui.ShoppingListScreenStateful
import com.example.plateplanner.ui.homeScreen.HomeScreenStateful

@Composable
fun Navigation( viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val context  = LocalContext.current
    Scaffold(bottomBar = {
            BottomNavigationBar(navController)
    })
    {innerPadding->


        NavHost(
            navController = navController, startDestination = BottomBarScreen.HomeScreen.route
            ,modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarScreen.HomeScreen.route) {
                HomeScreenStateful( viewModel) { recipeName ->
                    navController.navigate("${AppScreen.RecipeScreen.route}/$recipeName")
                }
            }
            composable(BottomBarScreen.ShoppingList.route) {
                val dataList by viewModel.shoppingList.collectAsState()

                Log.e("Data",dataList.toString())
                if(dataList != null) {
                    ShoppingListScreenStateful(dataList!!.items) {
                        navController.popBackStack()
                    }
                }else{
                    Toast.makeText(context,"Empty Shopping List",Toast.LENGTH_SHORT).show()
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


