package com.example.mypplication;

import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostManager {

    public List<PostModel> allPosts() {
        List<PostModel> posts = new ArrayList<PostModel>();
        try {
            ConnectionDB.connect();
            String sql = "select * from posts ";
            ResultSet rs = ConnectionDB.select(sql);
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
                posts.add(post);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return posts;

    }


}
