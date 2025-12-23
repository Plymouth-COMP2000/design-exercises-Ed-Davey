package com.example.restaurant_app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.R;
import com.example.restaurant_app.adapters.NotificationAdapter;
import com.example.restaurant_app.database.AppDatabaseHelper;

public class StaffNotificationActivity extends AppCompatActivity {

    private AppDatabaseHelper db;
    private NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        RecyclerView recycler = findViewById(R.id.recyclerNotification);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        db = new AppDatabaseHelper(this);
        adapter = new NotificationAdapter(db.getNotifications("staff"));
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateList(db.getNotifications("staff"));
    }
}