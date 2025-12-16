package com.example.restaurant_app.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthManager {
    private static final String PREF_NAME = "auth_prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ROLE = "role";

    public static void saveUser(Context context, String username, String role) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit()
                .putString(KEY_USERNAME, username)
                .putString(KEY_ROLE, role)
                .apply();
    }

    public static String getRole(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_ROLE, null);
    }

    public static void logout(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}
