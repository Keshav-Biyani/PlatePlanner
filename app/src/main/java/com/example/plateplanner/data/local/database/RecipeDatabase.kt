package com.example.plateplanner.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plateplanner.data.local.dao.RecipeDao

abstract class RecipeDatabase : RoomDatabase(){
    abstract fun recipeDao() :RecipeDao
    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeDatabase::class.java,
                        "RecipeDatabase"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}