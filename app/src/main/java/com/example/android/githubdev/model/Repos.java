package com.example.android.githubdev.model;

import com.google.gson.annotations.SerializedName;

public class Repos {

    @SerializedName("name_of_Repo")
    String repoName;

    public Repos(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }
}
