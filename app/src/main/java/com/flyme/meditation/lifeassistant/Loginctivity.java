package com.flyme.meditation.lifeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

/**
 * Created by zuojie on 17-7-21.
 */

public class Loginctivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLogin = (Button) findViewById(R.id.btn_login);

        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
