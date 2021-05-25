package com.example.kanime.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.kanime.R;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView ivLogo, ivGoTen, ivKame;
    Animation LogoAnimation, KameAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivLogo = findViewById(R.id.ivLogoName);
        LogoAnimation = AnimationUtils.loadAnimation(this, R.anim.ani_logo);
        LogoAnimation.setDuration(1500);
        KameAnimation = AnimationUtils.loadAnimation(this, R.anim.chuong_kame);
        KameAnimation.setDuration(1500);
        ivLogo.setAnimation(LogoAnimation);

        ivGoTen = findViewById(R.id.ivGoten);
        ivKame = findViewById(R.id.ivKame);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ivGoTen.setImageResource(R.drawable.goten_1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ivGoTen.setImageResource(R.drawable.goten_2);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ivGoTen.setImageResource(R.drawable.goten_3);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ivGoTen.setImageResource(R.drawable.goten_4);
                                        ivKame.setImageResource(R.drawable.kame);
                                        ivKame.setAnimation(KameAnimation);

                                    }
                                }, 1000);

                            }
                        }, 1000);

                    }
                }, 1000);
            }
        }, 3000);





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivities(new Intent[]{intent});
                finish();
            }
        }, 7500);
    }
}