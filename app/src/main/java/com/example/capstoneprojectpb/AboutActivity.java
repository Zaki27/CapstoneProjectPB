package com.example.capstoneprojectpb;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

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
                        Intent logActivity4 = new Intent(AboutActivity.this, MainActivity.class);
                        startActivity(logActivity4);break;
                    case R.id.nav_message:
                        Toast.makeText(AboutActivity.this, "Message is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.synch:
                        Toast.makeText(AboutActivity.this, "Synch is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.trash:
                        Toast.makeText(AboutActivity.this, "Trash is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.settings:
                        Toast.makeText(AboutActivity.this, "Settings is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_login:
                        Intent logActivity1 = new Intent(AboutActivity.this, Login.class);
                        startActivity(logActivity1);break;
                    case R.id.nav_about:
                        Intent logActivity2 = new Intent(AboutActivity.this, AboutActivity.class);
                        startActivity(logActivity2);break;
                    case R.id.nav_version:
                        Intent logActivity3 = new Intent(AboutActivity.this, AppVersionActivity.class);
                        startActivity(logActivity3);break;
                    default:
                        return true;
                }
                return true;
            }
        });
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