package com.example.forfeitgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private ImageView ImageViewFront;


    private TextView textViewForExercises;
    private TextView textViewEmpty;
    private Random random = new Random();

    private String[] exercises;
    private int numberOfExercise;
    private Boolean isAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        isAnim = false;
        if (savedInstanceState != null) {
            exercises = getResources().getStringArray(R.array.forfeit);
            setNumberOfExercise(savedInstanceState.getInt("numberOfExercise"));
        } else {
            exercises = getResources().getStringArray(R.array.forfeit);
            setNumberOfExercise(getRandomNumber());
        }


        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAnim) {
                    isAnim = true;
                    setNumberOfExercise(getRandomNumber());
                    textViewForExercises.setText(exercises[getNumberOfExercise()]);
                    flipCard(MainActivity.this, ImageViewFront, imageViewBack);
                    flipCard(MainActivity.this, textViewForExercises, textViewEmpty);
                    Log.d("MainActivity", "Back click");
                }
            }
        });

        ImageViewFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAnim) {
                    isAnim = true;
                    flipCard(MainActivity.this, imageViewBack, ImageViewFront);
                    flipCard(MainActivity.this, textViewEmpty, textViewForExercises);
                    Log.d("MainActivity", "Front click");
                }
            }
        });
    }



    private void initViews() {
        imageViewBack = findViewById(R.id.back);
        ImageViewFront = findViewById(R.id.front);
        textViewForExercises = findViewById(R.id.textViewForExercise);
        textViewEmpty = findViewById(R.id.textViewEmpty);
    }


    public void flipCard(Context context, View visibleView, View inVisibleView) {
        try {
            visibleView.setVisibility(View.VISIBLE);
            float scale = context.getResources().getDisplayMetrics().density;
            float cameraDist = 8000 * scale;
            visibleView.setCameraDistance(cameraDist);
            inVisibleView.setCameraDistance(cameraDist);
            AnimatorSet flipOutAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_out
            );
            flipOutAnimatorSet.setTarget(inVisibleView);
            AnimatorSet flipInAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
            );
            flipInAnimatorSet.setTarget(visibleView);
            flipOutAnimatorSet.start();
            flipInAnimatorSet.start();
            flipInAnimatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.d("MainActivity", "Анимация началась");
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    inVisibleView.setVisibility(View.GONE);
                    Log.d("MainActivity", "Анимация закончилась");
                    isAnim = false;
                }
            });
        } catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
        }
    }

    private int getRandomNumber() {
        return random.nextInt(exercises.length);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("numberOfExercise", numberOfExercise);
    }

    public int getNumberOfExercise() {
        return numberOfExercise;
    }

    public void setNumberOfExercise(int numberOfExercise) {
        this.numberOfExercise = numberOfExercise;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide navigation panel
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide state row
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }
}