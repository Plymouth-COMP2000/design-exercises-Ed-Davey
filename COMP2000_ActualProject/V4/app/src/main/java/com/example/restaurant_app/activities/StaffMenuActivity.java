package com.example.restaurant_app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.database.AppDatabaseHelper;

import java.util.List;

import com.example.restaurant_app.models.MenuItem;
import com.example.restaurant_app.R;
import com.example.restaurant_app.adapters.MenuAdapter;

public class StaffMenuActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AppDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_menu);

        recyclerView = findViewById(R.id.recycler_view_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new AppDatabaseHelper(this);
        List<MenuItem> menuList = db.getAllMenuItems();

        recyclerView.setAdapter(new MenuAdapter(menuList));
    }
}
