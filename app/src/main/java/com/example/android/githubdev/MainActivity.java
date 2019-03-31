package com.example.android.githubdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.githubdev.adapter.RowDevAdapter;
import com.example.android.githubdev.api.Server;
import com.example.android.githubdev.api.Service;
import com.example.android.githubdev.model.RowDev;
import com.example.android.githubdev.model.listOfDevs;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView tv_disconnect;
    ProgressDialog progress_dialog;
    private RecyclerView recyclerView;
    private RowDev rowDev;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadData();
                Toast.makeText(MainActivity.this, "Github Users Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {

        progress_dialog = new ProgressDialog(this);
        progress_dialog.setMessage("Loading details...");
        progress_dialog.setCancelable(false);
        progress_dialog.show();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadData();
    }

    private void loadData() {

        tv_disconnect = (TextView) findViewById(R.id.disconnected);
        try{
            Server Client = new Server();
            Service apiService =
                    Server.getClient().create(Service.class);
            Call<listOfDevs> call = apiService.getItems();
            call.enqueue(new Callback<listOfDevs>() {
                @Override
                public void onResponse(Call<listOfDevs> call, Response<listOfDevs> response) {
                    List<RowDev> items = response.body().getRowDevs();
                    recyclerView.setAdapter(new RowDevAdapter(items, getApplicationContext()));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    progress_dialog.hide();
                }

                //handles error message
                @Override
                public void onFailure(Call<listOfDevs> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error in loading details", Toast.LENGTH_SHORT).show();
                    tv_disconnect.setVisibility(View.VISIBLE);
                    progress_dialog.hide();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
