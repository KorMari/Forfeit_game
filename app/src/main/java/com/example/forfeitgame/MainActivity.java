package com.example.forfeitgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private ImageView imageViewCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();

        getWindow().getDecorView().setSystemUiVisibility(
                 View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // прячем панель навигации
                | View.SYSTEM_UI_FLAG_FULLSCREEN // прячем строку состояния
                | View.SYSTEM_UI_FLAG_IMMERSIVE
        );


        imageViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = ExerciseActivity.newIntent(MainActivity.this);

//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
//                        MainActivity.this,
//                        new Pair<View, String>(imageViewCard, "pour"));
//                startActivity(intent, options.toBundle());
                startActivity(intent);

            }
        });

    }

    private void initViews() {
        imageViewCard = findViewById(R.id.imageViewCard);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }


}