package com.example.plateplanner.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.plateplanner.data.remote.Ingredient

@Entity(
    tableName = "ShoppingTable",
    primaryKeys = ["id","ingredient"],
    foreignKeys = [
        ForeignKey(
            entity = Dish::class,
            parentColumns = ["id"],
            childColumns = ["id"], // Use the same 'id' as both primary key and foreign key
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ShoppingItem(
   // Same primary key as Dish.id
    val id : Int,
    val ingredient: Ingredient,
    val name: String,
    val shoppingStatus : Boolean = false
)

