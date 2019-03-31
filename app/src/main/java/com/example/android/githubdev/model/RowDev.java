package com.example.android.githubdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RowDev {

    private String login;
    private String profilePicUrl;
    private String url;

    public RowDev(String login, String avatarUrl, String htmlUrl){
        this.login = login;
        this.profilePicUrl = avatarUrl;
        this.url = htmlUrl;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getProfilePicUrl(){
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl){
        this.profilePicUrl = profilePicUrl;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }
}
