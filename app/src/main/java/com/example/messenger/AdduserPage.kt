package com.example.messenger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


@Composable
fun AddUserPage(viewModel:Lazy<viewModel>,navController: NavController)
{
    var number by remember {
        mutableStateOf("");
    }

    var list=viewModel.value.userList





    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Add User", fontSize = 35.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(50.dp))
            Card(backgroundColor = Color.LightGray, modifier = Modifier
                .width(290.dp)
                .height(60.dp), shape = RoundedCornerShape(30)) {
                TextField( value =number , onValueChange ={ number=it; valueChange(number,{viewModel.value.addtoUserList(it)}){viewModel.value.clearUserList()} }, colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent) )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {

                Text(text = "Matching Users", fontSize = 19.sp, color = Color.Black,fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn()
            {
                   items(list.size) {
                       index->
                       UserCard(viewModel.value.userList[index],navController)
                       {viewModel.value.changePresentChatModel(it)}
                   }
            }
        }


    }
}



fun valueChange(number:String,addToUserList:(UserModel)->Unit,clearUserList:()->Unit)
{
    val database = Firebase.database
    val myRef = database.reference.child("users")
    myRef.addListenerForSingleValueEvent(object: ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.

            clearUserList()




            for ( snap in snapshot.children)
            {

               if(snap.exists())
               {
                   var num :String=snap.key as String
                   var name=snap.child("name").getValue<String>() as String
                   var model=UserModel(num,name," "," ")
                   if(num.contains(number))
                      addToUserList(model)
               }





            } }

        override fun onCancelled(error: DatabaseError) {

        }

    })

}