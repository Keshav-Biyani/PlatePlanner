package com.example.plateplanner.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plateplanner.data.repository.Repository
import com.example.plateplanner.data.local.entities.Dish
import com.example.plateplanner.data.local.entities.ShoppingListEntity
import com.example.plateplanner.data.local.entities.Recipe
import com.example.plateplanner.data.localPreferences.EditablePref
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _shoppingList = MutableStateFlow<ShoppingListEntity?>(null)
    val shoppingList: StateFlow<ShoppingListEntity?> = _shoppingList

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

    fun getGPTResponse() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getGPTResponseAndSaveData(gptQuery.value)
            getRecipes()
            getShoppingList()
            _isLoading.value =  false

        }
    }
    fun insertDishInDB(dish: Dish){
            viewModelScope.launch {
                repository.saveDishData(dish)
            }
    }

    private fun getRecipes() {
        viewModelScope.launch {
            repository .getData()
                .collect { recipesList ->
                    _recipes.value = recipesList
                }
        }
    }
    private fun getShoppingList() {
        viewModelScope.launch {
            repository .getShoppingListData()
                .collect { shoppingListData ->
                    _shoppingList.value = shoppingListData
                }
        }
    }
    private fun getDishList() {
        viewModelScope.launch {
            repository .getDishListData()
                .collect { DishList ->
                    _dishList .value =DishList
                }
        }
    }



}
//val openAI = OpenAI(api.toString())
//
//try {
//    val chatCompletionRequest = ChatCompletionRequest(
//        model = ModelId("gpt-3.5-turbo"),
//        messages = listOf(
//            ChatMessage(
//                role = ChatRole.User,
//                content = gptQuery.value
//            )
//        )
//    )
//
//    val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
//
//    val response = completion.choices.first().message?.content
//    val gson = Gson()
//    val list = gson.fromJson(response, Shoppinglist::class.java)
//    _dataList.value = list
//    _isLoading.value = false
//
//} catch (e: Exception) {
//    Log.d(TAG, "getGPTResponse: ERROR: ${e.message ?: ""}")
//}
//    private fun say(textToSpeech: TextToSpeech, response: String?) {
//        textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
//            override fun onRangeStart(utteranceId: String?, start: Int, end: Int, frame: Int) {
//                super.onRangeStart(utteranceId, start, end, frame)
//
//                word.value = "${word.value} ${response?.substring(start, end) ?: ""}"
//            }
//
//            override fun onStart(p0: String?) {
//                word.value = ""
//            }
//
//            override fun onDone(p0: String?) {}
//
//            override fun onError(p0: String?) {}
//        })
//
//        textToSpeech.speak(
//            response,
//            TextToSpeech.QUEUE_FLUSH,
//            null,
//            "utterance_id"
//        )
//    }