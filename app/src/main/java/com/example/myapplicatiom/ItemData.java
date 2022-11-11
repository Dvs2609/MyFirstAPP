package com.example.myapplicatiom;

public class ItemData {

    private String title;
    private int image;

    public ItemData(String title,int image){

        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
