package com.example.mypplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

public class PetDetails extends AppCompatActivity {
    Dialog myDialog;
    public final static int MY_PERMISSIONS_REQUEST_CALL_PHONE=0;
    Button back_button;
    Button pet_details_call;
    Button pet_details_call1;
    TextView textView1;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    //VideoView video_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);
        //myDialog = new Dialog(this);



        //-----------------get widget-----------------------
        back_button=findViewById(R.id.back_button);
        pet_details_call=findViewById(R.id.pet_details_call);
        pet_details_call1=findViewById(R.id.pet_details_call1);
        textView1=findViewById(R.id.pet_details_name);
        imageView1=findViewById(R.id.pet_details_image1);
        imageView2=findViewById(R.id.pet_details_image2);
        imageView3=findViewById(R.id.pet_details_user);
        //video_view=findViewById(R.id.video_view);

        //String videoPath = "https://www.youtube.com/watch?v=8Lq3HyBCuAA&ab_channel=CodinginFlow";
        //Uri uri = Uri.parse(videoPath);
        //video_view.setVideoURI(uri);

        //MediaController mediaController = new MediaController(this);
        //video_view.setMediaController(mediaController);
        //mediaController.setAnchorView(video_view);
        //video_view.requestFocus();
        //video_view.start();

        //------------phone call---------------------
        pet_details_call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0623546765"));
                if (ActivityCompat.checkSelfPermission( PetDetails.this, Manifest.permission.CALL_PHONE)
                        != PackageManager. PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PetDetails.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                }else startActivity(callIntent);
            }
        });

        pet_details_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0623546765"));
                if (ActivityCompat.checkSelfPermission( PetDetails.this, Manifest.permission.CALL_PHONE)
                        != PackageManager. PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PetDetails.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                }else startActivity(callIntent);
            }
        });

        //--------------------back button--------------------------
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //--------------------get DATA----------------------------------------
        textView1.setText(getIntent().getStringExtra("name"));
        Picasso.get().load(getIntent().getStringExtra("img2")).into(imageView1);
        Picasso.get().load(getIntent().getStringExtra("img2")).into(imageView2);
        Picasso.get().load(getIntent().getStringExtra("img2")).into(imageView3);

    }

    //----------------------pop up-------------------------------


    //--------------------------check phone call permission------------------------------------
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE &&
                grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
        } else {
            return;
        }
    }
}