package com.flyme.meditation.lifeassistant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by zuojie on 17-7-21.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLogin;
    private TextView mReg;
    private SharedPreferences preferences;
    private TextInputLayout mAccount, mPassword;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        if (preferences.getBoolean("isLogin", false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        mLogin = (Button) findViewById(R.id.btn_login);
        mReg = (TextView) findViewById(R.id.btn_reg);
        mAccount = (TextInputLayout) findViewById(R.id.til_account);
        mPassword = (TextInputLayout) findViewById(R.id.til_password);

        mAccount.getEditText().setText(preferences.getString("currentUser", ""));
        mLogin.setOnClickListener(this);
        mReg.setOnClickListener(this);
        mLogin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    tryLogin();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                tryLogin();
                break;
            case R.id.btn_reg:
                startActivity(new Intent(this, RegActivity.class));
                break;
        }
    }

    private void tryLogin() {
        String account = mAccount.getEditText().getText().toString();
        String password = mPassword.getEditText().getText().toString();
        if (account.equals("")) {
            mAccount.setError("请输入帐号");
            return;
        }
        if (password.equals("")) {
            mPassword.setError("请输入密码");
            return;
        }
        if (preferences.getString(account, "").equals(password)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLogin", true);
            editor.putString("currentUser", account);
            editor.apply();
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "帐号或密码输入错误", Toast.LENGTH_SHORT).show();
            mPassword.getEditText().setText("");
        }
    }
}
