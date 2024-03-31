package com.aviad.exercise.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aviad.exercise.R;
import com.aviad.exercise.adapters.Adapter_RetroPhoto;
import com.aviad.exercise.network.RetroPhoto;
import com.aviad.exercise.network.RetroClient;

import java.util.List;

public class Activity_Items extends AppCompatActivity {

    private RecyclerView items_LST_photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        findViews();
        fetchPhotos();
    }

    private void fetchPhotos() {
        RetroClient.getInstance().getAllPhotos(new RetroClient.PhotosCallback() {
            @Override
            public void onResponse(List<RetroPhoto> photosList) {
                generateDataList(photosList);
            }

            @Override
            public void onFailure(String errorMsg) {
                Toast.makeText(Activity_Items.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViews() {
        items_LST_photos = findViewById(R.id.items_LST_photos);
    }


    /* Method to generate List of data using RecyclerView with custom adapter */
    private void generateDataList(List<RetroPhoto> photoList) {
        Adapter_RetroPhoto adapter = new Adapter_RetroPhoto(photoList, this);
        items_LST_photos.setLayoutManager(new GridLayoutManager(this, 1));
        items_LST_photos.setAdapter(adapter);
    }
}