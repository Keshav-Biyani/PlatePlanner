package com.example.plateplanner.data.remote

import com.example.plateplanner.data.local.entities.Recipe


data class Shoppinglist(
    val shoppingList: List<String>,
    val recipes: List<Recipe>
)