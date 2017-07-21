package com.flyme.meditation.lifeassistant;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyme.meditation.lifeassistant.bean.TicketBean;
import com.flyme.meditation.lifeassistant.database.ClazzBean;

/**
 * Created by kangweodai on 21/07/17.
 */
public class FakeTicketActivity extends AppCompatActivity {

    private TextView mInfoTextView;
    private TextView mStartTimeView;
    private TextView mEndTimeView;
    private TextView mStartSiteView;
    private TextView mEndSiteView;
    private TextView mDurationView;
    private ListView mListView;

    private PriceAdapter mAdapter;

    private TicketBean mTicket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Intent intent = getIntent();
        mTicket = (TicketBean) intent.getSerializableExtra(FakeResultActivity.EXTRA_TICKET);

        setTitle(mTicket.getStartSite().getCity() + " - " + mTicket.getEndSite().getCity());

        mInfoTextView = (TextView) findViewById(R.id.tv_info);
        mStartTimeView = (TextView) findViewById(R.id.tv_start_time);
        mEndTimeView = (TextView) findViewById(R.id.tv_end_time);
        mStartSiteView = (TextView) findViewById(R.id.tv_start_site);
        mEndSiteView = (TextView) findViewById(R.id.tv_end_site);
        mDurationView = (TextView) findViewById(R.id.tv_duration);
        mListView = (ListView) findViewById(android.R.id.list);

        mAdapter = new PriceAdapter();
        mListView.setAdapter(mAdapter);

        bindView();
    }

    private void bindView() {
        mInfoTextView.setText(mTicket.getAir().getCompany() + " " + mTicket.getAir().getName()
                + " " + mTicket.getStartTime().getDate());
        mStartTimeView.setText(mTicket.getStartTime().getTime());
        mEndTimeView.setText(mTicket.getEndTime().getTime());
        mStartSiteView.setText(mTicket.getStartSite().getName());
        mEndSiteView.setText(mTicket.getEndSite().getName());
        mDurationView.setText(DataManager.formatDuration(this, mTicket.getDuration()));
        mAdapter.notifyDataSetChanged();
    }

    private class PriceAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTicket != null && mTicket.getClazzs() != null ? mTicket.getClazzs().size() : 0;
        }

        @Override
        public ClazzBean getItem(int position) {
            return mTicket != null && mTicket.getClazzs() != null ?
                    mTicket.getClazzs().get(position) : null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(FakeTicketActivity.this)
                        .inflate(R.layout.item_ticket_price, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final ClazzBean clazz = getItem(position);
            holder.tvPrice.setText("￥ "  + clazz.getPrice());
            holder.tvClass.setText(clazz.getClazz());
            holder.btnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FakeTicketActivity.this, "预订成功！", Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvPrice;
        TextView tvClass;
        Button btnBook;

        public ViewHolder(View view) {
            tvPrice = (TextView) view.findViewById(R.id.tv_price);
            tvClass = (TextView) view.findViewById(R.id.tv_class);
            btnBook = (Button) view.findViewById(R.id.btn_book);
        }
    }
}
