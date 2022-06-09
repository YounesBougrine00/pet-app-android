package com.example.mypplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private ArrayList<PostModelPr> list;
    //private RecyclerViewClickListener Listener;

    public Adapter(PostProfile profile, ArrayList<PostModelPr> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview =LayoutInflater.from(parent.getContext()).inflate(R.layout.single_itempost_profile,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(list.get(position).getImgUser()).into(holder.img1);
        Picasso.get().load(list.get(position).getImgUser()).into(holder.img2);
        //holder.img1.setImageBitmap(BitmapFactory.decodeFile(list.get(position).getImgUser()));
        holder.txt1.setText(list.get(position).getUserName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        holder.txt2.setText(dateFormat.format(list.get(position).getDate()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //public interface RecyclerViewClickListener{
    //    void onClick(View v,int position);
    //}


}
