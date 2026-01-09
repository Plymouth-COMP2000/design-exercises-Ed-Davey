package com.example.restaurant_app.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.restaurant_app.R;

public class GuestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guest_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_activity_guest_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.buttonMainGuestMenu).setOnClickListener(v ->
                startActivity(new Intent(this, GuestMenuActivity.class)));

        findViewById(R.id.buttonMainGuestBookings).setOnClickListener(v ->
                startActivity(new Intent(this, GuestReservationActivity.class)));

        findViewById(R.id.buttonMainGuestNotifications).setOnClickListener(v ->
                startActivity(new Intent(this, GuestNotificationActivity.class)));

        findViewById(R.id.buttonMainGuestSettings).setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));

        findViewById(R.id.buttonMainGuestLogout).setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}