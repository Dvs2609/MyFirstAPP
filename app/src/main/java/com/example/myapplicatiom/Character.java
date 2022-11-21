package com.example.myapplicatiom;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Character {


    //@SerializedName("name")
    //@Expose
    private String name;
    //@SerializedName("birthday")
    //@Expose
    private String birthday;

    //@SerializedName("img")
    //@Expose
    private String img;

    //@SerializedName("nickname")
    //@Expose
    private String nickname;

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getImg() {
        return img;
    }

    public String getNickname() {
        return nickname;
    }
}