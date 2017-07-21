package com.flyme.meditation.lifeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.flyme.meditation.lifeassistant.bean.TicketBean;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kangweodai on 20/07/17.
 */

public class FakeResultActivity extends AppCompatActivity {

    private ListView mListView;
    private View mEmptyView;
    private View mFailView;

    private TicketAdapter mAdapter;

    private String mStart;
    private String mEnd;
    private Calendar mDate;

    private List<TicketBean> mTickets;

    private DataManager mDataManager;

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

        mAdapter = new TicketAdapter();
        mListView.setAdapter(mAdapter);

        initData();
    }

    private void initData() {
        mDataManager = DataManager.getInstance(this);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            mStart = intent.getStringExtra(MainActivity.EXTRA_START_SITE);
            mEnd = intent.getStringExtra(MainActivity.EXTRA_END_SITE);
            mDate = (Calendar) intent.getSerializableExtra(MainActivity.EXTRA_SELECTED_DATE);
            search();
        } else {
            onFail();
        }
    }

    private void search() {
        mTickets = mDataManager.searchTickets(mStart, mEnd, mDate);
        if (mTickets == null || mTickets.isEmpty()) {
            onEmpty();
        } else {
            onSuccess();
        }
    }

    private void onSuccess() {
        mListView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mFailView.setVisibility(View.GONE);
        mAdapter.notifyDataSetChanged();
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

    private class TicketAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTickets != null ? mTickets.size() : 0;
        }

        @Override
        public TicketBean getItem(int position) {
            return mTickets != null ? mTickets.get(position) : null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(FakeResultActivity.this)
                        .inflate(R.layout.item_ticket_result, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            TicketBean ticket = getItem(position);
            if (ticket == null) {
                return null;
            }

            holder.tvStartTime.setText(ticket.getStartTime().getTime());
            holder.tvEndTime.setText(ticket.getStartTime().getTime());
            holder.tvStartSite.setText(ticket.getStartSite().getName());
            holder.tvEndSite.setText(ticket.getEndSite().getName());
            holder.tvPrice.setText("￥" + ticket.getPrice());
            holder.tvAir.setText(ticket.getAir().toString());

            return convertView;
        }
    }

    private class ViewHolder {

        TextView tvStartTime;
        TextView tvEndTime;
        TextView tvStartSite;
        TextView tvEndSite;
        TextView tvPrice;
        TextView tvAir;

        ViewHolder(View view) {
            tvStartTime = (TextView) view.findViewById(R.id.tv_start_time);
            tvEndTime = (TextView) view.findViewById(R.id.tv_end_time);
            tvStartSite = (TextView) view.findViewById(R.id.tv_start_site);
            tvEndSite = (TextView) view.findViewById(R.id.tv_end_site);
            tvPrice = (TextView) view.findViewById(R.id.tv_price);
            tvAir = (TextView) view.findViewById(R.id.tv_air);
        }
    }
}
