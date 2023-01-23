package com.example.messenger.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(modifier: Modifier)
{
    Box(
        modifier = modifier)
    {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .align(Alignment.BottomCenter), backgroundColor = Color.White,) {

            Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
                Icon(imageVector = Icons.Default.Home, contentDescription ="",modifier= Modifier.size(30.dp), tint = Color.Gray )

                Icon(imageVector = Icons.Default.Email, contentDescription ="",modifier= Modifier.size(30.dp) , tint = Color.Gray)
                Icon(imageVector = Icons.Default.Phone, contentDescription ="",modifier= Modifier.size(30.dp), tint = Color.Gray )

                Icon(imageVector = Icons.Default.Settings, contentDescription ="",modifier= Modifier.size(30.dp), tint = Color.Gray )
            }

        }

        Card(modifier = Modifier
            .height(70.dp)
            .width(70.dp)
            .align(Alignment.TopCenter), shape = RoundedCornerShape(50), backgroundColor = Color.Magenta) {

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.Add, contentDescription ="", modifier = Modifier.size(30.dp), tint = Color.White )
            }
        }
    }
}

