package com.example.plateplanner.data.repository

import android.util.Log
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.example.plateplanner.data.local.database.RecipeDatabase
import com.example.plateplanner.data.local.entities.Dish
import com.example.plateplanner.data.local.entities.Recipe
import com.example.plateplanner.data.local.entities.ShoppingItem
import com.example.plateplanner.data.remote.RecipeData
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val apiKey : String, private val db : RecipeDatabase) {

    private suspend fun fetchAndSaveData(recipeDataList: RecipeData, dishList: List<Dish>) {
        val recipes = mutableListOf<Recipe>()
        val shoppingItems = mutableListOf<ShoppingItem>()

        recipeDataList.recipes.forEachIndexed { index, recipeData ->
            val dish = dishList.getOrNull(index)
            if (dish == null) {
                Log.e("Repository", "Dish ID not found for recipe: ${recipeData.name}")
                return@forEachIndexed
            }

            // Create Recipe and Shopping Items
            recipes.add(Recipe(recipeData, dish.id))
            shoppingItems.addAll(
                recipeData.ingredients.map { ingredient ->
                    ShoppingItem(dish.id, ingredient, recipeData.name)
                }
            )
        }

        // Batch insert for efficiency
        db.recipeDao().insertRecipe(recipes)
        db.shoppingItemDao().insertShoppingItems(shoppingItems)
    }

    suspend fun saveDishData(dish: Dish){
        db.dishDao().InsertDish(dish)
    }
    fun getData(): Flow<List<Recipe>> {
        return db.recipeDao().getRecipes()
    }
    fun  getShoppingListData(): Flow<List<ShoppingItem>> {
        return db.shoppingItemDao().getAllShoppingList()
    }
    fun getDishListData(): Flow<List<Dish>>{
        return db.dishDao().getListData()
    }
    suspend fun deleteDish(id : Int){
        db.dishDao().deleteDish(id)
    }
    suspend fun updateShoppingItem(shoppingItem: ShoppingItem){
        db.shoppingItemDao().updateShoppingItem(shoppingItem)
    }





    @OptIn(BetaOpenAI::class)
    suspend fun getGPTResponseAndSaveData(query: String, dishList: List<Dish>) {
        val openAI = OpenAI(apiKey)
        try {
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.User,
                        content = query
                    )
                )
            )

            val completion: ChatCompletion = withContext(Dispatchers.IO) {
                openAI.chatCompletion(chatCompletionRequest)
            }

            val response = completion.choices.first().message?.content
            val recipeList = Gson().fromJson(response, RecipeData::class.java)
            Log.e("ShoopingList",recipeList.toString())

            if (recipeList != null) {
                fetchAndSaveData(recipeList,dishList)
            }else {
                Log.e("Repository", "Failed to parse GPT response into RecipeData")
            }

          //  shoppinglist
        } catch (e: Exception) {
            Log.d("GPTRepository", "getGPTResponse: ERROR: ${e.message ?: ""}")
        }
    }
}