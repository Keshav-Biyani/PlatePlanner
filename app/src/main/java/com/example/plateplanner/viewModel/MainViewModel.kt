package com.example.plateplanner.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plateplanner.data.repository.Repository
import com.example.plateplanner.data.local.entities.Dish
import com.example.plateplanner.data.localPreferences.EditablePref
import com.example.plateplanner.data.local.entities.Recipe
import com.example.plateplanner.data.local.entities.ShoppingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, private val editablePref: EditablePref) : ViewModel() {

    val gptQuery = mutableStateOf("")

    val isEditable = editablePref.getEditableStatus().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = true
    )

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _dishList = MutableStateFlow<List<Dish>>(emptyList())
    val dishList : StateFlow<List<Dish>> = _dishList

    private val _shoppingList = MutableStateFlow<List<ShoppingItem>?>(null)
    val shoppingList: StateFlow<List<ShoppingItem>?> = _shoppingList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        getDishList()
        getRecipes()
        getShoppingList()
    }

    fun saveEditablePref(isEditable : Boolean){
        viewModelScope.launch {
            editablePref.setEditableStatus(isEditable)
        }
    }

    // GPT Response
    fun getGPTResponse(dishList: List<Dish>) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getGPTResponseAndSaveData(gptQuery.value, dishList)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching GPT response: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }


    // Insert Dish
    fun insertDishInDB(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.saveDishData(dish)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error inserting dish: ${e.message}")
            }
        }
    }
    // Observe Recipes
    private fun getRecipes() {
        viewModelScope.launch {
            repository .getData()
                .collect { recipesList ->
                    _recipes.value = recipesList
                }
        }
    }
    // Observe Shopping List
    private fun getShoppingList() {
        viewModelScope.launch {
            repository .getShoppingListData()
                .collect { shoppingListData ->
                    _shoppingList.value = shoppingListData
                }
        }
    }
    // Observe Dish List
    private fun getDishList() {
        viewModelScope.launch {
            repository .getDishListData()
                .collect { DishList ->
                    _dishList.value =DishList
                }
        }
    }


    // Delete Dish
    fun deleteDish(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteDish(id)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error deleting dish: ${e.message}")
            }
        }
    }

    // Update Shopping Item
    fun updateShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateShoppingItem(shoppingItem)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error updating shopping item: ${e.message}")
            }
        }
    }



}
