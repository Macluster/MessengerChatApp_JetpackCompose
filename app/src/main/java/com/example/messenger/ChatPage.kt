package com.example.messenger

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messenger.Components.ReceverCahtCard
import com.example.messenger.Components.SenderChatCard
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatPage(viewModel:Lazy<viewModel>)
{


    var message by remember {
        mutableStateOf("")
    }



    GetMessages(viewModel.value.presentChatMOdel.value.userNumber,viewModel.value.myNumber.value,{viewModel.value.addtoMessagetList(it)}){
        viewModel.value.clearMessageList()
    }
    var list=viewModel.value.currentMessageList


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Column() {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp), backgroundColor = Color(red = 100, green = 100, blue = 200)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(10.dp))

                    Image(painter = painterResource(id = R.drawable.man), contentDescription ="", modifier = Modifier.size(60.dp) )


                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = viewModel.value.presentChatMOdel.value.userName, fontSize = 30.sp, color = Color.White)
                }

            }



            LazyColumn(modifier = Modifier
                .weight(5f)
                .fillMaxWidth(), reverseLayout = true)
            {
                list.reverse()
                items(list.size){index->
                   if(list[index].who==viewModel.value.presentChatMOdel.value.userNumber)
                   {
                       ReceverCahtCard(model = list[index])

                   }
                    else
                   {
                       SenderChatCard(model = list[index])
                   }


                }
            }

            Row(modifier = Modifier

                .fillMaxWidth()
                .height(90.dp)
                , verticalAlignment = Alignment.CenterVertically) {

                Card(shape = RoundedCornerShape(40), modifier = Modifier

                    .weight(5f)
                    .fillMaxHeight()
                    .padding(10.dp), backgroundColor = Color.LightGray) {
                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        TextField(value = message, placeholder = {Text("Type Something")}, onValueChange ={message=it}, colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent) )
                    }


                }

                Card(shape = RoundedCornerShape(50),modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .weight(1f), backgroundColor = Color.White, onClick = {InsertMessage(viewModel.value.myNumber.value,MessageModel(message,
                    LocalTime.now().toString(), LocalDate.now().toString(),false,viewModel.value.presentChatMOdel.value.userNumber),viewModel.value.presentChatMOdel)}) {
                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                       Image(painter = painterResource(id = R.drawable.send), contentDescription = "")
                    }
                }



            }
        }
    }
}


fun GetMessages(presentChatModelNumber:String,myNumber:String,addToMessageList:(MessageModel)->Unit,clearMessageList:()->Unit)
{
    val database = Firebase.database
    val myRef = database.reference.child("users").child(myNumber).child("chats").child(presentChatModelNumber)

    myRef.addValueEventListener(object: ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.

            clearMessageList()




            for ( snap in snapshot.children)
            {
                if(snap.exists()&&snap.child("message").exists()) {
                    var message: String = snap.child("message").getValue<String>() as String
                    var date = snap.child("date").getValue<String>() as String
                    var time = snap.child("time").getValue<String>() as String
                    var sender = snap.child("who").getValue<String>() as String


                    var model = MessageModel(message, time, date, true, sender)

                    addToMessageList(model)
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }

    })

}

fun InsertMessage(myNumber:String,model: MessageModel, presentChatMOdel: MutableState<UserModel>)
{
    val database = Firebase.database
    database.reference.child("users").child(myNumber).child("chats").child(presentChatMOdel.value.userNumber).push().setValue(model)
     database.reference.child("users").child(myNumber).child("message").setValue(model.message)
   database.reference.child("users").child(presentChatMOdel.value.userNumber).child("chats").child(myNumber).push().setValue(model)
    database.reference.child("users").child(presentChatMOdel.value.userNumber).child("message").setValue(model.message)

}


