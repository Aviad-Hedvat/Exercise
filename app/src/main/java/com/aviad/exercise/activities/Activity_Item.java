package com.aviad.exercise.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aviad.exercise.R;
import com.aviad.exercise.network.RetroPhoto;
import com.aviad.exercise.utils.MySharedPreferences;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class Activity_Item extends AppCompatActivity {

    private ImageView item_IMG_image;
    private TextView item_LBL_title;
    private Button item_BTN_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        findView();

        RetroPhoto item =  MySharedPreferences.getInstance().getObject("RetroPhoto", RetroPhoto.class);

        Glide
                .with(getApplicationContext())
                .load(item.getUrl())
                .centerCrop()
                .into(item_IMG_image);

        item_LBL_title.setText(item.getTitle());

        item_BTN_back.setOnClickListener((v) -> finish());

    }

    private void findView() {
        item_IMG_image = findViewById(R.id.item_IMG_image);
        item_BTN_back = findViewById(R.id.item_BTN_back);
        item_LBL_title = findViewById(R.id.item_LBL_title);
    }

}