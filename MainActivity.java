package com.example.myapplication;

import android.os.Bundle;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    ImageView image;
    ListView listView;
    messege msg;
    ArrayList<String> msglist;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msg=new messege();
        listView=(ListView)findViewById(R.id.listview);
        msglist=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.list_layout,R.id.textView2,msglist);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference mref=firebaseDatabase.getReference("chat");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                msglist.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    msg=ds.getValue(messege.class);
                    msglist.add(msg.getName()+"\n"+msg.getMessage());
                }listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Firebase.setAndroidContext(this);
        final Firebase ref=new Firebase("https://btech-2020-19e8b.firebaseio.com/");
        editText=(EditText)findViewById(R.id.message);
        image=(ImageView)findViewById(R.id.send);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messege messege=new messege("Aryan",editText.getText().toString());
                ref.push().setValue(messege);
                editText.setText(" ");
            }
        });

    }
}
