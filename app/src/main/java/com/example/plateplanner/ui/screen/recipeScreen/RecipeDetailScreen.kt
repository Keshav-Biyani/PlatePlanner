package com.example.plateplanner.ui.recipeScreen


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.plateplanner.data.remote.Ingredient
import com.example.plateplanner.data.local.entities.RecipeData
import com.example.plateplanner.ui.screen.recipeScreen.component.IngredientListItem
import com.example.plateplanner.viewModel.MainViewModel
import java.util.Locale


@Composable
fun RecipeDetailScreenStateFul(recipeId : Int?, viewModel: MainViewModel,onBackClick: () -> Unit){

    if (recipeId != null) {
        Log.e("Resp",recipeId.toString())
    }
    val recipes by viewModel.recipes.collectAsState()
    val recipe =recipes.find {  it.id == recipeId}
    if(recipe != null) {
        RecipeDetailScreen(recipe.recipeData) {
            onBackClick()


        }
    }else{
        Text("Recipe not found.")
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipe: RecipeData,
    onBackClick: () -> Unit
) {
    val options = listOf("Ingredients","Instruction")

    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Text(
            text = recipe .name.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp, bottom = 16.dp)
        )
        SingleChoiceSegmentedButtonRow(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = { selectedIndex = index },
                    selected = index == selectedIndex,
                    colors = SegmentedButtonDefaults.colors(MaterialTheme.colorScheme.primary)
                ) {
                    Text(label)
                }
            }
        }
        Log.e("Index",selectedIndex.toString())
        if(selectedIndex ==0) {

               Column(modifier = Modifier.padding(5.dp)) {


                   Spacer(modifier = Modifier.height(8.dp))

                    recipe.ingredients.forEach {ingredient ->
                        IngredientListItem(ingredient = ingredient)
                   }
                }
           // }
        }else{

        // Card for Instructions
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Instructions:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = recipe.instructions,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Back Button
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Back", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}


@Preview(showBackground = true)
@PreviewLightDark
@PreviewDynamicColors
@Composable
fun RecipeDetailScreenPreview() {
    RecipeDetailScreen(
        recipe = RecipeData(
            id = 1,
            name = "Pasta",
            ingredients = listOf(Ingredient("Pasta","100GM"),Ingredient("Potato","200GM")),
            instructions = "Boil pasta. Add sauce. Top with cheese."
        ),
        onBackClick = {}
    )
}
