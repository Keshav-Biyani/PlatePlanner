package com.example.plateplanner.ui.screen.shoppingListScreen



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
import com.example.plateplanner.R
import com.example.plateplanner.data.local.entities.ShoppingItem
import com.example.plateplanner.data.remote.Ingredient
import com.example.plateplanner.ui.component.EmptyList
import com.example.plateplanner.ui.screen.shoppingListScreen.component.ShoppingListItem


@Composable
fun ShoppingListScreenStateful(
    dataList: List<ShoppingItem>?,
    onUpdateShoppingItem: (ShoppingItem) -> Unit
){
    if (dataList.isNullOrEmpty()) {
        EmptyList("shopping", R.drawable.empty_shopping_list)
    } else {
        ShoppingListScreen(dataList, onUpdateShoppingItem)
    }

}
@Composable
fun ShoppingListScreen(
    shoppingItems: List<ShoppingItem>,
    onUpdateShoppingItem: (ShoppingItem) -> Unit
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
                ShoppingListItem( shoppingItems[index]){shoppingItem->
                    onUpdateShoppingItem(shoppingItem)

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShoppingListScreenPreview() {
    ShoppingListScreen(
        shoppingItems = listOf(ShoppingItem(1, Ingredient("Pasta","500GM"),"Pasta"),ShoppingItem(1, Ingredient("Panner","500GM"),"Pasta")),
        onUpdateShoppingItem = {}
    )
}
