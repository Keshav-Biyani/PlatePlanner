package com.example.plateplanner.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plateplanner.data.local.dao.DishDao
import com.example.plateplanner.data.local.dao.RecipeDao
import com.example.plateplanner.data.local.dao.ShoppingItemDao
import com.example.plateplanner.data.local.entities.Dish
import com.example.plateplanner.data.local.Converters
import com.example.plateplanner.data.local.entities.Recipe
import com.example.plateplanner.data.local.entities.ShoppingItem


@Database(
entities =[Recipe::class ,ShoppingItem ::class,Dish ::class],
version = 1
)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase(){
    abstract fun recipeDao() :RecipeDao
    abstract fun shoppingItemDao() : ShoppingItemDao
    abstract fun dishDao() : DishDao

}