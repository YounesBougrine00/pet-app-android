package com.example.mypplication;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class PostProfile extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<PostModelPr> postList;
    Adapter customAdapter;
    ImageView back_button;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petpost);
        displayItems();

        //-----------------get widget-----------------------
        back_button=findViewById(R.id.back_button8);

        //--------------------back button--------------------------
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




    }
    private void displayItems(){
        //setOnClickListener();
        recyclerView=findViewById(R.id.recycler_Post);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        postList=new ArrayList<>();
        postList.add(new PostModelPr("https://i.ibb.co/gZ1jwhL/cover.jpg","https://firebasestorage.googleapis.com/v0/b/pet-app-45b4f.appspot.com/o/pet_images%2F1c3a41fc-50bf-4a88-801b-e228ef4151d6?alt=media&token=254e13bf-d482-43d0-9e33-a487c63ea65a","fatima", new Date(),13, "12",  "adoption"));
        postList.add(new PostModelPr("https://i.ibb.co/gZ1jwhL/cover.jpg","https://firebasestorage.googleapis.com/v0/b/pet-app-45b4f.appspot.com/o/pet_images%2F6ef31a63-48b0-423f-af44-b8580030d9f9?alt=media&token=24035056-25dc-47ce-9002-c67f224a0db5","fatima", new Date(),12, "12",  "take care"));
        postList.add(new PostModelPr("https://i.ibb.co/gZ1jwhL/cover.jpg","https://images.theconversation.com/files/443350/original/file-20220131-15-1ndq1m6.jpg?ixlib=rb-1.1.0&rect=0%2C0%2C3354%2C2464&q=45&auto=format&w=926&fit=clip","fatima", new Date(),15, "12",  "purchase"));

        customAdapter=new Adapter(PostProfile.this,postList);
        recyclerView.setAdapter(customAdapter);
    }
}