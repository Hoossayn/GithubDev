package com.example.android.githubdev.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.githubdev.MoreInfo;
import com.example.android.githubdev.R;
import com.example.android.githubdev.model.RowDev;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RowDevAdapter extends RecyclerView.Adapter<RowDevAdapter.ViewHolder> {

    private List<RowDev> rowDevs;
    private Context context;


    public RowDevAdapter(List<RowDev> rowDevsList, Context context) {
        this.rowDevs = rowDevsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RowDevAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowDevAdapter.ViewHolder holder, int position) {
        holder.userName.setText(rowDevs.get(position).getLogin());
        holder.githublink1.setText(rowDevs.get(position).getUrl());
        Picasso.with(context)
                .load(rowDevs.get(position).getProfilePicUrl())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return rowDevs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userName, githublink1;
        private CircleImageView imageView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            rowDevs = new ArrayList<>();
            userName = itemView.findViewById(R.id.title);
            githublink1 = itemView.findViewById(R.id.githublink1);
            imageView = itemView.findViewById(R.id.cover);

            //on click of cardview to display developer homepage
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        RowDev clickedDataItem = rowDevs.get(pos);
                        Intent intent = new Intent(context, MoreInfo.class);
                        intent.putExtra("login", rowDevs.get(pos).getLogin());
                        intent.putExtra("html_url", rowDevs.get(pos).getUrl());
                        intent.putExtra("avatar_url", rowDevs.get(pos).getProfilePicUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "Opening >>> " + clickedDataItem.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }
}
