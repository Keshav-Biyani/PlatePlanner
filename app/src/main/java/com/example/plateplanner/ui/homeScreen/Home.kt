package com.example.plateplanner.ui.homeScreen



import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plateplanner.MainViewModel
import com.example.plateplanner.data.Recipe
import com.example.plateplanner.utils.generateGPTQuery
import java.util.Locale


//If You want to render Preview You need to TextToSpeech
@Composable
fun HomeScreenStateful(ttsObject: TextToSpeech ,viewModel: MainViewModel,onNavigation:(String)->Unit) {
    var weeklyDishes by rememberSaveable { mutableStateOf(listOf<String>()) }
    var isSaveClicked by remember { mutableStateOf(false) }
    val data  by viewModel.data.collectAsState()
    val recipesList = data?.recipes// Store the list of recipes
    val isLoading by viewModel.isLoading.collectAsState()

    HomeScreenStateless(
        weeklyDishes = weeklyDishes,
        isSaveClicked,
        isLoading,
        onDishAdd = {dish->
            weeklyDishes= weeklyDishes + dish

        },
        onDishClick = {dishName->

            val selectedRecipe = recipesList?.find { it.id == dishName.toString() }
           // Log.e("Recipie Name",dishName)
            if (selectedRecipe != null) {
                // Navigate to recipe details or show recipe details
                Log.e("Recipie Name",selectedRecipe.name)
                onNavigation(selectedRecipe.id)
            // You can also pass selectedRecipe here for detailed screen navigation
            }
        },
        onSaveAction = {
            viewModel.gptQuery.value =generateGPTQuery(weeklyDishes, "Veg",4)

            viewModel.getGPTResponse(ttsObject)
            isSaveClicked = !isSaveClicked


        }
    )
}

@Composable
fun HomeScreenStateless(
    weeklyDishes: List<String>,  // List of dishes for the week
    isSaveClicked: Boolean, // Callback when a dish is added
    isLoading: Boolean,
    onDishAdd: (String) -> Unit,
    onDishClick: (Int) -> Unit,
    onSaveAction: () -> Unit,

// Callback when a dish is clicked
) {
    var newDish by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Plan Your Week's Dishes",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            DishInput(
                dishName = newDish,
                onDishChange = { newDish = it },
                onAddClick = {
                    if (newDish.isNotEmpty()) {
                        onDishAdd(newDish)
                        newDish = ""
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Dishes for the Week",
                style = MaterialTheme.typography.bodyLarge
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(weeklyDishes.size) { index ->
                    RecipeCard(
                        dishName = weeklyDishes[index],
                        isSaveClicked,
                        onClick = { onDishClick(index+1) }
                    )
                }
            }

        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            // Save button
            Button(
                onClick = { onSaveAction() },
                enabled = weeklyDishes.isNotEmpty(),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Save Weekly Dishes")
            }
        }

//        Button(
//            onClick = {
//                // Save action
//                onSaveAction()
//            },
//            enabled = weeklyDishes.isNotEmpty(),
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(16.dp)
//                .fillMaxWidth() // Optional: Make the button full-width
//        ) {
//            Text("Save Weekly Dishes")
//        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreenStateless(
        weeklyDishes = listOf("Pasta","Panner Butter Masala"),
        isSaveClicked = true,
        isLoading = false,
        onDishAdd = {},
        onDishClick = {},
        onSaveAction = {}
    )
}







