package com.example.capstoneprojectpb;

import static android.text.Html.fromHtml;

import android.content.Intent;
import android.net.Uri;
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

public class AppVersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_version);

        TextView Contact = (TextView)findViewById(R.id.Contact);
        Contact.setText(fromHtml("</font><font color='#3b5998'>CONTACT US</font>"));

        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("message/cfc822");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"rizkyadeningsih18@gmail.com"});

                startActivity(Intent.createChooser(email, "Send Mail"));
            }
        });

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
                        Intent logActivity4 = new Intent(AppVersionActivity.this, MainActivity.class);
                        startActivity(logActivity4);break;
                    case R.id.nav_message:
                        Toast.makeText(AppVersionActivity.this, "Message is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.synch:
                        Toast.makeText(AppVersionActivity.this, "Synch is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.trash:
                        Toast.makeText(AppVersionActivity.this, "Trash is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.settings:
                        Toast.makeText(AppVersionActivity.this, "Settings is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_login:
                        Intent logActivity1 = new Intent(AppVersionActivity.this, Login.class);
                        startActivity(logActivity1);break;
                    case R.id.nav_about:
                        Intent logActivity2 = new Intent(AppVersionActivity.this, AboutActivity.class);
                        startActivity(logActivity2);break;
                    case R.id.nav_version:
                        Intent logActivity3 = new Intent(AppVersionActivity.this, AppVersionActivity.class);
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