package com.example.capstoneprojectpb;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstoneprojectpb.adapter.RecyclerMoviesAdapter;
import com.example.capstoneprojectpb.model.Response;
import com.example.capstoneprojectpb.model.Result;
import com.example.capstoneprojectpb.rest.ApiClient;
import com.example.capstoneprojectpb.rest.ApiInterface;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity {

    private RecyclerMoviesAdapter adapter;


    String API_KEY = "dd7c30cbeb58edd0347801dfc5453354";
    String LANGUAGE = "en-US";
    String CATEGORY = "popular";
    int PAGE = 1;

    RecyclerView recyclerview;

    DBHelper dbHelper;

    CardView cardView;
    TextView textView, textView2,textView3;
    SearchView searchView;
    Animation anim_from_button, anim_from_top, anim_from_left;
    @TargetApi(Build.VERSION_CODES.KITKAT)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardView = findViewById(R.id.cardView);
        textView = findViewById(R.id.firstText);
        textView2 = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView2);
        searchView = findViewById(R.id.searchView);

        recyclerview = findViewById(R.id.recyclerView);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 1){
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<Response> call = apiInterface.getQuery(API_KEY,LANGUAGE,newText,PAGE);
                    call.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            List<Result> mList = response.body().getResults();
                            adapter = new RecyclerMoviesAdapter(MainActivity.this, mList);
                            recyclerview.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            t.fillInStackTrace();
                        }
                    });
                }
                return true;
            }
        });
        panggilRetrofit();


        //Load Animations
        anim_from_button = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);
        anim_from_top = AnimationUtils.loadAnimation(this, R.anim.anim_from_top);
        anim_from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);
        //Set Animations

        textView.setAnimation(anim_from_top);
        textView3.setAnimation(anim_from_top);
        searchView.setAnimation(anim_from_left);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "You are Here", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_message:
                        Toast.makeText(MainActivity.this, "Support is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_login:
                        Intent logActivity1 = new Intent(MainActivity.this, Login.class);
                        startActivity(logActivity1);break;
                    case R.id.nav_about:
                        Intent logActivity2 = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(logActivity2);break;
                    case R.id.nav_version:
                        Intent logActivity3 = new Intent(MainActivity.this, AppVersionActivity.class);
                        startActivity(logActivity3);break;
                    default:
                        return true;
                }
                return true;
            }
        });

        //cardView.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Intent secondActivity = new Intent(MainActivity.this, SecondActivity.class);
        //        startActivity(secondActivity);
        //    }
        //});

        //Hide status bar and navigation bar at the bottom
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
    private void panggilRetrofit() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Response> call = apiInterface.getMovie(CATEGORY, API_KEY, LANGUAGE, PAGE);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                List<Result> mList = response.body().getResults();
                adapter = new RecyclerMoviesAdapter(MainActivity.this, mList);
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.fillInStackTrace();
            }
        });
    }
}