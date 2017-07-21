package com.flyme.meditation.lifeassistant;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by kangweodai on 21/07/17.
 */

public class Utils {

    public static String MOBILE_REG = "^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\\\d{8}$";
    public static String EMAIL_REG = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    public static String formatDate(Calendar calendar) {
        return DateFormat.format("yyyy-MM-dd", calendar).toString();
    }

    public static String formatDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        return formatDate(calendar);
    }

    public static Calendar stringToCalendar(String date) {
        if (date.contains("-")) {
            String[] data = date.split("-");
            if (data.length == 3) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]) - 1,
                        Integer.parseInt(data[2]));
                return calendar;
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

    public static String getWeek(Context context, String date) {
        Calendar calendar = stringToCalendar(date);
        if (calendar != null) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return context.getResources().getStringArray(R.array.weeks)[dayOfWeek - 1];
        }
        return null;
    }

    public static boolean isMobilePhoneNumber(String number) {
        return Pattern.compile(MOBILE_REG).matcher(number).matches();
    }

    public static boolean isEmail(String email) {
        return Pattern.compile(EMAIL_REG).matcher(email).matches();
    }
}
