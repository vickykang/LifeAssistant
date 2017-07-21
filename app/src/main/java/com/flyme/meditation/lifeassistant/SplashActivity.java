package com.flyme.meditation.lifeassistant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zuojie on 17-7-21.
 */

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private Handler handler = new Handler();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, preferences.getBoolean("isLogin", false) ? MainActivity.class : LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1236);
    }
}
