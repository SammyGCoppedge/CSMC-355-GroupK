package com.example.grant.groupk;

import android.net.Uri;

public class Post {
    private String title;
    private String description;
    private String imageU;
    private String type;
    private String author;
    private String key;
    public Post(){

    }
    public Post(String title, String description, String imageU,String type, String author ){
        this.title = title;
        this.description = description;
        this.imageU = imageU;
        this.author = author;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public String getImageU() {
        return imageU;
    }

    public void setImageU(String imageU) {
        this.imageU = imageU;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
