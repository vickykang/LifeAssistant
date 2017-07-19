package com.flyme.meditation.lifeassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Formatter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CALENDAR_CODE = 1;

    private EditText mStartEditText;
    private EditText mEndEditText;
    private View mSwitchButton;
    private TextView mDateTextView;
    private Button mSearchButton;

    private String mStart;
    private String mEnd;
    private Calendar mSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mSelectedDate = Calendar.getInstance();
    }

    private void initView() {
        mStartEditText = (EditText) findViewById(R.id.edt_start);
        mEndEditText = (EditText) findViewById(R.id.edt_end);
        mSwitchButton = findViewById(R.id.iv_switch);
        mDateTextView = (TextView) findViewById(R.id.tv_date);
        mSearchButton = (Button) findViewById(R.id.btn_search);

        mSwitchButton.setOnClickListener(this);
        mDateTextView.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshDateView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CALENDAR_CODE && resultCode == RESULT_OK && data != null) {
            int year = data.getIntExtra(CalendarActivity.EXTRA_YEAR,
                    mSelectedDate.get(Calendar.YEAR));
            int month = data.getIntExtra(CalendarActivity.EXTRA_MONTH,
                    mSelectedDate.get(Calendar.MONTH));
            int day = data.getIntExtra(CalendarActivity.EXTRA_DAY_OF_MONTH,
                    mSelectedDate.get(Calendar.DAY_OF_MONTH));
            mSelectedDate.set(year, month, day);
            refreshDateView();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void refreshDateView() {
        mDateTextView.setText(DateFormat.getLongDateFormat(this).
                        format(mSelectedDate.getTimeInMillis()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_switch:
                switchStartAndEnd();
                break;
            case R.id.tv_date:
                startCalendarActivity();
                break;

            case R.id.btn_search:
                searchTicket();
                break;
        }
    }

    private void switchStartAndEnd() {
        mStart = mStartEditText.getText().toString();
        mEnd = mEndEditText.getText().toString();
        String tmp = mStart;
        mStart = mEnd;
        mEnd = tmp;
        if (!TextUtils.isEmpty(mStart)) {
            mStartEditText.setText(mStart);
        }
        if (!TextUtils.isEmpty(mEnd)) {
            mEndEditText.setText(mEnd);
        }
    }

    private void startCalendarActivity() {
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra(CalendarActivity.EXTRA_SELECTED_DATE, mSelectedDate.getTimeInMillis());
        startActivityForResult(intent, REQUEST_CALENDAR_CODE);
    }

    private void searchTicket() {

    }
}
