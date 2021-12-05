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
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import com.example.capstoneprojectpb.model.Result;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "string_extra" ;
    ImageView second_back_arrow, imgFilmDetail;
    TextView second_title, second_subtitle, second_rating_number;
    RatingBar second_ratingbar;
    Animation from_left, from_right, from_bottom;
    Result result;
    double nilai, populer;
    Boolean adult;
    String imageDetail, imageBackgroundDetail, rilisFilm, title, story;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        second_back_arrow = findViewById(R.id.second_back_arrow);
        second_title = findViewById(R.id.second_title);
        second_subtitle = findViewById(R.id.second_subtitle);
        second_rating_number = findViewById(R.id.second_rating_number);

        imgFilmDetail = findViewById(R.id.imgFilm);
        second_ratingbar = findViewById(R.id.second_ratingbar);

        result = getIntent().getParcelableExtra(EXTRA_MOVIE);

        //ambil data dari API
        nilai = result.getVoteAverage();
        title = result.getOriginalTitle();
        imageDetail = result.getPosterPath();
        imageBackgroundDetail = result.getPosterPath();

        story = result.getOverview();

        //masukkan data api di aplikasi
        second_rating_number.setText(nilai + "/10");
        second_title.setText(title);
        second_subtitle.setText(story);


        //rating film dalam bentuk bintang
        float newValue = (float)nilai;
        second_ratingbar.setNumStars(5);
        second_ratingbar.setStepSize((float) 0.5);
        second_ratingbar.setRating(newValue / 2);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w780/" + imageDetail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgFilmDetail);
        second_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
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
        //Load Animations
        from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);
        from_right = AnimationUtils.loadAnimation(this, R.anim.anim_from_right);
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);
        //Set Animations
        second_back_arrow.setAnimation(from_left);
        second_title.setAnimation(from_right);
        second_subtitle.setAnimation(from_right);
        second_ratingbar.setAnimation(from_left);
        second_rating_number.setAnimation(from_right);

    }
}