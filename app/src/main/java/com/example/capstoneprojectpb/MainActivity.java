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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    CardView cardView, cardView2, cardView3;
    TextView textView, textView2,textView3, textView4, textView5;
    SearchView searchView;
    Animation anim_from_button, anim_from_top, anim_from_left;
    @TargetApi(Build.VERSION_CODES.KITKAT)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardView = findViewById(R.id.cardView);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);
        textView = findViewById(R.id.firstText);
        textView2 = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView3);
        textView5 = findViewById(R.id.textView4);
        searchView = findViewById(R.id.searchView);

        //Load Animations
        anim_from_button = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);
        anim_from_top = AnimationUtils.loadAnimation(this, R.anim.anim_from_top);
        anim_from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);
        //Set Animations
        cardView.setAnimation(anim_from_button);
        cardView2.setAnimation(anim_from_button);
        cardView3.setAnimation(anim_from_button);
        textView.setAnimation(anim_from_top);
        textView2.setAnimation(anim_from_top);
        textView3.setAnimation(anim_from_top);
        textView4.setAnimation(anim_from_top);
        textView5.setAnimation(anim_from_top);
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
                                Intent sActivity = new Intent(MainActivity.this, Login.class);
                                startActivity(sActivity);break;
                    case R.id.nav_share:
                        Toast.makeText(MainActivity.this, "Share is clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_rate:
                        Toast.makeText(MainActivity.this, "Rate us is Clicked",Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;

                }
                return true;
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondActivity = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(secondActivity);
            }
        });
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
}