package com.example.mypplication;

import java.util.Date;

public class PostModelPr {
    String imgUser;
    String imgPet;
    String userName;
    Date date;
    int likes;
    String commentaire;
    String status;

    public PostModelPr() {
    }

    public PostModelPr(String imgUser, String imgPet, String userName, Date date, int likes, String commentaire, String status) {
this.imgUser=imgUser;
        this.imgPet=imgPet;
        this.userName = userName;
        this.date = date;
        this.likes = likes;
        this.commentaire = commentaire;
        this.status = status;
    }


    public void setCommentaire(String commentaire) {
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

    public String getCommentaire() {
        return commentaire;
    }

    public String getImgPet() {
        return imgPet;
    }

    public String getStatus() {
        return status;
    }

}
