package com.example.messenger.InititalPages

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.messenger.R
import com.example.messenger.viewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OnBoardingPage(sharedPreferences: SharedPreferences,navController: NavController,viewModel:Lazy<viewModel>)
{

    var contentIndex by remember {
        mutableStateOf(0)
    }

    var userName by remember {
        mutableStateOf("")
    }


    fun changecontentIndex()
    {
        contentIndex++;
    }

    fun changeUserName(name:String)
    {
        userName=name
    }
    Box(modifier = Modifier.fillMaxSize()){



        Column(  horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) {


            if(contentIndex==0)
            {
                ContentOne()
            }
            else if(contentIndex==1)
            {
                ContentTwo(sharedPreferences,viewModel,{changecontentIndex()}){
                    changeUserName(it)
                }
            }
            else if(contentIndex==2)
            {
               ContentThree(userName)
            }

            Card(modifier = Modifier
                .height(60.dp)
                .width(60.dp), backgroundColor = Color.Black, shape = RoundedCornerShape(50), onClick = {contentIndex++

                if(contentIndex==3)
                {
                    // navController.popBackStack()
                    navController.navigate("home")
                }
                }) {

                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(text = ">", color = Color.White, fontSize = 27.sp)
                }


            }
        }

    }
    
}
@Composable
fun ContentOne()
{
    var text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    Card(modifier = Modifier
        .fillMaxSize(0.8f)
        .shadow(0.dp, clip = true)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) {
            Text(text = "Welcome \n\t\t\t\t\tto messenger", fontSize = 35.sp, fontWeight = FontWeight.Bold)
            Image(painter = painterResource(id = R.drawable.omg1), contentDescription ="", modifier = Modifier.size(350.dp) )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text =text, fontSize = 17.sp, color = Color.Gray, textAlign = TextAlign.Center, modifier = Modifier.width(320.dp) )

            Spacer(modifier = Modifier.height(50.dp))



        }
    }

}


@Composable
fun ContentTwo(sharedPreferences: SharedPreferences,viewModel:Lazy<viewModel>,changeContentIndex:()->Unit,changeuserName:(String)->Unit)
{
    var number by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    Card(modifier = Modifier.fillMaxSize(0.8f).shadow(0.dp, clip = true)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) {

            Text(text = "Enter your Details", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(60.dp))
            Card(modifier = Modifier.width(260.dp), backgroundColor = Color.LightGray, shape = RoundedCornerShape(30)) {
                TextField(value =number , placeholder = { Text(text = "Phone Number")}, onValueChange ={number=it}, colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent) )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(modifier = Modifier.width(260.dp), backgroundColor = Color.LightGray, shape = RoundedCornerShape(30)) {
                TextField(value =name , placeholder = { Text(text = "Name")},onValueChange ={name=it}, colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent) )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                val database = Firebase.database
                 database.reference.child("users").child(number).child("name").setValue(name)
                database.reference.child("users").child(number).child("chats").setValue("")
                database.reference.child("users").child(number).child("message").setValue("")

                var editor = sharedPreferences.edit()
                editor.putBoolean("isdone",true)
                editor.putString("mynumber",number)
                editor.commit()
                changeContentIndex()
                changeuserName(name)
                viewModel.value.changeMyNumber(number)
            }) {
                Text(text = "Register")
            }



        }
    }
}





@Composable
fun ContentThree(name :String)
{

    Card(modifier = Modifier
        .fillMaxSize(0.8f)
        .shadow(0.dp, clip = true)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) {

            Text(text ="Welcome $name\n to Messenger", fontSize = 30.sp, color = Color.Black, textAlign = TextAlign.Center, modifier = Modifier.width(320.dp) )

            Spacer(modifier = Modifier.height(50.dp))



        }
    }

}