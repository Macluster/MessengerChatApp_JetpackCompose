package com.example.messenger.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.messenger.MessageModel


@Composable

fun SenderChatCard(model:MessageModel)
{

    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.Start,) {
        Card(shape = RoundedCornerShape(40),modifier = Modifier
            .height(40.dp)
            .width(200.dp), backgroundColor = Color.LightGray) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = model.message, fontWeight = FontWeight.Bold)
            }

        }
    }


}