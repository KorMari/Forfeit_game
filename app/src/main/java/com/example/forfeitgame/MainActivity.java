package com.example.forfeitgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private ImageView imageViewFace;
    private TextView textViewForExercise;


    private AnimatorSet flipOutAnimatorSet;
    private AnimatorSet flipInAnimationSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.card);
        initViews();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // прячем панель навигации
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // прячем строку состояния
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );



        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


flipCard(MainActivity.this, imageViewBack, imageViewFace);

            }
        });

        imageViewFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                flipCard(MainActivity.this, imageViewBack, imageViewFace);

            }
        });

    }

    private void initViews() {
        imageViewBack = findViewById(R.id.imageViewBack);
        imageViewFace = findViewById(R.id.imageViewFace);
        textViewForExercise = findViewById(R.id.textViewForExercise);

    }

    public static Intent newIntent(Context context) {

        return new Intent(context, MainActivity.class);
    }




    private void flipCard(Context context, View visibleView, View inVisibleView) {
        try {
            flipOutAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_out
            );


            flipOutAnimatorSet.setTarget(inVisibleView);
            flipInAnimationSet = (AnimatorSet) AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
            );
            flipInAnimationSet.setTarget(visibleView);

            AnimatorSet set = new AnimatorSet();
            set.playTogether(flipOutAnimatorSet, flipInAnimationSet);
            set.start();

//            inVisibleView.setVisibility(View.GONE);

            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationEnd(@NonNull Animator animation) {
                    inVisibleView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationRepeat(@NonNull Animator animation) {

                }
            });
} catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
        }
    }

}