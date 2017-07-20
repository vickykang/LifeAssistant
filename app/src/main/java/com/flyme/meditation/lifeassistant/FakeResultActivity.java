package com.flyme.meditation.lifeassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

/**
 * Created by kangweodai on 20/07/17.
 */

public class FakeResultActivity extends AppCompatActivity {

    private ListView mListView;
    private View mEmptyView;
    private View mFailView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_result);
        mListView = (ListView) findViewById(android.R.id.list);
        mEmptyView = findViewById(R.id.tv_empty);
        mFailView = findViewById(R.id.tv_fail);

        mFailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    private void search() {

    }

    private void onSuccess() {
        mListView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mFailView.setVisibility(View.GONE);
    }

    private void onFail() {
        mListView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mFailView.setVisibility(View.VISIBLE);
    }

    private void onEmpty() {
        mListView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        mFailView.setVisibility(View.GONE);
    }
}
