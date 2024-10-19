package com.example.plateplanner.ui.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DishInput(
    dishName: String,
    onDishChange: (String) -> Unit,
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextField(
            value = dishName,
            onValueChange = onDishChange,
            label = { Text("Enter dish name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onAddClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Dish")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DishInputPreview() {
    DishInput(
        dishName = "",
        onDishChange = {},
        onAddClick = {},

        )
}