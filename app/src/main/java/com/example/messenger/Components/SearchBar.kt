package com.example.messenger.Components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar()
{
    Card(modifier = Modifier
        .width(200.dp)
        .height(60.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Search, contentDescription ="", modifier = Modifier.size(30.dp) )

            TextField(value = "Search message", onValueChange ={}, colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, unfocusedIndicatorColor = Color.White, focusedIndicatorColor = Color.White) )
        }

    }
}
