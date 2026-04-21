package com.example.firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 5000;
    //Hooks
    View first,second,third,fourth,fifth,sixth;
    TextView a, slogan;
    //Animations
    Animation topAnimantion,bottomAnimation,middleAnimation;
    SharedPreferences sharedPreferences;

    private boolean isLogin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);


        first = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        third = findViewById(R.id.third_line);
        fourth = findViewById(R.id.fourth_line);
        fifth = findViewById(R.id.fifth_line);
        sixth = findViewById(R.id.sixth_line);
        a = findViewById(R.id.a);
        slogan = findViewById(R.id.tagLine);
        //Animation Calls
        topAnimantion = AnimationUtils.loadAnimation(this, R.anim.topanimation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottomanimation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middleanimation);
        //-----------Setting Animations to the elements of Splash Screen-------- -
                first.setAnimation(topAnimantion);
        second.setAnimation(topAnimantion);
        third.setAnimation(topAnimantion);
        fourth.setAnimation(topAnimantion);
        fifth.setAnimation(topAnimantion);
        sixth.setAnimation(topAnimantion);
        a.setAnimation(middleAnimation);
        slogan.setAnimation(bottomAnimation);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                   isLogin = sharedPreferences.getBoolean("isLogin",false);
                    if(isLogin){
                        Intent intent=new Intent(SplashActivity.this,MainActivity3.class);
                        finishAffinity();
                        startActivity(intent);
                    }
                    else {
                       Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                       finishAffinity();
                        startActivity(intent);
                    }
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}