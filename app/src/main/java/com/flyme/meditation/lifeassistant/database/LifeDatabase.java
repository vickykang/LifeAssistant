package com.flyme.meditation.lifeassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.flyme.meditation.lifeassistant.bean.AirBean;
import com.flyme.meditation.lifeassistant.bean.SiteBean;
import com.flyme.meditation.lifeassistant.bean.TicketBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangweodai on 19/07/17.
 */

public class LifeDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "lifeassistant.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_TICKET = "ticket";
    private static final String TABLE_AIR = "air";
    private static final String TABLE_SITE = "site";

    private static LifeDatabase sInstance;

    private Context mContext;

    public static LifeDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LifeDatabase(context);
        }
        return sInstance;
    }

    private LifeDatabase(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableTicket(db);
        createTableAir(db);
        createTableSite(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertAir(AirBean air) {
        SQLiteDatabase db = sInstance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Columns.COLUMN_COMPANY, air.getCompany());
        values.put(Columns.COLUMN_AIR_NAME, air.getName());
        values.put(Columns.COLUMN_MODEL, air.getModel());
        values.put(Columns.COLUMN_SIZE, air.getSize());
        return db.insert(TABLE_AIR, null, values);
    }

    public long insertSite(SiteBean site) {
        SQLiteDatabase db = sInstance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Columns.COLUMN_PROVINCE, site.getProvince());
        values.put(Columns.COLUMN_CITY, site.getCity());
        values.put(Columns.COLUMN_SITE_NAME, site.getName());
        return db.insert(TABLE_SITE, null, values);
    }

    public List<TicketBean> getTickets(String start, String end, long date) {

        List<SiteBean> startSiteList = querySite(start);
        List<SiteBean> endSiteList = querySite(end);

        if (startSiteList == null || startSiteList.size() == 0 || endSiteList == null
                || endSiteList.size() == 0) {
            return null;
        }

        List<TicketBean> tickets = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        String[] args = new String[startSiteList.size() * endSiteList.size()];

        final int slen = startSiteList.size();
        final int elen = startSiteList.size();



        SQLiteDatabase db = sInstance.getWritableDatabase();
        return tickets;
    }

    public List<SiteBean> querySite(String name) {
        SQLiteDatabase db = sInstance.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SITE, null, buildSiteSelection(), new String[] {name, name},
                null, null, null);
        List<SiteBean> sites = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                SiteBean site = new SiteBean();
                site.setId(cursor.getLong(cursor.getColumnIndexOrThrow(Columns._ID)));
                site.setProvince(cursor.getString(cursor.getColumnIndexOrThrow(
                        Columns.COLUMN_PROVINCE)));
                site.setCity(cursor.getString(cursor.getColumnIndexOrThrow(Columns.COLUMN_CITY)));
                site.setCity(cursor.getString(cursor.getColumnIndexOrThrow(
                        Columns.COLUMN_SITE_NAME)));
                sites.add(site);
            }
        } finally {
            cursor.close();
        }
        return sites;
    }

    private String buildSiteSelection() {
        return Columns.COLUMN_CITY + " = ? OR " + Columns.COLUMN_SITE_NAME + " = ?";
    }

    private void createTableTicket(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TICKET);
        db.execSQL("CREATE TABLE " + TABLE_TICKET + "( "
                + Columns._ID + " INTEGER PRIMARY KEY, "
                + Columns.COLUMN_START_SITE_ID + " INTEGER, "
                + Columns.COLUMN_END_SITE_ID + " INTEGER, "
                + Columns.COLUMN_START_DATE + " INTEGER, "
                + Columns.COLUMN_START_TIME + " INTEGER, "
                + Columns.COLUMN_END_DATE + " INTEGER, "
                + Columns.COLUMN_END_TIME + " INTEGER, "
                + Columns.COLUMN_AIR_ID + " INTEGER, "
                + Columns.COLUMN_DURATION + " INTEGER, "
                + Columns.COLUMN_PRICE + " INTEGER, "
                + Columns.COLUMN_IS_NONSTOP + " INTEGER"
                + ");"
        );
    }

    private void createTableAir(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AIR);
        db.execSQL("CREATE TABLE " + TABLE_AIR + "( "
                + Columns._ID + " INTEGER PRIMARY KEY, "
                + Columns.COLUMN_COMPANY + " TEXT, "
                + Columns.COLUMN_AIR_NAME + " TEXT, "
                + Columns.COLUMN_MODEL + " TEXT, "
                + Columns.COLUMN_SIZE + " TEXT"
                + ");"
        );
    }

    private void createTableSite(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SITE);
        db.execSQL("CREATE TABLE " + TABLE_SITE + "( "
                + Columns._ID + " INTEGER PRIMARY KEY, "
                + Columns.COLUMN_PROVINCE + " TEXT, "
                + Columns.COLUMN_CITY + " TEXT, "
                + Columns.COLUMN_SITE_NAME + " TEXT"
                +");"
        );
    }
}
