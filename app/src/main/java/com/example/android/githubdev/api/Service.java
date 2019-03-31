package com.example.android.githubdev.api;

import com.example.android.githubdev.model.listOfDevs;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("/search/users?q=+location:minna&page=")
    Call<listOfDevs> getItems();
}
