package com.example.plateplanner.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.plateplanner.data.local.entities.Recipe
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {

    @Upsert
    suspend fun insertRecipe(recipe: List<Recipe>)

   @Query("Delete From RecipeTable")
   suspend fun  deleteAllRecipeData()

   @Query("""Select  * From RecipeTable""")
   fun getRecipes() : Flow<List<Recipe>>




}