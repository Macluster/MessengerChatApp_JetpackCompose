package com.example.messenger

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.messenger.Components.TopBar
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomePage(viewModel:Lazy<viewModel>,navController: NavController)
{
    var list=viewModel.value.Chatlist

    getData(viewModel.value.myNumber.value,{viewModel.value.addtoChatList(it)}){
    viewModel.value.clearChatList()
    }

    Box(modifier = Modifier
        .fillMaxWidth().fillMaxHeight()
        .background(Color.White))
    {

        Column() {

            TopBar(navController = navController)
            LazyColumn(){
                items(viewModel.value.Chatlist.size){ index->
                    UserCard(viewModel.value.Chatlist[index],navController)
                    {viewModel.value.changePresentChatModel(it)}
                }
            }

        }
        Card(modifier = Modifier.align(Alignment.BottomEnd).height(100.dp).width(100.dp), backgroundColor = Color.White, onClick = {   navController.navigate("userAdd")}) {
            Image(painter = painterResource(id = R.drawable.chat), contentDescription ="", modifier = Modifier.size(50.dp).padding(0.dp,0.dp,40.dp,40.dp))

        }

    }





}



fun getData(myNumber:String,addTOChatList:(UserModel)->Unit,clearChat:()->Unit)
{
    val database = Firebase.database
    val myRef = database.reference.child("users")
    myRef.addValueEventListener(object: ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.

            clearChat()



            if(snapshot.child(myNumber).child("chats").exists()) {
                var snapshot2=snapshot.child(myNumber).child("chats")
                for (snap in snapshot2.children) {
                    var name = snapshot.child(snap.key as String).child("name")
                        .getValue<String>() as String

                    var message = snapshot.child(snap.key as String).child("message")
                        .getValue<String>() as String
                    var model = UserModel(snap.key as String, name, "", message)

                    addTOChatList(model)
                }
            }

        }

        override fun onCancelled(error: DatabaseError) {

        }

    })


}






