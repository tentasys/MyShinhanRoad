package com.example.shinple.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.shinple.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                finish();
                overridePendingTransition(R.anim.commons_fade_in,R.anim.commons_fade_out);
            }
        };
        handler.sendEmptyMessageDelayed(0,2200);
    }

    public void onBackPressd(){
    }
}
