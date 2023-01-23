package com.example.messenger

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class viewModel :ViewModel() {


    var myNumber= mutableStateOf("");

    fun changeMyNumber(number: String)
    {
        myNumber= mutableStateOf(number)
    }

    //For showing all the   chatUserinPage
    var Chatlist= mutableStateListOf<UserModel>();
    fun addtoChatList(item:UserModel)
    {
        Chatlist.add(item)
    }
    fun clearChatList()
    {
        Chatlist.clear()
    }


    //For  showing users which are oly needed
    var userList= mutableStateListOf<UserModel>()

    fun addtoUserList(item:UserModel)
    {
        userList.add(item)
    }
    fun clearUserList()
    {
        userList.clear()
    }




    // for indentifying cureent chatmodel to get model of user clicked in usercard
    var presentChatMOdel= mutableStateOf(UserModel("","","",""));

    fun changePresentChatModel(model:UserModel)
    {
        presentChatMOdel= mutableStateOf(model)
    }


    // list of all messages in the chat
    var currentMessageList= mutableStateListOf<MessageModel>()
    fun addtoMessagetList(item:MessageModel)
    {
        currentMessageList.add(item)
    }
    fun clearMessageList()
    {
        currentMessageList.clear()
    }

}