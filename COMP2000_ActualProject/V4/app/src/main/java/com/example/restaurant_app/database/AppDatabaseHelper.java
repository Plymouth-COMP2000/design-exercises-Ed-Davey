package com.example.restaurant_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.restaurant_app.models.MenuItem;
import com.example.restaurant_app.models.NotificationItem;
import com.example.restaurant_app.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class AppDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MENU = "menu";
    private static final String TABLE_RESERVATIONS = "reservations";
    private static final String TABLE_NOTIFICATIONS = "notifications";



    public AppDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create Menu and Reservation tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MENU_TABLE =
                "CREATE TABLE " + TABLE_MENU + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "price REAL," +
                        "imagePath TEXT)";

        String CREATE_RESERVATION_TABLE =
                "CREATE TABLE " + TABLE_RESERVATIONS + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "guestName TEXT," +
                        "date TEXT," +
                        "time TEXT," +
                        "status TEXT)";

        String CREATE_NOTIFICATION_TABLE =
                "CREATE TABLE " + TABLE_NOTIFICATIONS + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "title TEXT," +
                        "message TEXT," +
                        "userType TEXT," +
                        "timestamp TEXT)";


        db.execSQL(CREATE_MENU_TABLE);
        db.execSQL(CREATE_RESERVATION_TABLE);
        db.execSQL(CREATE_NOTIFICATION_TABLE);
    }

    //Drop both Menu and Reservation tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS menu");
        db.execSQL("DROP TABLE IF EXISTS reservations");
        db.execSQL("DROP TABLE IF EXISTS notifications");
        onCreate(db);
    }

    //Menu CRUD operations
    public boolean insertMenuItem(MenuItem item) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", item.getName());
        values.put("price", item.getPrice());
        values.put("imagePath", item.getImagePath());

        long result = db.insert(TABLE_MENU, null, values);
        db.close();

        return result != -1;
    }

    public List<MenuItem> getAllMenuItems() {

        List<MenuItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU, null);

        if (cursor.moveToFirst()) {
            do {
                MenuItem item = new MenuItem(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getString(3)
                );
                list.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    public boolean updateMenuItem(MenuItem item) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", item.getName());
        values.put("price", item.getPrice());
        values.put("imagePath", item.getImagePath());

        int rows = db.update(
                TABLE_MENU,
                values,
                "id=?",
                new String[]{String.valueOf(item.getId())}
        );

        db.close();
        return rows > 0;
    }

    public boolean deleteMenuItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_MENU, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return rows > 0;
    }

    //Reservation CRUD operations
    public boolean insertReservation(Reservation reservation) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("guestName", reservation.getGuestName());
        values.put("date", reservation.getDate());
        values.put("time", reservation.getTime());
        values.put("status", reservation.getStatus());

        long result = db.insert(TABLE_RESERVATIONS, null, values);
        db.close();

        return result != -1;
    }

    public List<Reservation> getAllReservations() {

        List<Reservation> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RESERVATIONS, null);

        if (cursor.moveToFirst()) {
            do {
                Reservation reservation = new Reservation(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                list.add(reservation);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    public boolean updateReservationStatus(int id, String newStatus) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", newStatus);

        int rows = db.update(
                TABLE_RESERVATIONS,
                values,
                "id=?",
                new String[]{String.valueOf(id)}
        );

        db.close();
        return rows > 0;
    }

    //I will be using the update function to keep a log of all booking made into the system using
    //the attribute Status and labelling the reservation as "Cancelled"
    public boolean deleteReservation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(
                TABLE_RESERVATIONS,
                "id=?",
                new String[]{String.valueOf(id)}
        );
        db.close();
        return rows > 0;
    }

    //Notification CRUD functions
    public boolean insertNotification(NotificationItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", item.getTitle());
        values.put("message", item.getMessage());
        values.put("userType", item.getUserType());
        values.put("timestamp", item.getTimestamp());

        long result = db.insert(TABLE_NOTIFICATIONS, null, values);
        db.close();
        return result != -1;
    }

    public List<NotificationItem> getNotifications(String userType) {
        List<NotificationItem> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM notifications WHERE userType=? ORDER BY id DESC",
                new String[]{userType}
        );

        if (c.moveToFirst()) {
            do {
                list.add(new NotificationItem(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)
                ));
            } while (c.moveToNext());
        }

        c.close();
        return list;
    }
}
