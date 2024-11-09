package com.example.plateplanner.ui.homeScreen



import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plateplanner.ui.viewModel.MainViewModel
import com.example.plateplanner.data.local.entities.Dish
import com.example.plateplanner.utils.generateGPTQuery



@Composable
fun HomeScreenStateful(viewModel: MainViewModel, onNavigation:(String)->Unit) {
    val weeklyDishes by viewModel.dishList.collectAsState()
    val recipesList  by viewModel.recipes.collectAsState()// Store the list of recipes
    val isLoading by viewModel.isLoading.collectAsState()
    val isEditable by viewModel.isEditable.collectAsState()

    HomeScreenStateless(
        weeklyDishes = weeklyDishes,
        isEditable,
        isLoading,
        onDishAdd = { dish->
            viewModel.insertDishInDB(Dish(dish))
        },
        onDishClick = { dishName->

            val selectedRecipe = recipesList.find { it.id == dishName.toString() }
           // Log.e("Recipie Name",dishName)
            if (selectedRecipe != null) {
                // Navigate to recipe details or show recipe details
                Log.e("Recipie Name",selectedRecipe.name)
                onNavigation(selectedRecipe.id)
            // You can also pass selectedRecipe here for detailed screen navigation
            }
        },
        onSaveAction = {
            val dishList = weeklyDishes.map{it.dishList}
           viewModel.gptQuery.value =generateGPTQuery(dishList, "Veg",4)

            viewModel.getGPTResponse()
            viewModel.saveEditablePref(false)


        }, setEditable = {
            viewModel.saveEditablePref(true)
        }

    )
}

@Composable
fun HomeScreenStateless(
    weeklyDishes: List<Dish>,
    isEditable: Boolean,
    isLoading: Boolean,
    onDishAdd: (String) -> Unit,
    onDishClick: (Int) -> Unit,
    onSaveAction: () -> Unit,
    setEditable :()->Unit
) {
    var newDish by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Plan Your Week's Dishes",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))
            if(isEditable){
            DishInput(
                dishName = newDish,
                isEditable= isEditable,
                onDishChange = { newDish = it },
                onAddClick = {
                    if (newDish.isNotEmpty()) {
                        onDishAdd(newDish)
                        newDish = ""
                    }
                }
            )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Dishes for the Week",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, bottom = 64.dp)
            ) {
                items(weeklyDishes){dish->
                    RecipeCard(
                    dishName =dish.dishList ,
                    isEditable= isEditable || isLoading,
                    onClick = { onDishClick(dish.id) }
                )}


            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if(isEditable) {
            Button(
                onClick = { onSaveAction() },
                enabled = weeklyDishes.isNotEmpty(),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.Filled.Search, // Use an AI-related icon here if you have one
                    contentDescription = "AI Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(end = 8.dp) // Adds space between the icon and the text
                )
                Text("Generate Recipe", color = MaterialTheme.colorScheme.onPrimary)
            }
        }else{
            Button(
                onClick = { setEditable() },
                enabled = weeklyDishes.isNotEmpty(),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Click for Edit", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreenStateless(
        weeklyDishes = listOf<Dish>(Dish("Pasta")),
        isEditable = true,
        isLoading = false,
        onDishAdd = {},
        onDishClick = {},
        onSaveAction = {},
        setEditable = {}
    )
}







