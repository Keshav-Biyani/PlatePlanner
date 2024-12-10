package com.example.plateplanner.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plateplanner.R


@Composable
fun EmptyList(nameOfList: String,image :Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Add an image
            Image(
                painter = painterResource(id = image), // Replace with your image resource
                contentDescription = "Empty list",
                modifier = Modifier
                    .size(120.dp) // Adjust size as needed
                    .padding(bottom = 16.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )

            // Add a text message
            Text(
                text = "Your $nameOfList list is empty!",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
@Preview
fun EmptyShoppingListPreview(){
    EmptyList("shopping",R.drawable.empty_plate)
}