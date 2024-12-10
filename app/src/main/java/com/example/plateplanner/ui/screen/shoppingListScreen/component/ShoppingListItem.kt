package com.example.plateplanner.ui.screen.shoppingListScreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plateplanner.data.local.entities.ShoppingItem
import com.example.plateplanner.data.remote.Ingredient

@Composable
fun ShoppingListItem(shoppingItem: ShoppingItem, onCheckedChange: (ShoppingItem) -> Unit) {
    var isChecked by remember { mutableStateOf(shoppingItem.shoppingStatus) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            // Dish Name
            Text(
                text = shoppingItem.name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Item Text and Quantity
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = shoppingItem.ingredient.ingredient,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Quantity: ${shoppingItem.ingredient.quantity}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Optional Checkbox
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {status->
                        isChecked = status
                        onCheckedChange(shoppingItem.copy(shoppingStatus = status))
                    }, // Add logic if needed
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShoppingListItemPreview() {
    ShoppingListItem(ShoppingItem(1, Ingredient("Pasta","500GM"),"Pasta")){}
}
