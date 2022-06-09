package com.example.mypplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<PostModelPr> postList;
    Adapter customAdapter;
    Dialog addDialog;
    private CircleImageView profileImageView;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
     Button button;
     ImageView back_button,btn1edit;
     TextView txtName;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("User");
        addDialog = new Dialog(this);
        profileImageView= findViewById(R.id.dp);






        button = findViewById(R.id.button99);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, PostProfile.class);
                startActivity(intent);
            }
        });



        //-----------------get widget-----------------------
        back_button=findViewById(R.id.back_button9);

        //--------------------back button--------------------------
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    //----------------------edit_profile-------------------------------
    public void ShowPopupBtn(View v) {
        TextView close;
        ImageView btnEdit;
        addDialog.setContentView(R.layout.edit_profile1);
        close =(TextView) addDialog.findViewById(R.id.close);
        close.setText("X");
        btnEdit = (ImageView) addDialog.findViewById(R.id.btn_edit);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog.dismiss();
            }
        });
        addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addDialog.show();
    }
    ////userinfo







}