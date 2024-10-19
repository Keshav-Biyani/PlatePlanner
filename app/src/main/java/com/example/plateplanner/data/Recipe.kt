package com.example.plateplanner.data

data class Recipe(
    val id: String,
    val ingredients: List<String>,
    val instructions: String,
    val name: String
)