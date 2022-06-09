package com.example.mypplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {
    Dialog myDialog;
    RecyclerView recyclerView;
    List<PostModel> postList;
    CustomAdapter customAdapter;
    PostManager postManager;
    Button button_profile;

    List<Post> pastsArray = new ArrayList<>();
    //private CustomAdapter.RecyclerViewClickListener Listener;

    private Button mAddBtn;



    private FirebaseDatabase mFirebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference firebaseRootRef=mFirebaseDatabase.getReference();
    private DatabaseReference eventsRef=firebaseRootRef.child("posts");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button_profile = findViewById(R.id.button33);
        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);
            }
        });

        myDialog = new Dialog(this);

        displayItems();

        mAddBtn = findViewById(R.id.add_pet_btn);
        mAddBtn.setOnClickListener(b -> {
            Intent postIntent = new Intent(this, AddPetActivity.class);
            startActivity(postIntent);
        });


    }

    //----------------------pop up-------------------------------
    public void ShowPopup(View v) {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.pop_up);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    //-------------------------------update likes---------------------------------
    public void updateLikes(View v) {
        Button like_button;
        like_button = findViewById(R.id.like_button);
        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //like_button.setText();
    }

    //------------------display items in RecyclerView-----------------------------
    private void displayItems() {
        //setOnClickListener();
        recyclerView = findViewById(R.id.recycler_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        postList = new ArrayList<PostModel>();
        //postList=postManager.allPosts();


        /*try {
            ConnectionDB.connect();
            String sql = "select * from posts ";
            ResultSet rs = ConnectionDB.select(sql);
            if (rs == null) {
                Toast.makeText(Home.this, "null", Toast.LENGTH_SHORT).show();
            }
            while (rs.next()) {
                PostModel post = new PostModel();
                post.setId(rs.getInt(1));
                post.setImgUser(rs.getString(2));
                post.setImgPet(rs.getString(3));
                post.setUserName(rs.getString(4));
                post.setDate(rs.getDate(5));
                post.setLikes(rs.getInt(6));
                post.setCommentaire(rs.getInt(7));
                post.setStatus(rs.getString(8));
                postList.add(post);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(Home.this, "inreach", Toast.LENGTH_SHORT).show();
        }*/

        /*postList.add(new PostModel("https://images.theconversation.com/files/443350/original/file-20220131-15-1ndq1m6.jpg?ixlib=rb-1.1.0&rect=0%2C0%2C3354%2C2464&q=45&auto=format&w=926&fit=clip","https://images.theconversation.com/files/443350/original/file-20220131-15-1ndq1m6.jpg?ixlib=rb-1.1.0&rect=0%2C0%2C3354%2C2464&q=45&auto=format&w=926&fit=clip","kawtar", new Date(),12, "12",  "adoption"));
        postList.add(new PostModel("https://images.theconversation.com/files/443350/original/file-20220131-15-1ndq1m6.jpg?ixlib=rb-1.1.0&rect=0%2C0%2C3354%2C2464&q=45&auto=format&w=926&fit=clip","https://images.theconversation.com/files/443350/original/file-20220131-15-1ndq1m6.jpg?ixlib=rb-1.1.0&rect=0%2C0%2C3354%2C2464&q=45&auto=format&w=926&fit=clip","fatima", new Date(),12, "12",  "take care"));
        postList.add(new PostModel("https://images.theconversation.com/files/443350/original/file-20220131-15-1ndq1m6.jpg?ixlib=rb-1.1.0&rect=0%2C0%2C3354%2C2464&q=45&auto=format&w=926&fit=clip","https://images.theconversation.com/files/443350/original/file-20220131-15-1ndq1m6.jpg?ixlib=rb-1.1.0&rect=0%2C0%2C3354%2C2464&q=45&auto=format&w=926&fit=clip","kawtar1", new Date(),12, "12",  "purchase"));
*/


        //customAdapter = new CustomAdapter(this);
        //recyclerView.setAdapter(customAdapter);
        postManager=new PostManager();
        loadData();
    }

    private void loadData(){
        postManager.get().addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                for (DataSnapshot ds : snapshot.getChildren())
                {

                    String userId = ds.child("userId").getValue(String.class);
                    String name = ds.child("name").getValue(String.class);
                    String imageUrl = ds.child("imageUrl").getValue(String.class);
                    String age = ds.child("age").getValue(String.class);
                    String gender = ds.child("gender").getValue(String.class);
                    String description = ds.child("description").getValue(String.class);

                    Post p=new Post( userId,  name,  description,  imageUrl,  age,  gender);

                    Log.d("tag", p.toString());
                    //Post emp = data.getValue(Post.class);
                    pastsArray.add(p);
                }
                //Toast.makeText(Home.this,pastsArray.toString(), Toast.LENGTH_SHORT).show();
                //customAdapter.setItems(pastsArray);


                customAdapter = new CustomAdapter(Home.this,pastsArray);
                recyclerView.setAdapter(customAdapter);

                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //eventsRef.addListenerForSingleValueEvent(valueEventListener);
    }


}