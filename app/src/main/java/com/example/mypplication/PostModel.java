package com.example.mypplication;

import java.util.Date;

public class PostModel {
    int id;
    String imgUser;
    String imgPet;
    String userName;
    Date date;
    int likes;
    int commentaire;
    String status;

    public PostModel() {
    }

    public PostModel(String imgUser, String imgPet, String userName, Date date, int likes, int commentaire, String status) {
        this.imgUser = imgUser;
        this.imgPet = imgPet;
        this.userName = userName;
        this.date = date;
        this.likes = likes;
        this.commentaire = commentaire;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommentaire(int commentaire) {
        this.commentaire = commentaire;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setImgPet(String imgPet) {
        this.imgPet = imgPet;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgUser() {
        return imgUser;
    }

    public String getUserName() {
        return userName;
    }

    public Date getDate() {
        return date;
    }

    public int getLikes() {
        return likes;
    }

    public int getCommentaire() {
        return commentaire;
    }

    public String getImgPet() {
        return imgPet;
    }

    public String getStatus() {
        return status;
    }

}
