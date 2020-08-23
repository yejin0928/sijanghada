package com.example.sijang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sijang.Login.LoginActivity;

public class loadingActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 3000;
    Animation bottomAnim;
    ImageView image;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        this.bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_ani);
        this.image=findViewById(R.id.people);
        this.image.setAnimation(this.bottomAnim);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                loadingActivity.this.startActivity(new Intent(loadingActivity.this, LoginActivity.class));
                loadingActivity.this.finish();
            }
        }, (long) SPLASH_SCREEN);
    }
}