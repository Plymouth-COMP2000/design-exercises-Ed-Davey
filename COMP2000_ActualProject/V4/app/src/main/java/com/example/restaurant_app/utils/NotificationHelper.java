package com.example.restaurant_app.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.restaurant_app.R;

public class NotificationHelper {
    public static final String CHANNEL_ID = "restaurant_channel";

    public static void createNotificationChannel(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Restaurant Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager =
                    context.getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel);
        }
    }

    public static void showNotification(Context context, String title, String message) {

        //Check for notifications being enabled
        SharedPreferences prefs =
                context.getSharedPreferences("settings", Context.MODE_PRIVATE);

        boolean enabled = prefs.getBoolean("notifications_enabled", true);

        //If not enabled then return out of the function
        if (!enabled) {
            return;
        }

        //If enabled then build the notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification_bell)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true);

        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify((int) System.currentTimeMillis(), builder.build());
    }
}
