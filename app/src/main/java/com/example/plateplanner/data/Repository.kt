package com.example.plateplanner.data

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
import com.example.plateplanner.data.local.entities.ShoppingListEntity
import com.example.plateplanner.data.remote.Recipe
import com.example.plateplanner.data.remote.Shoppinglist
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val apiKey : String, private val db : RecipeDatabase) {
    private suspend fun  fetchAndSaveData(shoppingList: Shoppinglist){
       db.shoppingListDao().InsertShoppingList(ShoppingListEntity(shoppingList.shoppingList))
       db.recipeDao().InsertRecipe(shoppingList.recipes)

    }
    suspend fun saveDishData(dish: Dish){
        db.dishDao().InsertDish(dish)
    }
    fun getData(): Flow<List<Recipe>> {
        return db.recipeDao().GetRecipes()
    }
    fun  getShoppingListData(): Flow<ShoppingListEntity>{
        return db.shoppingListDao().GetAllShoppingList()
    }
    fun getDishListData(): Flow<List<Dish>>{
        return db.dishDao().GetListData()
    }





    @OptIn(BetaOpenAI::class)
    suspend fun getGPTResponseAndSaveData(query: String) {
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
            val shoppinglist = Gson().fromJson(response, Shoppinglist::class.java)

            if (shoppinglist != null) {
                fetchAndSaveData(shoppinglist)
            }

          //  shoppinglist
        } catch (e: Exception) {
            Log.d("GPTRepository", "getGPTResponse: ERROR: ${e.message ?: ""}")
        }
    }
}