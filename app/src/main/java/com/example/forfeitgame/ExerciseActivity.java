package com.example.forfeitgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ExerciseActivity extends AppCompatActivity {
    private TextView textViewForExercise;
private ImageView imageViewFace;
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
        AnimatorSet set = new AnimatorSet();
        set.setDuration(400);
        set.playTogether(flipImage(), flipText());
        set.start();

//            flipImage();
//        flipText();



        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // прячем панель навигации
                | View.SYSTEM_UI_FLAG_FULLSCREEN // прячем строку состояния
                | View.SYSTEM_UI_FLAG_IMMERSIVE);


        textViewForExercise.setText(exercises[numberOfExercise]);
        textViewForExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimatorSet set = new AnimatorSet();
                set.setDuration(400);
                set.playTogether(flipImage(), flipText());
                set.start();
//                  flipImage();
//                  flipText();

                Intent intent = MainActivity.newIntent(ExerciseActivity.this);
                startActivity(intent);
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
        imageViewFace = findViewById(R.id.imageViewFace);
    }

    private int getNumber() {
        Random random = new Random();
        return random.nextInt(exercises.length);
    }

    private Animator flipImage() {
       return ObjectAnimator.ofFloat(imageViewFace, "rotationY", 0f, 180f);
//
//        ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewFace, "rotationY", 0f, 180f);
//        animator.setDuration(500);
//        animator.start();
    }

    private Animator flipText() {

//        textViewForExercise.setRotationY(180f);
       return ObjectAnimator.ofFloat(textViewForExercise, "rotationY", 0f, 180f);
//
//        ObjectAnimator animator = ObjectAnimator.ofFloat(textViewForExercise, "rotationY", 0f, 180f);
//        animator.setDuration(500);
//        animator.start();
    }

}