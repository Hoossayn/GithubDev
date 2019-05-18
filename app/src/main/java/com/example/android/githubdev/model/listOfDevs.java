package com.example.android.githubdev.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class listOfDevs {

    @SerializedName("row_dev")
    private List<RowDev> rowDevs;

    public List<RowDev> getRowDevs() {
        return rowDevs;
    }

    public void setRowDevs(List<RowDev> rowDevs) {
        this.rowDevs = rowDevs;
    }
}
