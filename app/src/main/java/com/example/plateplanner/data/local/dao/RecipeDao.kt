package com.example.plateplanner.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plateplanner.data.local.entities.Recipe
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertRecipe(recipe: List<Recipe>)

   @Query("Delete From RecipeTable")
   suspend fun  DeleteAllRecipeData()

   @Query("""Select  * From RecipeTable""")
   fun GetRecipes() : Flow<List<Recipe>>




}