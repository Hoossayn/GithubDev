package com.example.android.githubdev.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfRepos {

    @SerializedName("list_of_repos")
    List<Repos> list_of_repos;

    public List<Repos> getList_of_repos() {
        return list_of_repos;
    }

    public void setList_of_repos(List<Repos> list_of_repos) {
        this.list_of_repos = list_of_repos;
    }
}
