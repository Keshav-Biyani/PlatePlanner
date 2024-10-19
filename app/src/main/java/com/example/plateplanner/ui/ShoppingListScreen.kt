package com.example.plateplanner.ui



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plateplanner.MainViewModel
import com.example.plateplanner.data.Shoppinglist


@Composable
fun ShoppingListScreenStateful(viewModel: MainViewModel,onBackClick: () -> Unit){
    val dataList by viewModel.data.collectAsState()
    ShoppingListScreen(dataList?.shoppingList!!) {
onBackClick()
    }

}
@Composable
fun ShoppingListScreen(
    shoppingItems: List<String>, // List of shopping items
    onBackClick: () -> Unit // Callback to navigate back
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Shopping List",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(shoppingItems.size) { index ->
                ShoppingListItem(item = shoppingItems[index])
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}

@Composable
fun ShoppingListItem(item: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        // Optional: You can add a checkbox or any other UI component here.
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListItemPreview() {
    ShoppingListItem(item = "Pasta")
}


@Preview(showBackground = true)
@Composable
fun ShoppingListScreenPreview() {
    ShoppingListScreen(
        shoppingItems = listOf("Pasta", "Tomato Sauce", "Cheese"),
        onBackClick = {}
    )
}
