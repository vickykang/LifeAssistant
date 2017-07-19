package com.flyme.meditation.lifeassistant;

import android.content.Context;

/**
 * Created by kangweodai on 19/07/17.
 */

public class DataManager {

    private static DataManager sInstance;
    private Context mContext;

    public static DataManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataManager(context);
        }
        return sInstance;
    }

    private DataManager(Context context) {
        mContext = context.getApplicationContext();
    }
}
