package com.example.android.githubdev.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.githubdev.model.RowDev;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowDevAdapter extends RecyclerView.Adapter<RowDevAdapter.ViewHolder> {

    List<RowDev> rowDevs;
    Context context;

    public RowDevAdapter(List<RowDev> rowDevs, Context context) {
        this.rowDevs = rowDevs;
        this.context = context;
    }

    @NonNull
    @Override
    public RowDevAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RowDevAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
