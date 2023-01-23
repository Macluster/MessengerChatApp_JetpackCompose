package com.example.messenger

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserCard(model: UserModel, navController: NavController, changePresentChatModel:(UserModel)->Unit)
{
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .padding(5.dp), backgroundColor = Color.White, onClick = {

        navController.navigate("chatpage")
        changePresentChatModel(model)







    }) {

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {


            Image(painter = painterResource(id = R.drawable.man), contentDescription ="",Modifier.size(80.dp) )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(text =model.userName, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = model.UserMessage, color = Color.Green)

            }

        }

    }
}