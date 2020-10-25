package com.example.myapplication;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class messege {
   
    String name;
    String message;
    String id;
    public messege(){
    }
    public  messege(String name,String message){
        this.name=name;
        this.message=message;
        this.id=id;
    }
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getMessage(){return message;}
    public void setMessage(String message){this.message=message;}
    public String getId(){return id;}
    public void setId(String id){
        this.id=id;
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");





}
