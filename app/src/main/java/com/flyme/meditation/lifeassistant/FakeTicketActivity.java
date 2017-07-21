package com.flyme.meditation.lifeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.flyme.meditation.lifeassistant.bean.SourceBean;
import com.flyme.meditation.lifeassistant.bean.TicketBean;

import java.util.List;

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
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private FragmentPagerAdapter mAdapter;

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
        mTabLayout = (TabLayout) findViewById(R.id.tab_source);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mAdapter = new SourceFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

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
    }

    private class SourceFragmentAdapter extends FragmentPagerAdapter {

        List<SourceBean> sources;

        public SourceFragmentAdapter(FragmentManager fm) {
            super(fm);
            sources = mTicket.getSources();
        }

        @Override
        public Fragment getItem(int position) {
            return SourcePriceFragment.newInstance(sources.get(position));
        }

        @Override
        public int getCount() {
            return sources != null ? sources.size() : 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sources.get(position) != null ? sources.get(position).getName() : "";
        }
    }
}
