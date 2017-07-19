package com.flyme.meditation.lifeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

import java.util.Calendar;

/**
 * Created by kangweodai on 19/07/17.
 */

public class CalendarActivity extends AppCompatActivity {

    public static final String EXTRA_SELECTED_DATE = "selected_date";
    public static final String EXTRA_YEAR = "year";
    public static final String EXTRA_MONTH = "month";
    public static final String EXTRA_DAY_OF_MONTH = "dayOfMonth";

    private CalendarView mCalendarView;
    private long mSelectedDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mSelectedDate = intent.getLongExtra(EXTRA_SELECTED_DATE, 0);
    }

    private void initView() {
        Calendar today = Calendar.getInstance();
        mCalendarView.setDate(mSelectedDate == 0 ? today.getTimeInMillis(): mSelectedDate);
        mCalendarView.setMinDate(today.getTimeInMillis());

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_YEAR, year);
                intent.putExtra(EXTRA_MONTH, month);
                intent.putExtra(EXTRA_DAY_OF_MONTH, dayOfMonth);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
