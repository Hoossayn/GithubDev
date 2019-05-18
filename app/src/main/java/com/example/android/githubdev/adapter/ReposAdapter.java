package com.example.android.githubdev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.githubdev.R;
import com.example.android.githubdev.model.Repos;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {

    List<Repos> repos;
    Context context;


    public ReposAdapter(List<Repos> repos, Context context) {
        this.repos = repos;
        this.context = context;
    }

    @NonNull
    @Override
    public ReposAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposAdapter.ViewHolder holder, int position) {

        holder.repositorName.setText(repos.get(position).getRepoName());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView repositorName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            repositorName = itemView.findViewById(R.id.repo_name);
        }
    }
}
