package com.example.messenger

class MessageModel(message:String, time :String, date:String, status:Boolean, who:String) {


    var message:String;
    var time:String;
    var  date:String;
    var status: Boolean;
    var who:String;
    init {


        this.message=message;
        this.time=time;
        this.date=date;
        this.status=status;
        this.who=who;

    }
}