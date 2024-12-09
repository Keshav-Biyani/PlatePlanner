package com.example.plateplanner.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigation(val route: String, val icon: ImageVector, val label: String) {
    object HomeScreen : BottomNavigation("home_screen", Icons.Default.Home, "Home")
    object ShoppingList : BottomNavigation("shopping_list_screen", Icons.Default.ShoppingCart, "Shopping List")


    companion object {
        // List of screens in bottom navigation bar
        val bottomNavScreens = listOf(HomeScreen, ShoppingList)
    }
}