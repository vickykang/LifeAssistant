package com.flyme.meditation.lifeassistant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zuojie on 17-7-21.
 */

public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Button mReg;
    private TextInputLayout mAccount, mPassword, mPassword_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        mReg = (Button) findViewById(R.id.btn_reg);
        mAccount = (TextInputLayout) findViewById(R.id.til_account);
        mPassword = (TextInputLayout) findViewById(R.id.til_password);
        mPassword_confirm = (TextInputLayout) findViewById(R.id.til_password_confirm);

        mReg.setOnClickListener(this);
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reg:
                String account = mAccount.getEditText().getText().toString();
                String password = mPassword.getEditText().getText().toString();
                String password_confirm = mPassword_confirm.getEditText().getText().toString();
                if (account.equals("")) {
                    mAccount.setError("请输入帐号");
                    return;
                }
                if (password.equals("")) {
                    mPassword.setError("请输入密码");
                    return;
                }
                if (password_confirm.equals("")) {
                    mPassword_confirm.setError("请输入确认密码");
                    return;
                }
                if (!password_confirm.equals(password)) {
                    mPassword_confirm.setError("两次密码输入不一致");
                    mPassword_confirm.getEditText().setText("");
                    return;
                }
                editor = preferences.edit();
                editor.putString(account, password);
                editor.putBoolean("isLogin", true);
                editor.putString("currentUser", account);
                editor.apply();

                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
        }
    }
}
