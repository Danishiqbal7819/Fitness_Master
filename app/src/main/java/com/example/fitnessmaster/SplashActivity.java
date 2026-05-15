package com.example.fitnessmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2200;
    //Hooks
    TextView a, slogan;
    TextView subtitle, pill;
    ImageView brandIcon;
    //Animations
    Animation topAnimantion,bottomAnimation,middleAnimation;
    SharedPreferences sharedPreferences;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable navigateRunnable = new Runnable() {
        @Override
        public void run() {
            isLogin = sharedPreferences.getBoolean("isLogin",false);
            if(isLogin){
                Intent intent=new Intent(SplashActivity.this, DashboardActivity.class);
                finishAffinity();
                startActivity(intent);
            }
            else {
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                finishAffinity();
                startActivity(intent);
            }
        }
    };

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


        a = findViewById(R.id.a);
        slogan = findViewById(R.id.tagLine);
        subtitle = findViewById(R.id.splashSubtitle);
        pill = findViewById(R.id.splashPill);
        brandIcon = findViewById(R.id.brandIcon);
        //Animation Calls
        topAnimantion = AnimationUtils.loadAnimation(this, R.anim.topanimation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottomanimation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middleanimation);
        //-----------Setting Animations to the elements of Splash Screen-------- -
        brandIcon.setAnimation(topAnimantion);
        a.setAnimation(middleAnimation);
        slogan.setAnimation(bottomAnimation);
        subtitle.setAnimation(bottomAnimation);
        pill.setAnimation(bottomAnimation);
        handler.postDelayed(navigateRunnable, SPLASH_TIME_OUT);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(navigateRunnable);
        super.onDestroy();
    }
}
