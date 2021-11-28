package com.example.capstoneprojectpb;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.capstoneprojectpb.adapter.HotelAdapter;
import com.example.capstoneprojectpb.model.HotelModel;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    ImageView down_arrow;
    public static final String STRING_HOTEL = "string_hotel";
    ScrollView third_scrollview;
    Animation from_bottom;
    private RecyclerView mRecyclerView;
    private ArrayList<Hotel> mHotelsData;
    ArrayList<HotelModel> hotelModels = new ArrayList<>();
    private HotelAdapter hotelAdapter;
    private HotelModel hotelModel;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        down_arrow = findViewById(R.id.down_arrow);
        third_scrollview = findViewById(R.id.third_scrillview);
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);
        down_arrow.setAnimation(from_bottom);
        third_scrollview.setAnimation(from_bottom);
        TextView hotelsTitle = findViewById(R.id.third_title);
        TextView hotelSubTittle = findViewById(R.id.venue_type_text);
        TextView noTelp = findViewById(R.id.type_of_view_text);
        ImageView hotelsImage = findViewById(R.id.header_background);
//        Glide.with(this).load(getIntent().getIntExtra("image_resource",0)).into(hotelsImage);

        HotelModel HotelModel = null;
        if (getIntent() != null) {
            HotelModel = getIntent().getParcelableExtra(STRING_HOTEL);
        }

        if (HotelModel != null) {
            hotelsTitle.setText(HotelModel.getNama());
            hotelSubTittle.setText(HotelModel.getAlamat());
            noTelp.setText(HotelModel.getNo_telp());
            Glide.with(this).load(HotelModel.getGambar_url()).into(hotelsImage);

        }
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
        down_arrow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(down_arrow, "background_image_transition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ThirdActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }


}