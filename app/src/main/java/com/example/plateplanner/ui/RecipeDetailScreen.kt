package com.example.plateplanner.ui


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plateplanner.MainViewModel
import com.example.plateplanner.data.Recipe


@Composable
fun RecipeDetailScreenStateFul(recipeName : String?,viewModel: MainViewModel){
    val dataList by viewModel.data.collectAsState()
    if (recipeName != null) {
        Log.e("Resp",recipeName)
    }
    val recipe =dataList?.recipes?.find {  it.id == recipeName}
    if(recipe != null) {
        RecipeDetailScreen(recipe) {

        }
    }else{
        Text("Recipe not found.")
    }

}
@Composable
fun RecipeDetailScreen(
    recipe: Recipe,   // Pass the recipe data
    onBackClick: () -> Unit // Callback to navigate back
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Ingredients:",
            style = MaterialTheme.typography.bodyLarge
        )

        recipe.ingredients.forEach { ingredient ->
            Text(
                text = "- $ingredient",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Instructions:",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = recipe.instructions,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    RecipeDetailScreen(
        recipe = Recipe(
            id = "1",
            name = "Pasta",
            ingredients = listOf("Pasta", "Tomato Sauce", "Cheese"),
            instructions = "Boil pasta. Add sauce. Top with cheese."
        ),
        onBackClick = {}
    )
}
