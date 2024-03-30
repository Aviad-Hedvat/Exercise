package com.aviad.exercise;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aviad.exercise.database.RetroPhoto;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_RetroPhoto extends RecyclerView.Adapter<Adapter_RetroPhoto.ViewHolder> {

    private List<RetroPhoto> dataList;
    private LayoutInflater layoutInflater;
    private Context context;
    private PhotoItemClickedListener photoItemClickedListener;

    public Adapter_RetroPhoto(List<RetroPhoto> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    public void setPhotoItemClickedListener(PhotoItemClickedListener photoItemClickedListener) {
        this.photoItemClickedListener = photoItemClickedListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private ImageView coverImage;
        private Button list_BTN_details;
        private Gson gson;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.title);
            coverImage = itemView.findViewById(R.id.coverImage);
            list_BTN_details = itemView.findViewById(R.id.list_BTN_details);

            gson = new Gson();

            int position = getAdapterPosition();
            list_BTN_details.setOnClickListener(v -> {
                Log.d("pttt", "Adapter Click");
                Log.d("pttt", "size= " + getItemCount());
                Intent intent = new Intent(context, Activity_Item.class);
                RetroPhoto item = getItem(position);
                String json = gson.toJson(item);
                context
                        .getSharedPreferences("MY_SP", Context.MODE_PRIVATE)
                        .edit()
                        .putString("RetroPhoto", json)
                        .apply();
                context.startActivity(intent);
            });
        }
    }

    public RetroPhoto getItem(int position) {
        return dataList.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_of_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RetroPhoto photo = dataList.get(position);
        Glide
                .with(context)
                .load(photo.getUrl())
                .centerCrop()
                .into(holder.coverImage);

        holder.txtTitle.setText(photo.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface PhotoItemClickedListener{
        void itemClicked(RetroPhoto photo, int position);
    }
}