package com.example.mypplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<Post> list;
    Boolean like_click_one=true;
    //private RecyclerViewClickListener Listener;

    public CustomAdapter(Context context, List<Post> list) {
        this.context = context;
        this.list = list;
    }

    public void setItems(List<Post> post)
    {
        list.addAll(post);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.single_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {




        Picasso.get().load(list.get(position).getImageUrl()).into(holder.img1);
        Picasso.get().load(list.get(position).getImageUrl()).into(holder.img2);
        //holder.img1.setImageBitmap(BitmapFactory.decodeFile(list.get(position).getImgUser()));
        ////holder.txt1.setText(list.get(position).getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        //holder.txt2.setText(dateFormat.format(list.get(position).getDate()));
        holder.txt2.setText(dateFormat.format( new Date()));
        //holder.btn1.setText(String.valueOf(list.get(position).getLikes()));
        //holder.btn2.setText(String.valueOf(list.get(position).getCommentaire()));
        //holder.btn3.setText(list.get(position).getStatus());
        holder.btn1.setText(String.valueOf(12));
        holder.btn2.setText(String.valueOf(14));
        holder.btn3.setText("Adoption");

        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PetDetails.class);
                //intent.putExtra("name",list.get(position).getStatus());
                //intent.putExtra("img2",list.get(position).getImgUser());
                intent.putExtra("name","Adoption");
                intent.putExtra("img2",list.get(position).getImageUrl());
                context.startActivity(intent);
            }
        });

        /*holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(like_click_one){
                    like_click_one=false;
                    int nb = 0;

                    try {
                        ConnectionDB.connect();
                        nb = ConnectionDB.edite("update posts set likes="+(list.get(position).getLikes()+1)
                                +" where id="+list.get(position).getId());
                        ConnectionDB.disconnect();
                    } catch (ClassNotFoundException | SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if(nb!=0){holder.btn1.setText(String.valueOf(list.get(position).getLikes()+1));}
                }else {
                    like_click_one=true;
                    int nb = 0;

                    try {
                        ConnectionDB.connect();
                        nb = ConnectionDB.edite("update posts set likes="+(list.get(position).getLikes())
                                +" where id="+list.get(position).getId());
                        ConnectionDB.disconnect();
                    } catch (ClassNotFoundException | SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if(nb!=0){holder.btn1.setText(String.valueOf(list.get(position).getLikes()));}
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //public interface RecyclerViewClickListener{
    //    void onClick(View v,int position);
    //}
}