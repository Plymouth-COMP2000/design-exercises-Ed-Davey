package com.example.restaurant_app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.R;
import com.example.restaurant_app.adapters.MenuAdapter;
import com.example.restaurant_app.database.AppDatabaseHelper;
import com.example.restaurant_app.models.MenuItem;

import java.util.List;

public class GuestMenuActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MenuAdapter adapter;
    AppDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_menu);

        recyclerView = findViewById(R.id.recyclerGuestMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new AppDatabaseHelper(this);

        List<MenuItem> menuList = db.getAllMenuItems();

        adapter = new MenuAdapter(menuList);
        recyclerView.setAdapter(adapter);
    }
}
