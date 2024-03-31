package com.aviad.exercise.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aviad.exercise.R;
import com.aviad.exercise.network.RetroPhoto;
import com.aviad.exercise.activities.Activity_Item;
import com.aviad.exercise.utils.MySharedPreferences;
import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter_RetroPhoto extends RecyclerView.Adapter<Adapter_RetroPhoto.ViewHolder> {

    private List<RetroPhoto> dataList;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter_RetroPhoto(List<RetroPhoto> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
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

        holder.list_BTN_details.setOnClickListener(v -> {
            Intent intent = new Intent(context, Activity_Item.class);
            RetroPhoto item = getItem(holder.getLayoutPosition());
            MySharedPreferences.getInstance().putObject("RetroPhoto", item);
            context.startActivity(intent);
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private ImageView coverImage;
        private Button list_BTN_details;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.title);
            coverImage = itemView.findViewById(R.id.coverImage);
            list_BTN_details = itemView.findViewById(R.id.list_BTN_details);
        }
    }
}
