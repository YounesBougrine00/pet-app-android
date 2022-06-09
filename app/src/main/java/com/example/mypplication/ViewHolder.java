package com.example.mypplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{
    public ImageView img1,img2;
public TextView txt1,txt2;
public Button btn1,btn2,btn3;
    //private CustomAdapter.RecyclerViewClickListener Listener;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        img1=itemView.findViewById(R.id.imageViewpr);
        img2=itemView.findViewById(R.id.imageView2pr);

        txt1=itemView.findViewById(R.id.textView4pr);
        txt2=itemView.findViewById(R.id.textView5pr);

        btn1=itemView.findViewById(R.id.button6pr);
        btn2=itemView.findViewById(R.id.button7pr);
        btn3=itemView.findViewById(R.id.button9pr);
        //itemView.setOnClickListener(this);
    }


}
