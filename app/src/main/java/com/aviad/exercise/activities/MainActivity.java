package com.aviad.exercise.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aviad.exercise.adapters.Adapter_RetroPhoto;
import com.aviad.exercise.R;
import com.aviad.exercise.network.GetDataService;
import com.aviad.exercise.network.RetroPhoto;
import com.aviad.exercise.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView list_LST_photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_LST_photos = findViewById(R.id.list_LST_photos);

        /* Create handle for the RetrofitInstance interface */
        GetDataService service = RetrofitClientInstance.getInstance().create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(@NonNull Call<List<RetroPhoto>> call, @NonNull Response<List<RetroPhoto>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<RetroPhoto>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Method to generate List of data using RecyclerView with custom adapter */
    private void generateDataList(List<RetroPhoto> photoList) {
        Adapter_RetroPhoto adapter = new Adapter_RetroPhoto(photoList, this);
        list_LST_photos.setLayoutManager(new LinearLayoutManager(this));
        list_LST_photos.setAdapter(adapter);
    }
}