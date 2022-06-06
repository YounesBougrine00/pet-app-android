package com.example.mypplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<PostModel> list;
    Boolean like_click_one=true;
    //private RecyclerViewClickListener Listener;

    public CustomAdapter(Context context, List<PostModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.single_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {


        Picasso.get().load(list.get(position).getImgUser()).into(holder.img1);
        Picasso.get().load(list.get(position).getImgUser()).into(holder.img2);
        //holder.img1.setImageBitmap(BitmapFactory.decodeFile(list.get(position).getImgUser()));
        holder.txt1.setText(list.get(position).getUserName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        holder.txt2.setText(dateFormat.format(list.get(position).getDate()));
        holder.btn1.setText(String.valueOf(list.get(position).getLikes()));
        holder.btn2.setText(String.valueOf(list.get(position).getCommentaire()));
        holder.btn3.setText(list.get(position).getStatus());

        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PetDetails.class);
                intent.putExtra("name",list.get(position).getStatus());
                intent.putExtra("img2",list.get(position).getImgUser());
                context.startActivity(intent);
            }
        });

        holder.btn1.setOnClickListener(new View.OnClickListener() {
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
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //public interface RecyclerViewClickListener{
    //    void onClick(View v,int position);
    //}
}
