package com.example.plateplanner.ui



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ShoppingListScreenStateful(dataList: List<String>, onBackClick: () -> Unit){

    ShoppingListScreen(dataList) {
onBackClick()
    }

}
@Composable
fun ShoppingListScreen(
    shoppingItems: List<String>,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Text(
            text = "Shopping List",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier

                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        // LazyColumn for Shopping Items
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp)
        ) {
            items(shoppingItems.size) { index ->
                ShoppingListItem(item = shoppingItems[index])
            }
        }

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

@Composable
fun ShoppingListItem(item: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Item Text
            Text(
                text = item,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )

            // Optional Checkbox
            Checkbox(
                checked = false,
                onCheckedChange = {}, // Add logic if needed
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary
                )
            )
        }
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
