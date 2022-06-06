package com.example.mypplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder{
    public ImageView img1,img2;
public TextView txt1,txt2;
public Button btn1,btn2,btn3;
    //private CustomAdapter.RecyclerViewClickListener Listener;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        img1=itemView.findViewById(R.id.imageView);
        img2=itemView.findViewById(R.id.imageView2);

        txt1=itemView.findViewById(R.id.textView4);
        txt2=itemView.findViewById(R.id.textView5);

        btn1=itemView.findViewById(R.id.like_button);
        btn2=itemView.findViewById(R.id.button7);
        btn3=itemView.findViewById(R.id.button9);
        //itemView.setOnClickListener(this);
    }


}
