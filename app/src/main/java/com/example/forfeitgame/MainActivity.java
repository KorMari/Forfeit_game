package com.example.forfeitgame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private ImageView ImageViewFront;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.common);
        initViews();
        setupWindow();

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "onClick imageViewBack");
                flipCard(MainActivity.this, ImageViewFront, imageViewBack);
            }
        });

        ImageViewFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // write log
                Log.d("MainActivity", "onClick ImageViewFront");
                flipCard(MainActivity.this, imageViewBack, ImageViewFront);
            }
        });
    }

    private void setupWindow() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide navigation panel
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide state row
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }

    private void initViews() {
        imageViewBack = findViewById(R.id.back);
        ImageViewFront = findViewById(R.id.front);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
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
                public void onAnimationEnd(Animator animation) {
                    inVisibleView.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            Log.d("MainActivity", e.getMessage());
        }
    }


//    private void flipCard(Context context, View visibleView, View inVisibleView) {
//        try {
//            flipOutAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(
//                    context,
//                    R.animator.flip_out
//            );
//
//
//            flipOutAnimatorSet.setTarget(inVisibleView);
//            flipInAnimationSet = (AnimatorSet) AnimatorInflater.loadAnimator(
//                    context,
//                    R.animator.flip_in
//            );
//            flipInAnimationSet.setTarget(visibleView);
//
//            AnimatorSet set = new AnimatorSet();
//            set.playTogether(flipOutAnimatorSet, flipInAnimationSet);
//            set.start();
//
////            inVisibleView.setVisibility(View.GONE);
//
//            set.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(@NonNull Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(@NonNull Animator animation) {
//                    inVisibleView.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onAnimationCancel(@NonNull Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(@NonNull Animator animation) {
//
//                }
//            });
//        } catch (Exception e) {
//            Log.d("MainActivity", e.getMessage());
//        }
//    }

}