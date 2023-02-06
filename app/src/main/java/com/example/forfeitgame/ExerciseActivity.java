package com.example.forfeitgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class ExerciseActivity extends AppCompatActivity {
    private TextView textViewForExercise;

    private String[] exercises;
    int numberOfExercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        initViews();
        if (savedInstanceState != null) {
            exercises = getResources().getStringArray(R.array.forfeit);
            numberOfExercise = savedInstanceState.getInt("numberOfExercise");
        } else {
            exercises = getResources().getStringArray(R.array.forfeit);
            numberOfExercise = getNumber();
        }


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // прячем панель навигации
                | View.SYSTEM_UI_FLAG_FULLSCREEN // прячем строку состояния
                | View.SYSTEM_UI_FLAG_IMMERSIVE);


        textViewForExercise.setText(exercises[numberOfExercise]);
        textViewForExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.newIntent(ExerciseActivity.this);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ExerciseActivity.this, new Pair<View, String>(textViewForExercise, "pour"));
                startActivity(intent, options.toBundle());
            }
        });

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ExerciseActivity.class);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("numberOfExercise", numberOfExercise);
    }

    private void initViews() {
        textViewForExercise = findViewById(R.id.textViewForExercise);
    }

    private int getNumber() {
        Random random = new Random();
        return random.nextInt(exercises.length);
    }


}