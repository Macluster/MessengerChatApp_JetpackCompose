package com.example.messenger

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.messenger.InititalPages.OnBoardingPage
import com.example.messenger.ui.theme.MessengerTheme
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var FirstPage="Onboardingpage"
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            var viewModel=viewModels<viewModel>();
            val navController = rememberNavController()



            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
           var isDone=  sharedPreference.getBoolean("isdone",false)
            var number=sharedPreference.getString("mynumber","") as String
            viewModel.value.changeMyNumber(number);

           if(isDone==true)
           {
               FirstPage="home"
           }
            else
           {
               FirstPage="Onboardingpage"
           }
            MessengerTheme {
                // A surface container using the 'background' color from the theme


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Box(modifier = Modifier.fillMaxSize())
                    {
                        Column(verticalArrangement = Arrangement.SpaceBetween) {


                            NavHost(navController = navController, startDestination = FirstPage) {
                                composable("home") { HomePage(viewModel =viewModel, navController = navController) }
                                composable("chatpage") { ChatPage(viewModel = viewModel)}
                                composable("userAdd") { AddUserPage(
                                    viewModel = viewModel,
                                    navController = navController
                                ) }
                                composable("Onboardingpage") { OnBoardingPage(sharedPreference,navController,viewModel)}
                                /*...*/
                            }

                        }

                    }



                }
            }
        }
    }
}






