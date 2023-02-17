package com.example.forfeitgame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private ImageView imageViewBack;


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
        flipImage();

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    flipImage();



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
        imageViewBack = findViewById(R.id.imageViewBack);
    }

    public static Intent newIntent(Context context) {

        return new Intent(context, MainActivity.class);
    }

    private void flipImage() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewBack, "rotationY", 0f, 180f);
        animator.setDuration(500);
        animator.start();
    }
}