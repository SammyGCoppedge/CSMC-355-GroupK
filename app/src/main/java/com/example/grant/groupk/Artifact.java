package com.example.grant.groupk;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Grant on 4/24/2018.
 */

public class Artifact {

    private String name;
    private String userPoster;
    private String postDate;
    private String location;
    private String descriptionShort;
    private String descriptionLong;
    private String image;
    private ArrayList<String> comments;

    public Artifact(String name, String user, String date, String location, String dShort, String dLong, String image, ArrayList<String> comments) {
        this.name = name;
        this.userPoster = user;
        this.postDate = date;
        this.location = location;
        this.descriptionShort = dShort;
        this.descriptionLong = dLong;
        this.image = image;
        this.comments = comments;
    }

    public Artifact(String name, String user, String date, String location, String dShort, String dLong, String image) {
        this.name = name;
        this.userPoster = user;
        this.postDate = date;
        this.location = location;
        this.descriptionShort = dShort;
        this.descriptionLong = dLong;
        this.image = image;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setUser(String user)
    {
        this.userPoster = user;
    }

    public String getUser()
    {
        return this.userPoster;
    }

    public void setDate(String date)
    {
        this.postDate = date;
    }

    public String getDate()
    {
        return this.postDate;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setDescriptionShort(String des)
    {
        this.descriptionShort = des;
    }

    public String getDescriptionShort()
    {
        return this.descriptionShort;
    }

    public void setDescriptionLong(String des)
    {
        this.descriptionLong = des;
    }

    public String getDescriptionLong()
    {
        return this.descriptionLong;
    }

    public void setImages(String image)
    {
        this.image = image;
    }

    public String getImages()
    {
        return this.image;
    }

    public void setComments(ArrayList<String> comments)
    {
        this.comments = comments;
    }

    public void addComment(String comment)
    {
        this.comments.add(comment);
    }

    public void removeComment(String comment)
    {
        this.comments.remove(comment);
    }

    public ArrayList<String> getComments()
    {
        return this.comments;
    }

}
