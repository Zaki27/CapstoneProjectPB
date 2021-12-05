package com.example.capstoneprojectpb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.capstoneprojectpb.R;
import com.example.capstoneprojectpb.SecondActivity;
import com.example.capstoneprojectpb.model.Result;

import java.util.List;

public class RecyclerMoviesAdapter extends RecyclerView.Adapter<RecyclerMoviesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Result> resultList;
    private double Rating,nilai;

    public RecyclerMoviesAdapter(Context mContext, List<Result> resultList) {
        this.mContext = mContext;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.list_item, parent, false);

        MyViewHolder viewHolder = new  MyViewHolder(view);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), SecondActivity.class);
                Result result = new Result();
                result.setOriginalTitle(resultList.get(viewHolder.getAdapterPosition()).getOriginalTitle());
                result.setOverview(resultList.get(viewHolder.getAdapterPosition()).getOverview());
                result.setVoteAverage(resultList.get(viewHolder.getAdapterPosition()).getVoteAverage());
                result.setPosterPath(resultList.get(viewHolder.getAdapterPosition()).getPosterPath());
                intent.putExtra(SecondActivity.EXTRA_MOVIE, result);
                parent.getContext().startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Result data = resultList.get(position);
        nilai = data.getVoteAverage();
        Rating = data.getVoteAverage();
        holder.tvTitle.setText(resultList.get(position).getTitle());


        float newValue = (float)Rating;

        holder.nilai.setText(nilai + "/10");
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setStepSize((float) 0.5);
        holder.ratingBar.setRating(newValue / 2);
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w185" + resultList.get(position).getPosterPath()).into(holder.ivPoster);

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPoster;
        TextView tvTitle, tvDesc, nilai;
        RelativeLayout relativeLayout;
        RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPoster = itemView.findViewById(R.id.view);
            tvTitle = itemView.findViewById(R.id.textView5);
            nilai = itemView.findViewById(R.id.textView6);
            relativeLayout = itemView.findViewById(R.id.layoutMovieDetail);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
