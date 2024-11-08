package com.example.plateplanner.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Shopping_Table")
data class ShoppingListEntity( // Use a fixed ID since we have a single shopping list
    val items: List<String>,
    @PrimaryKey val id: Int = 0
)

