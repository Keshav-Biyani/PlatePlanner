package com.example.plateplanner.ui.screen.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DishInput(
    dishName: String,
    onDishChange: (String) -> Unit,
    onAddClick: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextField(
            value = dishName,
            onValueChange = onDishChange,
            label = { Text("Enter dish name") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray, RoundedCornerShape(8.dp))
                ,
            singleLine = true,
            //colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.background)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onAddClick,
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Add Dish", color = MaterialTheme.colorScheme.onPrimary)
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