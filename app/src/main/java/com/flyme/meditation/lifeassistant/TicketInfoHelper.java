package com.flyme.meditation.lifeassistant;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.flyme.meditation.lifeassistant.bean.TicketBean;

/**
 * Created by kangweodai on 21/07/17.
 */

public class TicketInfoHelper {

    private Context mContext;
    private View mContainer;

    private TextView mDateTextView;
    private TextView mStartTimeView;
    private TextView mEndTimeView;
    private TextView mStartSiteView;
    private TextView mEndSiteView;
    private TextView mDurationView;
    private TextView mAirTextView;

    public TicketInfoHelper(Context context, View container) {
        mContext = context;
        mContainer = container;
        initView();
    }

    private void initView() {
        mDateTextView = (TextView) mContainer.findViewById(R.id.tv_date);
        mStartTimeView = (TextView) mContainer.findViewById(R.id.tv_start_time);
        mEndTimeView = (TextView) mContainer.findViewById(R.id.tv_end_time);
        mStartSiteView = (TextView) mContainer.findViewById(R.id.tv_start_site);
        mEndSiteView = (TextView) mContainer.findViewById(R.id.tv_end_site);
        mDurationView = (TextView) mContainer.findViewById(R.id.tv_duration);
        mAirTextView = (TextView) mContainer.findViewById(R.id.tv_air);
    }

    public void bindView(TicketBean ticket) {
        String date = ticket.getStartTime().getDate();
        mDateTextView.setText(date + " " + Utils.getWeek(mContext, date));
        mStartTimeView.setText(ticket.getStartTime().getTime());
        mEndTimeView.setText(ticket.getEndTime().getTime());
        mStartSiteView.setText(ticket.getStartSite().getName());
        mEndSiteView.setText(ticket.getEndSite().getName());
        mDurationView.setText(Utils.formatDuration(mContext, ticket.getDuration()));
        mAirTextView.setText(ticket.getAir().toString());
    }
}
