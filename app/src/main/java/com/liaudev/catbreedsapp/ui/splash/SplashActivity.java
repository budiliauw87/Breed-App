package com.liaudev.catbreedsapp.ui.splash;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.liaudev.catbreedsapp.R;
import com.liaudev.catbreedsapp.databinding.ActivitySplashBinding;
import com.liaudev.catbreedsapp.ui.BaseActivity;
import com.liaudev.catbreedsapp.ui.main.MainActivity;


/**
 * Created by Budiliauw87 on 2022-05-14.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;
    private AnimatorSet animatorSet;
    private ObjectAnimator objLogo;
    private boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updateStatusBarColor(android.R.color.transparent);

        objLogo = ObjectAnimator.ofFloat(binding.imgLogo, View.TRANSLATION_Y, -150f, 0).setDuration(3000);
        objLogo.setInterpolator(new BounceInterpolator());

        objLogo.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isFinish = true;
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        objLogo.start();
        ObjectAnimator objTitle = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(500);
        ObjectAnimator objSubTitle = ObjectAnimator.ofFloat(binding.tvSubTitle, View.ALPHA, 1f).setDuration(500);
        ObjectAnimator objVersion = ObjectAnimator.ofFloat(binding.tvVersion, View.TRANSLATION_Y, 150f, 0).setDuration(500);

        animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(objTitle, objSubTitle, objVersion);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.start();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isFinish) {
            objLogo.resume();
            animatorSet.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        objLogo.pause();
        animatorSet.pause();
    }
}