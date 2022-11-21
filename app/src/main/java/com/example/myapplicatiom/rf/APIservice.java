package com.example.myapplicatiom.rf;

import com.example.myapplicatiom.Character;
import com.example.myapplicatiom.ListCharacters;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIservice {

    @GET("characters?limit=5")
    Call<List<Character>> getCharacterLimit5();

    @GET("characters?limit=10")
    Call<List<Character>> getCharacterLimit10();

}
