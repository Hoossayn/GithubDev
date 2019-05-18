package com.example.android.githubdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.githubdev.adapter.ReposAdapter;
import com.example.android.githubdev.adapter.RowDevAdapter;
import com.example.android.githubdev.api.Server;
import com.example.android.githubdev.api.Service;
import com.example.android.githubdev.model.ListOfRepos;
import com.example.android.githubdev.model.Repos;
import com.example.android.githubdev.model.RowDev;
import com.example.android.githubdev.model.listOfDevs;

import java.util.List;

public class MoreInfo extends AppCompatActivity {

    CircleImageView imageView;
    TextView reposName;
    ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);


        initViews();
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadRepos();
                Toast.makeText(MoreInfo.this, "Github Users Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
        String profilePicUrl = getIntent().getStringExtra("avatar_url");

        Glide.with(this)
                .load(profilePicUrl)
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(imageView);
    }

    private void initViews() {
        imageView = (CircleImageView)findViewById(R.id.profilePic);
        reposName = (TextView)findViewById(R.id.repo_name);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading details...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadRepos();
    }

    private void loadRepos() {
        try{
            Server Client = new Server();
            Service apiService =
                    Server.getClient().create(Service.class);
            Call<ListOfRepos> call = apiService.getRepos();
            call.enqueue(new Callback<ListOfRepos>() {
                @Override
                public void onResponse(Call<ListOfRepos> call, Response<ListOfRepos> response) {
                    List<Repos> items = response.body().getList_of_repos();
                    recyclerView.setAdapter(new ReposAdapter(items, getApplicationContext()));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    progressDialog.hide();
                }

                //handles error message
                @Override
                public void onFailure(Call<ListOfRepos> call, Throwable t) {
                    Toast.makeText(MoreInfo.this, "Error in loading details", Toast.LENGTH_SHORT).show();
                    //tv_disconnect.setVisibility(View.VISIBLE);
                    progressDialog.hide();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
