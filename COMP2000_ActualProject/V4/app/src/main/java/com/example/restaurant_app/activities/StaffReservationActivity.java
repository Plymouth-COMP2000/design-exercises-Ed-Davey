package com.example.restaurant_app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.R;
import com.example.restaurant_app.adapters.ReservationAdapter;
import com.example.restaurant_app.database.AppDatabaseHelper;

public class StaffReservationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AppDatabaseHelper db;
    private ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_reservations);

        recyclerView = findViewById(R.id.recyclerReservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new AppDatabaseHelper(this);

        loadReservations();
    }

    private void loadReservations() {
        adapter = new ReservationAdapter(db.getAllReservations(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadReservations(); // refresh list when returning
    }
}
