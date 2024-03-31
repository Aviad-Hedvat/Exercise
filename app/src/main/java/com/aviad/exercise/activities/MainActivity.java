package com.aviad.exercise.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.aviad.exercise.R;
import com.aviad.exercise.network.RetroPhoto;
import com.aviad.exercise.network.RetroClient;
import com.aviad.exercise.utils.MySharedPreferences;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Button main_BTN_all;
    private Button main_BTN_specific;
    private EditText main_EDT_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        findViews();
        initViews();
    }

    private void initViews() {
        main_BTN_all.setOnClickListener((v) -> {
            Intent intent = new Intent(context, Activity_Items.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        main_BTN_specific.setOnClickListener((v) -> showSpecificItem());
    }

    private void findViews() {
        main_BTN_all = findViewById(R.id.main_BTN_all);
        main_BTN_specific = findViewById(R.id.main_BTN_specific);
        main_EDT_id = findViewById(R.id.main_EDT_id);
    }

    private void showSpecificItem() {
        String id = String.valueOf(main_EDT_id.getText());
        if (id.isEmpty() || id.equals("") || id.length() == 0)
            Toast.makeText(context, "ID is missing", Toast.LENGTH_SHORT).show();
        else {
            RetroClient.getInstance().getPhotoById(new RetroClient.PhotosCallback() {
                @Override
                public void onResponse(List<RetroPhoto> photosList) {
                    Log.d("pttt", "respose= " + photosList);
                    Intent intent = new Intent(context, Activity_Item.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    RetroPhoto item = photosList.get(0);
                    MySharedPreferences.getInstance().putObject("RetroPhoto", item);
                    context.startActivity(intent);
                }

                @Override
                public void onFailure(String errorMsg) {
                    Log.d("pttt", "Fail");
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            }, id);
        }
    }
}