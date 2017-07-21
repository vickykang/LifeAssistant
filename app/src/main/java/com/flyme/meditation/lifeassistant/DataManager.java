package com.flyme.meditation.lifeassistant;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import com.flyme.meditation.lifeassistant.bean.FlightBean;
import com.flyme.meditation.lifeassistant.bean.SiteBean;
import com.flyme.meditation.lifeassistant.bean.TicketBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kangweodai on 19/07/17.
 */

public class DataManager {

    private static final String TAG = "DataManager";

    private static DataManager sInstance;
    private Context mContext;
    private List<TicketBean> mTickets;

    public static DataManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataManager(context);
        }
        return sInstance;
    }

    private DataManager(Context context) {
        mContext = context.getApplicationContext();
        Gson gson = new Gson();
        String json = readStringFromAssets(mContext, "tickets.json");
        mTickets = gson.fromJson(json, new TypeToken<List<TicketBean>>() {}.getType());
    }

    public List<TicketBean> getTickets() {
        return mTickets;
    }

    public List<TicketBean> searchTickets(String start, String end, Calendar calendar) {
        String date = formatDate(calendar);
        List<TicketBean> results = new ArrayList<>();

        for (TicketBean ticket : mTickets) {

            SiteBean startSite = ticket.getStartSite();
            SiteBean endSite = ticket.getEndSite();

            if (ticket.getStartTime().getDate().equals (date) &&
                    (startSite.getCity().equals(start) || startSite.getName().equals(start))
                    && (endSite.getCity().equals(end) || endSite.getName().equals(end))) {
                results.add(ticket);
            }
        }
        Collections.sort(results, new TicketPriceComparator());
        return results;
    }

    private String readStringFromAssets(Context context, String file) {
        StringBuilder buf = new StringBuilder();
        InputStream json = null;
        try {
            json = context.getAssets().open(file);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
        } catch (IOException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return buf.toString();
    }

    public String formatDate(Calendar calendar) {
        return DateFormat.format("yyyy-MM-dd", calendar).toString();
    }

    public String formatDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        return formatDate(calendar);
    }

    public Calendar stringToCalendar(String date) {
        if (date.contains("-")) {
            String[] data = date.split("-");
            if (data.length == 3) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.getInteger(data[0]),
                        Integer.getInteger(data[1]) - 1,
                        Integer.getInteger(data[2]));
            }
        }
        return null;
    }

    public static String formatDuration(Context context, int duration) {
        StringBuilder builder = new StringBuilder();
        int h = duration / 60;
        if (h > 0) {
            builder.append(h).append(context.getString(R.string.hour));
        }
        builder.append(duration - h * 60).append(context.getString(R.string.minute));
        return builder.toString();
    }

    private class TicketPriceComparator implements Comparator<TicketBean> {

        @Override
        public int compare(TicketBean o1, TicketBean o2) {
            return o1.getPrice() > o2.getPrice() ? 1 : (o1.getPrice() < o2.getPrice() ? -1 : 0);
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
