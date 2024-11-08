package com.example.plateplanner.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plateplanner.data.local.dao.DishDao
import com.example.plateplanner.data.local.dao.RecipeDao
import com.example.plateplanner.data.local.dao.ShoppingListDao
import com.example.plateplanner.data.local.entities.Dish
import com.example.plateplanner.data.local.entities.ShoppingListEntity
import com.example.plateplanner.data.remote.Converters
import com.example.plateplanner.data.remote.Recipe


@Database(
entities =[Recipe::class ,ShoppingListEntity ::class,Dish ::class],
version = 1
)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase(){
    abstract fun recipeDao() :RecipeDao
    abstract fun shoppingListDao() : ShoppingListDao
    abstract fun dishDao() : DishDao
//    companion object {
//        @Volatile
//        private var INSTANCE: RecipeDatabase? = null
//
//        fun getInstance(context: Context): RecipeDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        RecipeDatabase::class.java,
//                        "RecipeDatabase"
//                    ).fallbackToDestructiveMigration().build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }

}