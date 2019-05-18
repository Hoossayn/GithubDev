package com.example.android.githubdev.api;

import com.example.android.githubdev.model.ListOfRepos;
import com.example.android.githubdev.model.listOfDevs;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("/search/users?q=+location:minna&page=")
    Call<listOfDevs> getItems();


    //TODO add api to get developers Repository here
    //  "repos_url": "https://api.github.com/users/octocat/repos"
    @GET("/users/hoossayn/repos")
    Call<ListOfRepos> getRepos();
}
