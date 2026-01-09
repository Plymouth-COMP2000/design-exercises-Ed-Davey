package com.example.restaurant_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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
    MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_menu);

        Button btnAddMenu = findViewById(R.id.buttonAddMenuItem);
        recyclerView = findViewById(R.id.recycler_view_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new AppDatabaseHelper(this);

        btnAddMenu.setOnClickListener(v ->
                startActivity(new Intent(this, AddMenuItemActivity.class))
        );

        loadMenu();
    }

    private void loadMenu() {
        List<MenuItem> menuList = db.getAllMenuItems();
        adapter = new MenuAdapter(menuList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMenu(); // refresh after returning from AddMenuItemActivity
    }
}