package com.flyme.meditation.lifeassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zuojie on 17-7-21.
 */

public class PersonActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences preferences;
    private TextView mUser;
    private Button mLogout;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        mUser = (TextView) findViewById(R.id.user);
        mLogout = (Button) findViewById(R.id.btn_logout);

        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        mUser.setText(preferences.getString("currentUser", "null"));
        mLogout.setOnClickListener(this);

        builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.logout);
        builder.setMessage("即将退出该帐号，将不会再收到乘机提醒和优惠信息");
        builder.setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLogin", false);
                editor.apply();
                startActivity(new Intent(PersonActivity.this, LoginActivity.class));
                finishAffinity();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                if (dialog == null) {
                    dialog = builder.create();
                }
                dialog.show();
                break;
        }
    }
}
