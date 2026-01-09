package com.example.restaurant_app.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.R;

public class SettingsActivity extends AppCompatActivity {
    private Switch switchSettingNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchSettingNotification = findViewById(R.id.switchSettingNotification);

        SharedPreferences prefs =
                getSharedPreferences("settings", MODE_PRIVATE);

        // Notifications off on initial launch
        boolean enabled = prefs.getBoolean("notifications_enabled", false);
        switchSettingNotification.setChecked(enabled);

        // Save when updated in settings activity
        switchSettingNotification.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit()
                    .putBoolean("notifications_enabled", isChecked)
                    .apply();
        });
    }
}
