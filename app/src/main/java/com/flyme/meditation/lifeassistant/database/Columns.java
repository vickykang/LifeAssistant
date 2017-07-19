package com.flyme.meditation.lifeassistant.database;

import android.provider.BaseColumns;

/**
 * Created by kangweodai on 19/07/17.
 */

public class Columns implements BaseColumns {

    // Columns of table ticket
    public static final String COLUMN_START_SITE_ID = "start_site_id";
    public static final String COLUMN_END_SITE_ID = "end_site_id";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_END_TIME = "end_time";
    public static final String COLUMN_AIR_ID = "air_id";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IS_NONSTOP = "is_nonstop";

    // Columns of table air
    public static final String COLUMN_COMPANY = "company";
    public static final String COLUMN_AIR_NAME = "air_name";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_SIZE = "size";

    // Columns of table site
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_SITE_NAME = "site_name";
}
