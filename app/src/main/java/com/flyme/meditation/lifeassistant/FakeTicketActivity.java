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
import android.view.View;

import com.flyme.meditation.lifeassistant.bean.SourceBean;
import com.flyme.meditation.lifeassistant.bean.TicketBean;

import java.util.List;

/**
 * Created by kangweodai on 21/07/17.
 */
public class FakeTicketActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private FragmentPagerAdapter mAdapter;

    private TicketInfoHelper mTicketInfoHelper;

    private TicketBean mTicket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Intent intent = getIntent();
        mTicket = (TicketBean) intent.getSerializableExtra(FakeResultActivity.EXTRA_TICKET);

        setTitle(mTicket.getStartSite().getCity() + " - " + mTicket.getEndSite().getCity());

        View rootView = findViewById(R.id.root);
        mTicketInfoHelper = new TicketInfoHelper(this, rootView);
        mTicketInfoHelper.bindView(mTicket);

        mTabLayout = (TabLayout) findViewById(R.id.tab_source);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mAdapter = new SourceFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class SourceFragmentAdapter extends FragmentPagerAdapter {

        List<SourceBean> sources;

        public SourceFragmentAdapter(FragmentManager fm) {
            super(fm);
            sources = mTicket.getSources();
        }

        @Override
        public Fragment getItem(int position) {
            return SourcePriceFragment.newInstance(sources.get(position), mTicket);
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
