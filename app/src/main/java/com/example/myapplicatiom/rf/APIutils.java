package com.example.myapplicatiom.rf;

public class APIutils {

    public static APIservice getSOService() {
        return RetrofitClass.getClient().create(APIservice.class);
    }
}
