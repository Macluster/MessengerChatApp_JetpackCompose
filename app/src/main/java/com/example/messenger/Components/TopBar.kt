package com.example.messenger.Components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopBar(navController: NavController)
{
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp), backgroundColor = Color.White, onClick = {
        navController.navigate("userAdd")
    }) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)) {

            Text(text = "Messages", fontSize = 30.sp)
            Icon(imageVector = Icons.Default.Add, contentDescription ="", Modifier.size(30.dp) )

        }
    }
}
