package com.example.myapplicatiom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListCharacters {

    //@SerializedName("listcharacters");
    @Expose
    private List<Character> listcharacter;

    public List<Character> getListcharacter(){
        return listcharacter;
    }
    public void setListcharacter(List<Character> listcharacter){
        this.listcharacter = listcharacter;
    }
}
