package com.flyme.meditation.lifeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flyme.meditation.lifeassistant.bean.SourceBean;
import com.flyme.meditation.lifeassistant.bean.TicketBean;
import com.flyme.meditation.lifeassistant.database.ClazzBean;

/**
 * Created by kangweodai on 21/07/17.
 */

public class BookActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_TICKET = "ticket";
    public static final String EXTRA_SOURCE = "SOURCE";
    public static final String EXTRA_CLAZZ = "clazz";

    private TicketBean mTicket;
    private SourceBean mSource;
    private ClazzBean mClazz;

    private TicketInfoHelper mTicketInfoHelper;

    private TextView mClazzTextView;
    private TextView mPassengerView;
    private EditText mContactEdit;
    private EditText mPhoneEdit;
    private EditText mEmailEdit;
    private TextView mPriceTextView;
    private Button mSubmitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();
        mTicket = (TicketBean) intent.getSerializableExtra(EXTRA_TICKET);
        mSource = (SourceBean) intent.getSerializableExtra(EXTRA_SOURCE);
        mClazz = (ClazzBean) intent.getSerializableExtra(EXTRA_CLAZZ);

        View view = findViewById(R.id.root);
        mTicketInfoHelper = new TicketInfoHelper(this, view);

        mClazzTextView = (TextView) findViewById(R.id.tv_class);
        mPassengerView = (TextView) findViewById(R.id.passenger);
        mContactEdit = (EditText) findViewById(R.id.edt_contact);
        mPhoneEdit = (EditText) findViewById(R.id.edt_phone);
        mEmailEdit = (EditText) findViewById(R.id.edt_email);

        mPriceTextView = (TextView) findViewById(R.id.tv_price);
        mSubmitButton = (Button) findViewById(R.id.btn_submit);

        bindView();
    }

    private void bindView() {
        mTicketInfoHelper.bindView(mTicket);

        mClazzTextView.setText(mClazz.getClazz() + " ￥" + mClazz.getPrice());
        // TODO: single passenger
        mPriceTextView.setText("￥ " + mClazz.getPrice());

        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            String name = mContactEdit.getText().toString();
            String number = mPhoneEdit.getText().toString();
            String email = mEmailEdit.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "请输入姓名！", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(number)) {
                Toast.makeText(this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, BookResultActivity.class));
            }
        }
    }
}
