package com.example.plateplanner.data.local.entities

data class Recipe(
    val id: String,
    val ingredients: List<String>,
    val instructions: String,
    val name: String
)