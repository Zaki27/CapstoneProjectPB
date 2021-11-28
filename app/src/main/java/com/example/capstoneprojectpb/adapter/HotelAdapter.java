package com.example.capstoneprojectpb.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.capstoneprojectpb.R;
import com.example.capstoneprojectpb.model.HotelModel;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private ItemClickListener itemClickListener;
    Context context;
    private ArrayList<HotelModel> hotelModel = new ArrayList<>();

    public interface ItemClickListener {
        void onClick(HotelModel HotelModel);
    }

    public void onClick(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindItem(hotelModel.get(i), itemClickListener);

    }

    @Override
    public int getItemCount() {
        return hotelModel.size();
    }

    public void setData(ArrayList<HotelModel> hotelModel){
        this.hotelModel = hotelModel;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nama_hotel;
        private final ImageView hotelsImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_hotel =itemView.findViewById(R.id.textView5);
            hotelsImage =itemView.findViewById(R.id.view);
        }

        public void bindItem(HotelModel hotelModel, ItemClickListener itemClickListener) {
            nama_hotel.setText(hotelModel.getNama());
            if (hotelModel != null) {
                Glide.with(hotelsImage)
                        .load(hotelModel.getGambar_url())
                        .centerCrop()
                        .into(hotelsImage);
            }

            if (itemClickListener != null){
                itemView.setOnClickListener(view -> {
                    itemClickListener.onClick(hotelModel);
                });
            }
        }

    }
}
