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

import com.example.capstoneprojectpb.adapter.HotelAdapter;
import com.example.capstoneprojectpb.model.HotelModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<Hotel> mHotelsData;
    ArrayList<HotelModel> hotelModels = new ArrayList<>();
    private HotelAdapter hotelAdapter;
    private HotelModel hotelModel;

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
        hotelAdapter = new HotelAdapter();
        mRecyclerView = findViewById(R.id.recyclerView);

        getUser();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager); //vertikal

        //menggabungkan recycleview dgn adapter
        mRecyclerView.setAdapter(hotelAdapter);
        //onClick
        hotelAdapter.onClick(hotel -> {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            intent.putExtra(ThirdActivity.STRING_HOTEL, hotel);
            startActivity(intent);
        });
    }

    private void getUser() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://dev.farizdotid.com/api/purwakarta/hotel";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respone = new String(responseBody);
                try {
                    parseJson(respone);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseJson(String respone) throws JSONException {
        JSONObject jsonObject = new JSONObject(respone);
        JSONArray dataArray =jsonObject.getJSONArray("hotel");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject dataObject =dataArray.getJSONObject(i);
            String nama_hotel =dataObject.getString("nama");
            String alamat = dataObject.getString("alamat");
            String no_telp =dataObject.getString("nomor_telp");
            String gambar_url = dataObject.getString("gambar_url");

            hotelModel = new HotelModel(nama_hotel,alamat,no_telp,gambar_url);
            hotelModels.add(hotelModel);
        }

        hotelAdapter.setData(hotelModels);



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
                        Toast.makeText(MainActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_message:
                        Toast.makeText(MainActivity.this, "Message is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.synch:
                        Toast.makeText(MainActivity.this, "Synch is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.trash:
                        Toast.makeText(MainActivity.this, "Trash is Clicked",Toast.LENGTH_SHORT).show();break;
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
}