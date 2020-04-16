package com.example.kdm.is_kaami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    //Variables
    Animation topAnim, bottomAnim;

    TextView tv1, tv2, textView_slogan, textView_kaami;
    View textView2_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashScreen.this, AfterSplash.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();

        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        textView_slogan = findViewById(R.id.textView_slogan);
        textView_kaami = findViewById(R.id.textView_kaami);
        textView2_View = findViewById(R.id.textView2_View);

        tv1.setAnimation(topAnim);
        tv2.setAnimation(topAnim);
        textView2_View.setAnimation(topAnim);
        textView_slogan.setAnimation(topAnim);
        textView_kaami.setAnimation(bottomAnim);
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}
